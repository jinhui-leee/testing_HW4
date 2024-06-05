package org.example.cartCalculator;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTest {

    @Test
    public void testNumberOfItems_EmptyCart() {
        // Given an empty cart
        ShoppingCart cart = new ShoppingCart(new ArrayList<>());

        // When getting the number of items
        int numberOfItems = cart.numberOfItems();

        // Then the number of items should be 0
        assertEquals(0, numberOfItems);
    }

    @Test
    public void testNumberOfItems_NonEmptyCart() {
        // Given a cart with items
        List<Item> items = new ArrayList<>();
        items.add(new Item("Apple", 1.0, ItemType.GROCERY));
        items.add(new Item("Laptop", 1000.0, ItemType.ELECTRONIC));
        ShoppingCart cart = new ShoppingCart(items);

        // When getting the number of items
        int numberOfItems = cart.numberOfItems();

        // Then the number of items should be equal to the number of items in the cart
        assertEquals(items.size(), numberOfItems);
    }

    @Test
    public void testNumberOfItems_AdditionalItems() {
        // Given a cart with additional items added later
        List<Item> items = new ArrayList<>();
        items.add(new Item("Apple", 1.0, ItemType.GROCERY));
        ShoppingCart cart = new ShoppingCart(items);

        // When adding additional items to the cart
        items.add(new Item("Laptop", 1000.0, ItemType.ELECTRONIC));
        items.add(new Item("T-shirt", 20.0, ItemType.CLOTHING));
        cart.setItems(items);

        // When getting the number of items
        int numberOfItems = cart.numberOfItems();

        // Then the number of items should be equal to the total number of items in the cart
        assertEquals(items.size(), numberOfItems);
    }

    @Test
    public void testGetItems() {
        // Given
        Item item1 = new Item("Item1", 10.0, ItemType.GROCERY);
        Item item2 = new Item("Item2", 20.0, ItemType.CLOTHING);
        ShoppingCart cart = new ShoppingCart(List.of(item1, item2));

        // When
        List<Item> items = cart.getItems();

        // Then
        assertNotNull(items);
        assertEquals(2, items.size());
        assertEquals("Item1", items.get(0).getName());
        assertEquals(10.0, items.get(0).getPrice(), 0.01);
        assertEquals(ItemType.GROCERY, items.get(0).getType());
        assertEquals("Item2", items.get(1).getName());
        assertEquals(20.0, items.get(1).getPrice(), 0.01);
        assertEquals(ItemType.CLOTHING, items.get(1).getType());
    }
}