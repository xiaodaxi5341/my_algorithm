package com.company.dp;

/**
 * @program: my_algorithm
 * @description 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * @author: 34371
 * @create: 2022-04-20 23:02
 **/
public class HorseMoveToTarget {
    public static void main(String[] args) {
        System.out.println(getNums(7, 7, 10));
    }

    public static int getNums(int x, int y, int k) {
        return process(0, 0, x, y, k);
    }

    public static int dp(int x, int y, int k) {
        int[][][] dp = new int[9][10][k + 1];
        dp[x][y][0] = 1;
        for (int s = 1; s < k + 1; s++) {
            int res = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    res += getDp(i + 1, j + 2, s - 1, dp);
                    res += getDp(i + 1, j - 2, s - 1, dp);
                    res += getDp(i + 2, j + 1, s - 1, dp);

                    res += getDp(i + 2, j - 1, s - 1, dp);
                    res += getDp(i - 2, j - 1, s - 1, dp);
                    res += getDp(i - 1, j + 2, s - 1, dp);
                    res += getDp(i - 1, j - 2, s - 1, dp);
                    res += getDp(i - 2, j + 1, s - 1, dp);
                    dp[i][j][s] = res;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int getDp(int x, int y, int k, int[][][] dp) {
        try {
            return dp[x][y][k];
        } catch (Exception e) {
            return 0;
        }
    }

    private static int process(int i, int j, int tx, int ty, int s) {

        if (i < 0 || i > 8 || j < 0 || j > 9) {
            return 0;
        }
        if (s == 0) {
            return i == tx && j == ty ? 1 : 0;
        }

        int res = 0;
        res += process(i + 1, j + 2, tx, ty, s - 1);
        res += process(i + 1, j - 2, tx, ty, s - 1);
        res += process(i + 2, j + 1, tx, ty, s - 1);
        res += process(i + 2, j - 1, tx, ty, s - 1);

        res += process(i - 2, j - 1, tx, ty, s - 1);
        res += process(i - 1, j + 2, tx, ty, s - 1);
        res += process(i - 1, j - 2, tx, ty, s - 1);
        res += process(i - 2, j + 1, tx, ty, s - 1);
        return res;

    }


}
