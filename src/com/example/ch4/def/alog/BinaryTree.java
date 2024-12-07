package com.example.ch4.def.alog;


public class BinaryTree {
    TreeNode root;

    // 노드 추가
    public void add(int data) {
        root = addRecursive(root, data);
    }

    // 재귀적으로 노드 추가
    private TreeNode addRecursive(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }

        if (data < current.data) {
            current.left = addRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = addRecursive(current.right, data);
        }

        return current;
    }

    // 중위 순회 (왼쪽 - 루트 - 오른쪽)
    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" _ " + node.data);
            traverseInOrder(node.right);
        }
    }

    // 테스트
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        /* 트리 구조
                [6]
               /   \
             [4]   [8]
             / \   / \
           [3][5][7][9]
        */

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(9);
        bt.add(5);
        bt.add(7);
        bt.add(3);

        System.out.print("중위 순회 결과:");
        bt.traverseInOrder(bt.root); // 출력: 3 4 5 6 7 8 9
    }
}

