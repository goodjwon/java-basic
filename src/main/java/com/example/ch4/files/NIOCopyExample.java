package com.example.ch4.files;

import java.io.IOException;
import java.nio.file.*;

public class NIOCopyExample {
    public static void main(String[] args) {
        Path source = Paths.get("example.txt");
        Path target = Paths.get("target.txt");

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
