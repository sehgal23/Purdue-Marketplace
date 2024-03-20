# CS180---Project-5

## Instructions - How to Compile and Run
- To simulate the MarketPlace, first compile MarketPlaceServer.java in your local terminal with: 
      **javac MarketplaceServer.java**
      Run **java MarketplacePortalServer** directly after.
- Next, compile MarketPlaceClientGUI.java in your local terminal with:
       **javac MarketplaceClientGUI.java**
       Run **java MarketplaceClientGUI** directly after.
- Once you run the program, the the graphical user interfaces will guide you through the marketplace
- **Notes when using the MarketPlace:**
  - A user must log in or create an account to see the customer/seller interface
  - A user can only delete an account if they are logged in 
  - **Cart items are not held:** when a customer adds an item to their cart, another customer can check out the item and sell it out even though the item is in the first customer's cart. If the first customer tries to check out after the other customer (i.e. after the product has been sold out), then the program will inform them that the product was unable to be purchased. 
  - If exporting a CSV file, the exported CSV file will only appear in the directory AFTER the user exits the Marketplace program
  - CSV Files should be formatted in the following way: [name],[description],[quantity],[price]
  - Ensure to include the FULL filepath of the imported CSV if the imported CSV is not in the same directory of the cloned repository
  - For Seller - do NOT include a dash in the product name; this is an invalid product input.
  - When seller removes a store, removes all products of that store from marketplace, but does not remove the products from the customer’s cart immediately, instead waits until customer checks out then sends error message.
       - The store deleted along with its products will not appear in statistics for both customers and sellers unless one of the products has been purchased by a customer before the store was removed. 

## Project 4 Bugs Fixed: 
- add to cart items do not decrement from existing products in market
- customer and seller statistics fixed
- new statistics implemented (that was supposed to be implemented in project 4) 
     - Sellers can view a list of their sales by store, including customer information and revenues from the sale.
     - Sellers can view the number of products currently in customer shopping carts, along with the store and details associated with the products

## Submission Details 
- Priyanka Soe - Submitting all solution code, test cases, and documentation on Vocareum by cloning the repository
- Priyanka Soe - Submitting the written report on Brightspace
- Priyanka Soe - Submitting video presentation
- All - Submitting peer evaluation form(s) 

## Class Descriptions 

Product.java
------------
Functionality
- This class creates a product for a store
- The class ensures that each product has a name, associated StoreName, description, intial and current quanitity and price
- It also has setter and getter method to change any of the field variables of the product or to even create a new product for any given store

Relationship 
- The Store classe uses an arraylist of product to search for a given product above a certain thresold in terms of price and quantity
- The MarketPlace class uses an arraylist of products to add products to a given file and to search for a product based on the file.

--------------
Store.java
---------------
Functionality
- Creates a store for a seller
- Each store will have name, seller email and an arraylist of products
- The store class has a few methods goes through the entire arraylist of products and return products above a certain thresold based on price and quantity
- It also has setters and getters to access the store and change its field variables

Relationship
- A seller takes in an arraylist of stores and and can add, delete or modify stores based on the products

-------------------
Marketplace.java
----------------
Functionality
- Creates a Marketplace for the main portal
- Creates sorting and searching methods used in MarketplaceServer
- Fields include an array list of sellers and an array list of products

Relationship
- Does not write directly to .txt files, but can change array lists that are taken in parameters of writeFile() and readFile() methods

-------------------
Customer.java
----------------
Functionality
- Creates a customer object
- Fields include a name, email, password, purchasedProducts (arraylist of Product objects), shoppingCart (arraylist of Product objects) 
- Contains two constructors, remove from cart, add to cart, purchase product, custom equals, and relevant toString / view methods.
  
Relationship
- Does not write directly to .txt files, but can change array lists that are taken in parameters of writeFile() and readFile() methods

-------------------
Seller.java
----------------
Functionality
- Creates a seller object
- Fields include a name, email, password, storeList (arraylist of Store objects) 
- Contains two constructors, necessary setters/getters, custom equals, relevant toString / view methods
  
Relationship
- Does not write directly to .txt files, but can change array lists that are taken in parameters of writeFile() and readFile() methods

-------------------
MarketplaceClientGUI.java
---------------------
Functionality
- Takes the inputs in from the User
- Sends inputs to MarketplaceServer baseed on the task the server needs to complete
- Reads outputs from MarketplaceServer which are then displayed on graphical user interfaces
  
Relationship
- Uses Sockets to connect the MarketplaceServer.java

--------------------
MarketplaceServer.java
---------------------
Functionality
- Receives inputs from the MarketplaceClientGUI which are then carried out by the methods and switch cases in this class. 
- Handles all exceptions related to Datamanager 
- Handles all the backend work for Display All Products, Search Products, Select a product, Manage Shopping Cart, View Purchase History, Access Statistics, Delete account, Log out
- Handles all the backend work for Manage stores, Access Statistics, Delete account, Log out
- Allows mutliple clients to connect to this class, the server. 

Relationship
- Uses Sockets to connect to various MarketplaceClientGUIs
- Calls static DataManager methods directly to change/update .txt and .csv files
- Utilizes instances of every other class

-------------------
DataManager.java
--------------
Functionality
- Creates reading and writing to files for type Product, Customer, and Seller
- Reading takes in no parameters and returns an ArrayList of the type specified (Product, Customer, or Seller)
- Writing takes in a parameter of a specified type (Product, Customer, or Seller), and for products, the fileName
- Also allows functionality for user inputting of CSV of products, and exporting CSV file of products

Relationship
- Most user options in MarketplacePortal.java require reading from files and updating the content of these files to save them.
- Creates new CSV file when prompted in MarketplacePortal

-------------------
NotEnoughStockError.java
--------------
Functionality
- custom exception to be thrown when customer attempts to purchase more than the stock available

Relationship
- used in Customer.java
--------------
