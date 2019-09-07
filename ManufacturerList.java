import java.util.*;
import java.io.*;

public class ManufacturerList implements Serializable {
	private static final long serialVersionUID = 1L;
	public List manufacturers = new LinkedList();
  public List manufacturersOrder = new LinkedList();


	private static ManufacturerList manufacturerList;

	//constructor
	private ManufacturerList(){

	}

	public static ManufacturerList instance(){
		if(manufacturerList == null) {
			return (manufacturerList =new ManufacturerList());
	  } else {
		  return manufacturerList;
    }
  }

	public boolean insertManufacturer (Manufacturer manufacturer){
		manufacturers.add(manufacturer);
		return true;
	}

  public Manufacturer searchManufacturer(String id)
  {
    Iterator allManufacturer = manufacturerList.getManufacturers();
     while (allManufacturer.hasNext())
     {
      Manufacturer newManufacturer = (Manufacturer)(allManufacturer.next());
      String checkId = newManufacturer.getId();
      if (checkId.equals(id))
      {
        return newManufacturer;
      }
     }    
     return null;
  }

  public boolean contains(String id)
  {
    Iterator allManufacturer = manufacturerList.getManufacturers();
     while (allManufacturer.hasNext())
     {
      Manufacturer newManufacturer = (Manufacturer)(allManufacturer.next());
      String checkId = newManufacturer.getId();
      if (checkId.equals(id)) {
        return true;
      }
     }    
     return false;
  }

	public Iterator getManufacturers(){
		return manufacturers.iterator();
	}

	private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(manufacturerList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  	}

  	//removeManufacturer yet to be implemented

  	//searchManufacturer yet to be implemented

  	private void readObject(java.io.ObjectInputStream input) {
    try {
      if (manufacturerList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (manufacturerList == null) {
          manufacturerList = (ManufacturerList) input.readObject();
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
  public String toString() {
    return manufacturers.toString();
  }


}