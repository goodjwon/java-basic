package com.example.ch3.def.que;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // 우선순위 큐 생성 (자연 정렬 순서)
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        // 요소 추가
        priorityQueue.offer(5);
        priorityQueue.offer(1);
        priorityQueue.offer(3);


        // 큐 내용 출력
        System.out.println("현재 우선순위 큐: " + priorityQueue);

        // 요소 처리
        while (!priorityQueue.isEmpty()) {
            int item = priorityQueue.poll();
            System.out.println("처리된 요소: " + item);
            System.out.println("남은 큐: " + priorityQueue);
        }
    }
}
