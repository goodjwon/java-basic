package com.example.ch4.files;

import java.io.*;

public class BinaryCopyExample {
    public static void main(String[] args) {
        String src = "example.txt";
        String dest = "dest.bin";

        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
