package com.example.ch3.hw.alog;

import java.util.ArrayList;
import java.util.List;

public class LevelUpChecker {
    public static void main(String[] args) {
        int[][] userStats = {
                {1, 1500},  // 유저 ID 1, 경험치 1500
                {2, 800},   // 유저 ID 2, 경험치 800
                {3, 2300}   // 유저 ID 3, 경험치 2300
        };

        int levelUpThreshold = 1000;
        List<Integer> levelUpUsers = findLevelUpUsers(userStats, levelUpThreshold);
        System.out.println("레벨업 가능: " + levelUpUsers);
    }

    public static List<Integer> findLevelUpUsers(int[][] userStats, int threshold) {
        List<Integer> eligibleUsers = new ArrayList<>();
        for (int[] user : userStats) {
            if (user[1] >= threshold) {
                eligibleUsers.add(user[0]);
            }
        }
        return eligibleUsers;
    }
}
