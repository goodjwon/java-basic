package com.example.ch1.ch2.homework.ex8;

// Chargeable.java
public interface Chargeable {
    void charge(int amount);
}

// ElectricCar.java
class ElectricCar implements Chargeable {
    private int batteryLevel;

    // 생성자
    public ElectricCar() {
        batteryLevel = 0;
    }

    // 충전 메서드 구현
    @Override
    public void charge(int amount) {
        batteryLevel += amount;
        if (batteryLevel > 100) batteryLevel = 100;
        System.out.println("전기차 배터리 충전: " + batteryLevel + "%");
    }
}

// Smartphone.java
class Smartphone implements Chargeable {
    private int batteryPercentage;

    // 생성자
    public Smartphone() {
        batteryPercentage = 0;
    }

    // 충전 메서드 구현
    @Override
    public void charge(int amount) {
        batteryPercentage += amount;
        if (batteryPercentage > 100) batteryPercentage = 100;
        System.out.println("스마트폰 배터리 충전: " + batteryPercentage + "%");
    }
}

// Main.java (실행 예시)
class Main {
    public static void main(String[] args) {
        Chargeable car = new ElectricCar();
        Chargeable phone = new Smartphone();

        car.charge(40);
        phone.charge(25);

        car.charge(70); // 최대 100%로 제한
        phone.charge(80); // 최대 100%로 제한
    }
}
