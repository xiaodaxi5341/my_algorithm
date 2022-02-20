package com.company.bfprt;

import com.company.util.CommonUtils;

import java.util.Arrays;

import static com.company.util.CommonUtils.swap;

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

    public static void main(String[] args) {
        int[] a = new int[]{3,9,5,3,5,2,8,7};
//        23355789
        insertSort(a,1, a.length-2);
        System.out.println(Arrays.toString(a));
//        System.out.println(bfprt(a,0,a.length,6-1));
    }


    public static int bfprt(int[] arr,int left,int right,int k){
        if (left == right-1){
//            if (left != k){
//                throw new RuntimeException();
//            }else{
                return arr[left];
//            }
        }

        int pivot = medianOfMedians(arr,left,right);
        //获取P值以后进行常规划分
        int[] partition = partition(arr, left, right, pivot);
        int result;
        if (partition[0]>k){
            result = bfprt(arr,left,partition[0]-1,k);
        }else if (k<=partition[1]){
            result = arr[k];
        }else{
            result = bfprt(arr,partition[1]+1,right,k);
        }
        return result;
    }

    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int[] leftRightArr = new int[2];
        if (left == right-1){
            leftRightArr[0]=left;
            leftRightArr[1]=right;
            return leftRightArr;
        }
        int leftRegionIndex = left-1;
        int rightRegionIndex = right;
        int i=left;
        while (i<rightRegionIndex){
            //和右区域外的最后一个值交换位置，右区域扩大，左区域不动，i也要不动
            if (arr[i] > pivot){
                CommonUtils.swap(arr,--rightRegionIndex,i);
            }else if (arr[i]==pivot){
                i++;
            }else {
                //值小于给定的数时，当前位置与左区域外的值进行交换，左区域外扩，同时i++
                CommonUtils.swap(arr,++leftRegionIndex,i++);
            }
        }
        leftRightArr[0] = leftRegionIndex+1;
        leftRightArr[1] = rightRegionIndex;
        return leftRightArr;
    }

//    public static int[] partition(int[] arr, int L, int R, int pivot) {
//        int less = L - 1;
//        int more = R + 1;
//        int cur = L;
//        while (cur < more) {
//            if (arr[cur] < pivot) {
//                swap(arr, ++less, cur++);
//            } else if (arr[cur] > pivot) {
//                swap(arr, cur, --more);
//            } else {
//                cur++;
//            }
//        }
//        return new int[] { less + 1, more - 1 };
//    }

    //找中位数p
    private static int medianOfMedians(int[] arr, int left, int right) {
        int size = (right - left) / 5+((right-left)%5==0?0:1);
        int[] midIndex = new int[size];
        for (int i=0;i<midIndex.length;i++){
            midIndex[i] = sortAndGetMid(arr,i*5,Math.min(5*(i+1),right));
        }
        //对中位数数组再做排序
        return bfprt(midIndex,0,midIndex.length,midIndex.length/2);
    }

    private static int sortAndGetMid(int[] arr, int left, int right) {
        insertSort(arr,left,right);
        return arr[(right+left)/2];
    }

    private static void insertSort(int[] arr, int left, int right) {
        for (int i=left+1;i<right;i++){
            int newNumIndex = i;
           while (newNumIndex-1>=left&&arr[newNumIndex-1]>arr[newNumIndex]){
               swap(arr,newNumIndex-1,newNumIndex);
               newNumIndex--;
           }
        }
    }

}
