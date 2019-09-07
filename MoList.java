import java.util.*;
import java.io.*;

public class MoList implements Serializable {
	private static final long serialVersionUID = 1L;
	public List manufacturerOrders = new LinkedList();
	private static MoList manufacturerOrderList;

	//constructor
	private MoList(){

	}

	public static MoList instance(){
		if(manufacturerOrderList == null) {
			return (manufacturerOrderList =new MoList());
	  } else {
		  return manufacturerOrderList;
    }
  }

	public boolean insertOrder (ManufacturerOrder manufacturer){
		manufacturerOrders.add(manufacturer);
		return true;
	}

  

 

	public Iterator getOrders(){
		return manufacturerOrders.iterator();
	}

	private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(manufacturerOrderList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  	}

  	

  	private void readObject(java.io.ObjectInputStream input) {
    try {
      if (manufacturerOrderList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (manufacturerOrderList == null) {
          manufacturerOrderList = (MoList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  


}