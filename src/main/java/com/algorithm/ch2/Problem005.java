package com.algorithm.ch2;

import java.util.Scanner;

public class Problem005 {
    /*
     * 문제 요약:
     * - 길이가 N인 수열 A에서 연속된 부분 구간의 합이 M으로 나누어떨어지는 경우의 수를 구한다.
     * - prefixSum 배열을 만들어 각 구간의 합을 mod M으로 취한 값을 이용하여,
     *   같은 mod 값을 갖는 prefixSum 쌍을 세면, 그 차이가 M으로 나누어떨어짐을 알 수 있다.
     *
     * 예)
     * 입력:
     * 5 3
     * 1 2 3 1 2
     * 출력:
     * 4
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N];

        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }
        sc.close();

        long[] prefixSum = new long[N+1];
        // prefixSum[i] = A[0] + A[1] + ... + A[i-1]
        for(int i = 1; i <= N; i++){
            prefixSum[i] = prefixSum[i-1] + A[i-1];
        }

        // 각 prefixSum[i]를 M으로 나눈 나머지 개수 세기
        long[] count = new long[M];
        for(int i = 0; i <= N; i++){
            int modVal = (int)(prefixSum[i] % M);
            if(modVal < 0) modVal += M; // 음수 방지(언어에 따라 필요할 수 있음)
            count[modVal]++;
        }

        long answer = 0;
        // 같은 나머지를 가진 두 prefixSum을 고르는 조합( nC2 = n*(n-1)/2 )
        for(int i = 0; i < M; i++){
            if(count[i] > 1){
                answer += (count[i] * (count[i] - 1)) / 2;
            }
        }

        System.out.println(answer);
    }
}
