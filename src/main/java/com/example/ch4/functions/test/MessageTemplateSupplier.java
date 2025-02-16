package com.example.ch4.functions.test;

import java.util.function.Supplier;

public class MessageTemplateSupplier {
    public static void main(String[] args) {
        // 단일 기능: 환영 메시지 텍스트 반환
        Supplier<String> welcomeMessageSupplier = () ->
                "안녕하세요, 서비스를 이용해 주셔서 감사함";

        String message = welcomeMessageSupplier.get();
        System.out.println(message);
    }
}
