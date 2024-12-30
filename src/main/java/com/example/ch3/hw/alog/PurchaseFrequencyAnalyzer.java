package com.example.ch3.hw.alog;

import java.util.HashMap;

public class PurchaseFrequencyAnalyzer {
    public static void main(String[] args) {
        int[] purchaseHistory = {101, 102, 101, 103, 102, 101};

        int mostPurchased = findMostPurchased(purchaseHistory);
        System.out.println("가장 많이 구매한 상품 ID: " + mostPurchased);
    }

    public static int findMostPurchased(int[] purchases) {
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int id : purchases) {
            frequency.put(id, frequency.getOrDefault(id, 0) + 1);
        }
        int maxCount = 0, mostPurchased = Integer.MAX_VALUE;
        for (int id : frequency.keySet()) {
            if (frequency.get(id) > maxCount ||
                    (frequency.get(id) == maxCount && id < mostPurchased)) {
                maxCount = frequency.get(id);
                mostPurchased = id;
            }
        }
        return mostPurchased;
    }
}
