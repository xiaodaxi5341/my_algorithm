package com.company.dp;

/**
 * @program: my_algorithm
 * @description 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * @author: 34371
 * @create: 2022-04-24 22:19
 **/
public class LeftTop2RightBottom {

    public static int getMinSum(int[][] matrix) {
        return myProcess(matrix, 0, 0);
    }

    public static int myProcess(int[][] matrix, int row, int col) {
        int M = matrix.length;
        int N = matrix[0].length;
        if (row == M - 1 && col == N - 1) {
            return matrix[row][col];
        }

        int p1 = Integer.MAX_VALUE;
        //可以往右走
        if (row + 1 < M) {
            p1 = matrix[row][col] + Math.min(p1, myProcess(matrix, row + 1, col));
        }
        if (col + 1 < N) {
            p1 = matrix[row][col] + Math.min(p1, myProcess(matrix, row, col + 1));
        }

        return p1;
    }

}
