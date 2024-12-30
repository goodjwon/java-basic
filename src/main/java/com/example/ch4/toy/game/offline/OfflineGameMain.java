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

        // 데이터 경로 설정 (유저 데이터와 로그 저장용)
        String dataPath = System.getProperty("user.dir") + "/game_data";
        String backupPath = dataPath + "/backup";
        String playerDataPath = dataPath + "/player_data.txt";
        String logFilePath = dataPath + "/game_log.txt";

        // 디렉터리 생성
        FileManager.createDirectory(dataPath);
        FileManager.createDirectory(backupPath);

        // 유저 데이터 확인 및 생성
        File playerDataFile = new File(playerDataPath);
        if (!playerDataFile.exists()) {
            System.out.println("No player data found. Let's create a new player!");
            createPlayerData(playerDataPath);
        }

        // 유저 데이터 로드
        try {
            String playerData = FileManager.readFile(playerDataPath);
            System.out.println("Player Data:\n" + playerData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 맵 데이터 로드 (클래스패스를 통해 접근)
        try (InputStream mapFileStream = OfflineGameMain.class.getClassLoader().getResourceAsStream("map_data.json")) {
            if (mapFileStream == null) {
                throw new FileNotFoundException("map_data.json not found in resources.");
            }

            // JSON 파싱
            Gson gson = new Gson();
            Map<String, Object> mapData = gson.fromJson(new InputStreamReader(mapFileStream), Map.class);
            System.out.println("Map Data Loaded: " + mapData);
        } catch (Exception e) {
            System.out.println("Error loading map data: " + e.getMessage());
        }

        // 게임 로직 실행
        GameManager gameManager = new GameManager(dataPath, logFilePath);
        gameManager.enterDungeon("Haunted Grove");
        gameManager.defeatMonster("Goblin", 50, "Health Potion");
        gameManager.defeatBoss("Necromancer", 300, 100);

        // 게임 종료 및 데이터 저장
        gameManager.saveAndExit();
    }


    private static void createPlayerData(String filePath) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();

            String defaultData = String.format(
                    "PlayerName: %s\nLevel: 1\nExperience: 0\nInventory: None\nGold: 0\n",
                    playerName
            );
            FileManager.writeFile(filePath, defaultData);
            System.out.println("Player data created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

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
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
        System.out.println("File written: " + filePath);
    }

    public static void moveFile(String srcPath, String destPath) throws IOException {
        Files.move(Paths.get(srcPath), Paths.get(destPath));
        System.out.println("File moved: " + srcPath + " -> " + destPath);
    }
}

class MapLoader {
    public void loadMapData(String mapFilePath) {
        try {
            // 파일 읽기
            if (!Files.exists(Paths.get(mapFilePath))) {
                throw new IOException("File not found: " + mapFilePath);
            }

            String json = FileManager.readFile(mapFilePath);
            Gson gson = new Gson();
            Map<String, Object> mapData = gson.fromJson(json, Map.class);

            // 맵 데이터 출력
            System.out.println("Map Data Loaded: " + mapData);
        } catch (IOException e) {
            System.out.println("Error loading map data: " + e.getMessage());
        }
    }
}

class GameManager {
    private final String dataPath;
    private final String logFilePath;

    public GameManager(String dataPath, String logFilePath) {
        this.dataPath = dataPath;
        this.logFilePath = logFilePath;
    }

    public void enterDungeon(String dungeonName) {
        logActivity("Entered dungeon: " + dungeonName);
    }

    public void defeatMonster(String monsterName, int experience, String loot) {
        logActivity(String.format("Defeated monster: %s. Earned %d XP and found %s.", monsterName, experience, loot));
    }

    public void defeatBoss(String bossName, int experience, int gold) {
        logActivity(String.format("Defeated boss: %s. Earned %d XP and %d Gold.", bossName, experience, gold));
    }

    private void logActivity(String activity) {
        String logEntry = String.format("[%tF %<tT] %s\n", System.currentTimeMillis(), activity);
        try {
            FileManager.writeFile(logFilePath, logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAndExit() {
        try {
            String backupPath = dataPath + "/backup/game_log_" + System.currentTimeMillis() + ".txt";
            FileManager.moveFile(logFilePath, backupPath);
            System.out.println("Game log backed up to: " + backupPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
