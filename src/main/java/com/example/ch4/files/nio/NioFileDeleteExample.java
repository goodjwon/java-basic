package com.example.ch4.files.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class NioFileDeleteExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.txt");

        try {
            Files.deleteIfExists(filePath); // 파일 삭제
            System.out.println("파일 삭제 성공: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("파일 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}
