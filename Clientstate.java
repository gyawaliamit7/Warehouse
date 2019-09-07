import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class Clientstate extends WareState implements ActionListener{
 private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 private static WareHouse warehouse;
 private static WareContext context;
 private static Clientstate instance;
 private JFrame frame;

 private static final int EXIT =0;
 private static final int PLACE_ORDER =1;
 private static final int CHECK_PRICE =2;
 private static final int VIEW_ACCOUNT =3 ;
 private static final int HELP =4;
 private static final int LOGOUT =5;

 private AbstractButton placeOrderButton,checkPriceButton,viewAccountButton,helpButton,logoutButton;



 private Clientstate(){
 	warehouse =WareHouse.instance();
 }

 public static Clientstate instance(){
 	if(instance ==null){
 		instance = new Clientstate();
 	}
 	return instance;
 }

public void actionPerformed(ActionEvent event){
  		if (event.getSource().equals(this.placeOrderButton)){
  			this.placeOrder();
  		}
  		else if (event.getSource().equals(this.checkPriceButton)){
  			this.checkPrice();
  		} 
  		else if (event.getSource().equals(this.viewAccountButton)){
  			this.viewAccount();
  		} 
  		else if (event.getSource().equals(this.logoutButton)){
  			this.logout();
  		} 
  	}

public void logout(){
  	//logout;
  	(WareContext.instance()).changeState(3);
  }

public void placeOrder(){
	System.out.println("Order Placed");
}

public void checkPrice(){
	System.out.println("Price checked");
}

public void viewAccount(){
	System.out.println("View Account");
}


 public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help(){
  	 System.out.println("Enter a number between 0 and 5 as explained below:");
  	 System.out.println(EXIT + " to Exit\n");
  	 System.out.println(PLACE_ORDER + " to place Order");
  	 System.out.println(CHECK_PRICE + " to check Price");
  	 System.out.println(VIEW_ACCOUNT + "to view Account");
  	 System.out.println(HELP + "for help");
  	 System.out.println(LOGOUT + "for logout");


  }
/*
  public void process(){
  	int command;
  	help();
  	while ((command = getCommand()) !=EXIT){
  		switch(command){
  		case PLACE_ORDER: 	System.out.println("Order placed");
  							break;
  		case CHECK_PRICE: 	System.out.println("Price checked");
  							break;
  		case VIEW_ACCOUNT: 	System.out.println("Account viewed");
  							break;
  		case HELP: 			help();
  							break;
  		case LOGOUT:		logout();
  							break;
  		}
  	}
  }*/

  public void run(){
  	//process();

  	frame = WareContext.instance().getFrame();
 	frame.getContentPane().removeAll();
 	frame.getContentPane().setLayout(new FlowLayout());
 		placeOrderButton = new JButton("Place Order");
 		checkPriceButton = new JButton("Check Price");
 		viewAccountButton = new JButton("View Account");
 		logoutButton = new JButton("Logout");

 		placeOrderButton.addActionListener(this);
 		checkPriceButton.addActionListener(this);
 		viewAccountButton.addActionListener(this);
 		logoutButton.addActionListener(this);

	frame.getContentPane().add(this.placeOrderButton);
	frame.getContentPane().add(this.checkPriceButton);
	frame.getContentPane().add(this.viewAccountButton);
	frame.getContentPane().add(this.logoutButton);
	frame.setVisible(true);
	frame.paint(frame.getGraphics()); 
	frame.toFront();
	frame.requestFocus();






  }

  




}