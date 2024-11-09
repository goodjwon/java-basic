package com.example.ch1.ch2.homework.ex3;

public class House {
    // 필드
    private String address;
    private int numRooms;
    private double size; // 제곱미터 단위

    // 기본 생성자
    public House() {
        this.address = "알 수 없음";
        this.numRooms = 0;
        this.size = 0.0;
    }

    // 매개변수가 있는 생성자
    public House(String address, int numRooms, double size) {
        this.address = address;
        this.numRooms = numRooms;
        this.size = size;
    }

    // 메서드
    public void displayInfo() {
        System.out.println("주소: " + address);
        System.out.println("방 개수: " + numRooms);
        System.out.println("크기: " + size + "㎡");
    }

    // 메인 메서드 (실행 예시)
    public static void main(String[] args) {
        House defaultHouse = new House();
        defaultHouse.displayInfo();

        System.out.println();

        House myHouse = new House("서울시 강남구", 3, 85.5);
        myHouse.displayInfo();
    }
}
