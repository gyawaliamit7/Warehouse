import java.io.*;
public class ManufacturerIdServer implements Serializable {
  private  int idCounter;
  private static ManufacturerIdServer server;
  
  //Constructor.
  private ManufacturerIdServer() {
    idCounter = 1;
  }
  
  //UserInterface instance prepare the required instance for the ManufacturerIdServer to function.
  public static ManufacturerIdServer instance() {
    if (server == null) {
      return (server = new ManufacturerIdServer());
    } else {
      return server;
    }
  }
  
  //Function to get a new ID.
  public int getId() {
    return idCounter++;
  }
  
  //Function to return a string of the current idCounter.
  public String toString() {
    return ("IdServer" + idCounter);
  }
  
  //Function to retrieve the saved ManufacturerIdServer data after the user's latest interactions.
  public static void retrieve(ObjectInputStream input) {
    try {
      server = (ManufacturerIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
    
  }
  
  //Function to write an object to the MemberIdServer.
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
    
  }
  
  //Function to read an object from the MemberIdServer.
  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (server == null) {
        server = (ManufacturerIdServer) input.readObject();
      } else {
        input.readObject();
      }
      
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
    
  }
  
}