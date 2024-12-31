package com.example.ch4.toy.game.online.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

// 간단한 유저 데이터 모델
class UserData {
    private String userName;
    private String characterName;
    private int level;
    private int exp;

    // 기본 생성자 (Gson 역직렬화 시 필요)
    public UserData() { }

    public UserData(String userName) {
        this.userName = userName;
        this.characterName = "Newbie";
        this.level = 1;
        this.exp = 0;
    }

    public String getUserName() { return userName; }
    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }
    public int getLevel() { return level; }
    public int getExp() { return exp; }

    public void addExp(int amount) {
        exp += amount;
        while (exp >= 100) {
            exp -= 100;
            level++;
        }
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", characterName='" + characterName + '\'' +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }
}

public class GameServer {

    private static final int PORT = 3000;

    // 유저 상태 메모리 캐싱 (프로토타입용)
    private static Map<String, UserData> userDataMap = new HashMap<>();

    // 유저 정보를 저장할 파일(DB 대체)
    private static final String USER_DATA_FILE = "user_data.json";

    // 서버 이벤트/로그 기록 파일
    private static final String SERVER_LOG_FILE = "server_logs.jsonl";

    public static void main(String[] args) {
        // 1) 기존 user_data.json 로드
        loadUserDataFromFile();

        // 2) 서버 소켓 열기
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[서버 시작] 포트: " + PORT);

            // 간단히 단일 스레드로 계속 대기
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[클라이언트 연결] " + clientSocket.getInetAddress());
                // 클라이언트가 들어올 때마다 처리
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 서버가 완전히 종료될 때 (강제) 유저 데이터를 파일에 저장
            saveUserDataToFile();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String input;
            while ((input = reader.readLine()) != null) {
                System.out.println("[수신] " + input);

                // 명령어 분리 (최대 2덩이만 split)
                String[] tokens = input.split(" ", 2);
                String command = tokens[0];

                switch (command) {
                    case "LOGIN": {
                        // LOGIN userA
                        if (tokens.length < 2) {
                            writer.println("ERROR Invalid LOGIN command");
                            break;
                        }
                        String userName = tokens[1];
                        UserData userData = userDataMap.get(userName);
                        if (userData == null) {
                            userData = new UserData(userName);
                            userDataMap.put(userName, userData);
                            writer.println("NEW_CHARACTER_CREATED " + userData.getCharacterName());
                            logEvent("USER_CREATED", userName, null);
                        } else {
                            writer.println("WELCOME_BACK " + userData.getCharacterName());
                            logEvent("USER_LOGIN", userName, null);
                        }
                        // 변경사항 즉시 파일 반영
                        saveUserDataToFile();
                        break;
                    }
                    case "GET_STATUS": {
                        // GET_STATUS userA
                        if (tokens.length < 2) {
                            writer.println("ERROR Invalid GET_STATUS command");
                            break;
                        }
                        String userName = tokens[1];
                        UserData userData = userDataMap.get(userName);
                        if (userData == null) {
                            writer.println("ERROR User not found");
                        } else {
                            writer.println("STATUS " + userData.toString());
                        }
                        break;
                    }
                    case "ADD_EXP": {
                        // ADD_EXP userA 50
                        if (tokens.length < 2) {
                            writer.println("ERROR Invalid ADD_EXP command");
                            break;
                        }
                        String[] subTokens = tokens[1].split(" ");
                        if (subTokens.length < 2) {
                            writer.println("ERROR Invalid ADD_EXP params");
                            break;
                        }
                        String userName = subTokens[0];
                        int amount = Integer.parseInt(subTokens[1]);
                        UserData userData = userDataMap.get(userName);
                        if (userData == null) {
                            writer.println("ERROR User not found");
                        } else {
                            userData.addExp(amount);
                            writer.println("EXP_ADDED " + userData.getExp());
                            // 로그 남기기
                            logEvent("ADD_EXP", userName, "amount=" + amount);
                            // 변경사항 파일 반영
                            saveUserDataToFile();
                        }
                        break;
                    }
                    case "LOG_JSON": {
                        // LOG_JSON {json}
                        if (tokens.length < 2) {
                            writer.println("ERROR Invalid LOG_JSON command");
                            break;
                        }
                        String jsonStr = tokens[1];
                        // 콘솔 표시
                        System.out.println("[LOG_JSON] " + jsonStr);
                        // 서버 로그 파일에 기록
                        writeServerLog(jsonStr);
                        writer.println("LOG_JSON_OK");
                        break;
                    }
                    case "LOGOUT": {
                        // LOGOUT userA
                        if (tokens.length < 2) {
                            writer.println("ERROR Invalid LOGOUT command");
                            break;
                        }
                        String userName = tokens[1];
                        writer.println("LOGOUT_OK");
                        logEvent("USER_LOGOUT", userName, null);
                        break;
                    }
                    default:
                        writer.println("ERROR Unknown command");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 연결 끊길 때도 저장
            saveUserDataToFile();
            try {
                clientSocket.close();
            } catch (IOException ignore) {}
        }
    }

    // -----------------------------
    // 유저 데이터 파일 저장/로드 (Gson)
    // -----------------------------
    private static void loadUserDataFromFile() {
        File file = new File(USER_DATA_FILE);
        if (!file.exists()) {
            System.out.println("[서버] user_data.json 없음. 새로 생성 예정.");
            return;
        }
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            // userDataMap = { "userA": { ... }, ... }
            userDataMap = gson.fromJson(reader,
                    new TypeToken<Map<String, UserData>>(){}.getType());
            if (userDataMap == null) userDataMap = new HashMap<>();
            System.out.println("[서버] 기존 user_data.json 로딩 완료, 유저 수: " + userDataMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveUserDataToFile() {
        try (FileWriter writer = new FileWriter(USER_DATA_FILE)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userDataMap, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // 서버 로그 파일 기록
    // -----------------------------
    private static void logEvent(String eventType, String userName, String detail) {
        // 간단한 JSON 객체를 만들기 위해 StringBuilder나 JsonObject 등을 사용할 수 있음
        // 여기서는 String을 직접 조립하거나, 혹은 Gson 이용해도 됨
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"timestamp\":\"").append(Instant.now().toString()).append("\",");
        sb.append("\"eventType\":\"").append(eventType).append("\",");
        sb.append("\"userName\":\"").append(userName).append("\"");
        if (detail != null) {
            sb.append(",\"detail\":\"").append(detail).append("\"");
        }
        sb.append("}");

        writeServerLog(sb.toString());
    }

    private static void writeServerLog(String jsonLine) {
        try (FileWriter fw = new FileWriter(SERVER_LOG_FILE, true)) {
            fw.write(jsonLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
