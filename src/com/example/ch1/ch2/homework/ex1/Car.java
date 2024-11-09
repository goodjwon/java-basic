package com.example.ch1.ch2.homework.ex1;

public class Car {
    // 필드(속성)
    private String make;
    private String model;
    private int year;
    private String color;
    private boolean engineRunning;

    // 생성자
    public Car(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.engineRunning = false; // 기본값 설정
    }

    // 메서드(동작)
    public void startEngine() {
        if (!engineRunning) {
            engineRunning = true;
            System.out.println("엔진이 시동되었습니다.");
        } else {
            System.out.println("엔진이 이미 시동되어 있습니다.");
        }
    }

    public void stopEngine() {
        if (engineRunning) {
            engineRunning = false;
            System.out.println("엔진이 꺼졌습니다.");
        } else {
            System.out.println("엔진이 이미 꺼져 있습니다.");
        }
    }

    public void accelerate() {
        if (engineRunning) {
            System.out.println("자동차가 가속합니다.");
        } else {
            System.out.println("먼저 엔진을 시동하세요.");
        }
    }

    public void brake() {
        System.out.println("자동차가 감속합니다.");
    }

    // 메인 메서드 (실행 예시)
    public static void main(String[] args) {
        // 객체 생성
        Car myCar = new Car("현대", "소나타", 2021, "화이트");

        // 메서드 호출
        myCar.startEngine();
        myCar.accelerate();
        myCar.brake();
        myCar.stopEngine();
    }
}
