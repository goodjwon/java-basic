package com.example.ch4.functions;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface MyFunction {
    // 단 하나의 추상 메서드 적용
    void apply();
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        MyFunction function = () -> System.out.println("Applying function!");
        function.apply();

        // 1. Consumer: 입력값을 받아서 소비하는 함수형 인터페이스
        Consumer<String> printName = name -> System.out.println("Name: " + name);
        printName.accept("Wonsik");

        // 2. Supplier: 값을 반환하는 함수형 인터페이스 (인수 없음)
        Supplier<Double> getRandom = () -> Math.random();
        System.out.println("Random value: " + getRandom.get());

        // 3. Function: 입력값을 받아 결과값을 반환하는 함수형 인터페이스
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Functional': " + stringLength.apply("Functional"));

        // 4. Predicate: 조건을 검사하는 함수형 인터페이스
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));
    }
}
