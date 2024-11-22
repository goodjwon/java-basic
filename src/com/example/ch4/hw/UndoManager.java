package com.example.ch4.hw;

import java.util.ArrayDeque;

public class UndoManager {
    private ArrayDeque<Action> actionStack;

    public UndoManager() {
        actionStack = new ArrayDeque<>();
    }

    // 작업 수행
    public void performAction(Action action) {
        action.execute();
        actionStack.push(action);
    }

    // 실행 취소
    public void undo() {
        if (!actionStack.isEmpty()) {
            Action action = actionStack.pop();
            action.undo();
        } else {
            System.out.println("실행 취소할 작업이 없습니다.");
        }
    }

    // 테스트
    public static void main(String[] args) {
        UndoManager manager = new UndoManager();
        Action drawLine = new DrawLineAction();
        manager.performAction(drawLine);

        manager.undo(); // 실행 취소
    }
}

interface Action {
    void execute();
    void undo();
}

class DrawLineAction implements Action {
    @Override
    public void execute() {
        System.out.println("선을 그립니다.");
    }

    @Override
    public void undo() {
        System.out.println("선을 지웁니다.");
    }
}
