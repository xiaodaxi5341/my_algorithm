package com.company.sort;

import java.util.Arrays;

//归并排序：将无序的数组分别通过左右两边各自排序后再合并的方式进行排序
public class MergeSortDemo {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] originArr = copyArray(arr1);
            RandomQuickSortDemo.quickSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                printArray(originArr);
                break;
            }
        }
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

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
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


    public static void mergeSort(int[] toSortArr) {
        if (toSortArr == null) {
            return;
        }
        mergeSort(toSortArr, 0, toSortArr.length - 1);
    }

    public static void mergeSort(int[] toSortArr, int left, int right) {
        if (left >= right) {
            return;
        }
//        if (toSortArr[left] < toSortArr[right]) {
//            swap(toSortArr, left, right);
//        }
        //排序左半部分
        int newRight = ((right - left) >> 1) + left;
        mergeSort(toSortArr, left, newRight);
        //排序右半部分
        int newLeft = newRight + 1;
        mergeSort(toSortArr, newLeft, right);

        finalMerge(toSortArr, newLeft, newRight, left, right);

    }

    public static void finalMerge(int[] toSortArr, int newLeft, int newRight, int left, int right) {
        int[] help = new int[right - left + 1];
        //新的数组其实是: toSortArr[left,newRight] 和 toSortArr[right,newLeft]
        int i = 0;
        int p1 = left;
        int p2 = newLeft;
        while (p1 <= newRight && p2 <= right) {
            help[i++] = toSortArr[p1] < toSortArr[p2] ? toSortArr[p1++] : toSortArr[p2++];
        }
        while (p1 <= newRight) {
            help[i++] = toSortArr[p1++];
        }
        while (p2 <= right) {
            help[i++] = toSortArr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            toSortArr[left + i] = help[i];
        }
    }




}
