package com.example.ch2;

class Spell {
    public void cast(String spellName) {
        System.out.println(spellName + " 마법을 시전합니다.");
    }

    public void cast(String spellName, int power) {
        System.out.println(spellName + " 마법을 위력 " + power + "로 시전합니다.");
    }

    public void cast(String spellName, int power, Enemy2 target) {
        System.out.println(target.getName() + "에게 " + spellName + " 마법을 위력 " + power + "로 시전합니다.");
    }
}

class Enemy2 {
    private String name;

    public Enemy2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


class SpellGame {
    public static void main(String[] args) {
        // Spell 객체 생성
        Spell spell = new Spell();

        // Enemy2 객체 생성
        Enemy2 enemy = new Enemy2("고블린");

        // 다양한 형태의 cast 메서드 호출
        spell.cast("파이어볼");
        spell.cast("아이스 스피어", 5);
        spell.cast("썬더 스트라이크", 10, enemy);
    }
}