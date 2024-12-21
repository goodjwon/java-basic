package com.example.ch3.hw.alog;

public class DropRateCalculator {
    public static void main(String[] args) {
        int totalKills = 120;
        int totalDrops = 15;

        double dropRate = calculateDropRate(totalKills, totalDrops);
        System.out.printf("드랍 확률: %.2f%%%n", dropRate);
    }

    public static double calculateDropRate(int kills, int drops) {
        return (double) drops / kills * 100;
    }
}
