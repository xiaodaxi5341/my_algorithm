package brush.questions.chapter01;

/**
 * @program: my_algorithm
 * @description 给定一个二维数组matrix，你可以从任何位置出发，走向上、下、左、右四个方向，返回能走出来的最长地递增链长度
 * @author: 34371
 * @create: 2022-05-06 21:06
 **/
public class LongestIncreasingChain {

    /**
     *          7       8      9  1
     *           6      1      2  1
     *           5      4      3  1
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = new int[3][4];
        matrix[0][0] = 7;
        matrix[0][1] = 8;
        matrix[0][2] = 9;
        matrix[0][3] = 1;

        matrix[1][0] = 6;
        matrix[1][1] = 1;
        matrix[1][2] = 2;
        matrix[1][3] = 1;

        matrix[2][0] = 5;
        matrix[2][1] = 4;
        matrix[2][2] = 3;
        matrix[2][3] = 1;

        System.out.println(longestIncreasingChain(matrix));
    }

    public static int longestIncreasingChain(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int result = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                result = Math.max(process(matrix, row, col, 1), result);
            }
        }

        return result;

    }

    //我自己写的劣质递归并且题意理解错误。。。
    private static int process(int[][] matrix, int row, int col, int curLength) {
        if (row < 0 || row == matrix.length || col < 0 || col == matrix.length) {
            return curLength;
        }

        //往上走
        int result = curLength;
        int curNum = matrix[row][col] + 1;
        if (row - 1 >= 0 && curNum == matrix[row - 1][col]) {
            result = Math.max(process(matrix, row - 1, col, curLength + 1), result);
        }
        //往下走
        if (row + 1 < matrix.length && curNum == matrix[row + 1][col]) {
            result = Math.max(process(matrix, row + 1, col, curLength + 1), result);
        }
        //往左走
        if (col - 1 >= 0 && curNum == matrix[row][col - 1]) {
            result = Math.max(process(matrix, row, col - 1, curLength + 1), result);
        }
        //往右走
        if (col + 1 < matrix[0].length && curNum == matrix[row][col + 1]) {
            result = Math.max(process(matrix, row, col + 1, curLength + 1), result);
        }

        return result;
    }

}
