import java.util.ArrayList;

/**
 * Seller
 * <p>
 * represents a Seller object
 *
 * @author Alan Kang, Priyanka Soe, Sanketh Edara, Arnav Sehgal, Eamon Mukhopadhyay
 * @version November 13, 2023
 */
public class Seller {
    private String name;
    private String email;
    private String password;
    private ArrayList<Store> storeList;

    /*
     * Create new seller (initialize store list to empty arraylist)
     * @param email - seller's email
     * @param password - seller's password
     */
    public Seller(String name, String email, String password) {
        if (name == null || email == null || password == null) {   // handle exceptions
            throw new NullPointerException();
        }
        this.name = name;
        this.email = email;
        this.password = password;
        storeList = new ArrayList<>();
    }

    /*
     * Create new seller (with stores initialized)
     * @param email - seller's email
     * @param password - seller's password
     * @param storeList - arraylist of seller's stores
     */
    public Seller(String name, String email, String password, ArrayList<Store> storeList) {
        if (name == null || email == null || password == null) {   // handle exceptions
            throw new NullPointerException();
        }
        this.name = name;
        this.email = email;
        this.password = password;
        this.storeList = storeList;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Store> getStoreList() {
        return storeList;
    }

    public ArrayList<String> getStoreNames() { // Returns the names of the stores in ArrayList stores
        ArrayList<String> names = new ArrayList<>();
        for (Store store : storeList) {
            names.add(store.getName());
        }
        return names;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStoreList(ArrayList<Store> storeList) {
        this.storeList = storeList;
    }

    /*
     * Allows a seller to add a product
     * @param product - the Product to add
     * @return true if successfully added the product; false if did not
     */
    public boolean addProduct(Product product) {
        for (Store s : storeList) {
            if (s.getName().equals(product.getStoreName())) {   // check which store the seller wants the product in
                Product currProduct = s.getProduct(product.getName());
                if (currProduct != null) {    // if the product is already in the store, add to the quantity
                    currProduct.setCurrQuantity(currProduct.getCurrQuantity() + product.getCurrQuantity());
                    // add to both initial and current quantity (so the "purchased quantity will stay the same")
                    currProduct.setInitialQuantity(currProduct.getCurrQuantity() + product.getCurrQuantity());
                    return true;
                } else {
                    s.addProduct(product);   // if product is not in the store, add the product
                    return true;
                }
            }
        }
        return false;
    }


    /*
     * Allows a seller to add a new store
     * @param store - the Store to add
     * @return true if successfully added the store; false if did not
     */
    public boolean addStore(Store store) {
        for (Store s : storeList) {  // make sure the store does not already exist
            if (s.getName().equals(store.getName())) {
                return false;
            }
        }
        storeList.add(store);
        return true;
    }

    public boolean removeStore(Store store) { // Removes store from storeList
        for (Store s : storeList) {  // make sure the store already exists
            if (s.getName().equals(store.getName())) {
                storeList.remove(s);
                return true;
            }
        }
        return false;
    }

    public boolean equals(Seller c) { // Determines equality based on name, email, and password
        return c.getName().equals(name) && c.getEmail().equals(email) && c.getPassword().equals(password);

    }

    public String viewStores() { // Returns names of each store in storeList
        String output = name + "'s Stores: \n";
        for (Store s : storeList) {
            output += s.toString() + "\n";
        }
        return output;
    }

    public String viewProducts() { // Returns toString of all products in storeList
        String output = name + "'s Products: \n";
        for (Store s : storeList) {
            for (Product p : s.getProductList()) {
                output += p.toString() + "\n";
            }
        }
        return output;
    }

    public String toText() { // returns text representation of the Seller object to be used in a .txt file
        String storeListText = "";
        for (int i = 0; i < storeList.size(); i++) {
            storeListText += storeList.get(i).getName();
            if (i != storeList.size() - 1) {
                storeListText += ";";
            }
        }

        return String.format("%s,%s,%s,%s", name, email, password, storeListText);
    }


}
