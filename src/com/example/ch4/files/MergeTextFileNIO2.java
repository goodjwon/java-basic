package com.example.ch4.files;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class MergeTextFileNIO2 {
    public static void main(String[] args) {
        Path file1 = Paths.get("largeFile1.txt");
        Path file2 = Paths.get("largeFile2.txt");
        Path mergedFile = Paths.get("merged.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(mergedFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            mergeFile(file1, writer);
            mergeFile(file2, writer);
            System.out.println("대용량 파일 병합 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mergeFile(Path file, BufferedWriter writer) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            lines.forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }
}
