package com.example.ch4.def.vec;

import java.util.Vector;

public class VectorWithCapacity {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>(10, 5);

        // 초기 용량 및 증가 크기 확인
        System.out.println("초기 용량: " + vector.capacity());

        for (int i = 0; i < 15; i++) {
            vector.add(i);
        }

        System.out.println("현재 용량: " + vector.capacity());
        System.out.println("벡터 데이터: " + vector);
    }
}
