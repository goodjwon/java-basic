package com.example.ch4.files.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class NioFileStreamReadExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.txt");

        try {
            Files.lines(filePath).forEach(System.out::println); // 라인 단위로 출력
        } catch (IOException e) {
            System.err.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
    }
}
