package com.example.ch3.hw.alog;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagement {
    public static void main(String[] args) {
        int[][] inventory = {
                {101, 5},  // 상품 ID 101, 재고 5
                {102, 12}, // 상품 ID 102, 재고 12
                {103, 8}   // 상품 ID 103, 재고 8
        };

        List<Integer> lowStockItems = findLowStockItems(inventory, 10);
        System.out.println("부족한 재고: " + lowStockItems);
    }

    public static List<Integer> findLowStockItems(int[][] inventory, int threshold) {
        List<Integer> lowStock = new ArrayList<>();
        for (int[] item : inventory) {
            if (item[1] < threshold) {
                lowStock.add(item[0]);
            }
        }
        return lowStock;
    }
}
