import java.util.ArrayList;

/**
 * Store
 * <p>
 * represents a Store object
 *
 * @author Alan Kang, Priyanka Soe, Sanketh Edara, Arnav Sehgal, Eamon Mukhopadhyay
 * @version November 13, 2023
 */
public class Store {

    private ArrayList<Product> productList;

    private String sellerEmail;

    private String name;

    public Store(ArrayList<Product> productList, String sellerEmail, String name) {
        this.productList = productList;
        this.sellerEmail = sellerEmail;
        this.name = name;
    }

    public boolean updateProductQuantity(String productName, int quantitySold) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(productName)) {
                if (productList.get(i).getCurrQuantity() >= quantitySold) {
                    productList.get(i).setCurrQuantity(productList.get(i).getCurrQuantity() - quantitySold);
                    return true;
                }
            }
        }
        return false;
    }


    public ArrayList<Product> searchPrice(double priceThreshold) { // finds products above price threshold
        ArrayList<Product> byPrice = new ArrayList<Product>();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getPrice() >= priceThreshold) {
                byPrice.add(productList.get(i));
            }
        }

        return byPrice;
    }

    public ArrayList<Product> searchQuantity(double quantityThreshold) { // finds products above a quantity threshold
        ArrayList<Product> byQuantity = new ArrayList<Product>();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getPrice() >= quantityThreshold) {
                byQuantity.add(productList.get(i));
            }
        }

        return byQuantity;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Product> getProductList() {
        return this.productList;
    }

    public Product getProduct(String name) {
        for (Product p : productList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        System.out.println("This product doesn't exist in " + name);
        return null;
    }

    public String getSellerEmail() {
        return this.sellerEmail;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public void removeProduct(String productName) {
        for (Product p : productList) {
            if (p.getName().equals(productName)) {
                productList.remove(p);
            }
        }
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }


    public boolean equals(Object obj) { // Checks equality based off of name and productList
        if (obj instanceof Store) {
            Store other = (Store) obj;
            // note: does not account for the fact that two stores could have the same products in a different order
            return name.equals(other.getName()) && productList.equals(other.getProductList());
        }
        return false;
    }

    // statistics methods
    public String listCustomerData(ArrayList<Customer> customers) { // returns  customers that purchased from store
        String output = name + " Store Customer Data\n";
        for (Customer c : customers) {
            for (Product p : c.getPurchasedProducts()) {
                if (p.getStoreName().equals(name)) {
                    output += c.toString() + "\nNumber of items purchased: " + String.valueOf(p.getQuantityPurchased())
                            + "\n";
                }
            }
        }
        return output;
    }

    public int getQuantitySold() { // returns quantity of products sold
        int sold = 0;

        for (int i = 0; i < productList.size(); i++) {
            sold += productList.get(i).getInitialQuantity() - productList.get(i).getCurrQuantity();
        }

        return sold;
    }

    public String toString() {
        String output = "Store: " + name + "\nProducts: \n";
        for (Product p : productList) {
            output += p + "\n";
        }
        return output;
    }
}
