package org.example.cartCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void testDeliveryPrice_NoItems() {
        when(cart.numberOfItems()).thenReturn(0);

        double price = deliveryPrice.priceToAggregate(cart);

        assertEquals(0.0, price, 0.01);
    }

    @Test
    public void testDeliveryPrice_OneToThreeItems() {
        when(cart.numberOfItems()).thenReturn(1);

        double price = deliveryPrice.priceToAggregate(cart);

        assertEquals(5.0, price, 0.01);

        when(cart.numberOfItems()).thenReturn(3);

        price = deliveryPrice.priceToAggregate(cart);

        assertEquals(5.0, price, 0.01);


    }

    @Test
    public void testDeliveryPrice_FourToTenItems() {
        when(cart.numberOfItems()).thenReturn(4);

        double price = deliveryPrice.priceToAggregate(cart);

        assertEquals(12.5, price, 0.01);

        when(cart.numberOfItems()).thenReturn(10);

        price = deliveryPrice.priceToAggregate(cart);

        assertEquals(12.5, price, 0.01);
    }

    @Test
    public void testDeliveryPrice_ElevenOrMoreItems() {
        when(cart.numberOfItems()).thenReturn(11);

        double price = deliveryPrice.priceToAggregate(cart);

        assertEquals(20.0, price, 0.01);

        when(cart.numberOfItems()).thenReturn(20);

        price = deliveryPrice.priceToAggregate(cart);

        assertEquals(20.0, price, 0.01);
    }

    @Test
    public void testExtraChargeForElectronics_NoElectronics() {
        Item groceryItem = new Item("Apple", 1.0, ItemType.GROCERY);
        when(cart.getItems()).thenReturn(Collections.singletonList(groceryItem));

        double price = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(0.0, price, 0.01);
    }

    @Test
    public void testExtraChargeForElectronics_WithElectronics() {
        Item electronicItem = new Item("Laptop", 1000.0, ItemType.ELECTRONIC);
        when(cart.getItems()).thenReturn(Collections.singletonList(electronicItem));

        double price = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(7.5, price, 0.01);
    }

    @Test
    public void testExtraChargeForElectronics_MixedItems() {
        Item electronicItem = new Item("Laptop", 1000.0, ItemType.ELECTRONIC);
        Item groceryItem = new Item("Apple", 1.0, ItemType.GROCERY);
        when(cart.getItems()).thenReturn(Arrays.asList(electronicItem, groceryItem));

        double price = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(7.5, price, 0.01);
    }

    // 새로운 테스트 케이스 추가
    @Test
    public void testExtraChargeForElectronics_NoItems() {
        // 장바구니에 아무 아이템도 없는 경우를 테스트합니다.
        when(cart.getItems()).thenReturn(Collections.emptyList());

        double price = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(0.0, price, 0.01);
    }







    // 새로운 테스트 케이스 추가
    @Test
    public void testExtraChargeForElectronics_MultipleElectronics() {
        // 전자기기가 여러 개 포함된 경우를 테스트합니다.
        Item electronicItem1 = new Item("Laptop", 1000.0, ItemType.ELECTRONIC);
        Item electronicItem2 = new Item("Smartphone", 500.0, ItemType.ELECTRONIC);
        when(cart.getItems()).thenReturn(Arrays.asList(electronicItem1, electronicItem2));

        double price = extraChargeForElectronics.priceToAggregate(cart);

        assertEquals(7.5, price, 0.01);
    }
}