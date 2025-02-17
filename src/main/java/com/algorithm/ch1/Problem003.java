package com.algorithm.ch1;

import java.util.Scanner;

public class Problem003 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 배열 크기
        int M = sc.nextInt(); // 질의 개수

        // 입력 값 검증
        if (N <= 0 || M <= 0) {
            System.out.println("N과 M은 0보다 커야 합니다.");
            return;
        }

        long[] A = new long[N + 1];  // 편의상 인덱스를 1부터 사용

        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }

        // 누적 합(prefix sum) 계산
        // prefixSum[i] = A[1] + A[2] + ... + A[i]
        long[] prefixSum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        // M번 질의
        for (int queryIndex = 0; queryIndex < M; queryIndex++) {
            int i = sc.nextInt();
            int j = sc.nextInt();

            // 입력 값 검증
            if (i < 1 || j > N || i > j) {
                System.out.println("잘못된 구간입니다.");
                continue;
            }

            // 구간 합 = prefixSum[j] - prefixSum[i-1]
            long result = prefixSum[j] - prefixSum[i - 1];
            System.out.println(result);
        }

        sc.close();
    }
}