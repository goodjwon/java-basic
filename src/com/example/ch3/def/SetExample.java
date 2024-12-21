package com.example.ch3.def;

import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(2); // 중복 추가 (무시됨)
        set.add(3);

        for (int number : set) {
            System.out.println(number);
        }
    }
}
