package com.example.ch4.exception;

class CustomException extends Exception {
    CustomException(String message) {
        super(message);
    }
}

class BasicExceptionExample {
    public static void main(String[] args) {
        try {
            triggerError();
        } catch (CustomException e) {
            System.err.println("예외 발생: " + e.getMessage());
        } finally {
            System.out.println("자원 정리 로직");
        }
    }

    static void triggerError() throws CustomException {
        boolean errorCondition = true;
        if (errorCondition) {
            throw new CustomException("특정 오류 상황 발생");
        }
    }
}
