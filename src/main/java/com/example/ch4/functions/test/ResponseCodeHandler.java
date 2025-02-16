package com.example.ch4.functions.test;

import java.util.function.Function;

public class ResponseCodeHandler {
    public static void main(String[] args) {
        int responseCode = 404;

        // 단일 기능: HTTP 코드 -> 상태 문자열 변환
        Function<Integer, String> responseFunction = code -> {
            if (code >= 200 && code < 300) return "SUCCESS";
            else if (code == 404) return "NOT_FOUND";
            else if (code >= 500 && code < 600) return "SERVER_ERROR";
            else return "UNKNOWN";
        };

        String result = responseFunction.apply(responseCode);
        System.out.println(result); // 기대 출력: "NOT_FOUND"
    }
}
