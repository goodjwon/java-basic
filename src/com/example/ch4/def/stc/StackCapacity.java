package com.example.ch4.def.stc;

import java.util.Stack;

public class StackCapacity {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // 데이터 추가
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }

        // 현재 크기 확인
        System.out.println("스택 크기: " + stack.size());
        System.out.println("스택 데이터: " + stack);

        // 데이터 제거
        while (!stack.empty()) {
            stack.pop();
        }

        System.out.println("스택이 비었나요? " + stack.empty());
    }
}
