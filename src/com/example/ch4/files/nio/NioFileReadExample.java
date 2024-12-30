package com.example.ch4.files.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class NioFileReadExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.txt");

        try {
            String content = Files.readString(filePath); // 파일 내용 읽기
            System.out.println("파일 내용:\n" + content);
        } catch (IOException e) {
            System.err.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
    }
}