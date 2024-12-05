import java.util.HashMap;
import java.util.Map;
class ProductService {
    private Map<String, Product> productCatalog = new HashMap<>();

    public ProductService() {
        productCatalog.put("Laptop", new Product("Laptop", 10, 1000.0));
        productCatalog.put("Smartphone", new Product("Smartphone", 5, 500.0));
        productCatalog.put("Tablet", new Product("Tablet", 0, 300.0));
        productCatalog.put("Headphones", new Product("Headphones", 20, 50.0));
    }

    public void displayProducts() {
        System.out.println("\nAvailable products:");
        for (Product product : productCatalog.values()) {
            String availability = product.getStock() > 0 ? "In stock" : "Out of stock";
            System.out.printf("Product: %s, Price: $%.2f, Quantity: %d, Status: %s\n",
                    product.getName(), product.getPrice(), product.getStock(), availability);
        }
    }

    public Product getProduct(String name) {
        return productCatalog.get(name);
    }

    public Map<String, Product> getProductCatalog() {
        return productCatalog;
    }
}