package com.example.ch1.ch2.homework.ex4;

public class Person {
    // private 필드
    private int age;

    // 생성자
    public Person() {
        this.age = 0;
    }

    // 나이 설정 메서드
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("유효하지 않은 나이입니다. 0에서 150 사이로 설정하세요.");
        }
    }

    // 나이 가져오기 메서드
    public int getAge() {
        return age;
    }

    // 메인 메서드 (실행 예시)
    public static void main(String[] args) {
        Person person = new Person();

        person.setAge(30);
        System.out.println("나이: " + person.getAge());

        person.setAge(200); // 유효하지 않은 나이 설정 시도
    }
}
