package com.company.dp;

/**
 * @program: my_algorithm
 * @description arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * @author: 34371
 * @create: 2022-04-27 21:39
 **/
public class CountsOfUnlimitedMoney {

    public static void main(String[] args) {
        System.out.println(myDpMoreOptimize(new int[]{6, 3, 18, 4, 24, 1}, 6));
    }

    public static int myGetCounts(int[] arr, int aim) {
        return myProcess(arr, 0, aim);
    }

    //空间压缩的动态规划
    public static int myDpMoreOptimizeWithCompression(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        dp[0] = 1;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int surplus = 1; surplus <= aim; surplus++) {
                dp[surplus] = dp[surplus];
                if (surplus - arr[index] >= 0) {
                    dp[surplus] += dp[surplus - arr[index]];
                }
            }
        }

        return dp[aim];
    }

    public static int myDpMoreOptimize(int[] arr, int aim) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = 1;
        }

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int surplus = 1; surplus <= aim; surplus++) {
                dp[index][surplus] = dp[index + 1][surplus];
                if (surplus - arr[index] >= 0) {
                    dp[index][surplus] += dp[index][surplus - arr[index]];
                }
            }
        }

        return dp[0][aim];

    }

    public static int myDp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = 1;
        }

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int surplus = 1; surplus <= aim; surplus++) {
                int sum = 0;
                int res = 0;
                for (int i = 0; sum <= surplus; i++, sum = arr[index] * i) {
                    res += dp[index + 1][surplus - sum];
                }
                dp[index][surplus] = res;
            }
        }

        return dp[0][aim];

    }

    private static int myProcess(int[] arr, int index, int surplus) {
        if (surplus < 0) {
            return 0;
        }
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (surplus == 0) {
            return 1;
        }
        if (index == arr.length) {
            return 0;
        }

        int res = 0;
        for (int i = 0, sum = 0; sum <= surplus; i++, sum = arr[index] * i) {
            res += myProcess(arr, index + 1, surplus - sum);
        }
        return res;
    }
}
