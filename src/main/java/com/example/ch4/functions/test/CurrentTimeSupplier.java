package com.example.ch4.functions.test;

import java.util.function.Supplier;

public class CurrentTimeSupplier {
    public static void main(String[] args) {
        // 단일 기능: 현재 시간을 long 값으로 반환
        Supplier<Long> currentTimeSupplier =
                () -> System.currentTimeMillis();

        long currentTime = currentTimeSupplier.get();
        System.out.println(currentTime); // 예) 1679551234567
    }
}
