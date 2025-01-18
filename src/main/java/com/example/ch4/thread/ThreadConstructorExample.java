package com.example.ch4.thread;

public class ThreadConstructorExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Thread1 실행"));
        Thread t2 = new Thread(() -> System.out.println("Thread2 실행"), "CustomThread");
        t1.start();
        t2.start();
    }
}
