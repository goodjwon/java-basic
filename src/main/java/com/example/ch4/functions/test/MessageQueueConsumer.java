package com.example.ch4.functions.test;

import java.util.function.Consumer;

class Message {
    String type;
    String content;

    Message(String type, String content) {
        this.type = type;
        this.content = content;
    }
}

public class MessageQueueConsumer {
    public static void main(String[] args) {
        Message msg = new Message("ORDER", "주문 데이터");

        // 단일 기능: 메시지 큐에 전송
        Consumer<Message> sendToQueue = m -> {
            // 실제 큐 전송 로직 가정
            System.out.println("큐로 전송: " + m.type + " / " + m.content);
        };

        // 체이닝 예: 전송 후 로그 남김
        Consumer<Message> logAfterSend = m -> {
            System.out.println("전송 기록: " + m.type);
        };

        Consumer<Message> combined = sendToQueue.andThen(logAfterSend);
        combined.accept(msg);
    }
}
