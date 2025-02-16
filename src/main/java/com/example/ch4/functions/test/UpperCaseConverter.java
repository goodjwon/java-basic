package com.example.ch4.functions.test;

import java.util.function.Function;

public class UpperCaseConverter {
    public static void main(String[] args) {
        String input = "hello world  ".trim();
        System.out.println(input);

        // 단일 기능: 대문자 변환
        Function<String, String> upperCaseFunction =
                str -> str.toUpperCase();

        // 예시로 공백 제거를 추가한 후 체이닝
        Function<String, String> removeSpacesFunction =
                str -> str.trim();

        // 함수 체이닝: 먼저 공백 제거, 이후 대문자 변환
        Function<String, String> finalFunction =
                removeSpacesFunction.andThen(upperCaseFunction);

        System.out.println(upperCaseFunction.apply(input));

        String result = finalFunction.apply(input);
        System.out.println(result); // 기대 출력: "HELLO WORLD"
    }
}
