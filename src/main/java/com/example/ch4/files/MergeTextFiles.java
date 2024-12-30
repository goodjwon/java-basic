package com.example.ch4.files;

import java.io.*;

public class MergeTextFiles {
    public static void main(String[] args) {
        String file1 = "largeFile1.txt";
        String file2 = "largeFile1.txt";
        String outputFile = "mergedFile.txt";

        try (
                BufferedReader br1 = new BufferedReader(new FileReader(file1));
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {
            mergeFiles(br1, bw);
            mergeFiles(br2, bw);
            System.out.println("파일 병합 완료: " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mergeFiles(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
    }
}