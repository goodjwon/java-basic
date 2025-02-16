package com.example.ch4.functions.test;

import java.util.function.Function;

public class PasswordMasking {
    public static void main(String[] args) {
        String originalPassword = "mypassword";

        // 단일 기능: 비밀번호 길이에 맞춰 별표 문자열 생성
        Function<String, String> maskingFunction =
                pass -> "*".repeat(pass.length());

        String masked = maskingFunction.apply(originalPassword);
        System.out.println(masked); // 기대 출력: "**********"
    }
}
