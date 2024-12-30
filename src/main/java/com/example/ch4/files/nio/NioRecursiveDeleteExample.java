package com.example.ch4.files.nio;

import java.nio.file.*;
import java.io.IOException;

public class NioRecursiveDeleteExample {
    public static void main(String[] args) {
        Path dirPath = Paths.get("exampleDir");

        try {
            Files.walk(dirPath) // 디렉토리와 파일 순회
                    .sorted((p1, p2) -> p2.compareTo(p1)) // 하위 경로부터 삭제
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                            System.out.println("삭제 성공: " + path.toAbsolutePath());
                        } catch (IOException e) {
                            System.err.println("삭제 실패: " + path.toAbsolutePath());
                        }
                    });
        } catch (IOException e) {
            System.err.println("디렉토리 순회 중 오류 발생: " + e.getMessage());
        }
    }
}
