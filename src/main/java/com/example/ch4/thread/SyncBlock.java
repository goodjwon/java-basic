package com.example.ch4.thread;

class SyncBlockExample {
    private final Object lock = new Object();
    private int count = 0;

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

public class SyncBlock {
    public static void main(String[] args) throws InterruptedException {
        SyncBlockExample example = new SyncBlockExample();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("최종 카운트: " + example.getCount());
    }
}
