package brush.questions.chapter04;

import jdk.nashorn.internal.parser.JSONParser;

import java.util.Arrays;

/**
 * @program: my_algorithm
 * @description 给定一个正整数、负整数和 0 组成的 N × M矩阵，编写代码找出元素总和最大的子矩阵。
 * <p>
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 * @author: 34371
 * @create: 2022-05-15 20:54
 **/
public class MaxSubMatrix {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{};
        System.out.println(Arrays.toString(getMaxMatrix(matrix)));
    }

    public static int[] getMaxMatrix(int[][] matrix) {
        int maxSum = matrix[0][0];
        int[] result = new int[]{0, 0, 0, 0, maxSum};
        for (int row = 0; row < matrix.length; row++) {

            int[] newArr = new int[matrix[0].length];
            for (int r = 0; r <= row; r++) {
                //有重复计算
                for (int i = r; i <= row; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        newArr[j] += matrix[i][j];
                    }
                }

                int[] midResult = maxSubArray(newArr);
                if (midResult != null && midResult[2] > maxSum) {
                    result[0] = r;
                    result[1] = midResult[0];
                    result[2] = row;
                    result[3] = midResult[1];
                    maxSum = midResult[2];
                    result[4] = maxSum;
                }
                newArr = new int[matrix[0].length];
            }
        }
        return result;
    }


    public static int[] maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new int[]{0, 0, nums[0]};
        }

        int result = nums[0];
        int pre = nums[0];
        int isr = 0;
        int ier = 0;
        int is = 0;
        for (int i = 1; i < nums.length; i++) {
            if (pre >= 0) {
                pre += nums[i];
                if (pre >= result) {
                    isr = is;
                    ier = i;
                    result = pre;
                }
            } else {
                is = i;
                pre = nums[i];
                if (pre > result) {
                    isr = i;
                    ier = i;
                    result = pre;
                }
            }
        }


        return new int[]{isr, ier, result};

    }

}
