package com.example.ch4.functions.test;

import java.util.function.Function;

public class PaymentToPoint {
    public static void main(String[] args) {
        long paymentAmount = 15000;
        double rate = 0.01;

        // 단일 기능: 결제금액 -> 포인트 환산
        Function<Long, Integer> pointFunction = getLongIntegerFunction(rate);

        int points = pointFunction.apply(paymentAmount);
        System.out.println(points); // 기대 출력: 150
    }

    private static Function<Long, Integer> getLongIntegerFunction(double rate) {
        Function<Long, Integer> pointFunction =
                amount -> (int)(amount * rate);
        return pointFunction;
    }
}
