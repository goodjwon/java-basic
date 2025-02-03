package com.example.ch4.files.xls;

import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.List;
import java.util.function.BiConsumer;

public class ChangeValue {
    static class InventoryData {
        private String name;
        private int amount;
        private int price;

        public InventoryData(String name, int amount, int price) {
            this.name = name;
            this.amount = amount;
            this.price = price;
        }

        // 변경 메서드 추가
        public void updateAmount(int newAmount) {
            this.amount = newAmount;
        }

        @Override
        public String toString() {
            return name + "," + amount + "," + price;
        }
    }

    public static List<InventoryData> readExcel(String filePath) {
        return ExcelUtils.readExcel(
                filePath,
                row -> new InventoryData(
                        ExcelUtils.getStringValue(row, 0),
                        ExcelUtils.getIntValue(row, 1),
                        ExcelUtils.getIntValue(row, 2)
                )
        );
    }

    public static void writeExcel(
            String filePath,
            List<InventoryData> data,
            boolean includeHeader
    ) {
        String[] headers = includeHeader
                ? new String[]{"제품명", "수량", "가격"}
                : new String[0];

        ExcelUtils.writeExcel(
                filePath,
                data,
                headers,
                (item, row) -> {
                    row.createCell(0).setCellValue(item.name);
                    row.createCell(1).setCellValue(item.amount);
                    row.createCell(2).setCellValue(item.price);
                }
        );
    }

    public static void changeAmount(
            List<InventoryData> data,
            String productName,
            int newAmount
    ) {
        data.stream()
                .filter(item -> item.name.equals(productName))
                .findFirst()
                .ifPresent(item -> item.updateAmount(newAmount));
    }

    public static void main(String[] args) {
        try {
            // 1. 리소스 경로 조회
            String inputPath = ExcelUtils.getResourcePath("inventory.xlsx");
            String outputPath = "modified_inventory.xlsx";

            System.out.println(inputPath);
            // 2. 데이터 읽기
            List<InventoryData> inventoryData = readExcel(inputPath);

            // 3. 데이터 수정
            changeAmount(inventoryData, "제품1", 100);

            // 4. 결과 저장
            writeExcel(outputPath, inventoryData, true);
            inventoryData.forEach(System.out::println);

        } catch (ExcelUtils.ExcelException e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}