/**
 * Product
 * <p>
 * represents a Product object
 *
 * @author Alan Kang, Priyanka Soe, Sanketh Edara, Arnav Sehgal, Eamon Mukhopadhyay
 * @version November 13, 2023
 */
public class Product {

    private String name;
    private String storeName;
    private String description;
    private int initialQuantity;
    private int currQuantity;
    private double price;

    public Product(String name, String storeName, String description, int quantity, double price) {
        this.name = name;
        this.storeName = storeName;
        this.description = description;
        this.initialQuantity = quantity;
        this.currQuantity = quantity;
        this.price = price;
    }

    public Product(String name, String storeName, String description, int initialQuantity, int currQuantity,
                   double price) {
        this.name = name;
        this.storeName = storeName;
        this.description = description;
        this.initialQuantity = initialQuantity;
        this.currQuantity = currQuantity;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCurrQuantity() {
        return this.currQuantity;
    }

    public int getInitialQuantity() {
        return this.initialQuantity;
    }

    public int getQuantityPurchased() {
        return initialQuantity - currQuantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setInitialQuantity(int quantity) {
        this.initialQuantity = quantity;
    }

    public void setCurrQuantity(int quantity) {
        this.currQuantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public boolean equals(Object obj) { // checks equality based on name, description, price
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return name.equals(other.getName()) && description.equals(other.getDescription()) &&
                    price == other.getPrice();  // quantity doesn't have to be the same
        }
        return false;
    }

    public String toString() {
        return "Product{name: [" + name + "], storeName: [" + storeName + "], description: [" + description + "], " +
                "quantity: [" +
                String.valueOf(currQuantity) + "], price: " + String.valueOf(price) + "]}";
    }

    public String toStringWoQuantity() {
        return "Product{name: [" + name + "], storeName: [" + storeName + "], description: [" + description + "], " +
                "price: [" + String.valueOf(price) + "]}";
    }
}
