package com.example.ch4.functions.test;

import java.util.function.Function;

public class StockCheck {
    public static void main(String[] args) {
        int currentStock = 2;
        int requiredStock = 5;

        // 단일 기능: 재고 수량 -> 부족 여부
        Function<Integer, Boolean> isStockInsufficient =
                stock -> stock < requiredStock;

        boolean result = isStockInsufficient.apply(currentStock);
        System.out.println(result); // 기대 출력: true
    }
}
