import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class MarketplaceClientGUI {


    private static JFrame frame;
    private static JPanel cardPanel;
    private static CardLayout cardLayout;

    private static String name = "";
    private static String email = "";
    private static String password = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Marketplace Portal");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        createWelcomePanel();
        createCustomerPanel();
        createSellerPanel();

        frame.add(cardPanel);
        cardLayout.show(cardPanel, "Welcome");

        frame.setVisible(true);


    }

    private static void createWelcomePanel() {
        try {
            Socket socket = new Socket("localhost", 4242);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());


            JPanel welcomePanel = new JPanel(new GridLayout(3, 1));
            JButton customerButton = new JButton("Customer Portal");
            JButton sellerButton = new JButton("Seller Portal");
            customerButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
            sellerButton.setFont(new Font("Times New Roman", Font.BOLD, 18));


            JLabel welcomeLabel = new JLabel("Welcome to our Marketplace!");
            welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
            welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
            welcomePanel.add(welcomeLabel);
            welcomePanel.add(customerButton);
            welcomePanel.add(sellerButton);

            customerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Your PrintWriter operations
                    writer.write(1);
                    writer.println();
                    writer.flush();


                    cardLayout.show(cardPanel, "customer login");
                }
            });

            sellerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Your PrintWriter operations
                    writer.write(2);
                    writer.println();
                    writer.flush();


                    cardLayout.show(cardPanel, "seller login");
                }
            });


            cardPanel.add(welcomePanel, "Welcome");

        } catch (IOException e) {
            System.out.println();
        }


    }

    private static void createCustomerPanel() {
        try {
            Socket socket = new Socket("localhost", 4242);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //Login Panel
            JPanel customerLoginPanel = new JPanel(new GridLayout(3, 1));
            JButton customerLoginButton = new JButton("Login");
            JButton customerCreateAccButton = new JButton("Create an Account");

            customerLoginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
            customerCreateAccButton.setFont(new Font("Times New Roman", Font.BOLD, 18));

            JLabel cLoginLabel = new JLabel("Please select whether you would like to Login or Create an Account!");
            cLoginLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
            cLoginLabel.setHorizontalAlignment(JLabel.CENTER);

            customerLoginPanel.add(cLoginLabel);
            customerLoginPanel.add(customerLoginButton);
            customerLoginPanel.add(customerCreateAccButton);

            customerLoginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Your PrintWriter operations
                    writer.write(1);
                    writer.println();
                    writer.flush();


                    cardLayout.show(cardPanel, "customer login credentials");
                }
            });
            cardPanel.add(customerLoginPanel, "customer login");

            customerCreateAccButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.write(0);
                    writer.println();
                    writer.flush();

                    cardLayout.show(cardPanel, "customer create account credentials");

                }
            });

            //LoginCredentials Panel
            JPanel cLoginCredPanel = new JPanel(null);

            JLabel cUserLabel = new JLabel("Name:");
            cUserLabel.setBounds(10, 20, 80, 25);
            cLoginCredPanel.add(cUserLabel);

            JTextField userText = new JTextField(20);
            userText.setBounds(100, 20, 165, 25);
            cLoginCredPanel.add(userText);

            JLabel cEmailLabel = new JLabel("Email:");
            cEmailLabel.setBounds(10, 50, 80, 25);
            cLoginCredPanel.add(cEmailLabel);

            JTextField emailText = new JTextField(20);
            emailText.setBounds(100, 50, 165, 25);
            cLoginCredPanel.add(emailText);


            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(10, 80, 80, 25);
            cLoginCredPanel.add(passwordLabel);


            JPasswordField passwordText = new JPasswordField(20);
            passwordText.setBounds(100, 80, 165, 25);
            cLoginCredPanel.add(passwordText);

            JButton backToLoginButton = new JButton("Back");
            backToLoginButton.setBounds(100, 110, 80, 25);
            cLoginCredPanel.add(backToLoginButton);

            backToLoginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "Welcome");
                }

            });

            //createAccountPanel

            JPanel cCreateAccountPanel = new JPanel(null);

            JLabel cCreateUserLabel = new JLabel("Name:");
            cCreateUserLabel.setBounds(10, 20, 80, 25);
            cCreateAccountPanel.add(cCreateUserLabel);

            JTextField createUserText = new JTextField(20);
            createUserText.setBounds(100, 20, 165, 25);
            cCreateAccountPanel.add(createUserText);

            JLabel cCreateEmailLabel = new JLabel("Email:");
            cCreateEmailLabel.setBounds(10, 50, 80, 25);
            cCreateAccountPanel.add(cCreateEmailLabel);

            JTextField createEmailText = new JTextField(20);
            createEmailText.setBounds(100, 50, 165, 25);
            cCreateAccountPanel.add(createEmailText);

            JLabel createPasswordLabel = new JLabel("Password");
            createPasswordLabel.setBounds(10, 80, 80, 25);
            cCreateAccountPanel.add(createPasswordLabel);

            JPasswordField createPasswordText = new JPasswordField(20);
            createPasswordText.setBounds(100, 80, 165, 25);
            cCreateAccountPanel.add(createPasswordText);

            // Creating create account button
            JButton createAccountButton = new JButton("Create Account");
            createAccountButton.setBounds(10, 110, 150, 25);
            cCreateAccountPanel.add(createAccountButton);

            JButton backToLoginButton2 = new JButton("Back");
            backToLoginButton2.setBounds(175, 110, 80, 25);
            cCreateAccountPanel.add(backToLoginButton2);

            backToLoginButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "Welcome");
                }

            });

            createAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Your PrintWriter operations

                        String createName = createUserText.getText();
                        String createEmail = createEmailText.getText();
                        String createPassword = createPasswordText.getText();

                        if (createName.isEmpty() || createEmail.isEmpty() || createPassword.isEmpty()) {
                            throw new Exception();
                        }

                        writer.write("customerCreateAccount");
                        writer.println();
                        writer.flush();

                        writer.write(createName + "," + createEmail + "," + createPassword);
                        writer.println();
                        writer.flush();

                        String createAccStatus = reader.readLine();
                        String allCreateAccStatus = "";
                        Boolean accFound = false;
                        while (!createAccStatus.isEmpty()) {
                            if (createAccStatus.equals("Created account and logged in successfully!")) {
                                accFound = true;
                            }
                            allCreateAccStatus = createAccStatus + "\n";
                            createAccStatus = reader.readLine();
                        }

                        JOptionPane.showMessageDialog(frame, allCreateAccStatus, "Account Status",
                                JOptionPane.PLAIN_MESSAGE);

                        if (accFound) {
                            name = createName;
                            email = createEmail;
                            password = createPassword;
                            cardLayout.show(cardPanel, "after customer login");
                        }


                        createUserText.setText("");
                        createEmailText.setText("");
                        createPasswordText.setText("");
                    } catch (IOException e2) {
                        System.out.println();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all inputs.", "ErrorInput",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            });
            cardPanel.add(cCreateAccountPanel, "customer create account credentials");


            // Creating login button
            JButton loginButton = new JButton("Login");
            loginButton.setBounds(10, 110, 80, 25);
            cLoginCredPanel.add(loginButton);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Your PrintWriter operations
                        writer.write("customerLogin");
                        writer.println();
                        writer.flush();

                        name = userText.getText();
                        email = emailText.getText();
                        password = passwordText.getText();

                        JTextArea alrLogged = new JTextArea("Already logged in");
                        alrLogged.setEditable(false);
                        alrLogged.setBounds(10, 140, 80, 25);

                        JTextArea errorInput = new JTextArea("Invalid credentials");
                        errorInput.setEditable(false);
                        errorInput.setBounds(10, 140, 150, 25);

                        // Your PrintWriter operations
                        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill out all input.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            // writing inputs to login method in server
                            writer.write(name + "," + email + "," + password);
                            writer.println();
                            writer.flush();

                            String currLoginStatus = reader.readLine();

                            if (!currLoginStatus.equals("Login Successful")) {
                                name = "";
                                email = "";
                                password = "";
                                JOptionPane.showMessageDialog(null, "Invalid Credentials",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                cardLayout.show(cardPanel, "after customer login");
                                userText.setText("");
                                emailText.setText("");
                                passwordText.setText("");
                            }
                        }

                    } catch (IOException e1) {
                        System.out.println();
                    }
                }
            });


            cardPanel.add(cLoginCredPanel, "customer login credentials");
        } catch (IOException e) {
            System.out.println();
        }
        //after Customer Login

        JPanel customerOptions = new JPanel(null);
        customerOptions.setBackground(Color.LIGHT_GRAY);

        String[] options = {
                "Display All Products",
                "Search Products",
                "Select Product",
                "Manage Shopping Cart",
                "View Purchase History",
                "Export Purchase History",
                "Statistics",
                "Delete Account",
                "Log Out"
        };

        JComboBox<String> optionsDropdown = new JComboBox<>(options);
        optionsDropdown.setBounds(10, 10, 200, 25);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 40, 80, 25);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 4242);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String selectedOption = (String) optionsDropdown.getSelectedItem();

                    if (selectedOption.equals("Display All Products")) {
                        writer.write("displayAllProducts");
                        writer.println();
                        writer.flush();

                        String currProduct = reader.readLine();
                        String allProducts = "";
                        while (!currProduct.isEmpty()) {
                            allProducts += currProduct + "\n\n";
                            currProduct = reader.readLine();
                        }

                        JTextArea textAreaDispProd =
                                new JTextArea(allProducts);
                        JScrollPane scrollPaneDispProd = new JScrollPane(textAreaDispProd);
                        textAreaDispProd.setLineWrap(true);
                        textAreaDispProd.setWrapStyleWord(true);
                        scrollPaneDispProd.setPreferredSize(new Dimension(500, 500));

                        JOptionPane.showMessageDialog(null, scrollPaneDispProd, "Products:",
                                JOptionPane.PLAIN_MESSAGE);
                    }

                    if (selectedOption.equals("Search Products")) {
                        JPanel searchProductsPanel = new JPanel(null);
                        searchProductsPanel.setBackground(Color.LIGHT_GRAY);
                        String[] searchOptions = {
                                "Search by keyword",
                                "Search by store name",
                                "Search by price threshold",
                                "Sort by price",
                                "Sort by quantity available",
                                "Back"
                        };

                        JComboBox<String> optionsDropdown2 = new JComboBox<>(searchOptions);
                        optionsDropdown2.setBounds(10, 10, 200, 25);

                        JButton submitButton2 = new JButton("Submit");
                        submitButton2.setBounds(10, 40, 80, 25);

                        searchProductsPanel.add(optionsDropdown2);
                        searchProductsPanel.add(submitButton2);

                        cardPanel.add(searchProductsPanel, "search products");
                        cardLayout.show(cardPanel, "search products");

                        submitButton2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String selectedSearchOption = (String) optionsDropdown2.getSelectedItem();
                                if (selectedSearchOption.equals("Search by keyword")) {
                                    JPanel keywordPanel = new JPanel(null);
                                    keywordPanel.setBackground(Color.LIGHT_GRAY);

                                    JLabel searchTermLabel = new JLabel("Enter your search term:");
                                    searchTermLabel.setBounds(10, 20, 160, 25);
                                    keywordPanel.add(searchTermLabel);

                                    JTextField searchTermText = new JTextField(20);
                                    searchTermText.setBounds(10, 50, 165, 25);
                                    keywordPanel.add(searchTermText);

                                    JButton searchButton = new JButton("Search");
                                    searchButton.setBounds(10, 80, 80, 25);
                                    keywordPanel.add(searchButton);

                                    JButton backToSearchOptionsButton = new JButton("Back");
                                    backToSearchOptionsButton.setBounds(100, 80, 80, 25);
                                    keywordPanel.add(backToSearchOptionsButton);

                                    backToSearchOptionsButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            cardLayout.show(cardPanel, "search products");
                                        }

                                    });

                                    searchButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                Socket socket = new Socket("localhost", 4242);
                                                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.
                                                        getInputStream()));
                                                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                                                String searchTerm = searchTermText.getText();
                                                writer.write("searchByKeyword");
                                                writer.println();
                                                writer.flush();
                                                writer.write(searchTerm);
                                                writer.println();
                                                writer.flush();
                                                String output = reader.readLine();
                                                String allOutputs = "";
                                                while (!output.isEmpty()) {
                                                    allOutputs += output + "\n";
                                                    output = reader.readLine();
                                                }


                                                JOptionPane.showMessageDialog(null, allOutputs,
                                                        "Search Results:", JOptionPane.PLAIN_MESSAGE);
                                            } catch (IOException e4) {
                                                System.out.println();
                                            }
                                        }
                                    });

                                    cardPanel.add(keywordPanel, "keyword search");
                                    cardLayout.show(cardPanel, "keyword search");
                                }

                                if (selectedSearchOption.equals("Search by store name")) {
                                    JPanel storeNamePanel = new JPanel(null);
                                    storeNamePanel.setBackground(Color.LIGHT_GRAY);

                                    JLabel searchTermLabel2 = new JLabel("Enter your search term:");
                                    searchTermLabel2.setBounds(10, 20, 160, 25);
                                    storeNamePanel.add(searchTermLabel2);

                                    JTextField searchTermText2 = new JTextField(20);
                                    searchTermText2.setBounds(10, 50, 165, 25);
                                    storeNamePanel.add(searchTermText2);

                                    JButton searchButton2 = new JButton("Search");
                                    searchButton2.setBounds(10, 80, 80, 25);
                                    storeNamePanel.add(searchButton2);

                                    JButton backToSearchOptionsButton2 = new JButton("Back");
                                    backToSearchOptionsButton2.setBounds(100, 80, 80, 25);
                                    storeNamePanel.add(backToSearchOptionsButton2);

                                    backToSearchOptionsButton2.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            cardLayout.show(cardPanel, "search products");
                                        }

                                    });

                                    searchButton2.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                Socket socket = new Socket("localhost", 4242);
                                                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.
                                                        getInputStream()));
                                                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                                                String searchStore = searchTermText2.getText();
                                                writer.write("searchByStoreName");
                                                writer.println();
                                                writer.flush();
                                                writer.write(searchStore);
                                                writer.println();
                                                writer.flush();
                                                String storeOutput = reader.readLine();
                                                String allStoreOutputs = "";
                                                while (!storeOutput.isEmpty()) {
                                                    allStoreOutputs += storeOutput + "\n";
                                                    storeOutput = reader.readLine();
                                                }
                                                JOptionPane.showMessageDialog(null, allStoreOutputs,
                                                        "Search Results:", JOptionPane.PLAIN_MESSAGE);
                                            } catch (IOException e4) {
                                                System.out.println();
                                            }
                                        }
                                    });

                                    cardPanel.add(storeNamePanel, "store search");
                                    cardLayout.show(cardPanel, "store search");
                                }

                                if (selectedSearchOption.equals("Search by price threshold")) {
                                    JPanel priceThresholdPanel = new JPanel(null);
                                    priceThresholdPanel.setBackground(Color.LIGHT_GRAY);


                                    JLabel searchTermLabel3 = new JLabel("Enter price:");
                                    searchTermLabel3.setBounds(10, 20, 160, 25);
                                    priceThresholdPanel.add(searchTermLabel3);

                                    JTextField searchTermText3 = new JTextField(20);
                                    searchTermText3.setBounds(10, 50, 165, 25);
                                    priceThresholdPanel.add(searchTermText3);

                                    String[] priceThresholdOptions = {
                                            "Lower",
                                            "Higher"
                                    };

                                    JComboBox<String> priceThresholdDropdown = new JComboBox<>(priceThresholdOptions);
                                    priceThresholdDropdown.setBounds(8, 80, 200, 25);
                                    priceThresholdPanel.add(priceThresholdDropdown);

                                    JButton searchButton3 = new JButton("Search");
                                    searchButton3.setBounds(10, 150, 80, 25);
                                    priceThresholdPanel.add(searchButton3);

                                    JButton backToSearchOptionsButton3 = new JButton("Back");
                                    backToSearchOptionsButton3.setBounds(100, 150, 80, 25);
                                    priceThresholdPanel.add(backToSearchOptionsButton3);

                                    backToSearchOptionsButton3.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            cardLayout.show(cardPanel, "search products");
                                        }

                                    });

                                    searchButton3.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                Socket socket = new Socket("localhost", 4242);
                                                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.
                                                        getInputStream()));
                                                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                                                String tempPrice = searchTermText3.getText();
                                                String priceThreshold =
                                                        (String) priceThresholdDropdown.getSelectedItem();
                                                int price = Integer.parseInt(tempPrice);
                                                if (price < 0) {
                                                    throw new Exception();
                                                }
                                                writer.write("searchByPriceThreshold");
                                                writer.println();
                                                writer.flush();
                                                writer.write("" + price);
                                                writer.println();
                                                writer.flush();

                                                int higherLower = 0;
                                                if (priceThreshold != null) {
                                                    if (priceThreshold.equals("Lower")) {
                                                        higherLower = 1;
                                                    } else {
                                                        higherLower = 2;
                                                    }
                                                }
                                                writer.write("" + higherLower);
                                                writer.println();
                                                writer.flush();
                                                String priceThresholdOutput = reader.readLine();
                                                String allPriceThresholdOutputs = "";
                                                while (!priceThresholdOutput.isEmpty()) {
                                                    allPriceThresholdOutputs += priceThresholdOutput + "\n\n";
                                                    priceThresholdOutput = reader.readLine();
                                                }

                                                JTextArea textArea = new JTextArea(allPriceThresholdOutputs);
                                                JScrollPane scrollPane = new JScrollPane(textArea);
                                                textArea.setLineWrap(true);
                                                textArea.setWrapStyleWord(true);
                                                scrollPane.setPreferredSize(new Dimension(500, 500));

                                                JOptionPane.showMessageDialog(null, scrollPane,
                                                        "Search Results:",
                                                        JOptionPane.PLAIN_MESSAGE);

                                            } catch (Exception e1) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Please enter a valid price.",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    });

                                    cardPanel.add(priceThresholdPanel, "price threshold search");
                                    cardLayout.show(cardPanel, "price threshold search");
                                }
                                if (selectedSearchOption.equals("Sort by price")) {
                                    JPanel pricePanel = new JPanel(null);
                                    pricePanel.setBackground(Color.LIGHT_GRAY);


                                    try {
                                        Socket socket = new Socket("localhost", 4242);
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket
                                                .getInputStream()));
                                        PrintWriter writer = new PrintWriter(socket.getOutputStream());

                                        writer.write("sortByPrice");
                                        writer.println();
                                        writer.flush();
                                        String priceOutput = reader.readLine();
                                        String allPriceOutputs = "";
                                        while (!priceOutput.isEmpty()) {
                                            allPriceOutputs += priceOutput + "\n\n";
                                            priceOutput = reader.readLine();
                                        }

                                        JTextArea textArea = new JTextArea(allPriceOutputs);
                                        JScrollPane scrollPane = new JScrollPane(textArea);
                                        textArea.setLineWrap(true);
                                        textArea.setWrapStyleWord(true);
                                        scrollPane.setPreferredSize(new Dimension(500, 500));

                                        JOptionPane.showMessageDialog(null, scrollPane,
                                                "Search Results:",
                                                JOptionPane.PLAIN_MESSAGE);
                                    } catch (IOException e4) {
                                        System.out.println();
                                    }


                                }
                                if (selectedSearchOption.equals("Sort by quantity available")) {
                                    JPanel quantityAvailablePanel = new JPanel(null);
                                    quantityAvailablePanel.setBackground(Color.LIGHT_GRAY);


                                    try {
                                        Socket socket = new Socket("localhost", 4242);
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.
                                                getInputStream()));
                                        PrintWriter writer = new PrintWriter(socket.getOutputStream());

                                        writer.write("sortByQuantity");
                                        writer.println();
                                        writer.flush();
                                        String quantAvailOutput = reader.readLine();
                                        String allQuantAvailOutputs = "";
                                        while (!quantAvailOutput.isEmpty()) {
                                            allQuantAvailOutputs += quantAvailOutput + "\n\n";
                                            quantAvailOutput = reader.readLine();
                                        }

                                        JTextArea textArea = new JTextArea(allQuantAvailOutputs);
                                        JScrollPane scrollPane = new JScrollPane(textArea);
                                        textArea.setLineWrap(true);
                                        textArea.setWrapStyleWord(true);
                                        scrollPane.setPreferredSize(new Dimension(500, 500));

                                        JOptionPane.showMessageDialog(null, scrollPane,
                                                "Search Results:",
                                                JOptionPane.PLAIN_MESSAGE);
                                    } catch (IOException e4) {
                                        System.out.println();
                                    }


                                }
                                if (selectedSearchOption.equals("Back")) {
                                    cardLayout.show(cardPanel, "after customer login");
                                }
                            }
                        });
                    }
                    if (selectedOption.equals("Select Product")) {
                        JPanel selectProdPanel = new JPanel(null);
                        selectProdPanel.setBackground(Color.LIGHT_GRAY);

                        cardPanel.add(selectProdPanel, "select product panel");

                        JLabel searchTermLabel2 = new JLabel("Enter the name of the product you would like to " +
                                "select:");
                        searchTermLabel2.setBounds(10, 20, 160, 25);
                        selectProdPanel.add(searchTermLabel2);

                        JTextField searchTermText2 = new JTextField(20);
                        searchTermText2.setBounds(10, 50, 165, 25);
                        selectProdPanel.add(searchTermText2);

                        JButton searchButton2 = new JButton("Search");
                        searchButton2.setBounds(10, 80, 80, 25);
                        selectProdPanel.add(searchButton2);

                        JButton backToSelectButton = new JButton("Back");
                        backToSelectButton.setBounds(100, 80, 80, 25);
                        selectProdPanel.add(backToSelectButton);

                        backToSelectButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(cardPanel, "after customer login");
                            }

                        });

                        searchButton2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {

                                    Socket socket = new Socket("localhost", 4242);
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket
                                            .getInputStream()));
                                    PrintWriter writer = new PrintWriter(socket.getOutputStream());

                                    String searchTerm2 = searchTermText2.getText();
                                    writer.write("selectProduct");
                                    writer.println();
                                    writer.flush();
                                    writer.write(searchTerm2);
                                    writer.println();
                                    writer.flush();
                                    JPanel selectProdPanel2 = new JPanel(null);
                                    selectProdPanel2.setBackground(Color.LIGHT_GRAY);

                                    cardPanel.add(selectProdPanel2, "select product panel2");

                                    String selectProductOutput = reader.readLine();
                                    JLabel productOutputLabel = new JLabel(selectProductOutput);

                                    productOutputLabel.setBounds(10, 10, 2000, 25);
                                    selectProdPanel2.add(productOutputLabel);
                                    cardLayout.show(cardPanel, "select product panel2");


                                    if (!(selectProductOutput.equals("Product could not be found or is out of stock!")
                                            || selectProductOutput.equals("No products exist in the market currently" +
                                            "."))) {
                                        JLabel selectProdLabel = new JLabel("Would you like to add this product " +
                                                "to your cart?");
                                        selectProdLabel.setBounds(10, 40, 500, 25);
                                        selectProdPanel2.add(selectProdLabel);

                                        String[] selectProductOptions = {
                                                "Yes",
                                                "No"
                                        };

                                        JComboBox<String> selectProductDropdown = new JComboBox<>(selectProductOptions);
                                        selectProductDropdown.setBounds(10, 80, 200, 25);
                                        selectProdPanel2.add(selectProductDropdown);

                                        JButton selectProductButton2 = new JButton("Enter");
                                        selectProductButton2.setBounds(10, 110, 80, 25);
                                        selectProdPanel2.add(selectProductButton2);

                                        JButton backToSelectButton2 = new JButton("Back");
                                        backToSelectButton2.setBounds(100, 110, 80, 25);
                                        selectProdPanel2.add(backToSelectButton2);

                                        backToSelectButton2.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                cardLayout.show(cardPanel, "select product panel");
                                            }

                                        });

                                        selectProductButton2.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                String addToCart = (String) selectProductDropdown.getSelectedItem();
                                                if (addToCart.equals("Yes")) {
                                                    // writing the action
                                                    JPanel selectProdPanel3 = new JPanel(null);
                                                    selectProdPanel3.setBackground(Color.LIGHT_GRAY);

                                                    cardPanel.add(selectProdPanel3, "select product panel");

                                                    JLabel selectProdLabel2;
                                                    JTextField searchTermText3;
                                                    JButton selectProductButton3;
                                                    selectProdLabel2 = new JLabel("What quantity would you like " +
                                                            "to add to cart?");
                                                    selectProdLabel2.setBounds(10, 20, 500, 25);
                                                    selectProdPanel3.add(selectProdLabel2);

                                                    searchTermText3 = new JTextField(20);
                                                    searchTermText3.setBounds(10, 50, 165, 25);
                                                    selectProdPanel3.add(searchTermText3);

                                                    selectProductButton3 = new JButton("Enter");
                                                    selectProductButton3.setBounds(10, 110, 80, 25);
                                                    selectProdPanel3.add(selectProductButton3);

                                                    cardLayout.show(cardPanel, "select product panel");

                                                    JButton backToSelectButton3 = new JButton("Back");
                                                    backToSelectButton3.setBounds(100, 110, 80, 25);
                                                    selectProdPanel3.add(backToSelectButton3);

                                                    backToSelectButton3.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            cardLayout.show(cardPanel, "select product panel2");
                                                        }

                                                    });


                                                    // send inputs to addToCart method in server
                                                    JTextField finalSearchTermText = searchTermText3;
                                                    selectProductButton3.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            try {
                                                                int quantity = Integer.parseInt(finalSearchTermText
                                                                        .getText());
                                                                if (quantity <= 0) {
                                                                    throw new Exception();
                                                                }
                                                                writer.write("addToCart");
                                                                writer.println();
                                                                writer.flush();

                                                                writer.write(searchTerm2 + "," + quantity + ","
                                                                        + email);
                                                                writer.println();
                                                                writer.flush();

                                                                String addToCartOutput = reader.readLine();
                                                                String allAddToCartOutputs = "";
                                                                while (!addToCartOutput.isEmpty()) {
                                                                    allAddToCartOutputs += addToCartOutput + "\n";
                                                                    addToCartOutput = reader.readLine();
                                                                }
                                                                JOptionPane.showMessageDialog(frame,
                                                                        allAddToCartOutputs, "",
                                                                        JOptionPane.PLAIN_MESSAGE);
                                                            } catch (Exception e2) {
                                                                System.out.println();
                                                                JLabel selectProdLabel3 = new JLabel("Error. " +
                                                                        "Invalid Input.");
                                                                selectProdLabel3.setBounds(10, 20,
                                                                        160, 25);
                                                                selectProdPanel3.add(selectProdLabel3);
                                                            }

                                                            cardLayout.show(cardPanel, "after customer login");

                                                        }
                                                    });


