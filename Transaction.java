import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Transaction implements Serializable {
  private static final String TRANSACTION_STRING = "O";
  private String id;
  private List orders = new LinkedList();
  private static Transaction transaction;
  private ProductList productList;
  public Transaction() {
	    id = TRANSACTION_STRING + (TransactionIdServer.instance()).getId();
  }
  public static Transaction instance() {
    if (transaction == null) {
      return (transaction = new Transaction());
    } else {
      return transaction;
    }
  }

  public boolean insertOrder(Order order) {
    orders.add(order);
    return true;
  }

  public Iterator getOrders(){
     return orders.iterator();
  }

  public Order searchOrders(String id)
  {
    Iterator proIT = transaction.getOrders();
    String checkId;
    while (proIT.hasNext())
    {
      Order orderFound = (Order)(proIT.next());
      checkId = orderFound.getId();
      if (checkId.equals(id)) {
        return orderFound;
      }
    }    
    return null;
  }

  public boolean contains(String id)
  {
    Iterator proIT = transaction.getOrders();
    String checkID;
    while (proIT.hasNext())
    {
      Order orderFound = (Order)(proIT.next());
      checkID = orderFound.getId();
      if (checkID.equals(id)) {
        return true;
      }
    }    
    return false;
  }
  
  public float getTotal() {
	  	float total = 0f;
	    Iterator allOrders = transaction.getOrders();
	    while(allOrders.hasNext()) {
	      Price price = (Price)(allOrders.next());
	      Order order = (Order) (allOrders.next());
	      total += order.getquantity() * price.getPrice();
	    }
	    
	 return total;
  }
  
  public String getDate() {
	  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	    
	  return timeStamp;
	  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(transaction);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (transaction != null) {
        return;
      } else {
        input.defaultReadObject();
        if (transaction == null) {
          transaction = (Transaction) input.readObject();
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
    return "Transaction ID: +" + id + "Orders: " + orders.toString() + 
    		" Total: " + transaction.getTotal() + "Time: " + transaction.getDate();
  }
}