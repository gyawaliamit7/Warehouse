//Group 3, Genavieve Holmberg
import java.util.*;
import java.io.*;
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;
  private String productID;
  private String manufacturerID;
  private Integer quantity;
  private String id;
  private static final String ORDER_STRING = "O";
  public  Order (String productID, String manufacturerID, Integer quantity) {
    this.productID = productID;
    this.manufacturerID = manufacturerID;
    this.quantity = quantity;
    id = ORDER_STRING + (OrderIdServer.instance()).getId();
  }

  public String getProductID() {
    return productID;
  }
  public String getManufacturerID() {
    return manufacturerID;
  }
  public Integer getquantity() {
    return quantity;
  }
  public String getId() {
    return id;
  }
  public void setProductID(String newProductID) {
    productID = newProductID;
  }
  public void setManufacturerID(String newManufacturerID) {
    manufacturerID = newManufacturerID;
  }
  public void setquantity(Integer newquantity) {
    quantity = newquantity;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Product ID " + productID + "\nquantity " + quantity + "\nManufacturer ID " + manufacturerID + "\nid " + id;
    return string;
  }
}
