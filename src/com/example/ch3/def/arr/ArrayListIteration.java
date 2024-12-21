package com.example.ch3.def.arr;

import java.util.ArrayList;

public class ArrayListIteration {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            numbers.add(i);
        }

        // 1. for-each 루프
        for (int num : numbers) {
            System.out.println(num);
        }

        // 2. for 루프
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("인덱스 " + i + ": " + numbers.get(i));
        }

        // 3. 람다 표현식 (Java 8 이상)
        numbers.forEach(num -> System.out.println("람다: " + num));
    }
}
