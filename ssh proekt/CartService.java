import java.util.ArrayList;
import java.util.List;
class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getName().equalsIgnoreCase(product.getName())) {
                item.increaseQuantity(quantity);
                System.out.println(quantity + " units of " + product.getName() + " added to cart.");
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
        System.out.println(quantity + " units of " + product.getName() + " added to cart.");
    }

    public void removeItem(String productName) {
        cartItems.removeIf(item -> item.getProduct().getName().equalsIgnoreCase(productName));
        System.out.println("Product " + productName + " removed from cart.");
    }

    public void displayCart() {
        System.out.println("\nShopping Cart:");
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            double totalAmount = 0;
            for (CartItem item : cartItems) {
                double itemTotal = item.getProduct().getPrice() * item.getQuantity();
                totalAmount += itemTotal;
                System.out.printf("Product: %s, Quantity: %d, Price per unit: $%.2f, Total: $%.2f\n",
                        item.getProduct().getName(), item.getQuantity(), item.getProduct().getPrice(), itemTotal);
            }
            System.out.printf("Total amount: $%.2f\n", totalAmount);
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}