package com.example.ch4.functions.test;

import java.util.function.Supplier;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumberSupplier {
    public static void main(String[] args) {
        // 단일 기능: 주문번호 생성
        Supplier<String> orderNumberSupplier = () -> {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            return "ORDER-" + timestamp;
        };

        String orderNumber = orderNumberSupplier.get();
        System.out.println(orderNumber); // 예) ORDER-20250201123045
    }
}
