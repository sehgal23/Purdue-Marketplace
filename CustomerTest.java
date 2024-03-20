//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class CustomerTest {
//
//    private Customer customer;
//    private Product product1;
//    private Product product2;
//
//    @Before
//    public void setUp() {
//        // Initialize a sample customer
//        customer = new Customer("Sanketh", "edara@purdue.edu", "ILoveVirginia");
//
//        // Initialize sample products
//        product1 = new Product("Laptop", "MyStore", "High-performance laptop", 5, 5, 1000.0);
//        product2 = new Product("Phone", "MyStore", "Smartphone", 8, 8, 500.0);
//    }
//
//    @Test
//    public void testPurchaseProduct() {
//        assertTrue(customer.purchaseProduct(product1));
//
//        // Check if the product is added to the purchased products
//        assertEquals(1, customer.getPurchasedProducts().size());
//
//        // Try purchasing the same product again (should increase quantity)
//        assertTrue(customer.purchaseProduct(product1));
//
//        // Check if the quantity is increased
//        assertEquals(1, customer.getPurchasedProducts().size());
//        assertEquals(10, customer.getPurchasedProducts().get(0).getCurrQuantity());
//    }
//
//    @Test
//    public void testAddToCart() {
//        assertTrue(customer.addToCart(product1));
//
//        // Check if the product is added to the shopping cart
//        assertEquals(1, customer.getShoppingCart().size());
//
//        // Try adding the same product again (should increase quantity)
//        assertTrue(customer.addToCart(product1));
//
//        // Check if the quantity is increased
//        assertEquals(1, customer.getShoppingCart().size());
//        assertEquals(10, customer.getShoppingCart().get(0).getCurrQuantity());
//    }
//
//    @Test
//    public void testRemoveFromCart() {
//        // Add a product to the shopping cart
//        customer.addToCart(product1);
//
//        // Remove the product from the shopping cart
//        assertTrue(customer.removeFromCart(product1));
//
//        // Check if the product is removed from the shopping cart
//        assertEquals(0, customer.getShoppingCart().size());
//
//        // Try removing a product that is not in the shopping cart (should fail)
//        assertTrue(!customer.removeFromCart(product2));
//    }
//
//    @Test
//    public void testViewPurchaseHistory() {
//        // Purchase two products
//        customer.purchaseProduct(product1);
//        customer.purchaseProduct(product2);
//
//        String expectedOutput = "Sanketh's Purchase History:\n" +
//                "Product{name: [Laptop], description: [High-performance laptop], quantity available: [5], price: 1000.0]}\n" +
//                "Product{name: [Phone], description: [Smartphone], quantity available: [8], price: 500.0]}\n";
//
//        assertEquals(expectedOutput, customer.viewPurchaseHistory());
//    }
//}
