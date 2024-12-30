package com.example.ch4.files.acc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCreateExample {
    public static void main(String[] args) {
        // IO 방식
        PerformanceUtil.measurePerformance(() -> {
            File file = new File("io_example.txt");
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.write("초기화 데이터");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "IO 방식 파일 생성 및 초기화");

        // NIO 방식
        PerformanceUtil.measurePerformance(() -> {
            Path filePath = Paths.get("nio_example.txt");
            try {
                Files.writeString(filePath, "초기화 데이터");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "NIO 방식 파일 생성 및 초기화");
    }
}
