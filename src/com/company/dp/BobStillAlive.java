package com.company.dp;

/**
 * @program: my_algorithm
 * @description 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 * @author: 34371
 * @create: 2022-04-28 21:48
 **/
public class BobStillAlive {

    public static double bobAlive(int N, int M, int row, int col, int k) {
        return process(N, M, row, col, k) / Math.pow(4, k);
    }

    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static int getByIndex(int[][][] dp,int row,int col,int k){
        try{
            return dp[row][col][k];
        }catch (Exception e){
            return 0;
        }
    }

    private static double process(int n, int m, int row, int col, int k) {

        if (row < 0 || row >= n || col < 0 || col >= m) {
            return 0;
        }

        if (k == 0) {
            return 1;
        }

        //往上走
        return process(n, m, row - 1, col, k - 1)
                //往左走
                + process(n, m, row, col - 1, k - 1)
                //往右走
                + process(n, m, row, col + 1, k - 1)
                //往下走
                + process(n, m, row + 1, col, k - 1);

    }

    public static void main(String[] args) {
        System.out.println(bobAlive(50, 50, 6, 6, 10));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }

}
