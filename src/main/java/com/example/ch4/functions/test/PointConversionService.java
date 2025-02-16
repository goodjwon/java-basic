package com.example.ch4.functions.test;

import java.util.function.Function;

class PointData {
    String userId;
    int amount;

    PointData(String userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }
}

class CashData {
    int cashAmount;
    int validDays;

    CashData(int cashAmount, int validDays) {
        this.cashAmount = cashAmount;
        this.validDays = validDays;
    }

    @Override
    public String toString() {
        return "CashData{cashAmount=" + cashAmount + ", validDays=" + validDays + "}";
    }
}

public class PointConversionService {
    public static void main(String[] args) {
        PointData pointData = new PointData("userABC", 3000);

        // 단일 기능: 포인트 -> CashData 변환
        Function<PointData, CashData> pointToCash =
                pdata -> new CashData(pdata.amount / 100, 30);

        CashData result = pointToCash.apply(pointData);
        System.out.println(result); // 예) CashData{cashAmount=30, validDays=30}
    }
}
