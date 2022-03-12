package com.company.slide.window;

import java.util.*;

/**
 * @program: my_algorithm
 * @description arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：
 * 因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 * @author: 34371
 * @create: 2022-03-10 21:05
 **/
public class MinMoneyCounts {

    public static void main(String[] args) {
//        System.out.println(recursionDp(new int[]{3,1},3));
        int maxLen = 5;
        int maxValue = 6;
//        int testTime = 3000;
        System.out.println("功能测试开始");
//        for (int i = 0; i < testTime; i++) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = randomArray(N, maxValue);
        int aim = (int) (Math.random() * maxValue);
        printArray(arr);
        Integer ans1 = recursionCache(arr, aim);
        Integer ans2 = slideWindow(arr, aim);
//        Integer ans2 = slideWindow(new int[0], 0);
//            if (ans1 == ans2) {
//                continue;
//            }
//            if (ans1.intValue() != ans2.intValue()) {
//                System.out.println("Oops!");
        System.out.println(aim);
        System.out.println(ans1);
        System.out.println(ans2);
//                break;
//            }
//        }
        System.out.println("功能测试结束");

    }

    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static Integer minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static Integer process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return null;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : null;
        } else {
            Integer count = null;
            Integer p1 = process(arr, index + 1, rest);
            if (p1 != null) {
                count = p1;
            }
            Integer p2 = process(arr, index + 1, rest - arr[index]);
            if (p2 != null) {
                p2++;
                count = count == null ? p2 : Math.min(count, p2);
            }
            return count;
        }
    }

    public static Integer invoke(int[] arr, int aim) {
        return recursion(arr, 0, aim);
    }

    public static Integer recursion(int[] arr, int index, int aim) {
        if (index == arr.length) {
            return aim == 0 ? 0 : null;
        }

        Integer count = null;
        Integer noUse = recursion(arr, index + 1, aim);
        if (noUse != null) {
            count = noUse;
        }
        Integer use = recursion(arr, index + 1, aim - arr[index]);
        if (use != null) {
            if (count != null) {
                count = Math.min(count, use + 1);
            } else {
                count = use + 1;
            }

        }

        return count;
    }

    public static Integer recursionCache(int[] arr, int aim) {
        Integer[][] dp = new Integer[arr.length + 1][aim + 1];

        dp[arr.length][0] = 0;
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 0; j < dp[i].length; j++) {
                Integer count = dp[i + 1][j];
                Integer p2 = null;
                if (j - arr[i] >= 0 && dp[i + 1][j - arr[i]] != null) {
                    p2 = dp[i + 1][j - arr[i]];
                }
                if (count == null) {
                    count = p2 == null ? null : p2 + 1;
                } else {
                    count = p2 == null ? count : Math.min(count, p2 + 1);
                }
                dp[i][j] = count;
            }
        }
        return dp[0][aim];
    }

    public static int slideWindow(int[] arr, int aim) {
        if (aim == 0){
            return 0;
        }
        Info info = getInfo(arr);
        int[] counts = info.counts;
        int[] denominations = info.denominations;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < counts.length; i++) {
            min = Math.min(min, processSlideWindow(denominations, counts, i, aim));
        }
        return min;
    }

    private static int processSlideWindow(int[] denominations, int[] counts, int i, int aim) {

        if (aim == 0){
            return 0;
        }
        int min = 0;
        for (int j = 0; j <= counts[i]; j++) {
            if (i+1<counts.length){
                int num = processSlideWindow(denominations, counts, i + 1, aim - denominations[i] * j);
                min = Math.min(min, num == Integer.MAX_VALUE?Integer.MAX_VALUE:num+j);
            }
        }
        return min;
    }

    private static Info getInfo(int[] arr) {
        final Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(arr).forEach(num -> {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        });
        int[] denominations = new int[map.keySet().size()];
        int[] counts = new int[map.keySet().size()];
        int num = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            denominations[num] = entry.getKey();
            counts[num] = entry.getValue();
            num++;
        }

        return new Info(denominations, counts);
    }

    static class Info {
        int[] denominations;
        int[] counts;

        public Info(int[] denominations, int[] counts) {
            this.denominations = denominations;
            this.counts = counts;
        }
    }

}
