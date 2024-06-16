package org.example.cartCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class FinalPriceCalculatorTest {

    private ShoppingCart cart;
    private PriceRule deliveryPriceRule;
    private PriceRule extraChargeForElectronicsRule;
    private FinalPriceCalculator finalPriceCalculator;

    @Before
    public void setUp() {
        // ShoppingCart 및 PriceRule의 모의 객체 생성
        cart = mock(ShoppingCart.class);
        deliveryPriceRule = mock(PriceRule.class);
        extraChargeForElectronicsRule = mock(PriceRule.class);

        // 모의 객체를 사용하여 FinalPriceCalculator 생성
        finalPriceCalculator = new FinalPriceCalculator(Arrays.asList(deliveryPriceRule, extraChargeForElectronicsRule));
    }

    @After
    public void tearDown() {
        // 객체 초기화
        cart = null;
        deliveryPriceRule = null;
        extraChargeForElectronicsRule = null;
        finalPriceCalculator = null;
    }

    @Test
    public void testCalculate_NoItems_NoElectronics() {
        // 빈 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(0);
        when(cart.getItems()).thenReturn(Collections.emptyList());
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(0.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(0.0, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_OneToThreeItems_WithElectronics() {
        // 1~3개의 아이템이 있고 전자기기가 포함된 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(3);
        when(cart.getItems()).thenReturn(Collections.singletonList(new Item("Laptop", 1000.0, ItemType.ELECTRONIC)));
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(5.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(7.5);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(12.5, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_OneToThreeItems_NoElectronics() {
        // 1~3개의 아이템이 있고 전자기기가 없는 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(3);
        when(cart.getItems()).thenReturn(Collections.singletonList(new Item("Apple", 1.0, ItemType.GROCERY)));
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(5.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(5.0, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_FourToTenItems_WithElectronics() {
        // 4~10개의 아이템이 있고 전자기기가 포함된 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(4);
        when(cart.getItems()).thenReturn(Collections.singletonList(new Item("Laptop", 1000.0, ItemType.ELECTRONIC)));
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(12.5);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(7.5);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(20.0, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_FourToTenItems_NoElectronics() {
        // 4~10개의 아이템이 있고 전자기기가 없는 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(10);
        when(cart.getItems()).thenReturn(Collections.singletonList(new Item("Apple", 1.0, ItemType.GROCERY)));
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(12.5);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(12.5, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_ElevenOrMoreItems_WithElectronics() {
        // 11개 이상의 아이템이 있고 전자기기가 포함된 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(11);
        when(cart.getItems()).thenReturn(Collections.singletonList(new Item("Laptop", 1000.0, ItemType.ELECTRONIC)));
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(20.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(7.5);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(27.5, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_ElevenOrMoreItems_NoElectronics() {
        // 11개 이상의 아이템이 있고 전자기기가 없는 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(11);
        when(cart.getItems()).thenReturn(Collections.singletonList(new Item("Apple", 1.0, ItemType.GROCERY)));
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(20.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(20.0, finalPrice, 0.01);
    }
}