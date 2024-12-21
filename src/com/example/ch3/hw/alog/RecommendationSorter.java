package com.example.ch3.hw.alog;

import java.util.Arrays;

public class RecommendationSorter {
    public static void main(String[] args) {
        int[][] recommendations = {
                {101, 85},
                {102, 95},
                {103, 70}
        };

        sortRecommendations(recommendations);
    }

    public static void sortRecommendations(int[][] recommendations) {
        Arrays.sort(recommendations, (a, b) -> b[1] - a[1]);
        System.out.println("추천 상품:");
        for (int[] rec : recommendations) {
            System.out.println(rec[0] + ": " + rec[1]);
        }
    }
}
