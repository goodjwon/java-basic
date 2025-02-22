package com.example.ch4.network;

import java.net.*;

import java.util.Scanner;

public class SimpleUDPClient {
    public static void main(String[] args) throws Exception {
        String serverAddress = "127.0.0.1"; // 서버 IP
        int port = 12346; // 서버 포트
        DatagramSocket socket = new DatagramSocket();

        byte[] buffer = new byte[1024];
        InetAddress serverIP = InetAddress.getByName(serverAddress);
        Scanner scanner = new Scanner(System.in); // Scanner 사용

        System.out.println("UDP 서버에 메시지 보내기. 종료하려면 `exit` 입력");

        while (true) {
            System.out.print("메시지 입력: ");
            String message = scanner.nextLine(); // Scanner로 입력 받기
            if ("exit".equalsIgnoreCase(message)) break;

            byte[] messageBytes = message.getBytes();
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, serverIP, port);
            socket.send(packet);

            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("서버 응답: " + response);
        }

        scanner.close();
        socket.close();
    }
}

