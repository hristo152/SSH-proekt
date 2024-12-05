import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderFacade orderFacade = new OrderFacade();
        String customerId, password, response, item;
        int quantity;

        System.out.print("Enter your customer ID for registration: ");
        customerId = scanner.nextLine();
        do {
            System.out.print("Enter a password for registration: ");
            password = scanner.nextLine();
        } while (!orderFacade.getCustomerVerificationService().registerCustomer(customerId, password));

        System.out.print("Enter your customer ID for login: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter your password for login: ");
        String loginPassword = scanner.nextLine();

        if (orderFacade.getCustomerVerificationService().verifyCustomer(loginId, loginPassword)) {
            orderFacade.displayProducts();

            while (true) {
                Product selectedProduct = null;

                while (true) {
                    System.out.print("Enter the product you want to add to your cart (or 'checkout' to finish): ");
                    item = scanner.nextLine();
                    if (item.equalsIgnoreCase("checkout")) {
                        if (orderFacade.getCartService().getCartItems().isEmpty()) {
                            System.out.println("Your cart is empty. Please add products before checking out.");
                            continue;
                        }
                        break;
                    } else if (!orderFacade.getProductService().getProductCatalog().containsKey(item)) {
                        System.out.println("The product does not exist. Please enter a valid product.");
                        continue;
                    }
                    selectedProduct = orderFacade.getProductService().getProductCatalog().get(item);
                    if (selectedProduct.getStock() == 0) {
                        System.out.println("The selected product is out of stock. Please choose another product.");
                        continue;
                    }
                    break;
                }
                do {
                    System.out.println("Enter quantity: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next();
                    }

                    quantity = scanner.nextInt();
                    scanner.nextLine();

                    if (quantity > selectedProduct.getStock()) {
                        System.out.println("Quantity is too big.");
                        System.out.println("Available quantity: " + selectedProduct.getStock());
                    } else if (quantity <= 0) {
                        System.out.println("Please enter a positive number for the quantity.");
                    }
                } while (quantity > selectedProduct.getStock() || quantity <= 0);
                orderFacade.addToCart(item, quantity);
                System.out.println("Product added to cart: " + item + " (Quantity: " + quantity + ")");
                orderFacade.getCartService().displayCart();

                System.out.print("Would you like to add another product? (yes/no): ");
                String addMore = scanner.nextLine();
                if (addMore.equalsIgnoreCase("no")) {
                    break;
                }
            }
            System.out.print("Would you like to place the order? (yes/no): ");
            do {
                response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    orderFacade.placeOrder(loginId, loginPassword);
                    System.out.println("Order placed successfully.");
                    break;
                } else if (response.equalsIgnoreCase("no")) {
                    System.out.println("Order cancelled.");
                    break;
                } else {
                    System.out.println("Please choose between (yes/no).");
                }
            } while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no"));

        } else {
            System.out.print("Login failed. Please check your credentials.");
        }
        scanner.close();
    }
}
