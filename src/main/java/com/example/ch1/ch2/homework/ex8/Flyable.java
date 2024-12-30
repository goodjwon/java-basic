package com.example.ch1.ch2.homework.ex8;

// Flyable.java
public interface Flyable {
    void fly();
}

// Bird.java
class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("새가 날개를 펴고 납니다.");
    }
}

// Airplane.java
class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("비행기가 엔진을 사용하여 납니다.");
    }
}

// Main.java (실행 예시)
class Main2 {
    public static void main(String[] args) {
        Flyable bird = new Bird();
        Flyable airplane = new Airplane();

        bird.fly();
        airplane.fly();
    }
}
