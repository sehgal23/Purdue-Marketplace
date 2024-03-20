import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MarketplacePortal
 * <p>
 * used by Customers and Sellers to access the marketplace
 *
 * @author Alan Kang, Priyanka Soe, Sanketh Edara, Arnav Sehgal, Eamon Mukhopadhyay
 * @version November 13, 2023
 */

public class MarketplacePortal {

    public static String welcomeScreen = "Welcome to the Purdue Market homepage! Please select user type or press " +
            "3 " +
            "to quit:\n" +
            "[1] Customer\n" +
            "[2] Seller\n" +
            "[3] Exit";

    public static String loginInterface = "Please choose one of the following options: \n" +
            "[1] Log in \n" +
            "[2] Create an account";

    public static String customerInterface = "Welcome to your marketplace! Choose one of the below options to " +
            "continue: \n" +
            "[1] Display All Products\n" +
            "[2] Search Products\n" +
            "[3] Select a product\n" +
            "[4] Manage Shopping Cart\n" +
            "[5] View Purchase History\n" +
            "[6] Statistics\n" +
            "[7] Delete your account\n" +
            "[8] Log out";

    public static String sellerInterface = "Welcome to your marketplace! Choose one of the below options to " +
            "continue: \n" +
            "[1] Manage stores\n" +
            "[2] Statistics\n" +
            "[3] Delete your account\n" +
            "[4] Log out";
    public static String errorInput = "Please enter a valid input.";


