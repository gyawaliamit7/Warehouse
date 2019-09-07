import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class Clerkstate extends WareState implements ActionListener{
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static WareHouse warehouse;
  private static WareContext context;
  private static Clerkstate instance;
  private JFrame frame;

  private static final int EXIT = 0;
  private static final int ADD_CLIENT =1;
  private static final int ADD_PRODUCT =2;
  private static final int DISPLAY_PRODUCT =3;
  private static final int DISPLAY_MANUFACTURER =4;
  private static final int LOAD_DATABASE =5;
  private static final int CLIENTMENU =7;
  private static final int HELP = 8;
  private static final int LOGOUT =9;

  private AbstractButton addClientButton, addProductButton, displayProductButton,
                        displayManufacturerButton,loadDatabaseButton,clientMenuButton,helpButton,logoutButton;



  private Clerkstate(){
  	super();
  	warehouse =WareHouse.instance();
  }

  public static Clerkstate instance(){
  	if (instance == null){
  		instance = new Clerkstate();
  	}
  	return instance;
  }

  public void actionPerformed(ActionEvent event){
    if (event.getSource().equals(this.addClientButton)){
        this.addClient();
      }
    else if (event.getSource().equals(this.addProductButton)){
        this.addProduct();
      } 
    else if (event.getSource().equals(this.displayProductButton)){
        this.displayProduct();
      } 
    else if (event.getSource().equals(this.displayManufacturerButton)){
        this.displayManufacturer();
      } 
    else if (event.getSource().equals(this.loadDatabaseButton)){
        this.loadDatabase();
      } 
    else if (event.getSource().equals(this.clientMenuButton)){
        this.clientMenu();
      } 
    else if (event.getSource().equals(this.logoutButton)){
        this.logout();
      }     
  }

  public void logout(){
    //logout;
    (WareContext.instance()).changeState(3);
  }

  public void addClient(){
    System.out.println("Client added");

  }

  public void addProduct(){
    System.out.println("Product added");

  }

  public void displayProduct(){
    System.out.println("Product Displayed");

  }

  public void displayManufacturer(){
    System.out.println("Manufacturer Displayed");

  }

  public void loadDatabase(){
    System.out.println("Database Displayed");

  }

  public void clientMenu(){
    System.out.println("clientMenu Displayed");

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

  /*public boolean clientMenu(){
  	return true;
  }*/

  public void help(){
  	System.out.println("Enter a number between 0 and 9 as explained below:");
  	System.out.println(EXIT + "To Exit \n");
  	System.out.println(ADD_CLIENT+ "To add client");
  	System.out.println(ADD_PRODUCT+ "To add Product");
  	System.out.println(DISPLAY_PRODUCT+ "To display product");
  	System.out.println(DISPLAY_MANUFACTURER+ "To display Manufacturer");
  	System.out.println(LOAD_DATABASE + "to load database");
  	System.out.println(CLIENTMENU + "to switch to client menu");
  	System.out.println(HELP+ "for help");
    System.out.println(LOGOUT + "for logout");




  }
/*
  public void process(){
  	int command ,exitcode = -1;
  	help();
  	boolean done =false;
  	while(!done){
  		switch(getCommand()){
  			case ADD_PRODUCT:			     System.out.println("Product Added");
  										             break;
  			case ADD_CLIENT: 			     System.out.println("Client Added");
  										              break;
  			case DISPLAY_PRODUCT: 		System.out.println("Product Displayed");
  										            break;
  			case DISPLAY_MANUFACTURER:	System.out.println("Manufacturer Displayed");
  										              break;
  			case LOAD_DATABASE: 		System.out.println("Database Loaded");
  										          break;
  			case CLIENTMENU: 				if (clientMenu()){
  											           exitcode =0;
  											           done =true;
 										              }
 										           break;

 			case HELP:					     help();
 										           break;
 			case EXIT: 				 exitcode = 1;
 										     done =true;
 										     break;
      case LOGOUT:       logout();
                         break;


  		}
  	}
  	terminate(exitcode);

  } */ 



public void run(){
	//process();
  frame = WareContext.instance().getFrame();
  frame.getContentPane().removeAll();
  frame.getContentPane().setLayout(new FlowLayout());
    addClientButton = new JButton ("Add Client");
    addProductButton = new JButton ("Add Product");
    displayProductButton =new JButton("Display Product");
    displayManufacturerButton = new JButton("Display Manufacturer");
    loadDatabaseButton = new JButton("loadDatabase");
    clientMenuButton = new JButton("Client Menu");
    logoutButton = new JButton("logout");

    addClientButton.addActionListener(this);
    addProductButton.addActionListener(this);
    displayProductButton.addActionListener(this);
    displayManufacturerButton.addActionListener(this);
    loadDatabaseButton.addActionListener(this);
    clientMenuButton.addActionListener(this);
    logoutButton.addActionListener(this);

  frame.getContentPane().add(this.addClientButton);
  frame.getContentPane().add(this.addProductButton);
  frame.getContentPane().add(this.displayProductButton);
  frame.getContentPane().add(this.displayManufacturerButton);
  frame.getContentPane().add(this.loadDatabaseButton);
  frame.getContentPane().add(this.clientMenuButton);
  frame.getContentPane().add(this.logoutButton);

  frame.setVisible(true);
  frame.paint(frame.getGraphics()); 
  frame.toFront();
  frame.requestFocus();



  
}


}