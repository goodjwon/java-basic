package com.example.ch4.def;

import java.util.*;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        System.out.println(queue.poll()); // First
        System.out.println(queue.poll()); // Second


    }
}