import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MarketplaceServer {

    public static final Object addStoreWithCSV = new Object();

    public static final Object addBlankStore = new Object();
    public static final Object addProduct = new Object();


    public static void main(String[] args) {
        ServerSocket server = null;

        try {

            server = new ServerSocket(4242);
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                        + client.getInetAddress()
                        .getHostAddress());

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            System.out.println("");
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    System.out.println("");
                }
            }
        }
    }

    public static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            try {

                // get the outputstream of client
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);

                // get the inputstream of client
                in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));

                while (true) {
                    String action = in.readLine();
                    ArrayList<Seller> sellers;
                    Marketplace marketplace;

                    switch (action) {
                        case "customerLogin":
                            String[] loginInput = in.readLine().split(",");
                            String name = loginInput[0];
                            String email = loginInput[1];
                            String password = loginInput[2];

                            String loginOutput = "";
                            try {
                                Customer potentialCustomer = DataManager.identifyCustomer(email);
                                if (potentialCustomer != null) {
                                    if (potentialCustomer.getName().equals(name) && potentialCustomer.getPassword()
                                            .equals(password)) {
                                        loginOutput = "Login Successful";
                                    } else {
                                        loginOutput = "Unable to login - wrong credentials.";
                                    }
                                } else {
                                    loginOutput = "An account with the specified email does not exist. You may create" +
                                            " an account instead.";
                                }
                            } catch (Exception e) {
                                // TODO: get rid of
                                System.out.println("");
                            }

                            out.write(loginOutput);
                            out.println();
                            out.flush();
                            break;
                        case "customerCreateAccount":
                            String[] createAccountInput = in.readLine().split(",");
                            String newName = createAccountInput[0];
                            String newEmail = createAccountInput[1];
                            String newPassword = createAccountInput[2];

                            String createAccOutput = customerCreateAccount(newName, newEmail, newPassword);

                            out.write(createAccOutput);
                            out.println();
                            out.flush();
                            break;
                        case "displayAllProducts":
                            String productsList = "";
                            try {
                                ArrayList<Product> products = DataManager.readProductsFile("products.txt");
                                if (products != null) {
                                    for (Product p : products) {
                                        if (p.getCurrQuantity() != 0) {
                                            productsList += p + "\n";
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                productsList = "Error in reading products catalog." + "\n";
                            }

                            out.write(productsList);
                            out.println();
                            out.flush();
                            break;
                        case "searchByKeyword":
                            sellers = DataManager.readSellerAccountInformation();
                            marketplace = new Marketplace(sellers);

                            String searchTerm = in.readLine();
                            ArrayList<Product> productsContaining = marketplace.productsContaining(searchTerm);

                            String output = "";
                            if (!productsContaining.isEmpty()) {
                                for (Product product : productsContaining) {
                                    if (product.getCurrQuantity() != 0) {   // only display product
                                        // if quantity is 0
                                        output += product.toString() + "\n";
                                    }
                                }
                            }
                            if (output.isEmpty()) {
                                output = "No products match your search." + "\n";
                            }
                            out.write(output);
                            out.println();
                            out.flush();
                            break;
                        case "searchByStoreName":
                            sellers = DataManager.readSellerAccountInformation();
                            marketplace = new Marketplace(sellers);

                            String searchStore = in.readLine();
                            System.out.println(searchStore);
                            String[] searchByStore = marketplace.searchByStore(searchStore);

                            String storeOutput = "";
                            if (searchByStore.length != 0) {
                                for (String productWithStore : searchByStore) {
                                    System.out.println(productWithStore);
                                    storeOutput += productWithStore + "\n";
                                }
                            } else {
                                storeOutput = "No products exist in this store." + "\n";
                            }
                            System.out.println("output: " + storeOutput);
                            out.write(storeOutput);
                            out.println();
                            out.flush();
                            break;
                        case "searchByPriceThreshold":
                            sellers = DataManager.readSellerAccountInformation();
                            marketplace = new Marketplace(sellers);

                            String searchByThreshResults = "";

                            double price = Double.parseDouble(in.readLine());
                            int higherLower = Integer.parseInt(in.readLine());

                            String[] searchByThresh;
                            if (higherLower == 1) {
                                searchByThresh = marketplace.searchByPrice(price,
                                        false);
                            } else {
                                searchByThresh = marketplace.searchByPrice(price,
                                        true);
                            }

                            if (searchByThresh.length != 0) {
                                for (String string : searchByThresh) {
                                    searchByThreshResults += "" + string + "\n";
                                }
                            } else {
                                searchByThreshResults = "No products match your search." + "\n";
                            }
                            out.write(searchByThreshResults);
                            out.println();
                            out.flush();
                            break;
                        case "sortByPrice":
                            sellers = DataManager.readSellerAccountInformation();
                            marketplace = new Marketplace(sellers);

                            String sortPriceResults = "";
                            try {
                                String[] productsByPrice = marketplace.sortProductsByPrice();
                                for (String string : productsByPrice) {
                                    sortPriceResults += "" + string + "\n";
                                }
                            } catch (NullPointerException npe) {
                                sortPriceResults = "No products in the marketplace yet!" + "\n";
                            } catch (Exception e) {
                                sortPriceResults = "An error occurred." + "\n";
                            }
                            out.write(sortPriceResults);
                            out.println();
                            out.flush();
                            break;
                        case "sortByQuantity":
                            sellers = DataManager.readSellerAccountInformation();
                            marketplace = new Marketplace(sellers);

                            String quantitySort = "";
                            try {
                                String[] sortedByQuantity = marketplace.sortProductsByQuantity();
                                for (String string : sortedByQuantity) {
                                    quantitySort += "" + string + "\n";
                                }
                            } catch (NullPointerException npe) {
                                quantitySort = "No products in the marketplace yet!" + "\n";
                            } catch (Exception e) {
                                quantitySort = "An error occurred." + "\n";
                            }
                            out.write(quantitySort);
                            out.println();
                            out.flush();
                            break;
                        case "selectProduct":
                            String selectedProductName = in.readLine();
                            boolean foundProduct = false;
                            String selectProductOutput = "";
                            ArrayList<Product> products = DataManager.readProductsFile("products.txt");
                            if (products != null) {
                                for (Product p : products) {
                                    if (p.getName().equals(selectedProductName) && p.getCurrQuantity() != 0) {
                                        selectProductOutput = p.toString();
                                        foundProduct = true;
                                    }
                                }
                            } else {
                                selectProductOutput = "No products exist in the market currently.";
                            }

                            if (!foundProduct) {
                                selectProductOutput = "Product could not be found or is out of stock!";
                            }

                            System.out.println(selectProductOutput);

                            out.write(selectProductOutput);
                            out.println();
                            out.flush();
                            break;
                        case "addToCart":
                            String[] addToCartInput = in.readLine().split(",");
                            String addToCartProductName = addToCartInput[0];
                            int addToCartQuantity = Integer.parseInt(addToCartInput[1]);
                            String customerEmail = addToCartInput[2];
                            boolean productInCart = false;

                            String addToCartOutput = "";

                            Product addToCartProduct = DataManager.identifyProduct(addToCartProductName);
                            Customer customer = DataManager.identifyCustomer(customerEmail);
                            if (addToCartProduct != null) {
                                if (customer.getShoppingCart() != null) {
                                    for (Product currCartProduct : customer.getShoppingCart()) {
                                        if (addToCartProduct.getName().equals(currCartProduct.getName())) {
                                            if ((currCartProduct.getCurrQuantity()
                                                    + addToCartQuantity) > addToCartProduct.getCurrQuantity()) {
                                                addToCartOutput
                                                        = "Unable to add to cart because the stock available of " +
                                                        addToCartProduct.getName() +
                                                        " is " + addToCartProduct.getCurrQuantity() + " and "
                                                        + "you tried to add " + addToCartQuantity + " to your " +
                                                        "cart that already has " + currCartProduct.getCurrQuantity()
                                                        + " " +
                                                        "of this product in it." + "\n";
                                                productInCart = true;
                                            }
                                        }
                                    }
                                }
                                if (addToCartProduct.getCurrQuantity() < addToCartQuantity && !productInCart) {
                                    addToCartOutput = "Unable to add to cart because the stock available of " +
                                            addToCartProduct.getName() +
                                            " is " + addToCartProduct.getCurrQuantity() + " and "
                                            + "you tried to add " + addToCartQuantity + " to your " +
                                            "cart." + "\n";
                                } else if (!productInCart) {
                                    if (customer != null) {
                                        customer.addToCart(new Product(addToCartProductName,
                                                addToCartProduct.getStoreName(), addToCartProduct.getDescription(),
                                                addToCartQuantity, addToCartQuantity, addToCartProduct.getPrice()));
                                        DataManager.updateCustomerInformation(customer);
                                        addToCartOutput = "Successfully added to cart!" + "\n";
                                    } else {
                                        addToCartOutput = "An error occurred while adding your product to cart." + "\n";
                                    }
                                }
                            } else {
                                addToCartOutput = "An error occurred while adding your product to cart." + "\n";
                            }

                            out.write(addToCartOutput);
                            out.println();
                            out.flush();
                            break;
                        case "viewCart":
                            String viewCartEmail = in.readLine();
                            String viewCartOutput = "";
                            Customer viewCartCustomer = DataManager.identifyCustomer(viewCartEmail);
                            if (viewCartCustomer != null) {
                                for (Product p : viewCartCustomer.getShoppingCart()) {
                                    viewCartOutput += p.toString() + "\n";
                                }
                            } else {
                                viewCartOutput = "Error accessing shopping cart." + "\n";
                            }

                            out.write(viewCartOutput);
                            out.println();
                            out.flush();
                            break;
                        case "removeFromCart":
                            String[] removeCartInput = in.readLine().split(",");
                            String removeCartEmail = removeCartInput[0];
                            String prodName = removeCartInput[1];
                            String removeFromCartOutput = "";

                            Customer removeCartCustomer = DataManager.identifyCustomer(removeCartEmail);
                            if (removeCartCustomer != null) {
                                // if product is in cart / was able to be removed
                                if (removeCartCustomer.removeFromCart(prodName)) {
                                    DataManager.updateCustomerInformation(removeCartCustomer);
                                    removeFromCartOutput = "Successfully removed product." + "\n";
                                } else {
                                    removeFromCartOutput = "No products of name [" + prodName + "] exist in" +
                                            " your cart." + "\n";
                                }
                            } else {
                                removeFromCartOutput = "Error removing from cart." + "\n";
                            }

                            out.write(removeFromCartOutput);
                            out.println();
                            out.flush();
                            break;
                        case "checkout":
                            String checkoutEmail = in.readLine();
                            String checkoutOutput = checkout(checkoutEmail);

                            out.write(checkoutOutput);
                            out.println();
                            out.flush();
                            break;
                        case "viewPurchaseHistory":
                            String viewPurchasesEmail = in.readLine();
                            String viewPurchasesOutput = "";
                            Customer viewPurchasesCustomer = DataManager.identifyCustomer(viewPurchasesEmail);
                            if (viewPurchasesCustomer != null) {
                                for (Product p : viewPurchasesCustomer.getPurchasedProducts()) {
                                    viewPurchasesOutput += p.toString() + "\n";
                                }
                            } else {
                                viewPurchasesOutput = "Error accessing shopping cart." + "\n";
                            }

                            out.write(viewPurchasesOutput);
                            out.println();
                            out.flush();
                            break;
                        case "exportPurchaseHistory":
                            String exportHistoryEmail = in.readLine();
                            String exportPurchaseHistoryOutput = "";

                            Customer exportHistoryCustomer = DataManager.identifyCustomer(exportHistoryEmail);
                            if (exportHistoryCustomer != null) {
                                DataManager.writeCustomerReceipt(exportHistoryCustomer);
                                exportPurchaseHistoryOutput = "Successfully exported receipt!" + "\n";
                            } else {
                                exportPurchaseHistoryOutput = "Unable to export receipt." + "\n";
                            }

                            out.write(exportPurchaseHistoryOutput);
                            out.println();
                            out.flush();
                            break;
                        // customer statistics
                        case "statistics":
                            String[] customerStatisticsInput = in.readLine().split(",");
                            sellers = DataManager.readSellerAccountInformation();
                            marketplace = new Marketplace(sellers);

                            String statisticsOutput = "";

                            String statisticsEmail = customerStatisticsInput[0];
                            int statisticsChoice = Integer.parseInt(customerStatisticsInput[1]);
                            int customerStatisticsSort = Integer.parseInt(customerStatisticsInput[2]);

                            Customer currCustomer = DataManager.identifyCustomer(statisticsEmail);

                            if (currCustomer != null) {
                                ArrayList<Store> storesStatistics = null;
                                if (statisticsChoice == 1) {  // make stores list all the stores in the marketplace
                                    storesStatistics = (marketplace.getStores());
                                } else if (statisticsChoice == 2) {  // make stores list the stores the customer
                                    // has purchased from
                                    storesStatistics = (marketplace.getStoresPurchasedFrom(currCustomer));
                                } else if (statisticsChoice == 3) {  // exit statistics menu
                                    break;
                                }

                                // if no stores exist or customer has not purchased anything
                                if (storesStatistics == null || storesStatistics.isEmpty()) {
                                    statisticsOutput = (statisticsChoice == 1) ? "No stores exist in the market!" +
                                            "\n" :
                                            "You have not purchased from any stores yet!";
                                } else {
                                    switch (customerStatisticsSort) {
                                        case 1:   // alphabetical sort
                                            for (int i = 0; i < storesStatistics.size() - 1; i++) {
                                                for (int j = 0; j < storesStatistics.size() - i - 1; j++) {
                                                    if (storesStatistics.get(j).getName().compareTo
                                                            (storesStatistics.get(j + 1).getName()) > 0) {
                                                        Store temp = storesStatistics.get(j);
                                                        storesStatistics.set(j, storesStatistics.get(j + 1));
                                                        storesStatistics.set(j + 1, temp);
                                                    }
                                                }
                                            }
                                            for (Store store : storesStatistics) {
                                                statisticsOutput += store.toString() + "Products Sold: " +
                                                        store.getQuantitySold() + "\n~" + "\n";
                                            }
                                            break;
                                        case 2:   // ascending by products sold sort
                                            for (int i = 0; i < storesStatistics.size() - 1; i++) {
                                                for (int j = 0; j < storesStatistics.size() - i - 1; j++) {
                                                    if (storesStatistics.get(j).getQuantitySold() >
                                                            storesStatistics.get(j + 1).getQuantitySold()) {
                                                        Store temp = storesStatistics.get(j);
                                                        storesStatistics.set(j, storesStatistics.get(j + 1));
                                                        storesStatistics.set(j + 1, temp);
                                                    }
                                                }
                                            }
                                            for (Store store : storesStatistics) {
                                                statisticsOutput += store.toString() + "Products Sold: "
                                                        + store.getQuantitySold() + "\n~" + "\n";
                                            }
                                            break;
                                        case 3:   // descending by products sold sort
                                            for (int i = 0; i < storesStatistics.size() - 1; i++) {
                                                for (int j = 0; j < storesStatistics.size() - i - 1; j++) {
                                                    if (storesStatistics.get(j).getQuantitySold() <
                                                            storesStatistics.get(j + 1).getQuantitySold()) {
                                                        Store temp = storesStatistics.get(j);
                                                        storesStatistics.set(j, storesStatistics.get(j + 1));
                                                        storesStatistics.set(j + 1, temp);
                                                    }
                                                }
                                            }
                                            for (Store store : storesStatistics) {
                                                statisticsOutput += store.toString() + "Products Sold: "
                                                        + store.getQuantitySold() + "\n~" + "\n";
                                            }
                                            break;
                                    }
                                }
                            } else {
                                statisticsOutput = "Error computing statistics." + "\n";
                            }

                            System.out.println(statisticsOutput);
                            out.write(statisticsOutput);
                            out.println();
                            out.flush();
                            break;
                        case "customerDeleteAccount":
                            String deleteEmail = in.readLine();
                            String deleteOutput = "";

                            ArrayList<Customer> customers = DataManager.readCustomerAccountInformation();
                            if (customers != null) {
                                for (Customer c : customers) {
                                    if (c.getEmail().equals(deleteEmail)) {
                                        customers.remove(c);
                                        for (Customer test : customers) {
                                            System.out.println(test.toText());
                                        }
                                        deleteOutput = "Successfully deleted account and logged out!" + "\n";
                                        break;
                                    }
                                }
                                DataManager.writeCustomerInformation(customers);
                            } else {
                                deleteOutput = "Error deleting account." + "\n";
                            }

                            out.write(deleteOutput);
                            out.println();
                            out.flush();
                            break;
                        case "sellerLogin":
                            String[] sellerLoginInput = in.readLine().split(",");
                            System.out.println(sellerLoginInput[0]);
                            String sellerName = sellerLoginInput[0];
                            String sellerEmail = sellerLoginInput[1];
                            String sellerPassword = sellerLoginInput[2];
                            String sellerLoginOutput = "";
                            try {
                                Seller potentialSeller = DataManager.identifySeller(sellerEmail);
                                if (potentialSeller != null) {
                                    if (potentialSeller.getName().equals(sellerName) && potentialSeller.getPassword()
                                            .equals(sellerPassword)) {
                                        sellerLoginOutput = "Login Successful";
                                    } else {
                                        sellerLoginOutput = "Unable to login - wrong credentials.";
                                    }
                                } else {
                                    sellerLoginOutput =
                                            "An account with the specified email does not exist. You may create" +
                                            " an account instead.";
                                }
                            } catch (Exception e) {
                                // TODO: get rid of
                                System.out.println("");
                            }

                            out.write(sellerLoginOutput);
                            out.println();
                            out.flush();
                            break;
                        case "sellerCreateAccount":
                            String[] createSellerAccountInput = in.readLine().split(",");
                            String newSellerName = createSellerAccountInput[0];
                            String newSellerEmail = createSellerAccountInput[1];
                            String newSellerPassword = createSellerAccountInput[2];

                            String createSellerAccOutput = sellerCreateAccount(newSellerName, newSellerEmail,
                                    newSellerPassword);

                            out.write(createSellerAccOutput);
                            out.println();
                            out.flush();
                            break;
                        case "sellerDisplayStores":
                            System.out.println("activated");
                            String displayStoreEmail = in.readLine();
                            String stringToSend = "";
                            sellers = DataManager.readSellerAccountInformation();
                            for (Seller seller : sellers) {
                                if (seller.getEmail().equals(displayStoreEmail)) {
                                    System.out.println(seller.viewStores());
                                    String initialText = seller.viewStores();
                                    String adjustedString = initialText.replaceAll("(?m)^[ \t]*\r?\n",
                                            "");
                                    System.out.println(adjustedString);
                                    out.write(adjustedString);
                                    out.println();
                                    out.flush();
                                    out.write("Stop");
                                    out.println();
                                    out.flush();
                                    break;
                                }
                            }
                            break;
                        case "removeStore":
                            String[] removeStoreInput = in.readLine().split(",");
                            String removeStoreEmail = removeStoreInput[0];
                            String removeStoreName = removeStoreInput[1];
                            String removeFromStoreOutput = "";
                            Seller removeStoreSeller = DataManager.identifySeller(removeStoreEmail);
                            ArrayList<Store> currSellerStores = removeStoreSeller.getStoreList();
                            ArrayList<Product> removeStoreProducts = DataManager
                                    .readProductsFile("products.txt");


                            if (removeStoreName.isEmpty()) {
                                out.write("Store Name cannot be empty.");
                                out.println();
                                out.flush();
                                break;
                            }

                            boolean removed = false;
                            for (int k = 0; k < currSellerStores.size(); k++) {
                                if (currSellerStores.get(k).getName().equalsIgnoreCase(removeStoreName)) {
                                    currSellerStores.remove(k);
                                    removeStoreSeller.setStoreList(currSellerStores);
                                    removed = true;
                                }
                            }
                            for (int i = 0; i < removeStoreProducts.size(); i++) {
                                if (removeStoreProducts.get(i).getStoreName().equals(removeStoreName)) {
                                    try {
                                        DataManager.removeProduct("products.txt", removeStoreProducts.get(i));
                                    } catch (Exception e) {
                                        System.out.println("unable to find file products.txt");
                                    }
                                }
                            }
                            if (!removed) {
                                out.write("Could not locate store.");
                                out.println();
                                out.flush();
                                break;
                            } else {
                                try {
                                    DataManager.updateSellerInformation(removeStoreSeller);
                                    out.write("Store removed successfully!");
                                    out.println();
                                    out.flush();
                                } catch (Exception e) {
                                    out.write("There was an error removing the given store.");
                                    out.println();
                                    out.flush();
                                }
                            }
                            break;
                        case "storeExists":
                            String[] storeExistsInput = in.readLine().split(",");
                            String storeName = storeExistsInput[0];
                            String emailStoreExists = storeExistsInput[1];
                            Seller storeExistsSeller = DataManager.identifySeller(emailStoreExists);
                            ArrayList<Store> currSellerStoresExists = storeExistsSeller.getStoreList();
                            if (storeName.isEmpty()) {
                                out.write("Please enter a valid input.");
                                out.println();
                                out.flush();
                                break;
                            }

                            Store selectedStore = null;
                            for (int k = 0; k < currSellerStoresExists.size(); k++) {
                                if (currSellerStoresExists.get(k).getName().equalsIgnoreCase(storeName)) {
                                    selectedStore = currSellerStoresExists.get(k);
                                }
                            }

                            if (selectedStore == null) {
                                out.write("Error selecting store.");
                                out.println();
                                out.flush();
                                break;
                            } else {
                                out.write("Correct");
                                System.out.println("Correct");
                                out.println();
                                out.flush();
                                if (selectedStore.getProductList() == null) {
                                    out.write("Stop");
                                    System.out.println();
                                    out.println();
                                    out.flush();
                                } else {
                                    for (int i = 0; i < selectedStore.getProductList().size(); i++) {
                                        out.write(selectedStore.getProductList().get(i).getName());
                                        out.println();
                                        out.flush();
                                        out.write(selectedStore.getProductList().get(i).getStoreName());
                                        out.println();
                                        out.flush();
                                        out.write(selectedStore.getProductList().get(i).getDescription());
                                        out.println();
                                        out.flush();
                                        out.write(String.valueOf(selectedStore.getProductList().get(i).getPrice()));
                                        out.println();
                                        out.flush();
                                        out.write(String.valueOf(selectedStore.getProductList().get(i)
                                                .getInitialQuantity()));
                                        out.println();
                                        out.flush();
                                    }
                                    out.write("Stop");
                                    out.println();
                                    out.flush();
                                }
                            }
                            break;
                        case "productExists":
                            String[] productExistsInput = in.readLine().split(",");
                            String storeNameProduct = productExistsInput[0];
                            String productName = productExistsInput[1];
                            Store selectedStoreProduct = DataManager.identifyStore(storeNameProduct);
                            boolean foundProductToEdit = false;
                            Product productToEdit = null;
                            assert selectedStoreProduct != null;
                            for (Product product : selectedStoreProduct.getProductList()) {
                                if (product.getName().equals(productName)) {
                                    productToEdit = product;
                                    foundProductToEdit = true;
                                    break;
                                }
                            }
                            if (!foundProductToEdit) {
                                System.out.printf("No product found matching name " +
                                        "\"%s\"\n", productName);
                                out.write("Error finding product");
                                out.println();
                                out.flush();
                            } else {
                                out.write("Success");
                                out.println();
                                out.flush();
                                out.write(productToEdit.getName());
                                out.println();
                                out.flush();
                                out.write(productToEdit.getStoreName());
                                out.println();
                                out.flush();
                                out.write(productToEdit.getDescription());
                                out.println();
                                out.flush();
                                out.write(String.valueOf(productToEdit.getCurrQuantity()));
                                out.println();
                                out.flush();
                                out.write(String.valueOf(productToEdit.getPrice()));
                                out.println();
                                out.flush();
                                out.write("Stop");
                                out.println();
                                out.flush();
                            }
                            break;
                        case "editProductDescription":
                            String[] description = in.readLine().split(",");
                            String selectedStoreProductNameDesc = description[0];
                            String selectedStoreDescription = description[1];
                            String newDescription = description[2];
                            if (newDescription.isEmpty()) {
                                out.write("empty");
                                out.println();
                                out.flush();
                                break;
                            }
                            System.out.println(selectedStoreProductNameDesc + "," + selectedStoreDescription + ","
                                    + newDescription);
                            Store selectedStoreDesc = DataManager.identifyStore(selectedStoreDescription);
                            Product descriptionProductToEdit = null;
                            for (Product product : selectedStoreDesc.getProductList()) {
                                if (product.getName().equals(selectedStoreProductNameDesc)) {
                                    descriptionProductToEdit = product;
                                    break;
                                }
                            }
                            descriptionProductToEdit.setDescription(newDescription);
                            DataManager.updateProducts(
                                    "products.txt", descriptionProductToEdit);
                            out.write("Description successfully edited!");
                            out.println();
                            out.flush();
                            break;
                        case "editProductQuantity":
                            String[] quantity = in.readLine().split(",");
                            String selectedStoreProductNameQuantity = quantity[0];
                            String selectedStoreQuantity = quantity[1];
                            int newQuantity = Integer.parseInt(quantity[2]);
                            Store selectedStoreQuan = DataManager.identifyStore(selectedStoreQuantity);
                            Product quantityProductToEdit = null;
                            for (Product product : selectedStoreQuan.getProductList()) {
                                if (product.getName().equals(selectedStoreProductNameQuantity)) {
                                    quantityProductToEdit = product;
                                    break;
                                }
                            }
                            quantityProductToEdit.setCurrQuantity(newQuantity);
                            quantityProductToEdit.setInitialQuantity(newQuantity);
                            DataManager.updateProducts(
                                    "products.txt", quantityProductToEdit);
                            out.write("Quantity successfully edited!");
                            out.println();
                            out.flush();
                            break;
                        case "editProductPrice":
                            String[] priceProduct = in.readLine().split(",");
                            String selectedStoreProductNamePrice = priceProduct[0];
                            String selectedStorePrice = priceProduct[1];
                            double newPrice = Double.parseDouble(priceProduct[2]);
                            Store selectedStorePr = DataManager.identifyStore(selectedStorePrice);
                            Product priceProductToEdit = null;
                            for (Product product : selectedStorePr.getProductList()) {
                                if (product.getName().equals(selectedStoreProductNamePrice)) {
                                    priceProductToEdit = product;
                                    break;
                                }
                            }
                            priceProductToEdit.setPrice(newPrice);
                            DataManager.updateProducts(
                                    "products.txt", priceProductToEdit);
                            out.write("Price successfully edited!");
                            out.println();
                            out.flush();
                            break;
                        case "removeProduct":
                            String[] removeProduct = in.readLine().split(",");
                            String storeFrom = removeProduct[0];
                            String productToRemoveName = removeProduct[1];
                            Store selStore = DataManager.identifyStore(storeFrom);
                            boolean removedProduct = false;

                            try {
                                for (int i = 0; i < selStore.getProductList().size(); i++) {
                                    Product currProduct =
                                            selStore.getProductList().get(i);
                                    if (currProduct.getName().equalsIgnoreCase(productToRemoveName)) {
                                        DataManager.removeProduct("products.txt",
                                                currProduct);
                                        removedProduct = true;
                                        out.write("Successfully done");
                                        out.println();
                                        out.flush();

                                    }
                                }

                            } catch (Exception e) {
                                out.write("Error");
                                out.println();
                                out.flush();
                                //System.out.println("Error in updating seller information");
                                //sout"
                            }

                            if (!removedProduct) {
                                out.write("Error");
                                out.println();
                                out.flush();
                            }


                            break;
                        case "createProduct":
                            synchronized (addProduct) {
                                String[] createProduct = in.readLine().split(",");
                                System.out.println(createProduct[0]);
                                String createProductName = createProduct[0];
                                String createStoreName = createProduct[1];
                                String createProductDescription = createProduct[2];
                                int createProductQuantity = Integer.parseInt(createProduct[3]);
                                double createProductPrice = Double.parseDouble(createProduct[4]);

                                int integerQuantity = 0;

                                try {
                                    integerQuantity = createProductQuantity;
                                    if (integerQuantity <= 0) {
                                        break;
                                    }
                                } catch (Exception e) {
                                    out.write("Error");
                                    out.println();
                                    out.flush();
                                    break;
                                }

                                try {
                                    if (createProductPrice <= 0) {
                                        break;
                                    }
                                } catch (Exception e) {
                                    out.write("Error");
                                    out.println();
                                    out.flush();
                                    break;
                                }

                                Product productToAdd = new Product(createProductName, createStoreName
                                        , createProductDescription, createProductQuantity
                                        , createProductPrice);

                                DataManager.updateProducts("products.txt",
                                        productToAdd);
                                out.write("Successfully done");
                                out.println();
                                out.flush();
                                break;
                            }
                        case "addStoreWithCSV":
                            synchronized (addStoreWithCSV) {
                                String[] addStoreWithCSVComponents = in.readLine().split(",");
                                String storeNameAdd = addStoreWithCSVComponents[0];
                                String sellerEmailToAdd = addStoreWithCSVComponents[1];
                                String fileName = addStoreWithCSVComponents[2];
                                Seller currentSeller = DataManager.identifySeller(sellerEmailToAdd);
                                ArrayList<Store> currentSellerStores = currentSeller.getStoreList();
                                boolean validStoreOption = true;

                                for (Seller seller : DataManager.readSellerAccountInformation()) {
                                    for (Store store : seller.getStoreList()) {
                                        if (storeNameAdd.equals(store.getName())) {
                                            System.out.println("Sorry! A store with name [" + storeNameAdd +
                                                    "] " +
                                                    "already exists.");
                                            validStoreOption = false;
                                            out.write("storeExists");
                                            out.println();
                                            out.flush();
                                            break;
                                        }
                                    }
                                }

                                if (validStoreOption) {
                                    if (fileName.isEmpty()) {
                                        System.out.println("Test");
                                        out.write("NotValidStoreOption");
                                        out.println();
                                        out.flush();
                                        break;
                                    }
                                    try {

                                        boolean enableNewStore = true;

                                        try {
                                            // update seller info
                                            currentSellerStores.add(DataManager.addStoreProducts(storeNameAdd,
                                                    sellerEmailToAdd,
                                                    fileName));
                                            currentSeller.setStoreList(currentSellerStores);
                                            DataManager.updateSellerInformation(currentSeller);
                                            out.write("Successful");
                                            out.println();
                                            out.flush();
                                            System.out.println("Store(s) successfully added.");
                                        } catch (ExistingProductException exists) {
                                            System.out.println("One or more of the products you attempted" +
                                                    " to add to your store has a name that exists in this" +
                                                    " market already. You may only add products of unique" +
                                                    " name.");
                                            out.write("ProductError");
                                            out.println();
                                            out.flush();
                                        } catch (Exception ex) {
                                            ex.printStackTrace();   // TODO get rid of
                                            System.out.println("Error adding store.");
                                            out.write("Error");
                                            out.println();
                                            out.flush();
                                        }

                                    } catch (Exception e) {
                                        System.out.println("There was an error adding the store to the " +
                                                "database.");
                                        out.write("Error");
                                        out.println();
                                        out.flush();

                                    }
                                }
                                break;
                            }
                        case "addBlankStore":
                            synchronized (addBlankStore) {
                                String[] addStoreComponents = in.readLine().split(",");
                                String storeNameToAdd = addStoreComponents[0];
                                String storeEmail = addStoreComponents[1];
                                boolean validStore = true;

                                for (Seller seller : DataManager.readSellerAccountInformation()) {
                                    for (Store store : seller.getStoreList()) {
                                        if (storeNameToAdd.equals(store.getName())) {
                                            System.out.println("Sorry! A store with name [" + storeNameToAdd +
                                                    "] " +
                                                    "already exists.");
                                            validStore = false;
                                            break;
                                        }
                                    }
                                }

                                Seller currSeller = DataManager.identifySeller(storeEmail);
                                ArrayList<Store> currSellerStoresTwo = currSeller.getStoreList();


                                if (validStore) {
                                    try {
                                        currSellerStoresTwo.add(new Store(null, storeEmail, storeNameToAdd));
                                        currSeller.setStoreList(currSellerStoresTwo);
                                        DataManager.updateSellerInformation(currSeller);
                                        out.write("Successful");
                                        out.println();
                                        out.flush();
                                    } catch (Exception e) {
                                        System.out.println("Error adding new store.");
                                        out.write("Error");
                                        out.println();
                                        out.flush();
                                    }
                                } else {
                                    out.write("Error2");
                                    out.println();
                                    out.flush();
                                }
                                break;
                            }
                        case "exportStoreProducts":
                            String[] exportProducts = in.readLine().split(",");
                            String file = exportProducts[0];
                            String emailExport = exportProducts[1];
                            String exportStoreName = exportProducts[2];
                            boolean found = false;
                            try {
                                if (file.isEmpty()) {
                                    out.write("FileEmptyError");
                                    out.println();
                                    out.flush();
                                    break;
                                }
                                if (exportStoreName.isEmpty()) {
                                    out.write("StoreEmptyError");
                                    out.println();
                                    out.flush();
                                    break;
                                }

                                ArrayList<Seller> readSellers =
                                        DataManager.readSellerAccountInformation();
                                if (file.equals("SellerInformation.txt") ||
                                        file.equals("CustomerInformation.txt") ||
                                        file.equals("Products.txt") ||
                                        file.equals("StoresToAdd.txt")
                                ) {
                                    throw new IOException();
                                }
                                for (int i = 0; i < readSellers.size(); i++) {
                                    if (readSellers.get(i).getEmail().equals(emailExport)) {
                                        for (int j = 0; j < readSellers.get(i).getStoreList().size();
                                             j++) {
                                            if (readSellers.get(i).getStoreList().get(j).getName()
                                                    .equals(exportStoreName)) {
                                                DataManager.exportProducts(exportStoreName, file);
                                                System.out.println("Successfully Done");
                                                out.write("Successfully Done");
                                                out.println();
                                                out.flush();
                                                found = true;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                out.write("InvalidFileError");
                                out.println();
                                out.flush();
                                break;
                            }

                            if (!found) {
                                out.write("StoreNotFoundError");
                                out.println();
                                out.flush();
                                break;
                            }
                            break;
                        // statistics
                        case "sellerDisplayAllCustomers":
                            String[] displayAllCustomersInput = in.readLine().split(",");
                            String sellerDisplayAllCustomersEmail = displayAllCustomersInput[0];
                            String displayAllCustomersSort = displayAllCustomersInput[1];
                            String displayAllCustomersOutput = "";

                            Seller displayAllCustomersSeller = DataManager
                                    .identifySeller(sellerDisplayAllCustomersEmail);
                            ArrayList<Customer> displayAllCustomers = DataManager.readCustomerAccountInformation();
                            ArrayList<Customer> sellerCustomers = new ArrayList<Customer>();
                            ArrayList<Integer> numProductsPurchased = new ArrayList<Integer>();

                            if (displayAllCustomersSeller != null && displayAllCustomers != null) {
                                for (Customer c : displayAllCustomers) {

                                    if (c.boughtFrom(displayAllCustomersSeller)) {
                                        sellerCustomers.add(c);
                                        ArrayList<Product> purchasedProducts = c.getPurchasedProducts();
                                        int quantityBought = 0;
                                        for (Product p : purchasedProducts) {
                                            for (Store s : displayAllCustomersSeller.getStoreList()) {
                                                if (p.getStoreName().equals(s.getName())) {
                                                    quantityBought += p.getCurrQuantity();
                                                }
                                            }
                                        }
                                        numProductsPurchased.add(quantityBought);
                                    }
                                }
                                switch (displayAllCustomersSort) {
                                    case "alphabetical":
                                        for (int i = 0; i < sellerCustomers.size() - 1; i++) {
                                            for (int j = 0; j < sellerCustomers.size() - i - 1; j++) {
                                                if (sellerCustomers.get(j).getName().toLowerCase()
                                                        .compareTo(sellerCustomers.get(j + 1)
                                                                .getName().toLowerCase()) > 0) {
                                                    Customer temp = sellerCustomers.get(j);

                                                    int tempProductsPurchased = numProductsPurchased.get(j);

                                                    sellerCustomers.set(j, sellerCustomers.get(j + 1));
                                                    numProductsPurchased.set(j, numProductsPurchased.get(j + 1));

                                                    sellerCustomers.set(j + 1, temp);
                                                    numProductsPurchased.set(j + 1, tempProductsPurchased);
                                                }
                                            }
                                        }
                                        displayAllCustomersOutput += "Your customers (sorted alphabetically "
                                                + "by customer name): " + "\n";
                                        for (int i = 0; i < sellerCustomers.size(); i++) {
                                            displayAllCustomersOutput += "Customer Name: " +
                                                    sellerCustomers.get(i).getName() + ", Purchased Products: " +
                                                    numProductsPurchased.get(i) + "\n";
                                        }
                                        break;
                                    case "ascending":
                                        for (int i = 0; i < numProductsPurchased.size() - 1; i++) {
                                            for (int j = 0; j < numProductsPurchased.size() - i - 1; j++) {
                                                if (numProductsPurchased.get(j) > numProductsPurchased.get(j + 1)) {
                                                    Customer temp = sellerCustomers.get(j);
                                                    int tempProductsPurchased = numProductsPurchased.get(j);
                                                    sellerCustomers.set(j, sellerCustomers.get(j + 1));
                                                    numProductsPurchased.set(j, numProductsPurchased.get(j + 1));
                                                    sellerCustomers.set(j + 1, temp);
                                                    numProductsPurchased.set(j + 1, tempProductsPurchased);
                                                }
                                            }
                                        }

                                        displayAllCustomersOutput += "Your customers (sorted ascending by " +
                                                "products purchased): " + "\n";
                                        for (int i = 0; i < sellerCustomers.size(); i++) {
                                            displayAllCustomersOutput += "Customer Name: " +
                                                    sellerCustomers.get(i).getName() + ", Purchased Products: "
                                                    + numProductsPurchased.get(i) + "\n";
                                        }
                                        break;
                                    case "descending":
                                        for (int i = 0; i < numProductsPurchased.size() - 1; i++) {
                                            for (int j = 0; j < numProductsPurchased.size() - i - 1;
                                                 j++) {
                                                if (numProductsPurchased.get(j) < numProductsPurchased.get(j + 1)) {
                                                    Customer temp = sellerCustomers.get(j);
                                                    int tempProductsPurchased = numProductsPurchased.get(j);
                                                    sellerCustomers.set(j, sellerCustomers.get(j + 1));
                                                    numProductsPurchased.set(j, numProductsPurchased.get(j + 1));
                                                    sellerCustomers.set(j + 1, temp);
                                                    numProductsPurchased.set(j + 1, tempProductsPurchased);
                                                }
                                            }
                                        }
                                        displayAllCustomersOutput += "Your customers (sorted descending by " +
                                                "products purchased): " + "\n";
                                        for (int i = 0; i < sellerCustomers.size(); i++) {
                                            displayAllCustomersOutput += "Customer Name: " +
                                                    sellerCustomers.get(i).getName() + ", Purchased Products: "
                                                    + numProductsPurchased.get(i) + "\n";
                                        }
                                        break;
                                    default:
                                        displayAllCustomersOutput += "default" + "\n";
                                        break;
                                }
                            } else {
                                displayAllCustomersOutput = "Error displaying all customers." + "\n";
                            }

                            out.write(displayAllCustomersOutput);
                            out.println();
                            out.flush();
                            break;
                        case "sellerDisplayAllProductsAndSales":
                            String[] displayAllProductsInput = in.readLine().split(",");
                            String sellerDisplayAllProductsEmail = displayAllProductsInput[0];
                            String displayAllProductsSort = displayAllProductsInput[1];
                            String displayAllProductsOutput = "";

                            Seller displayAllProductsSeller =
                                    DataManager.identifySeller(sellerDisplayAllProductsEmail);
                            ArrayList<Customer> displayProductsCustomers =
                                    DataManager.readCustomerAccountInformation();
                            ArrayList<Product> productsSold = new ArrayList<Product>();

                            if (displayAllProductsSeller != null && displayProductsCustomers != null) {
                                for (Customer c : displayProductsCustomers) {
                                    for (Product p : c.getPurchasedProducts()) {
                                        if (displayAllProductsSeller.getStoreNames().contains(p.getStoreName())) {
                                            boolean purchasedMoreThanOnce = false;
                                            // check if product has already been added to products sold by different
                                            // customer
                                            for (Product productSold : productsSold) {
                                                if (productSold.getName().equals(p.getName())) {
                                                    purchasedMoreThanOnce = true;
                                                    productSold.setCurrQuantity(productSold.getCurrQuantity()
                                                            + p.getCurrQuantity());
                                                }
                                            }
                                            if (!purchasedMoreThanOnce) {
                                                productsSold.add(p);
                                            }
                                        }
                                    }
                                }

                                switch (displayAllProductsSort) {
                                    case "alphabetical":
                                        for (int i = 0; i < productsSold.size() - 1; i++) {
                                            for (int j = 0; j < productsSold.size() - i - 1; j++) {
                                                if (productsSold.get(j).getName().compareTo(productsSold.get(j + 1)
                                                        .getName()) > 0) {
                                                    Product temp = productsSold.get(j);
                                                    productsSold.set(j, productsSold.get(j + 1));
                                                    productsSold.set(j + 1, temp);
                                                }
                                            }
                                        }
                                        displayAllProductsOutput += "Your products (sorted alphabetically by product "
                                                + "name):" + "\n";
                                        break;
                                    case "ascending":
                                        for (int i = 0; i < productsSold.size() - 1; i++) {
                                            for (int j = 0; j < productsSold.size() - i - 1; j++) {
                                                if (productsSold.get(j).getQuantityPurchased() > productsSold.get(j + 1)
                                                        .getQuantityPurchased()) {
                                                    Product temp = productsSold.get(j);
                                                    productsSold.set(j, productsSold.get(j + 1));
                                                    productsSold.set(j + 1, temp);
                                                }
                                            }
                                        }
                                        displayAllProductsOutput += "Your products (sorted ascending by quantity " +
                                                "purchased): " + "\n";
                                        break;
                                    case "descending":
                                        for (int i = 0; i < productsSold.size() - 1; i++) {
                                            for (int j = 0; j < productsSold.size() - i - 1; j++) {
                                                if (productsSold.get(j).getQuantityPurchased() < productsSold.get(j + 1)
                                                        .getQuantityPurchased()) {
                                                    Product temp = productsSold.get(j);
                                                    productsSold.set(j, productsSold.get(j + 1));
                                                    productsSold.set(j + 1, temp);
                                                }
                                            }
                                        }
                                        displayAllProductsOutput += "Your products (sorted descending by quantity " +
                                                "purchased): " + "\n";
                                        break;
                                    default:
                                        displayAllProductsOutput += "default" + "\n";
                                        break;
                                }
                                for (Product sortedProduct : productsSold) {
                                    displayAllProductsOutput += sortedProduct.toString() + "\n";
                                    int sales = 0;
                                    for (Customer c : displayProductsCustomers) {
                                        for (Product p : c.getPurchasedProducts()) {
                                            if (p.getName().equals(sortedProduct.getName())) {
                                                sales++;
                                            }
                                        }
                                    }
                                    displayAllProductsOutput += sortedProduct.getName() + " Sales: " + sales + "\n";
                                }
                            } else {
                                displayAllProductsOutput = "Error displaying all products and sales" + "\n";
                            }
                            out.write(displayAllProductsOutput);
                            out.println();
                            out.flush();
                            break;
                        case "sellerDisplaySalesWithInfo":
                            String displaySalesEmail = in.readLine();
                            String displaySalesOutput = "";

                            Seller displaySalesSeller = DataManager.identifySeller(displaySalesEmail);
                            ArrayList<Customer> displaySalesCustomers = DataManager.readCustomerAccountInformation();

                            if (displaySalesSeller != null && displaySalesCustomers != null) {
                                for (Store store : displaySalesSeller.getStoreList()) {
                                    displaySalesOutput += store.getName() + " Customers & Revenue:" + "\n";
                                    double revenue = 0;
                                    for (Product p : store.getProductList()) {
                                        revenue += p.getPrice() * (p.getInitialQuantity() -
                                                p.getCurrQuantity());
                                    }
                                    DecimalFormat df = new DecimalFormat("#.##");
                                    df.setRoundingMode(RoundingMode.CEILING);
                                    displaySalesOutput += "Revenue: " + df.format(revenue) + "\n";

                                    for (Customer displaySalesCustomer : displaySalesCustomers) {
                                        ArrayList<Product> purchasedProducts = displaySalesCustomer
                                                .getPurchasedProducts();
                                        for (Product product : purchasedProducts) {
                                            if (product.getStoreName().equals(store.getName())) {
                                                displaySalesOutput += displaySalesCustomer.getName() + " purchased " +
                                                        product.getName() + "\n";
                                            }
                                        }
                                    }
                                }
                            } else {
                                displaySalesOutput = "Error displaying sales." + "\n";
                            }

                            out.write(displaySalesOutput);
                            out.println();
                            out.flush();
                            break;
                        case "sellerViewProductsInCarts":
                            String sellerViewProductsInCartsEmail = in.readLine();
                            String productsInCartsOutput = "";

                            Seller viewProductsInCartsSeller =
                                    DataManager.identifySeller(sellerViewProductsInCartsEmail);
                            ArrayList<Customer> viewProductsInCartsCustomers =
                                    DataManager.readCustomerAccountInformation();

                            if (viewProductsInCartsSeller != null && viewProductsInCartsCustomers != null) {
                                for (Customer c : viewProductsInCartsCustomers) {
                                    ArrayList<Product> productsInCart = c.getShoppingCart();
                                    int totalProductsInCart = 0;

                                    for (Product product : productsInCart) {
                                        if (viewProductsInCartsSeller.getStoreNames()
                                                .contains(product.getStoreName())) {
                                            totalProductsInCart += product.getCurrQuantity();
                                            productsInCartsOutput += "Customer Name: " + c.getName() +
                                                    ", Product Name: " +
                                                    product.getName() + ", Store Name: " + product.getStoreName() +
                                                    "Quantity in Cart: " + product.getCurrQuantity() + "\n";
                                        }
                                    }

                                    productsInCartsOutput += "Total products in Cart for " + c.getName() + ": " +
                                            totalProductsInCart + "\n---" + "\n";

                                }
                            } else {
                                productsInCartsOutput = "Error accessing products in carts." + "\n";
                            }

                            out.write(productsInCartsOutput);
                            out.println();
                            out.flush();
                            break;
                        case "sellerDeleteAccount":
                            String sellerDeleteEmail = in.readLine();
                            String sellerDeleteOutput = "";

                            ArrayList<Seller> sellersDeleteAcc = DataManager.readSellerAccountInformation();
                            if (sellersDeleteAcc != null) {
                                for (Seller s : sellersDeleteAcc) {
                                    if (s.getEmail().equals(sellerDeleteEmail)) {
                                        sellersDeleteAcc.remove(s);
                                        sellerDeleteOutput = "Successfully deleted account and logged out!" + "\n";
                                        break;
                                    }
                                }
                                DataManager.writeSellerInformation(sellersDeleteAcc);
                            } else {
                                sellerDeleteOutput = "Error deleting account." + "\n";
                            }

                            out.write(sellerDeleteOutput);
                            out.println();
                            out.flush();
                            break;
                    }
                }


            } catch (Exception e) {
                System.out.println("");
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    System.out.println("");
                }
            }
        }

        public static final Object customerCreateAccountGatekeeper = new Object();

        public String customerCreateAccount(String newName, String newEmail, String newPassword) {
            synchronized (customerCreateAccountGatekeeper) {
                String createAccOutput = "";

                Customer potentialCustomer = DataManager.identifyCustomer(newEmail);
                try {
                    if (potentialCustomer == null) {
                        DataManager.updateCustomerInformation(new Customer(newName, newEmail, newPassword));
                        createAccOutput = "Created account and logged in successfully!" + "\n";
                    } else {
                        createAccOutput = "Account could not be created - email is already taken." + "\n";
                    }
                } catch (Exception e) {
                    System.out.println("");
                    createAccOutput = "An error occurred while creating your account." + "\n";
                }
                return createAccOutput;
            }
        }

        public static final Object sellerCreateAccountGatekeeper = new Object();

        public String sellerCreateAccount(String newSellerName, String newSellerEmail, String newSellerPassword) {
            synchronized (sellerCreateAccountGatekeeper) {
                String createSellerAccOutput = "";

                Seller potentialSeller = DataManager.identifySeller(newSellerEmail);
                try {
                    if (potentialSeller == null) {
                        DataManager.updateSellerInformation(new Seller(newSellerName, newSellerEmail,
                                newSellerPassword));
                        createSellerAccOutput = "Created account and logged in successfully!" + "\n";
                    } else {
                        createSellerAccOutput = "Account could not be created - email is already taken." + "\n";
                    }
                } catch (Exception e) {
                    System.out.println(""); // TODO: get rid of
                    createSellerAccOutput = "An error occurred while creating your account." + "\n";
                }
                return createSellerAccOutput;
            }
        }

        public static final Object checkoutGatekeeper = new Object();

        public String checkout(String checkoutEmail) {
            synchronized (checkoutGatekeeper) {
                String checkoutOutput = "";
                try {
                    Customer checkoutCustomer = DataManager.identifyCustomer(checkoutEmail);
                    ArrayList<Product> productsInMarket = DataManager.readProductsFile("products.txt");

                    if (checkoutCustomer != null && productsInMarket != null) {
                        int initCartItems = checkoutCustomer.getShoppingCart().size();

                        if (initCartItems == 0) {
                            return "Nothing in cart to checkout." + "\n";
                        }
                        int successfullyPurchased = 0;

                        Product[] tempArray = new Product[checkoutCustomer.getShoppingCart().size()];

                        for (int j = 0; j < initCartItems; j++) {
                            tempArray[j] = checkoutCustomer.getShoppingCart().get(j);
                        }

                        for (int i = 0; i < initCartItems; i++) {
                            Product cartProduct = tempArray[i];
                            boolean purchased = false;
                            for (Product p : productsInMarket) {
                                if (p.getName().equals(cartProduct.getName())) {
                                    // add/update product to purchasedProducts.txt
                                    if (p.getCurrQuantity() < cartProduct.getCurrQuantity()) {
                                        checkoutOutput += "Unable to purchase item: [" + p.getName() +
                                                "] because there is not enough stock left." + "\n";
                                        checkoutCustomer.removeFromCart(cartProduct);
                                        purchased = true;
                                    } else {
                                        DataManager.updatePurchasedProducts(
                                                "PurchasedProducts.txt", cartProduct);
                                        // update product quantity in products.txt
                                        p.setCurrQuantity(p.getCurrQuantity() -
                                                cartProduct.getCurrQuantity());
                                        DataManager.updateProducts("products.txt", p);
                                        // update customer info
                                        checkoutCustomer.removeFromCart(cartProduct);
                                        checkoutCustomer.purchaseProduct(cartProduct);
                                        purchased = true;
                                        successfullyPurchased++;
                                    }
                                }
                            }

                            if (!purchased) {
                                checkoutOutput += "Unable to purchase: " +
                                        cartProduct.toString() + "because this product has been removed from the " +
                                        "market by its seller." + "\n";
                            }
                        }

                        DataManager.updateCustomerInformation(checkoutCustomer);
                        checkoutOutput += "Successfully checked out " +
                                successfullyPurchased + " items!" + "\n";
                    } else {
                        checkoutOutput = "Error checking out." + "\n";
                    }
                } catch (Exception e) {
                    checkoutOutput = "Error checking out." + "\n";
                }
                return checkoutOutput;
            }
        }
    }
}
