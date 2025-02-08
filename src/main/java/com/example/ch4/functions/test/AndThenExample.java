package com.example.ch4.functions.test;

import java.util.function.Function;

public class AndThenExample {
    public static void main(String[] args) {
        // 첫 번째 Function: 문자열을 대문자로 변환
        Function<String, String> toUpperCase = String::toUpperCase;

        // 두 번째 Function: 문자열의 길이 반환
        Function<String, Integer> getLength = s -> s.length();

        // 두 Function을 연결 (대문자로 변환한 후 길이 구하기)
        Function<String, Integer> combined = toUpperCase.andThen(getLength);

        System.out.println(combined.apply("Hello, Function!"));  // 출력: 17
    }
}
