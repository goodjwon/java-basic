package com.example.ch3.hw;

import java.util.ArrayList;
import java.util.List;

public class FileNode {
    private String name;
    private boolean isFile;
    private List<FileNode> children;

    // 생성자
    public FileNode(String name, boolean isFile) {
        this.name = name;
        this.isFile = isFile;
        if (!isFile) {
            children = new ArrayList<>();
        }
    }

    // 자식 노드 추가
    public void addChild(FileNode child) {
        if (!isFile) {
            children.add(child);
        }
    }

    // 트리 구조 출력
    public void printTree(String indent) {
        System.out.println(indent + (isFile ? "파일: " : "폴더: ") + name);
        if (children != null) {
            for (FileNode child : children) {
                child.printTree(indent + "    ");
            }
        }
    }

    // 테스트
    public static void main(String[] args) {
        // 루트 폴더 생성
        FileNode root = new FileNode("루트", false);

        // 서브 폴더 및 파일 생성
        FileNode documents = new FileNode("문서", false);
        FileNode photos = new FileNode("사진", false);
        FileNode file1 = new FileNode("Resume.docx", true);
        FileNode file2 = new FileNode("Vacation.jpg", true);

        // 구조 구성
        documents.addChild(file1);
        photos.addChild(file2);
        root.addChild(documents);
        root.addChild(photos);

        // 트리 출력
        root.printTree("");
    }
}
