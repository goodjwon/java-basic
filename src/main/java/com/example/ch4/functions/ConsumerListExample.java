package com.example.ch4.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerListExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // 첫 번째 Consumer: 이름 출력
        Consumer<String> printName = name -> System.out.println("Name: " + name);

        // 두 번째 Consumer: 이름의 길이 출력
        Consumer<String> printLength = name -> System.out.println("Length: " + name.length());

        // Consumer 연결
        Consumer<String> combined = printName.andThen(printLength);

        // 리스트의 각 요소에 대해 Consumer 실행
        names.forEach(combined);
    }
}
