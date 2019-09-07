import java.util.*;
import java.io.*;
import java.lang.*;


public class ManufacturerOrder implements Serializable{
  private static final long serialVersionUID = 1L;
  private String id;
  public static final String MANUFACTURER_ORDER_STRING = "MO";
  private Manufacturer manufacturer;
  private Product product;
  private Integer quantity;
  
  public ManufacturerOrder(Manufacturer m,Product p,Integer q){
    this.manufacturer = m;
    id = MANUFACTURER_ORDER_STRING + (ManufacturerOrderIDServer.instance()).getID();
    this.product = p;
    this.quantity =q;
  }


 public void setManufacturer(Manufacturer newManufacturer){
  manufacturer = newManufacturer;

 }
  public Manufacturer getManufacturer()
  {
    return manufacturer;
  }

  public void setProduct(Product newProduct){
  product = newProduct;

 }
  public Product getProduct()
  {
    return product;
  }


  public void setQuantity(Integer newQuantity){
  quantity = newQuantity;

 }
  public Integer getQuantity()
  {
    return quantity;
  }

  
}
