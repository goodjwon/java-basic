package com.example.ch4.functions.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StringToIntExample {
    public static void main(String[] args) {
//        Function<String, Integer> stringToInt = Integer::parseInt;

//        System.out.println(stringToInt.apply("123"));  // 출력: 123
//        System.out.println(stringToInt.apply("456"));  // 출력: 456

        List<String> lists = Arrays.asList("123", "456", "789");

        Function<String, String> displayInputType = s -> {
            System.out.println("Input: \"" + s + "\" (Type: " + s.getClass().getSimpleName() + ")");
            return s;
        };

        // 문자열 → 정수 변환 후 데이터 형 출력
        Function<String, Integer> stringToInt = s -> {
            Integer result = Integer.parseInt(s);
            System.out.println("Converted: " + result + " (Type: " + result.getClass().getSimpleName() + ")");
            return result;
        };

        lists.forEach(input -> displayInputType.andThen(stringToInt).apply(input));
    }

}
