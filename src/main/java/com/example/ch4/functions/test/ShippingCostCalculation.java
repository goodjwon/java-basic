package com.example.ch4.functions.test;

import java.util.function.Function;

public class ShippingCostCalculation {
    public static void main(String[] args) {
        int quantity = 5;
        int baseFee = 3000;
        int extraFeePerItem = 500;

        // 단일 기능: 배송비 계산
        Function<Integer, Integer> shippingCostFunction =
                qty -> baseFee + (extraFeePerItem * qty);

        int totalShippingCost = shippingCostFunction.apply(quantity);
        System.out.println(totalShippingCost); // 기대 출력: 5500
    }
}
