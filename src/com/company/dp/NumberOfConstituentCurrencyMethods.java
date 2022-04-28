package com.company.dp;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: my_algorithm
 * @description arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 * @author: 34371
 * @create: 2022-04-25 21:35
 **/
public class NumberOfConstituentCurrencyMethods {

    public static void main(String[] args) {
        int[] arr = new int[]{16, 12, 9, 21, 19, 1, 3, 29};
        System.out.println(myDp(arr, 12));
        System.out.println(myDpSpaceCompression(arr, 12));
    }

    public static int getCounts(int[] arr, int aim) {

        IntSummaryStatistics intSummaryStatistics = Arrays.stream(arr).summaryStatistics();
        if (aim > intSummaryStatistics.getSum()) {
            return 0;
        }

        return myProcess(arr, 0, aim);
    }

    public static int myDpSpaceCompression(int[] arr, int aim){
        int[] dp = new int[aim+1];
        dp[0] = 1;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int surplus = aim; surplus >=0; surplus--) {
                int res = 0;
                if (surplus - arr[index] >= 0) {
                    res += dp[surplus - arr[index]];
                }
                res += dp[surplus];
                dp[surplus] = res;
            }
        }

        return dp[aim];
    }

    public static int myDp(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = 1;
        }

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int surplus = 1; surplus <= aim; surplus++) {
                int res = 0;
                if (surplus - arr[index] >= 0) {
                    res += dp[index + 1][surplus - arr[index]];
                }
                res += dp[index + 1][surplus];
                dp[index][surplus] = res;
            }
        }

        return dp[0][aim];
    }

    private static int myProcess(int[] arr, int index, int aim) {
        if (index == arr.length && aim != 0) {
            return 0;
        }
        if (aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }

        //要当前 + 不要当前
        return myProcess(arr, index + 1, aim - arr[index]) + myProcess(arr, index + 1, aim);

    }

}
