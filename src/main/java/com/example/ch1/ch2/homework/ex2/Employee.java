package com.example.ch1.ch2.homework.ex2;

public class Employee {
    // 필드
    private String name;
    private int employeeId;

    // 생성자
    public Employee(String name, int employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    // 메서드
    public void printInfo() {
        System.out.println("직원 ID: " + employeeId + ", 이름: " + name);
    }

    // 메인 메서드 (실행 예시)
    public static void main(String[] args) {
        Employee emp1 = new Employee("김철수", 101);
        Employee emp2 = new Employee("이영희", 102);
        Employee emp3 = new Employee("박민수", 103);

        emp1.printInfo();
        emp2.printInfo();
        emp3.printInfo();
    }
}
