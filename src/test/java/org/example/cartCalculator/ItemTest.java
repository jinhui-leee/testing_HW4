package org.example.cartCalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ItemTest {

    @Test
    public void testItemConstructorAndGetters() {
        // Given
        String expectedName = "Apple";
        double expectedPrice = 1.0;
        ItemType expectedType = ItemType.GROCERY;

        // When
        Item item = new Item(expectedName, expectedPrice, expectedType);

        // Then
        assertEquals(expectedName, item.getName());
        assertEquals(expectedPrice, item.getPrice(), 0.01);
        assertEquals(expectedType, item.getType());
    }

    @Test
    public void testItemSetters() {
        // Given
        Item item = new Item("Apple", 1.0, ItemType.GROCERY);

        // When
        String newName = "Banana";
        double newPrice = 1.5;
        ItemType newType = ItemType.GROCERY;
        item.setName(newName);
        item.setPrice(newPrice);
        item.setType(newType);

        // Then
        assertEquals(newName, item.getName());
        assertEquals(newPrice, item.getPrice(), 0.01);
        assertEquals(newType, item.getType());
    }
}