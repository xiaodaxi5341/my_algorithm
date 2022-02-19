package com.company.sort;

import com.company.util.CommonUtils;

public class SelectSort {

    public static void selectSort(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }

        int length = arr.length;
        for (int i=0;i<length;i++){
            int min = i;
            for (int j = i+1;j<length;j++){
                min = arr[min]>arr[j]?j:min;
            }
            if (min != i){
                CommonUtils.swap(arr,i,min);
            }
        }
    }

}
