import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private float balance;
  private static final String CLIENT_STRING = "C";
  private List transactions = new LinkedList();
  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.balance = 0;
    id = CLIENT_STRING + (ClientIdServer.instance()).getId();
  }

  public String getName() {
    return name;
  }
  public String getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public float getBalance() {
     return balance;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }
  public void addToBalance(float charge) {
   balance = charge;
  }
  public Boolean makePayment(float payment) {
    if(payment <= balance) {
      balance -= payment;
      return true;
    }
    else {
      return false;
    }   
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public Iterator getTransactions() {
    return transactions.iterator();
  }
  public void addTransaction(float transactionID) {
    System.out.println("Dummy Action");
  }
  public String toString() {
    String string = "Client name " + name + "\naddress " + address + "\nid " + id + "\nphone " + phone + "\nbalance " + balance;
    return string;
  }
}
