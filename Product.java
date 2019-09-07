import java.util.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String description;
  private String id;
  private static final String PRODUCT_STRING = "P";
  private LinkedList<Price> manufacturerList = new LinkedList();
  public  Product (String name, String description) {
    this.name = name;
    this.description = description;
    id = PRODUCT_STRING + (ProductIdServer.instance()).getProductId();
  }

  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setDescription(String newDescription) {
    description = newDescription;
  }
  
  public boolean removeManufacturer(String manufacturerID) {
    Iterator manIT = this.getManufacturers();
    String checkID;
    while (manIT.hasNext())
    {
      Price manFound = (Price)(manIT.next());
      checkID = manFound.getManufacturerID();
      if (checkID.equals(manufacturerID)) {
        manIT.remove();
        return true;        
      }
    }
    return false;
  }
  public Iterator getManufacturers() {
    return manufacturerList.iterator();
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public void addPriceForManufactuer(String manufacturerID, float price) {
    Price tempPrice = new Price();
    tempPrice.setManufacturerID(manufacturerID);
    tempPrice.changePrice(price);
    manufacturerList.add(tempPrice);
  }
  public void changePriceForManufactuer(String manufacturerID, float price) {
    System.out.println("Dummy Action");
  }
  public String toString() {
    String string = "Product name " + name + "\ndescription " + description + " \nid " + id;
    return string;
  }
}
