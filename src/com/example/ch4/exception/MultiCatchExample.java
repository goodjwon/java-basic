package com.example.ch4.exception;

public class MultiCatchExample {
    public static void main(String[] args) {
        String[] values = {"100", null, "ABC"};

        for (String val : values) {
            try {
                int number = Integer.parseInt(val);
                // val이 null이면 NullPointerException
                // 숫자로 변환 불가 시 NumberFormatException
                System.out.println("변환 성공: " + number);
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println("변환 실패: " + e.getMessage());
            }
        }
    }
}

/*
코드 설명:
- values 배열에 null, "ABC" 포함
- null 값은 NullPointerException, "ABC"는 NumberFormatException 발생
- 멀티 catch 문으로 두 예외를 하나의 블록에서 처리
- 다양한 예외 유형을 단순화하여 예외 처리 로직 간소화
*/
