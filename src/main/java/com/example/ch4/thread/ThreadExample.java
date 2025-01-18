package com.example.ch4.thread;

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread 실행 중" + Thread.currentThread().getName());
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
