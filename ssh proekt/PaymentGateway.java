class PaymentGateway {
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount);
        return amount > 0;
    }
}