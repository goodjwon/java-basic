package com.example.ch4.files.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class NioFileOutputBinExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.bin");
        byte[] data = {10, 20, 30, 40};

        try {
            Files.write(filePath, data); // 파일 생성 및 바이트 쓰기
            System.out.println("바이트 데이터 파일 작성 완료: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("파일 작성 중 오류 발생: " + e.getMessage());
        }
    }
}
