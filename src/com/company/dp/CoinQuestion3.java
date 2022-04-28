package com.company.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * @author: 34371
 * @create: 2022-04-28 20:35
 **/
public class CoinQuestion3 {

    public static int myGetCounts(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> faceValue2Count = new HashMap<>(arr.length);
        Arrays.stream(arr).forEach(num -> {
            if (faceValue2Count.containsKey(num)) {
                faceValue2Count.put(num, faceValue2Count.get(num) + 1);
            } else {
                faceValue2Count.put(num, 1);
            }
        });

        int[] coins = new int[faceValue2Count.keySet().size()];
        int[] counts = new int[faceValue2Count.keySet().size()];
        int i = 0;
        for (Integer key : faceValue2Count.keySet()) {
            coins[i] = key;
            counts[i] = faceValue2Count.get(key);
            i++;
        }

        return myProcess(coins, counts, 0, aim);
    }

    private static int myDp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> faceValue2Count = new HashMap<>(arr.length);
        Arrays.stream(arr).forEach(num -> {
            if (faceValue2Count.containsKey(num)) {
                faceValue2Count.put(num, faceValue2Count.get(num) + 1);
            } else {
                faceValue2Count.put(num, 1);
            }
        });

        int[] coins = new int[faceValue2Count.keySet().size()];
        int[] counts = new int[faceValue2Count.keySet().size()];
        int in = 0;
        for (Integer key : faceValue2Count.keySet()) {
            coins[in] = key;
            counts[in] = faceValue2Count.get(key);
            in++;
        }

        int[][] dp = new int[coins.length + 1][aim + 1];
        for (int index = 0; index < dp.length; index++) {
            dp[index][0] = 1;
        }

        for (int index = coins.length - 1; index >= 0; index--) {
            for (int surplus = 1; surplus <= aim; surplus++) {
                int sum;
                int res = 0;
                for (int num = 0; num <= counts[index]; num++) {
                    sum = coins[index] * num;
                    if (surplus - sum >= 0) {
                        res += dp[index + 1][surplus - sum];
                    }
                }
                dp[index][surplus] = res;
            }
        }

        return dp[0][aim];
    }

    private static int myProcess(int[] coins, int[] counts, int index, int surplus) {

        if (surplus == 0) {
            return 1;
        }

        if (index == coins.length) {
            return 0;
        }

        int res = 0;
        int sum;
        for (int i = 0; i < counts[index] + 1; i++) {
            sum = coins[index] * i;
            if (surplus - sum >= 0) {
                res += myProcess(coins, counts, index + 1, surplus - sum);
            }
        }

        return res;
    }


}
