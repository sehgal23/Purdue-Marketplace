import java.util.ArrayList;

public class Customer {
    private String name;
    private String email;
    private String password;
    private ArrayList<Product> purchasedProducts;
    private ArrayList<Product> shoppingCart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasedProducts(ArrayList<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Customer(String name, String email, String password) {
        if (name == null || email == null || password == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.email = email;
        this.password = password;
        this.purchasedProducts = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
    }

    public Customer(String name, String email, String password, ArrayList<Product> purchasedProducts,
                    ArrayList<Product> shoppingCart) {
        if (name == null || email == null || password == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.email = email;
        this.password = password;
        this.purchasedProducts = (purchasedProducts == null ? new ArrayList<>() : purchasedProducts);
        this.shoppingCart = (shoppingCart == null ? new ArrayList<>() : shoppingCart);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public String purchasedProductsToString() {
        String output = name + "'s Purchased Products\n";
        for (Product product : purchasedProducts) {
            output += product.toString() + "\n";
        }
        return output;
    }

    public String shoppingCartToString() {
        String output = name + "'s Shopping Cart\n";
        for (Product product : shoppingCart) {
            output += product.toString() + "\n";
        }
        return output;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean purchaseProduct(Product product) {
        boolean purchased = false;
        if (purchasedProducts != null) {
            for (Product p : purchasedProducts) {
                if (p.getName().equals(product.getName())) {
                    p.setCurrQuantity(p.getCurrQuantity() + product.getCurrQuantity());
                    purchased = true;
                }
            }
            if (!purchased) {
                purchasedProducts.add(product);
            }
        } else {
            purchasedProducts = new ArrayList<>();
            purchasedProducts.add(product);
        }
        return true;
    }

    public boolean addToCart(Product product) {
        for (Product p : shoppingCart) {
            if (p.equals(product)) {
                p.setCurrQuantity(p.getCurrQuantity() + product.getCurrQuantity());
                return true;
            }
        }
        shoppingCart.add(product);
        return true;
    }

    public String viewPurchaseHistory() {
        String output = name + "'s Purchase History:\n";
        for (Product p : purchasedProducts) {
            output += p.toString() + "\n";
        }
        return output;
    }

    public boolean removeFromCart(Product productToRemove) {
        for (Product p : shoppingCart) {
            if (p.equals(productToRemove)) {
                shoppingCart.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean removeFromCart(String productName) {
        for (Product p : shoppingCart) {
            if (p.getName().equals(productName)) {
                shoppingCart.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean boughtFrom(Seller s) {
        for (Product p : purchasedProducts) {
            for (Store store : s.getStoreList()) {
                if (p.getStoreName().equals(store.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumItemsPurchasedFrom(Seller s) {
        int itemsPurchasedFrom = 0;
        for (Product p : purchasedProducts) {
            for (Store store : s.getStoreList()) {
                if (p.getStoreName().equals(store.getName())) {
                    itemsPurchasedFrom++;
                }
            }
        }
        return itemsPurchasedFrom;
    }

    public boolean equals(Customer c) { // checks if name, email, and password are the same
        return c.getName().equals(name) && c.getEmail().equals(email) && c.getPassword().equals(password);
    }

    public String toString() {
        return "Customer{email:" + email + ", password:" + password + ", purchasedProducts:" +
                purchasedProductsToString() + ", shoppingCart:" + shoppingCartToString() + "}";
    }

    public String toText() {
        String purchasedProductsText = "";
        for (int i = 0; i < purchasedProducts.size(); i++) {
            purchasedProductsText += purchasedProducts.get(i).getName() + "-" + purchasedProducts.get(i)
                    .getCurrQuantity();
            if (i != purchasedProducts.size() - 1) {
                purchasedProductsText += ";";
            }
        }
        String shoppingCartText = "";
        for (int i = 0; i < shoppingCart.size(); i++) {
            shoppingCartText += shoppingCart.get(i).getName() + "-" + shoppingCart.get(i).getCurrQuantity();
            if (i != shoppingCart.size() - 1) {
                shoppingCartText += ";";
            }
        }

        if (purchasedProductsText.isEmpty()) {
            purchasedProductsText = "null";
        }
        if (shoppingCartText.isEmpty()) {
            shoppingCartText = "null";
        }
        return String.format("%s,%s,%s,%s,%s", name, email, password, purchasedProductsText, shoppingCartText);
    }
}
