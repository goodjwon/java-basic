package com.example.ch4.thread;

public class JoinExample {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("작업 완료");
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        });
        t.start();
        try {
            // t.join();
            System.out.println("메인 쓰레드 종료");
        } catch (Exception e) {
            System.out.println("Main thread interrupted");
        }
    }
}
