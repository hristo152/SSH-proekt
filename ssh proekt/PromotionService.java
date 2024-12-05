class PromotionService {
    public double applyPromotion(String item, double amount) {
        if ("Laptop".equalsIgnoreCase(item)) {
            double discount = amount * 0.10;
            System.out.println("Promotion applied: 10% discount on " + item + ", amount reduced by $" + discount);
            return amount - discount;
        }
        return amount;
    }
}