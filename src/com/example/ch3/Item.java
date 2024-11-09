package com.example.ch3;

abstract class Item {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void use(Player player);
}


class HealthPotion extends Item {
    private int healAmount;

    public HealthPotion(int healAmount) {
        super("체력 물약");
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player player) {
        System.out.println(player.getName() + "이(가) 체력을 " + healAmount + " 회복했습니다.");
        // 실제 회복 로직 추가
    }
}

class ManaPotion extends Item {
    private int manaAmount;

    public ManaPotion(int manaAmount) {
        super("마나 물약");
        this.manaAmount = manaAmount;
    }

    @Override
    public void use(Player player) {
        System.out.println(player.getName() + "이(가) 마나를 " + manaAmount + " 회복했습니다.");
        // 실제 회복 로직 추가
    }
}


class Game4 {
    public static void main(String[] args) {
        // 플레이어 생성
        Player player = new Player("Jwon");

        // 경험치 획득 및 레벨 상승
        player.gainExperience(1000);

        // 플레이어가 데미지를 입고 마나를 소모했다고 가정
        player.increaseHealth(-30); // 체력 30 감소
        player.increaseMana(-20);   // 마나 20 감소

        // 현재 상태 출력
        System.out.println(player.getName() + "의 현재 체력: " + player.getHealth());
        System.out.println(player.getName() + "의 현재 마나: " + player.getMana());

        // 아이템 생성
        Item healthPotion = new HealthPotion(20);
        Item manaPotion = new ManaPotion(15);

        // 아이템 사용
        healthPotion.use(player);
        manaPotion.use(player);

        // 아이템 사용 후 상태 출력
        System.out.println(player.getName() + "의 현재 체력: " + player.getHealth());
        System.out.println(player.getName() + "의 현재 마나: " + player.getMana());
    }
}
