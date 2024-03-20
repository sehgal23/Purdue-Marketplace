//import org.junit.Test;
//import org.junit.After;
//import java.lang.reflect.Field;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.rules.Timeout;
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;
//import javax.swing.*;
//import java.io.*;
//import java.lang.reflect.*;
//import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;
//import java.lang.reflect.InvocationTargetException;
//import java.util.UUID;
//import static org.junit.Assert.*;
//
//public class MarketplacePortalTest {
//
//    public static void main(String[] args) {
//        Result result = JUnitCore.runClasses(TestCase.class);
//        System.out.printf("Test Count: %d.\n", result.getRunCount());
//        if (result.wasSuccessful()) {
//            System.out.printf("Excellent - all local tests ran successfully.\n");
//        } else {
//            System.out.printf("Tests failed: %d.\n",result.getFailureCount());
//            for (Failure failure : result.getFailures()) {
//                System.out.println(failure.toString());
//            }
//        }
//    }
//
//
//    public static class TestCase {
//        private final PrintStream originalOutput = System.out;
//        private final InputStream originalSysin = System.in;
//        @SuppressWarnings("FieldCanBeLocal")
//        private ByteArrayInputStream testIn;
//        @SuppressWarnings("FieldCanBeLocal")
//        private ByteArrayOutputStream testOut;
//
//        @Before
//        public void outputStart() {
//            testOut = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(testOut));
//        }
//
//        @After
//        public void restoreInputAndOutput() {
//            System.setIn(originalSysin);
//            System.setOut(originalOutput);
//        }
//
//        private String getOutput() {
//            return testOut.toString();
//        }
//
//        @SuppressWarnings("SameParameterValue")
//        private void receiveInput(String str) {
//            testIn = new ByteArrayInputStream(str.getBytes());
//            System.setIn(testIn);
//        }
//
//        @Test(timeout = 1000)
//        public void testOne() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nBob Smith\nbob.smith@email.com\npassword456\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testTwo() throws IOException {
//
//            // Set the input
//            String input = "1\n2\nBob Dolan\nbob.dolan@purdue.edu\npassword\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Created account and logged in successfully!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testThree() throws IOException {
//
//            // Set the input
//            String input = "3\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                                "[1] Customer\n" +
//                                "[2] Seller\n" +
//                                "[3] Exit\n" +
//                                "Thank you for shopping with us!";
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testFour() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n1\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                    "Product{name: [Organic Matcha Green Tea Powder], description: [High-quality ceremonial-grade matcha for a healthy boost], quantity available: [50], price: 24.99]}\n" +
//                    "Product{name: [Home Workout Dumbbell Set], description: [Compact dumbbell set for versatile at-home workouts], quantity available: [80], price: 59.99]}\n" +
//                    "Product{name: [Digital Drawing Tablet], description: [Professional drawing tablet with pressure sensitivity and customizable buttons], quantity available: [15], price: 199.99]}\n" +
//                    "Product{name: [Handwoven Cotton Throw Blanket], description: [Soft and cozy throw blanket made from handwoven cotton], quantity available: [60], price: 44.99]}\n" +
//                    "Product{name: [Miniature Succulent Garden Kit], description: [Create your mini succulent garden with this DIY kit], quantity available: [25], price: 19.99]}\n" +
//                    "Product{name: [Smart Home Security Camera], description: [Wireless security camera with real-time monitoring and smart features], quantity available: [20], price: 129.99]}\n" +
//                    "Product{name: [Aromatherapy Essential Oil Diffuser], description: [Ultrasonic diffuser for a calming atmosphere with essential oils], quantity available: [70], price: 34.99]}\n" +
//                    "Product{name: [Vintage Polaroid Camera], description: [Capture memories in style with this vintage-inspired Polaroid camera], quantity available: [10], price: 89.99]}\n" +
//                    "Product{name: [Natural Bamboo Bath Towel Set], description: [Luxurious and eco-friendly bath towels made from bamboo fibers], quantity available: [55], price: 29.99]}\n" +
//                    "Product{name: [Premium Leather Laptop Bag], description: [Sleek and durable laptop bag made from premium leather], quantity available: [18], price: 69.99]}\n" +
//                    "Product{name: [Bluetooth Noise-Canceling Earbuds], description: [Wireless earbuds with noise-canceling technology for an immersive audio experience], quantity available: [50], price: 79.99]}\n" +
//                    "Product{name: [Portable Espresso Maker], description: [Compact and portable espresso maker for coffee enthusiasts], quantity available: [30], price: 49.99]}\n" +
//                    "Product{name: [Natural Beeswax Candle Set], description: [Handcrafted beeswax candles with natural scents], quantity available: [50], price: 14.99]}\n" +
//                    "Product{name: [Bluetooth Fitness Tracker], description: [Track your fitness goals with this Bluetooth-enabled fitness tracker], quantity available: [65], price: 39.99]}\n" +
//                    "Product{name: [Reusable Silicone Food Storage Bags], description: [Environmentally friendly food storage solution], quantity available: [40], price: 19.99]}\n" +
//                    "Product{name: [Solar-Powered Phone Charger], description: [Charge your devices on the go with solar power], quantity available: [25], price: 29.99]}\n" +
//                    "Product{name: [Wireless Charging Pad], description: [Convenient wireless charging for compatible devices], quantity available: [30], price: 19.99]}\n" +
//                    "Product{name: [Premium Bamboo Bed Sheets], description: [Luxurious bed sheets made from sustainable bamboo fibers], quantity available: [8], price: 79.99]}\n" +
//                    "Product{name: [Collapsible Travel Backpack], description: [Compact and durable backpack for travel and outdoor adventures], quantity available: [35], price: 29.99]}\n" +
//                    "Product{name: [Stainless Steel Insulated Water Bottle], description: [Keeps your drinks cold or hot on the go], quantity available: [4], price: 24.99]}\n" +
//                    "Product{name: [Indoor Herb Garden Kit], description: [Start your own herb garden indoors with this kit], quantity available: [4], price: 34.99]}\n" +
//                    "Product{name: [Smart Home Thermostat], description: [Energy-efficient thermostat with smart home integration], quantity available: [5], price: 99.99]}\n" +
//                    "Product{name: [Biodegradable Bamboo Toothbrush Set], description: [Sustainable toothbrush set for eco-conscious oral care], quantity available: [1], price: 9.99]}\n" +
//                    "Product{name: [Digital SLR Camera], description: [Professional-grade camera for stunning photography], quantity available: [15], price: 599.99]}\n" +
//                    "Product{name: [Yoga Mat and Accessories Kit], description: [All-in-one kit for yoga enthusiasts], quantity available: [2], price: 49.99]}\n" +
//                    "Product{name: [Solar-Powered Outdoor String Lights], description: [Decorate your outdoor space with eco-friendly solar-powered lights], quantity available: [2], price: 24.99]}\n" +
//                    "Product{name: [Premium Tea Infuser Set], description: [Enhance your tea experience with this stylish infuser set], quantity available: [40], price: 14.99]}\n" +
//                    "Product{name: [Compact Folding Umbrella], description: [Stay dry with this compact and durable folding umbrella], quantity available: [3], price: 19.99]}\n" +
//                    "Product{name: [Gourmet Chocolate Truffle Assortment], description: [Indulge in a variety of delicious chocolate truffles], quantity available: [7], price: 29.99]}\n" +
//                    "Product{name: [Digital Air Fryer], description: [Enjoy crispy and healthy meals with this digital air fryer], quantity available: [8], price: 89.99]}\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testFive() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n2\n1\nBamboo";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please choose one of the following options\n" +
//                    "[1] Search by keyword\n" +
//                    "[2] Search by store name\n" +
//                    "[3] Search by price threshold\n" +
//                    "[4] Sort by price\n" +
//                    "[5] Sort by quantity available\n" +
//                    "[6] View all products\n" +
//                    "[7] Back\n" +
//                    "Enter search term:\n" +
//                    "Product{name: [Natural Bamboo Bath Towel Set], description: [Luxurious and eco-friendly bath towels made from bamboo fibers], quantity available: [55], price: 29.99]}\n" +
//                    "Product{name: [Premium Bamboo Bed Sheets], description: [Luxurious bed sheets made from sustainable bamboo fibers], quantity available: [8], price: 79.99]}\n" +
//                    "Product{name: [Biodegradable Bamboo Toothbrush Set], description: [Sustainable toothbrush set for eco-conscious oral care], quantity available: [1], price: 9.99]}\n" +
//                    "\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testSix() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n2\n2\nAudioElegance";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please choose one of the following options\n" +
//                    "[1] Search by keyword\n" +
//                    "[2] Search by store name\n" +
//                    "[3] Search by price threshold\n" +
//                    "[4] Sort by price\n" +
//                    "[5] Sort by quantity available\n" +
//                    "[6] View all products\n" +
//                    "[7] Back\n" +
//                    "Enter store name:\n" +
//                    "Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                    "Product{name: [Natural Bamboo Bath Towel Set], description: [Luxurious and eco-friendly bath towels made from bamboo fibers], quantity available: [55], price: 29.99]}\n" +
//                    "Product{name: [Solar-Powered Phone Charger], description: [Charge your devices on the go with solar power], quantity available: [25], price: 29.99]}\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testSeven() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n2\n3\n99\n2\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please choose one of the following options\n" +
//                    "[1] Search by keyword\n" +
//                    "[2] Search by store name\n" +
//                    "[3] Search by price threshold\n" +
//                    "[4] Sort by price\n" +
//                    "[5] Sort by quantity available\n" +
//                    "[6] View all products\n" +
//                    "[7] Back\n" +
//                    "Enter price\n" +
//                    "Lower or Higher? \n" +
//                    "[1] Lower\n" +
//                    "[2] Higher\n" +
//                    "Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                    "Product{name: [Digital Drawing Tablet], description: [Professional drawing tablet with pressure sensitivity and customizable buttons], quantity available: [15], price: 199.99]}\n" +
//                    "Product{name: [Smart Home Security Camera], description: [Wireless security camera with real-time monitoring and smart features], quantity available: [20], price: 129.99]}\n" +
//                    "Product{name: [Smart Home Thermostat], description: [Energy-efficient thermostat with smart home integration], quantity available: [5], price: 99.99]}\n" +
//                    "Product{name: [Digital SLR Camera], description: [Professional-grade camera for stunning photography], quantity available: [15], price: 599.99]}\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testEight() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n2\n4\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please choose one of the following options\n" +
//                    "[1] Search by keyword\n" +
//                    "[2] Search by store name\n" +
//                    "[3] Search by price threshold\n" +
//                    "[4] Sort by price\n" +
//                    "[5] Sort by quantity available\n" +
//                    "[6] View all products\n" +
//                    "[7] Back\n" +
//                    "Product{name: [Biodegradable Bamboo Toothbrush Set], description: [Sustainable toothbrush set for eco-conscious oral care], quantity available: [1], price: 9.99]}\n" +
//                    "Product{name: [Natural Beeswax Candle Set], description: [Handcrafted beeswax candles with natural scents], quantity available: [50], price: 14.99]}\n" +
//                    "Product{name: [Premium Tea Infuser Set], description: [Enhance your tea experience with this stylish infuser set], quantity available: [40], price: 14.99]}\n" +
//                    "Product{name: [Miniature Succulent Garden Kit], description: [Create your mini succulent garden with this DIY kit], quantity available: [25], price: 19.99]}\n" +
//                    "Product{name: [Reusable Silicone Food Storage Bags], description: [Environmentally friendly food storage solution], quantity available: [40], price: 19.99]}\n" +
//                    "Product{name: [Wireless Charging Pad], description: [Convenient wireless charging for compatible devices], quantity available: [30], price: 19.99]}\n" +
//                    "Product{name: [Compact Folding Umbrella], description: [Stay dry with this compact and durable folding umbrella], quantity available: [3], price: 19.99]}\n" +
//                    "Product{name: [Organic Matcha Green Tea Powder], description: [High-quality ceremonial-grade matcha for a healthy boost], quantity available: [50], price: 24.99]}\n" +
//                    "Product{name: [Stainless Steel Insulated Water Bottle], description: [Keeps your drinks cold or hot on the go], quantity available: [4], price: 24.99]}\n" +
//                    "Product{name: [Solar-Powered Outdoor String Lights], description: [Decorate your outdoor space with eco-friendly solar-powered lights], quantity available: [2], price: 24.99]}\n" +
//                    "Product{name: [Natural Bamboo Bath Towel Set], description: [Luxurious and eco-friendly bath towels made from bamboo fibers], quantity available: [55], price: 29.99]}\n" +
//                    "Product{name: [Solar-Powered Phone Charger], description: [Charge your devices on the go with solar power], quantity available: [25], price: 29.99]}\n" +
//                    "Product{name: [Collapsible Travel Backpack], description: [Compact and durable backpack for travel and outdoor adventures], quantity available: [35], price: 29.99]}\n" +
//                    "Product{name: [Gourmet Chocolate Truffle Assortment], description: [Indulge in a variety of delicious chocolate truffles], quantity available: [7], price: 29.99]}\n" +
//                    "Product{name: [Aromatherapy Essential Oil Diffuser], description: [Ultrasonic diffuser for a calming atmosphere with essential oils], quantity available: [70], price: 34.99]}\n" +
//                    "Product{name: [Indoor Herb Garden Kit], description: [Start your own herb garden indoors with this kit], quantity available: [4], price: 34.99]}\n" +
//                    "Product{name: [Bluetooth Fitness Tracker], description: [Track your fitness goals with this Bluetooth-enabled fitness tracker], quantity available: [65], price: 39.99]}\n" +
//                    "Product{name: [Handwoven Cotton Throw Blanket], description: [Soft and cozy throw blanket made from handwoven cotton], quantity available: [60], price: 44.99]}\n" +
//                    "Product{name: [Portable Espresso Maker], description: [Compact and portable espresso maker for coffee enthusiasts], quantity available: [30], price: 49.99]}\n" +
//                    "Product{name: [Yoga Mat and Accessories Kit], description: [All-in-one kit for yoga enthusiasts], quantity available: [2], price: 49.99]}\n" +
//                    "Product{name: [Home Workout Dumbbell Set], description: [Compact dumbbell set for versatile at-home workouts], quantity available: [80], price: 59.99]}\n" +
//                    "Product{name: [Premium Leather Laptop Bag], description: [Sleek and durable laptop bag made from premium leather], quantity available: [18], price: 69.99]}\n" +
//                    "Product{name: [Bluetooth Noise-Canceling Earbuds], description: [Wireless earbuds with noise-canceling technology for an immersive audio experience], quantity available: [50], price: 79.99]}\n" +
//                    "Product{name: [Premium Bamboo Bed Sheets], description: [Luxurious bed sheets made from sustainable bamboo fibers], quantity available: [8], price: 79.99]}\n" +
//                    "Product{name: [Vintage Polaroid Camera], description: [Capture memories in style with this vintage-inspired Polaroid camera], quantity available: [10], price: 89.99]}\n" +
//                    "Product{name: [Digital Air Fryer], description: [Enjoy crispy and healthy meals with this digital air fryer], quantity available: [8], price: 89.99]}\n" +
//                    "Product{name: [Smart Home Thermostat], description: [Energy-efficient thermostat with smart home integration], quantity available: [5], price: 99.99]}\n" +
//                    "Product{name: [Smart Home Security Camera], description: [Wireless security camera with real-time monitoring and smart features], quantity available: [20], price: 129.99]}\n" +
//                    "Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                    "Product{name: [Digital Drawing Tablet], description: [Professional drawing tablet with pressure sensitivity and customizable buttons], quantity available: [15], price: 199.99]}\n" +
//                    "Product{name: [Digital SLR Camera], description: [Professional-grade camera for stunning photography], quantity available: [15], price: 599.99]}\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testNine() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n2\n5\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please choose one of the following options\n" +
//                    "[1] Search by keyword\n" +
//                    "[2] Search by store name\n" +
//                    "[3] Search by price threshold\n" +
//                    "[4] Sort by price\n" +
//                    "[5] Sort by quantity available\n" +
//                    "[6] View all products\n" +
//                    "[7] Back\n" +
//                    "Product{name: [Biodegradable Bamboo Toothbrush Set], description: [Sustainable toothbrush set for eco-conscious oral care], quantity available: [1], price: 9.99]}\n" +
//                    "Product{name: [Yoga Mat and Accessories Kit], description: [All-in-one kit for yoga enthusiasts], quantity available: [2], price: 49.99]}\n" +
//                    "Product{name: [Solar-Powered Outdoor String Lights], description: [Decorate your outdoor space with eco-friendly solar-powered lights], quantity available: [2], price: 24.99]}\n" +
//                    "Product{name: [Compact Folding Umbrella], description: [Stay dry with this compact and durable folding umbrella], quantity available: [3], price: 19.99]}\n" +
//                    "Product{name: [Stainless Steel Insulated Water Bottle], description: [Keeps your drinks cold or hot on the go], quantity available: [4], price: 24.99]}\n" +
//                    "Product{name: [Indoor Herb Garden Kit], description: [Start your own herb garden indoors with this kit], quantity available: [4], price: 34.99]}\n" +
//                    "Product{name: [Smart Home Thermostat], description: [Energy-efficient thermostat with smart home integration], quantity available: [5], price: 99.99]}\n" +
//                    "Product{name: [Gourmet Chocolate Truffle Assortment], description: [Indulge in a variety of delicious chocolate truffles], quantity available: [7], price: 29.99]}\n" +
//                    "Product{name: [Premium Bamboo Bed Sheets], description: [Luxurious bed sheets made from sustainable bamboo fibers], quantity available: [8], price: 79.99]}\n" +
//                    "Product{name: [Digital Air Fryer], description: [Enjoy crispy and healthy meals with this digital air fryer], quantity available: [8], price: 89.99]}\n" +
//                    "Product{name: [Vintage Polaroid Camera], description: [Capture memories in style with this vintage-inspired Polaroid camera], quantity available: [10], price: 89.99]}\n" +
//                    "Product{name: [Digital Drawing Tablet], description: [Professional drawing tablet with pressure sensitivity and customizable buttons], quantity available: [15], price: 199.99]}\n" +
//                    "Product{name: [Digital SLR Camera], description: [Professional-grade camera for stunning photography], quantity available: [15], price: 599.99]}\n" +
//                    "Product{name: [Premium Leather Laptop Bag], description: [Sleek and durable laptop bag made from premium leather], quantity available: [18], price: 69.99]}\n" +
//                    "Product{name: [Smart Home Security Camera], description: [Wireless security camera with real-time monitoring and smart features], quantity available: [20], price: 129.99]}\n" +
//                    "Product{name: [Miniature Succulent Garden Kit], description: [Create your mini succulent garden with this DIY kit], quantity available: [25], price: 19.99]}\n" +
//                    "Product{name: [Solar-Powered Phone Charger], description: [Charge your devices on the go with solar power], quantity available: [25], price: 29.99]}\n" +
//                    "Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                    "Product{name: [Portable Espresso Maker], description: [Compact and portable espresso maker for coffee enthusiasts], quantity available: [30], price: 49.99]}\n" +
//                    "Product{name: [Wireless Charging Pad], description: [Convenient wireless charging for compatible devices], quantity available: [30], price: 19.99]}\n" +
//                    "Product{name: [Collapsible Travel Backpack], description: [Compact and durable backpack for travel and outdoor adventures], quantity available: [35], price: 29.99]}\n" +
//                    "Product{name: [Reusable Silicone Food Storage Bags], description: [Environmentally friendly food storage solution], quantity available: [40], price: 19.99]}\n" +
//                    "Product{name: [Premium Tea Infuser Set], description: [Enhance your tea experience with this stylish infuser set], quantity available: [40], price: 14.99]}\n" +
//                    "Product{name: [Organic Matcha Green Tea Powder], description: [High-quality ceremonial-grade matcha for a healthy boost], quantity available: [50], price: 24.99]}\n" +
//                    "Product{name: [Bluetooth Noise-Canceling Earbuds], description: [Wireless earbuds with noise-canceling technology for an immersive audio experience], quantity available: [50], price: 79.99]}\n" +
//                    "Product{name: [Natural Beeswax Candle Set], description: [Handcrafted beeswax candles with natural scents], quantity available: [50], price: 14.99]}\n" +
//                    "Product{name: [Natural Bamboo Bath Towel Set], description: [Luxurious and eco-friendly bath towels made from bamboo fibers], quantity available: [55], price: 29.99]}\n" +
//                    "Product{name: [Handwoven Cotton Throw Blanket], description: [Soft and cozy throw blanket made from handwoven cotton], quantity available: [60], price: 44.99]}\n" +
//                    "Product{name: [Bluetooth Fitness Tracker], description: [Track your fitness goals with this Bluetooth-enabled fitness tracker], quantity available: [65], price: 39.99]}\n" +
//                    "Product{name: [Aromatherapy Essential Oil Diffuser], description: [Ultrasonic diffuser for a calming atmosphere with essential oils], quantity available: [70], price: 34.99]}\n" +
//                    "Product{name: [Home Workout Dumbbell Set], description: [Compact dumbbell set for versatile at-home workouts], quantity available: [80], price: 59.99]}\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testTen() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n2\n6\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please choose one of the following options\n" +
//                    "[1] Search by keyword\n" +
//                    "[2] Search by store name\n" +
//                    "[3] Search by price threshold\n" +
//                    "[4] Sort by price\n" +
//                    "[5] Sort by quantity available\n" +
//                    "[6] View all products\n" +
//                    "[7] Back\n" +
//                    "Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                    "Product{name: [Organic Matcha Green Tea Powder], description: [High-quality ceremonial-grade matcha for a healthy boost], quantity available: [50], price: 24.99]}\n" +
//                    "Product{name: [Home Workout Dumbbell Set], description: [Compact dumbbell set for versatile at-home workouts], quantity available: [80], price: 59.99]}\n" +
//                    "Product{name: [Digital Drawing Tablet], description: [Professional drawing tablet with pressure sensitivity and customizable buttons], quantity available: [15], price: 199.99]}\n" +
//                    "Product{name: [Handwoven Cotton Throw Blanket], description: [Soft and cozy throw blanket made from handwoven cotton], quantity available: [60], price: 44.99]}\n" +
//                    "Product{name: [Miniature Succulent Garden Kit], description: [Create your mini succulent garden with this DIY kit], quantity available: [25], price: 19.99]}\n" +
//                    "Product{name: [Smart Home Security Camera], description: [Wireless security camera with real-time monitoring and smart features], quantity available: [20], price: 129.99]}\n" +
//                    "Product{name: [Aromatherapy Essential Oil Diffuser], description: [Ultrasonic diffuser for a calming atmosphere with essential oils], quantity available: [70], price: 34.99]}\n" +
//                    "Product{name: [Vintage Polaroid Camera], description: [Capture memories in style with this vintage-inspired Polaroid camera], quantity available: [10], price: 89.99]}\n" +
//                    "Product{name: [Natural Bamboo Bath Towel Set], description: [Luxurious and eco-friendly bath towels made from bamboo fibers], quantity available: [55], price: 29.99]}\n" +
//                    "Product{name: [Premium Leather Laptop Bag], description: [Sleek and durable laptop bag made from premium leather], quantity available: [18], price: 69.99]}\n" +
//                    "Product{name: [Bluetooth Noise-Canceling Earbuds], description: [Wireless earbuds with noise-canceling technology for an immersive audio experience], quantity available: [50], price: 79.99]}\n" +
//                    "Product{name: [Portable Espresso Maker], description: [Compact and portable espresso maker for coffee enthusiasts], quantity available: [30], price: 49.99]}\n" +
//                    "Product{name: [Natural Beeswax Candle Set], description: [Handcrafted beeswax candles with natural scents], quantity available: [50], price: 14.99]}\n" +
//                    "Product{name: [Bluetooth Fitness Tracker], description: [Track your fitness goals with this Bluetooth-enabled fitness tracker], quantity available: [65], price: 39.99]}\n" +
//                    "Product{name: [Reusable Silicone Food Storage Bags], description: [Environmentally friendly food storage solution], quantity available: [40], price: 19.99]}\n" +
//                    "Product{name: [Solar-Powered Phone Charger], description: [Charge your devices on the go with solar power], quantity available: [25], price: 29.99]}\n" +
//                    "Product{name: [Wireless Charging Pad], description: [Convenient wireless charging for compatible devices], quantity available: [30], price: 19.99]}\n" +
//                    "Product{name: [Premium Bamboo Bed Sheets], description: [Luxurious bed sheets made from sustainable bamboo fibers], quantity available: [8], price: 79.99]}\n" +
//                    "Product{name: [Collapsible Travel Backpack], description: [Compact and durable backpack for travel and outdoor adventures], quantity available: [35], price: 29.99]}\n" +
//                    "Product{name: [Stainless Steel Insulated Water Bottle], description: [Keeps your drinks cold or hot on the go], quantity available: [4], price: 24.99]}\n" +
//                    "Product{name: [Indoor Herb Garden Kit], description: [Start your own herb garden indoors with this kit], quantity available: [4], price: 34.99]}\n" +
//                    "Product{name: [Smart Home Thermostat], description: [Energy-efficient thermostat with smart home integration], quantity available: [5], price: 99.99]}\n" +
//                    "Product{name: [High-Performance Blender], description: [Blend smoothies and soups with this powerful kitchen appliance], quantity available: [0], price: 129.99]}\n" +
//                    "Product{name: [Biodegradable Bamboo Toothbrush Set], description: [Sustainable toothbrush set for eco-conscious oral care], quantity available: [1], price: 9.99]}\n" +
//                    "Product{name: [Digital SLR Camera], description: [Professional-grade camera for stunning photography], quantity available: [15], price: 599.99]}\n" +
//                    "Product{name: [Yoga Mat and Accessories Kit], description: [All-in-one kit for yoga enthusiasts], quantity available: [2], price: 49.99]}\n" +
//                    "Product{name: [Solar-Powered Outdoor String Lights], description: [Decorate your outdoor space with eco-friendly solar-powered lights], quantity available: [2], price: 24.99]}\n" +
//                    "Product{name: [Premium Tea Infuser Set], description: [Enhance your tea experience with this stylish infuser set], quantity available: [40], price: 14.99]}\n" +
//                    "Product{name: [Compact Folding Umbrella], description: [Stay dry with this compact and durable folding umbrella], quantity available: [3], price: 19.99]}\n" +
//                    "Product{name: [Gourmet Chocolate Truffle Assortment], description: [Indulge in a variety of delicious chocolate truffles], quantity available: [7], price: 29.99]}\n" +
//                    "Product{name: [Digital Air Fryer], description: [Enjoy crispy and healthy meals with this digital air fryer], quantity available: [8], price: 89.99]}\n" +
//                    "Product{name: [Chef's Knife Set], description: [High-quality chef's knives for precision and durability], quantity available: [0], price: 79.99]}\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testEleven() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n2\n7\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please choose one of the following options\n" +
//                    "[1] Search by keyword\n" +
//                    "[2] Search by store name\n" +
//                    "[3] Search by price threshold\n" +
//                    "[4] Sort by price\n" +
//                    "[5] Sort by quantity available\n" +
//                    "[6] View all products\n" +
//                    "[7] Back\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testTwelve() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n3\nPremium Noise-Canceling Headphones\ny\n5\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Enter the name of the product you would like to select:\n" +
//                    "The product you have selected: Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                    "Would you like to add this product to your cart? Y/N\n" +
//                    "What quantity would you like to add to your cart?\n" +
//                    "Successfully added to cart!\n" +
//                    "Please choose one of the following options to continue: \n" +
//                    "[1] Select another item\n" +
//                    "[2] Return to main menu\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testThirteen() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n5\ny\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Your Purchased Products: \n" +
//                    "Would you like to export a receipt of your purchase history? (Y/N)\n" +
//                    "Successfully exported receipt!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//            System.out.println(output);
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testFourteen() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n6\n1\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Welcome to your statistics menu.\n" +
//                    "Please select an option to continue.\n" +
//                    "[1] View all stores with number of products sold\n" +
//                    "[2] View stores purchased from\n" +
//                    "[3] Return to main menu\n" +
//                    "How would you like to sort? \n" +
//                    "[1] Alphabetical (by store name)\n" +
//                    "[2] Ascending (by products sold per store)\n" +
//                    "[3] Descending (by products sold per store)\n" +
//                    "\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//            System.out.println(output);
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testFifteen() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n6\n2\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Welcome to your statistics menu.\n" +
//                    "Please select an option to continue.\n" +
//                    "[1] View all stores with number of products sold\n" +
//                    "[2] View stores purchased from\n" +
//                    "[3] Return to main menu\n" +
//                    "How would you like to sort? \n" +
//                    "[1] Alphabetical (by store name)\n" +
//                    "[2] Ascending (by products sold per store)\n" +
//                    "[3] Descending (by products sold per store)\n" +
//                    "\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//            System.out.println(output);
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//        @Test(timeout = 1000)
//        public void testSixteen() throws IOException {
//
//            // Set the input
//            String input = "1\n1\nSanketh Edara\nedara@purdue.edu\nILoveVirginia\n6\n3\n";
//            // Pair the input with the expected result
//            String expected = "Welcome to the Purdue Market homepage! Please select user type or press 3 to quit:\n" +
//                    "[1] Customer\n" +
//                    "[2] Seller\n" +
//                    "[3] Exit\n" +
//                    "Please choose one of the following options: \n" +
//                    "[1] Log in \n" +
//                    "[2] Create an account\n" +
//                    "Please enter name:\n" +
//                    "Please enter email:\n" +
//                    "Please enter password:\n" +
//                    "Logged in!\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Welcome to your statistics menu.\n" +
//                    "Please select an option to continue.\n" +
//                    "[1] View all stores with number of products sold\n" +
//                    "[2] View stores purchased from\n" +
//                    "[3] Return to main menu\n" +
//                    "Welcome to your marketplace! Choose one of the below options to continue: \n" +
//                    "[1] Display All Products\n" +
//                    "[2] Search Products\n" +
//                    "[3] Select a product\n" +
//                    "[4] Manage Shopping Cart\n" +
//                    "[5] View Purchase History\n" +
//                    "[6] Statistics\n" +
//                    "[7] Delete your account\n" +
//                    "[8] Log out\n" +
//                    "Please enter a valid input.";
//
//
//            // Runs the program with the input values
//            receiveInput(input);
//            try
//            {
//                MarketplacePortal.main(new String[0]);
//            } catch (Exception e) {
//
//            }
//
//
//            // Retrieves the output from the program
//            String output = getOutput();
//            System.out.println(output);
//            // Trims the output and verifies it is correct.
//            output = output.replace("\r\n", "\n");
//            expected = expected.replace("\r\n", "\n");
//            assertEquals("Make sure your output matches the expected output",
//                    expected.trim(), output.trim());
//        }
//
//
//
//
//
//
//
//
//
//
//
//    }
//}
