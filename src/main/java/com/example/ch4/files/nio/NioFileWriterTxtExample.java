package com.example.ch4.files.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class NioFileWriterTxtExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.txt");
        String content = "NIO를 사용한 문자열 파일 작성";

        try {
            Files.writeString(filePath, content); // 파일 생성 및 문자열 쓰기
            System.out.println("문자열 데이터 파일 작성 완료: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("파일 작성 중 오류 발생: " + e.getMessage());
        }
    }
}
