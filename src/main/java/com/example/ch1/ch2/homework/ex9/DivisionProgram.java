package com.example.ch1.ch2.homework.ex9;

import java.util.Scanner;

public class DivisionProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("첫 번째 정수를 입력하세요: ");
            int num1 = scanner.nextInt();

            System.out.print("두 번째 정수를 입력하세요: ");
            int num2 = scanner.nextInt();

            int result = num1 / num2;
            System.out.println("결과: " + result);

        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
        } finally {
            scanner.close();
        }
    }
}
