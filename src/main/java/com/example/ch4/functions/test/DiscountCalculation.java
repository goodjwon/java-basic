package com.example.ch4.functions.test;

import java.util.function.Function;

public class DiscountCalculation {
    public static void main(String[] args) {
        double originalPrice = 10000.0;
        double discountRate = 0.1;

        // 단일 기능: 할인 계산
        Function<Double, Double> discountFunction = price -> price - (price * discountRate);

        // 단일 기능: 소수점 정리 (예시: 반올림)
        Function<Double, Double> roundFunction = val -> Math.round(val * 100) / 100.0;
        // 올림 double roundedUp = Math.ceil(val * 100.0) / 100.0;
        // 내림 double roundedDown = Math.floor(val * 100.0) / 100.0;

        // 함수 체이닝: 할인 후 소수점 정리
        Function<Double, Double> finalCalculation = discountFunction.andThen(roundFunction);

        System.out.println(discountFunction.apply(originalPrice));

        double result = finalCalculation.apply(originalPrice);
        System.out.println(result); // 기대 출력: 9000.0
    }
}
