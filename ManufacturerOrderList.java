import java.util.*;
import java.io.*;

public class ManufacturerOrderList implements Serializable {
    private static final long serialVersionUID = 1L;
    public LinkedList<ManufacturerOrder> manufacturerOrders = new LinkedList<>();
    private static ManufacturerOrderList manufacturerOrderList;

    private ManufacturerOrderList() {

    }

    public static ManufacturerOrderList instance() {
    if(manufacturerOrderList == null) {
      return (manufacturerOrderList =new ManufacturerOrderList());
    } else {
      return manufacturerOrderList;
    }
  }

    public boolean insertOrder(){
      //manufacturerOrders.add(order);
      System.out.println("lets check");
       return true;
    }



    public Iterator getOrders()
    {
    return manufacturerOrders.iterator();
    }

    private void writeObject(java.io.ObjectOutputStream output) {
          try {
              output.defaultWriteObject();
              output.writeObject(manufacturerOrderList);
          }
          catch(IOException ioe) {
              System.out.println(ioe);
          }
    }

    private void readObject(java.io.ObjectInputStream input) {
          try {
              if (manufacturerOrderList != null) {
                  return;
              }
              else {
                  input.defaultReadObject();
                  if (manufacturerOrderList == null) {
                      manufacturerOrderList = (ManufacturerOrderList) input.readObject();
                  }
                  else {
                      input.readObject();
                  }
              }
          }
          catch(IOException ioe) {
              System.out.println("in ManufacturerOrders readObject \n" + ioe);
          }
          catch(ClassNotFoundException cnfe) {
              cnfe.printStackTrace();
          }
      }
}
