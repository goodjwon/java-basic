package com.algorithm.ch1;

import java.util.Scanner;

public class Problem003 {
    /*
     * 문제 요약:
     * - 길이 N인 배열 A가 주어지고, M번의 구간 [i, j] 합을 구하는 질의가 주어진다.
     * - 매 질의마다 A[i]부터 A[j]까지의 합을 출력한다.
     * - N, M이 큰 경우, 누적 합(prefix sum)을 이용하면 효율적이다.
     *
     * 예)
     * 입력:
     * 5 3
     * 1 2 3 4 5
     * 1 2
     * 1 3
     * 2 5
     *
     * 출력:
     * 3
     * 6
     * 14
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 배열 크기
        int M = sc.nextInt(); // 질의 개수

        int[] A = new int[N+1];  // 편의상 인덱스를 1부터 사용

        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }

        // 누적 합(prefix sum) 계산
        // prefixSum[i] = A[1] + A[2] + ... + A[i]
        long[] prefixSum = new long[N+1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i-1] + A[i];
        }

        // M번 질의
        for (int q = 0; q < M; q++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            // 구간 합 = prefixSum[j] - prefixSum[i-1]
            long result = prefixSum[j] - prefixSum[i-1];
            System.out.println(result);
        }

        sc.close();
    }
}
