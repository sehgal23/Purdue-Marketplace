import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Data Manager
 * <p>
 * This program creates file input
 * and output methods to store data
 * in the marketplace, pertaining to
 * products, customers, and sellers.
 *
 * @author Alan Kang, Priyanka Soe, Sanketh Edara, Arnav Sehgal, Eamon Mukhopadhyay
 * *
 * @version November 13, 2023
 */

public class DataManager {

    /**
     * Reads the "products.txt" file and creates an ArrayList of products.
     *
     * @return an ArrayList of type <Product>.
     */
    public static ArrayList<Product> readProductsFile(String filename) throws IOException {
        ArrayList<Product> returnProducts = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String readLine = bf.readLine();
            while (readLine != null) {
                String[] attributes = readLine.split(",");
                String productName = attributes[0];
                if (!(productName.equals("Name"))) {
                    String storeName = attributes[1];
                    String description = attributes[2];
                    int initialQuantity = Integer.parseInt(attributes[3]);
                    int currQuantity = Integer.parseInt(attributes[4]);
                    double price = Double.parseDouble(attributes[5]);
                    returnProducts.add(new Product(productName, storeName, description, initialQuantity, currQuantity,
                            price));
                    readLine = bf.readLine();
                } else {
                    readLine = bf.readLine();
                }
            }

        } catch (IOException error) {
            return null;
        } catch (NullPointerException npe) {
            return null;
        } catch (Exception e) {
            return null;
        }

