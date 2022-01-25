package com.company.util;

public class CommonUtils {

    public  static void swap(int[] toSortArr, int left, int right) {
        int temp = toSortArr[left];
        toSortArr[left] = toSortArr[right];
        toSortArr[right] = temp;
    }


    public static boolean equals(Object obj1,Object object2){
        if (obj1 == object2){
            return true;
        }

        try {
            return obj1.equals(object2);
        }catch (NullPointerException e){
            return false;
        }
    }
}
