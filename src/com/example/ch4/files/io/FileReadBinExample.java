package com.example.ch4.files.io;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReadBinExample {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("output.bin")) {
            int value;
            while ((value = fis.read()) != -1) { // 파일 끝(-1)까지 읽기
                System.out.println(value); // 읽은 바이트를 숫자로 출력
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
