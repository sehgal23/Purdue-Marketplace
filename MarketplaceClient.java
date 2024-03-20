import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MarketplaceClient {

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

    public static void main(String[] args) throws Exception {

        Marketplace marketplace = null;
        ArrayList<Seller> sellers = null;

        Scanner s = new Scanner(System.in);
        int choice = 0;

        do {
            Socket socket = new Socket("localhost", 4242);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());


            System.out.println(welcomeScreen);
            try {
                choice = Integer.parseInt(s.nextLine());
            } catch (Exception e) {
                choice = 0;
            }

            //Sends the User Choice to the Server
            writer.write(choice);
            writer.println();
            writer.flush();

            String name = "";
            String email = "";
            String password = "";

            switch (choice) {
                case 1: // Portal for Customer
                    int customerChoice = 0;

                    do {
                        System.out.println(loginInterface);
                        try {
                            customerChoice = Integer.parseInt(s.nextLine());
                        } catch (Exception e) {
                            customerChoice = 0;
                        }



                        switch (customerChoice) {
                            case 1: // Customer log in

                                // writing the action
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
                                    name = "";
                                    break;
                                }
                                System.out.println("Please enter password:");
                                password = s.nextLine();
                                if (password.isEmpty()) {
                                    System.out.println(errorInput);
                                    name = "";
                                    email = "";
                                    break;
                                }

                                writer.write("customerLogin");
                                writer.println();
                                writer.flush();

                                // writing inputs to login method in server
                                writer.write(name + "," + email + "," + password);
                                writer.println();
                                writer.flush();

                                String currLoginStatus = reader.readLine();
                                if (!currLoginStatus.equals("Login Successful")) {
                                    name = "";
                                    email = "";
                                    password = "";
                                }
                                System.out.println(currLoginStatus);
                                break;
                            case 2: // Customer create account

                                // writing the action

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

                                System.out.println("Please enter password:");
                                String newPassword = s.nextLine();
                                if (newPassword.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }

                                writer.write("customerCreateAccount");
                                writer.println();
                                writer.flush();

                                // writing inputs to create account method in server
                                writer.write(newName + "," + newEmail + "," + newPassword);
                                writer.println();
                                writer.flush();

                                String createAccStatus = reader.readLine();
                                while (!createAccStatus.isEmpty()) {
                                    if (createAccStatus.equals("Created account and logged in successfully!")) {
                                        name = newName;
                                        email = newEmail;
                                        password = newPassword;
                                    }
                                    System.out.println(createAccStatus);
                                    createAccStatus = reader.readLine();
                                }
                                break;
                            default:
                                System.out.println("Please enter a valid option!");

                        }

                    } while (email.isEmpty() || email == null);

                    do {   // run customer interface UNTIL customer logs out or deletes account
                        int customerOption = 0;
                        System.out.println(customerInterface);

                        try {
                            customerOption = Integer.parseInt(s.nextLine());
                        } catch (Exception e) {
                            System.out.println(errorInput);
                            s.nextLine();
                            continue;
                        }

                        switch (customerOption) {
                            case 1:  // customer shop all products

                                // writing the action
                                writer.write("displayAllProducts");
                                writer.println();
                                writer.flush();

                                String currProduct = reader.readLine();
                                while (!currProduct.isEmpty()) {
                                    System.out.println(currProduct);
                                    currProduct = reader.readLine();
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
                                            "[6] Back");
                                    try {
                                        searchChoice = Integer.parseInt(s.nextLine());
                                    } catch (Exception e) {
                                        System.out.println(errorInput);
                                        continue;
                                    }

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
                                            writer.write("searchByKeyword");
                                            writer.println();
                                            writer.flush();

                                            writer.write(searchTerm);
                                            writer.println();
                                            writer.flush();
                                            String output;
                                            output = reader.readLine();
                                            while (!output.isEmpty()) {
                                                System.out.println(output);
                                                output = reader.readLine();
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
                                            writer.write("searchByStoreName");
                                            writer.println();
                                            writer.flush();

                                            writer.write(searchStore);
                                            writer.println();
                                            writer.flush();
                                            String storeOutput = reader.readLine();
                                            while (!storeOutput.isEmpty()) {
                                                System.out.println(storeOutput);
                                                storeOutput = reader.readLine();
                                            }
                                            break;
                                        case 3: // Search by price threshold TODO: Testing (Not Sure if Correct)

                                            double price = 0;
                                            try {
                                                System.out.println("Enter price");
                                                price = Double.parseDouble(s.nextLine());
                                            } catch (Exception e) {
                                                System.out.println("Your price is defaulted to 0.00");
                                                price = 0;
                                            }

                                            writer.write("searchByPriceThreshold");
                                            writer.println();
                                            writer.flush();

                                            writer.write("" + price);
                                            writer.println();
                                            writer.flush();


                                            int higherLower;
                                            do {
                                                try {
                                                    System.out.println("Lower or Higher? \n[1] Lower\n[2] Higher");
                                                    higherLower = Integer.parseInt(s.nextLine());
                                                } catch (Exception e) {
                                                    System.out.println(errorInput);
                                                    higherLower = 0;
                                                }
                                                if (higherLower != 1 && higherLower != 2) {
                                                    System.out.println("Please enter the number either 1 or 2.");
                                                }
                                            } while (higherLower != 1 && higherLower != 2);
                                            writer.write("" + higherLower);
                                            writer.println();
                                            writer.flush();

                                            String searchByThreshResults = reader.readLine();
                                            while (!searchByThreshResults.isEmpty()) {
                                                System.out.println(searchByThreshResults);
                                                searchByThreshResults = reader.readLine();
                                            }
                                            break;
                                        case 4: // Sort by price high to low
                                            writer.write("sortByPrice");
                                            writer.println();
                                            writer.flush();

                                            String priceSort = reader.readLine();
                                            while (!priceSort.isEmpty()) {
                                                System.out.println(priceSort);
                                                priceSort = reader.readLine();
                                            }
                                            break;
                                        case 5: // Sort by quantity
                                            writer.write("sortByQuantity");
                                            writer.println();
                                            writer.flush();

                                            String quantitySort = reader.readLine();
                                            while (!quantitySort.isEmpty()) {
                                                System.out.println(quantitySort);
                                                quantitySort = reader.readLine();
                                            }
                                            break;
                                        case 6: // Exit
                                            break;
                                        default:
                                            keepSearching = true;
                                            System.out.println(errorInput);
                                    }
                                } while (keepSearching);
                                break;
                            case 3:  // customer select product

                                int selectAnotherItem = 0;
                                String addToCart="";

                                do {

                                    System.out.println("Enter the name of the product you would like to select:");
                                    String selectedName = s.nextLine();

                                    if (selectedName.isEmpty()) {
                                        System.out.println(errorInput);
                                        continue;
                                    }

                                    writer.write("selectProduct");
                                    writer.println();
                                    writer.flush();

                                    writer.write(selectedName);
                                    writer.println();
                                    writer.flush();

                                    String selectProductOutput = reader.readLine();

                                    System.out.println(selectProductOutput);

                                    // if product was found
                                    if (!(selectProductOutput.equals("Product could not be found or is out of stock!")
                                            || selectProductOutput.equals("No products exist in the market currently" +
                                            "."))) {
                                        System.out.println("Would you like to add this product to your cart? " +
                                                "Y/N");
                                        addToCart = s.nextLine();

                                        while (!(addToCart.equalsIgnoreCase("y") ||
                                                addToCart.equalsIgnoreCase("n"))) {
                                            System.out.println("Please enter either Y or N.");
                                            addToCart = s.nextLine();
                                        }

                                        if (addToCart.equalsIgnoreCase("y")) {
                                            // writing the action

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

                                            } while (quantity <= 0);

                                            // send inputs to addToCart method in server
                                            writer.write("addToCart");
                                            writer.println();
                                            writer.flush();

                                            writer.write(selectedName + "," + quantity + "," + email);
                                            writer.println();
                                            writer.flush();

                                            String addToCartOutput = reader.readLine();
                                            while(!addToCartOutput.isEmpty()) {
                                                System.out.println(addToCartOutput);
                                                addToCartOutput = reader.readLine();
                                            }

                                            do {
                                                System.out.println("Please choose one of the following options to " +
                                                        "continue: \n" +
                                                        "[1] Select another item\n" +
                                                        "[2] Return to main menu");
                                                try {
                                                    selectAnotherItem = s.nextInt();
                                                    s.nextLine();
                                                    if (selectAnotherItem <= 0 || selectAnotherItem > 2) {
                                                        System.out.println(errorInput);
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("errorInput");
                                                }
                                            } while (selectAnotherItem <= 0 || selectAnotherItem > 2);

                                        }
                                    }

                                } while (selectAnotherItem == 1 && addToCart.equalsIgnoreCase("y"));
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
                                            cartOption = Integer.parseInt(s.nextLine());
                                        } catch (Exception e) {
                                            System.out.println(errorInput);
                                            continue;
                                        }

                                        switch (cartOption) {
                                            case 1:  // view cart

                                                // writing the action
                                                writer.write("viewCart");
                                                writer.println();
                                                writer.flush();

                                                writer.write(email);
                                                writer.println();
                                                writer.flush();

                                                System.out.println("Products in Cart:\n");
                                                String viewCartOutput = reader.readLine();
                                                while (!viewCartOutput.isEmpty()) {
                                                    System.out.println(viewCartOutput);
                                                    viewCartOutput = reader.readLine();
                                                }
                                                break;
                                            case 2:   // remove item from cart

                                                // writing the action

                                                String removeName;
                                                do {
                                                    System.out.println("Enter the name of the product you would like" +
                                                            " to remove: ");
                                                    removeName = s.nextLine();
                                                    if (removeName.isEmpty()) {
                                                        System.out.println(errorInput);
                                                    }
                                                } while (removeName.isEmpty());

                                                writer.write("removeFromCart");
                                                writer.println();
                                                writer.flush();

                                                writer.write(email + "," + removeName);
                                                writer.println();
                                                writer.flush();

                                                String removeFromCartOutput = reader.readLine();
                                                while (!removeFromCartOutput.isEmpty()) {
                                                    System.out.println(removeFromCartOutput);
                                                    removeFromCartOutput = reader.readLine();
                                                }
                                                break;
                                            case 3:   // check out

                                                // writing the action
                                                writer.write("checkout");
                                                writer.println();
                                                writer.flush();

                                                writer.write(email);
                                                writer.println();
                                                writer.flush();

                                                String checkoutOutput = reader.readLine();
                                                while(!checkoutOutput.isEmpty()) {
                                                    System.out.println(checkoutOutput);
                                                    checkoutOutput = reader.readLine();
                                                }
                                                break;
                                            case 4:
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

                                // writing the action
                                writer.write("viewPurchaseHistory");
                                writer.println();
                                writer.flush();

                                writer.write(email);
                                writer.println();
                                writer.flush();

                                System.out.println("Purchase History:\n");
                                String viewPurchasesOutput = reader.readLine();
                                while (!viewPurchasesOutput.isEmpty()) {
                                    System.out.println(viewPurchasesOutput);
                                    viewPurchasesOutput = reader.readLine();
                                }

                                System.out.println("Would you like to export a receipt of your purchase history? " +
                                        "(Y/N)");
                                String response = s.nextLine();

                                while (!(response.equalsIgnoreCase("y") ||
                                        response.equalsIgnoreCase("n"))) {
                                    System.out.println("Please enter either Y or N.");
                                    response = s.nextLine();
                                }

                                if (response.equalsIgnoreCase("y")) {
                                    writer.write("exportPurchaseHistory");
                                    writer.println();
                                    writer.flush();

                                    writer.write(email);
                                    writer.println();
                                    writer.flush();

                                    String exportPurchaseHistoryOutput = reader.readLine();
                                    while (!exportPurchaseHistoryOutput.isEmpty()) {
                                        System.out.println(exportPurchaseHistoryOutput);
                                        exportPurchaseHistoryOutput = reader.readLine();
                                    }
                                }
                                break;
                            case 6: // Customer statistics
                                System.out.println("Welcome to your statistics menu.");
                                int statisticsChoice = 0;
                                do {

                                    // writing the action
                                    writer.write("statistics");
                                    writer.println();
                                    writer.flush();

                                    System.out.println("Please select an option to continue.\n" +
                                            "[1] View all stores with number of products sold\n" +
                                            "[2] View stores purchased from\n" +
                                            "[3] Return to main menu");

                                    do {
                                        try {
                                            statisticsChoice = Integer.parseInt(s.nextLine());
                                        } catch (Exception e) {
                                            System.out.println(errorInput);
                                            continue;
                                        }
                                        if (statisticsChoice != 1 && statisticsChoice != 2 && statisticsChoice != 3) {
                                            System.out.println(errorInput);
                                            continue;
                                        }
                                    } while (statisticsChoice != 1 && statisticsChoice != 2 && statisticsChoice != 3);

                                    if (statisticsChoice == 3) {
                                        break;
                                    }
                                  
                                    int sortChoice = 0;
                                  
                                    do {
                                        System.out.println("How would you like to sort? \n" +
                                            "[1] Alphabetical (by store name)\n" +
                                            "[2] Ascending (by products sold per store)\n" +
                                            "[3] Descending (by products sold per store)");

                                        try {
                                            sortChoice = Integer.parseInt(s.nextLine());
                                        } catch (Exception e) {
                                            sortChoice = 0;
                                            continue;
                                        }
                                        if (sortChoice != 1 && sortChoice != 2 && sortChoice != 3) {
                                            System.out.println(errorInput);
                                        }
                                    } while (sortChoice != 1 && sortChoice != 2 && sortChoice != 3);


                                    // writing the input
                                    writer.write(email+","+statisticsChoice+","+sortChoice);
                                    writer.println();
                                    writer.flush();

                                    String statisticsOutput = reader.readLine();
                                    while (!statisticsOutput.isEmpty()) {
                                        System.out.println(statisticsOutput);
                                        statisticsOutput = reader.readLine();
                                    }
                                } while (statisticsChoice != 3);
                                break;
                            case 7:  // Customer delete account
                                // writing the action
                                writer.write("customerDeleteAccount");
                                writer.println();
                                writer.flush();

                                writer.write(email);
                                writer.println();
                                writer.flush();

                                name = "";
                                email = "";
                                password = "";

                                String customerDeleteOutput = reader.readLine();
                                while (!customerDeleteOutput.isEmpty()) {
                                    System.out.println(customerDeleteOutput);
                                    customerDeleteOutput = reader.readLine();
                                }
                                break;
                            case 8:  // Customer log out of account
                                System.out.println("Logging out...");
                                name = "";
                                email = "";
                                password = "";
                                System.out.println("Logged out succesfully!");
                                break;
                            default:
                                System.out.println(errorInput);
                        }

                    } while (!email.isEmpty());
                    break;
                case 2: // Portal for Seller
                    int sellerChoice;

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
                                    name = "";
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter password:");
                                password = s.nextLine();
                                if (password.isEmpty()) {
                                    name = "";
                                    email = "";
                                    System.out.println(errorInput);
                                    break;
                                }

                                writer.write("sellerLogin");
                                writer.println();
                                writer.flush();

                                // writing inputs to login method in server
                                writer.write(name + "," + email + "," + password);
                                writer.println();
                                writer.flush();

                                String currLoginStatus = reader.readLine();
                                if (!currLoginStatus.equals("Login Successful")) {
                                    name = "";
                                    email = "";
                                    password = "";
                                } else {
                                    Seller potentialNewSeller = new Seller(name, email, password);
                                }

                                System.out.println(currLoginStatus);
                                break;
                            case 2:
                                if (!(name.isEmpty() || email.isEmpty() || password.isEmpty())) {
                                    System.out.println("Must log out to create account.");
                                    break;
                                }
                                System.out.println("Please enter new account name:");
                                String newName = s.nextLine();
                                if (newName.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter new account email:");
                                String newEmail = s.nextLine();
                                if (newEmail.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }
                                System.out.println("Please enter new account password:");
                                String newPassword = s.nextLine();
                                if (newPassword.isEmpty()) {
                                    System.out.println(errorInput);
                                    break;
                                }

                                writer.write("sellerCreateAccount");
                                writer.println();
                                writer.flush();

                                String sendCreateAccountInfo = newName + "," + newEmail + "," + newPassword;
                                writer.write(sendCreateAccountInfo);
                                writer.println();
                                writer.flush();

                                String checkOutputCreateAccount = reader.readLine();
                                while (!checkOutputCreateAccount.isEmpty()) {
                                    if (checkOutputCreateAccount.equals("Created account and logged in successfully!")) {
                                        name = newName;
                                        email = newEmail;
                                        password = newPassword;
                                    }
                                    System.out.println(checkOutputCreateAccount);
                                    checkOutputCreateAccount = reader.readLine();
                                }
                                break;
                            default:
                                System.out.println(errorInput);
                        }

                    } while (email.isEmpty());

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
                                            writer.write("sellerDisplayStores");
                                            writer.println();
                                            writer.flush();
                                            writer.write(email);
                                            writer.println();
                                            writer.flush();
                                            String responseFromReader = reader.readLine();
                                            System.out.println();
                                            while (!responseFromReader.equals("Stop")) {
                                                if (responseFromReader.contains("Store: ")) {
                                                    System.out.println();
                                                    System.out.println(responseFromReader);
                                                } else {
                                                    System.out.println(responseFromReader);
                                                }
                                                responseFromReader = reader.readLine();
                                            }
                                            break;
                                        case 2: // Remove store
                                            System.out.println("Enter the name of the store to be removed:");
                                            String storeForRemovalName = s.nextLine();
                                            if (storeForRemovalName.isEmpty()) {
                                                System.out.println(errorInput);
                                                break;
                                            }
                                            writer.write("removeStore");
                                            writer.println();
                                            writer.flush();

                                            writer.write(email + "," + storeForRemovalName);
                                            writer.println();
                                            writer.flush();
                                            System.out.println(reader.readLine());
                                            break;
                                        case 3:  // select store
                                            System.out.println("Please enter the store name:");
                                            String storeName = s.nextLine();
                                            if (storeName.isEmpty()) {
                                                System.out.println(errorInput);
                                                break;
                                            }
                                            writer.write("storeExists");
                                            writer.println();
                                            writer.flush();

                                            writer.write(storeName + "," + email);
                                            writer.println();
                                            writer.flush();
                                            String responseSelectStore = reader.readLine();
                                            ArrayList<Product> allProducts = new ArrayList<>();
                                            String readValue = "initialized";
                                            boolean endOfProductList = true;
                                            String nameOfProduct = "";
                                            String storeNameOfProduct = "";
                                            String descriptionOfProducts = "";
                                            double priceToAdd = 0.0;
                                            int quantityToAdd = 0;
                                            int counter = 1;
                                            if (responseSelectStore.equals("Error selecting store.")) {
                                                System.out.println("Error selecting store.");
                                                break;
                                            } else {
                                                readValue = reader.readLine();
                                                while (!readValue.equals("Stop")) {
                                                    if (endOfProductList) {
                                                        if ((counter % 5 == 1 && counter > 5) || counter == 1) {
                                                            nameOfProduct = readValue;
                                                        } else if ((counter % 5 == 2 && counter > 5) || counter == 2) {
                                                            storeNameOfProduct = readValue;
                                                        } else if ((counter % 5 == 3 && counter > 5) || counter == 3) {
                                                            descriptionOfProducts = readValue;
                                                        } else if ((counter % 5 == 4 && counter > 5) || counter == 4) {
                                                            priceToAdd = Double.parseDouble(readValue);
                                                        } else if (counter % 5 == 0) {
                                                            quantityToAdd = Integer.parseInt(readValue);
                                                            allProducts.add(new Product(nameOfProduct, storeName, descriptionOfProducts, quantityToAdd, priceToAdd));
                                                        }
                                                        counter++;
                                                    }
                                                    readValue = reader.readLine();
                                                    if (readValue.equals("Stop")) {
                                                        break;
                                                    }
                                                }
                                            }
                                            Store selectedStore = null;
                                            if (allProducts.isEmpty()) {
                                                selectedStore = new Store(null, email, storeName);
                                            } else {
                                                selectedStore = new Store(allProducts, email, allProducts.get(0).getStoreName());
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
                                                        writer.write("productExists");
                                                        writer.println();
                                                        writer.flush();

                                                        writer.write(storeName + "," + productName);
                                                        writer.println();
                                                        writer.flush();
                                                        String response = reader.readLine();

                                                        if (response.equals("Error finding product")) {
                                                            System.out.printf("No product found matching name " +
                                                                    "\"%s\"\n", productName);
                                                            break;
                                                        } else {
                                                            readValue = "";
                                                            counter = 0;
                                                            readValue = reader.readLine();
                                                            while (!readValue.equals("Stop")) {
                                                                counter += 1;
                                                                if (counter == 1) {
                                                                    nameOfProduct = readValue;
                                                                } else if (counter == 2) {
                                                                    storeNameOfProduct = readValue;
                                                                } else if (counter == 3) {
                                                                    descriptionOfProducts = readValue;
                                                                } else if (counter == 4) {
                                                                    quantityToAdd = Integer.parseInt(readValue);
                                                                } else if (counter == 5) {
                                                                    priceToAdd = Double.parseDouble(readValue);
                                                                }
                                                                readValue = reader.readLine();
                                                            }
                                                            Product productToEdit = new Product(nameOfProduct, storeNameOfProduct, descriptionOfProducts, quantityToAdd, priceToAdd);
                                                            System.out.println(productToEdit.toString());
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
                                                                        writer.write("editProductDescription");
                                                                        writer.println();
                                                                        writer.flush();
                                                                        System.out.println("Enter new description:");
                                                                        String newDescription = s.nextLine();
                                                                        writer.write(productToEdit.getName() + "," + productToEdit.getStoreName() + "," + newDescription);
                                                                        writer.println();
                                                                        writer.flush();
                                                                        productToEdit.setDescription(newDescription);
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
                                                                            writer.write("editProductQuantity");
                                                                            writer.println();
                                                                            writer.flush();

                                                                            writer.write(productToEdit.getName() + "," + productToEdit.getStoreName() + "," + String.valueOf(newQuantity));
                                                                            writer.println();
                                                                            writer.flush();

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
                                                                            writer.write("editProductPrice");
                                                                            writer.println();
                                                                            writer.flush();

                                                                            writer.write(productToEdit.getName() + "," + productToEdit.getStoreName() + "," + String.valueOf(newPrice));
                                                                            writer.println();
                                                                            writer.flush();
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
                                                        writer.write("removeProduct");
                                                        writer.println();
                                                        writer.flush();

                                                        writer.write(selectedStore.getName() + "," + productToRemoveName);
                                                        writer.println();
                                                        writer.flush();

                                                        String checkIfSuccessful = reader.readLine();
                                                        if (checkIfSuccessful.equals("Error")) {
                                                            System.out.println("Error in updating seller information");
                                                            break;
                                                        } else {
                                                            System.out.println("Product successfully removed!");
                                                            //Updating Seller Information - TODO WORK IN PROGRESS
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

                                                        writer.write("createProduct");
                                                        writer.println();
                                                        writer.flush();

                                                        writer.write(newProductName + "," + selectedStore.getName() + "," + description + "," + quantity + "," + price);
                                                        writer.println();
                                                        writer.flush();

                                                        String readerResponseAddProduct = reader.readLine();
                                                        if (!readerResponseAddProduct.equals("Error")) {
                                                            System.out.println("Product Added Successfully!");
                                                        } else {
                                                            System.out.println("There was an error adding your product.");
                                                        }
                                                        break;
                                                    case 4: // Exit
                                                        break;
                                                    default:
                                                        System.out.println(errorInput);
                                                }
                                            } while (selectStoreChoice != 4);
                                            break;
                                        case 4: // Add store
                                            int manageAddStoreChoice = 0;
                                            do {
                                                System.out.println("Please select an option:\n" +
                                                        "[1] Add Store with CSV File\n" +
                                                        "[2] Add New Empty Store\n" +
                                                        "[3] Exit");
                                                try {
                                                    manageAddStoreChoice = s.nextInt();
                                                    s.nextLine();
                                                } catch (Exception e) {
                                                    System.out.println(errorInput);
                                                    s.nextLine();
                                                    continue;
                                                }

                                                boolean validStore = true;
                                                switch (manageAddStoreChoice) {
                                                    case 1:
                                                        System.out.println("Enter the name of the store you would like to add " +
                                                                "products to or enter a new store to add: ");
                                                        String storeNameAdd = s.nextLine();
                                                        System.out.println("Enter the name of the csv file with the products " +
                                                                "you would like to add to this store, including the \"" +
                                                                ".csv\":");
                                                        System.out.println("NOTE: Your CSV File must be in the format: 'Name', 'Description', 'Quantity', 'Price'");
                                                        String fileName = s.nextLine();

                                                        // check for empty input
                                                        if (storeNameAdd.isEmpty()) {
                                                            System.out.println(errorInput);
                                                            validStore = false;
                                                            break;
                                                        }

                                                        writer.write("addStoreWithCSV");
                                                        writer.println();
                                                        writer.flush();

                                                        writer.write(storeNameAdd + "," + email + "," + fileName);
                                                        writer.println();
                                                        writer.flush();

                                                        String responseFromAddStore = reader.readLine();
                                                        if (responseFromAddStore.equals("Error")) {
                                                            System.out.println("There was an error adding products to the store.");
                                                            break;
                                                        } else if (responseFromAddStore.equals("storeExists")) {
                                                            System.out.println("Sorry! A store with name [" + storeNameAdd +
                                                                    "] " +
                                                                    "already exists.");
                                                            break;
                                                        } else if (responseFromAddStore.equals("NotValidStoreOption")) {
                                                            System.out.println("There was an error with your file.");
                                                        } else if (responseFromAddStore.equals("ProductError")) {
                                                            System.out.println("One or more of the products you attempted" +
                                                                    " to add to your store has a name that exists in this" +
                                                                    " market already. You may only add products of unique" +
                                                                    " name.");
                                                        } else {
                                                            System.out.println("Successfully Added New Store!");
                                                        }

                                                        // check for same store name

                                                        break;

                                                    case 2: // Add Store
                                                        System.out.println("Enter the name of the store you would like to add: ");
                                                        String storeNameToAdd = s.nextLine();

                                                        // check for empty input
                                                        if (storeNameToAdd.isEmpty()) {
                                                            System.out.println(errorInput);
                                                            validStore = false;
                                                            break;
                                                        }

                                                        writer.write("addBlankStore");
                                                        writer.println();
                                                        writer.flush();

                                                        writer.write(storeNameToAdd + "," + email);
                                                        writer.println();
                                                        writer.flush();

                                                        String responseFromAddBlankStore = reader.readLine();
                                                        if (responseFromAddBlankStore.equals("Error")) {
                                                            System.out.println("There was an error creating a new store.");
                                                            break;
                                                        } else if (responseFromAddBlankStore.equals("Error2")) {
                                                            System.out.println("Your Store Name is not valid and/or taken.");
                                                            break;
                                                        } else {
                                                            System.out.println("Store Successfully Created!");
                                                        }

                                                    case 3: // Exit
                                                        break;

                                                    default:
                                                        System.out.println(errorInput);
                                                }
                                            } while (manageAddStoreChoice != 3);
                                            break;
                                        case 5: // Export
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

                                            writer.write("exportStoreProducts");
                                            writer.println();
                                            writer.flush();

                                            writer.write(file + "," + email + "," + exportStoreName);
                                            writer.println();
                                            writer.flush();

                                            String readerResponseExport = reader.readLine();
                                            if (!readerResponseExport.equals("Error")) {
                                                System.out.println("File Exported Successfully!");
                                            } else {
                                                System.out.println("There was an error exporting to a file.");
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
                                            "[4] View number of products currently in customer's shopping cart\n" +
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
                                                // writing the action

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

                                                writer.write("sellerDisplayAllCustomers");
                                                writer.println();
                                                writer.flush();


                                                switch (sortChoice) {
                                                    case 1:   // alphabetical sort
                                                        // writing the input 
                                                        writer.write(email + ",alphabetical");
                                                        writer.println();
                                                        writer.flush();

                                                        String alphabeticalOutput = reader.readLine();
                                                        while (!alphabeticalOutput.isEmpty()) {
                                                            System.out.println(alphabeticalOutput);
                                                            alphabeticalOutput = reader.readLine();
                                                        }
                                                        break;
                                                    case 2:   // ascending by products purchased sort
                                                        // writing the input 
                                                        writer.write(email + ",ascending");
                                                        writer.println();
                                                        writer.flush();

                                                        String ascendingOutput = reader.readLine();
                                                        while (!ascendingOutput.isEmpty()) {
                                                            System.out.println(ascendingOutput);
                                                            ascendingOutput = reader.readLine();
                                                        }
                                                        break;
                                                    case 3:   // descending by products sold sort
                                                        // writing the input
                                                        writer.write(email + ",descending");
                                                        writer.println();
                                                        writer.flush();

                                                        String descendingOutput = reader.readLine();
                                                        while (!descendingOutput.isEmpty()) {
                                                            System.out.println(descendingOutput);
                                                            descendingOutput = reader.readLine();
                                                        }
                                                        break;
                                                    case 4:
                                                        // have to still write input to keep order of action -> input
                                                        writer.write(email+",defaultCase");
                                                        writer.println();
                                                        writer.flush();

                                                        String defaultOutput = reader.readLine();
                                                        while (!defaultOutput.isEmpty()) {
                                                            defaultOutput = reader.readLine();
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println(errorInput);
                                                        break;
                                                }
                                                // repeat if error input (i.e. the sort choice was not 1, 2, or 3)
                                            } while (sortChoice != 4);
                                            break;
                                        case 2:    // view all products w/ number of sales

                                            int sortChoice2 = 0;
                                            do {
                                                // writing the action
                                                writer.write("sellerDisplayAllProductsAndSales");
                                                writer.println();
                                                writer.flush();

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

                                                switch (sortChoice2) {
                                                    case 1:   // alphabetical sort
                                                        // writing the action
                                                        writer.write(email + ",alphabetical");
                                                        writer.println();
                                                        writer.flush();

                                                        String alphabeticalOutput = reader.readLine();
                                                        while (!alphabeticalOutput.isEmpty()) {
                                                            System.out.println(alphabeticalOutput);
                                                            alphabeticalOutput = reader.readLine();
                                                        }
                                                        break;
                                                    case 2:   // ascending by products purchased sort
                                                        // writing the action
                                                        writer.write(email + ",ascending");
                                                        writer.println();
                                                        writer.flush();

                                                        String ascendingOutput = reader.readLine();
                                                        while (!ascendingOutput.isEmpty()) {
                                                            System.out.println(ascendingOutput);
                                                            ascendingOutput = reader.readLine();
                                                        }
                                                        break;
                                                    case 3:   // descending by products sold sort
                                                        // writing the action
                                                        writer.write(email + ",descending");
                                                        writer.println();
                                                        writer.flush();

                                                        String descendingOutput = reader.readLine();
                                                        while (!descendingOutput.isEmpty()) {
                                                            System.out.println(descendingOutput);
                                                            descendingOutput = reader.readLine();
                                                        }
                                                        break;
                                                    case 4:
                                                        // have to still write input to keep order of action -> input
                                                        writer.write(email+",defaultCase");
                                                        writer.println();
                                                        writer.flush();

                                                        String defaultOutput1 = reader.readLine();
                                                        while (!defaultOutput1.isEmpty()) {
                                                            defaultOutput1 = reader.readLine();
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println(errorInput);
                                                        break;
                                                }
                                                // repeat if input (i.e. the sort choice was not [4] Back)
                                            } while (sortChoice2 != 4);   //
                                            break;
                                        case 3:
                                            // writing the action
                                            writer.write("sellerDisplaySalesWithInfo");
                                            writer.println();
                                            writer.flush();
                                            // writing the input
                                            writer.write(email);
                                            writer.println();
                                            writer.flush();

                                            String displaySalesInfoOutput = reader.readLine();
                                            while (!displaySalesInfoOutput.isEmpty()) {
                                                System.out.println(displaySalesInfoOutput);
                                                displaySalesInfoOutput = reader.readLine();
                                            }
                                            break;
                                        case 4:    // view products in customer shopping carts
                                            // writing the action
                                            writer.write("sellerViewProductsInCarts");
                                            writer.println();
                                            writer.flush();

                                            // writing input
                                            writer.write(email);
                                            writer.println();
                                            writer.flush();

                                            // Handle the response from the server containing products in customer shopping carts
                                            String productsInCarts = reader.readLine();
                                            while (!productsInCarts.isEmpty()) {
                                                System.out.println(productsInCarts);
                                                productsInCarts = reader.readLine();
                                            }
                                            break;
                                        case 5:
                                            break;
                                        default: // wrong input
                                            System.out.println(errorInput);
                                            break;
                                    }
                                } while (statisticsChoice != 5);
                                break;
                            case 3:  // delete account
                                // writing the action
                                writer.write("sellerDeleteAccount");
                                writer.println();
                                writer.flush();

                                writer.write(email);
                                writer.println();
                                writer.flush();

                                name = "";
                                email = "";
                                password = "";

                                String customerDeleteOutput = reader.readLine();
                                while (!customerDeleteOutput.isEmpty()) {
                                    System.out.println(customerDeleteOutput);
                                    customerDeleteOutput = reader.readLine();
                                }
                                break;
                            case 4:  // log out
                                System.out.println("Logging out...");
                                System.out.println("Logged out successfully!");
                                name = "";
                                email = "";
                                password = "";
                                break;
                            default:
                                System.out.println(errorInput);
                        }
                    } while (!email.isEmpty());
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
