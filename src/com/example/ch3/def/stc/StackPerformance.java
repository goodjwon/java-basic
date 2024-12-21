package com.example.ch3.def.stc;

import java.util.Stack;

public class StackPerformance {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int n = 1_000_000;

        // 데이터 추가 성능
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("데이터 추가 시간: " + (end - start) + "ms");

        // 데이터 제거 성능
        start = System.currentTimeMillis();
        while (!stack.empty()) {
            stack.pop();
        }
        end = System.currentTimeMillis();
        System.out.println("데이터 제거 시간: " + (end - start) + "ms");
    }
}
