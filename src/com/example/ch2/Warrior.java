package com.example.ch2;


/**
 * 상속에 대해서 이해.
 */
class Warrior extends Character {
    private int stamina;

    public Warrior(String name, int health, int level, int stamina) {
        super(name, health, level);
        this.stamina = stamina;
    }

    public void heavyAttack() {
        if (stamina >= 10) {
            stamina -= 10;
            System.out.println(getName() + "이(가) 강력한 공격을 합니다! 남은 스태미나: " + stamina);
        } else {
            System.out.println(getName() + "의 스태미나가 부족하여 강력한 공격을 할 수 없습니다.");
        }
    }

    public String getName() {
        return super.getName();
    }
}

class Mage extends Character {
    private int mana;

    public Mage(String name, int health, int level, int mana) {
        super(name, health, level);
        this.mana = mana;
    }

    public void castSpell() {
        if (mana >= 20) {
            mana -= 20;
            System.out.println(getName() + "이(가) 마법을 시전합니다! 남은 마나: " + mana);
        } else {
            System.out.println(getName() + "의 마나가 부족하여 마법을 시전할 수 없습니다.");
        }
    }

    public String getName() {
        return super.getName();
    }
}

class Archer extends Character {
    private int arrows;

    public Archer(String name, int health, int level, int arrows) {
        super(name, health, level);
        this.arrows = arrows;
    }

    public void shootArrow() {
        if (arrows > 0) {
            arrows--;
            System.out.println(getName() + "이(가) 화살을 발사합니다! 남은 화살: " + arrows);
        } else {
            System.out.println(getName() + "의 화살이 부족하여 공격할 수 없습니다.");
        }
    }

    public String getName() {
        return super.getName();
    }
}

// Game.java
class Game2 {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("전사", 100, 1, 50);
        Mage mage = new Mage("마법사", 80, 1, 100);
        Archer archer = new Archer("궁수", 90, 1, 30);

        warrior.attack();
        warrior.heavyAttack();

        mage.attack();
        mage.castSpell();

        archer.attack();
        archer.shootArrow();
    }
}


