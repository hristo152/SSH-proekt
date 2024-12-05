class InventoryService {
    private ProductService productService;

    public InventoryService(ProductService productService) {
        this.productService = productService;
    }

    public boolean checkAvailability(String item, int quantity) {
        Product product = productService.getProduct(item);
        if (product != null) {
            if (product.getStock() >= quantity) {
                System.out.println("Item is available in inventory: " + item + ", Quantity available: " + product.getStock());
                return true;
            } else {
                System.out.println("Not enough stock available for: " + item + ". Only " + product.getStock() + " left.");
                return false;
            }
        } else {
            System.out.println("Item not found in inventory: " + item);
            return false;
        }
    }
}