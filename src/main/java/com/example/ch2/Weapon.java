package com.example.ch2;

class Weapon {
    public void attack() {
        System.out.println("무기로 공격합니다.");
    }
}

class Sword extends Weapon {
    @Override
    public void attack() {
        System.out.println("검으로 베기 공격을 합니다!");
    }
}

class Bow extends Weapon {
    @Override
    public void attack() {
        System.out.println("활로 화살을 발사합니다!");
    }
}

class Staff extends Weapon {
    @Override
    public void attack() {
        System.out.println("지팡이로 마법 공격을 합니다!");
    }
}


class WeaponGame {
    public static void main(String[] args) {
        // Weapon 클래스의 인스턴스 생성
        Weapon basicWeapon = new Weapon();
        basicWeapon.attack();

        // Sword 클래스의 인스턴스 생성
        Weapon sword = new Sword();
        sword.attack();

        // Bow 클래스의 인스턴스 생성
        Weapon bow = new Bow();
        bow.attack();

        // Staff 클래스의 인스턴스 생성
        Weapon staff = new Staff();
        staff.attack();

        // 다형성 예시: Weapon 배열에 각 무기 객체 저장
        Weapon[] weapons = { new Weapon(), new Sword(), new Bow(), new Staff() };
        System.out.println("\n무기 배열을 사용하여 공격합니다:");

        for (Weapon weapon : weapons) {
            weapon.attack();
        }
    }
}
