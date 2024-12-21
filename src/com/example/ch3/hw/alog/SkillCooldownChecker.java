package com.example.ch3.hw.alog;

import java.util.ArrayList;
import java.util.List;

public class SkillCooldownChecker {
    public static void main(String[] args) {
        String[][] skills = {
                {"Fireball", "-3"},
                {"Heal", "5"},
                {"Shield", "0"}
        };

        List<String> availableSkills = findAvailableSkills(skills);
        System.out.println("사용 가능한 스킬: " + availableSkills);
    }

    public static List<String> findAvailableSkills(String[][] skills) {
        List<String> available = new ArrayList<>();
        for (String[] skill : skills) {
            if (Integer.parseInt(skill[1]) <= 0) {
                available.add(skill[0]);
            }
        }
        return available;
    }
}
