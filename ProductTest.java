//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class ProductTest {
//
//    private Product product;
//
//    @Before
//    public void setUp() {
//        // Initialize a sample product
//        product = new Product("Laptop", "MyStore", "High-performance laptop", 5, 5, 1000.0);
//    }
//
//    @Test
//    public void testGetName() {
//        assertEquals("Laptop", product.getName());
//    }
//
//    @Test
//    public void testGetStoreName() {
//        assertEquals("MyStore", product.getStoreName());
//    }
//
//    @Test
//    public void testGetDescription() {
//        assertEquals("High-performance laptop", product.getDescription());
//    }
//
//    @Test
//    public void testGetCurrQuantity() {
//        assertEquals(5, product.getCurrQuantity());
//    }
//
//    @Test
//    public void testGetInitialQuantity() {
//        assertEquals(5, product.getInitialQuantity());
//    }
//
//    @Test
//    public void testGetQuantityPurchased() {
//        assertEquals(0, product.getQuantityPurchased());
//    }
//
//    @Test
//    public void testGetPrice() {
//        assertEquals(1000.0, product.getPrice(), 0.001);
//    }
//
//    @Test
//    public void testSetInitialQuantity() {
//        product.setInitialQuantity(10);
//        assertEquals(10, product.getInitialQuantity());
//    }
//
//    @Test
//    public void testSetCurrQuantity() {
//        product.setCurrQuantity(3);
//        assertEquals(3, product.getCurrQuantity());
//    }
//
//    @Test
//    public void testSetPrice() {
//        product.setPrice(1200.0);
//        assertEquals(1200.0, product.getPrice(), 0.001);
//    }
//
//    @Test
//    public void testSetName() {
//        product.setName("New Laptop");
//        assertEquals("New Laptop", product.getName());
//    }
//
//    @Test
//    public void testSetDescription() {
//        product.setDescription("Updated description");
//        assertEquals("Updated description", product.getDescription());
//    }
//
//    @Test
//    public void testSetStoreName() {
//        product.setStoreName("NewStore");
//        assertEquals("NewStore", product.getStoreName());
//    }
//
//    @Test
//    public void testEquals() {
//        Product sameProduct = new Product("Laptop", "MyStore", "High-performance laptop", 5, 5, 1000.0);
//        assertTrue(product.equals(sameProduct));
//    }
//
//    @Test
//    public void testToString() {
//        String expected = "Product{name: [Laptop], description: [High-performance laptop], quantity available: [5], price: 1000.0]}";
//        assertEquals(expected, product.toString());
//    }
//}
