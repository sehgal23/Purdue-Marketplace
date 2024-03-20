//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//class DataManagerTest {
//
//    @Test
//    void testReadProductsFile() {
//        try {
//            ArrayList<Product> products = DataManager.readProductsFile("Products.csv");
//
//            assertNotNull(products);
//
//            // Print or log the contents of the list for debugging
//            System.out.println("Products: " + products);
//
//            // Add more assertions based on your expected data in the "Products.csv" file
//            assertEquals(34, products.size());
//            assertEquals("Premium Noise-Canceling Headphones", products.get(0).getName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    void testUpdatePurchasedProducts() {
//        try {
//            // Create a sample product for testing
//            Product product = new Product("TestProduct", "TestStore", "TestDescription", 10, 5, 20.0);
//            DataManager.updatePurchasedProducts("Products.csv", product);
//            // Add assertions based on your expected data in the "Products.csv" file after the update
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testReadCustomerAccountInformation() {
//        try {
//            ArrayList<Customer> customers = DataManager.readCustomerAccountInformation();
//            assertNotNull(customers);
//            assertEquals(17, customers.size());
//            // Add more assertions based on your expected data in the "CustomerInformation.csv" file
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testUpdateCustomerInformation() {
//        try {
//            // Create a sample customer for testing
//            Customer customer = new Customer("TestCustomer", "test@example.com", "password");
//            DataManager.updateCustomerInformation(customer);
//            // Add assertions based on your expected data in the "CustomerInformation.csv" file after the update
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testReadSellerAccountInformation() {
//        try {
//            ArrayList<Seller> sellers = DataManager.readSellerAccountInformation();
//            assertNotNull(sellers);
//            // Add more assertions based on your expected data in the "SellerInformation.csv" file
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testUpdateSellerInformation() {
//        try {
//            // Create a sample seller for testing
//            Seller seller = new Seller("TestSeller", "test_seller@example.com", "seller_password");
//            DataManager.updateSellerInformation(seller);
//            // Add assertions based on your expected data in the "SellerInformation.csv" file after the update
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*@Test
//    void testReadStoresFile() {
//        try {
//            ArrayList<Store> stores = DataManager.readStoresFile();
//            assertNotNull(stores);
//            // Add more assertions based on your expected data in the "StoresToAdd.csv" file
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testAddStores() {
//        try {
//            // Create a sample store for testing
//            ArrayList<Product> productList = new ArrayList<>();
//            productList.add(new Product("Laptop", "MyStore", "High-performance laptop", 5, 5, 1000.0));
//            productList.add(new Product("Phone", "MyStore", "Smartphone", 8, 8, 500.0));
//
//            Store store = new Store(productList, "john@example.com", "MyStore");
//            DataManager.addStores(store);
//            // Add assertions based on your expected data in the "StoresToAdd.csv" file after the update
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }*/
//
//
//}
