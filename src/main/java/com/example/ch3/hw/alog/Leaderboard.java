package com.example.ch3.hw.alog;

import java.util.Arrays;

public class Leaderboard {
    public static void main(String[] args) {
        int[][] scores = {
                {1, 500},
                {2, 1200},
                {3, 800},
                {4, 600}
        };
        int topN = 3;

        printTopScores(scores, topN);
    }

    public static void printTopScores(int[][] scores, int topN) {
        Arrays.sort(scores, (a, b) -> b[1] - a[1]);
        System.out.println("리더보드:");
        for (int i = 0; i < Math.min(topN, scores.length); i++) {
            System.out.println(scores[i][0] + ": " + scores[i][1]);
        }
    }
}
