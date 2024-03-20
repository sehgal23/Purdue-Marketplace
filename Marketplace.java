import java.util.ArrayList;

/**
 * Marketplace
 * <p>
 * represents the marketplace and provides methods to search through its products
 *
 * @author Alan Kang, Priyanka Soe, Sanketh Edara, Arnav Sehgal, Eamon Mukhopadhyay
 * @version November 13, 2023
 */

public class Marketplace {

    private ArrayList<Seller> sellers;
    private ArrayList<Product> products;


    public Marketplace(ArrayList<Seller> sellers) {
        this.sellers = sellers;
        try {
            this.products = DataManager.readProductsFile("Products.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] listProducts() { // returns string array of all products in marketplace
        String[] listProducts = new String[products.size()];

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) != null) {
                listProducts[i] = products.get(i).toString();
            } else {
                listProducts[i] = null;
            }
        }

        return listProducts;
    }

    public String searchProducts(String productName) { // searches by product name
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(productName)) {
                return products.get(i).getName();
            }
        }
        return "Error: Product Not Found";
    }

    public ArrayList<Product> productsContaining(String keyword) { // Finds all products containing a keyword
        ArrayList<Product> list = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                list.add(product);
            }
        }
        return list;
    }

    public String[] sortProductsByPrice() { // sorts products from low to high price
        int n = products.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (products.get(j).getPrice() > products.get(j + 1).getPrice()) {
                    // Swap products
                    Product temp = products.get(j);
                    products.set(j, products.get(j + 1));
                    products.set(j + 1, temp);
                }
            }
        }

        // remove all products w/ curr quantity of 0 in display
        ArrayList<Product> temp = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCurrQuantity() != 0) {
                temp.add(products.get(i));
            }
        }

        String[] sortedProducts = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            sortedProducts[i] = temp.get(i).toString();
        }

        return sortedProducts;
    }

    public String[] sortProductsByQuantity() {
        int n = products.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (products.get(j).getCurrQuantity() > products.get(j + 1).getCurrQuantity()) {
                    // Swap products
                    Product temp = products.get(j);
                    products.set(j, products.get(j + 1));
                    products.set(j + 1, temp);
                }
            }
        }

        // remove all products w/ curr quantity of 0 in display
        ArrayList<Product> temp = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCurrQuantity() != 0) {
                temp.add(products.get(i));
            }
        }

        String[] sortedProducts = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            sortedProducts[i] = temp.get(i).toString();
        }

        return sortedProducts;
    }

    public String[] searchByStore(String storeName) {
        ArrayList<String> matchingProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getCurrQuantity() != 0) {
                if (product.getStoreName().equalsIgnoreCase(storeName)) {
                    matchingProducts.add(product.toString());
                }
            }
        }

        return matchingProducts.toArray(new String[0]);
    }

    public String[] searchByPrice(double threshold, boolean higher) {
        ArrayList<String> matchingProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getCurrQuantity() != 0) {
                if (higher && product.getPrice() > threshold) {
                    matchingProducts.add(product.toString());
                } else if (!higher && product.getPrice() < threshold) {
                    matchingProducts.add(product.toString());
                }
            }
        }

        return matchingProducts.toArray(new String[0]);
    }

    public ArrayList<Store> getStoresPurchasedFrom(Customer customer) {
        ArrayList<Store> stores = new ArrayList<>();

        if (customer.getPurchasedProducts() != null) {
            for (Product product : customer.getPurchasedProducts()) {
                for (Seller seller : sellers) {
                    for (Store store : seller.getStoreList()) {
                        if (store.getName().equals(product.getStoreName()) && !stores.contains(store)) {
                            stores.add(store);
                        }
                    }
                }
            }
            return stores;
        }
        return null;
    }

    public ArrayList<Store> getStores() {
        ArrayList<Store> stores = new ArrayList<>();
        for (Seller seller : sellers) {
            stores.addAll(seller.getStoreList());
        }
        return stores;
    }

    public ArrayList<Store> getStoreByProductSold() {

        ArrayList<Store> stores = new ArrayList<>();

        //puts all the stores in an arraylist
        for (int i = 0; i < sellers.size(); i++) {
            for (int j = 0; j < sellers.get(i).getStoreList().size(); j++) {
                stores.add(sellers.get(i).getStoreList().get(j));
            }
        }

        //Sorts the Stores Based on Quantity Sold from Lowest to Highest
        Store temp;
        for (int z = 0; z < stores.size(); z++) {
            for (int y = z + 1; y < stores.size(); y++) {
                if (stores.get(y).getQuantitySold() < stores.get(z).getQuantitySold()) {
                    temp = stores.get(z);
                    stores.set(z, stores.get(y));
                    stores.set(y, temp);
                }
            }
        }

        return stores;
    }


}
