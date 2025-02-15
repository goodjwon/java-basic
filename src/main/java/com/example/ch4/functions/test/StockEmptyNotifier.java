package com.example.ch4.functions.test;

import java.util.function.Consumer;

public class StockEmptyNotifier {
    public static void main(String[] args) {
        String emptySku = "SKU12345";

        // 단일 기능: 재고 소진 알림 발행
        Consumer<String> notifyConsumer = sku -> {
            // 실제 알림 서비스 호출 가정
            System.out.println("재고 소진 알림 발행: " + sku);
        };

        // 체이닝 예: 발행 후 로깅
        Consumer<String> logConsumer = sku -> {
            System.out.println("추가 로그: " + sku + " 알림 완료");
        };

        Consumer<String> combined = notifyConsumer.andThen(logConsumer);
        combined.accept(emptySku);
    }
}
