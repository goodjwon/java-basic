package com.example.ch1.ch2.homework.ex5;

// Vehicle.java
public class Vehicle {
    public void move() {
        System.out.println("차량이 이동합니다.");
    }
}

// Car.java
 class Car extends Vehicle {
    @Override
    public void move() {
        System.out.println("자동차가 도로를 달립니다.");
    }
}

// Bicycle.java
 class Bicycle extends Vehicle {
    @Override
    public void move() {
        System.out.println("자전거가 자전거 도로를 달립니다.");
    }
}

// Main.java (실행 예시)
class Main5 {
    public static void main(String[] args) {
        Vehicle myVehicle;

        myVehicle = new Car();
        myVehicle.move();

        myVehicle = new Bicycle();
        myVehicle.move();
    }
}
