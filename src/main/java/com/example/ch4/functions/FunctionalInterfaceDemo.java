package com.example.ch4.functions;

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);

    // 디폴트 메서드
    default void printOperation(String operation) {
        System.out.println("Performing: " + operation);
    }

    // 디폴트 메서드
    default void printOperation2(String operation) {
        System.out.println("Performing: " + operation);
    }

    // 정적 메서드
    static void printInfo() {
        System.out.println("This is a functional interface.");
    }
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        MathOperation addition = (a, b) -> a + b;

        addition.printOperation("Addition");
        System.out.println("Result: " + addition.operate(5, 3));

        addition.printOperation2("Addition");
        System.out.println("Result: " + addition.operate(5, 3));

        // 정적 메서드 호출
        MathOperation.printInfo();
    }
}
