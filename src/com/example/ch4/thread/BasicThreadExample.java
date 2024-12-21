package com.example.ch4.thread;

public class BasicThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask("스레드1"));
        Thread t2 = new Thread(new MyTask("스레드2"));

        t1.start();
        t2.start();
    }
}

class MyTask implements Runnable {
    private String name;
    MyTask(String name) { this.name = name; }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " 실행: " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
