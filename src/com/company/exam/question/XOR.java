package com.company.exam.question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @program: my_algorithm
 * @description 异或运算
 * @author: 34371
 * @create: 2022-02-22 19:55
 **/
public class XOR {

    //数组中a出现k次，其余数字出现m次，且m>1,k<m
    public static int getKTimesNumber(int[] arr, int m, int k) {
        int[] bitCounts = new int[32];
        for (int num : arr) {
            int bit = 0;
            while (num != 0 && bit < 32) {
                if (((num >>> bit) & 1) == 1) {
                    bitCounts[bit] += 1;
                }
                bit++;
            }

        }

        int result = 0;
        for (int i = 0; i < bitCounts.length; i++) {
            if (bitCounts[i] != 0 && bitCounts[i] % m != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }

    //一个数出现奇数次，其他数出现偶数次，求出现奇数次的数
    public static int getOddTimesNumber(int[] arr) {
        assert arr != null;
        int result = 0;
        for (int j : arr) {
            result ^= j;
        }
        return result;
    }

    //两个数出现奇数次，其余数出现偶数次，求出这两个数
    public static int[] getTwoOddTimesNumber(int[] arr) {
        assert arr != null;
        //假设a，b出现奇数次，则result = a^b
        int result = getOddTimesNumber(arr);
        int mostRightOne = result & (-result);
        int groupingResult = 0;
        int[] finalResult = new int[2];

        for (int j : arr) {
            if ((j & mostRightOne) == 0) {
                groupingResult ^= j;
            }
        }

        finalResult[0] = groupingResult;
        finalResult[1] = groupingResult ^ result;
        return finalResult;

    }

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        // 真命天子出现的次数
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }


    //对数器貌似有问题
    public static void main(String[] args) {

        int[] ar = new int[]{18, -12, 15, 13, -12, 15, -12, 13, 18, 13, 18, -12, -15, -12, 18, 15, 13, 18, 18, 15, -12, 15, -12, 13, 18, -12, 18, 13, 13, 13, 15, 15, 15};
        System.out.println(getKTimesNumber(ar, 8, 1));
        System.out.println(test(ar, 1, 8));

        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            System.out.println(Arrays.toString(arr));
            int ans1 = test(arr, m, m);
            int ans2 = getKTimesNumber(arr, m, k);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