    public static void main(String[] args) throws IOException {
        Marketplace marketplace = null;
        ArrayList<Seller> sellers = null;

        Scanner s = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println(welcomeScreen);
            try {
                choice = s.nextInt();
            } catch (Exception e) {
                choice = 0;
            }
            s.nextLine();
            String name = "";
            String email = "";
            String password = "";

            try {
                sellers = DataManager.readSellerAccountInformation();
                marketplace = new Marketplace(sellers);
            } catch (Exception e) {
                //e.printStackTrace();
            }

            switch (choice) {
                case 1: // Portal for Customer
                    int customerChoice = 0;

                    // refresh current customer attributes
                    Customer currCustomer = null;
                    ArrayList<Product> currCustomerPurchasedProducts = null;
                    ArrayList<Product> currCustomerShoppingCart = null;

                    do {
                        System.out.println(loginInterface);
                        try {
                            customerChoice = s.nextInt();
                        } catch (Exception e) {
                            customerChoice = 0;
                        }
                        s.nextLine();

                        switch (customerChoice) {
                            case 1: // Customer log in
                                if (!(name.isEmpty() || email.isEmpty() || password.isEmpty())) {
                                    System.out.println("Already logged in!");
                                    break;
                                }
                                System.out.println("Please enter name:");
                                name = s.nextLine();
                                if (name.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter email:");
                                email = s.nextLine();
                                if (email.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter password:");
                                password = s.nextLine();
                                if (password.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                Customer c = new Customer(name, email, password);
                                try {
                                    ArrayList<Customer> customers = DataManager.readCustomerAccountInformation();
                                    boolean foundAccount = false;
                                    for (Customer checkCustomer : customers) {
                                        if (checkCustomer.getName().equals(c.getName())) {
                                            foundAccount = true;
                                            currCustomer = checkCustomer;
                                            currCustomerPurchasedProducts = currCustomer.getPurchasedProducts();
                                            currCustomerShoppingCart = currCustomer.getShoppingCart();
                                        }
                                    }
                                    if (foundAccount) {
                                        System.out.println("Logged in!");
                                    } else {
                                        throw new Exception();
                                    }
                                } catch (Exception e) {
                                    //e.printStackTrace();
                                    System.out.println("Error in logging in!");
                                    name = "";
                                    email = "";
                                    password = "";
                                }
                                break;
                            case 2: // Customer create account
                                if (!(name.isEmpty() || email.isEmpty() || password.isEmpty())) {
                                    System.out.println("Must log out to create account.");
                                    break;
                                }
                                System.out.println("Please enter name:");
                                String newName = s.nextLine();
                                if (newName.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter email:");
                                String newEmail = s.nextLine();
                                if (newEmail.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }

                                try {
                                    ArrayList<Customer> currentCustomers =
                                            DataManager.readCustomerAccountInformation();
                                    for (Customer currentCustomer : currentCustomers) {
                                        if (currentCustomer.getEmail().equals(newEmail)) {
                                            throw new Exception();
                                        }
                                    }
                                    System.out.println("Please enter password:");
                                    String newPassword = s.nextLine();
                                    if (newPassword.isEmpty()) {
                                        System.out.println(errorInput);
                                        break;
                                    }
                                    Customer potentialNewCustomer = new Customer(newName, newEmail, newPassword);
                                    DataManager.updateCustomerInformation(potentialNewCustomer);
                                    name = newName;
                                    email = newEmail;
                                    password = newPassword;
                                    currCustomer = potentialNewCustomer;
                                    currCustomerPurchasedProducts = currCustomer.getPurchasedProducts();
                                    currCustomerShoppingCart = currCustomer.getShoppingCart();
                                    System.out.println("Created account and logged in successfully!");
                                } catch (Exception e) {
                                    System.out.println("Account could not be created - email is already taken.");
                                }
                                break;
                            default:
                                System.out.println("Please enter a valid option!");
                        }
                    } while (currCustomer == null);

                    do {   // run customer interface UNTIL customer logs out or deletes account
                        System.out.println(customerInterface);
                        int customerOption = 0;
                        try {
                            customerOption = s.nextInt();
                            s.nextLine();
                        } catch (Exception e) {
                            System.out.println(errorInput);
                            s.nextLine();
                            continue;
                        }
                        switch (customerOption) {
                            case 1:  // customer shop all products
                                try {
                                    ArrayList<Product> products = DataManager.readProductsFile("products.txt");
                                    for (Product p : products) {
                                        if (p.getCurrQuantity() != 0) {
                                            System.out.println(p);
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error in reading products catalog.");
                                }
                                break;
                            case 2:  // customer search products
                                int searchChoice;
                                boolean keepSearching = false;
                                do {
                                    System.out.println("Please choose one of the following options\n" +
                                            "[1] Search by keyword\n" +
                                            "[2] Search by store name\n" +
                                            "[3] Search by price threshold\n" +
                                            "[4] Sort by price\n" +
                                            "[5] Sort by quantity available\n" +
                                            "[6] View all products\n" +
                                            "[7] Back");
                                    try {
                                        searchChoice = s.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(errorInput);
                                        s.nextLine();
                                        continue;
                                    }
                                    s.nextLine();

                                    switch (searchChoice) {
                                        case 1: // Search by keyword
                                            String searchTerm;
                                            do {
                                                System.out.println("Enter search term:");
                                                searchTerm = s.nextLine();
                                                if (searchTerm.isEmpty()) {
                                                    System.out.println(errorInput);
                                                }
                                            } while (searchTerm.isEmpty());
                                            ArrayList<Product> productsContaining =
                                                    marketplace.productsContaining(searchTerm);
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
                                                System.out.println("No products match your search.");
                                            } else {
                                                System.out.println(output);
                                            }
                                            break;
                                        case 2: // Search by store name
                                            String searchStore;
                                            do {
                                                System.out.println("Enter store name:");
                                                searchStore = s.nextLine();
                                                if (searchStore.isEmpty()) {
                                                    System.out.println(errorInput);
                                                }
                                            } while (searchStore.isEmpty());
                                            String[] searchByStore = marketplace.searchByStore(searchStore);
                                            if (searchByStore.length != 0) {
                                                for (String productWithStore : searchByStore) {
                                                    System.out.println(productWithStore);
                                                }
                                            } else {
                                                System.out.println("No products match your search.");
                                            }
                                            break;
                                        case 3: // Search by price threshold
                                            boolean validatedInput = false;
                                            do {
                                                try {
                                                    System.out.println("Enter price");
                                                    double price = 0;
                                                    try {
                                                        price = s.nextDouble();
                                                    } catch (Exception e) {
                                                        price = 0;
                                                    }
                                                    s.nextLine();
                                                    System.out.println("Lower or Higher? \n[1] Lower\n[2] Higher");
                                                    int higherLower;
                                                    try {
                                                        higherLower = s.nextInt();
                                                    } catch (Exception e) {
                                                        higherLower = 0;
                                                    }
                                                    s.nextLine();
                                                    if (!(higherLower == 1 || higherLower == 2)) {
                                                        throw new Exception();
                                                    }
                                                    String[] searchByThresh;
                                                    if (higherLower == 1) {
                                                        searchByThresh = marketplace.searchByPrice(price,
                                                                false);
                                                        validatedInput = true;
                                                    } else {
                                                        searchByThresh = marketplace.searchByPrice(price,
                                                                true);
                                                        validatedInput = true;
                                                    }
                                                    if (searchByThresh.length != 0) {
                                                        for (String string : searchByThresh) {
                                                            System.out.println(string);
                                                        }
                                                    } else {
                                                        System.out.println("No products match your search.");
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(errorInput);
                                                }
                                            } while (!validatedInput);
                                            break;
                                        case 4: // Sort by price high to low
                                            try {
                                                String[] productsByPrice = marketplace.sortProductsByPrice();
                                                for (String string : productsByPrice) {
                                                    System.out.println(string);
                                                }
                                            } catch (NullPointerException npe) {
                                                System.out.println("No products in the marketplace yet!");
                                            } catch (Exception e) {
                                                //e.printStackTrace();
                                                System.out.println("An error occurred.");
                                            }
                                            break;
                                        case 5: // Sort by quantity
                                            try {
                                                String[] sortedByQuantity = marketplace.sortProductsByQuantity();
                                                for (String string : sortedByQuantity) {
                                                    System.out.println(string);
                                                }
                                            } catch (NullPointerException npe) {
                                                System.out.println("No products in the marketplace yet!");
                                            } catch (Exception e) {
                                                System.out.println("An error occurred.");
                                            }
                                            break;
                                        case 6: // View all products
                                            try {
                                                String[] allProducts = marketplace.listProducts();
                                                for (String allProductsString : allProducts) {
                                                    if (allProductsString != null) {
                                                        System.out.println(allProductsString);
                                                    }
                                                }
                                            } catch (NullPointerException npe) {
                                                System.out.println("No products in the marketplace yet!");
                                            } catch (Exception e) {
                                                System.out.println("An error occurred.");
                                            }
                                            break;
                                        case 7: // Exit
                                            break;
                                        default:
                                            keepSearching = true;
                                            System.out.println(errorInput);
                                    }
                                } while (keepSearching);
                                break;
                            case 3:  // customer select product
                                boolean keepSelecting = true;
                                Product selectedProduct = null;
                                do {
                                    System.out.println("Enter the name of the product you would like to select:");
                                    String selectedName = s.nextLine();
                                    if (selectedName.isEmpty()) {
                                        System.out.println(errorInput);
                                        continue;
                                    }
                                    try {
                                        boolean found = false;
                                        ArrayList<Product> products = DataManager.readProductsFile(
                                                "products.txt");
                                        if (products == null) {
                                            throw new NullPointerException();
                                        }
                                        for (Product p : products) {
                                            if (p.getName().equals(selectedName) && p.getCurrQuantity() != 0) {
                                                found = true;
                                                selectedProduct = p;
                                                System.out.println("The product you have selected: " + p.toString());
                                                System.out.println("Would you like to add this product to your cart? " +
                                                        "Y/N");
                                                String response = s.nextLine();
                                                while (!(response.equalsIgnoreCase("y") ||
                                                        response.equalsIgnoreCase("n"))) {
                                                    System.out.println("Please enter either Y or N.");
                                                    response = s.nextLine();
                                                }
                                                boolean addToCart = response.equalsIgnoreCase("y");
                                                if (addToCart) {
                                                    int quantity = 0;
                                                    do {
                                                        System.out.println(
                                                                "What quantity would you like to add to your " +
                                                                "cart?");
                                                        try {
                                                            quantity = s.nextInt();
                                                            s.nextLine();
                                                        } catch (Exception e) {
                                                            System.out.println(errorInput);
                                                            s.nextLine();
                                                            continue;
                                                        }
                                                        if (quantity <= 0) {
                                                            System.out.println(errorInput);
                                                            continue;
                                                        }
                                                        if (quantity > selectedProduct.getCurrQuantity()) {
                                                            System.out.println("The stock available of " +
                                                                    selectedProduct.getName() +
                                                                    " is " + selectedProduct.getCurrQuantity() + " and "
                                                                    + "you tried to add " + quantity + " to your " +
                                                                    "cart.");
                                                            System.out.println("Please enter a smaller quantity.");
                                                        }
                                                    } while (quantity > selectedProduct.getCurrQuantity() || quantity <=
                                                            0);

                                                    Product productToAdd = new Product(selectedProduct.getName(),
                                                            selectedProduct.getStoreName(),
                                                            selectedProduct.getDescription(), quantity,
                                                            selectedProduct.getPrice());
                                                    currCustomer.addToCart(productToAdd);
                                                    // updating shopping cart
                                                    currCustomerShoppingCart = currCustomer.getShoppingCart();
                                                    DataManager.updateCustomerInformation(currCustomer);
                                                    System.out.println("Successfully added to cart!");
                                                }
                                            }
                                        }
                                        if (!found) {
                                            System.out.println("The product you selected could not be found or is out" +
                                                    " of stock.");
                                        }
                                        boolean keepAsking = true;
                                        while (keepAsking) {
                                            System.out.println("Please choose one of the following options to " +
                                                    "continue: \n" +
                                                    "[1] Select another item\n" +
                                                    "[2] Return to main menu");
                                            int productSelectionChoice;
                                            try {
                                                productSelectionChoice = s.nextInt();
                                                keepAsking = false;
                                            } catch (Exception e) {
                                                System.out.println(errorInput);
                                                productSelectionChoice = 0;
                                            }
                                            s.nextLine();
                                            switch (productSelectionChoice) {
                                                case 1:
                                                    selectedProduct = null;
                                                    keepAsking = false;
                                                    break;
                                                case 2:
                                                    keepAsking = false;
                                                    keepSelecting = false;
                                                    break;
                                                default:
                                                    System.out.println(errorInput);
                                            }
                                        }
                                    } catch (IOException io) {
                                        System.out.println("could not read products file");
                                    }

                                } while (selectedProduct == null && keepSelecting);
                                break;
                            case 4:  // customer manage shopping cart
                                System.out.println("Welcome to your cart!");
                                boolean inCart = true;
                                do {
                                    try {
                                        System.out.println("Please choose an option: \n" +
                                                "[1] View your cart\n" +
                                                "[2] Remove an item from your cart\n" +
                                                "[3] Check out\n" +
                                                "[4] Back");
                                        int cartOption = 0;
                                        try {
                                            cartOption = s.nextInt();
                                            s.nextLine();
                                        } catch (Exception e) {
                                            System.out.println(errorInput);
                                            s.nextLine();
                                            continue;
                                        }

                                        switch (cartOption) {
                                            case 1:  // view cart
                                                System.out.println("Products in Cart:\n");
                                                for (Product cartProduct : currCustomerShoppingCart) {
                                                    System.out.println(cartProduct.toString() + "\n");
                                                }
                                                break;
                                            case 2:   // remove item from cart
                                                String removeName;
                                                do {
                                                    System.out.println("Enter the name of the product you would like" +
                                                            " to remove: ");
                                                    removeName = s.nextLine();
                                                    if (removeName.isEmpty()) {
                                                        System.out.println(errorInput);
                                                    }
                                                } while (removeName.isEmpty());
                                                boolean removed = false;
                                                for (int i = 0; i < currCustomerShoppingCart.size(); i++) {
                                                    Product cartProduct = currCustomerShoppingCart.get(i);
                                                    if (cartProduct.getName().equals(removeName)) {
                                                        currCustomer.removeFromCart(cartProduct);   // will
                                                        // remove all products with that name
                                                        // refresh the quantity of product available for purchase
                                                        for (Product p: DataManager.readProductsFile("products.txt")) {
                                                            if (p.equals(cartProduct)) {
                                                                p.setCurrQuantity(cartProduct.getCurrQuantity()+p.getCurrQuantity());
                                                                DataManager.updateProducts("products.txt", p);
                                                            }
                                                        }

                                                        System.out.println("Successfully removed product.");
                                                        removed = true;
                                                    }
                                                }

                                                currCustomerShoppingCart = currCustomer.getShoppingCart();
                                                DataManager.updateCustomerInformation(currCustomer);

                                                if (!removed) {
                                                    System.out.println("No products of name [" + removeName + "] " +
                                                            "exist in" +
                                                            " your cart.");
                                                }
                                                break;
                                            case 3:   // check out
                                                int initCartItems = currCustomerShoppingCart.size();
                                                if (initCartItems == 0) {
                                                    System.out.println("Nothing in cart to checkout.");
                                                    break;
                                                }
                                                int successfullyPurchased = 0;

                                                Product[] tempArray = new Product[currCustomerShoppingCart.size()];
                                                for (int j = 0; j < initCartItems; j++) {
                                                    tempArray[j] = currCustomerShoppingCart.get(j);
                                                }

                                                for (int z = 0; z < initCartItems; z++) {
                                                    Product cartProduct = tempArray[z];
                                                    boolean purchased = false;
                                                    // update product's quantity in the marketplace
                                                    for (Product p : DataManager.readProductsFile(
                                                            "products.txt")) {
                                                        if (p.getName().equals(cartProduct.getName())) {
                                                            // add/update product to purchasedProducts.txt
                                                            if (p.getCurrQuantity() < cartProduct.getCurrQuantity()) {
                                                                System.out.println("Unable to purchase item: ["+p.getName()+
                                                                        "] because there is not enough stock left.");
                                                                currCustomer.removeFromCart(cartProduct);
                                                                purchased = true;
                                                            } else {
                                                                DataManager.updatePurchasedProducts(
                                                                        "PurchasedProducts.txt", cartProduct);
                                                                // update product quantity in products.txt
                                                                p.setCurrQuantity(p.getCurrQuantity() -
                                                                        cartProduct.getCurrQuantity());
                                                                DataManager.updateProducts("products.txt", p);
                                                                // update customer info
                                                                currCustomer.removeFromCart(cartProduct);
                                                                currCustomer.purchaseProduct(cartProduct);

                                                                purchased = true;
                                                                successfullyPurchased++;
                                                            }
                                                        }
                                                    }

                                                    // means that the product in the cart was not found in the
                                                    // marketplace (which shouldn't be possible since the product has
                                                    // to be in the marketplace to be added to the cart)
                                                    if (!purchased) {
                                                        System.out.println("Unable to purchase: " +
                                                                cartProduct.toString());
                                                    }

                                                }

                                                DataManager.updateCustomerInformation(currCustomer);
                                                currCustomerShoppingCart = currCustomer.getShoppingCart();

                                                System.out.println("Successfully checked out " +
                                                            successfullyPurchased + " items!");
                                                break;
                                            case 4:   // back
                                                System.out.println("Leaving cart...");
                                                inCart = false;
                                                break;
                                            default:
                                                System.out.println(errorInput);
                                        }
                                    } catch (Exception e) {
                                        System.out.println(errorInput);
                                    }
                                } while (inCart);
                                System.out.println("Successfully left cart.");
                                break;
                            case 5:  // customer view purchase history
                                System.out.println("Your Purchased Products: ");
                                for (Product purchasedProduct : currCustomerPurchasedProducts) {
                                    System.out.println(purchasedProduct.toString() + "\n");
                                }
                                System.out.println("Would you like to export a receipt of your purchase history? " +
                                        "(Y/N)");
                                String response = s.nextLine();
                                while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase(
                                        "n"))) {  // might be something wrong with this logic
                                    System.out.println("Please enter either Y or N.");
                                    response = s.nextLine();
                                }
                                boolean wantsReceipt = response.equalsIgnoreCase("y");
                                if (wantsReceipt) {
                                    ArrayList<Customer> allCustomers = DataManager.readCustomerAccountInformation();
                                    Customer currentCustomer = null;
                                    for (int a = 0; a < allCustomers.size(); a++) {
                                        if (allCustomers.get(a).getName().equals(name)) {
                                            currentCustomer = allCustomers.get(a);
                                        }
                                    }
                                    DataManager.writeCustomerReceipt(currentCustomer);
                                    System.out.println("Successfully exported receipt!");
                                }
                                break;
                            case 6: // Customer statistics
                                System.out.println("Welcome to your statistics menu.");
                                int statisticsChoice = 0;
                                do {
                                    System.out.println("Please select an option to continue.\n" +
                                            "[1] View all stores with number of products sold\n" +
                                            "[2] View stores purchased from\n" +
                                            "[3] Return to main menu");
                                    try {
                                        statisticsChoice = s.nextInt();
                                        s.nextLine();
                                    } catch (Exception e) {
                                        System.out.println(errorInput);
                                        s.nextLine();
                                        continue;
                                    }

                                    ArrayList<Store> storesStatistics = null;

                                    if (statisticsChoice == 1) {  // make stores list all the stores in the marketplace
                                        Marketplace m = new Marketplace(DataManager.readSellerAccountInformation());
                                        storesStatistics = (m.getStores());
                                    } else if (statisticsChoice == 2) {  // make stores list the stores the customer
                                        // has purchased from
                                        storesStatistics = (marketplace.getStoresPurchasedFrom(currCustomer));
                                    } else if (statisticsChoice == 3) {  // exit statistics menu
                                        break;
                                    } else {    // exit current iteration of while
                                        System.out.println(errorInput);
                                        continue;
                                    }

                                    if (storesStatistics != null) {   // only choose to sort if option 1 or 2 was chosen
                                        int sortChoice = 0;
                                        do {
                                            System.out.println("How would you like to sort? \n" +
                                                    "[1] Alphabetical (by store name)\n" +
                                                    "[2] Ascending (by products sold per store)\n" +
                                                    "[3] Descending (by products sold per store)\n");
                                            try {
                                                sortChoice = s.nextInt();
                                                s.nextLine();
                                            } catch (Exception e) {
                                                System.out.println(errorInput);
                                                s.nextLine();
                                                continue;
                                            }
                                            s.nextLine();

                                            switch (sortChoice) {
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
                                                        System.out.println(store.toString() + "Products Sold: " +
                                                                store.getQuantitySold() + "\n");
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
                                                        System.out.println(store.toString() + ",\nProducts Sold: " +
                                                                store.getQuantitySold() + "\n");
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
                                                        System.out.println(store.toString() + "Products Sold: " +
                                                                store.getQuantitySold() + "\n");
                                                    }
                                                    break;
                                                default:
                                                    System.out.println(errorInput);
                                                    break;
                                            }
                                        } while (sortChoice != 1 && sortChoice != 2 && sortChoice != 3);
                                    } else {
                                        String output = ((statisticsChoice == 1) ? "No stores exist in the market!" :
                                                "You have no purchased from any stores yet!");
                                        System.out.println(output);
                                    }
                                } while (statisticsChoice != 3);
                                break;
                            case 7:  // Customer delete account
                                try {
                                    Customer customerToDelete = new Customer(name, email, password);

                                    ArrayList<Customer> customers = DataManager.readCustomerAccountInformation();
                                    for (Customer customer : customers) {
                                        if (customer.equals(customerToDelete)) {
                                            customers.remove(customer);
                                            break;
                                        }
                                    }
                                    DataManager.writeCustomerInformation(customers);
                                    currCustomer = null;
                                    name = "";
                                    email = "";
                                    password = "";
                                } catch (NullPointerException npe) {
                                    //npe.printStackTrace();
                                } catch (IOException e) {
                                    //e.printStackTrace();
                                }
                                break;
                            case 8:  // Customer log out of account
                                System.out.println("Logging out...");

                                try {
                                    DataManager.updateCustomerInformation(currCustomer);
                                    currCustomer = null;
                                    name = "";
                                    email = "";
                                    password = "";
                                    System.out.println("Logged out succesfully!");
                                } catch (IOException e) {
                                    System.out.println("Error in updating customer information.");
                                }

                                break;
                            default:
                                System.out.println(errorInput);
                        }

                    } while (currCustomer != null);
                    break;
                case 2: // Portal for Seller
                    int sellerChoice;
                    Seller currSeller = null;
                    ArrayList<Store> currSellerStores = null;

                    do {
                        System.out.println(loginInterface);
                        try {
                            sellerChoice = s.nextInt();
                            s.nextLine();
                        } catch (Exception e) {
                            s.nextLine();
                            System.out.println(errorInput);
                            continue;
                        }

                        switch (sellerChoice) {
                            case 1:
                                if (!(name.isEmpty() || email.isEmpty() || password.isEmpty())) {
                                    System.out.println("Already logged in!");
                                    break;
                                }
                                System.out.println("Please enter name:");
                                name = s.nextLine();
                                if (name.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter email:");
                                email = s.nextLine();
                                if (email.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter password:");
                                password = s.nextLine();
                                if (password.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                Seller c = new Seller(name, email, password);
                                try {

                                    boolean foundAccount = false;
                                    for (Seller checkSeller : sellers) {
                                        if (checkSeller.equals(c)) {
                                            foundAccount = true;
                                            currSeller = checkSeller;
                                            currSellerStores = checkSeller.getStoreList();
                                            break;
                                        }
                                    }
                                    if (foundAccount) {
                                        System.out.println("Logged in!");
                                        DataManager.updateSellerInformation(currSeller);
                                    } else {
                                        throw new Exception();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error in logging in!");
                                    name = "";
                                    email = "";
                                    password = "";
                                }
                                break;
                            case 2:
                                if (!(name.isEmpty() || email.isEmpty() || password.isEmpty())) {
                                    System.out.println("Must log out to create account.");
                                    break;
                                }
                                System.out.println("Please enter name:");
                                String newName = s.nextLine();
                                if (newName.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter email:");
                                String newEmail = s.nextLine();
                                if (newEmail.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }

                                try {
                                    ArrayList<Seller> currentSellers =
                                            DataManager.readSellerAccountInformation();
                                    for (Seller currentSeller : currentSellers) {
                                        if (currentSeller.getEmail().equals(newEmail)) {
                                            throw new Exception();
                                        }
                                    }
                                    System.out.println("Please enter password:");
                                    String newPassword = s.nextLine();
                                    if (newPassword.isEmpty()) {
                                        System.out.println(errorInput);
                                        break;
                                    }
                                    Seller potentialNewSeller = new Seller(newName, newEmail, newPassword);
                                    DataManager.updateSellerInformation(potentialNewSeller);
                                    name = newName;
                                    email = newEmail;
                                    password = newPassword;
                                    currSeller = potentialNewSeller;
                                    System.out.println("Created account and logged in successfully!");
                                } catch (Exception e) {
                                    System.out.println("Account could not be created - email is already taken.");
                                }
                                break;
                            default:
                                System.out.println(errorInput);
                        }

                    } while (currSeller == null);

                    // seller interface goes here

                    do {
                        System.out.println(sellerInterface);
                        int sellerOption;
                        try {
                            sellerOption = s.nextInt();
                            s.nextLine();
                        } catch (Exception e) {
                            System.out.println(errorInput);
                            s.nextLine();
                            continue;
                        }

                        switch (sellerOption) {
                            case 1:  // manage stores - Alan
                                int manageStoreChoice = 0;
                                do {
                                    System.out.println("Please select an option:\n" +
                                            "[1] Display stores\n" +
                                            "[2] Remove store\n" +
                                            "[3] Select store\n" +
                                            "[4] Add store\n" +
                                            "[5] Export store products\n" +
                                            "[6] Exit");
                                    try {
                                        manageStoreChoice = s.nextInt();
                                        s.nextLine();
                                    } catch (Exception e) {
                                        s.nextLine();
                                        System.out.println(errorInput);
                                        continue;
                                    }
                                    switch (manageStoreChoice) {
                                        case 1: // Display stores
                                            sellers = DataManager.readSellerAccountInformation();
                                            for (Seller seller : sellers) {
                                                if (seller.getEmail().equals(email)) {
                                                    System.out.println(seller.viewStores());
                                                    break;
                                                }
                                            }
                                            break;
                                        case 2: // Remove store
                                            System.out.println("Enter the name of the store to be removed:");
                                            String storeForRemovalName = s.nextLine();
                                            if (storeForRemovalName.isEmpty()) {
                                                System.out.println(errorInput);
                                                break;
                                            }
                                            boolean removed = false;
                                            for (int k=0; k<currSellerStores.size(); k++) {
                                                if (currSellerStores.get(k).getName().equalsIgnoreCase(storeForRemovalName)) {
                                                    currSellerStores.remove(k);
                                                    currSeller.setStoreList(currSellerStores);
                                                    removed = true;
                                                }
                                            }
                                            if (!removed) {
                                                System.out.println("Store could not be found.");
                                                break;
                                            } else {
                                                try {
                                                    DataManager.updateSellerInformation(currSeller);
                                                    System.out.println("Store successfully removed.");
                                                } catch (Exception e) {
                                                    System.out.println("Seller information could not be updated.");
                                                    //e.printStackTrace();
                                                }
                                            }
                                            break;
                                        case 3:  // select store
                                            System.out.println("Please enter the store name:");
                                            String storeName = s.nextLine();
                                            if (storeName.isEmpty()) {
                                                System.out.println(errorInput);
                                                break;
                                            }

                                            Store selectedStore = null;
                                            for (int k=0; k<currSellerStores.size(); k++) {
                                                if (currSellerStores.get(k).getName().equalsIgnoreCase(storeName)) {
                                                    System.out.println(currSellerStores.get(k).toString());
                                                    selectedStore = currSellerStores.get(k);
                                                }
                                            }

                                            if (selectedStore == null) {
                                                System.out.println("Your store does not exist or is not owned by you!");
                                                break;
                                            }

                                            int selectStoreChoice = 0;
                                            do {
                                                System.out.println("Please select an option:\n" +
                                                        "[1] Edit product in this store\n" +
                                                        "[2] Remove product from this store\n" +
                                                        "[3] Create product for this store\n" +
                                                        "[4] Exit");
                                                try {
                                                    selectStoreChoice = s.nextInt();
                                                    s.nextLine();
                                                } catch (Exception e) {
                                                    s.nextLine();
                                                    System.out.println(errorInput);
                                                    continue;
                                                }
                                                switch (selectStoreChoice) {
                                                    case 1: // Edit product
                                                        System.out.println("Please enter the name of a product:");
                                                        String productName = s.nextLine();
                                                        if (productName.isEmpty()) {
                                                            System.out.println(errorInput);
                                                            break;
                                                        }

                                                        boolean foundProductToEdit = false;
                                                        Product productToEdit = null;
                                                        for (Product product : selectedStore.getProductList()) {
                                                            if (product.getName().equals(productName)) {
                                                                productToEdit = product;
                                                                foundProductToEdit = true;
                                                                break;
                                                            }
                                                        }
                                                        if (!foundProductToEdit) {
                                                            System.out.printf("No product found matching name " +
                                                                    "\"%s\"\n", productName);
                                                            break;
                                                        } else {
                                                            int editChoice = 0;
                                                            do {
                                                                System.out.println("Please choose an option:\n" +
                                                                        "[1] Edit description\n" +
                                                                        "[2] Edit quantity\n" +
                                                                        "[3] Edit price\n" +
                                                                        "[4] Exit");
                                                                try {
                                                                    editChoice = s.nextInt();
                                                                    s.nextLine();
                                                                } catch (Exception e) {
                                                                    System.out.println(errorInput);
                                                                    s.nextLine();
                                                                    continue;
                                                                }
                                                                switch (editChoice) {
                                                                    case 1:
                                                                        System.out.println("Enter new description:");
                                                                        String newDescription = s.nextLine();
                                                                        productToEdit.setDescription(newDescription);
                                                                        DataManager.updateProducts(
                                                                                "products.txt", productToEdit);
                                                                        System.out.println("Description updated!");
                                                                        break;
                                                                    case 2:
                                                                        System.out.println("Enter new quantity (Must " +
                                                                                "be an integer):");
                                                                        try {
                                                                            int newQuantity = s.nextInt();
                                                                            s.nextLine();
                                                                            productToEdit.setCurrQuantity(newQuantity);

                                                                            productToEdit.setInitialQuantity(newQuantity
                                                                            );
                                                                            DataManager.
                                                                                    updateProducts(
                                                                                            "products.txt",
                                                                                            productToEdit);


                                                                            System.out.println("Quantity updated!");
                                                                        } catch (Exception e) {
                                                                            System.out.println(errorInput);
                                                                            s.nextLine();
                                                                        }
                                                                        break;
                                                                    case 3:
                                                                        System.out.println("Enter new price (Must be " +
                                                                                "a double):");
                                                                        try {
                                                                            double newPrice = s.nextDouble();
                                                                            s.nextLine();
                                                                            productToEdit.setPrice(newPrice);
                                                                            DataManager.updateProducts(
                                                                                    "products.txt",
                                                                                    productToEdit);
                                                                            System.out.println("Price updated!");
                                                                        } catch (Exception e) {
                                                                            System.out.println(errorInput);
                                                                        }
                                                                        break;
                                                                    case 4: //Exit
                                                                        break;
                                                                    default:
                                                                        System.out.println(errorInput);
                                                                }
                                                            } while (editChoice != 4);
                                                        }

                                                        break;
                                                    case 2: // Remove product
                                                        System.out.println("Enter product name");
                                                        String productToRemoveName = s.nextLine();
                                                        if (productToRemoveName.isEmpty()) {
                                                            System.out.println(errorInput);
                                                            break;
                                                        }

                                                        try {
                                                            boolean removedProduct = false;
                                                            for (int i = 0; i < selectedStore.getProductList().size(); i++) {
                                                                Product currProduct =
                                                                        selectedStore.getProductList().get(i);
                                                                if (currProduct.getName().equalsIgnoreCase(productToRemoveName)) {
                                                                    selectedStore.removeProduct(currProduct);
                                                                    DataManager.removeProduct("products.txt",
                                                                            currProduct);
                                                                    removedProduct = true;
                                                                    System.out.println("Product Successfully Removed!");
                                                                }
                                                            }

                                                            if (!removedProduct) {
                                                                System.out.println("The product you have selected " +
                                                                        "does not exist in this store.");
                                                            } else {
                                                                // updating seller information
                                                                for (int i = 0; i < currSellerStores.size(); i++) {
                                                                    if (currSellerStores.get(i).getName().equals(selectedStore.getName())) {
                                                                        currSellerStores.set(i, selectedStore);
                                                                    }
                                                                }
                                                                currSeller.setStoreList(currSellerStores);
                                                            }


                                                        } catch (Exception e) {
                                                            System.out.println("Error in updating seller information");
                                                            //e.printStackTrace();
                                                        }
                                                        break;
                                                    case 3: // Create product
                                                        System.out.println("Enter Product Name: ");
                                                        String newProductName = s.nextLine();
                                                        System.out.println("Enter Description: ");
                                                        String description = s.nextLine();
                                                        System.out.println("Enter Quantity (Integer): ");
                                                        String quantity = s.nextLine();
                                                        int integerQuantity = 0;

                                                        try {
                                                            integerQuantity = Integer.parseInt(quantity);
                                                            if (integerQuantity <= 0) {
                                                                System.out.println("Ensure quantity is greater than " +
                                                                        "zero and try again.");
                                                                break;
                                                            }
                                                        } catch (Exception e) {
                                                            System.out.println("Please enter a valid integer " +
                                                                    "and try again.");
                                                            break;
                                                        }

                                                        System.out.println("Enter Price (Decimal): ");
                                                        String price = s.nextLine();
                                                        double doublePrice = 0;
                                                        try {
                                                            doublePrice = Double.parseDouble(price);
                                                            if (doublePrice <= 0) {
                                                                System.out.println("Ensure price is greater than " +
                                                                        "zero and try again.");
                                                                break;
                                                            }
                                                        } catch (Exception e) {
                                                            System.out.println("Please enter a valid decimal " +
                                                                    "and try again.");
                                                            break;
                                                        }

                                                        Product productToAdd = new Product(newProductName,
                                                                selectedStore.getName(), description, integerQuantity
                                                                , doublePrice);
                                                        selectedStore.addProduct(productToAdd);

                                                        // updating seller information
                                                        for (int i = 0; i < currSellerStores.size(); i++) {
                                                            if (currSellerStores.get(i).getName().equals(selectedStore.getName())) {
                                                                currSellerStores.set(i, selectedStore);
                                                            }
                                                        }
                                                        currSeller.setStoreList(currSellerStores);

                                                        DataManager.updateProducts("products.txt",
                                                                productToAdd);
                                                        System.out.println("Product Added Successfully!");
                                                        break;
                                                    case 4: // Exit
                                                        break;
                                                    default:
                                                        System.out.println(errorInput);
                                                }
                                            } while (selectStoreChoice != 4);
                                            break;
                                        case 4: // Add store
                                            boolean validStore = true;

                                            System.out.println("Enter the name of the store you would like to add " +
                                                    "products to or enter a new store to add: ");
                                            String storeNameAdd = s.nextLine();

                                            // check for empty input
                                            if (storeNameAdd.isEmpty()) {
                                                System.out.println(errorInput);
                                                validStore = false;
                                                break;
                                            }

                                            // check for same store name
                                            for (Seller seller: DataManager.readSellerAccountInformation()) {
                                                for (Store store: seller.getStoreList()) {
                                                    if (storeNameAdd.equals(store.getName())) {
                                                        System.out.println("Sorry! A store with name ["+storeNameAdd+
                                                                "] "+
                                                                "already exists.");
                                                        validStore = false;
                                                        break;
                                                    }
                                                }
                                            }

                                            if (validStore) {
                                                System.out.println("Enter the name of the csv file with the products " +
                                                        "you would like to add to this store, including the \"" +
                                                        ".csv\":");
                                                String fileName = s.nextLine();
                                                if (fileName.isEmpty()) {
                                                    System.out.println(errorInput);
                                                    break;
                                                }
                                                try {

                                                    boolean enableNewStore = true;

                                                    try {
                                                        // update seller info
                                                        currSellerStores.add(DataManager.addStoreProducts(storeNameAdd,
                                                                email,
                                                                fileName));
                                                        currSeller.setStoreList(currSellerStores);
                                                        DataManager.updateSellerInformation(currSeller);
                                                        System.out.println("Store(s) successfully added.");
                                                    } catch (ExistingProductException exists){
                                                        System.out.println("One or more of the products you attempted" +
                                                                " to add to your store has a name that exists in this" +
                                                                " market already. You may only add products of unique" +
                                                                " name.");
                                                    } catch (Exception ex) {
                                                        ex.printStackTrace();   // TODO get rid of
                                                        System.out.println("Error adding store.");
                                                    }

                                                } catch (Exception e) {
                                                    System.out.println("There was an error adding the store to the " +
                                                            "database.");
                                                }
                                            }
                                            break;
                                        case 5: // Export
                                            try {
                                                System.out.println("Enter the name of the file you would like to " +
                                                        "export to: ");
                                                String file = s.nextLine();
                                                if (file.isEmpty()) {
                                                    System.out.println(errorInput);
                                                    break;
                                                }
                                                System.out.println("Enter the name of the store that you want to " +
                                                        "export the products from");
                                                String exportStoreName = s.nextLine();
                                                if (exportStoreName.isEmpty()) {
                                                    System.out.println(errorInput);
                                                    break;
                                                }
                                                ArrayList<Seller> readSellers =
                                                        DataManager.readSellerAccountInformation();
                                                boolean found = false;
                                                if (file.equals("SellerInformation.txt") ||
                                                        file.equals("CustomerInformation.txt") ||
                                                        file.equals("Products.txt") ||
                                                        file.equals("StoresToAdd.txt")
                                                ) {
                                                    throw new IOException();
                                                }
                                                for (int i = 0; i < readSellers.size(); i++) {
                                                    if (readSellers.get(i).getEmail().equals(email)) {
                                                        for (int j = 0; j < readSellers.get(i).getStoreList().size();
                                                             j++) {
                                                            if (readSellers.get(i).getStoreList().get(j).getName()
                                                                    .equals(exportStoreName)) {
                                                                DataManager.exportProducts(exportStoreName, file);
                                                                found = true;
                                                                System.out.println("Your store was exported" +
                                                                        " successfully. You can stop the project " +
                                                                        "to see the new file. ");
                                                            }
                                                        }
                                                    }
                                                }

                                                if (!found) {
                                                    System.out.println("Your store does not exist or " +
                                                            "is not owned by you.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("There was an error exporting" +
                                                        " your products.");
                                            }
                                            break;
                                        case 6:
                                            break;
                                        default:
                                            System.out.println(errorInput);
                                    }
                                } while (manageStoreChoice != 6);
                                break;
                            case 2: // Seller statistics
                                System.out.println("Welcome to your statistics menu.");
                                int statisticsChoice = 0;
                                do {
                                    System.out.println("Please select an option to continue.\n" +
                                            "[1] View all your customers with number of items purchased\n" +
                                            "[2] View all your products & number of sales per product\n" +
                                            "[3] View sales by store, including customer information and revenue\n" +
                                            "[4] View number of products currently in customer's shopping carts\n" +
                                            "[5] Return to main menu");
                                    try {
                                        statisticsChoice = s.nextInt();
                                        s.nextLine();
                                    } catch (Exception e) {
                                        s.nextLine();
                                        System.out.println(errorInput);
                                        continue;
                                    }

                                    switch (statisticsChoice) {
                                        case 1:  // view all customers w/ number of items purchased
                                            int sortChoice = 0;
                                            do {
                                                System.out.println("How would you like to sort? \n" +
                                                        "[1] Alphabetical (by customer name)\n" +
                                                        "[2] Ascending (by products sold per store)\n" +
                                                        "[3] Descending (by products sold per store)\n" +
                                                        "[4] Back");
                                                try {
                                                    sortChoice = s.nextInt();
                                                    s.nextLine();
                                                } catch (Exception e) {
                                                    s.nextLine();
                                                    System.out.println(errorInput);
                                                    continue;
                                                }

                                                try {
                                                    ArrayList<Customer> customers =
                                                            DataManager.readCustomerAccountInformation();
                                                    ArrayList<Seller> sellers1 =
                                                            DataManager.readSellerAccountInformation();
                                                    ArrayList<Customer> sellerCustomers = new ArrayList<Customer>();
                                                    ArrayList<Integer> numProductsPurchased = new ArrayList<Integer>();
                                                    for (Customer c : customers) {
                                                        int itemsPurchased = 0;
                                                        for (Product p : c.getPurchasedProducts()) {

                                                            if (currSeller.getStoreNames().contains(p.getStoreName())
                                                                    && itemsPurchased >= 1) {
                                                                itemsPurchased += 1;

                                                            }
                                                            if (currSeller.getStoreNames().contains(p.getStoreName())
                                                                    && !sellerCustomers.contains(c)) {
                                                                sellerCustomers.add(c);
                                                                itemsPurchased = 1;
                                                            }
                                                        }
                                                        if (itemsPurchased > 0) {
                                                            numProductsPurchased.add(itemsPurchased);
                                                        }
                                                    }

                                                    switch (sortChoice) {
                                                        case 1:   // alphabetical sort
                                                            for (int i = 0; i < sellerCustomers.size() - 1; i++) {
                                                                for (int j = 0; j < sellerCustomers.size() - i - 1; j++)
                                                                {
                                                                    if (sellerCustomers.get(j).getName().toLowerCase()
                                                                            .compareTo(sellerCustomers.get(j + 1)
                                                                                    .getName().toLowerCase()) > 0) {
                                                                        Customer temp = sellerCustomers.get(j);
                                                                        int tempProductsPurchased =
                                                                                numProductsPurchased.get(j);
                                                                        sellerCustomers.set(j,
                                                                                sellerCustomers.get(j + 1));
                                                                        numProductsPurchased.set(j,
                                                                                numProductsPurchased.get(j + 1));
                                                                        sellerCustomers.set(j + 1, temp);
                                                                        numProductsPurchased.set(j + 1,
                                                                                tempProductsPurchased);
                                                                    }
                                                                }
                                                            }
                                                            System.out.println("Your customers (sorted alphabetically "
                                                                    +
                                                                    "by customer name): ");
                                                            for (int i = 0; i < sellerCustomers.size(); i++) {
                                                                System.out.println("Customer Name: " +
                                                                        sellerCustomers.get(i).getName() +
                                                                        ", Purchased Products: " +
                                                                        numProductsPurchased.get(i));
                                                            }
                                                            break;
                                                        case 2:   // ascending by products purchased sort
                                                            for (int i = 0; i < numProductsPurchased.size() - 1; i++) {
                                                                for (int j = 0; j < numProductsPurchased.size() - i - 1;
                                                                     j++) {
                                                                    if (numProductsPurchased.get(j) >
                                                                            numProductsPurchased.get(j + 1)) {
                                                                        Customer temp = sellerCustomers.get(j);
                                                                        int tempProductsPurchased =
                                                                                numProductsPurchased.get(j);
                                                                        sellerCustomers.set(j,
                                                                                sellerCustomers.get(j + 1));
                                                                        numProductsPurchased.set(j,
                                                                                numProductsPurchased.get(j + 1));
                                                                        sellerCustomers.set(j + 1, temp);
                                                                        numProductsPurchased.set(j + 1,
                                                                                tempProductsPurchased);
                                                                    }
                                                                }
                                                            }
                                                            System.out.println("Your customers (sorted ascending by " +
                                                                    "products purchased): ");
                                                            for (int i = 0; i < sellerCustomers.size(); i++) {
                                                                System.out.println("Customer Name: " + sellerCustomers
                                                                        .get(i).getName() +
                                                                        ", Purchased Products: " + numProductsPurchased
                                                                        .get(i));
                                                            }
                                                            break;
                                                        case 3:   // descending by products sold sort
                                                            for (int i = 0; i < numProductsPurchased.size() - 1; i++) {
                                                                for (int j = 0; j < numProductsPurchased.size() - i - 1;
                                                                     j++) {
                                                                    if (numProductsPurchased.get(j) <
                                                                            numProductsPurchased.get(j + 1)) {
                                                                        Customer temp = sellerCustomers.get(j);
                                                                        int tempProductsPurchased =
                                                                                numProductsPurchased.get(j);
                                                                        sellerCustomers.set(j,
                                                                                sellerCustomers.get(j + 1));
                                                                        numProductsPurchased.set(j,
                                                                                numProductsPurchased.get(j + 1));
                                                                        sellerCustomers.set(j + 1, temp);
                                                                        numProductsPurchased.set(j + 1,
                                                                                tempProductsPurchased);
                                                                    }
                                                                }
                                                            }
                                                            System.out.println("Your customers (sorted descending by " +
                                                                    "products purchased): ");
                                                            for (int i = 0; i < sellerCustomers.size(); i++) {
                                                                System.out.println("Customer Name: " + sellerCustomers
                                                                        .get(i).getName() +
                                                                        ", Purchased Products: " + numProductsPurchased
                                                                        .get(i));
                                                            }
                                                            break;
                                                        case 4:
                                                            break;
                                                        default:
                                                            System.out.println(errorInput);
                                                            break;
                                                    }
                                                } catch (IOException io) {
                                                    System.out.println("An error occurred when reading/writing files");
                                                    io.printStackTrace();
                                                }
                                                // repeat if error input (i.e. the sort choice was not 1, 2, or 3)
                                            } while (sortChoice != 4);
                                            break;
                                        case 2:    // view all products w/ number of sales
                                            int sortChoice2 = 0;
                                            do {
                                                System.out.println("How would you like to sort? \n" +
                                                        "[1] Alphabetical (by product name)\n" +
                                                        "[2] Ascending (by quantity of product sold)\n" +
                                                        "[3] Descending (by quantity of product sold)\n" +
                                                        "[4] Back");
                                                try {
                                                    sortChoice2 = s.nextInt();
                                                    s.nextLine();
                                                } catch (Exception e) {
                                                    s.nextLine();
                                                    System.out.println(errorInput);
                                                    continue;
                                                }

                                                try {
                                                    ArrayList<Customer> customers =
                                                            DataManager.readCustomerAccountInformation();
                                                    ArrayList<Product> productsSold = new ArrayList<Product>();
                                                    for (Customer c : customers) {
                                                        for (Product p : c.getPurchasedProducts()) {
                                                            if (currSeller.getStoreNames().contains(p.getStoreName())) {
                                                                productsSold.add(p);
                                                            }
                                                        }
                                                    }

                                                    switch (sortChoice2) {
                                                        case 1:   // alphabetical sort
                                                            for (int i = 0; i < productsSold.size() - 1; i++) {
                                                                for (int j = 0; j < productsSold.size() - i - 1; j++) {
                                                                    if (productsSold.get(j).getName().compareTo(
                                                                            productsSold.get(j + 1).getName()) > 0) {
                                                                        Product temp = productsSold.get(j);
                                                                        productsSold.set(j,
                                                                                productsSold.get(j + 1));
                                                                        productsSold.set(j + 1, temp);
                                                                    }
                                                                }
                                                            }
                                                            System.out.println("Your products (sorted " +
                                                                    "alphabetically " +
                                                                    "by product name): ");
                                                            for (Product sortedProduct : productsSold) {
                                                                System.out.println(sortedProduct.toString());
                                                            }
                                                            break;
                                                        case 2:   // ascending by products purchased sort
                                                            for (int i = 0; i < productsSold.size() - 1; i++) {
                                                                for (int j = 0; j < productsSold.size() - i - 1; j++) {
                                                                    if (productsSold.get(j).getQuantityPurchased() >
                                                                            productsSold.get(j + 1)
                                                                                    .getQuantityPurchased()) {
                                                                        Product temp = productsSold.get(j);
                                                                        productsSold.set(j,
                                                                                productsSold.get(j + 1));
                                                                        productsSold.set(j + 1, temp);
                                                                    }
                                                                }
                                                            }
                                                            System.out.println("Your products (sorted " +
                                                                    "ascending by quantity purchased): ");
                                                            for (Product sortedProduct : productsSold) {
                                                                System.out.println(sortedProduct.toString());
                                                            }
                                                            break;
                                                        case 3:   // descending by products sold sort
                                                            for (int i = 0; i < productsSold.size() - 1; i++) {
                                                                for (int j = 0; j < productsSold.size() - i - 1; j++) {
                                                                    if (productsSold.get(j).getQuantityPurchased() <
                                                                            productsSold.get(j + 1)
                                                                                    .getQuantityPurchased()) {
                                                                        Product temp = productsSold.get(j);
                                                                        productsSold.set(j,
                                                                                productsSold.get(j + 1));
                                                                        productsSold.set(j + 1, temp);
                                                                    }
                                                                }
                                                            }
                                                            System.out.println("Your products (sorted " +
                                                                    "descending by quantity purchased): ");
                                                            for (Product sortedProduct : productsSold) {
                                                                System.out.println(sortedProduct.toString());
                                                            }
                                                            break;
                                                        case 4:
                                                            break;
                                                        default:
                                                            System.out.println(errorInput);
                                                            break;
                                                    }
                                                } catch (IOException io) {
                                                    io.printStackTrace();
                                                    System.out.println("Error in reading/writing files.");
                                                }
                                                // repeat if input (i.e. the sort choice was not [4] Back)
                                            } while (sortChoice2 != 4);   //
                                            break;
                                        case 3:
                                            try {
                                                ArrayList<Customer> customers =
                                                        DataManager.readCustomerAccountInformation();
                                                ArrayList<Seller> sellers1 =
                                                        DataManager.readSellerAccountInformation();
                                                ArrayList<Store> storeList = new ArrayList<>();
                                                for (Seller seller : sellers1) {
                                                    if (seller.equals(currSeller)) {
                                                        storeList = seller.getStoreList();
                                                        break;
                                                    }
                                                }
                                                for (Store store : storeList) {
                                                    System.out.println(store.getName() + " Customers & Revenue:");
                                                    double revenue = 0;
                                                    int numSold = 0;
                                                    for (Product p : store.getProductList()) {
                                                        revenue += p.getPrice() * (p.getInitialQuantity() -
                                                                p.getCurrQuantity());
                                                        numSold += p.getInitialQuantity() - p.getCurrQuantity();
                                                    }
                                                    System.out.printf("Total Revenue: %.2f\n", revenue);
                                                    System.out.println("Total units sold: " + numSold);

                                                    for (Customer customer : customers) {
                                                        ArrayList<Product> purchasedProducts =
                                                                customer.getPurchasedProducts();
                                                        for (Product product : purchasedProducts) {
                                                            if (product.getStoreName().equals(store.getName())) {
                                                                int quantity = customer.getPurchasedProducts()
                                                                        .get(customer.getPurchasedProducts()
                                                                                .indexOf(product)).getCurrQuantity();
                                                                System.out.printf("%s purchased %d of %s. Revenue: " +
                                                                        "%.2f\n", customer.getName(),
                                                                        quantity, product.getName(),
                                                                        quantity * product.getPrice());
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 4: // View number of products in customer's shopping carts
                                            try {
                                                ArrayList<Customer> customers = DataManager.readCustomerAccountInformation();
                                                for (Customer customer : customers) {
                                                    ArrayList<Product> productsInCart = customer.getShoppingCart();
                                                    int totalProductsInCart = 0;

                                                    for (Product product : productsInCart) {
                                                        if (currSeller.getStoreNames().contains(product.getStoreName())) {
                                                            totalProductsInCart += product.getCurrQuantity();
                                                            System.out.printf("Customer Name: %s, Product Name: %s, Store Name: %s, Quantity in Cart: %d%n",
                                                                    customer.getName(), product.getName(), product.getStoreName(), product.getCurrQuantity());
                                                        }
                                                    }

                                                    System.out.printf("Total Products in Cart for %s: %d%n", customer.getName(), totalProductsInCart);
                                                    System.out.println();
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            break;

                                        case 5:
                                            break;
                                        default: // wrong input
                                            System.out.println(errorInput);
                                            break;
                                    }
                                } while (statisticsChoice != 4);
                                break;
                            case 3:  // delete account
                                try {
                                    Seller sellerToDelete = new Seller(name, email, password);

                                    for (Seller seller : sellers) {
                                        if (seller.equals(sellerToDelete)) {

                                            sellers.remove(seller);
                                            break;
                                        }
                                    }
                                    DataManager.writeSellerInformation(sellers);
                                    currSeller = null;
                                    name = "";
                                    email = "";
                                    password = "";
                                } catch (NullPointerException npe) {
                                    System.out.println("Error reading files from database");
                                    //npe.printStackTrace();
                                } catch (IOException e) {
                                    //e.printStackTrace();
                                }
                                break;
                            case 4:  // log out
                                DataManager.updateSellerInformation(currSeller);
                                System.out.println("Logging out...");
                                System.out.println("Logged out successfully!");
                                currSeller = null;
                                name = "";
                                email = "";
                                password = "";
                                break;
                            default:
                                System.out.println(errorInput);
                        }
                    } while (currSeller != null);
                    break;
                case 3: // Exit the portal
                    System.out.println("Thank you for shopping with us!");
                    break;
                default: // Invalid input
                    System.out.println(errorInput);
            }
        } while (choice != 3);


    }
}
