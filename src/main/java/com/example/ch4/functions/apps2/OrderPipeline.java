package com.example.ch4.functions.apps2;

import java.util.function.Function;

public class OrderPipeline {
    private final Function<Order, Order> pipeline;

    // 생성자에서 Function<Order, Order>를 주입받아 사용
    public OrderPipeline(Function<Order, Order> pipeline) {
        this.pipeline = pipeline;
    }

    // 파이프라인 적용
    public Order process(Order order) {
        return pipeline.apply(order);
    }
}
