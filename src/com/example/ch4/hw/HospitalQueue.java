package com.example.ch4.hw;

import java.util.LinkedList;

public class HospitalQueue {
    private LinkedList<Patient> queue;

    public HospitalQueue() {
        queue = new LinkedList<>();
    }

    // 일반 환자 추가 (뒤에 추가)
    public void addPatient(Patient patient) {
        queue.addLast(patient);
    }

    // 긴급 환자 추가 (중간에 삽입)
    public void addUrgentPatient(Patient patient) {
        int position = findUrgentPosition();
        queue.add(position, patient);
    }

    // 환자 제거 (앞에서 제거)
    public Patient removePatient() {
        return queue.pollFirst();
    }

    // 대기열 표시
    public void displayQueue() {
        for (Patient patient : queue) {
            System.out.println(patient);
        }
    }

    // 긴급 환자 삽입 위치 결정 (예시로 두 번째 위치에 삽입)
    private int findUrgentPosition() {
        return 1;
    }

    // 테스트
    public static void main(String[] args) {
        HospitalQueue hQueue = new HospitalQueue();
        hQueue.addPatient(new Patient("홍길동"));
        hQueue.addPatient(new Patient("이순신"));
        hQueue.addUrgentPatient(new Patient("긴급환자"));

        hQueue.displayQueue();
        System.out.println("진료 중: " + hQueue.removePatient());
        hQueue.displayQueue();
    }
}

class Patient {
    private String name;

    public Patient(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "환자: " + name;
    }
}
