package com.example.ch4.functions.apps1;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

// 주문 정보를 저장할 간단한 클래스
class Order {
    double price;     // 상품 가격
    int quantity;     // 구매 수량
    double discount;  // 할인 금액
    double shipping;  // 배송비
    int points;       // 적립 포인트

    // 테스트 편의상 생성자에 최소 정보만 받음
    Order(double price, int quantity) {
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

/**
 *
 간단한 주문( Order ) 객체를 예시로, 다음 과정을 거치는 Fluent 스타일 파이프라인을 구성
 주문 생성 (Supplier)
 할인 적용 (Function)
 배송비 계산 (Function)
 포인트 적립 (Function)
 최종 로그 출력 (Consumer)
 */
public class OrderPipelineExample {
    public static void main(String[] args) {
        // 1) 주문 생성 (Supplier)
        //    - 실제 환경에서는 사용자 입력, DB 조회 등으로 데이터를 가져온다고 가정
        Supplier<Order> orderSupplier = () -> new Order(10000.0, 5);

        // 2) 할인 적용 (Function)
        //    - 이전 예시 '상품 할인 계산'과 유사, 가격 * 수량에 할인율 10% 적용
        Function<Order, Order> applyDiscount = order -> {
            double rate = 0.1;
            double totalPrice = order.price * order.quantity;
            double discountAmount = totalPrice * rate;
            order.discount = discountAmount;
            return order; // 체이닝 위해 order 반환
        };

        // 3) 배송비 계산 (Function)
        //    - 이전 예시 '배송비 산정'과 유사, 기본 배송비 3000 + 개당 500
        Function<Order, Order> calculateShipping = order -> {
            int baseFee = 3000;
            int extraFeePerItem = 500;
            order.shipping = baseFee + (extraFeePerItem * order.quantity);
            return order;
        };

        // 4) 포인트 적립 (Function)
        //    - 이전 예시 '결제 금액 포인트 환산'과 유사
        //    - 여기서는 (상품 총액 - 할인 + 배송비)의 1% 포인트라고 가정
        Function<Order, Order> accumulatePoints = order -> {
            double totalPriceAfterDiscount = (order.price * order.quantity) - order.discount;
            double totalCost = totalPriceAfterDiscount + order.shipping;
            order.points = (int)(totalCost * 0.01);
            return order;
        };

        // 체이닝: applyDiscount -> calculateShipping -> accumulatePoints
        Function<Order, Order> orderPipeline =
                applyDiscount.andThen(calculateShipping).andThen(accumulatePoints);

        // 파이프라인 실행
        Order myOrder = orderSupplier.get();       // 새 주문 생성
        Order finalOrder = orderPipeline.apply(myOrder); // 단계별 로직 처리

        // 5) 최종 로그 출력 (Consumer)
        //    - 예시: 콘솔에 최종 주문 정보 출력
        Consumer<Order> logOrder = order -> {
            System.out.println("[최종 주문 정보] " + order);
        };

        // Consumer 실행
        logOrder.accept(finalOrder);
    }
}
