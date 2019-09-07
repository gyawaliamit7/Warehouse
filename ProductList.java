import java.util.*;
import java.io.*;
public class ProductList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List products = new LinkedList();
  private static ProductList productList;
  private ProductList() {
  }
  public static ProductList instance() {
    if (productList == null) {
      return (productList = new ProductList());
    } else {
      return productList;
    }
  }

  public boolean insertProduct(Product product) {
    products.add(product);
    return true;
  }

  public Iterator getProducts(){
     return products.iterator();
  }

  public Product searchProducts(String id)
  {
    Iterator proIT = productList.getProducts();
    String checkId;
    while (proIT.hasNext())
    {
      Product productFound = (Product)(proIT.next());
      checkId = productFound.getId();
      if (checkId.equals(id)) {
        return productFound;
      }
    }    
    return null;
  }

  public boolean contains(String id)
  {
    Iterator proIT = productList.getProducts();
    String checkID;
    while (proIT.hasNext())
    {
      Product productFound = (Product)(proIT.next());
      checkID = productFound.getId();
      if (checkID.equals(id)) {
        return true;
      }
    }    
    return false;
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(productList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (productList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (productList == null) {
          productList = (ProductList) input.readObject();
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
    return products.toString();
  }
}
