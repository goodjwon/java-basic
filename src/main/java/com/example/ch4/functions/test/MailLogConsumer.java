package com.example.ch4.functions.test;

import java.util.function.Consumer;

public class MailLogConsumer {
    public static void main(String[] args) {
        String mailResult = "메일 전송 완료: user@example.com";

        // 단일 기능: 메시지 로그 출력
        Consumer<String> logConsumer = msg -> System.out.println("[LOG] " + msg);

        // 체이닝 예: 로그에 출력한 후, 길이 출력
        Consumer<String> lengthConsumer = msg -> System.out.println("메시지 길이: " + msg.length());

        Consumer<String> combinedConsumer = logConsumer.andThen(lengthConsumer);

        combinedConsumer.accept(mailResult);
    }
}
