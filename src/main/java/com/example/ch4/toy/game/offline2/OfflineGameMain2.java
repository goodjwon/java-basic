package com.example.ch4.toy.game.offline2;


import com.google.gson.Gson;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class OfflineGameMain2 {

    public static void main(String[] args) {
        System.out.println("Welcome to My Offline Game!");

        // 데이터 경로 설정
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
        Player player = null;
        try {
            player = GameDataManager.loadPlayerData(playerDataPath);
            if (player != null) {
                System.out.println("Player Data Loaded:\n" + player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 맵 데이터 로드
        MapData mapData = GameDataManager.loadMapData();
        if (mapData != null) {
            System.out.println("Map Data Loaded: " + mapData.getMaps());
        }

        // 게임 로직 실행
        GameManager gameManager = new GameManager(dataPath, logFilePath);
        gameManager.enterDungeon("Haunted Grove");
        gameManager.defeatMonster("Goblin", 50, "Health Potion");
        gameManager.defeatBoss("Necromancer", 300, 100);

        // 게임 종료 및 데이터 저장
        gameManager.saveAndExit(player);
    }

    private static void createPlayerData(String filePath) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();

            Player player = new Player(playerName);
            GameDataManager.savePlayerData(filePath, player);
            System.out.println("Player data created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 파일 관리 클래스
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

// 게임 데이터 관리 클래스
class GameDataManager {
    private static final Gson gson = new Gson();

    // 플레이어 데이터 로드
    public static Player loadPlayerData(String filePath) throws IOException {
        String playerData = FileManager.readFile(filePath);
        String[] lines = playerData.split("\n");

        String name = lines[0].split(": ")[1];
        int level = Integer.parseInt(lines[1].split(": ")[1]);
        int experience = Integer.parseInt(lines[2].split(": ")[1]);
        String inventory = lines[3].split(": ")[1];
        int gold = Integer.parseInt(lines[4].split(": ")[1]);

        return new Player(name, level, experience, inventory, gold);
    }

    // 플레이어 데이터 저장
    public static void savePlayerData(String filePath, Player player) throws IOException {
        String playerData = String.format(
                "PlayerName: %s\nLevel: %d\nExperience: %d\nInventory: %s\nGold: %d\n",
                player.getName(), player.getLevel(), player.getExperience(), player.getInventory(), player.getGold()
        );
        FileManager.writeFile(filePath, playerData);
    }

    // 맵 데이터 로드
    public static MapData loadMapData() {
        try (InputStream mapFileStream = OfflineGameMain2.class.getClassLoader().getResourceAsStream("map_data.json")) {
            if (mapFileStream == null) {
                throw new FileNotFoundException("map_data.json not found in resources.");
            }

            return gson.fromJson(new InputStreamReader(mapFileStream), MapData.class);
        } catch (Exception e) {
            System.out.println("Error loading map data: " + e.getMessage());
            return null;
        }
    }
}

// 플레이어 클래스
class Player {
    private String name;
    private int level;
    private int experience;
    private String inventory;
    private int gold;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.inventory = "None";
        this.gold = 0;
    }

    public Player(String name, int level, int experience, String inventory, int gold) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.inventory = inventory;
        this.gold = gold;
    }

    // 경험치 추가
    public void gainExperience(int exp) {
        this.experience += exp;
        if (this.experience >= 1000) {
            this.level++;
            this.experience = 0;
        }
    }

    // 골드 추가
    public void addGold(int gold) {
        this.gold += gold;
    }

    // 아이템 추가
    public void addItem(String item) {
        if (this.inventory.equals("None")) {
            this.inventory = item;
        } else {
            this.inventory += ", " + item;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "PlayerName: %s\nLevel: %d\nExperience: %d\nInventory: %s\nGold: %d",
                name, level, experience, inventory, gold
        );
    }

    // Getter 메서드
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public String getInventory() { return inventory; }
    public int getGold() { return gold; }
}

// 맵 데이터 클래스
class MapData {
    private List<Map> maps;

    public static class Map {
        private String name;
        private List<Dungeon> dungeons;

        public static class Dungeon {
            private String name;
            private List<String> monsters;
            private Boss boss;

            public static class Boss {
                private String name;
                private Rewards rewards;

                public static class Rewards {
                    private int experience;
                    private int gold;
                }
            }
        }
    }

    // Getter 메서드
    public List<Map> getMaps() { return maps; }
}

// 게임 로그 클래스
class GameLog {
    private List<String> logs;

    public GameLog() {
        this.logs = new ArrayList<>();
    }

    // 활동 로그 추가
    public void logActivity(String activity) {
        logs.add("[" + System.currentTimeMillis() + "] " + activity);
    }

    // 로그 목록 반환
    public List<String> getLogs() {
        return logs;
    }
}

// 게임 관리 클래스
class GameManager {
    private final String dataPath;
    private final String logFilePath;
    private final GameLog gameLog;

    public GameManager(String dataPath, String logFilePath) {
        this.dataPath = dataPath;
        this.logFilePath = logFilePath;
        this.gameLog = new GameLog();
    }

    public void enterDungeon(String dungeonName) {
        gameLog.logActivity("Entered dungeon: " + dungeonName);
    }

    public void defeatMonster(String monsterName, int experience, String loot) {
        gameLog.logActivity(String.format("Defeated monster: %s. Earned %d XP and found %s.", monsterName, experience, loot));
    }

    public void defeatBoss(String bossName, int experience, int gold) {
        gameLog.logActivity(String.format("Defeated boss: %s. Earned %d XP and %d Gold.", bossName, experience, gold));
    }

    public void saveAndExit(Player player) {
        try {
            // 플레이어 데이터 저장
            GameDataManager.savePlayerData(dataPath + "/player_data.txt", player);

            // 로그 파일 저장
            StringBuilder logContent = new StringBuilder();
            for (String log : gameLog.getLogs()) {
                logContent.append(log).append("\n");
            }
            FileManager.writeFile(logFilePath, logContent.toString());

            // 로그 파일 백업
            String backupPath = dataPath + "/backup/game_log_" + System.currentTimeMillis() + ".txt";
            FileManager.moveFile(logFilePath, backupPath);
            System.out.println("Game log backed up to: " + backupPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}