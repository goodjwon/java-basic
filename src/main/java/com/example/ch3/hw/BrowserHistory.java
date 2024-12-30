package com.example.ch3.hw;

import java.util.ArrayDeque;

public class BrowserHistory {
    private ArrayDeque<String> historyStack;

    public BrowserHistory() {
        historyStack = new ArrayDeque<>();
    }

    // 페이지 방문
    public void visit(String url) {
        historyStack.push(url);
        System.out.println("방문: " + url);
    }

    // 뒤로가기
    public void goBack() {
        if (!historyStack.isEmpty()) {
            String lastVisited = historyStack.pop();
            System.out.println("뒤로가기: " + lastVisited);
            System.out.println("현재 페이지: " + (historyStack.peek() != null ? historyStack.peek() : "홈"));
        } else {
            System.out.println("뒤로 갈 페이지가 없습니다.");
        }
    }

    // 테스트
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        browser.visit("홈");
        browser.visit("소개");
        browser.visit("문의");
        browser.goBack(); // 문의 페이지에서 뒤로가기
        browser.goBack(); // 소개 페이지에서 뒤로가기
    }
}
