package com.example.ch4.files.acc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LargeFileReadExample {
    public static void main(String[] args) {
        // IO 방식
        PerformanceUtil.measurePerformance(() -> {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader("largeFile1.txt"));
                while (reader.readLine() != null) {
                    // 파일 내용 처리
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "IO 방식 대용량 파일 읽기");

        // NIO 방식
        PerformanceUtil.measurePerformance(() -> {
            Path filePath = Paths.get("largeFile1.txt");
            try (var lines = Files.lines(filePath)) { // 자원 자동 해제
                lines.forEach(line -> {
                    // 파일 내용 처리
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "NIO 방식 대용량 파일 읽기");
    }
}
