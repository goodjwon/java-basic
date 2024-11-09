package com.example.ch1.ch2.homework.ex3;

public class Computer {
    // 필드
    private String brand;
    private String cpu;
    private int ram; // GB 단위

    // 생성자
    public Computer(String brand, String cpu, int ram) {
        this.brand = brand;
        this.cpu = cpu;
        this.ram = ram;
    }

    // 메서드
    public void displaySpecs() {
        System.out.println("브랜드: " + brand);
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram + "GB");
    }

    // 메인 메서드 (실행 예시)
    public static void main(String[] args) {
        Computer myComputer = new Computer("애플", "M1", 16);
        myComputer.displaySpecs();
    }
}