        return returnProducts;
    }

    // Exports products from a store to a csv file of a given name.
    public static void exportProducts(String storeName, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            ArrayList<Product> products = readProductsFile("products.txt");
            for (int i = 0; i < products.size(); i++) {
                if (storeName.equals(products.get(i).getStoreName())) {
                    writer.println(
                            products.get(i).getName() + "," +
                                    products.get(i).getDescription() + "," +
                                    products.get(i).getCurrQuantity() + "," +
                                    products.get(i).getPrice());
                }
            }
            writer.close();
        } catch (IOException io) {
            System.out.println("Error occurred in exporting products.");
        }
    }

    // Imports products from a csv file of a given name to a store of a given name and email
    public static Store addStoreProducts(String name, String sellerEmail, String file) throws FileNotFoundException,
            ExistingProductException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<Product> products = new ArrayList<>();
        int counter = 0;
        try {
            String readLine = reader.readLine();
            counter += 1;
            String[] attributes;
            while (readLine != null) {
                attributes = readLine.split(",");
                products.add(new Product(attributes[0],
                        name,
                        attributes[1],
                        Integer.parseInt(attributes[2]),
                        Double.parseDouble(attributes[3])));
                ArrayList<Product> currProducts = readProductsFile("products.txt");
                if (currProducts != null) {
                    for (Product p : currProducts) {
                        if (p.getName().equals(attributes[0])) {
                            throw new ExistingProductException("Product with name [" + attributes[0]
                                    + "] exists in the " +
                                    "market.");
                        }
                    }
                }
                updateProducts("products.txt", new Product(attributes[0],
                        name,
                        attributes[1],
                        Integer.parseInt(attributes[2]),
                        Double.parseDouble(attributes[3])));
                readLine = reader.readLine();
            }
            Store store = new Store(products, sellerEmail, name);
            return store;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Adds or updates the "Products.txt" file with a product.
     * If the product name already exists, its attributes will be
     * adjusted.
     * Calls the readProductsFile() method.
     *
     * @param product product representing the product to be added to the file
     */
    public static void updatePurchasedProducts(String filename, Product product) throws IOException {
        boolean enabled = false;
        ArrayList<Product> products = readProductsFile(filename);
        //Objects.requireNonNull(products);
        PrintWriter writer = new PrintWriter(new FileWriter(filename));
        writer.println("Name,Store,Description,InitialQuantity,CurrentQuantity,Price");

        if (products == null) {
            products = new ArrayList<Product>();
            products.add(product);
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().equals(product.getName())) {
                    enabled = true;
                    products.get(i).setName(product.getName());
                    products.get(i).setStoreName(product.getStoreName());
                    products.get(i).setDescription(product.getDescription());
                    products.get(i).setInitialQuantity(product.getInitialQuantity());
                    products.get(i).setCurrQuantity(product.getCurrQuantity() + product.getCurrQuantity());
                    products.get(i).setPrice(product.getPrice());
                    break;
                }
            }

            if (!enabled && product.getCurrQuantity() != 0) {
                products.add(product);
            }
        }

        for (int i = 0; i < products.size(); i++) {

            String productName = products.get(i).getName();
            String storeName = products.get(i).getStoreName();
            String description = products.get(i).getDescription();
            String initialQuantity = String.valueOf(products.get(i).getInitialQuantity());
            String currQuantity = String.valueOf(products.get(i).getCurrQuantity());
            String price = String.valueOf(products.get(i).getPrice());
            String writeLine = productName + "," + storeName + "," + description
                    + "," + initialQuantity + "," + currQuantity + "," + price;
            writer.println(writeLine);
        }

        writer.flush();
        writer.close();
    }

    public static void updateProducts(String filename, Product product) throws IOException {
        boolean enabled = false;
        ArrayList<Product> products = readProductsFile(filename);
        //Objects.requireNonNull(products);
        PrintWriter writer = new PrintWriter(new FileWriter(filename));
        writer.println("Name,Store,Description,InitialQuantity,CurrentQuantity,Price");

        if (products == null) {
            products = new ArrayList<Product>();
            products.add(product);
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().equals(product.getName())) {
                    enabled = true;
                    products.get(i).setName(product.getName());
                    products.get(i).setStoreName(product.getStoreName());
                    products.get(i).setDescription(product.getDescription());
                    products.get(i).setInitialQuantity(product.getInitialQuantity());
                    products.get(i).setCurrQuantity(product.getCurrQuantity());
                    products.get(i).setPrice(product.getPrice());
                    break;
                }
            }

            if (!enabled && product.getCurrQuantity() != 0) {
                products.add(product);
            }
        }

        for (int i = 0; i < products.size(); i++) {

            String productName = products.get(i).getName();
            String storeName = products.get(i).getStoreName();
            String description = products.get(i).getDescription();
            String initialQuantity = String.valueOf(products.get(i).getInitialQuantity());
            String currQuantity = String.valueOf(products.get(i).getCurrQuantity());
            String price = String.valueOf(products.get(i).getPrice());
            String writeLine = productName + "," + storeName + "," + description
                    + "," + initialQuantity + "," + currQuantity + "," + price;
            writer.println(writeLine);
        }

        writer.flush();
        writer.close();
    }

    // removes product from specified file
    public static void removeProduct(String filename, Product product) throws IOException {
        boolean enabled = false;
        ArrayList<Product> products = readProductsFile(filename);
        Objects.requireNonNull(products);
        PrintWriter writer = new PrintWriter(new FileWriter(filename));
        products.add(product);
        writer.println("Name,Store,Description,InitialQuantity,CurrentQuantity,Price");
        for (int i = 0; i < products.size(); i++) {
            if (!(products.get(i).getName().equals(product.getName()))) {
                String productName = products.get(i).getName();
                String storeName = products.get(i).getStoreName();
                String description = products.get(i).getDescription();
                String initialQuantity = String.valueOf(products.get(i).getInitialQuantity());
                String currQuantity = String.valueOf(products.get(i).getCurrQuantity());
                String price = String.valueOf(products.get(i).getPrice());
                String writeLine = productName + "," + storeName + "," + description
                        + "," + initialQuantity + "," + currQuantity + "," + price;
                writer.println(writeLine);
            }
        }

        writer.flush();
        writer.close();
    }

    /**
     * Writes a customer receipt txt file customers can export.
     * Will be exported in the form [customer name]-Receipt.txt
     *
     * @param customer customer whose receipt to be written
     */
    public static void writeCustomerReceipt(Customer customer) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(customer.getName() + "-Receipt.txt"));
            writer.write(customer.getName() + "'s Purchased Products\n");
            for (Product p : customer.getPurchasedProducts()) {
                writer.write(p.toString() + "\n");
            }

            writer.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    /**
     * Reads the "customerInformation.txt" file and creates an ArrayList of customers.
     *
     * @return an ArrayList of type <Customer>.
     */
    public static ArrayList<Customer> readCustomerAccountInformation() throws IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        int counter = 0;
        BufferedReader reader = new BufferedReader(new FileReader("CustomerInformation.txt"));
        try {
            String readLine = reader.readLine();
            while (readLine != null) {
                counter += 1;
                String[] attributes = readLine.split(",");
                ArrayList<Product> customerPurchasedProducts = new ArrayList<>();
                ArrayList<Product> shoppingCart = new ArrayList<>();
                // represents ALL purchased products in the market
                ArrayList<Product> purchasedProducts = readProductsFile("PurchasedProducts.txt");
                // represents ALL available products in the market
                ArrayList<Product> products = readProductsFile("products.txt");
                String name = attributes[0];
                if (!name.equals("Name")) {
                    String email = attributes[1];
                    String password = attributes[2];
                    if (attributes.length >= 4 && !(attributes[3].equals("null")) && purchasedProducts != null) {
                        try {
                            String[] purchasedProductNames = attributes[3].split(";");
                            for (int i = 0; i < purchasedProductNames.length; i++) {
                                String[] purchasedProductsAttributes = purchasedProductNames[i].split("-");
                                // search through PurchasedProducts.txt instead of Products.txt
                                for (int j = 0; j < purchasedProducts.size(); j++) {
                                    if (purchasedProductsAttributes[0].equals(purchasedProducts.get(j).getName())) {
                                        Product purchasedProductToAdd = purchasedProducts.get(j);
                                        purchasedProductToAdd.setCurrQuantity(Integer
                                                .parseInt(purchasedProductsAttributes[1]));
                                        customerPurchasedProducts.add(purchasedProductToAdd);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Exception");
                            customerPurchasedProducts = null;
                        }
                    } else {
                        customerPurchasedProducts = null;
                    }


                    if (attributes.length >= 5 && !(attributes[4].equals("null")) && products != null) {
                        String[] shoppingCartProductNames = attributes[4].split(";");
                        for (int i = 0; i < shoppingCartProductNames.length; i++) {
                            try {
                                String[] productAttributes = shoppingCartProductNames[i].split("-");
                                for (int j = 0; j < products.size(); j++) {
                                    if (productAttributes[0].equals(products.get(j).getName())) {
                                        Product productToAdd = products.get(j);
                                        productToAdd.setCurrQuantity(Integer.parseInt(productAttributes[1]));
                                        shoppingCart.add(productToAdd);
                                    }
                                }
                            } catch (Exception e) {
                                shoppingCart = null;
                            }
                        }
                    } else {
                        shoppingCart = null;
                    }


                    if (shoppingCart == null && customerPurchasedProducts == null) {
                        customers.add(new Customer(name, email, password));
                    } else if (shoppingCart == null) {
                        customers.add(new Customer(name, email, password,
                                customerPurchasedProducts, null));
                    } else if (customerPurchasedProducts == null) {
                        customers.add(new Customer(name, email, password, null, shoppingCart));
                    } else {
                        customers.add(new Customer(name, email, password, customerPurchasedProducts, shoppingCart));
                    }


                    readLine = reader.readLine();


                } else {
                    readLine = reader.readLine();
                }
            }


        } catch (IOException error) {
            error.printStackTrace();
            return null;
        }

        return customers;
    }

    public static Customer identifyCustomer(String email) {
        try {
            ArrayList<Customer> customers = DataManager.readCustomerAccountInformation();
            if (customers != null) {
                for (Customer c : customers) {
                    if (c.getEmail().equals(email)) {
                        return c;
                    }
                }
            }
        } catch (IOException io) {
            System.out.println("Could not find CustomerInformation.txt");
        }
        return null;
    }

    public static Seller identifySeller(String email) {
        try {
            ArrayList<Seller> sellers = DataManager.readSellerAccountInformation();
            if (sellers != null) {
                for (Seller s : sellers) {
                    if (s.getEmail().equals(email)) {
                        return s;
                    }
                }
            }
        } catch (IOException io) {
            System.out.println("Could not find SellerInformation.txt");
        }
        return null;
    }

    public static Product identifyProduct(String name) {
        try {
            ArrayList<Product> products = DataManager.readProductsFile("products.txt");
            if (products != null) {
                for (Product p : products) {
                    if (p.getName().equals(name)) {
                        return p;
                    }
                }
            }
        } catch (IOException io) {
            System.out.println("Could not find products.txt");
        }
        return null;
    }

    public static Store identifyStore(String storeName) {
        try {
            ArrayList<Seller> sellers = DataManager.readSellerAccountInformation();

            for (Seller s : sellers) {
                for (Store store : s.getStoreList()) {
                    if (store.getName().equals(storeName)) {
                        return store;
                    }
                }
            }
        } catch (IOException io) {
            System.out.println("Could not find SellerInformation.txt");
        }
        return null;
    }


    /**
     * Adds or updates the "CustomerInformation.txt" file with a customer.
     * If the customer email already exists, its attributes will be
     * adjusted.
     * Calls the readCustomerInformation() method.
     *
     * @param customer customer representing customer to be added to a file.
     */
    public static void updateCustomerInformation(Customer customer) throws IOException {
        boolean enabled = false;
        ArrayList<Customer> customers = readCustomerAccountInformation();
        Objects.requireNonNull(customers);
        PrintWriter writer = new PrintWriter(new FileWriter("CustomerInformation.txt"));
        writer.println("Name,Email,Password,Purchased Products,Shopping Cart");
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equals(customer.getEmail())) {
                enabled = true;
                customers.get(i).setName(customer.getName());
                customers.get(i).setEmail(customer.getEmail());
                customers.get(i).setPassword(customer.getPassword());
                customers.get(i).setShoppingCart(customer.getShoppingCart());
                customers.get(i).setPurchasedProducts(customer.getPurchasedProducts());
                break;
            }
        }


        if (!enabled) {
            customers.add(customer);
        }


        for (int i = 0; i < customers.size(); i++) {
            String name = customers.get(i).getName();
            String email = customers.get(i).getEmail();
            String password = customers.get(i).getPassword();
            String purchasedProducts;
            String shoppingCart;
            if (!(customers.get(i).getPurchasedProducts() == null)) {
                purchasedProducts = "";
                for (int j = 0; j < customers.get(i).getPurchasedProducts().size(); j++) {
                    if (j == customers.get(i).getPurchasedProducts().size() - 1) {
                        purchasedProducts += customers.get(i).getPurchasedProducts().get(j).getName() + "-"
                                + customers.get(i).getPurchasedProducts().get(j).getCurrQuantity();
                    } else {
                        purchasedProducts += customers.get(i).getPurchasedProducts().get(j).getName() + "-"
                                + customers.get(i).getPurchasedProducts().get(j).getCurrQuantity() + ";";
                    }
                }
                if (purchasedProducts.isEmpty()) {
                    purchasedProducts = null;
                }
            } else {
                purchasedProducts = null;
            }


            if (!(customers.get(i).getShoppingCart() == null)) {
                shoppingCart = "";
                for (int j = 0; j < customers.get(i).getShoppingCart().size(); j++) {
                    if (j == customers.get(i).getShoppingCart().size() - 1) {
                        shoppingCart += customers.get(i).getShoppingCart().get(j).getName() + "-"
                                + customers.get(i).getShoppingCart().get(j).getCurrQuantity();
                    } else {
                        shoppingCart += customers.get(i).getShoppingCart().get(j).getName() + "-"
                                + customers.get(i).getShoppingCart().get(j).getCurrQuantity() + ";";
                    }
                }
                if (shoppingCart.isEmpty()) {
                    shoppingCart = null;
                }
            } else {
                shoppingCart = null;
            }


            if (purchasedProducts == null && shoppingCart == null) {
                String writeLine = name + "," + email + "," + password + "," + "null"
                        + "," + "null";
                writer.println(writeLine);
            } else if (purchasedProducts == null) {
                String writeLine = name + "," + email + "," + password + "," + "null"
                        + "," + shoppingCart;
                writer.println(writeLine);
            } else if (shoppingCart == null) {
                String writeLine = name + "," + email + "," + password + ","
                        + purchasedProducts + "," + "null";
                writer.println(writeLine);
            } else {
                String writeLine = name + "," + email + "," + password + "," + purchasedProducts
                        + "," + shoppingCart;
                writer.println(writeLine);


            }
        }


        writer.flush();
        writer.close();


    }


    /**
     * Overwrite "customerInformation.txt" file with an ArrayList of customers.
     *
     * @param customers representing the list of customers to overwrite in customerInformation.txt
     */
    public static void writeCustomerInformation(ArrayList<Customer> customers) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("customerInformation.txt"));
        writer.println("Name,Email,Password,Purchased Products,Shopping Cart");
        for (int i = 0; i < customers.size(); i++) {
            writer.write(customers.get(i).toText() + "\n");
        }
        writer.close();
    }

    /**
     * Reads the "sellerInformation.txt" file and creates an ArrayList of sellers.
     *
     * @return an ArrayList of type <Seller>.
     */
    public static ArrayList<Seller> readSellerAccountInformation() throws IOException {
        ArrayList<Seller> sellers = new ArrayList<>();
        int counter = 0;
        BufferedReader reader = new BufferedReader(new FileReader("SellerInformation.txt"));
        try {
            String readLine = reader.readLine();
            while (readLine != null) {
                counter += 1;
                String[] attributes = readLine.split(",");
                ArrayList<Store> stores = new ArrayList<Store>();
                ArrayList<Product> products = readProductsFile("products.txt");
                ArrayList<Product> storeProducts = new ArrayList<>();
                String name = attributes[0];
                if (!(name.equals("Name"))) {
                    String email = attributes[1];
                    String password = attributes[2];
                    if (attributes.length == 4 && products != null) {
                        String[] storeNames = attributes[3].split(";");
                        for (int i = 0; i < storeNames.length; i++) {
                            storeProducts = new ArrayList<>();
                            for (int j = 0; j < products.size(); j++) {
                                if (products.get(j).getStoreName().equals(storeNames[i])) {
                                    storeProducts.add(products.get(j));
                                }
                            }
                            stores.add(new Store(storeProducts, email, storeNames[i]));
                        }
                        sellers.add(new Seller(name, email, password, stores));
                    } else {
                        stores = null;
                        sellers.add(new Seller(name, email, password));
                    }
                    readLine = reader.readLine();

                } else {
                    readLine = reader.readLine();
                }
            }

        } catch (IOException error) {
            return null;
        }

        return sellers;

    }

    /**
     * Adds or updates the "sellerInformation.csv" file with a seller.
     * If the seller email already exists, its attributes will be
     * adjusted.
     * Calls the readSellerInformation() method.
     *
     * @param seller seller representing seller to be added to a file.
     */

    public static void updateSellerInformation(Seller seller) throws IOException {
        boolean enabled = false;
        ArrayList<Seller> sellers = DataManager.readSellerAccountInformation();
        PrintWriter writer = new PrintWriter(new FileWriter("SellerInformation.txt"));
        writer.println("Name,Email,Password,Stores");
        Objects.requireNonNull(sellers);
        for (int i = 0; i < sellers.size(); i++) {
            if (sellers.get(i).getEmail().equals(seller.getEmail())) {
                enabled = true;
                sellers.get(i).setName(seller.getName());
                sellers.get(i).setEmail(seller.getEmail());
                sellers.get(i).setPassword(seller.getPassword());
                sellers.get(i).setStoreList(seller.getStoreList());
                break;
            }
        }

        if (!enabled) {
            sellers.add(seller);
        }


        for (int i = 0; i < sellers.size(); i++) {
            String name = sellers.get(i).getName();
            String email = sellers.get(i).getEmail();
            String password = sellers.get(i).getPassword();
            String storeNames = "";
            if (!(sellers.get(i).getStoreList() == null)) {
                for (int j = 0; j < sellers.get(i).getStoreList().size(); j++) {
                    if (j == sellers.get(i).getStoreList().size() - 1) {
                        storeNames += sellers.get(i).getStoreList().get(j).getName();
                    } else {
                        storeNames += sellers.get(i).getStoreList().get(j).getName() + ";";
                    }
                }
                String writeLine = name + "," + email + "," + password + "," + storeNames;
                writer.println(writeLine);
            } else {
                String writeLine = name + "," + email + "," + password + "," + storeNames;
                writer.println(writeLine);

            }

        }
        writer.flush();
        writer.close();
    }

    // writes over the SellerInformation.txt file from the parameter seller.
    public static void writeSellerInformation(ArrayList<Seller> seller) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("SellerInformation.txt"));
        writer.println("Name,Email,Password,Stores");
        for (int i = 0; i < seller.size(); i++) {
            writer.write(seller.get(i).toText() + "\n");
        }
        writer.close();
    }

}
