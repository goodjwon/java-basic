package com.example.ch4.files.acc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FileSearchExample {
    public static void main(String[] args) {
        // IO 방식
        PerformanceUtil.measurePerformance(() -> {
            File dir = new File("example_dir");
            searchFile(dir, "target_file.txt");
        }, "IO 방식 파일 검색");

        // NIO 방식
        PerformanceUtil.measurePerformance(() -> {
            Path dir = Paths.get("example_dir");
            try {
                Files.walk(dir)
                        .filter(path -> path.getFileName().toString().equals("target_file.txt"))
                        .forEach(path -> System.out.println("파일 발견: " + path.toAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "NIO 방식 파일 검색");
    }

    private static void searchFile(File dir, String fileName) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchFile(file, fileName);
            } else if (file.getName().equals(fileName)) {
                System.out.println("파일 발견: " + file.getAbsolutePath());
            }
        }
    }
}
