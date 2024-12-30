package com.example.ch4.files.nio;

import java.nio.file.*;
import java.io.IOException;

public class NioFileContentSearch {
    public static void main(String[] args) {
        Path startDir = Paths.get("C:\\example");
        String keyword = "검색어";

        try {
            Files.walk(startDir) // 디렉토리 트리 순회
                    .filter(Files::isRegularFile) // 파일만 필터링
                    .forEach(path -> {
                        try {
                            Files.lines(path) // 파일 내용 읽기
                                    .filter(line -> line.contains(keyword))
                                    .forEach(line -> System.out.printf("키워드 '%s' 발견: %s (내용: %s)%n", keyword, path, line));
                        } catch (IOException e) {
                            System.err.println("파일 읽기 오류: " + path.toAbsolutePath());
                        }
                    });
        } catch (IOException e) {
            System.err.println("검색 중 오류 발생: " + e.getMessage());
        }
    }
}
