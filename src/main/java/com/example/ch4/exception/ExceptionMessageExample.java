package com.example.ch4.exception;

public class ExceptionMessageExample {
    public static void main(String[] args) {
        try {
            riskyOperation();
        } catch (RuntimeException e) {
            System.err.println("예외 메시지: " + e.getMessage());
            System.err.println("스택 추적 정보:");
            e.printStackTrace();
        }
    }

    static void riskyOperation() {
        throw new RuntimeException("위험한 상황 발생");
    }
}

/*
코드 설명:
- riskyOperation 메서드에서 의도적으로 RuntimeException 발생
- catch 블록에서 getMessage(), printStackTrace()로 예외 상세 정보 확인
- 문제 원인 및 발생 지점 추적 가능
*/
