package com.example.ch4.def.vec;

import java.util.Vector;

public class VectorPerformance {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        int n = 1_000_000;

        // 벡터 끝에 추가 성능
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            vector.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("벡터 끝에 추가 시간: " + (end - start) + "ms");

        // 중간 삽입 성능
        start = System.currentTimeMillis();
        for (int i = 0; i < n / 10; i++) {
            vector.add(vector.size() / 2, i);
        }
        end = System.currentTimeMillis();
        System.out.println("중간 삽입 시간: " + (end - start) + "ms");
    }
}
