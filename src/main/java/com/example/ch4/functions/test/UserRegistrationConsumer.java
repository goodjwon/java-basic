package com.example.ch4.functions.test;

import java.util.function.Consumer;

class User {
    String name;
    String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

public class UserRegistrationConsumer {
    public static void main(String[] args) {
        User newUser = new User("홍길동", "test@example.com");

        // 단일 기능: 사용자 정보 저장
        Consumer<User> saveUser = user -> {
            // 실제 DB 저장 로직 가정
            System.out.println("사용자 저장: " + user.name + ", " + user.email);
        };

        // 체이닝 예: 저장 후 알림 메시지
        Consumer<User> sendWelcomeMessage = user -> {
            System.out.println("환영 메시지 발송: " + user.email);
        };

        Consumer<User> combinedFlow = saveUser.andThen(sendWelcomeMessage);
        combinedFlow.accept(newUser);
    }
}
