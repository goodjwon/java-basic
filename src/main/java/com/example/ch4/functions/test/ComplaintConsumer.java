package com.example.ch4.functions.test;

import java.util.function.Consumer;

class Complaint {
    String userId;
    String content;

    Complaint(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}

public class ComplaintConsumer {
    public static void main(String[] args) {
        Complaint complaint = new Complaint("user123", "배송 지연");

        // 단일 기능: 컴플레인 정보를 저장
        Consumer<Complaint> saveComplaint = c -> {
            System.out.println("불만 접수: " + c.userId + ", 내용: " + c.content);
            // 실제 DB 저장 로직 가정
        };

        saveComplaint.accept(complaint);
    }
}