//
                                                } else {
                                                    cardLayout.show(cardPanel, "after customer login");
                                                }
                                            }
                                        });

                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                selectProductOutput, "Removal " +
                                                        "Status", JOptionPane.ERROR_MESSAGE);
                                        cardLayout.show(cardPanel, "after customer login");
                                    }


                                } catch (IOException e4) {
                                    System.out.println();
                                }
                            }
                        });

                        cardPanel.add(selectProdPanel, "select product panel");
                        cardLayout.show(cardPanel, "select product panel");

                    }
                    if (selectedOption.equals("Manage Shopping Cart")) {
                        JPanel manageCartOptions = new JPanel(null);
                        manageCartOptions.setBackground(Color.LIGHT_GRAY);

                        String[] cartOptions = {
                                "View your cart",
                                "Remove an item from your cart",
                                "Check out",
                                "Back"
                        };
                        JComboBox<String> cartOptionsDropdown = new JComboBox<>(cartOptions);
                        cartOptionsDropdown.setBounds(10, 10, 200, 25);

                        JButton cartSubmitButton = new JButton("Submit");
                        cartSubmitButton.setBounds(10, 40, 80, 25);

                        manageCartOptions.add(cartOptionsDropdown);
                        manageCartOptions.add(cartSubmitButton);
                        cardPanel.add(manageCartOptions, "manage cart");
                        cardLayout.show(cardPanel, "manage cart");
                        cartSubmitButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Socket socket = new Socket("localhost", 4242);
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket
                                            .getInputStream()));
                                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                    String selectedCartOption = (String) cartOptionsDropdown.getSelectedItem();
                                    switch (selectedCartOption) {
                                        case "View your cart":
                                            writer.write("viewCart");
                                            writer.println();
                                            writer.flush();

                                            writer.write(email);
                                            writer.println();
                                            writer.flush();

                                            String currCartProduct = reader.readLine();
                                            String allCartProducts = "";
                                            while (!currCartProduct.isEmpty()) {
                                                allCartProducts += currCartProduct + "\n";
                                                currCartProduct = reader.readLine();
                                            }
                                            if (allCartProducts.isEmpty()) {
                                                allCartProducts = "Shopping Cart is empty!";
                                            }
                                            JOptionPane.showMessageDialog(null, allCartProducts,
                                                    "All Cart Products",
                                                    JOptionPane.PLAIN_MESSAGE);
                                            break;
                                        case "Remove an item from your cart":
                                            JPanel cartPanel = new JPanel(null);
                                            cartPanel.setBackground(Color.LIGHT_GRAY);

                                            writer.write("viewCart");
                                            writer.println();
                                            writer.flush();

                                            writer.write(email);
                                            writer.println();
                                            writer.flush();

                                            String currCartProduct2 = reader.readLine();
                                            ArrayList<String> allCurrCartProducts2 = new ArrayList<>();
                                            while (!currCartProduct2.isEmpty()) {
                                                allCurrCartProducts2.add(currCartProduct2);
                                                currCartProduct2 = reader.readLine();
                                            }
                                            if (allCurrCartProducts2.isEmpty()) {
                                                JOptionPane.showMessageDialog(frame, "Shopping Cart is empty!",
                                                        "", JOptionPane.PLAIN_MESSAGE);
                                            }

                                            String[] allCartProducts2 = allCurrCartProducts2.toArray(new String[0]);

                                            JComboBox<String> cartProductsDropdown = new JComboBox<>(allCartProducts2);
                                            cartProductsDropdown.setBounds(10, 10, 120, 25);
                                            cartPanel.add(cartProductsDropdown);


                                            // Creating remove button
                                            JButton removeButton = new JButton("Remove");
                                            removeButton.setBounds(10, 100, 120, 25);
                                            cartPanel.add(removeButton);

                                            JButton backToManageCartButton = new JButton("Back");
                                            backToManageCartButton.setBounds(150, 100, 80, 25);
                                            cartPanel.add(backToManageCartButton);


                                            backToManageCartButton.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    cardLayout.show(cardPanel, "manage cart");
                                                }

                                            });


                                            cardPanel.add(cartPanel, "remove product");
                                            cardLayout.show(cardPanel, "remove product");
                                            removeButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        Socket socket = new Socket("localhost", 4242);
                                                        BufferedReader reader = new BufferedReader
                                                                (new InputStreamReader(socket.getInputStream()));
                                                        PrintWriter writer = new PrintWriter(socket.getOutputStream());


                                                        String productToRemove = (String) cartProductsDropdown
                                                                .getSelectedItem();
                                                        productToRemove = productToRemove.substring(15, productToRemove
                                                                .indexOf("]"));
                                                        System.out.println(productToRemove);
                                                        writer.write("removeFromCart");
                                                        writer.println();
                                                        writer.flush();

                                                        String removalString = email + "," + productToRemove;

                                                        writer.write(removalString);
                                                        writer.println();
                                                        writer.flush();

                                                        String removalStatus = reader.readLine();
                                                        if (removalStatus.equals("Successfully removed product.")) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    removalStatus, "Removal " +
                                                                            "Status", JOptionPane.INFORMATION_MESSAGE);
                                                        } else {
                                                            JOptionPane.showMessageDialog(null,
                                                                    removalStatus, "Removal " +
                                                                            "Status", JOptionPane.ERROR_MESSAGE);
                                                        }

                                                        cardLayout.show(cardPanel, "manage cart");

                                                    } catch (Exception e1) {
                                                        System.out.println();
                                                    }
                                                }
                                            });
                                            break;
                                        case "Check out":

                                            try {
                                                Socket socket1 = new Socket("localhost", 4242);
                                                BufferedReader reader1 =
                                                        new BufferedReader(new
                                                                InputStreamReader(socket.getInputStream()));
                                                PrintWriter writer1 = new PrintWriter(socket.getOutputStream());

                                                writer1.write("checkout");
                                                writer1.println();
                                                writer1.flush();

                                                writer1.write(email);
                                                writer1.println();
                                                writer1.flush();

                                                String checkoutMessage = reader1.readLine();
                                                JOptionPane.showMessageDialog(null, checkoutMessage,
                                                        "Checkout " +
                                                                "Status", JOptionPane.INFORMATION_MESSAGE);
                                            } catch (Exception e1) {
                                                System.out.println();
                                            }

                                            cardLayout.show(cardPanel, "manage cart");
                                            break;
                                        case "Back": //todo
                                            cardLayout.show(cardPanel, "after customer login");
                                            break;
                                    }
                                } catch (Exception e1) {
                                    System.out.println();
                                }
                            }
                        });

                    }
                    if (selectedOption.equals("View Purchase History")) {
                        writer.write("viewPurchaseHistory");
                        writer.println();
                        writer.flush();

                        writer.write(email);
                        writer.println();
                        writer.flush();

                        String purchaseHistory = "Purchase History:\n";
                        String viewPurchasesOutput = reader.readLine();
                        while (!viewPurchasesOutput.isEmpty()) {
                            purchaseHistory += viewPurchasesOutput + "\n";
                            viewPurchasesOutput = reader.readLine();
                        }
                        JOptionPane.showMessageDialog(null, purchaseHistory, "Purchase History:",
                                JOptionPane.PLAIN_MESSAGE);


                    }
                    if (selectedOption.equals("Export Purchase History")) {
                        writer.write("exportPurchaseHistory");
                        writer.println();
                        writer.flush();

                        writer.write(email);
                        writer.println();
                        writer.flush();

                        String exportPurchaseHistoryOutput = reader.readLine();
                        JOptionPane.showMessageDialog(null, exportPurchaseHistoryOutput,
                                "Export Status",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                    if (selectedOption.equals("Statistics")) { //todo
                        JPanel statOptions = new JPanel(null);
                        statOptions.setBackground(Color.LIGHT_GRAY);

                        String[] statOptionString = {
                                "View all stores with number of products sold",
                                "View stores purchased from"
                        };
                        JComboBox<String> statOptionsDropdown = new JComboBox<>(statOptionString);
                        statOptionsDropdown.setBounds(10, 10, 300, 25);

                        JButton statSubmitButton = new JButton("Submit");
                        statSubmitButton.setBounds(10, 40, 80, 25);

                        JButton backToStatsButton = new JButton("Back");
                        backToStatsButton.setBounds(100, 40, 80, 25);
                        statOptions.add(backToStatsButton);

                        backToStatsButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(cardPanel, "after customer login");
                            }

                        });

                        statOptions.add(statOptionsDropdown);
                        statOptions.add(statSubmitButton);
                        cardPanel.add(statOptions, "stat options");
                        cardLayout.show(cardPanel, "stat options");
                        statSubmitButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Socket socket2 = new Socket("localhost", 4242);
                                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket
                                            .getInputStream()));
                                    PrintWriter writer2 = new PrintWriter(socket.getOutputStream());
                                    String selectedStatOption = (String) statOptionsDropdown.getSelectedItem();
                                    switch (selectedStatOption) {
                                        case "View all stores with number of products sold":
                                            JPanel stat1Options = new JPanel(null);
                                            stat1Options.setBackground(Color.LIGHT_GRAY);

                                            String[] stat1OptionString = {
                                                    "Alphabetical (by store name)",
                                                    "Ascending (by products sold per store)",
                                                    "Descending (by products sold per store)"
                                            };
                                            JComboBox<String> stat1OptionsDropdown = new JComboBox<>(stat1OptionString);
                                            stat1OptionsDropdown.setBounds(10, 10, 300, 25);

                                            JButton stat1SubmitButton = new JButton("Submit");
                                            stat1SubmitButton.setBounds(10, 40, 80, 25);

                                            JButton backToStatsButton2 = new JButton("Back");
                                            backToStatsButton2.setBounds(100, 40, 80, 25);
                                            stat1Options.add(backToStatsButton2);

                                            backToStatsButton2.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    cardLayout.show(cardPanel, "stat options");
                                                }

                                            });

                                            stat1Options.add(stat1OptionsDropdown);
                                            stat1Options.add(stat1SubmitButton);
                                            cardPanel.add(stat1Options, "sort1 options");
                                            cardLayout.show(cardPanel, "sort1 options");
                                            stat1SubmitButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        String stat1SortType = (String) stat1OptionsDropdown
                                                                .getSelectedItem();
                                                        switch (stat1SortType) {
                                                            case "Alphabetical (by store name)":

                                                                writer.write("statistics");
                                                                writer.println();
                                                                writer.flush();

                                                                String statCall11 = email + ",1,1";

                                                                writer.write(statCall11);
                                                                writer.println();
                                                                writer.flush();

                                                                String stat11 = reader2.readLine();
                                                                String allStat11 = "";
                                                                while (!stat11.isEmpty()) {
                                                                    allStat11 += stat11 + "\n\n";
                                                                    stat11 = reader2.readLine();
                                                                }
                                                                JTextArea textArea11 =
                                                                        new JTextArea(allStat11);
                                                                JScrollPane scrollPane11 = new JScrollPane(textArea11);
                                                                textArea11.setLineWrap(true);
                                                                textArea11.setWrapStyleWord(true);
                                                                scrollPane11.setPreferredSize(new Dimension(500,
                                                                        500));
                                                                JOptionPane.showMessageDialog(null,
                                                                        scrollPane11,
                                                                        "All stores with number of products sold",
                                                                        JOptionPane.PLAIN_MESSAGE);


                                                                break;
                                                            case "Ascending (by products sold per store)":
                                                                writer.write("statistics");
                                                                writer.println();
                                                                writer.flush();

                                                                String statCall12 = email + ",1,2";

                                                                writer.write(statCall12);
                                                                writer.println();
                                                                writer.flush();

                                                                String stat12 = reader2.readLine();
                                                                String allStat12 = "";
                                                                while (!stat12.isEmpty()) {
                                                                    allStat12 += stat12 + "\n\n";
                                                                    stat12 = reader2.readLine();
                                                                }

                                                                JTextArea textArea12 =
                                                                        new JTextArea(allStat12);
                                                                JScrollPane scrollPane12 = new JScrollPane(textArea12);
                                                                textArea12.setLineWrap(true);
                                                                textArea12.setWrapStyleWord(true);
                                                                scrollPane12.setPreferredSize(new Dimension(500,
                                                                        500));

                                                                JOptionPane.showMessageDialog(null,
                                                                        scrollPane12,
                                                                        "All stores with number of products sold",
                                                                        JOptionPane.PLAIN_MESSAGE);
                                                                break;
                                                            case "Descending (by products sold per store)":
                                                                writer.write("statistics");
                                                                writer.println();
                                                                writer.flush();

                                                                String statCall13 = email + ",1,3";

                                                                writer.write(statCall13);
                                                                writer.println();
                                                                writer.flush();

                                                                String stat13 = reader2.readLine();
                                                                String allStat13 = "";
                                                                while (!stat13.isEmpty()) {
                                                                    allStat13 += stat13 + "\n\n";
                                                                    stat13 = reader2.readLine();
                                                                }

                                                                JTextArea textArea13 =
                                                                        new JTextArea(allStat13);
                                                                JScrollPane scrollPane13 = new JScrollPane(textArea13);
                                                                textArea13.setLineWrap(true);
                                                                textArea13.setWrapStyleWord(true);
                                                                scrollPane13.setPreferredSize(new Dimension(500,
                                                                        500));

                                                                JOptionPane.showMessageDialog(null,
                                                                        scrollPane13,
                                                                        "All stores with number of products sold",
                                                                        JOptionPane.PLAIN_MESSAGE);
                                                                break;
                                                        }
                                                    } catch (Exception e1) {
                                                        System.out.println();
                                                    }
                                                }
                                            });

                                            break;
                                        case "View stores purchased from":
                                            JPanel stat2Options = new JPanel(null);
                                            stat2Options.setBackground(Color.LIGHT_GRAY);

                                            String[] stat2OptionString = {
                                                    "Alphabetical (by store name)",
                                                    "Ascending (by products sold per store)",
                                                    "Descending (by products sold per store)"
                                            };
                                            JComboBox<String> stat2OptionsDropdown = new JComboBox<>(stat2OptionString);
                                            stat2OptionsDropdown.setBounds(10, 10, 200, 25);

                                            JButton stat2SubmitButton = new JButton("Submit");
                                            stat2SubmitButton.setBounds(10, 40, 80, 25);

                                            JButton backToStatsButton3 = new JButton("Back");
                                            backToStatsButton3.setBounds(100, 40, 80, 25);
                                            stat2Options.add(backToStatsButton3);

                                            backToStatsButton3.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    cardLayout.show(cardPanel, "after customer login");
                                                }

                                            });

                                            stat2Options.add(stat2OptionsDropdown);
                                            stat2Options.add(stat2SubmitButton);
                                            cardPanel.add(stat2Options, "sort2 options");
                                            cardLayout.show(cardPanel, "sort2 options");
                                            stat2SubmitButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        String stat2SortType = (String) stat2OptionsDropdown
                                                                .getSelectedItem();
                                                        switch (stat2SortType) {
                                                            case "Alphabetical (by store name)":

                                                                writer.write("statistics");
                                                                writer.println();
                                                                writer.flush();

                                                                String statCall21 = email + ",2,1";

                                                                writer.write(statCall21);
                                                                writer.println();
                                                                writer.flush();

                                                                String stat21 = reader2.readLine();
                                                                String allStat21 = "";
                                                                while (!stat21.isEmpty()) {
                                                                    allStat21 += stat21 + "\n\n";
                                                                    stat21 = reader2.readLine();
                                                                }

                                                                JTextArea textArea21 =
                                                                        new JTextArea(allStat21);
                                                                JScrollPane scrollPane21 = new JScrollPane(textArea21);
                                                                textArea21.setLineWrap(true);
                                                                textArea21.setWrapStyleWord(true);
                                                                scrollPane21.setPreferredSize(new Dimension(500,
                                                                        500));

                                                                JOptionPane.showMessageDialog(null,
                                                                        scrollPane21,
                                                                        "Stores Purchased From",
                                                                        JOptionPane.PLAIN_MESSAGE);
                                                                break;
                                                            case "Ascending (by products sold per store)":
                                                                writer.write("statistics");
                                                                writer.println();
                                                                writer.flush();

                                                                String statCall22 = email + ",2,2";

                                                                writer.write(statCall22);
                                                                writer.println();
                                                                writer.flush();

                                                                String stat22 = reader2.readLine();
                                                                String allStat22 = "";
                                                                while (!stat22.isEmpty()) {
                                                                    allStat22 += stat22 + "\n\n";
                                                                    stat22 = reader2.readLine();
                                                                }

                                                                JTextArea textArea22 =
                                                                        new JTextArea(allStat22);
                                                                JScrollPane scrollPane22 = new JScrollPane(textArea22);
                                                                textArea22.setLineWrap(true);
                                                                textArea22.setWrapStyleWord(true);
                                                                scrollPane22.setPreferredSize(new Dimension(500,
                                                                        500));

                                                                JOptionPane.showMessageDialog(null,
                                                                        scrollPane22,
                                                                        "Stores Purchased From",
                                                                        JOptionPane.PLAIN_MESSAGE);
                                                                break;
                                                            case "Descending (by products sold per store)":
                                                                writer.write("statistics");
                                                                writer.println();
                                                                writer.flush();

                                                                String statCall23 = email + ",2,3";

                                                                writer.write(statCall23);
                                                                writer.println();
                                                                writer.flush();

                                                                String stat23 = reader2.readLine();
                                                                String allStat23 = "";
                                                                while (!stat23.isEmpty()) {
                                                                    allStat23 += stat23 + "\n\n";
                                                                    stat23 = reader2.readLine();
                                                                }

                                                                JTextArea textArea23 =
                                                                        new JTextArea(allStat23);
                                                                JScrollPane scrollPane23 = new JScrollPane(textArea23);
                                                                textArea23.setLineWrap(true);
                                                                textArea23.setWrapStyleWord(true);
                                                                scrollPane23.setPreferredSize(new Dimension(500,
                                                                        500));

                                                                JOptionPane.showMessageDialog(null,
                                                                        scrollPane23,
                                                                        "Stores Purchased From",
                                                                        JOptionPane.PLAIN_MESSAGE);
                                                                break;
                                                        }
                                                    } catch (Exception e1) {
                                                        System.out.println();
                                                    }
                                                }
                                            });

                                            break;
                                        // todo add back button
                                    }

                                } catch (Exception e2) {
                                    System.out.println();
                                }
                            }
                        });
                    }
                    if (selectedOption.equals("Delete Account")) {
                        int choice = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete your " +
                                        "account?",
                                "Delete" +
                                        " " +
                                        "account", JOptionPane.YES_NO_OPTION);
                        if (choice == 0) {
                            writer.write("customerDeleteAccount");
                            writer.println();
                            writer.flush();

                            writer.write(email);
                            writer.println();
                            writer.flush();

                            String deletionStatus = reader.readLine();
                            JOptionPane.showMessageDialog(null, deletionStatus, "Deletion Status",
                                    JOptionPane.PLAIN_MESSAGE);

                            cardLayout.show(cardPanel, "Welcome");
                        }


                    }
                    if (selectedOption.equals("Log Out")) { //todo take the user back to the customer/seller screen
                        email = "";
                        JOptionPane.showMessageDialog(null, "Thank you for shopping with us!",
                                "Logged Out " +
                                        "Successfully", JOptionPane.PLAIN_MESSAGE);
                        cardLayout.show(cardPanel, "Welcome");
                    }
                } catch (IOException e2) {
                    System.out.println();
                }
            }
        });

        customerOptions.add(optionsDropdown);
        customerOptions.add(submitButton);

        cardPanel.add(customerOptions, "after customer login");


    }

    private static void createSellerPanel() {
        try {
            Socket socket = new Socket("localhost", 4242);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //Login panel
            JPanel sellerLoginPanel = new JPanel(new GridLayout(3, 1));
            sellerLoginPanel.setBackground(Color.LIGHT_GRAY);
            JButton sellerLoginButton = new JButton("Login");
            JButton sellerCreateAccButton = new JButton("Create an Account");

            sellerLoginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
            sellerCreateAccButton.setFont(new Font("Times New Roman", Font.BOLD, 18));

            JLabel sLoginLabel = new JLabel("Please select whether you would like to Login or Create an Account!");
            sLoginLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
            sLoginLabel.setHorizontalAlignment(JLabel.CENTER);

            sellerLoginPanel.add(sLoginLabel);
            sellerLoginPanel.add(sellerLoginButton);
            sellerLoginPanel.add(sellerCreateAccButton);

            sellerLoginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    writer.write(2);
                    writer.println();
                    writer.flush();

                    cardLayout.show(cardPanel, "seller login credentials");
                }
            });

            sellerCreateAccButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writer.write(5);
                    writer.println();
                    writer.flush();

                    cardLayout.show(cardPanel, "seller create account credentials");

                }
            });

            cardPanel.add(sellerLoginPanel, "seller login");

            //LoginCredentials Panel
            JPanel sLoginCredPanel = new JPanel(null);
            sLoginCredPanel.setBackground(Color.LIGHT_GRAY);


            JLabel sUserLabel = new JLabel("Name:");
            sUserLabel.setBounds(10, 20, 80, 25);
            sLoginCredPanel.add(sUserLabel);

            JTextField userText = new JTextField(20);
            userText.setBounds(100, 20, 165, 25);
            sLoginCredPanel.add(userText);

            JLabel sEmailLabel = new JLabel("Email:");
            sEmailLabel.setBounds(10, 50, 80, 25);
            sLoginCredPanel.add(sEmailLabel);

            JTextField emailText = new JTextField(20);
            emailText.setBounds(100, 50, 165, 25);
            sLoginCredPanel.add(emailText);

            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(10, 80, 80, 25);
            sLoginCredPanel.add(passwordLabel);

            JPasswordField passwordText = new JPasswordField(20);
            passwordText.setBounds(100, 80, 165, 25);
            sLoginCredPanel.add(passwordText);

            JButton backToLoginButton = new JButton("Back");
            backToLoginButton.setBounds(100, 110, 80, 25);
            sLoginCredPanel.add(backToLoginButton);

            backToLoginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "Welcome");
                }

            });

            //createAccountPanel

            JPanel sCreateAccountPanel = new JPanel(null);

            JLabel sCreateUserLabel = new JLabel("Name:");
            sCreateUserLabel.setBounds(10, 20, 80, 25);
            sCreateAccountPanel.add(sCreateUserLabel);

            JTextField createUserText = new JTextField(20);
            createUserText.setBounds(100, 20, 165, 25);
            sCreateAccountPanel.add(createUserText);

            JLabel sCreateEmailLabel = new JLabel("Email:");
            sCreateEmailLabel.setBounds(10, 50, 80, 25);
            sCreateAccountPanel.add(sCreateEmailLabel);

            JTextField createEmailText = new JTextField(20);
            createEmailText.setBounds(100, 50, 165, 25);
            sCreateAccountPanel.add(createEmailText);

            JLabel createPasswordLabel = new JLabel("Password");
            createPasswordLabel.setBounds(10, 80, 80, 25);
            sCreateAccountPanel.add(createPasswordLabel);

            JPasswordField createPasswordText = new JPasswordField(20);
            createPasswordText.setBounds(100, 80, 165, 25);
            sCreateAccountPanel.add(createPasswordText);

            // Creating create account button
            JButton createAccountButton = new JButton("Create Account");
            createAccountButton.setBounds(10, 110, 150, 25);
            sCreateAccountPanel.add(createAccountButton);

            JButton backToLoginButton2 = new JButton("Back");
            backToLoginButton2.setBounds(175, 110, 80, 25);
            sCreateAccountPanel.add(backToLoginButton2);

            backToLoginButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "Welcome");
                }

            });

            createAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        String createName = createUserText.getText();
                        String createEmail = createEmailText.getText();
                        String createPassword = createPasswordText.getText();

                        if (createName.isEmpty() || createEmail.isEmpty() || createPassword.isEmpty()) {
                            throw new Exception();
                        }
                        // Your PrintWriter operations
                        writer.write("sellerCreateAccount");
                        writer.println();
                        writer.flush();

                        writer.write(createName + "," + createEmail + "," + createPassword);
                        writer.println();
                        writer.flush();

                        String createAccStatus = reader.readLine();
                        boolean accFound = false;
                        String allCreateAccStatus = "";
                        while (!createAccStatus.isEmpty()) {
                            if (createAccStatus.equals("Created account and logged in successfully!")) {
                                accFound = true;
                            }
                            allCreateAccStatus = createAccStatus + "\n";
                            createAccStatus = reader.readLine();
                        }

                        JOptionPane.showMessageDialog(frame, allCreateAccStatus, "Account Status",
                                JOptionPane.PLAIN_MESSAGE);

                        if (accFound) {
                            name = createName;
                            email = createEmail;
                            password = createPassword;
                            cardLayout.show(cardPanel, "after seller login");
                            createUserText.setText("");
                            createEmailText.setText("");
                            createPasswordText.setText("");
                        }

                        createUserText.setText("");
                        createEmailText.setText("");
                        createPasswordText.setText("");

                    } catch (IOException e2) {
                        System.out.println();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all input.", "ErrorInput",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            });
            cardPanel.add(sCreateAccountPanel, "seller create account credentials");

            // Creating login button
            JButton loginButton = new JButton("login");
            loginButton.setBounds(10, 110, 80, 25);
            sLoginCredPanel.add(loginButton);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        name = userText.getText();
                        email = emailText.getText();
                        password = passwordText.getText();

                        JTextArea alrLogged = new JTextArea("Already logged in");
                        alrLogged.setEditable(false);
                        alrLogged.setBounds(10, 140, 80, 25);

                        JTextArea errorInput = new JTextArea("Invalid credentials");
                        errorInput.setEditable(false);
                        errorInput.setBounds(10, 140, 80, 25);

                        // Your PrintWriter operations
                        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in all input.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Your PrintWriter operations
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
                                JOptionPane.showMessageDialog(null, "Invalid Credentials",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                cardLayout.show(cardPanel, "after seller login");
                                userText.setText("");
                                emailText.setText("");
                                passwordText.setText("");
                                createUserText.setText("");
                                createEmailText.setText("");
                                createPasswordText.setText("");
                            }
                        }

                    } catch (IOException e1) {
                        System.out.println();
                    }
                }
            });

            cardPanel.add(sLoginCredPanel, "seller login credentials");
        } catch (Exception e) {
            System.out.println();
        }
        // after Seller Login

        JPanel sellerOptions = new JPanel(null);
        sellerOptions.setBackground(Color.LIGHT_GRAY);


        String[] options = {
                "Manage Stores",
                "Statistics",
                "Delete Your Account",
                "Log Out"
        };

        JComboBox<String> optionsDropdown = new JComboBox<>(options);
        optionsDropdown.setBounds(10, 10, 200, 25);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 40, 80, 25);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 4242);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String selectedOption = (String) optionsDropdown.getSelectedItem();

                    if (selectedOption.equals("Manage Stores")) {
                        JPanel manageStoreOptions = new JPanel(null);
                        manageStoreOptions.setBackground(Color.LIGHT_GRAY);

                        String[] storeOptions = {
                                "Display Stores",
                                "Remove Store",
                                "Select Store",
                                "Add Store",
                                "Export Store Products"
                        };
                        JComboBox<String> storeOptionsDropdown = new JComboBox<>(storeOptions);
                        storeOptionsDropdown.setBounds(10, 10, 200, 25);

                        JButton storeOptionSubmitButton = new JButton("Submit");
                        storeOptionSubmitButton.setBounds(10, 40, 80, 25);

                        JButton back = new JButton("Back");
                        back.setBounds(100, 40, 80, 25);
                        manageStoreOptions.add(back);

                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(cardPanel, "after seller login");
                            }
                        });

                        manageStoreOptions.add(storeOptionsDropdown);
                        manageStoreOptions.add(storeOptionSubmitButton);
                        cardPanel.add(manageStoreOptions, "manage stores");
                        cardLayout.show(cardPanel, "manage stores");

                        storeOptionSubmitButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Socket socket = new Socket("localhost", 4242);
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket
                                            .getInputStream()));
                                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                    String selectedStoreOption = (String) storeOptionsDropdown.getSelectedItem();


                                    if (selectedStoreOption.equals("Display Stores")) {
                                        writer.write("sellerDisplayStores");
                                        writer.println();
                                        writer.flush();
                                        writer.write(email);
                                        writer.println();
                                        writer.flush();
                                        String responseFromReader = reader.readLine();
                                        String displayStores = "";
                                        while (!responseFromReader.equals("Stop")) {
                                            if (responseFromReader.contains("Store: ")) {
                                                displayStores += "\n" + responseFromReader + "\n";
                                            } else {
                                                displayStores += responseFromReader + "\n";
                                            }
                                            responseFromReader = reader.readLine();
                                        }
                                        JOptionPane.showMessageDialog(null, displayStores,
                                                "Displaying All " +
                                                "Stores", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    if (selectedStoreOption.equals("Remove Store")) {
                                        JLabel removeLabel = new JLabel("Remove Store");
                                        removeLabel.setBounds(10, 10, 140, 25);
                                        JPanel cartPanel = new JPanel(null);
                                        cartPanel.setBackground(Color.LIGHT_GRAY);

                                        cartPanel.add(removeLabel);
                                        writer.write("sellerDisplayStores");
                                        writer.println();
                                        writer.flush();
                                        writer.write(email);
                                        writer.println();
                                        writer.flush();
                                        String response = reader.readLine();
                                        ArrayList<String> displayStoresArrayList = new ArrayList<>();
                                        while (!response.equals("Stop")) {
                                            if (response.contains("Store: ")) {
                                                displayStoresArrayList.add(response.substring(7));
                                            }
                                            response = reader.readLine();
                                        }

                                        JPanel storeSelectOptions = new JPanel(null);
                                        storeSelectOptions.setBackground(Color.LIGHT_GRAY);

                                        String stores[] =
                                                displayStoresArrayList.toArray(new String[displayStoresArrayList
                                                        .size()]);
                                        JComboBox<String> storesDropdown = new JComboBox<>(stores);
                                        storesDropdown.setBounds(120, 10, 200, 25);
                                        cartPanel.add(storesDropdown);


                                        JButton removeButton = new JButton("Remove");
                                        removeButton.setBounds(10, 40, 100, 25);
                                        cartPanel.add(removeButton);

                                        JButton backButton = new JButton("Back");
                                        backButton.setBounds(120, 40, 100, 25);
                                        cartPanel.add(backButton);

                                        backButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                cardLayout.show(cardPanel, "manage stores");
                                            }
                                        });

                                        cardPanel.add(cartPanel, "remove store");
                                        cardLayout.show(cardPanel, "remove store");
                                        removeButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    Socket socket = new Socket("localhost", 4242);
                                                    BufferedReader reader = new BufferedReader(new InputStreamReader
                                                            (socket.getInputStream()));
                                                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                                    String storeToRemove = (String) storesDropdown.getSelectedItem();
                                                    writer.write("removeStore");
                                                    writer.println();
                                                    writer.flush();

                                                    String removalString = email + "," + storeToRemove;

                                                    writer.write(removalString);
                                                    writer.println();
                                                    writer.flush();

                                                    String removalStatus = reader.readLine();
                                                    if (removalStatus.equals("Store removed successfully!")) {
                                                        JOptionPane.showMessageDialog(null,
                                                                removalStatus, "Removal " +
                                                                        "Status", JOptionPane.INFORMATION_MESSAGE);
                                                        cardLayout.show(cardPanel, "manage stores");
                                                    } else {
                                                        JOptionPane.showMessageDialog(null,
                                                                removalStatus, "Removal " +
                                                                        "Status", JOptionPane.ERROR_MESSAGE);
                                                    }

                                                } catch (Exception e1) {
                                                    System.out.println();
                                                }
                                            }
                                        });
                                    }
                                    if (selectedStoreOption.equals("Select Store")) {
                                        writer.write("sellerDisplayStores");
                                        writer.println();
                                        writer.flush();
                                        writer.write(email);
                                        writer.println();
                                        writer.flush();
                                        String response = reader.readLine();
                                        ArrayList<String> displayStoresArrayList = new ArrayList<>();
                                        while (!response.equals("Stop")) {
                                            if (response.contains("Store: ")) {
                                                displayStoresArrayList.add(response.substring(7));
                                            }
                                            response = reader.readLine();
                                        }

                                        JPanel storeSelectOptions = new JPanel(null);
                                        storeSelectOptions.setBackground(Color.LIGHT_GRAY);

                                        String stores[] =
                                                displayStoresArrayList.toArray(new String[displayStoresArrayList
                                                        .size()]);
                                        JComboBox<String> storesDropdown = new JComboBox<>(stores);
                                        storesDropdown.setBounds(10, 10, 200, 25);

                                        JButton storeSubmitButton = new JButton("Select");
                                        storeSubmitButton.setBounds(10, 40, 80, 25);


                                        JButton storesBack = new JButton("Back");
                                        storesBack.setBounds(100, 40, 80, 25);
                                        storeSelectOptions.add(storesBack);

                                        storeSelectOptions.add(storesDropdown);
                                        storeSelectOptions.add(storeSubmitButton);
                                        cardPanel.add(storeSelectOptions, "select options");
                                        cardLayout.show(cardPanel, "select options");


                                        storesBack.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                cardLayout.show(cardPanel, "manage stores");
                                            }
                                        });
                                        storeSubmitButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    Socket socket2 = new Socket("localhost", 4242);
                                                    BufferedReader reader2 = new BufferedReader(new InputStreamReader
                                                            (socket.getInputStream()));
                                                    PrintWriter writer2 = new PrintWriter(socket.getOutputStream());
                                                    String selectedStoreName =
                                                            (String) storesDropdown.getSelectedItem();
                                                    selectedStoreName.trim();

                                                    JPanel productsInStore = new JPanel(null);
                                                    productsInStore.setBackground(Color.LIGHT_GRAY);

                                                    JButton storeProductButton = new JButton("Create Product");
                                                    storeProductButton.setBounds(10, 80, 120, 25);
                                                    productsInStore.add(storeProductButton);

                                                    JButton storeProductButton2 = new JButton("Remove Product");
                                                    storeProductButton2.setBounds(10, 120, 140, 25);
                                                    productsInStore.add(storeProductButton2);

                                                    storeProductButton.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            JPanel createProdPanel = new JPanel(null);
                                                            createProdPanel.setBackground(Color.LIGHT_GRAY);

                                                            JLabel prodName = new JLabel("Enter Product Name: ");
                                                            prodName.setBounds(10, 10, 200, 25);
                                                            createProdPanel.add(prodName);

                                                            JTextField prodNameText = new JTextField(20);
                                                            prodNameText.setBounds(200, 10, 120, 25);
                                                            createProdPanel.add(prodNameText);

                                                            JLabel prodDescription = new JLabel("Enter " +
                                                                    "Description: ");
                                                            prodDescription.setBounds(10, 50, 200,
                                                                    25);
                                                            createProdPanel.add(prodDescription);

                                                            JTextField prodDescriptionText = new JTextField(20);
                                                            prodDescriptionText.setBounds(200, 50, 120,
                                                                    25);
                                                            createProdPanel.add(prodDescriptionText);

                                                            JLabel prodQuantity = new JLabel("Enter " +
                                                                    "Quantity (Integer)): ");
                                                            prodQuantity.setBounds(10, 90, 200, 25);
                                                            createProdPanel.add(prodQuantity);

                                                            JTextField prodQuantText = new JTextField(20);
                                                            prodQuantText.setBounds(200, 90, 120,
                                                                    25);
                                                            createProdPanel.add(prodQuantText);

                                                            cardPanel.add(createProdPanel, "create product");
                                                            cardLayout.show(cardPanel, "create product");

                                                            JLabel prodPrice = new JLabel("Enter Price " +
                                                                    "(Decimal): ");
                                                            prodPrice.setBounds(10, 130, 200, 25);
                                                            createProdPanel.add(prodPrice);

                                                            JTextField prodPriceText = new JTextField(20);
                                                            prodPriceText.setBounds(200, 130, 120,
                                                                    25);
                                                            createProdPanel.add(prodPriceText);


                                                            JButton enterButtonForCreateProd = new JButton
                                                                    ("Enter");
                                                            enterButtonForCreateProd.setBounds(10, 170, 120,
                                                                    25);
                                                            createProdPanel.add(enterButtonForCreateProd);

                                                            JButton backToSelectStore = new JButton("Back");
                                                            backToSelectStore.setBounds(150, 170, 80,
                                                                    25);
                                                            createProdPanel.add(backToSelectStore);

                                                            backToSelectStore.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    cardLayout.show(cardPanel, "select options");
                                                                }
                                                            });


                                                            enterButtonForCreateProd.addActionListener
                                                                    (new ActionListener() {
                                                                public void actionPerformed(ActionEvent e) {
                                                                    try {
                                                                        int integerQuantity = Integer.parseInt
                                                                                (prodQuantText.getText());
                                                                        double doublePrice =
                                                                                Double.parseDouble(prodPriceText
                                                                                        .getText());
                                                                        if (integerQuantity <= 0 || doublePrice <= 0) {
                                                                            throw new Exception();
                                                                        }

                                                                        writer.write("createProduct");
                                                                        writer.println();
                                                                        writer.flush();

                                                                        String selectedStoreName =
                                                                                (String) storesDropdown
                                                                                        .getSelectedItem();
                                                                        selectedStoreName.trim();

                                                                        writer.write(prodNameText.getText() + ","
                                                                                + selectedStoreName + ","
                                                                                + prodDescriptionText.getText() + ","
                                                                                + prodQuantText.getText() + "," +
                                                                                prodPriceText.getText());
                                                                        writer.println();
                                                                        writer.flush();

                                                                        String readerResponseAddProduct = reader
                                                                                .readLine();
                                                                        if (!readerResponseAddProduct.equals("Error")) {
                                                                            JOptionPane.showMessageDialog
                                                                                    (null,
                                                                                            "Product" +
                                                                                                    " Added " +
                                                                                                    "Successfully!",
                                                                                            "",
                                                                                            JOptionPane.PLAIN_MESSAGE);
                                                                            cardLayout.show(cardPanel, "manage " +
                                                                                    "stores");
                                                                        } else {
                                                                            JOptionPane.showMessageDialog
                                                                                    (null,
                                                                                            "There was " +
                                                                                                    "an error" +
                                                                                                    " adding your" +
                                                                                                    " product", "",
                                                                                            JOptionPane.PLAIN_MESSAGE);
                                                                        }
                                                                    } catch (IOException io) {
                                                                        JOptionPane.showMessageDialog
                                                                                (null, "Error "
                                                                                                +
                                                                                        "reading files",
                                                                                "Error", JOptionPane
                                                                                                .ERROR_MESSAGE);
                                                                    } catch (Exception e5) {
                                                                        JOptionPane.showMessageDialog
                                                                                (null,
                                                                                        "Please " +
                                                                                "enter valid input", "Error " +
                                                                                "Input", JOptionPane.ERROR_MESSAGE);
                                                                    }

                                                                    cardLayout.show(cardPanel, "manage stores");

                                                                }
                                                            });
                                                        }
                                                    });


                                                    storeProductButton2.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            JPanel removeProdPanel = new JPanel(null);
                                                            removeProdPanel.setBackground(Color.LIGHT_GRAY);

                                                            JButton enterButtonForRemoveProd = new JButton
                                                                    ("Remove");
                                                            enterButtonForRemoveProd.setBounds(10, 70, 120,
                                                                    25);
                                                            removeProdPanel.add(enterButtonForRemoveProd);
                                                            JLabel prodName2 = new JLabel("Enter the name of the" +
                                                                    " product you would like to remove: ");
                                                            prodName2.setBounds(10, 10, 2000, 25);
                                                            removeProdPanel.add(prodName2);

                                                            JButton backToSelectStore2 = new JButton("Back");
                                                            backToSelectStore2.setBounds(150, 70, 80,
                                                                    25);
                                                            removeProdPanel.add(backToSelectStore2);

                                                            backToSelectStore2.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    cardLayout.show(cardPanel, "select options");
                                                                }
                                                            });

                                                            JTextField prodNameText2 = new JTextField(20);
                                                            prodNameText2.setBounds(10, 40, 200, 25);
                                                            removeProdPanel.add(prodNameText2);
                                                            cardPanel.add(removeProdPanel, "remove product");
                                                            cardLayout.show(cardPanel, "remove product");


                                                            enterButtonForRemoveProd.addActionListener
                                                                    (new ActionListener() {
                                                                public void actionPerformed(ActionEvent e) {
                                                                    try {


                                                                        writer.write("removeProduct");
                                                                        writer.println();
                                                                        writer.flush();

                                                                        String selectedStoreName2 =
                                                                                (String) storesDropdown
                                                                                        .getSelectedItem();
                                                                        selectedStoreName2.trim();


                                                                        writer.write(selectedStoreName2 + ","
                                                                                + prodNameText2.getText());
                                                                        writer.println();
                                                                        writer.flush();

                                                                        String checkIfSuccessful = reader.readLine();
                                                                        if (checkIfSuccessful.equals("Error")) {
                                                                            JOptionPane.showMessageDialog
                                                                                    (null,
                                                                                            "Error in " +
                                                                                                    "removing" +
                                                                                                    " product", "",
                                                                                            JOptionPane.PLAIN_MESSAGE);
                                                                            cardLayout.show(cardPanel, "manage" +
                                                                                    " stores");
                                                                        } else {
                                                                            JOptionPane.showMessageDialog
                                                                                    (null,
                                                                                            "Product " +
                                                                                                    "successfully" +
                                                                                                    " removed", "",
                                                                                            JOptionPane.PLAIN_MESSAGE);
                                                                            cardLayout.show(cardPanel, "manage " +
                                                                                    "stores");

                                                                        }


                                                                    } catch (Exception e1) {
                                                                        System.out.println("");
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });


                                                    // get all products of the selected store to put in an array
                                                    writer.write("searchByStoreName");
                                                    writer.println();
                                                    writer.flush();

                                                    writer.write(selectedStoreName);
                                                    writer.println();
                                                    writer.flush();

                                                    String outputFromSearch = reader2.readLine();
//                                                    if (outputFromSearch.equals("No products exist in this store.")) {
//                                                        JOptionPane.showMessageDialog(null, outputFromSearch,
//                                                                "Alert", JOptionPane.ERROR_MESSAGE);
//                                                        cardLayout.show(cardPanel, "select options");
//                                                    } else {
                                                    if (outputFromSearch.equals("No products exist in this store.")) {
                                                        JOptionPane.showMessageDialog(null,
                                                                outputFromSearch,
                                                                "Alert", JOptionPane.ERROR_MESSAGE);
                                                        cardLayout.show(cardPanel, "select options");
                                                    }
                                                    ArrayList<String> products = new ArrayList<>();
                                                    while (!outputFromSearch.isEmpty() && !outputFromSearch.equals(
                                                            "No products exist in this store.")) {
                                                        products.add(outputFromSearch.substring(15,
                                                                outputFromSearch.indexOf("]")));
                                                        outputFromSearch = reader2.readLine();
                                                    }
                                                    String productsArray[] =
                                                            products.toArray(new String[products.size()]);
                                                    JComboBox<String> optionsDropdown =
                                                            new JComboBox<>(productsArray);
                                                    optionsDropdown.setBounds(10, 10, 400, 25);

                                                    JButton submitButton = new JButton("Select");
                                                    submitButton.setBounds(10, 40, 80, 25);

                                                    JButton backToStores = new JButton("Back");
                                                    backToStores.setBounds(100, 40, 80, 25);
                                                    productsInStore.add(backToStores);

                                                    backToStores.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            cardLayout.show(cardPanel, "select options");
                                                        }
                                                    });

                                                    submitButton.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            try {
                                                                String selectedProductName =
                                                                        (String) optionsDropdown.getSelectedItem();
                                                                JPanel productPanel = new JPanel(null);
                                                                productPanel.setBackground(Color.LIGHT_GRAY);

                                                                writer.write("selectProduct");
                                                                writer.println();
                                                                writer.flush();

                                                                writer.write(selectedProductName);
                                                                writer.println();
                                                                writer.flush();

                                                                String productToString = reader.readLine();
                                                                String[] productOptions = {
                                                                        "Edit description",
                                                                        "Edit quantity",
                                                                        "Edit price"
                                                                };

                                                                JLabel productString = new JLabel(productToString);
                                                                productString.setBounds(10, 10, 1100,
                                                                        25);
                                                                productPanel.add(productString);

                                                                JComboBox<String> productOptionsDropdown =
                                                                        new JComboBox<>(productOptions);
                                                                productOptionsDropdown.setBounds(10, 40,
                                                                        200,
                                                                        25);
                                                                productPanel.add(productOptionsDropdown);

                                                                JButton productOptionSubmitButton =
                                                                        new JButton("Submit");
                                                                productOptionSubmitButton.setBounds(10, 70,
                                                                        80, 25);
                                                                productPanel.add(productOptionSubmitButton);

                                                                JButton POBackButton = new JButton("Back");
                                                                POBackButton.setBounds(100, 70, 80,
                                                                        25);
                                                                productPanel.add(POBackButton);

                                                                POBackButton.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        cardLayout.show(cardPanel, "manage " +
                                                                                "stores");
                                                                    }
                                                                });

                                                                cardPanel.add(productPanel, "after chose " +
                                                                        "product");
                                                                cardLayout.show(cardPanel, "after chose " +
                                                                        "product");

                                                                productOptionSubmitButton.addActionListener
                                                                        (new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        try {
                                                                            String productOption =
                                                                                    (String) productOptionsDropdown
                                                                                            .getSelectedItem();


                                                                            if (productOption.equals(
                                                                                    "Edit description")) {
                                                                                JLabel editDescriptionLabel =
                                                                                        new JLabel(
                                                                                                "Edit" +
                                                                                                        " description");
                                                                                editDescriptionLabel.setBounds(10,
                                                                                        10, 140,
                                                                                        25);
                                                                                JPanel editDPanel =
                                                                                        new JPanel(null);
                                                                                editDPanel.setBackground(Color
                                                                                        .LIGHT_GRAY);
                                                                                editDPanel.add(editDescriptionLabel);


                                                                                JTextField itemText = new JTextField
                                                                                        (20);
                                                                                itemText.setBounds(110,
                                                                                        10, 400, 25);
                                                                                editDPanel.add(itemText);

                                                                                JButton editButton =
                                                                                        new JButton(
                                                                                                "Submit");
                                                                                editButton.setBounds(10, 40,
                                                                                        120,
                                                                                        25);
                                                                                editDPanel.add(editButton);

                                                                                JButton backButton = new JButton(
                                                                                        "Back");
                                                                                backButton.setBounds(140, 40,
                                                                                        120
                                                                                        , 25);
                                                                                editDPanel.add(backButton);

                                                                                backButton.addActionListener
                                                                                        (new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed
                                                                                            (ActionEvent e) {
                                                                                        cardLayout.show(cardPanel
                                                                                                , "after chose " +
                                                                                                        "product");
                                                                                    }
                                                                                });

                                                                                cardPanel.add(editDPanel,
                                                                                        "editD");
                                                                                cardLayout.show(cardPanel
                                                                                        , "editD");

                                                                                editButton.addActionListener
                                                                                        (new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed
                                                                                            (ActionEvent e) {
                                                                                        try {
                                                                                            String newDescription =
                                                                                                    itemText.getText();
                                                                                            if (newDescription
                                                                                                    .isEmpty()) {
                                                                                                JOptionPane
                                                                                                .showMessageDialog
                                                                                                (null,
                                                                                                "Please enter "
                                                                                                        +
                                                                                                "a valid description.",
                                                                                                        "Error",
                                                                                                JOptionPane
                                                                                                    .ERROR_MESSAGE);
                                                                                            } else {
                                                                                                String descriptionToSend
                                                                                                = selectedProductName
                                                                                                        .trim()
                                                                                                        + "," +
                                                                                                    selectedStoreName
                                                                                                            .trim()
                                                                                                        + "," +
                                                                                                        newDescription;
                                                                                                writer.write(
                                                                                                    "editProduct" +
                                                                                                        "Description");
                                                                                                writer.println();
                                                                                                writer.flush();

                                                                                                writer.write
                                                                                                    (descriptionToSend);
                                                                                                writer.println();
                                                                                                writer.flush();

                                                                                                String x =
                                                                                                        reader
                                                                                                            .readLine();

                                                                                                JOptionPane
                                                                                                    .showMessageDialog(

                                                                                        null, x,
                                                                                                "Edit status",
                                                                                                        JOptionPane
                                                                                                .INFORMATION_MESSAGE);

                                                                                                cardLayout
                                                                                                    .show(cardPanel,
                                                                                                "show store " +
                                                                                                        "products");
                                                                                            }
                                                                                        } catch (
                                                                                                Exception e1) {
                                                                                            System.out.println();
                                                                                        }
                                                                                    }
                                                                                });
                                                                            }
                                                                            if (productOption.equals(
                                                                                    "Edit" +
                                                                                            " " +
                                                                                            "quantity")) {
                                                                                JLabel editQuantityLabel =
                                                                                        new JLabel(
                                                                                                "Edit " +
                                                                                                        "quantity");
                                                                                editQuantityLabel.setBounds(10,
                                                                                        10, 140,
                                                                                        25);
                                                                                JPanel editQPanel =
                                                                                        new JPanel(null);
                                                                                editQPanel.setBackground
                                                                                        (Color.LIGHT_GRAY);

                                                                                editQPanel.add(editQuantityLabel);


                                                                                JTextField quantityText =
                                                                                        new JTextField(20);
                                                                                quantityText.setBounds(110,
                                                                                        10, 400, 25);
                                                                                editQPanel.add(quantityText);

                                                                                JButton editQButton =
                                                                                        new JButton(
                                                                                                "Submit");
                                                                                editQButton.setBounds(10,
                                                                                        40, 120,
                                                                                        25);
                                                                                editQPanel.add(editQButton);

                                                                                JButton backButton = new JButton(
                                                                                        "Back");
                                                                                backButton.setBounds(140, 40,
                                                                                        120
                                                                                        , 25);
                                                                                editQPanel.add(backButton);

                                                                                backButton.addActionListener
                                                                                        (new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed
                                                                                            (ActionEvent e) {
                                                                                        cardLayout.show(cardPanel
                                                                                                , "after chose " +
                                                                                                        "product");
                                                                                    }
                                                                                });
                                                                                cardPanel.add(editQPanel,
                                                                                        "editQ");
                                                                                cardLayout.show(cardPanel
                                                                                        , "editQ");

                                                                                editQButton.addActionListener
                                                                                        (new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed
                                                                                            (ActionEvent e) {
                                                                                        try {
                                                                                            String tempQuantity =
                                                                                                    quantityText
                                                                                                            .getText();
                                                                                            int newQuantity =
                                                                                                    Integer.parseInt
                                                                                                        (tempQuantity);
                                                                                            if (newQuantity < 0) {
                                                                                                throw new Exception();
                                                                                            }
                                                                                            String quantityToSend
                                                                                            = selectedProductName.trim()
                                                                                                    + ","
                                                                                            + selectedStoreName.trim()
                                                                                                    + "," + newQuantity;
                                                                                            writer.write(
                                                                                                    "editProduct" +
                                                                                                            "Quantity");
                                                                                            writer.println();
                                                                                            writer.flush();

                                                                                            writer.write
                                                                                                    (quantityToSend);
                                                                                            writer.println();
                                                                                            writer.flush();

                                                                                            String y =
                                                                                                    reader.readLine();

                                                                                            JOptionPane
                                                                                                    .showMessageDialog(
                                                                                            null, y,
                                                                                                "Edit status",
                                                                                            JOptionPane
                                                                                                .INFORMATION_MESSAGE);
                                                                                            cardLayout.show(cardPanel,
                                                                                            "show store products");

                                                                                        } catch (
                                                                                                Exception e1) {
                                                                                            JOptionPane
                                                                                                .showMessageDialog(
                                                                            null, "Please enter"
                                                                                                                +
                                                                                                    " a valid quantity",
                                                                                                        "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    }
                                                                                });
                                                                            }
                                                                            if (productOption.equals("Edit " +
                                                                                    "price")) {
                                                                                JLabel editPLabel =
                                                                                        new JLabel(
                                                                                                "Edit " +
                                                                                                        "Price");
                                                                                editPLabel.setBounds(10, 10,
                                                                                        140,
                                                                                        25);
                                                                                JPanel editPPanel =
                                                                                        new JPanel(null);
                                                                                editPPanel.setBackground(Color
                                                                                        .LIGHT_GRAY);

                                                                                editPPanel.add(editPLabel);


                                                                                JTextField priceText =
                                                                                        new JTextField(20);
                                                                                priceText.setBounds(110,
                                                                                        10, 400, 25);
                                                                                editPPanel.add(priceText);

                                                                                JButton editPButton =
                                                                                        new JButton(
                                                                                                "Submit");
                                                                                editPButton.setBounds(10,
                                                                                        40, 120,
                                                                                        25);
                                                                                editPPanel.add(editPButton);

                                                                                JButton backButton = new JButton(
                                                                                        "Back");
                                                                                backButton.setBounds(140, 40,
                                                                                        120
                                                                                        , 25);
                                                                                editPPanel.add(backButton);

                                                                                backButton.addActionListener
                                                                                        (new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed
                                                                                            (ActionEvent e) {
                                                                                        cardLayout.show(cardPanel
                                                                                                , "after chose " +
                                                                                                        "product");
                                                                                    }
                                                                                });

                                                                                cardPanel.add(editPPanel,
                                                                                        "editP");
                                                                                cardLayout.show(cardPanel
                                                                                        , "editP");

                                                                                editPButton.addActionListener
                                                                                        (new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed
                                                                                            (ActionEvent e) {
                                                                                        try {
                                                                                            String newPrice =
                                                                                                    priceText.getText();
                                                                                            double temu =
                                                                                                    Double.parseDouble
                                                                                                            (newPrice);
                                                                                            if (temu <= 0) {
                                                                                                throw new Exception();
                                                                                            }
                                                                                            String priceToSend
                                                                                                = selectedProductName
                                                                                                    .trim()
                                                                                            + "," + selectedStoreName
                                                                                                    .trim()
                                                                                                    + "," + newPrice;
                                                                                            writer.write(
                                                                                                    "editProduct" +
                                                                                                            "Price");
                                                                                            writer.println();
                                                                                            writer.flush();

                                                                                            writer.write(priceToSend);
                                                                                            writer.println();
                                                                                            writer.flush();

                                                                                            String z =
                                                                                                    reader.readLine();
                                                                                            JOptionPane
                                                                                                    .showMessageDialog
                                                                                                (null,
                                                                                                z, "Edit Status",
                                                                                                        JOptionPane
                                                                                              .INFORMATION_MESSAGE);
                                                                                            cardLayout.show(cardPanel,
                                                                                          "show store products");

                                                                                        } catch (
                                                                                                Exception e1) {
                                                                                            JOptionPane
                                                                                        .showMessageDialog
                                                                                                (null,
                                                                                    "Please enter a valid" +
                                                                                            " price.", "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
                                                                                        }
                                                                                    }
                                                                                });
                                                                            }

                                                                        } catch (Exception e1) {
                                                                            System.out.println();
                                                                        }
                                                                    }
                                                                });

                                                            } catch (Exception e1) {
                                                                System.out.println();
                                                            }
                                                        }
                                                    });

                                                    productsInStore.add(optionsDropdown);
                                                    productsInStore.add(submitButton);

                                                    cardPanel.add(productsInStore, "show store products");
                                                    cardLayout.show(cardPanel, "show store products");
