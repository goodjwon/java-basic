package com.example.ch4.functions.test;

import java.util.function.Supplier;
import java.util.Random;

public class EventWinSupplier {
    public static void main(String[] args) {
        // 단일 기능: 당첨 여부 결정
        Supplier<Boolean> winSupplier = () -> {
            Random rand = new Random();
            // 예: 20% 확률로 당첨
            return rand.nextInt(100) < 20;
        };

        boolean isWinner = winSupplier.get();
        System.out.println(isWinner); // true or false
    }
}
