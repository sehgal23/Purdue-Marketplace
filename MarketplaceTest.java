//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//import java.util.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class MarketplaceTest {
//
//    private Marketplace marketplace;
//
//    @BeforeEach
//    void setUp() {
//        // Set up the marketplace with sample data for testing
//        ArrayList<Seller> sellers = new ArrayList<>(); // Add sample sellers
//        // Add sample products to the DataManager's "Products.csv" file
//        //DataManager.writeProductsFile(new ArrayList<>());
//        marketplace = new Marketplace(sellers);
//    }
//
//    @Test
//    void testListProducts() {
//        // Assuming you have added sample products to the DataManager
//        String[] products = marketplace.listProducts();
//        // Add assertions based on the expected number of products and their details
//        assertEquals(33, products.length);
//        assertEquals("Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}", products[0]); // Replace with actual product details
//    }
//
//    @Test
//    void testSearchProducts() {
//        // Assuming you have added sample products to the DataManager
//        String result = marketplace.searchProducts("Home Workout Dumbbell Set");
//        System.out.println(result);
//        // Add assertions based on the expected result
//        assertEquals("Home Workout Dumbbell Set", result); // Replace with the expected result
//    }
//
//    @Test
//    void testProductsContaining() {
//        // Assuming you have added sample products to the DataManager
//        ArrayList<Product> productsContaining = marketplace.productsContaining("Set");
//        // Add assertions based on the expected number of products containing the keyword
//        assertEquals(6, productsContaining.size()); // Replace with the expected size
//    }
//
//    @Test
//    void testSortProductsByPrice() {
//        // Assuming you have added sample products to the DataManager
//        String[] sortedProducts = marketplace.sortProductsByPrice();
//        // Add assertions based on the expected order of products by price
//        assertEquals("Product{name: [Biodegradable Bamboo Toothbrush Set], description: [Sustainable toothbrush set for eco-conscious oral care], quantity available: [1], price: 9.99]}", sortedProducts[0]);
//        assertEquals("Product{name: [Reusable Silicone Food Storage Bags], description: [Environmentally friendly food storage solution], quantity available: [40], price: 19.99]}", sortedProducts[4]);
//        assertEquals("Product{name: [Collapsible Travel Backpack], description: [Compact and durable backpack for travel and outdoor adventures], quantity available: [35], price: 29.99]}", sortedProducts[12]);
//
//    }
//
//    @Test
//    void testSortProductsByQuantity() {
//        // Assuming you have added sample products to the DataManager
//        String[] sortedProducts = marketplace.sortProductsByQuantity();
//        // Add assertions based on the expected order of products by quantity
//        assertEquals("Product{name: [High-Performance Blender], description: [Blend smoothies and soups with this powerful kitchen appliance], quantity available: [3], price: 129.99]}", sortedProducts[2]); // Replace with the expected order
//        assertEquals("Product{name: [Smart Home Thermostat], description: [Energy-efficient thermostat with smart home integration], quantity available: [5], price: 99.99]}", sortedProducts[7]); // Replace with the expected order
//        assertEquals("Product{name: [Premium Leather Laptop Bag], description: [Sleek and durable laptop bag made from premium leather], quantity available: [18], price: 69.99]}", sortedProducts[14]); // Replace with the expected order
//    }
//
//    @Test
//    void testSearchByStore() {
//        // Assuming you have added sample products to the DataManager
//        String[] matchingProducts = marketplace.searchByStore("AudioElegance");
//        String matchingProductsString = "";
//        for (String s: matchingProducts) {
//            matchingProductsString += s.toString() + "\n";
//        }
//        // Add assertions based on the expected products in the specified store
//        assertEquals("Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                             "Product{name: [Premium Bamboo Bed Sheets], description: [Luxurious bed sheets made from sustainable bamboo fibers], quantity available: [9], price: 79.99]}\n" +
//                            "Product{name: [Yoga Mat and Accessories Kit], description: [All-in-one kit for yoga enthusiasts], quantity available: [3], price: 49.99]}\n" +
//                              "Product{name: [Compact Folding Umbrella], description: [Stay dry with this compact and durable folding umbrella], quantity available: [3], price: 19.99]}", matchingProductsString.trim()); // Replace with the expected products
//    }
//
//    @Test
//    void testSearchByPrice() {
//        // Assuming you have added sample products to the DataManager
//        String[] matchingProducts = marketplace.searchByPrice(99.99, false);
//        // Add assertions based on the expected products with prices higher than the threshold
//        String matchingProductsString = "";
//        for (String s: matchingProducts) {
//            matchingProductsString += s.toString() + "\n";
//        }
//        assertEquals("Product{name: [Premium Noise-Canceling Headphones], description: [Immersive audio experience with advanced technology], quantity available: [30], price: 149.99]}\n" +
//                "Product{name: [Digital Drawing Tablet], description: [Professional drawing tablet with pressure sensitivity and customizable buttons], quantity available: [15], price: 199.99]}\n" +
//                "Product{name: [Smart Home Security Camera], description: [Wireless security camera with real-time monitoring and smart features], quantity available: [20], price: 129.99]}\n" +
//                "Product{name: [High-Performance Blender], description: [Blend smoothies and soups with this powerful kitchen appliance], quantity available: [3], price: 129.99]}\n" +
//                "Product{name: [Digital SLR Camera], description: [Professional-grade camera for stunning photography], quantity available: [15], price: 599.99]}", matchingProductsString.trim());
//
//    }
//
//    @Test
//    void testGetStoresPurchasedFrom() {
//        // Assuming you have added sample products and customers to the DataManager
//        Product p1 = new Product("Natural Beeswax Candle Set","ArtisanCandles","Handcrafted beeswax candles with natural scents",50,50,14.99);
//        Product p2 = new Product("Bluetooth Fitness Tracker","FitTech","Track your fitness goals with this Bluetooth-enabled fitness tracker",65,65,39.99);
//        Product p3 = new Product("Reusable Silicone Food Storage Bags","EcoFresh","Environmentally friendly food storage solution",40,40,19.99);
//        ArrayList<Product> myproducts = new ArrayList<>();
//        System.out.println(myproducts);
//        myproducts.add(p1);
//        myproducts.add(p2);
//        myproducts.add(p3);
//        Customer customer = new Customer("Sanketh", "edara@purdue.edu", "ILoveVirginia", myproducts, null);
//        ArrayList<Store> storesPurchasedFrom = marketplace.getStoresPurchasedFrom(customer);
//
//
//        //  Add assertions based on the expected stores purchased from by the customer
//        assertEquals("AudioElegance", storesPurchasedFrom); // Replace with the expected stores
//    }
//
//
//}
