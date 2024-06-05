package org.example.cartCalculator;

import java.util.List;

public class ShoppingCart {

    private List<Item> items;

    public ShoppingCart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int numberOfItems() {
        return items.size();
    }
}
