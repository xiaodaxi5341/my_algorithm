package com.company.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @program: my_algorithm
 * @description 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 * @author: 34371
 * @create: 2022-04-24 20:14
 **/
public class CoffeeClean {

    public static int minCleanTime(int[] arr, int N, int a, int b) {

        int[] drinks = getDrinkTimes(arr, N);
        return processMy(drinks, a, b, 0, 0);
    }

    public static int myDp(int[] arr, int N, int wash, int air) {
        int[] drinks = getDrinkTimes(arr, N);
        int limit = 0;
        for (int i = 0; i < N; i++) {
            limit = Math.max(drinks[i], limit) + wash;
        }
        int[][] dp = new int[N + 1][limit + 1];
        for (int index = N; index >= 0; index++) {
            for (int free = 0; free < limit + 1; free++) {

                int selfClean1 = Math.max(drinks[index], free) + wash;
                if (selfClean1 > limit) {
                    break; // 因为后面的也都不用填了
                }
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);

                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);

                dp[index][free] = Math.min(p1,p2);
            }
        }

        return dp[0][0];
    }

    public static int processMy(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        // index号杯子 决定洗
        int selfClean1 = Math.max(drinks[index], free) + wash;
        int restClean1 = processMy(drinks, wash, air, index + 1, selfClean1);
        int p1 = Math.max(selfClean1, restClean1);

        // index号杯子 决定挥发
        int selfClean2 = drinks[index] + air;
        int restClean2 = processMy(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfClean2, restClean2);
        return Math.min(p1, p2);
    }

    private static int[] getDrinkTimes(int[] arr, int n) {

        PriorityQueue<MyMachine> priorityQueue = new PriorityQueue<>(new MyMachineComparator());
        int[] drinks = new int[n];
        for (int i = 0; i < arr.length; i++) {
            MyMachine myMachine = new MyMachine(arr[i], 0);
            priorityQueue.add(myMachine);
        }

        for (int i = 0; i < n; i++) {
            MyMachine poll = priorityQueue.poll();
            poll.canUseTime = poll.canUseTime + poll.useTime;
            drinks[i] = poll.canUseTime;
            priorityQueue.add(poll);
        }
//        System.out.println("getDrinkTimes : " + Arrays.toString(drinks));
        return drinks;
    }

    static class MyMachine {
        //洗的时间
        int useTime;
        //可用时间
        int canUseTime;

        public MyMachine(int useTime, int canUseTime) {
            this.useTime = useTime;
            this.canUseTime = canUseTime;
        }
    }

    static class MyMachineComparator implements Comparator<MyMachine> {

        @Override
        public int compare(MyMachine o1, MyMachine o2) {
            return o1.canUseTime + o1.useTime - o2.canUseTime - o2.useTime;
        }
    }

}
