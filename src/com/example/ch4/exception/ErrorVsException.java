package com.example.ch4.exception;

public class ErrorVsException {
    public static void main(String[] args) {
        // Exception 예제
        try {
            int result = 10 / 0;  // ArithmeticException 발생
        } catch (ArithmeticException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }

        // Error 예제
        try {
            infiniteRecursion();  // StackOverflowError 발생
        } catch (Error e) {
            System.out.println("에러 처리: " + e.getMessage());
        }
    }

    public static void infiniteRecursion() {
        infiniteRecursion();  // 무한 재귀 호출
    }
}
