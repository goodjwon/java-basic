package com.example.ch4.files.nio;

import java.nio.file.*;
import java.io.IOException;

public class NioFileNameSearch {
    public static void main(String[] args) {
        Path startDir = Paths.get("C:\\example");
        String targetFileName = "targetFile.txt";

        try {
            Files.walk(startDir, Integer.MAX_VALUE) // 디렉토리 트리 순회
                    .filter(path -> path.getFileName().toString().equalsIgnoreCase(targetFileName))
                    .forEach(path -> System.out.println("파일 발견: " + path.toAbsolutePath()));
        } catch (IOException e) {
            System.err.println("파일 검색 중 오류 발생: " + e.getMessage());
        }
    }
}
