package com.example.ch4.def;

import java.util.*;

public class TreeMapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(3, "Three");
        map.put(1, "One");
        map.put(2, "Two");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
