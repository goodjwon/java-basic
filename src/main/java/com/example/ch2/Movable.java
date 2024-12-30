package com.example.ch2;

public interface Movable {
    void move(int x, int y);
}

class Player2 implements Movable {
    private String name;
    private int x, y;

    public Player2(String name) {
        this.name = name;
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println(name + "이(가) 위치 (" + x + ", " + y + ")로 이동했습니다.");
    }
}

class NPC implements Movable {
    private String name;
    private int x, y;

    public NPC(String name) {
        this.name = name;
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("NPC " + name + "이(가) 위치 (" + x + ", " + y + ")로 이동했습니다.");
    }
}


class MoveGame {
    public static void main(String[] args) {
        // Player2 인스턴스 생성
        Player2 player = new Player2("용사");

        // NPC 인스턴스 생성
        NPC npc = new NPC("상인");

        // 플레이어와 NPC 이동
        player.move(10, 20);
        npc.move(15, 25);

        // 추가 이동
        player.move(30, 40);
        npc.move(35, 45);
    }
}
