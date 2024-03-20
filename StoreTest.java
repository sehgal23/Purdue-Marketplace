//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class StoreTest {
//
//    private Customer customer;
//    private Store store;
//
//    @Before
//    public void setUp() {
//        // Initialize a sample customer
//        customer = new Customer("Sanketh", "edara@purdue.edu", "ILoveVirginia");
//
//        // Initialize a sample store with products
//        ArrayList<Product> productList = new ArrayList<>();
//        productList.add(new Product("Laptop", "MyStore", "High-performance laptop", 5, 5, 1000.0));
//        productList.add(new Product("Phone", "MyStore", "Smartphone", 8, 8, 500.0));
//
//        store = new Store(productList, "john@example.com", "MyStore");
//    }
//    @Test
//    public void testUpdateProductQuantity() {
//        assertTrue(store.updateProductQuantity("Laptop", 3));
//        assertEquals(2, store.getProduct("Laptop").getCurrQuantity());
//    }
//
//    @Test
//    public void testUpdateProductQuantityNotEnoughStock() {
//        assertFalse(store.updateProductQuantity("Laptop", 10));
//        assertEquals(5, store.getProduct("Laptop").getCurrQuantity());
//    }
//
//    @Test
//    public void testSearchPrice() {
//        ArrayList<Product> result = store.searchPrice(600.0);
//        assertEquals(1, result.size());
//        assertEquals("Phone", result.get(0).getName());
//    }
//
//    @Test
//    public void testSearchQuantity() {
//        ArrayList<Product> result = store.searchQuantity(7);
//        assertEquals(1, result.size());
//        assertEquals("Laptop", result.get(0).getName());
//    }
//
//    @Test
//    public void testGetName() {
//        assertEquals("MyStore", store.getName());
//    }
//
//    @Test
//    public void testGetProductList() {
//        assertEquals(2, store.getProductList().size());
//    }
//
//    @Test
//    public void testGetProduct() {
//        assertNotNull(store.getProduct("Laptop"));
//        assertNull(store.getProduct("Tablet"));
//    }
//
//    @Test
//    public void testGetSellerEmail() {
//        assertEquals("john@example.com", store.getSellerEmail());
//    }
//
//    @Test
//    public void testAddProduct() {
//        Product newProduct = new Product("Tablet", "MyStore", "Touchscreen tablet", 10, 10, 300.0);
//        store.addProduct(newProduct);
//        assertEquals(3, store.getProductList().size());
//        assertNotNull(store.getProduct("Tablet"));
//    }
//
//    @Test
//    public void testRemoveProduct() {
//        //  assertTrue(store.removeProduct("Laptop"));
//        assertEquals(1, store.getProductList().size());
//        assertNull(store.getProduct("Laptop"));
//    }
//
//    @Test
//    public void testRemoveProductNotExists() {
//        //assertFalse(store.removeProduct("Tablet"));
//        assertEquals(2, store.getProductList().size());
//    }
//
//    @Test
//    public void testSetProductList() {
//        ArrayList<Product> newProductList = new ArrayList<>();
//        newProductList.add(new Product("Tablet", "MyStore", "Touchscreen tablet", 10, 10, 300.0));
//        newProductList.add(new Product("Desktop", "MyStore", "Powerful desktop computer", 3, 3, 1200.0));
//
//        store.setProductList(newProductList);
//        assertEquals(2, store.getProductList().size());
//        assertNotNull(store.getProduct("Tablet"));
//        assertNotNull(store.getProduct("Desktop"));
//    }
//
//    @Test
//    public void testEquals() {
//        Store sameStore = new Store(store.getProductList(), "john@example.com", "MyStore");
//        assertTrue(store.equals(sameStore));
//    }
//
//    @Test
//    public void testListCustomerData() {
//        // Assuming you have a list of customers and their purchased products
//        ArrayList<Customer> customers = new ArrayList<>();
//        customers.add(customer);
//
//        // Assuming the customer purchased a product from the store
//        customer.purchaseProduct(store.getProduct("Laptop"));
//
//        String expected = "Alice's Purchase History:\n" +
//                "Product{name: [Laptop], description: [High-performance laptop], quantity available: [5], price: 1000.0}\n";
//
//        assertEquals(expected, customer.viewPurchaseHistory());
//    }
//
//    @Test
//    public void testGetQuantitySold() {
//        assertEquals(13, store.getQuantitySold());
//    }
//
//    @Test
//    public void testToString() {
//        String expected = "Store MyStore\nProducts:\n" +
//                " + Product{name: [Laptop], description: [High-performance laptop], quantity available: [5], price: 1000.0}\n" +
//                " + Product{name: [Phone], description: [Smartphone], quantity available: [8], price: 500.0}";
//
//        assertEquals(expected, store.toString());
//    }
//}
