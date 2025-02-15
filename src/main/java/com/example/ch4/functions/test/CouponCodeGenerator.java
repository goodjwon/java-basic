package com.example.ch4.functions.test;

import java.util.function.Supplier;
import java.util.Random;

public class CouponCodeGenerator {
    public static void main(String[] args) {
        // 단일 기능: 랜덤 쿠폰 코드 생성
        Supplier<String> couponSupplier = () -> {
            Random random = new Random();
            // 간단한 형식 예시
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append((char)('A' + random.nextInt(26)));
            }
            sb.append("-");
            for (int i = 0; i < 4; i++) {
                sb.append(random.nextInt(10));
            }
            return sb.toString();
        };

        String couponCode = couponSupplier.get();
        System.out.println(couponCode);
    }
}
