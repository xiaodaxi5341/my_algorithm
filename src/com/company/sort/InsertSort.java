package com.company.sort;

import com.company.util.CommonUtils;

import java.util.Arrays;

/**
 * @program: my_algorithm
 * @description 插入排序：保证0-i范围有序，第i+1个数添加进去后，进行调整从而保证0-(i+1)上有序
 * 时间复杂度：o(n²)
 * @author: 34371
 * @create: 2022-02-22 12:58
 **/
public class InsertSort {

    public static void sort(int[] arr){

        if (arr == null || arr.length<2){
            return;
        }

        for (int i=0;i<arr.length;i++){
            //0-(i-1)位置已经有序，再插入第i个数
            for(int j = i-1;j>=0&&arr[j+1]<arr[j];j--){
                CommonUtils.swap(arr,j,j+1);
            }
        }
    }

    public static void main(String[] args){
        int length = (int)(Math.random()*10);
        int[] arr = new int[length];
        for (int i = 0 ;i<length;i++){
            arr[i] = (int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