//                                                    }


                                                } catch (
                                                        Exception e1) {
                                                    System.out.println();
                                                }

                                            }
                                        });
                                    }

                                    if (selectedStoreOption.equals("Add Store")) { //todo
                                        JPanel addStorePanel = new JPanel(null);
                                        addStorePanel.setBackground(Color.LIGHT_GRAY);

                                        String[] addStoreOptionStrings = {
                                                "Add Store with CSV File",
                                                "Add New Empty Store"
                                        };
                                        JComboBox<String> addStoreOptions = new JComboBox<>(addStoreOptionStrings);
                                        addStoreOptions.setBounds(10, 10, 200, 25);
                                        JButton addButton = new JButton("Submit");
                                        addButton.setBounds(10, 40, 80, 25);

                                        addStorePanel.add(addStoreOptions);
                                        addStorePanel.add(addButton);
                                        cardPanel.add(addStorePanel, "add store");
                                        cardLayout.show(cardPanel, "add store");

                                        addButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    String addStoreOption = (String) addStoreOptions.getSelectedItem();

                                                    if (addStoreOption.equals("Add Store with CSV File")) {
                                                        JPanel addCSVPanel = new JPanel(null);
                                                        addCSVPanel.setBackground(Color.LIGHT_GRAY);

                                                        JLabel csvLabel = new JLabel("Enter Store name:");
                                                        csvLabel.setBounds(10, 40, 110, 25);
                                                        addCSVPanel.add(csvLabel);

                                                        JTextField storeName = new JTextField(40);
                                                        storeName.setBounds(140, 40, 110, 25);
                                                        addCSVPanel.add(storeName);

                                                        JLabel csvFileTitle = new JLabel("Enter CSV path:");
                                                        csvFileTitle.setBounds(10, 10, 200, 25);
                                                        addCSVPanel.add(csvFileTitle);

                                                        JTextField csvFileText = new JTextField(40);
                                                        csvFileText.setBounds(140, 10, 110, 25);
                                                        addCSVPanel.add(csvFileText);


                                                        JButton csvButton = new JButton("Add Store");
                                                        csvButton.setBounds(10, 75, 110, 25);
                                                        addCSVPanel.add(csvButton);

                                                        JButton backToManageStoresButton = new JButton("Back");
                                                        backToManageStoresButton.setBounds(140, 75, 80,
                                                                25);
                                                        addCSVPanel.add(backToManageStoresButton);

                                                        backToManageStoresButton.addActionListener(new ActionListener()
                                                        {
                                                            public void actionPerformed(ActionEvent e) {
                                                                cardLayout.show(cardPanel, "manage stores");
                                                            }

                                                        });

                                                        cardPanel.add(addCSVPanel, "add store csv");
                                                        cardLayout.show(cardPanel, "add store csv");

                                                        csvButton.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                try {
                                                                    String csvFile = csvFileText.getText();
                                                                    String storeNameText = storeName.getText();
                                                                    String addStoreToSend =
                                                                            storeNameText + "," + email + "," + csvFile;
                                                                    writer.write("addStoreWithCSV");
                                                                    writer.println();
                                                                    writer.flush();

                                                                    writer.write(addStoreToSend);
                                                                    writer.println();
                                                                    writer.flush();

                                                                    String addStoreCSVStatus = reader.readLine();
                                                                    if (addStoreCSVStatus.equals("storeExists")) {
                                                                        JOptionPane.showMessageDialog(
                                                                                null,
                                                                                "A store " +
                                                                                        "with the name " +
                                                                                        storeNameText + " " +
                                                                                        "already exists.",
                                                                                "Add Store Status",
                                                                                JOptionPane.ERROR_MESSAGE);
                                                                    } else if (addStoreCSVStatus.equals("ProductError"))
                                                                    {
                                                                        JOptionPane.showMessageDialog
                                                                                (null, "One or"
                                                                                                +
                                                                                                " more of the " +
                                                                                                "products you " +
                                                                                                "attempted" +
                                                                                        " to add to your store has a " +
                                                                                                "name that exists in " +
                                                                                                "this" +
                                                                                        " market already. You may " +
                                                                                                "only" +
                                                                                                " add products of" +
                                                                                                " unique" +
                                                                                        " name.", "Add Store " +
                                                                                                "Status",
                                                                                JOptionPane.ERROR_MESSAGE);
                                                                    } else if (addStoreCSVStatus.equals("NotValid" +
                                                                            "StoreOption") || addStoreCSVStatus
                                                                            .equals("Error")) {
                                                                        JOptionPane.showMessageDialog
                                                                                (null, "There "
                                                                                                +
                                                                                                "was an error creating"
                                                                                                +
                                                                                                " the store; ensure the"
                                                                                                +
                                                                                                " path of your CSV file"
                                                                                                +
                                                                                                " is valid!",
                                                                                        "Add Store Status",
                                                                                JOptionPane.ERROR_MESSAGE);
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(
                                                                                null, "Store " +
                                                                                "successfully created!",
                                                                                "Add Store " +
                                                                                "Status", JOptionPane
                                                                                        .INFORMATION_MESSAGE);
                                                                    }
                                                                    // todo go back to previous screen
                                                                } catch (Exception e1) {
                                                                    System.out.println();
                                                                }
                                                            }
                                                        });
                                                    }
                                                    if (addStoreOption.equals("Add New Empty Store")) {
                                                        JPanel addEmptyStorePanel = new JPanel(null);
                                                        addEmptyStorePanel.setBackground(Color.LIGHT_GRAY);

                                                        JLabel storeNameLabel = new JLabel("Enter store name:");
                                                        storeNameLabel.setBounds(10, 10, 110, 25);
                                                        addEmptyStorePanel.add(storeNameLabel);

                                                        JTextField storeName = new JTextField(40);
                                                        storeName.setBounds(130, 10, 400, 250);
                                                        addEmptyStorePanel.add(storeName);

                                                        JButton addStoreButton = new JButton("Add Store");
                                                        addStoreButton.setBounds(10, 40, 120, 25);
                                                        addEmptyStorePanel.add(addStoreButton);

                                                        JButton backToManageStoresButton = new JButton("Back");
                                                        backToManageStoresButton.setBounds(10, 75, 80,
                                                                25);
                                                        addEmptyStorePanel.add(backToManageStoresButton);

                                                        backToManageStoresButton.addActionListener
                                                                (new ActionListener() {
                                                            public void actionPerformed(ActionEvent e) {
                                                                cardLayout.show(cardPanel, "manage stores");
                                                            }

                                                        });

                                                        cardPanel.add(addEmptyStorePanel, "add blank store");
                                                        cardLayout.show(cardPanel, "add blank store");

                                                        addStoreButton.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                try {
                                                                    String storeToCreate =
                                                                            storeName.getText() + "," + email;
                                                                    writer.write("addBlankStore");
                                                                    writer.println();
                                                                    writer.flush();

                                                                    writer.write(storeToCreate);
                                                                    writer.println();
                                                                    writer.flush();

                                                                    String in = reader.readLine();
                                                                    if (in.equals("Successful")) {
                                                                        JOptionPane.showMessageDialog
                                                                                (null, "Store "
                                                                                        +
                                                                                "successfully created", "Add " +
                                                                                        "Store " +
                                                                                "Status", JOptionPane
                                                                                        .INFORMATION_MESSAGE);
                                                                    } else {
                                                                        JOptionPane.showMessageDialog
                                                                                (null,
                                                                                        "Store " +
                                                                                "name unavailable.", "Add Store" +
                                                                                                " " +
                                                                                "Status", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                    // todo go back
                                                                } catch (Exception e1) {
                                                                    System.out.println();
                                                                }
                                                            }
                                                        });
                                                    }
                                                } catch (Exception e1) {
                                                    System.out.println();
                                                }
                                            }
                                        });


                                        //todo back button
                                    }
                                    if (selectedStoreOption.equals("Export Store Products")) {
                                        JPanel exportPanel = new JPanel(null);
                                        exportPanel.setBackground(Color.LIGHT_GRAY);

                                        JLabel fileTitle = new JLabel("Enter File Name:");
                                        fileTitle.setBounds(10, 10, 110, 25);
                                        exportPanel.add(fileTitle);

                                        JTextField fileName = new JTextField(40);
                                        fileName.setBounds(110, 10, 400, 25);
                                        exportPanel.add(fileName);

                                        JLabel storeNameTitle = new JLabel("Enter store name:");
                                        storeNameTitle.setBounds(10, 40, 110, 25);
                                        exportPanel.add(storeNameTitle);

                                        JTextField storeName = new JTextField(40);
                                        storeName.setBounds(125, 40, 400, 25);
                                        exportPanel.add(storeName);

                                        JButton exportButton = new JButton("Export Store");
                                        exportButton.setBounds(10, 70, 120, 25);
                                        exportPanel.add(exportButton);
                                        exportButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    String exportToSend =
                                                            fileName.getText() + "," + email
                                                                    + "," + storeName.getText();
                                                    writer.write("exportStoreProducts");
                                                    writer.println();
                                                    writer.flush();

                                                    writer.write(exportToSend);
                                                    writer.println();
                                                    writer.flush();

                                                    String in = reader.readLine();
                                                    if (in.equals("Successfully Done")) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "Export Successful.",
                                                                "Export Status", JOptionPane.INFORMATION_MESSAGE);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, in,
                                                                "Export Status",
                                                                JOptionPane.ERROR_MESSAGE);
                                                    }
                                                    //todo go back
                                                } catch (Exception e1) {
                                                    System.out.println();
                                                }
                                            }
                                        });
                                        cardPanel.add(exportPanel, "export panel");
                                        cardLayout.show(cardPanel, "export panel");
                                        //todo back button
                                    }


                                } catch (Exception e1) {
                                    System.out.println();
                                }
                            }
                        });
                    }
                    if (selectedOption.equals("Statistics")) {
                        JPanel sellerStatisticsPanel = new JPanel(null);
                        sellerStatisticsPanel.setBackground(Color.LIGHT_GRAY);

                        String[] sellerStatsOptions = {
                                "View all your customers with number of items purchased",
                                "View all your products & number of sales per product",
                                "View sales by store, including customer information and revenue",
                                "View number of products currently in customer's shopping cart",
                                "Return to main menu"
                        };

                        JComboBox<String> sellerStatOptionsDropdown = new JComboBox<>(sellerStatsOptions);
                        sellerStatOptionsDropdown.setBounds(10, 10, 200, 25);

                        JButton sellerStatSubmitButton = new JButton("Submit");
                        sellerStatSubmitButton.setBounds(10, 40, 80, 25);

                        sellerStatisticsPanel.add(sellerStatOptionsDropdown);
                        sellerStatisticsPanel.add(sellerStatSubmitButton);
                        cardPanel.add(sellerStatisticsPanel, "seller stats panel");
                        cardLayout.show(cardPanel, "seller stats panel");


                        sellerStatSubmitButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Socket socket2 = new Socket("localhost", 4242);
                                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket2
                                            .getInputStream()));
                                    PrintWriter writer2 = new PrintWriter(socket2.getOutputStream());

                                    String selectedSellerStatOption = (String) sellerStatOptionsDropdown
                                            .getSelectedItem();
                                    switch (selectedSellerStatOption) {
                                        case "View all your customers with number of items purchased":

                                            JPanel sellerStat1Options = new JPanel(null);
                                            sellerStat1Options.setBackground(Color.LIGHT_GRAY);

                                            String[] sellerStat1OptionString = {
                                                    "Alphabetical (by customer name)",
                                                    "Ascending (by products sold per store)",
                                                    "Descending (by products sold per store)",
                                                    "Back"
                                            };

                                            JLabel sellerStat1SortLabel = new JLabel("How would you like " +
                                                    "to sort?");
                                            sellerStat1SortLabel.setBounds(10, 10, 200, 25);

                                            JComboBox<String> sellerStat1OptionsDropdown = new JComboBox<>
                                                    (sellerStat1OptionString);
                                            sellerStat1OptionsDropdown.setBounds(10, 40, 200, 25);

                                            JButton stat1SubmitButton = new JButton("Submit");
                                            stat1SubmitButton.setBounds(10, 70, 80, 25);

                                            sellerStat1Options.add(sellerStat1SortLabel);
                                            sellerStat1Options.add(sellerStat1OptionsDropdown);
                                            sellerStat1Options.add(stat1SubmitButton);

                                            cardPanel.add(sellerStat1Options, "seller stats1");
                                            cardLayout.show(cardPanel, "seller stats1");

                                            stat1SubmitButton.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    String selectedSellerStat1Option =
                                                            (String) sellerStat1OptionsDropdown.getSelectedItem();
                                                    switch (selectedSellerStat1Option) {
                                                        case "Alphabetical (by customer name)":
                                                            try {
                                                                writer.write("sellerDisplayAllCustomers");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",alphabetical");
                                                                writer.println();
                                                                writer.flush();

                                                                String alphabeticalOutput = reader.readLine();
                                                                String allAlphabeticalOutputs = "";

                                                                while (!alphabeticalOutput.isEmpty()) {
                                                                    allAlphabeticalOutputs += alphabeticalOutput
                                                                            + "\n\n";
                                                                    alphabeticalOutput = reader.readLine();
                                                                }

                                                                JTextArea textArea =
                                                                        new JTextArea(allAlphabeticalOutputs);
                                                                JScrollPane scrollPane = new JScrollPane(textArea);
                                                                textArea.setLineWrap(true);
                                                                textArea.setWrapStyleWord(true);
                                                                scrollPane
                                                                        .setPreferredSize(new Dimension
                                                                                (500, 500));

                                                                JOptionPane.showMessageDialog(frame, scrollPane,
                                                                        "Alphabetical" +
                                                                                " sort of all Customers",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            } catch (IOException e2) {
                                                                System.out.println();
                                                            }
                                                            break;
                                                        case "Ascending (by products sold per store)":
                                                            try {
                                                                writer.write("sellerDisplayAllCustomers");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",ascending");
                                                                writer.println();
                                                                writer.flush();

                                                                String ascendingOutput = reader.readLine();
                                                                String allAscendingOutputs = "";

                                                                while (!ascendingOutput.isEmpty()) {
                                                                    allAscendingOutputs += ascendingOutput + "\n\n";
                                                                    ascendingOutput = reader.readLine();
                                                                }

                                                                JTextArea textArea = new JTextArea(allAscendingOutputs);
                                                                JScrollPane scrollPane = new JScrollPane(textArea);
                                                                textArea.setLineWrap(true);
                                                                textArea.setWrapStyleWord(true);
                                                                scrollPane.setPreferredSize(new Dimension(500
                                                                        , 500));

                                                                JOptionPane.showMessageDialog(frame, scrollPane,
                                                                        "Ascending" +
                                                                                " " +
                                                                                "sort(by products sold) of all " +
                                                                                "Customers",
                                                                        JOptionPane.INFORMATION_MESSAGE);

                                                            } catch (IOException e2) {
                                                                System.out.println();
                                                            }
                                                            break;
                                                        case "Descending (by products sold per store)":
                                                            try {
                                                                writer.write("sellerDisplayAllCustomers");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",descending");
                                                                writer.println();
                                                                writer.flush();

                                                                String descendingOutput = reader.readLine();
                                                                String allDescendingOutputs = "";

                                                                while (!descendingOutput.isEmpty()) {
                                                                    allDescendingOutputs += descendingOutput + "\n\n";
                                                                    descendingOutput = reader.readLine();
                                                                }

                                                                JTextArea textArea =
                                                                        new JTextArea(allDescendingOutputs);
                                                                JScrollPane scrollPane = new JScrollPane(textArea);
                                                                textArea.setLineWrap(true);
                                                                textArea.setWrapStyleWord(true);
                                                                scrollPane.setPreferredSize(new Dimension(500,
                                                                        500));

                                                                JOptionPane.showMessageDialog(frame, scrollPane,
                                                                        "Ascending" +
                                                                                " " +
                                                                                "sort(by products sold) of " +
                                                                                "all Customers",
                                                                        JOptionPane.INFORMATION_MESSAGE);

                                                            } catch (IOException e2) {
                                                                System.out.println();
                                                            }
                                                            break;
                                                        case "Back":
                                                            try {
                                                                writer.write("sellerDisplayAllCustomers");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",defaultCase");
                                                                writer.println();
                                                                writer.flush();

                                                                String defaultOutput1 = reader.readLine();
                                                                while (!defaultOutput1.isEmpty()) {
                                                                    defaultOutput1 = reader.readLine();
                                                                }
                                                            } catch (IOException ioe) {

                                                                System.out.println("");
                                                            }


                                                            cardLayout.show(cardPanel, "seller stats panel");
                                                            break;

                                                    }
                                                }

                                            });
                                            break;
                                        case "View all your products & number of sales per product":

                                            JPanel sellerStat2Options = new JPanel(null);
                                            sellerStat2Options.setBackground(Color.LIGHT_GRAY);

                                            String[] sellerStat2OptionString = {
                                                    "Alphabetical (by product name)",
                                                    "Ascending (by quantity of product sold)",
                                                    "Descending (by quantity of product sold)",
                                                    "Back"
                                            };

                                            JLabel sellerStat2SortLabel = new JLabel("How would you like to" +
                                                    " sort?");
                                            sellerStat2SortLabel.setBounds(10, 10, 200, 25);

                                            JComboBox<String> sellerStat2OptionsDropdown =
                                                    new JComboBox<>(sellerStat2OptionString);
                                            sellerStat2OptionsDropdown.setBounds(10, 40, 200, 25);

                                            JButton stat2SubmitButton = new JButton("Submit");
                                            stat2SubmitButton.setBounds(10, 70, 80, 25);

                                            sellerStat2Options.add(sellerStat2SortLabel);
                                            sellerStat2Options.add(sellerStat2OptionsDropdown);
                                            sellerStat2Options.add(stat2SubmitButton);

                                            cardPanel.add(sellerStat2Options, "seller stats2");
                                            cardLayout.show(cardPanel, "seller stats2");

                                            stat2SubmitButton.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    String selectedSellerStat2Option = (String)
                                                            sellerStat2OptionsDropdown.getSelectedItem();
                                                    switch (selectedSellerStat2Option) {
                                                        case "Alphabetical (by product name)":
                                                            try {
                                                                writer.write("sellerDisplayAllProductsAndSales");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",alphabetical");
                                                                writer.println();
                                                                writer.flush();

                                                                String alphabeticalOutput = reader.readLine();
                                                                String allAlphabeticalOutputs = "";

                                                                while (!alphabeticalOutput.isEmpty()) {
                                                                    allAlphabeticalOutputs += alphabeticalOutput +
                                                                            "\n\n";
                                                                    alphabeticalOutput = reader.readLine();
                                                                }

                                                                JTextArea textArea =
                                                                        new JTextArea(allAlphabeticalOutputs);
                                                                JScrollPane scrollPane = new JScrollPane(textArea);
                                                                textArea.setLineWrap(true);
                                                                textArea.setWrapStyleWord(true);
                                                                scrollPane.setPreferredSize(new Dimension(500,
                                                                        500));
                                                                JOptionPane.showMessageDialog(frame, scrollPane,
                                                                        "Alphabetical" +
                                                                                " sort of all Customers",
                                                                        JOptionPane.INFORMATION_MESSAGE);

                                                            } catch (IOException e2) {
                                                                System.out.println();
                                                            }
                                                            break;
                                                        case "Ascending (by quantity of product sold)":
                                                            try {
                                                                writer.write("sellerDisplayAllProductsAndSales");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",ascending");
                                                                writer.println();
                                                                writer.flush();

                                                                String ascendingOutput2 = reader.readLine();
                                                                String allAscendingOutputs2 = "";

                                                                while (!ascendingOutput2.isEmpty()) {
                                                                    allAscendingOutputs2 += ascendingOutput2 + "\n\n";
                                                                    ascendingOutput2 = reader.readLine();
                                                                }

                                                                JTextArea textArea =
                                                                        new JTextArea(allAscendingOutputs2);
                                                                JScrollPane scrollPane = new JScrollPane(textArea);
                                                                textArea.setLineWrap(true);
                                                                textArea.setWrapStyleWord(true);
                                                                scrollPane.setPreferredSize(new Dimension(500,
                                                                        500));

                                                                JOptionPane.showMessageDialog(frame, scrollPane,
                                                                        "Ascending" +
                                                                                " " +
                                                                                "sort by products sold per store",
                                                                        JOptionPane.INFORMATION_MESSAGE);

                                                            } catch (IOException e2) {
                                                                System.out.println();
                                                            }
                                                            break;
                                                        case "Descending (by quantity of product sold)":
                                                            try {
                                                                writer.write("sellerDisplayAllProductsAndSales");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",descending");
                                                                writer.println();
                                                                writer.flush();

                                                                String descendingOutput2 = reader.readLine();
                                                                String allDescendingOutputs2 = "";

                                                                while (!descendingOutput2.isEmpty()) {
                                                                    allDescendingOutputs2 += descendingOutput2 + "\n\n";
                                                                    descendingOutput2 = reader.readLine();
                                                                }

                                                                JTextArea textArea =
                                                                        new JTextArea(allDescendingOutputs2);
                                                                JScrollPane scrollPane = new JScrollPane(textArea);
                                                                textArea.setLineWrap(true);
                                                                textArea.setWrapStyleWord(true);
                                                                scrollPane.setPreferredSize(new Dimension(500,
                                                                        500));
                                                                JOptionPane.showMessageDialog(frame, scrollPane,
                                                                        "Descending" +
                                                                                " " +
                                                                                "sort by products sold per store",
                                                                        JOptionPane.INFORMATION_MESSAGE);

                                                            } catch (IOException e2) {
                                                                System.out.println();
                                                            }
                                                            break;
                                                        case "Back":
                                                            try {
                                                                writer.write("sellerDisplayAllProductsAndSales");
                                                                writer.println();
                                                                writer.flush();
                                                                writer.write(email + ",defaultCase");
                                                                writer.println();
                                                                writer.flush();

                                                                String defaultOutput2 = reader.readLine();
                                                                while (!defaultOutput2.isEmpty()) {
                                                                    defaultOutput2 = reader.readLine();
                                                                }
                                                            } catch (IOException ioe) {
                                                                // TODO: GET RID OF
                                                                System.out.println("");
                                                            }

                                                            cardLayout.show(cardPanel, "seller stats panel");
                                                            break;

                                                    }
                                                }

                                            });
                                            break;
                                        case "View sales by store, including customer information and revenue":
                                            writer.write("sellerDisplaySalesWithInfo");
                                            writer.println();
                                            writer.flush();

                                            // writing the input
                                            writer.write(email);
                                            writer.println();
                                            writer.flush();

                                            String displaySalesInfoOutput = reader.readLine();
                                            String allDisplaySalesInfoOutput = "";
                                            while (!displaySalesInfoOutput.isEmpty()) {
                                                allDisplaySalesInfoOutput += displaySalesInfoOutput + "\n\n";
                                                displaySalesInfoOutput = reader.readLine();
                                            }

                                            JTextArea textArea = new JTextArea(allDisplaySalesInfoOutput);
                                            JScrollPane scrollPane = new JScrollPane(textArea);
                                            textArea.setLineWrap(true);
                                            textArea.setWrapStyleWord(true);
                                            scrollPane.setPreferredSize(new Dimension(500, 500));
                                            JOptionPane.showMessageDialog(frame, scrollPane, "Sales by store",
                                                    JOptionPane.PLAIN_MESSAGE);
                                            break;
                                        case "View number of products currently in customer's shopping cart":
                                            writer.write("sellerViewProductsInCarts");
                                            writer.println();
                                            writer.flush();

                                            // writing the input
                                            writer.write(email);
                                            writer.println();
                                            writer.flush();

                                            String productsInCarts = reader.readLine();
                                            String allProductsInCarts = "";
                                            while (!productsInCarts.isEmpty()) {
                                                allProductsInCarts += productsInCarts + "\n\n";
                                                productsInCarts = reader.readLine();
                                            }

                                            JTextArea textArea2 = new JTextArea(allProductsInCarts);
                                            JScrollPane scrollPane2 = new JScrollPane(textArea2);
                                            textArea2.setLineWrap(true);
                                            textArea2.setWrapStyleWord(true);
                                            scrollPane2.setPreferredSize(new Dimension(500, 500));
                                            JOptionPane.showMessageDialog(frame, scrollPane2,
                                                    "Number of products " +
                                                    "currently" +
                                                    " " +
                                                    "in" +
                                                    " " +
                                                    "customer's shopping carts", JOptionPane.PLAIN_MESSAGE);
                                            break;
                                        case "Return to main menu":
                                            cardLayout.show(cardPanel, "after seller login");
                                    }
                                } catch (IOException e2) {
                                    System.out.println();
                                }
                            }
                        });


                    }


                    if (selectedOption.equals("Delete Your Account")) {
                        int choice = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete your " +
                                        "account?",
                                "Delete" +
                                        " " +
                                        "account", JOptionPane.YES_NO_OPTION);
                        if (choice == 0) {
                            writer.write("sellerDeleteAccount");
                            writer.println();
                            writer.flush();

                            writer.write(email);
                            writer.println();
                            writer.flush();

                            String deletionStatus = reader.readLine();
                            JOptionPane.showMessageDialog(null, deletionStatus, "Deletion Status",
                                    JOptionPane.PLAIN_MESSAGE);

                            cardLayout.show(cardPanel, "Welcome");
                        }
                    }
                    if (selectedOption.equals("Log Out")) {
                        name = "";
                        email = "";
                        password = "";
                        JOptionPane.showMessageDialog(null,
                                "Thank you for selling with us!", "Logged Out " +
                                        "Successfully", JOptionPane.PLAIN_MESSAGE);
                        //todo take you back to the customer/seller screen
                        cardLayout.show(cardPanel, "Welcome");
                    }


                } catch (
                        IOException e2) {
                    System.out.println();
                }
            }
        });

        sellerOptions.add(optionsDropdown);
        sellerOptions.add(submitButton);

        cardPanel.add(sellerOptions, "after seller login");


    }
}



