package com.example.ch4.exception;

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class BusinessLogicExceptionExample {
    public static void main(String[] args) {
        try {
            withdraw(1000, 5000);
        } catch (InsufficientBalanceException e) {
            System.err.println("잔고 부족 예외: " + e.getMessage());
        }
    }

    static void withdraw(int balance, int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("요청 금액: " + amount + ", 현재 잔고: " + balance + " -> 인출 불가");
        }
    }
}
