package com.company.sort;

/**
 * @program: my_algorithm
 * @description 小和问题
 * @author: 34371
 * @create: 2022-02-23 17:42
 **/
public class SmallSum {

    public static void main(String[] args) {
//        System.out.println(((5-3)>>1)+3);
//        System.out.println(smallSum(new int[]{1,2,3}));
        System.out.println(smallSum(new int[]{6,3,2,1,6,7}));
    }

    public static int smallSum(int[] arr){

        return process(arr,0,arr.length-1);

    }

    public static int process(int[] arr,int left,int right){

        if (left == right){
            return 0;
        }

        int mid = ((right-left)>>1)+left;
        int result = 0;
//        System.out.println(" ===== = == = start  =====");
        result+=process(arr,left,mid);
//        System.out.println(" ======= left end =====");
        result+=process(arr,mid+1,right);
//        System.out.println(" ======= right end === ");
        result += merge(arr,left,mid,mid+1,right);

//        System.out.println("===== merge end ======");
        return result;
    }

    private static int merge(int[] arr, int left, int mid, int midAdd1, int right) {
        int[] help = new int[right-left+1];
        int i = 0;
        int leftIndex = left;
        int result = 0;
        while (leftIndex<=mid && midAdd1<=right){
            if (arr[leftIndex]>=arr[midAdd1]){
                help[i++] = arr[midAdd1++];
            }else{
                result += arr[leftIndex]*(right-midAdd1+1);
                help[i++] = arr[leftIndex++];
            }
        }

        while (leftIndex<=mid){
            help[i++] = arr[leftIndex++];
        }

        while (midAdd1<=right){
            help[i++] = arr[midAdd1++];
        }

        for (int j = left,m = 0; j<=right;j++,m++){
            arr[j] = help[m];
        }

        return result;
    }

}
