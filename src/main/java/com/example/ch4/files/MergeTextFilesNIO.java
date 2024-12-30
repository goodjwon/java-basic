package com.example.ch4.files;

import java.nio.file.*;
import java.io.IOException;

public class MergeTextFilesNIO {
    public static void main(String[] args) throws IOException {
        Path file1 = Paths.get("largeFile1.txt");
        Path file2 = Paths.get("largeFile2.txt");
        Path mergedFile = Paths.get("merged.txt");

        Files.write(mergedFile, Files.readAllLines(file1));
        Files.write(mergedFile, Files.readAllLines(file2), StandardOpenOption.APPEND);
        System.out.println("병합 완료");
    }
}
