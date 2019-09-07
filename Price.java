import java.util.*;
import java.io.*;
public class Price implements Serializable {
  private static final long serialVersionUID = 1L;
  private String manufacturerID;
  private Float price;
  public  Price () {
    this.manufacturerID = "0";
    this.price = 0.0f;
  }

  public float getPrice() {
    return price;
  }
  public String getManufacturerID() {
    return manufacturerID;
  }
  public void setManufacturerID(String newManufacturerID) {
   manufacturerID = newManufacturerID;
  }
  public void changePrice(Float newPrice) {
    price = newPrice;
  }
}
