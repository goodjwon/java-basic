package com.example.ch3.hw.alog;

public class ShoppingCartCalculator {
    public static void main(String[] args) {
        double[][] cart = {
                {100.0, 2},  // 상품 가격 100, 수량 2
                {50.0, 1},   // 상품 가격 50, 수량 1
                {200.0, 1}   // 상품 가격 200, 수량 1
        };
        double discountRate = 10.0;

        double finalPrice = calculateFinalPrice(cart, discountRate);
        System.out.printf("최종 금액: %.2f%n", finalPrice);
    }

    public static double calculateFinalPrice(double[][] cart, double discountRate) {
        double total = 0;
        for (double[] item : cart) {
            total += item[0] * item[1];
        }
        return total * (1 - discountRate / 100);
    }
}
