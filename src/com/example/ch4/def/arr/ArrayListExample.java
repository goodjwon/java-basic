package com.example.ch4.def.arr;

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        // 데이터 추가
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // 데이터 접근
        System.out.println("첫 번째 요소: " + list.get(0)); // Apple

        // 데이터 수정
        list.set(1, "Blueberry");
        System.out.println("수정된 리스트: " + list);

        // 데이터 삭제
        list.remove(2);
        System.out.println("삭제 후 리스트: " + list);

        // 크기 확인
        System.out.println("리스트 크기: " + list.size());
    }
}
