package com.example.ch1.ch2.homework.ex5;

// Animal.java
public class Animal {
    protected String name;
    protected int age;

    // 생성자
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 공통 메서드
    public void eat() {
        System.out.println(name + "이(가) 먹이를 먹습니다.");
    }
}

// Dog.java
class Dog extends Animal {
    // 생성자
    public Dog(String name, int age) {
        super(name, age);
    }

    // 고유 메서드
    public void bark() {
        System.out.println(name + "이(가) 멍멍 짖습니다.");
    }
}

// Cat.java
class Cat extends Animal {
    // 생성자
    public Cat(String name, int age) {
        super(name, age);
    }

    // 고유 메서드
    public void meow() {
        System.out.println(name + "이(가) 야옹하고 웁니다.");
    }
}

// Main.java (실행 예시)
class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("바둑이", 3);
        dog.eat();
        dog.bark();

        Cat cat = new Cat("나비", 2);
        cat.eat();
        cat.meow();
    }
}
