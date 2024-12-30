package com.example.ch4.files.io;

import java.io.*;

public class BasicFileIOExample {
    public static void main(String[] args) {
        String filename = "example.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("첫 번째 줄");
            writer.newLine();
            writer.write("두 번째 줄");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("읽은 내용: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
