package com.example.ch4.toy.game.offline;

import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class OfflineGameMain {
    public static void main(String[] args) {
        System.out.println("Welcome to My Offline Game!");

        // Task 1.1: 데이터 경로 설정 (유저 데이터와 로그 저장용)
        String dataPath = System.getProperty("user.dir") + "/game_data";
        String backupPath = dataPath + "/backup"; // Task 1.3: 로그 백업 경로
        String playerDataPath = dataPath + "/player_data.txt"; // Task 1.1: 유저 데이터 경로
        String logFilePath = dataPath + "/game_log.txt"; // Task 1.2: 활동 로그 경로

        // Task 1.1: 디렉터리 생성
        FileManager.createDirectory(dataPath);
        FileManager.createDirectory(backupPath);

        // Task 1.1: 유저 데이터 확인 및 생성
        File playerDataFile = new File(playerDataPath);
        if (!playerDataFile.exists()) {
            System.out.println("No player data found. Let's create a new player!");
            createPlayerData(playerDataPath); // Task 1.1: 새 유저 데이터 생성
        }

        // Task 1.1: 유저 데이터 로드
        try {
            String playerData = FileManager.readFile(playerDataPath);
            System.out.println("Player Data:\n" + playerData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Task 2.1: 맵 데이터 로드 (클래스 로더 활용)
        MapLoader mapLoader = new MapLoader();
        InputStream mapFileStream = OfflineGameMain.class.getClassLoader().getResourceAsStream("map_data.json");
        if (mapFileStream == null) {
            System.out.println("map_data.json not found in classpath.");
        } else {
            mapLoader.loadMapData(mapFileStream); // Task 2.1: JSON 데이터 로드
        }

        // Task 3.1: 게임 로직 실행
        GameManager gameManager = new GameManager(dataPath, logFilePath);
        gameManager.enterDungeon("Haunted Grove"); // Task 3.1: 던전 진입
        gameManager.defeatMonster("Goblin", 50, "Health Potion"); // Task 3.2: 몬스터 처치
        gameManager.defeatBoss("Necromancer", 300, 100); // Task 3.2: 보스 처치

        // Task 3.3: 게임 종료 및 데이터 저장
        gameManager.saveAndExit();
    }

    // Task 1.1: 새 유저 데이터 생성
    private static void createPlayerData(String filePath) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();

            String defaultData = String.format(
                    "PlayerName: %s\nLevel: 1\nExperience: 0\nInventory: None\nGold: 0\n",
                    playerName
            );
            FileManager.writeFile(filePath, defaultData); // Task 1.1: 기본 데이터 저장
            System.out.println("Player data created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Task 1.1, Task 1.3: 파일 및 디렉터리 관리 유틸리티
class FileManager {
    public static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Directory created: " + path);
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath))); // Task 1.1: 파일 읽기
    }

    public static void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes()); // Task 1.1: 파일 쓰기
        System.out.println("File written: " + filePath);
    }

    public static void moveFile(String srcPath, String destPath) throws IOException {
        Files.move(Paths.get(srcPath), Paths.get(destPath)); // Task 1.3: 파일 이동 (백업)
        System.out.println("File moved: " + srcPath + " -> " + destPath);
    }
}

// Task 2.1: 맵 데이터 로드 및 출력
class MapLoader {
    public void loadMapData(InputStream mapFileStream) {
        try (InputStreamReader reader = new InputStreamReader(mapFileStream)) {
            Gson gson = new Gson();
            Map<String, Object> mapData = gson.fromJson(reader, Map.class); // Task 2.1: JSON 데이터 파싱
            System.out.println("Map Data Loaded: " + mapData);
        } catch (IOException e) {
            System.out.println("Error loading map data: " + e.getMessage());
        }
    }
}

// Task 3.1, Task 3.2, Task 3.3: 게임 로직 처리
// Task 3.1, Task 3.2, Task 3.3: 게임 로직 처리
class GameManager {
    private final String dataPath;  // Task 1.1: 유저 데이터 관리 경로
    private final String logFilePath;  // Task 1.2: 로그 파일 경로

    // Task 3.1: 생성자에서 게임 데이터 및 로그 파일 경로 설정
    public GameManager(String dataPath, String logFilePath) {
        this.dataPath = dataPath;
        this.logFilePath = logFilePath;
    }

    // Task 3.1: 던전 진입 이벤트 처리
    public void enterDungeon(String dungeonName) {
        // 던전 진입 기록
        logActivity("Entered dungeon: " + dungeonName); // Task 3.1: 던전 이름 로그에 기록
    }

    // Task 3.2: 몬스터 처치 이벤트 처리
    public void defeatMonster(String monsterName, int experience, String loot) {
        // 몬스터 처치 기록
        logActivity(String.format(
                "Defeated monster: %s. Earned %d XP and found %s.",
                monsterName, experience, loot
        )); // Task 3.2: 몬스터 이름, 경험치, 전리품 로그에 기록
    }

    // Task 3.2: 보스 처치 이벤트 처리
    public void defeatBoss(String bossName, int experience, int gold) {
        // 보스 처치 기록
        logActivity(String.format(
                "Defeated boss: %s. Earned %d XP and %d Gold.",
                bossName, experience, gold
        )); // Task 3.2: 보스 이름, 경험치, 골드 로그에 기록
    }

    // Task 1.2, Task 3.2: 활동 로그 작성
    private void logActivity(String activity) {
        // 로그에 시간 정보 포함하여 기록
        String logEntry = String.format("[%tF %<tT] %s\n", System.currentTimeMillis(), activity); // Task 1.2: 활동 시간 기록
        try {
            FileManager.writeFile(logFilePath, logEntry); // Task 1.2: 로그 파일에 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Task 3.3: 게임 종료 및 로그 백업
    public void saveAndExit() {
        try {
            // Task 1.3: 백업 경로 생성
            String backupPath = dataPath + "/backup/game_log_" + System.currentTimeMillis() + ".txt";
            // Task 1.3: 로그 파일 이동 (백업 처리)
            FileManager.moveFile(logFilePath, backupPath);
            System.out.println("Game log backed up to: " + backupPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
