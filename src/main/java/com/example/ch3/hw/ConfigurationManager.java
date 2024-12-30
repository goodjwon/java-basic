package com.example.ch3.hw;

import java.util.concurrent.ConcurrentHashMap;

public class ConfigurationManager {
    private ConcurrentHashMap<String, String> settings;

    public ConfigurationManager() {
        settings = new ConcurrentHashMap<>();
    }

    // 설정 값 추가 또는 수정
    public void setSetting(String key, String value) {
        settings.put(key, value);
    }

    // 설정 값 조회
    public String getSetting(String key) {
        return settings.get(key);
    }

    // 테스트
    public static void main(String[] args) {
        ConfigurationManager configManager = new ConfigurationManager();

        // 스레드 생성
        Thread writerThread = new Thread(() -> {
            configManager.setSetting("theme", "dark");
        });

        Thread readerThread = new Thread(() -> {
            String theme = configManager.getSetting("theme");
            System.out.println("현재 테마: " + theme);
        });

        writerThread.start();
        readerThread.start();
    }
}
