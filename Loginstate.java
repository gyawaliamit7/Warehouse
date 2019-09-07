import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class Loginstate extends WareState implements ActionListener{
	private static final int CLIENT_LOGIN =0;
	private static final int CLERK_LOGIN =1;
	private static final int ADMIN_LOGIN =2;
	private static final int EXIT =2;
  	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
  	private WareContext context;
  	private JFrame frame;
  	private static Loginstate instance;
  	private AbstractButton clientButton,adminButton,clerkButton,logoutButton; 

  	private Loginstate(){
  		super();
  	}

  	public static Loginstate instance(){
  		if (instance == null){
  			instance = new Loginstate();
  		}
  		return instance;
  	}

  	public void actionPerformed(ActionEvent event){
  		if (event.getSource().equals(this.clientButton)){
  			this.client();
  		}
  		else if (event.getSource().equals(this.adminButton)){
  			this.admin();
  		} 
  		else if (event.getSource().equals(this.clerkButton)){
  			this.clerk();
  		} 
  		else if (event.getSource().equals(this.logoutButton)){
  			(WareContext.instance()).changeState(3);
  		} 

  	}

  	public void clear() { //clean up stuff
    	frame.getContentPane().removeAll();
    	frame.paint(frame.getGraphics());   
 	}  

 	public void client(){
 		(WareContext.instance()).setLogin(WareContext.isClient);
    	 clear();
    	(WareContext.instance()).changeState(0);	

 	}

 	public void admin(){
 		(WareContext.instance()).setLogin(WareContext.isClerk);
    	 clear();
    	(WareContext.instance()).changeState(2);	
	

 	}

 	public void clerk(){
 		(WareContext.instance()).setLogin(WareContext.isClerk);
    	 clear();
    	(WareContext.instance()).changeState(1);	


 	}

 	public void run(){
 		frame = WareContext.instance().getFrame();
 	  	frame.getContentPane().removeAll();
 	    frame.getContentPane().setLayout(new FlowLayout());
 	    	clientButton = new JButton("client");
 	    	clerkButton = new JButton("clerk");
 	    	adminButton = new JButton("admin");
 	    	logoutButton =new JButton("logout");

 	    	clientButton.addActionListener(this);
 	    	clerkButton.addActionListener(this);
 	    	adminButton.addActionListener(this);
 	    	logoutButton.addActionListener(this);

	    frame.getContentPane().add(this.clientButton);
	    frame.getContentPane().add(this.clerkButton);
	    frame.getContentPane().add(this.adminButton);
	    frame.getContentPane().add(this.logoutButton);
	    frame.setVisible(true);
		frame.paint(frame.getGraphics()); 
		frame.toFront();
		frame.requestFocus();
	    	
 	}


}