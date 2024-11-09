package com.example.ch1.ch2.homework.ex9;

public class ArrayExceptionHandling {
    public static void main(String[] args) {
        String[] fruits = {"사과", "바나나", "체리"};

        try {
            // 존재하지 않는 인덱스에 접근
            System.out.println(fruits[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("인덱스 범위를 벗어났습니다. 배열의 길이는 " + fruits.length + "입니다.");
        }
    }
}
