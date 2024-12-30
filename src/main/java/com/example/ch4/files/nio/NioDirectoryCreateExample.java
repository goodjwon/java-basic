package com.example.ch4.files.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class NioDirectoryCreateExample {
    public static void main(String[] args) {
        Path dirPath = Paths.get("exampleDir/subDir");

        try {
            Files.createDirectories(dirPath); // 하위 디렉토리 포함 생성
            System.out.println("디렉토리 생성 성공: " + dirPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("디렉토리 생성 중 오류 발생: " + e.getMessage());
        }
    }
}
