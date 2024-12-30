package com.example.ch3.hw.alog;

import java.util.HashSet;

public class DiscountCodeValidator {
    public static void main(String[] args) {
        String discountCode = "SAVE2023";

        boolean isValid = isUniqueCode(discountCode);
        System.out.println(isValid ? "유효" : "무효");
    }

    public static boolean isUniqueCode(String code) {
        HashSet<Character> seen = new HashSet<>();
        for (char c : code.toCharArray()) {
            if (!seen.add(c)) {
                return false;
            }
        }
        return true;
    }
}
