import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class Adminstate extends WareState implements ActionListener{
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static WareHouse warehouse;
  private static WareContext context;
  private static Adminstate instance;
  private JFrame frame;

  private static final int EXIT =0;
  private static final int MODIFY_SALES =1;
  private static final int ADD_MANUFACTURER =2;
  private static final int ADD_PROD_TO_MAN =3;
  private static final int CLERKMENU =4;
  private static final int HELP =5;
  private static final int LOGOUT =6;

  private AbstractButton modifySalesButton,addManButton,addProManuButton,clerkMenuButton,logoutButton;




  private Adminstate(){
  	super();
  	warehouse =WareHouse.instance();
  }

  public static Adminstate instance(){
  	if (instance == null){
  		instance = new Adminstate();
  	}
  	return instance;
  }

  public void actionPerformed(ActionEvent event){
 		if (event.getSource().equals(this.modifySalesButton)){
  			this.modifySales();
  		}
  		else if (event.getSource().equals(this.addManButton)){
  			this.addMan();
  		} 
  		else if (event.getSource().equals(this.addProManuButton)){
  			this.addProManu();
  		} 
  		else if (event.getSource().equals(this.clerkMenuButton)){
  			this.clerkMenu();
  		} 
  		else if (event.getSource().equals(this.logoutButton)){
  			this.logout();
  		}  	

  }

  public void logout(){
    //logout;
    (WareContext.instance()).changeState(3);
  }

  public void modifySales(){
  	System.out.println("Sales modified");

  }

  public void addMan(){
  	System.out.println("manufacturer added");

  }

  public void addProManu(){
  	System.out.println("manufacturer added to product");

  }

  public void clerkMenu(){
  	System.out.println("Clerk Menu");

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

  public void terminate(int exitcode){
  	(WareContext.instance()).changeState(exitcode);
  }

  public void help(){
    System.out.println("Enter a number between 0 and 6 as explained below:");
    System.out.println(EXIT + " To Exit \n");
    System.out.println(MODIFY_SALES + " to modify sales");
    System.out.println(ADD_MANUFACTURER+ " to add manufacturer");
    System.out.println(ADD_PROD_TO_MAN+ " to add product to manufacturer");
    System.out.println(CLERKMENU+" to switch to clerk menu");
    System.out.println(HELP+" for help");
    System.out.println(LOGOUT+" for logout");


  }
  /*
  public boolean clerkMenu(){
  	return true;
  }

  public void process(){
  	int command ,exitcode = -1;
  	help();
  	boolean done = false;
  	while(!done){
  		switch(getCommand()){
  			case MODIFY_SALES:		System.out.println("Sales modified");
  									break;
  			case ADD_MANUFACTURER:	System.out.println("Added to manufacturer");
  									break;
  			case ADD_PROD_TO_MAN: 	System.out.println("Product added to manufacturer");
  									break;
  			case CLERKMENU:			if (clerkMenu()){
  										exitcode = 1;
  										done =true;										done =true;

  										}
  									break;
  			case HELP:				help();
  									break;
  			case EXIT: 				exitcode = 2;
  									done =true;
  									break;	
  			case LOGOUT: 			logout();
  									break;	
  		}
  	}

  	terminate(exitcode);
  } 

  public void logout(){
    //logout;
    (WareContext.instance()).changeState(3);
  }*/

 public void run(){
 	//process();
	frame = WareContext.instance().getFrame();
 	frame.getContentPane().removeAll();
 	frame.getContentPane().setLayout(new FlowLayout());
 		modifySalesButton = new JButton("Modify sales");
 		addManButton =new JButton("Add manufacturer");
 		addProManuButton = new JButton("Add product to manufacturer");
 		clerkMenuButton = new JButton("Clerk Menu");
 		logoutButton = new JButton("Logout ");

 	    modifySalesButton.addActionListener(this);
 	    addManButton.addActionListener(this);
 	    addProManuButton.addActionListener(this);
 	    clerkMenuButton.addActionListener(this);
 	    logoutButton.addActionListener(this);
	
	frame.getContentPane().add(this.modifySalesButton);
	frame.getContentPane().add(this.addManButton);
	frame.getContentPane().add(this.addProManuButton);
	frame.getContentPane().add(this.clerkMenuButton);
	frame.getContentPane().add(this.logoutButton);

	frame.setVisible(true);
	frame.paint(frame.getGraphics()); 
	frame.toFront();
	frame.requestFocus();




 }

}