package com.example.ch3;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
        System.out.println(item + "이(가) 인벤토리에 추가되었습니다.");
    }

    public void removeItem(T item) {
        items.remove(item);
        System.out.println(item + "이(가) 인벤토리에서 제거되었습니다.");
    }

    public List<T> getItems() {
        return items;
    }
}

class Weapon2 {
    private String name;

    public Weapon2(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "무기: " + name;
    }
}

class Potion {
    private String type;

    public Potion(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "물약: " + type;
    }
}

class InventoryGame {
    public static void main(String[] args) {
        Inventory<Weapon2> weaponInventory = new Inventory<>();
        weaponInventory.addItem(new Weapon2("전설의 검"));

        Inventory<Potion> potionInventory = new Inventory<>();
        potionInventory.addItem(new Potion("체력 회복 물약"));
    }
}
