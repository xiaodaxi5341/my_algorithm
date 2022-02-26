package com.company.sort;

import java.util.Arrays;

//随机快排，由荷兰国旗问题衍生
public class RandomQuickSortDemo {

    public static void main(String[] args) {
        int counts = 0;
        while (counts < 1000000) {
            int[] ints = MergeSortDemo.generateRandomArray(10000, 10000);
//        int[] ints = {-3, -12, 65, -58, 35, 7, -46, -12, -3, 29};
            int[] arr = new int[ints.length];

            int[] origin = new int[ints.length];
            System.arraycopy(ints, 0, arr, 0, ints.length);
            System.arraycopy(ints, 0, origin, 0, ints.length);
            sort(ints);
//            System.out.println(Arrays.toString(ints));
            Arrays.sort(arr);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != ints[i]) {
                    System.out.println(Arrays.toString(origin));
                    return;
                }
            }

            counts++;
        }

        System.out.println("end");

    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        partion(arr, 0, arr.length - 1);
    }

    private static void partion(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

//        System.out.println(Arrays.toString(arr));
//        int splitIndex = arr[random(left,right)];
        int splitIndex = arr[right];
        int l = left - 1;
        int m = right + 1;
        int i = left;
        while (l < m && i < m) {
            //当i位置的数小于splitIndex的时候，左区域右扩,i++
            if (arr[i] < splitIndex) {
                swap(arr, i++, l + 1);
                l++;
//                i++;
            } else if (arr[i] == splitIndex) {
                //当i位置的数与splitIndex相等时，i++
                i++;
            } else {
                //i位置>splitIndex时，i与r前一个位置的数交换，i不动，右区域左阔
                swap(arr, i, m - 1);
                m--;
            }
        }

        partion(arr, left, l);
        partion(arr, m, right);
    }

    private static int random(int left, int right) {
        int num;
        do {
            num = (int) (Math.random() * (right + 1));
        } while (num < left);
//        System.out.printf("left : %d, right:%d, random : %d%n",left,right,num);
        return num;
    }

    public static void swap(int[] toSortArr, int left, int right) {
        int temp = toSortArr[left];
        toSortArr[left] = toSortArr[right];
        toSortArr[right] = temp;
    }


}
