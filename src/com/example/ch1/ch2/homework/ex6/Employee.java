package com.example.ch1.ch2.homework.ex6;

// Employee.java
public class Employee {
    protected int hour;
    protected int pay;

    // 생성자
    public Employee(int hour, int pay) {
        this.hour = hour;
        this.pay = pay;
    }

    // 급여 계산 메서드
    public void calculatePay() {
        System.out.println("하루 급여는: " + (hour * pay) + "원입니다.");
    }
}

// FullTimeEmployee.java
class FullTimeEmployee extends Employee {

    // 풀타임 직원은 고정된 근무 시간(예: 8시간)을 가집니다.
    public FullTimeEmployee(int pay) {
        super(8, pay); // 풀타임 근무 시간은 8시간으로 설정
    }

    // 급여 계산 메서드 오버라이딩
    @Override
    public void calculatePay() {
        System.out.println("풀타임 직원의 하루 급여는: " + (hour * pay) + "원입니다.");
    }
}

// PartTimeEmployee.java
class PartTimeEmployee extends Employee {

    // 파트타임 직원은 유동적인 근무 시간을 가집니다.
    public PartTimeEmployee(int hour, int pay) {
        super(hour, pay);
    }

    // 급여 계산 메서드 오버라이딩
    @Override
    public void calculatePay() {
        System.out.println("파트타임 직원의 하루 급여는: " + (hour * pay) + "원입니다.");
    }
}

// Main.java (실행 예시)
class Main2 {
    public static void main(String[] args) {

        Employee fullTimeEmp = new FullTimeEmployee(15000);
        // 파트타임 직원 생성 (하루 4시간 근무, 시급 10,000원)
        Employee partTimeEmp = new PartTimeEmployee(4, 10000);

        // 급여 계산
        fullTimeEmp.calculatePay();
        partTimeEmp.calculatePay();
    }
}
