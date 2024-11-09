package com.example.ch1.ch2.homework.ex7;

// Animal.java
public abstract class Animal {
    public abstract void makeSound();
}

// Dog.java
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("멍멍!");
    }
}

// Cat.java
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("야옹!");
    }
}

// Main.java (실행 예시)
class Main2 {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.makeSound();
        cat.makeSound();
    }
}
