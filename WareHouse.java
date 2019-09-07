import java.util.*;
import java.io.*;
public class WareHouse implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int CLIENT_NOT_FOUND  = 1;
  public static final int PRODUCT_NOT_FOUND  = 2;
  public static final int MANUFACTURER_NOT_FOUND  = 3;
  private ClientList clientList;
  private ProductList productList;
  private ManufacturerList manufacturerList;
  private MoList moList;
  private static WareHouse warehouse;

  
  //Constructor
  private WareHouse() {
    clientList = ClientList.instance();
    productList = ProductList.instance();
    manufacturerList = ManufacturerList.instance();
    moList =MoList.instance();

  }
  
  //UserInterface instance prepare the required instances for the warehouse to function.
  public static WareHouse instance() {
    if (warehouse == null) {
      ClientIdServer.instance(); // instantiate all singletons
      ProductIdServer.instance(); // instantiate all singletons
      ManufacturerIdServer.instance(); // instantiate all singletons
      ManufacturerOrderIDServer.instance();


      return (warehouse = new WareHouse());
    } else {
      return warehouse;
    }
    
  }
  
  //Function to add a product to the warehouse.
  public Product addProduct(String name, String description) {
    Product product = new Product(name, description);
    if (productList.insertProduct(product)) {
      return (product);
    }
    
    return null;
  }
  
  //Function to add a client to the warehouse.
  public Client addClient(String name, String address, String phone) {
	    Client client = new Client(name, address, phone);
	    if (clientList.insertClient(client)) {
	      return (client);
	    }
	    
	    return null;
	  }

  public void searchClient(String id)
  {
    Client searchClient = clientList.searchClient(id);
    if( searchClient == null)
      System.out.println("Search unSuccessfull");
    else{
     System.out.println("Your search result is " +searchClient.toString());

    }
  }


  //searching Manufacturer
   public Manufacturer searchManufacturer(String id)
  {
    Manufacturer searchManufacturer = manufacturerList.searchManufacturer(id);
    return searchManufacturer;

  }

  //searching Product

  public Product searchProducts(String id)
  {
    Product searchProducts = productList.searchProducts(id);
    return searchProducts;

  }

  //checking if Manufacturer contains Product item

  public boolean containsManufacturerProduct(Manufacturer m ,String productID){
    if ( m.containProduct(productID))
      return true;
    else
      return false;

  }


  
  //Function to add a manufacturer to the warehouse.
  public Manufacturer addManufacturer(String name, String address, String phone) {
	    Manufacturer manufacturer = new Manufacturer(name, address, phone);
	    if (manufacturerList.insertManufacturer(manufacturer)) {
	      return (manufacturer);
	    }
	    
	    return null;
	  }
  
  //Function to get products available at the warehouse.
  public Iterator getProducts() {
      return productList.getProducts();
  }

  //Function to get clients available at the warehouse.
  public Iterator getClients() {
      return clientList.getClients();
  }
  
  //Function to get manufacturers available at the warehouse.
  public Iterator getManufacturers() {
      return manufacturerList.getManufacturers();
  }

  // add a manufactuer to a product's list of suppliers and the product to the manufacturer's list of product they supply.
  public void addManufacturerToProduct(String mID, String pID, Float price) {
    Product product = productList.searchProducts(pID);
    Manufacturer manufacturer = manufacturerList.searchManufacturer(mID);
    product.addPriceForManufactuer(mID, price);
    manufacturer.addProduct(pID);
  }

  // remove a manufactuer from a product's list of suppliers and the product from the manufacturer's list of product they supply.
  public void removeManufacturerFromProduct(String manufacturerID, String productID) {
    Product product = productList.searchProducts(productID);
    Manufacturer manufacturer = manufacturerList.searchManufacturer(manufacturerID);
    manufacturer.removeProduct(productID);
    if(product.removeManufacturer(manufacturerID)) {
      System.out.println("Removed manufacturer from product's supplier list and product from manufacturer's list of products they supply");
    }
    else {
      System.out.println("Cannot remove manufacturer form product list.");
    }
  }

  //Function to query the products supplied by a manufacturer.
  public void queryManufacturerProducts(String manufacturerID) {
    Manufacturer manufacturer = manufacturerList.searchManufacturer(manufacturerID);
    Iterator allProducts = manufacturer.getProducts();
    String pID;
    while(allProducts.hasNext()) {
      pID = (String)(allProducts.next());
      Product product = productList.searchProducts(pID);
      System.out.println(product.toString());
    }
  }
  
  //Function to query a product for all manufacturers which supply the product.
  public void queryProductManufacturer(String productID) {
    Product product = productList.searchProducts(productID);
    Iterator allManufacturers = product.getManufacturers();
    while(allManufacturers.hasNext()) {
      Price price = (Price)(allManufacturers.next());
      String manufacturerID = price.getManufacturerID();
      Manufacturer manufacturer = manufacturerList.searchManufacturer(manufacturerID);
      System.out.println(manufacturer.toString());
      String mPrice = String.valueOf(price.getPrice());
      System.out.println("Price $" + mPrice);
    }
  }

  // Checks if id is valid
  public boolean containsProduct(String ID) {
    return productList.contains(ID);
  }

  // Checks if id is valid
  public boolean containsManufacturer(String ID) {
    return manufacturerList.contains(ID);
  }
  // Checks if id is valid
  public boolean containsClient(String ID) {
    return clientList.contains(ID);
  }
  
  //Function to retrieve the saved warehouse data after the user's latest interactions.
  public static WareHouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WareHouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIdServer.retrieve(input);
      ProductIdServer.retrieve(input);
      ManufacturerIdServer.retrieve(input);
      return warehouse;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
    
  }
  
  //Function to save warehouse after the user's interactions.
  public static  boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WareHouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIdServer.instance());
      output.writeObject(ProductIdServer.instance());
      output.writeObject(ManufacturerIdServer.instance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
    
  }
  // Display client's balance
  public String dispalyBalance(String clientID) {
    Client client = clientList.searchClient(clientID);
    String balance = "Balance: " + client.getBalance();
    return balance;
  }

  // Make a payment to a client's outstanding balance
  public Boolean makePayment(String clientID, Float payment) {
    Client client = clientList.searchClient(clientID);
    return client.makePayment(payment);
  }

  // Make an order for a client
  public void makeOrder(String clientID, String productID, Integer quantity) {
    // needs to add order to order list of client
    System.out.println("Dummy Action");
  }


  //creating supplier list class created by amit
  /*public ManufacturerOrder createSupplierOrder(String id ) {
    Manufacturer manufacturer = manufacturerList.searchManufacturer(id);
    ManufacturerOrder order = new ManufacturerOrder(manufacturer);
    boolean success = moList.insertOrder(order);
    return order ; */

  //}
  // Order from a manufacturer created by amit
  public boolean makeOrderManufacturer(Manufacturer manufacturer , Product product, int quantity) {
    ManufacturerOrder order = new ManufacturerOrder(manufacturer,product,quantity);
    moList.insertOrder(order);
    return true;


  }





  

  public void getOrderWithManufacturer(){
   Iterator orders = moList.getOrders();
    while (orders.hasNext()){
      ManufacturerOrder order =(ManufacturerOrder)(orders.next());
      System.out.println("Manufacturer Name = " +order.getManufacturer() + " Product Name = " + order.getProduct() + " Quantity =" + order.getQuantity());
      System.out.println("_______________________________");

    } 
  }

  


  // get a list of all clients with outstanding balances
  public String getOutstandingBalances() {
    Iterator clients = clientList.getClients();
    String clientsBalance = "";
    while(clients.hasNext()) {
      Client client = (Client)(clients.next());
      if(client.getBalance() > 0.0) {
        clientsBalance += client.toString() + "\n";
      }
    }
    return clientsBalance;
  }
  // get all back ordered products
  public void getAllBackOrders() {
    System.out.println("Dummy Action");
  }
  // get a list of products a client has ordered which are on back order
  public void getClientBackOrders() {
    System.out.println("Dummy Action");
  }
  // get all orders made with a manufacturer
  public void getManufacturersOrders() {
    System.out.println("Dummy Action");
  }
  
  //Function to write an object to the warehouse.
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(warehouse);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
    
  }
  
  //Function to read an object from the warehouse.
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (warehouse == null) {
        warehouse = (WareHouse) input.readObject();
      } else {
        input.readObject();
      }
      
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
    
  }
  
  //Function to return a string of productList, clientList and manufacturerList components.
  public String toString() {
    return productList + "\n" + clientList + "\n" + manufacturerList;
  }
  
}