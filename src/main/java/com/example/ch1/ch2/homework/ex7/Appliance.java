package com.example.ch1.ch2.homework.ex7;

// Appliance.java
public abstract class Appliance {
    public abstract void turnOn();

    public abstract void turnOff();
}

// WashingMachine.java
class WashingMachine extends Appliance {
    @Override
    public void turnOn() {
        System.out.println("세탁기가 켜졌습니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("세탁기가 꺼졌습니다.");
    }
}

// Refrigerator.java
class Refrigerator extends Appliance {
    @Override
    public void turnOn() {
        System.out.println("냉장고가 켜졌습니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("냉장고가 꺼졌습니다.");
    }
}

// Main.java (실행 예시)
class Main {
    public static void main(String[] args) {
        Appliance washer = new WashingMachine();
        Appliance fridge = new Refrigerator();

        washer.turnOn();
        washer.turnOff();

        fridge.turnOn();
        fridge.turnOff();
    }
}
