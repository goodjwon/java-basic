package com.example.ch1.ch2.homework.ex6;

// Instrument.java
public class Instrument {
    public void play() {
        System.out.println("악기를 연주합니다.");
    }
}

// Piano.java
class Piano extends Instrument {
    @Override
    public void play() {
        System.out.println("피아노를 연주합니다.");
    }
}

// Guitar.java
class Guitar extends Instrument {
    @Override
    public void play() {
        System.out.println("기타를 연주합니다.");
    }
}

// Drum.java
class Drum extends Instrument {
    @Override
    public void play() {
        System.out.println("드럼을 연주합니다.");
    }
}

// Main.java (실행 예시)
class Main {
    public static void main(String[] args) {
        Instrument[] instruments = new Instrument[3];

        instruments[0] = new Piano();
        instruments[1] = new Guitar();
        instruments[2] = new Drum();

        for (Instrument instrument : instruments) {
            instrument.play();
        }
    }
}
