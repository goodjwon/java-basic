package com.example.ch4.toy.game.online.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;
import java.time.Instant;
import java.util.Scanner;

/**
 * Client Actions
 *
 * LOGIN userA
 * ADD_EXP userA 50
 * GET_STATUS userA
 * SEND_MONSTER_KILL_LOG userA slime dungeon1 10
 * LOGOUT userA
 * quit
 */
public class GameClient {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 3000;

    // 클라이언트에서 참조할 기초 데이터
    private static Object gameData;

    // 클라이언트 이벤트 로그 파일 (오프라인 대비)
    private static final String CLIENT_LOG_FILE = "client_logs.jsonl";

    public static void main(String[] args) {
        // 1) 로컬 game_data.json 로드
        loadLocalGameData();

        // 2) 서버와 연결 (재시도 로직 적용)
        try (
                Socket socket = connectWithRetry(SERVER_HOST, SERVER_PORT, 5, 5000);
                BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("[클라이언트] 서버에 연결됨: " + SERVER_HOST + ":" + SERVER_PORT);

            // 간단한 명령어 루프
            while (true) {
                System.out.print("명령어 입력(quit=종료): ");
                String cmd = scanner.nextLine();
                if ("quit".equalsIgnoreCase(cmd)) {
                    break;
                }

                // 예시 명령:
                //   LOGIN userA
                //   ADD_EXP userA 50
                //   GET_STATUS userA
                //   SEND_MONSTER_KILL_LOG userA slime dungeon1 10
                //   LOGOUT userA
                //   ...

                if (cmd.startsWith("SEND_MONSTER_KILL_LOG")) {
                    // 예) SEND_MONSTER_KILL_LOG userA slime dungeon1 10
                    String[] parts = cmd.split(" ");
                    if (parts.length < 5) {
                        System.out.println("사용법: SEND_MONSTER_KILL_LOG <userId> <monsterId> <mapId> <expGained>");
                        continue;
                    }
                    String userId = parts[1];
                    String monsterId = parts[2];
                    String mapId = parts[3];
                    int expGained = Integer.parseInt(parts[4]);

                    // JSON 로그 생성 후 서버에 전송
                    sendMonsterKillLog(serverWriter, userId, monsterId, mapId, expGained);

                    // 서버 응답 한 줄 수신
                    String response = serverReader.readLine();
                    if (response == null) {
                        System.out.println("[서버와 연결 끊김] 프로그램을 종료합니다.");
                        break;
                    }
                    System.out.println("[서버응답] " + response);

                } else {
                    // 일반 명령 그대로 전송
                    serverWriter.println(cmd);

                    String response = serverReader.readLine();
                    if (response == null) {
                        System.out.println("[서버와 연결 끊김] 프로그램을 종료합니다.");
                        break;
                    }
                    System.out.println("[서버응답] " + response);
                }
            }

            System.out.println("[클라이언트 종료]");
        } catch (Exception e) {
            // 최대 재시도 횟수 초과 등으로 연결에 실패하면 여기로 옴
            System.err.println("[클라이언트] 서버에 접속할 수 없습니다: " + e.getMessage());
            System.err.println("서버가 꺼져 있거나, 네트워크 문제일 수 있습니다.");
            System.out.println("프로그램을 종료합니다. 서버를 실행 후 다시 시도해 주세요.");
        }
    }

    /**
     * 서버 소켓 연결 재시도 로직
     *
     * @param host       서버 호스트
     * @param port       서버 포트
     * @param retries    최대 재시도 횟수
     * @param waitMillis 각 재시도 사이 대기 시간 (밀리초)
     * @return 연결 성공 시 Socket 반환
     * @throws Exception 재시도 횟수 모두 실패 시 예외 발생
     */
    private static Socket connectWithRetry(String host, int port, int retries, long waitMillis) throws Exception {
        int attempt = 0;
        while (true) {
            try {
                attempt++;
                System.out.println("[클라이언트] 서버 연결 시도 중... (시도 횟수: " + attempt + ")");
                Socket sock = new Socket(host, port);  // 성공 시 반환
                System.out.println("[클라이언트] 서버 연결 성공!");
                return sock;
            } catch (IOException e) {
                System.err.println("[클라이언트] 서버 연결 실패(" + attempt + "회차): " + e.getMessage());
                if (attempt >= retries) {
                    throw new Exception("서버 연결 재시도 (" + retries + "번) 모두 실패");
                }
                // 재시도 대기
                System.out.println(waitMillis / 1000 + "초 후 재시도합니다...");
                try {
                    Thread.sleep(waitMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new Exception("서버 연결 대기 중 인터럽트 발생", ie);
                }
            }
        }
    }

    // -----------------------------
    // 로컬 기초 데이터 로딩 (game_data.json) - 클래스패스에서 읽기
    // -----------------------------
    private static void loadLocalGameData() {
        try (InputStream in = GameClient.class.getClassLoader().getResourceAsStream("game_data.json")) {
            if (in == null) {
                System.out.println("[클라이언트] /game_data.json 리소스를 찾을 수 없습니다.");
                return;
            }
            Gson gson = new Gson();
            gameData = gson.fromJson(new InputStreamReader(in), Object.class);
            System.out.println("[클라이언트] game_data.json 로딩 완료 (클래스패스 리소스).");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // 몬스터 처치 이벤트를 서버로 LOG_JSON 전송
    // -----------------------------
    private static void sendMonsterKillLog(PrintWriter serverWriter,
                                           String userId,
                                           String monsterId,
                                           String mapId,
                                           int expGained) {
        // Gson의 JsonObject 사용
        JsonObject log = new JsonObject();
        log.addProperty("timestamp", Instant.now().toString());
        log.addProperty("userId", userId);
        log.addProperty("eventType", "MONSTER_KILL");

        JsonObject details = new JsonObject();
        details.addProperty("monsterId", monsterId);
        details.addProperty("mapId", mapId);
        details.addProperty("expGained", expGained);
        log.add("details", details);

        // LOG_JSON <json 문자열> 형태로 전송
        String command = "LOG_JSON " + log.toString();
        serverWriter.println(command);

        // 오프라인 모드 대비해서 클라이언트도 로컬 로그에 기록 가능
        writeClientLog(log.toString());
    }

    // -----------------------------
    // 클라이언트 로컬 로그 파일에 기록 (오프라인 모드 대비)
    // -----------------------------
    private static void writeClientLog(String jsonLine) {
        try (FileWriter fw = new FileWriter(CLIENT_LOG_FILE, true)) {
            fw.write(jsonLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
