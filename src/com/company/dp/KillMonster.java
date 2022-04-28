package com.company.dp;

/**
 * @program: my_algorithm
 * @description 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 * @author: 34371
 * @create: 2022-04-28 22:16
 **/
public class KillMonster {

    public static double kill(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        return myProcess(N, M, K) / Math.pow(M + 1, K);
    }

    //有错 === 但是不想做了。。。
    public static double myDp(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        int[][] dp = new int[N + 1][K + 1];
        dp[0][0] = 1;
        for (int col = 1; col <= K; col++) {
            for (int row = 0; row <= N; row++) {
                int res = 0;
                for (int i = 0; i <= M; i++) {
                    res+=getValue(row-i,col-1,dp,M,K);
                }
                dp[row][col] = res;
            }
        }

        return dp[N][K]/Math.pow(M+1,K);
    }

    private static int getValue(int row, int col, int[][] dp,int m,int k) {
        if (row<0){
            if (col == 0){
                return 1;
            }
            else if (col>=0&&col<=k){
                return (int)Math.pow(m+1,k);
            }else {
                return 0;
            }
        }else{
            return dp[row][col];
        }
    }

    private static double myProcess(int N, int M, int K) {
        if (K == 0) {
            return N <= 0 ? 1 : 0;
        }
        if (N <= 0) {
            return Math.pow(M + 1, K);
        }
        int res = 0;
        for (int hit = 0; hit <= M; hit++) {
            res += myProcess(N - hit, M, K - 1);
        }
        return res;
    }


}
