package com.company.exam.question;

/**
 * @program: my_algorithm
 * @description 局部最小值
 * @author: 34371
 * @create: 2022-02-22 16:28
 **/
public class LocalMinima {

    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 4;
        arr[1] = 3;
        arr[2] = 5;
        arr[3] = 6;
        arr[4] = 7;
        System.out.println(getLocalMinima(arr));
    }

    public static int getLocalMinima(int[] arr){
        if (arr == null || arr.length<2){
            return -1;
        }

        if (arr[0]<arr[1]){
            return 0;
        }

        int right = arr.length-1;
        if (arr[right]<arr[right-1]){
            return right;
        }

        int left = 0;
        int result = -1;
        while (left<right){
            int mid = (right-left)>>1+left;
            if (arr[mid]<arr[mid-1]&&arr[mid]<arr[mid+1]){
                result = mid;
                break;
            }else if (arr[mid]>arr[mid-1]){
                //继续向左寻找即可
                right = mid;
            }else if (arr[mid]>arr[mid+1]){
                //向右寻找
                left = mid;
            }else{
                break;
            }
        }

        return result;
    }

}
