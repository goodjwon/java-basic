package com.example.ch1.ch2.homework.ex4;

public class Wallet {
    // private 필드
    private double balance;

    // 생성자
    public Wallet() {
        this.balance = 0.0;
    }

    // 돈 추가 메서드
    public void addMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + "원이 지갑에 추가되었습니다.");
        } else {
            System.out.println("유효하지 않은 금액입니다.");
        }
    }

    // 돈 사용 메서드
    public void spendMoney(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + "원이 지갑에서 사용되었습니다.");
        } else {
            System.out.println("잔액이 부족하거나 유효하지 않은 금액입니다.");
        }
    }

    // 잔액 조회 메서드
    public double getBalance() {
        return balance;
    }

    // 메인 메서드 (실행 예시)
    public static void main(String[] args) {
        Wallet myWallet = new Wallet();

        myWallet.addMoney(50000);
        myWallet.spendMoney(15000);
        System.out.println("현재 잔액: " + myWallet.getBalance() + "원");
    }
}
