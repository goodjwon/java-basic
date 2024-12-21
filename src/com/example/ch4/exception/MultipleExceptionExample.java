package com.example.ch4.exception;

public class MultipleExceptionExample {
    public static void main(String[] args) {
        try {
            processOperation();
        } catch (CustomException e) {
            System.err.println("사용자 정의 예외: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("숫자 변환 오류: " + e.getMessage());
        }
    }

    static void processOperation() throws CustomException {
        String input = "abc";
        if (!input.matches("\\d+")) {
            throw new NumberFormatException("입력값이 숫자가 아님");
        }

        boolean anotherError = true;
        if (anotherError) {
            throw new CustomException("추가적인 오류 상황");
        }
    }
}
