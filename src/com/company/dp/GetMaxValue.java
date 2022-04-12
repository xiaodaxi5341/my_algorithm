package com.company.dp;

/**
 * @program: my_algorithm
 * @description 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * @author: 34371
 * @create: 2022-04-12 19:57
 **/
public class GetMaxValue {

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(processDp(weights, values, bag));
    }

    public static int maxValue(int[] weights, int[] values, int bag) {
        if (bag <= 0) {
            return 0;
        }

        return process(weights, values, 0, bag);
    }

    private static int process(int[] weights, int[] values, int i, int surplus) {

        if (i > weights.length - 1 || surplus <= 0) {
            return 0;
        }

        int res = 0;
        //不要第i个
        res = Math.max(process(weights, values, i + 1, surplus), res);
        //要第i个,如果能装下第i个
        if (weights[i] <= surplus) {
            res = Math.max(values[i] + process(weights, values, i + 1, surplus - weights[i]), res);
        }

        return res;
    }

    private static int processDp(int[] weights, int[] values, int bag) {

        int n = weights.length + 1;
        int[][] dp = new int[n][bag + 1];

        for (int i = weights.length - 1; i >= 0; i--) {
            for (int j = 1; j <= bag; j++) {
                dp[i][j] = dp[i+1][j];
                if (weights[i] <= j){
                    dp[i][j] = Math.max(values[i]+dp[i+1][j-weights[i]],dp[i][j]);
                }
            }
        }

        return dp[0][bag];

    }

}
