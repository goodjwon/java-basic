package com.example.ch4.functions.apps3;

import java.util.function.Function;

public class OrderService {
    private final OrderProperties properties;

    public OrderService(OrderProperties properties) {
        this.properties = properties;
    }

    // 도메인 객체의 내부 로직을 파이프라인 형태로 조합하여 주문 처리
    public Order processOrder(Order order) {
        Function<Order, Order> pipeline = ((Function<Order, Order>) (o -> o.applyDiscount(properties.getDiscountRate())))
                .andThen(o -> o.calculateShipping(properties.getBaseShippingFee(), properties.getPerItemShippingFee()))
                .andThen(o -> o.accumulatePoints(properties.getPointRate()));
        return pipeline.apply(order);
    }
}
