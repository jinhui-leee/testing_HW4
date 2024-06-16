package org.example.cartCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class PriceRuleTest {

    private ShoppingCart cart;
    private DeliveryPrice deliveryPrice;
    private ExtraChargeForElectronics extraChargeForElectronics;

    @Before
    public void setUp() {
        cart = mock(ShoppingCart.class);
        deliveryPrice = new DeliveryPrice();
        extraChargeForElectronics = new ExtraChargeForElectronics();
    }

    @After
    public void tearDown() {
        // 객체 초기화
        cart = null;
        deliveryPrice = null;
        extraChargeForElectronics = null;
    }

    @Test
    public void testCase1_NoItems_NoElectronics() {
        when(cart.numberOfItems()).thenReturn(0);
        when(cart.getItems()).thenReturn(Collections.emptyList());

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(0.0, deliveryPriceResult, 0.01);
        assertEquals(0.0, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase2_OneItem_WithElectronics() {
        Item electronicItem = new Item("Laptop", 1000.0, ItemType.ELECTRONIC);
        when(cart.numberOfItems()).thenReturn(1);
        when(cart.getItems()).thenReturn(Collections.singletonList(electronicItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(5.0, deliveryPriceResult, 0.01);
        assertEquals(7.5, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase3_ThreeItems_NoElectronics() {
        Item groceryItem = new Item("Apple", 1.0, ItemType.GROCERY);
        when(cart.numberOfItems()).thenReturn(3);
        when(cart.getItems()).thenReturn(Collections.singletonList(groceryItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(5.0, deliveryPriceResult, 0.01);
        assertEquals(0.0, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase4_FourItems_WithElectronics() {
        Item electronicItem = new Item("Laptop", 1000.0, ItemType.ELECTRONIC);
        when(cart.numberOfItems()).thenReturn(4);
        when(cart.getItems()).thenReturn(Collections.singletonList(electronicItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(12.5, deliveryPriceResult, 0.01);
        assertEquals(7.5, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase5_TenItems_NoElectronics() {
        Item groceryItem = new Item("Apple", 1.0, ItemType.GROCERY);
        when(cart.numberOfItems()).thenReturn(10);
        when(cart.getItems()).thenReturn(Collections.singletonList(groceryItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(12.5, deliveryPriceResult, 0.01);
        assertEquals(0.0, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase6_ElevenItems_WithElectronics() {
        Item electronicItem = new Item("Laptop", 1000.0, ItemType.ELECTRONIC);
        when(cart.numberOfItems()).thenReturn(11);
        when(cart.getItems()).thenReturn(Collections.singletonList(electronicItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(20.0, deliveryPriceResult, 0.01);
        assertEquals(7.5, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase7_ElevenItems_NoElectronics() {
        Item groceryItem = new Item("Apple", 1.0, ItemType.GROCERY);
        when(cart.numberOfItems()).thenReturn(11);
        when(cart.getItems()).thenReturn(Collections.singletonList(groceryItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(20.0, deliveryPriceResult, 0.01);
        assertEquals(0.0, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase8_FourToTenItems_NoElectronics() {
        Item groceryItem = new Item("Apple", 1.0, ItemType.GROCERY);
        when(cart.numberOfItems()).thenReturn(6);
        when(cart.getItems()).thenReturn(Collections.singletonList(groceryItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(12.5, deliveryPriceResult, 0.01);
        assertEquals(0.0, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase9_TwoItems_NoElectronics() {
        Item groceryItem = new Item("Apple", 1.0, ItemType.GROCERY);
        when(cart.numberOfItems()).thenReturn(2);
        when(cart.getItems()).thenReturn(Collections.singletonList(groceryItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(5.0, deliveryPriceResult, 0.01);
        assertEquals(0.0, electronicsChargeResult, 0.01);
    }

    @Test
    public void testCase10_ThreeItems_WithElectronics() {
        Item electronicItem = new Item("Laptop", 1000.0, ItemType.ELECTRONIC);
        when(cart.numberOfItems()).thenReturn(3);
        when(cart.getItems()).thenReturn(Collections.singletonList(electronicItem));

        double deliveryPriceResult = deliveryPrice.priceToAggregate(cart);
        double electronicsChargeResult = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(5.0, deliveryPriceResult, 0.01);
        assertEquals(7.5, electronicsChargeResult, 0.01);
    }
}