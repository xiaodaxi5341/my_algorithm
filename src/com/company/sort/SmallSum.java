package com.company.sort;

/**
 * @program: my_algorithm
 * @description 小和问题
 * @author: 34371
 * @create: 2022-02-23 17:42
 **/
public class SmallSum {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);

    }

    public static int process(int[] arr, int left, int right) {

        if (left == right) {
            return 0;
        }

        int mid = ((right - left) >> 1) + left;
        return process(arr, left, mid)
                + process(arr, mid + 1, right)
                + merge(arr, left, mid, mid + 1, right);
    }

    private static int merge(int[] arr, int left, int mid, int midAdd1, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int leftIndex = left;
        int result = 0;
        while (leftIndex <= mid && midAdd1 <= right) {
            if (arr[leftIndex] >= arr[midAdd1]) {
                help[i++] = arr[midAdd1++];
            } else {
                result += arr[leftIndex] * (right - midAdd1 + 1);
                help[i++] = arr[leftIndex++];
            }
        }

        while (leftIndex <= mid) {
            help[i++] = arr[leftIndex++];
        }

        while (midAdd1 <= right) {
            help[i++] = arr[midAdd1++];
        }

        for (int j = left, m = 0; j <= right; j++, m++) {
            arr[j] = help[m];
        }

        return result;
    }

}
