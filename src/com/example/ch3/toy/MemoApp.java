package com.example.ch3.toy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoApp {

    // 메모 클래스 정의
    static class Memo {
        private static int idCounter = 1;
        private int id;
        private String title;
        private String content;
        private String createdDate;
        private String modifiedDate;

        public Memo(String title, String content) {
            this.id = idCounter++;
            this.title = title;
            this.content = content;
            this.createdDate = getCurrentDateTime();
            this.modifiedDate = getCurrentDateTime();
        }

        public void updateContent(String title, String content) {
            this.title = title;
            this.content = content;
            this.modifiedDate = getCurrentDateTime();
        }

        @Override
        public String toString() {
            return "ID: " + id + "\nTitle: " + title + "\nContent: " + content +
                    "\nCreated: " + createdDate + "\nModified: " + modifiedDate + "\n";
        }

        private static String getCurrentDateTime() {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }

    // 메모 리스트
    private static List<Memo> memoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Memo Application ===");
            System.out.println("1. Add Memo");
            System.out.println("2. List Memos");
            System.out.println("3. Search Memo");
            System.out.println("4. Edit Memo");
            System.out.println("5. Delete Memo");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addMemo(scanner);
                    break;
                case 2:
                    listMemos();
                    break;
                case 3:
                    searchMemo(scanner);
                    break;
                case 4:
                    editMemo(scanner);
                    break;
                case 5:
                    deleteMemo(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addMemo(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        Memo memo = new Memo(title, content);
        memoList.add(memo);
        System.out.println("Memo added successfully!");
    }

    private static void listMemos() {
        if (memoList.isEmpty()) {
            System.out.println("No memos found.");
            return;
        }
        for (Memo memo : memoList) {
            System.out.println(memo);
        }
    }

    private static void searchMemo(Scanner scanner) {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        boolean found = false;
        for (Memo memo : memoList) {
            if (memo.getTitle().contains(keyword)) {
                System.out.println(memo);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No memos matched your search.");
        }
    }

    private static void editMemo(Scanner scanner) {
        System.out.print("Enter the ID of the memo to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Memo memo : memoList) {
            if (memo.getId() == id) {
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new content: ");
                String newContent = scanner.nextLine();
                memo.updateContent(newTitle, newContent);
                System.out.println("Memo updated successfully!");
                return;
            }
        }
        System.out.println("Memo with the given ID not found.");
    }

    private static void deleteMemo(Scanner scanner) {
        System.out.print("Enter the ID of the memo to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Memo memo : memoList) {
            if (memo.getId() == id) {
                memoList.remove(memo);
                System.out.println("Memo deleted successfully!");
                return;
            }
        }
        System.out.println("Memo with the given ID not found.");
    }
}
