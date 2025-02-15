package com.example.ch4.functions.test;

import java.util.function.Supplier;

class UserSession {
    private String userId;
    private long expiryTime;

    public UserSession(String userId, long expiryTime) {
        this.userId = userId;
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "UserSession{userId='" + userId + "', expiryTime=" + expiryTime + "}";
    }
}

public class UserSessionSupplier {
    public static void main(String[] args) {
        // 단일 기능: 세션 객체 생성 및 반환
        Supplier<UserSession> sessionSupplier = () -> {
            // 가상의 세션 정보라 가정
            return new UserSession("user123", System.currentTimeMillis() + 3600000);
        };

        UserSession session = sessionSupplier.get();
        System.out.println(session);
    }
}
