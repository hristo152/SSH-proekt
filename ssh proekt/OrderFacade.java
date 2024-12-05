import java.util.List;
class OrderFacade {
    private CustomerVerificationService customerVerificationService;
    private ProductService productService;
    private InventoryService inventoryService;
    private CartService cartService;
    private PromotionService promotionService;
    private PaymentGateway paymentGateway;
    private ShippingService shippingService;

    public OrderFacade() {
        this.customerVerificationService = new CustomerVerificationService();
        this.productService = new ProductService();
        this.inventoryService = new InventoryService(productService);
        this.cartService = new CartService();
        this.promotionService = new PromotionService();
        this.paymentGateway = new PaymentGateway();
        this.shippingService = new ShippingService();
    }

    public CartService getCartService() {
        return cartService;
    }

    public CustomerVerificationService getCustomerVerificationService() {
        return customerVerificationService;
    }
   public ProductService getProductService() { // Нов метод
        return productService;
    }

    public void displayProducts() {
        productService.displayProducts();
    }

    public void addToCart(String item, int quantity) {
        Product product = productService.getProduct(item);
        if (product != null && product.getStock() >= quantity) {
            cartService.addItem(product, quantity);
        } else {
            System.out.println("Product not available or insufficient stock.");
        }
    }

    public void displayCart() {
        cartService.displayCart();
    }

    public void placeOrder(String customerId, String password) {
        if (customerVerificationService.verifyCustomer(customerId, password)) {
            List<CartItem> cartItems = cartService.getCartItems();
            double totalAmount = 0;
            for (CartItem item : cartItems) {
                double itemTotal = item.getProduct().getPrice() * item.getQuantity();
                totalAmount += itemTotal;
            }
            double finalAmount = promotionService.applyPromotion("Laptop", totalAmount);
            if (paymentGateway.processPayment(finalAmount)) {
                for (CartItem item : cartItems) {
                    item.getProduct().decreaseStock(item.getQuantity());
                    shippingService.shipItem(item.getProduct().getName());
                }
                System.out.println("Order placed successfully! Final amount: $" + finalAmount);
            } else {
                System.out.println("Payment failed.");
            }
        } else {
            System.out.println("Order cannot be placed. Customer verification failed.");
        }
    }
}