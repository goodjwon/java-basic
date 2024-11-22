package com.example.ch4.hw;

import java.util.Vector;

public class LogCollector {
    private Vector<String> logs;

    public LogCollector() {
        logs = new Vector<>();
    }

    // 로그 추가
    public void addLog(String log) {
        logs.add(log);
    }

    // 로그 조회
    public Vector<String> getLogs() {
        return logs;
    }

    // 테스트
    public static void main(String[] args) {
        LogCollector logCollector = new LogCollector();

        // 스레드 생성
        Thread thread1 = new Thread(() -> {
            logCollector.addLog("Thread1: 시작");
        });

        Thread thread2 = new Thread(() -> {
            logCollector.addLog("Thread2: 종료");
        });

        thread1.start();
        thread2.start();

        // 스레드 종료 대기
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 로그 출력
        System.out.println("로그 목록: " + logCollector.getLogs());
    }
}
