package com.example.ch3.hw;

import java.util.ArrayDeque;

public class MessageQueue {
    private ArrayDeque<String> queue;

    public MessageQueue() {
        queue = new ArrayDeque<>();
    }

    // 메시지 전송 (뒤에 추가)
    public void sendMessage(String message) {
        queue.addLast(message);
    }

    // 메시지 수신 (앞에서 제거)
    public String receiveMessage() {
        return queue.pollFirst();
    }

    // 테스트
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.sendMessage("안녕하세요");
        messageQueue.sendMessage("어떻게 지내세요?");

        System.out.println("수신된 메시지: " + messageQueue.receiveMessage());
        System.out.println("수신된 메시지: " + messageQueue.receiveMessage());
    }
}
