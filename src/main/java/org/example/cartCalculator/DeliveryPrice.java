package org.example.cartCalculator;

public class DeliveryPrice implements PriceRule {
    @Override
    public double priceToAggregate(ShoppingCart cart) {

        int totalItems = cart.numberOfItems();

        if (totalItems == 0) return 0;
        else if (totalItems >= 1 && totalItems <= 3) return 5;
        else if (totalItems >= 4 && totalItems <= 10) return 12.5;
        else return 20.0;
    }
}
