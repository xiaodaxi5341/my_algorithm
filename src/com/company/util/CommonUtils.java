package com.company.util;

public class CommonUtils {

    public  static void swap(int[] toSortArr, int left, int right) {
        int temp = toSortArr[left];
        toSortArr[left] = toSortArr[right];
        toSortArr[right] = temp;
    }

}
