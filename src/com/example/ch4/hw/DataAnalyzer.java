package com.example.ch4.hw;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;

public class DataAnalyzer {
    private ArrayList<Integer> dataList;

    public DataAnalyzer() {
        dataList = new ArrayList<>();
    }

    // 데이터 로드
    public void loadData() {
        // 수백만 건의 데이터를 로드합니다.
        for (int i = 0; i < 1_000_000; i++) {
            dataList.add(i);
        }
        System.out.println("데이터 로드 완료: " + dataList.size() + "건");
    }

    // 데이터 처리
    public void processData() {
        // 랜덤 접근을 통해 데이터를 읽습니다.
        Random random = new Random();
        int total = 0;
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(dataList.size());
            int data = dataList.get(index);
            System.out.println("인덱스 " + index + "의 데이터: " + data);
            total += data;
        }
        System.out.println("선택된 데이터의 합계: " + total);
    }

    // 테스트
    public static void main(String[] args) {
        DataAnalyzer analyzer = new DataAnalyzer();
        analyzer.loadData();
        analyzer.processData();
    }
}
