package com.example.ch4.def.arr;

import java.util.ArrayList;

public class ArrayListWithCapacity {
    public static void main(String[] args) {
        int initialCapacity = 1_000_000;
        ArrayList<Integer> list = new ArrayList<>(initialCapacity);

        long start = System.currentTimeMillis();
        for (int i = 0; i < initialCapacity; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("미리 용량 설정 후 추가 시간: " + (end - start) + "ms");
    }
}
