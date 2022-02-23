package com.company.sort;

/**
 * @program: my_algorithm
 * @description 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 *
 * @author: 34371
 * @create: 2022-02-23 21:35
 **/
public class BigThanRightTwice {

    //97
    //122
    //65
    //90
    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{1,3,2,3,1}));
    }

    public static int reversePairs(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        if (left == right){
            return 0;
        }

        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid)
                + process(arr, mid + 1, right)
                + merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int L, int m, int r) {
        // [L....M] [M+1....R]
        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = m + 1;
        for (int i = L; i <= m; i++) {
            while (windowR <= r && (long) arr[i] > (long) arr[windowR] * 2) {
                windowR++;
            }
            ans += windowR - m - 1;
        }
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;

    }

}
