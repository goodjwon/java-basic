package com.example.ch4.files;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTxtExample {
    public static void main(String[] args) {
        String filename = "output.txt";

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("문자 기반 출력\n");
            fw.write("두 번째 줄");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
