//import org.junit.Before;
//import org.junit.Test;
//import java.util.ArrayList;
//import static org.junit.Assert.*;
//import java.util.Arrays;
//
//public class SellerTest {
//
//    private Seller seller;
//
//    @Before
//    public void setUp() {
//        // Initialize a Seller object with some sample data
//        seller = new Seller("Sanketh Edara", "edara@purdue.edu", "ILoveVirginia");
//        Product product1 = new Product("Tesla", "MyStore", "Electric car", 10, 10, 30000);
//        Product product2 = new Product("Toyota Camry", "MyStore", "Compact sedan car", 30, 30, 26000);
//        Product product3 = new Product("Cadillac Escilade", "MyStore", "Large standard utility vehicle", 5, 5, 75000);
//        ArrayList<Product> myStoreProducts = new ArrayList<Product>();
//        myStoreProducts.add(product1);
//        myStoreProducts.add(product2);
//        myStoreProducts.add(product3);
//        // Create a sample store
//        Store store = new Store(myStoreProducts,"edara@purdue.edu", "MyStore");
//        seller.addStore(store);
//    }
//
//    @Test
//    public void testAddProduct() {
//        Product product = new Product("Laptop", "MyStore", "High-performance laptop", 5, 5, 1000.0);
//        assertTrue(seller.addProduct(product));
//
//       // assertFalse(seller.addProduct(product));
//    }
//
//
//    @Test
//    public void testAddStore() {
//        Store newStore = new Store(new ArrayList<>(), "john@example.com", "NewStore");
//        assertTrue(seller.addStore(newStore));
//
//        // Try adding the same store again
//        assertFalse(seller.addStore(newStore));
//    }
//
//    @Test
//    public void testRemoveStore() {
//        Store storeToRemove = new Store(new ArrayList<>(), "john@example.com", "ToRemove");
//        seller.addStore(storeToRemove);
//
//        assertTrue(seller.removeStore(storeToRemove));
//
//        // Try removing the same store again (should fail as it's already removed)
//        assertFalse(seller.removeStore(storeToRemove));
//    }
//
//    @Test
//    public void testViewStores() {
//        String expectedOutput = "Sanketh Edara's Stores: \nStore: MyStore\nProducts: \n" +
//                "Product{name: [Tesla], description: [Electric car], quantity available: [10], price: 30000.0]}\n" +
//                "Product{name: [Toyota Camry], description: [Compact sedan car], quantity available: [30], price: 26000.0]}\n" +
//                "Product{name: [Cadillac Escilade], description: [Large standard utility vehicle], quantity available: [5], price: 75000.0]}";
//
//        assertEquals(expectedOutput.trim(), seller.viewStores().trim());
//    }
//
//    @Test
//    public void testViewProducts() {
//
//        // Add a product to the store
//        Product product = new Product("Tablet", "MyStore", "Portable tablet", 3, 3, 300.0);
//        seller.addProduct(product);
//
//        String expectedOutput = "Sanketh Edara's Products: \n" +
//                "Product{name: [Tesla], description: [Electric car], quantity available: [10], price: 30000.0]}\n" +
//                "Product{name: [Toyota Camry], description: [Compact sedan car], quantity available: [30], price: 26000.0]}\n" +
//                "Product{name: [Cadillac Escilade], description: [Large standard utility vehicle], quantity available: [5], price: 75000.0]}";
//
//        expectedOutput += "\n" + product.toString();
//        assertEquals(expectedOutput.trim(), seller.viewProducts().trim());
//    }
//
//    @Test
//    public void testToText() {
//        String expectedText = "Sanketh Edara,edara@purdue.edu,ILoveVirginia,MyStore";
//
//        assertEquals(expectedText, seller.toText());
//    }
//}
