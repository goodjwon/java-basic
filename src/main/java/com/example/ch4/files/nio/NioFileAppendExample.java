package com.example.ch4.files.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class NioFileAppendExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.txt");
        String additionalContent = "\n추가 내용: NIO를 사용한 파일 내용 추가";

        try {
            Files.writeString(filePath, additionalContent, StandardOpenOption.APPEND);
            System.out.println("파일에 추가 내용 작성 완료: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("파일 내용 추가 중 오류 발생: " + e.getMessage());
        }
    }
}
