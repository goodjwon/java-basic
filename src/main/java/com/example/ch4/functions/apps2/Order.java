package com.example.ch4.functions.apps2;

public class Order {
    double price;     // 상품 가격
    int quantity;     // 구매 수량
    double discount;  // 할인 금액
    double shipping;  // 배송비
    int points;       // 적립 포인트

    public Order(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "Order{ price=%.2f, quantity=%d, discount=%.2f, shipping=%.2f, points=%d }",
                price, quantity, discount, shipping, points
        );
    }
}
