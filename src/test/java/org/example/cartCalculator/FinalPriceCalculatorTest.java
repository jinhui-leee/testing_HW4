package org.example.cartCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void testCalculate_NoItems() {
        // 빈 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(0);
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(0.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(0.0, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_OneToThreeItems() {
        // 1~3개의 아이템이 있는 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(3);
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(5.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(5.0, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_FourToTenItems() {
        // 4~10개의 아이템이 있는 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(4);
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(12.5);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(12.5, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_ElevenOrMoreItems() {
        // 11개 이상의 아이템이 있는 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(11);
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(20.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(0.0);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(20.0, finalPrice, 0.01);
    }

    @Test
    public void testCalculate_WithElectronics() {
        // 전자기기가 포함된 장바구니에 대한 동작을 모의 객체로 설정
        when(cart.numberOfItems()).thenReturn(2);
        when(deliveryPriceRule.priceToAggregate(cart)).thenReturn(5.0);
        when(extraChargeForElectronicsRule.priceToAggregate(cart)).thenReturn(7.5);

        // 최종 가격 계산
        double finalPrice = finalPriceCalculator.calculate(cart);

        // 최종 가격 검증
        assertEquals(12.5, finalPrice, 0.01);
    }
}