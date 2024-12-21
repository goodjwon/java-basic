package com.example.ch3.hw;

import java.util.PriorityQueue;
import java.util.Comparator;

public class OrderSystem {
    private PriorityQueue<Order> orderQueue;

    public OrderSystem() {
        orderQueue = new PriorityQueue<>(new OrderComparator());
    }

    // 주문 추가
    public void addOrder(Order order) {
        orderQueue.add(order);
    }

    // 주문 처리
    public Order processOrder() {
        return orderQueue.poll();
    }

    // 테스트
    public static void main(String[] args) {
        OrderSystem system = new OrderSystem();
        system.addOrder(new Order("일반 고객", false));
        system.addOrder(new Order("VIP 고객", true));
        system.addOrder(new Order("일반 고객2", false));

        System.out.println("처리된 주문: " + system.processOrder());
        System.out.println("처리된 주문: " + system.processOrder());
    }
}

class Order {
    private String customerName;
    private boolean isVIP;

    public Order(String customerName, boolean isVIP) {
        this.customerName = customerName;
        this.isVIP = isVIP;
    }

    public boolean isVIP() {
        return isVIP;
    }

    @Override
    public String toString() {
        return customerName + (isVIP ? " (VIP)" : "");
    }
}

class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        // VIP 고객 우선
        if (o1.isVIP() && !o2.isVIP()) {
            return -1;
        } else if (!o1.isVIP() && o2.isVIP()) {
            return 1;
        } else {
            return 0; // VIP 여부가 같으면 입력 순서대로
        }
    }
}
