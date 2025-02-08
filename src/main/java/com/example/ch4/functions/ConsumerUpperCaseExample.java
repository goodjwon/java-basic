package com.example.ch4.functions;

import java.util.function.Consumer;

public class ConsumerUpperCaseExample {
    public static void main(String[] args) {
        // 첫 번째 Consumer: 대문자로 변환
        Consumer<String> toUpperCase = s -> {
            String result = s.toUpperCase();
            System.out.println("Uppercase: " + result);
        };

        // 두 번째 Consumer: 변환된 문자열 출력
        Consumer<String> print = s -> System.out.println("Original input: " + s);

        // Consumer 연결
        Consumer<String> combined = toUpperCase.andThen(print);

        // 입력 문자열 처리
        combined.accept("hello, functional interface");
    }
}
