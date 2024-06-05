package org.example.cartCalculator;

public interface PriceRule {
    double priceToAggregate(ShoppingCart cart);
}
