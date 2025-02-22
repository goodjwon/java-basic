package com.example.ch4.network;

import java.io.*;
import java.net.*;

import java.util.Scanner;

public class SimpleTCPClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // 서버 IP
        int port = 12345; // 서버 포트

        Socket socket = new Socket(serverAddress, port);
        System.out.println("서버에 연결됨");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("입력: ");
            String userInput = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userInput)) break;

            out.println(userInput);
            System.out.println("서버 응답: " + in.readLine());
        }

        scanner.close();
        socket.close();
    }
}
