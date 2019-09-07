import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;
public class WareContext {

	private static int currentState;
	private static WareHouse warehouse;
	private static WareContext context;
	private int currentUser;
	private String userID;
	private static JFrame wareFrame;
	private BufferedReader reader = new BufferedReader(new
										InputStreamReader(System.in));
	public static final int isClient =0;
	public static final int isClerk =1;
	public static final int isAdmin =2 ;
	private static  WareState[] state;
	private int[][] nextState;

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


  private void retrieve() {
    try {
      WareHouse temp = WareHouse.retrieve();
      if (temp != null) {
        System.out.println(" The WareHouse has been successfully retrieved from the file Warehouse Data \n" );
        warehouse = temp;
      } else {
        System.out.println("File doesnt exist; creating new WareHouse" );
        warehouse = WareHouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }

  public void setLogin(int code){
  	currentUser = code;
  }
  public void setUser(String uID){
  	userID = uID;
  }

  public int getLogin(){
  	return currentUser;
  }

  public String getUser(){
  	return userID;
  }

  public JFrame getFrame(){
  	return wareFrame;
  }

  //constructor
  private WareContext(){
  	state = new WareState[4];
  	state[0] = Clientstate.instance();
  	state[1] = Clerkstate.instance();
  	state[2] = Adminstate.instance();
  	state[3] = Loginstate.instance();

  	//need to understand this thing
  	nextState = new int[4][4];
  	nextState[0][0] =2 ; nextState[0][1] = 1; nextState[0][2] =2; nextState[0][3] =2;
  	nextState[1][0] =2 ; nextState[1][1] = 1; nextState[1][2] =2; nextState[1][3] =2;
  	nextState[2][0] =2 ; nextState[2][1] = 1; nextState[2][2] =2; nextState[2][3] =2;
  	nextState[3][0] =2 ; nextState[3][1] = 1; nextState[3][2] =2; nextState[3][3] =2;
  	currentState =3;
  	wareFrame = new JFrame("Warehouse GUI");
  	wareFrame.addWindowListener(new WindowAdapter(){
  		public void windowClosing(WindowEvent e){
  			System.exit(0);
  		}
  	});

  	wareFrame.setSize(400,400);
  	wareFrame.setLocation(400,400);

  }

  public void changeState(int transition){
  	currentState =nextState[currentState][transition];
  	if (currentState == -2){
  		System.out.println("Wrror has occured");
  		terminate();
  	}
  	if (currentState == -1){
  		terminate();

  	}

  	state[currentState].run();
  }



  private void terminate()
  {
   if (yesOrNo("Save data?")) {
      if (warehouse.save()) {
         System.out.println(" The Warehouse has been successfully saved in the file LibraryData \n" );
       } else {
         System.out.println(" There has been an error in saving \n" );
       }
     }
   System.out.println(" Goodbye \n "); System.exit(0);
  }

  public static WareContext instance(){
  	if(context == null){
  		System.out.println("calling constructor");
  		context = new WareContext();
  	}
  	return context;
  }

  public void process(){
  	state[currentState].run();
  }

	public static void main (String[] args){
    	WareContext.instance().process(); 
    	state[currentState].run();
  	}




}