package brush.questions.chapter18;

import java.util.Scanner;

/**
 * @program: my_algorithm
 * @description 给定一个矩阵matrix，
 * 先从左上角开始，
 * 每一步只能往右或者往下走，走到右下角。
 * 然后从右下角出发，
 * 每一步只能往上或者往左走，再回到左上角。任何一个位置的数字，只能获得一遍。返回最大路径和。
 * @author: 34371
 * @create: 2022-06-15 13:39
 **/
public class MaxDistanceSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int ans = getMaxDistanceSum(matrix);
        System.out.println(ans);
        sc.close();
    }


    //该问题可以转换为两个人都从左上角出发，但是经到同一个地方时只获得一次分数，到达右下角时获取到的最大分数
    public static int getMaxDistanceSum(int[][] matrix) {

        return getPoint(matrix, 0, 0, 0);

    }

    private static int getPoint(int[][] matrix, int rowA, int colA, int rowB) {
        int colB = rowA + colA - rowB;
        if (rowA == matrix.length || colA == matrix[0].length || rowB == matrix.length || colB == matrix[0].length) {
            return 0;
        }

        //A往右走,B也往右走
        int point1 = getPoint(matrix, rowA, colA + 1, rowB);
        //A往右走,B往下走
        int point2 = getPoint(matrix, rowA, colA + 1, rowB + 1);

        //A往下走,B也往下走
        int point3 = getPoint(matrix, rowA + 1, colA, rowB);
        //A往下走,B往下走
        int point4 = getPoint(matrix, rowA + 1, colA, rowB + 1);

        int point = Math.max(point1, Math.max(point2,Math.max(point3,point4)));
        point += matrix[rowA][colA];
        if (rowA != rowB) {
            point += matrix[rowB][colB];
        }

        return point;
    }

}
