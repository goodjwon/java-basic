package com.example.ch3;

class Character {
    private String name;
    private int health;
    private int level;

    public Character(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    public void attack() {
        System.out.println(name + "이(가) 공격합니다!");
    }

    public void defend() {
        System.out.println(name + "이(가) 방어합니다!");
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

}

class Game {
    public static void main(String[] args) {
        Character hero = new Character("용사", 100, 1);
        Character villain = new Character("악당", 80, 2);

        hero.attack();
        villain.defend();
    }
}
