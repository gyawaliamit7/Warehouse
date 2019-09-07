import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static WareHouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int ADD_MANUFACTURER = 3;
  private static final int QUERY_PRODUCTS = 4;
  private static final int QUERY_PRODUCTS_BY_MANUFACTURER= 5;
  private static final int QUERY_MANUFACTURERS_AND_PRICES_OF_A_PRODUCT = 6;
  private static final int SAVE = 7;
  private static final int RETRIEVE = 8;
  private static final int ADD_MANUFACTURER_TO_PRODUCT = 9;
  private static final int REMOVE_MANUFACTURER_FROM_PRODUCT = 10;
  private static final int MAKE_PAYMENT = 11;
  private static final int MAKE_ORDER = 12;
  private static final int MAKE_ORDER_WITH_MANUFACTURER = 13;
  private static final int QUERY_OUTSTANDING_BALANCES = 14;
  private static final int QUERY_BACK_ORDERED_PRODUCT = 15;
  private static final int QUERY_BACK_ORDERED_CLIENT_PRODUCTS = 16;
  private static final int QUERY_MANUFACTURER_ORDERS = 17;
  private static final int HELP = 18;
  
  //Constructor
  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
    } else {
      warehouse = WareHouse.instance();
    }
    
  }
  
  //UserInterface instance provides window for user interactions.
  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
    
  }
  
  //Function to get user's input.
  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
        
      } catch (IOException ioe) {
        System.exit(0);
      }
      
    } while (true);
    
  }
  
  //Function to get user's input for yes or no questions.
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    
    return true;
  }
  
  //Function to get user's input when the input is a number.
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
      
    } while (true);
    
  }
  
  //Function to get date and time.
  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
      
    } while (true);
    
  }
  
  //Function to get the command a user has chosen from the interactive menu.
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
        
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
      
    } while (true);
    
  }

  //Function to show the interactive menu to the user.
  public void help() {
    System.out.println("Enter a number between 0 and 18 as explained below:");
    System.out.println(EXIT + " to Exit");
    System.out.println(ADD_CLIENT + " to add a client.");
    System.out.println(ADD_PRODUCT + " to add products.");
    System.out.println(ADD_MANUFACTURER + " to add manufacturer.");
    System.out.println(QUERY_PRODUCTS + " to print products.");
    System.out.println(QUERY_PRODUCTS_BY_MANUFACTURER + " to print products by manufacturers.");
    System.out.println(QUERY_MANUFACTURERS_AND_PRICES_OF_A_PRODUCT + " to print manufacturers and "
    		+ "different prices for a product.");
    System.out.println(SAVE + " to save data.");
    System.out.println(RETRIEVE + " to retrieve.");
    System.out.println(ADD_MANUFACTURER_TO_PRODUCT + " to add a manufacturer to a product's supplier's list\n\tand the product to the manufacturer's list of products they supply.");
    System.out.println(REMOVE_MANUFACTURER_FROM_PRODUCT + " to remove a manufacturer from a product's supply list\n\tand the product from the manufacturer's list of products they supply.");
    System.out.println(MAKE_PAYMENT + " to make a payment towards a client's outstanding balance.");
    System.out.println(MAKE_ORDER + " to make an order for a client.");
    System.out.println(MAKE_ORDER_WITH_MANUFACTURER + " to make an order for a certain manufacturer.");
    System.out.println(QUERY_OUTSTANDING_BALANCES + " to print all clients with outstanding balances.");
    System.out.println(QUERY_BACK_ORDERED_PRODUCT + " to print all products on back order.");
    System.out.println(QUERY_BACK_ORDERED_CLIENT_PRODUCTS + " to print all products on back order for a certain client.");
    System.out.println(QUERY_MANUFACTURER_ORDERS + " to print all orders placed with a manufacturer.");
    System.out.println(HELP + " for help menu.");
  }

  //Function to add a client to the warehouse.
  public void addClient() {
    String name = getToken("Enter client name:");
    String address = getToken("Enter address:");    
    String phone = getToken("Enter phone:");
    Client result;
    result = warehouse.addClient(name, address, phone);
    if (result == null) {
      System.out.println("Could not add client.");
    }
    
    System.out.println(result);
  }
  
  //Function to add a product to the warehouse.
  public void addProduct() {
	    Product result;
	    do {
	      String name = getToken("Enter product's name:");
	      String description = getToken("Enter description:");
	      result = warehouse.addProduct(name, description);
	      if (result != null) {
	        System.out.println(result);
	      } else {
	        System.out.println("Product could not be added.");
	      }
	      
	      if (!yesOrNo("Add more products?")) {
	        break;
	      }
	      
	    } while (true);
	    
	  }
  
  //Function to add a manufacturer to the warehouse.
  public void addManufacturer() {
	  String name = getToken("Enter manufacturer's name:");
	  String address = getToken("Enter address:");
	  String phone = getToken("Enter phone:");
	  Manufacturer result;
	  result = warehouse.addManufacturer(name, address, phone);
	  if (result == null) {
	    System.out.println("Could not add manufacturer.");
	  }
	  System.out.println(result);   
  }

  // Add manufacturer to Product
  public void addManufacturerToProduct() {
    String productID = getToken("Enter product  ID:");
    while(!warehouse.containsProduct(productID)) {
      productID = getToken("Enter product  ID:");
    }
    String manufacturerID = getToken("Enter manufacturer ID:");
    while(!warehouse.containsManufacturer(manufacturerID)) {
      manufacturerID = getToken("Enter manufacturer  ID:");
    }
	  Float price = Float.parseFloat(getToken("Enter price:"));
    warehouse.addManufacturerToProduct(manufacturerID, productID, price);
  }
  public void removeManufacturerFromProduct() {
    String productID = getToken("Enter product  ID:");
    while(!warehouse.containsProduct(productID)) {
      productID = getToken("Enter product  ID:");
    }
    String manufacturerID = getToken("Enter manufacturer ID:");
    while(!warehouse.containsManufacturer(manufacturerID)) {
      manufacturerID = getToken("Enter manufacturer  ID:");
    }
    warehouse.removeManufacturerFromProduct(manufacturerID, productID);
  }

  //Function to query the products available at the warehouse.
  public void queryProducts() {
      Iterator allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
	  Product product = (Product)(allProducts.next());
          System.out.println(product.toString());
      }
      
  }

  //Function to query the products supplied by a manufacturer.
  public void queryManufacturerProducts() {
    String manufacturerID = getToken("Enter manufacturer ID:");
    while(!warehouse.containsManufacturer(manufacturerID)) {
      manufacturerID = getToken("Enter manufacturer  ID:");
    }
    warehouse.queryManufacturerProducts(manufacturerID);
  }
  
  //Function to query a product for all manufacturers which supply the product.
  public void queryProductManufacturer() {
    String productID = getToken("Enter product  ID:");
    while(!warehouse.containsProduct(productID)) {
      productID = getToken("Enter product  ID:");
    }
    warehouse.queryProductManufacturer(productID);
  }
  //Function to save warehouse after the user's interactions.
  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
    } else {
      System.out.println(" There has been an error in saving \n" );
    }
    
  }
  
  //Function to retrieve the saved warehouse data after the user's latest interactions.
  private void retrieve() {
    try {
      WareHouse tempWarehouse = WareHouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has been successfully retrieved from the file WareHouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new library" );
        warehouse = WareHouse.instance();
      }
      
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  
  // Make a payment to a client's outstanding balance
  private void makePayment() {
    String clientID = getToken("Enter client's ID:");
    while(!warehouse.containsClient(clientID)) {
      clientID = getToken("Enter client's ID:");
    }

    String balance = warehouse.dispalyBalance(clientID);
    System.out.println(balance);

	  Float payment = Float.parseFloat(getToken("Enter payment amount:"));
    while(!warehouse.makePayment(clientID, payment)) {
      System.out.println("Payment must be less than or equal to outstanding balance, try again: ");
      payment = Float.parseFloat(getToken("Enter payment amount:"));
    }
    System.out.println("Payment accepted.");
  }

  // Make an order for a client
  private void makeOrder() {
    do
    {
      String clientID = getToken("Enter client's ID:");
      while(!warehouse.containsClient(clientID)) {
        clientID = getToken("Enter client's ID:");
      }
      String productID = getToken("Enter product  ID:");
      while(!warehouse.containsProduct(productID)) {
        productID = getToken("Enter product ID:");
      }
	    Integer quantity = Integer.parseInt(getToken("Enter payment amount:"));
      warehouse.makeOrder(clientID, productID, quantity);
      if(!yesOrNo("Do you want to add another product to your order")) {
        break;
      }
    } while(true);
  }


  // Order from a manufacturer created by amit
  private void makeOrderWithManufacturer() {

      Product product;
    
      String manufacturerID = getToken("Enter Manufacturer's ID:");
      while(!warehouse.containsManufacturer(manufacturerID)) {
        manufacturerID = getToken("Enter Manufacturer's ID:");
      }

    
    Manufacturer manufacturer = warehouse.searchManufacturer(manufacturerID);
    if ( manufacturer == null){
      System.out.println("Couldn't create order");
    }

    do{

      String productID = getToken("Enter Product ID");
      if (warehouse.containsManufacturerProduct(manufacturer,productID)){
        product = warehouse.searchProducts(productID);
        String q = getToken("Enter quantity");
        Integer quantity = Integer.parseInt(q);
        boolean success = warehouse.makeOrderManufacturer(manufacturer,product,quantity);
      }
      else
        System.out.println("Couldn't find product");

      if(!yesOrNo("Do you want to add another product ")) {        
        break;
      }
    } while(true);
      

    }

  

  // get a list of all clients with outstanding balances
  private void getOutstandingBalances() {
    System.out.println(warehouse.getOutstandingBalances());
  }
  // get all back ordered products
  private void getAllBackOrders() {
    warehouse.getAllBackOrders();
  }
  // get a list of products a client has ordered which are on back order
  private void getClientBackOrders() {
    warehouse.getClientBackOrders();
  }
  // get all orders made with a manufacturer
  private void getManufacturersOrders() {
    warehouse.getOrderWithManufacturer();

  }
  
  //Function to invoke the suitable functions according to the user's choice.
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:                                  addClient();
                                break;
        case ADD_PRODUCT:                                 addProduct();
        						break;
        case ADD_MANUFACTURER:                            addManufacturer();
                                break;
        case QUERY_PRODUCTS:                              queryProducts();
                                break;
        case QUERY_PRODUCTS_BY_MANUFACTURER:              queryManufacturerProducts();
                                break;
        case QUERY_MANUFACTURERS_AND_PRICES_OF_A_PRODUCT: queryProductManufacturer();
                                break;
        case SAVE:                                        save();
                                break;
        case RETRIEVE:                                    retrieve();
                                break;
        case ADD_MANUFACTURER_TO_PRODUCT:                 addManufacturerToProduct();
                                break;
        case REMOVE_MANUFACTURER_FROM_PRODUCT:            removeManufacturerFromProduct();
                                break;
        case MAKE_PAYMENT:                                makePayment();
                                break;
        case MAKE_ORDER:                                  makeOrder();
                                break;
        case MAKE_ORDER_WITH_MANUFACTURER:                makeOrderWithManufacturer();
                                break;
        case QUERY_OUTSTANDING_BALANCES:                  getOutstandingBalances();
                                break;
        case QUERY_BACK_ORDERED_PRODUCT:                  getAllBackOrders();
                                break;
        case QUERY_BACK_ORDERED_CLIENT_PRODUCTS:          getClientBackOrders();
                                break;
        case QUERY_MANUFACTURER_ORDERS:                   getManufacturersOrders();
                                break;

        case HELP:                                        help();
                                break;
      }
      
    }
    
  }
  
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
  
}
