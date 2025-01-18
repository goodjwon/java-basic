package com.example.ch4.thread;

public class InterruptExample {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("실행 중...");
            }
            System.out.println("쓰레드 종료");
        });

        t.start();
        try {
            Thread.sleep(1000);
             t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
