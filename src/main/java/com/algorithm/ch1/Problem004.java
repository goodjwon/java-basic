package com.algorithm.ch1;

import java.util.Scanner;

/*
 * 문제 요약:
 * - 길이 N인 배열 A가 있고, Q개의 연산을 수행한다.
 *   1) b=1, (c, d): c번째 원소를 d로 변경
 *   2) b=2, (c, d): 구간 [c, d]의 합을 구하여 출력
 * - 세그먼트 트리 또는 펜윅 트리(바이너리 인덱스 트리)를 사용해 효율적으로 처리 가능.
 *
 * 여기서는 Fenwick Tree(=Binary Indexed Tree)로 예시를 보여준다.
 */

public class Problem004 {
    static int N, Q;
    static long[] fenwick;  // 펜윅 트리(1-based 인덱스 사용)

    // Fenwick Tree 에서 특정 index에 value를 더하는 함수
    static void update(int index, long value) {
        while (index <= N) {
            fenwick[index] += value;
            index += (index & -index);
        }
    }

    // Fenwick Tree 에서 1부터 index까지의 합을 구하는 함수
    static long prefixSum(int index) {
        long result = 0;
        while (index > 0) {
            result += fenwick[index];
            index -= (index & -index);
        }
        return result;
    }

    // 구간 [left, right]의 합
    static long rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        Q = sc.nextInt();

        fenwick = new long[N+1];
        long[] A = new long[N+1];

        // 배열 입력
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextLong();
        }

        // 초기에 Fenwick Tree 구성
        for (int i = 1; i <= N; i++) {
            update(i, A[i]);
        }

        // Q번의 연산 처리
        for (int i = 0; i < Q; i++) {
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            if (b == 1) {
                // c번째 원소를 d로 변경
                long diff = d - A[c];
                A[c] = d;
                update(c, diff);
            } else if (b == 2) {
                // 구간 [c, d] 합 출력
                System.out.println(rangeSum(c, d));
            }
        }

        sc.close();
    }
}
