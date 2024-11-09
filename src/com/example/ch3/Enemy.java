package com.example.ch3;

class Enemy {
    public void attack() {
        System.out.println("적이 공격합니다!");
    }
}

class Goblin extends Enemy {
    @Override
    public void attack() {
        System.out.println("고블린이 단검으로 찌릅니다!");
    }
}

class Troll extends Enemy {
    @Override
    public void attack() {
        System.out.println("트롤이 몽둥이로 내리칩니다!");
    }
}

 class Dragon extends Enemy {
    @Override
    public void attack() {
        System.out.println("드래곤이 불을 뿜습니다!");
    }
}

class Game3 {
    public static void main(String[] args) {
        Enemy[] enemies = { new Goblin(), new Troll(), new Dragon() };

        for (Enemy enemy : enemies) {
            enemy.attack();
        }
    }
}
