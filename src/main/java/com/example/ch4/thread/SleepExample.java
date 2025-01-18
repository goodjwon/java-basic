package com.example.ch4.thread;

public class SleepExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                System.out.println("1초 대기 중");
                Thread.sleep(1000);
                System.out.println("대기 종료");
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };
        new Thread(task).start();
    }
}