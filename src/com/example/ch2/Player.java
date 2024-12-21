package com.example.ch2;

public class Player {
    private String name;
    private int experience = 0;
    private int level = 1;
    private int health = 100; // 체력 추가
    private int mana = 50;    // 마나 추가

    public Player(String name) {
        this.name = name;
    }

    public void gainExperience(int amount) {
        experience += amount;
        System.out.println(name + "이(가) 경험치 " + amount + "점을 획득했습니다.");

        while (experience >= level * 100) {
            experience -= level * 100;
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        System.out.println(name + "이(가) 레벨 " + level + "로 상승했습니다!");
    }

    public String getName() {
        return name;
    }

    // 체력 관련 메서드
    public int getHealth() {
        return health;
    }

    public void increaseHealth(int amount) {
        health += amount;
        if (health > 100) health = 100; // 최대 체력은 100으로 제한
        System.out.println(name + "의 체력이 " + amount + "만큼 변경되어 현재 체력: " + health);
    }

    // 마나 관련 메서드
    public int getMana() {
        return mana;
    }

    public void increaseMana(int amount) {
        mana += amount;
        if (mana > 50) mana = 50; // 최대 마나는 50으로 제한
        System.out.println(name + "의 마나가 " + amount + "만큼 변경되어 현재 마나: " + mana);
    }
}

class GamePlayer {
    public static void main(String[] args) {
        Player player = new Player("Jwon");
        player.gainExperience(1000);

    }
}