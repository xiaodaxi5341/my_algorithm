package com.company.bfprt;

import com.company.sort.MergeSortDemo;
import com.company.util.CommonUtils;

import java.util.Arrays;

/**
 * 在一堆无序数组中选择第k小的数
 *
 * 思路：精心选一个P值，以P值进行荷兰国旗分区
 * 分区后看k在left ，right的哪个区间
 * 如果在left左，或者right右，则继续划分对应区域
 * 如果在left与right之间，返回值即可
 *
 * 选P策略：
 *  1，将整个数组分成5个一组的小部分，由小到大排序
 *  2，分别取每个部分的中位数，组成新的数组
 *  3，将中位数数组再排序，取中位数的值即为P
 */
public class BFPRT {

    public static int bfprt(int[] arr,int left,int right,int k){
        if (left == right){
            if (left != k){
                throw new RuntimeException();
            }else{
                return arr[left];
            }
        }

        int pivot = medianOfMedians(arr,left,right);
        //获取P值以后进行常规划分
        partition(arr,left,right,pivot);
        return 0;
    }

    private static void partition(int[] arr, int left, int right, int pivot) {
        if (left == right){
            return;
        }
        int leftRegionIndex = -1;
        int rightRegionIndex = arr.length;
        for (int i=left;i<right;i++){
            //和右区域外的最后一个值交换位置，右区域扩大，左区域不动，i也要不动
            if (arr[left] > pivot){

            }
        }
    }

    //找中位数p
    private static int medianOfMedians(int[] arr, int left, int right) {
        int size = (right - left) / 5+((right-left)%5==0?0:1);
        int[] midIndex = new int[size];
        for (int i=0;i<midIndex.length;i++){
            midIndex[i] = sortAndGetMid(arr,left,right);
        }
        //对中位数数组再做排序
        return bfprt(midIndex,0,midIndex.length,midIndex[midIndex.length/2]);
    }

    private static int sortAndGetMid(int[] arr, int left, int right) {
        insertSort(arr,left,right);
        return arr[(right+left)/2];
    }

    private static void insertSort(int[] arr, int left, int right) {
        for (int i=left;i<right;i++){
            for (int j = i+1;j<right;j++){
                if (arr[i]>arr[j]){
                    CommonUtils.swap(arr,i,j);
                }
            }
        }
    }

}
