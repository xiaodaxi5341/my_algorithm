package com.company.sort;

import java.util.Arrays;

//随机快排，由荷兰国旗问题衍生
public class RandomQuickSortDemo {

    public static void main(String[] args) {
        int[] ints = MergeSortDemo.generateRandomArray(10, 100);
//        int[] ints = {-26,30,-9,10,23,14,33,1,-37,2,64,-84,38,34,4,-1,-70,-12,-57,-75,-11,-6,-5,-26,-37,36,29,33,-7,-7,-5,65,-68,-3,-59,19,38,-56};
        System.out.println(Arrays.toString(ints));
        RandomQuickSortDemo demo = new RandomQuickSortDemo();
        demo.quickSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void quickSort(int[] toSortArr) {
        if (toSortArr == null || toSortArr.length<2){
            return;
        }
        quickSort(toSortArr, 0, toSortArr.length - 1, toSortArr[toSortArr.length - 1]);
    }

    public  static void swap(int[] toSortArr, int left, int right) {
        int temp = toSortArr[left];
        toSortArr[left] = toSortArr[right];
        toSortArr[right] = temp;
    }

    public  static void quickSort(int[] toSortArr, int left, int right, int num) {
//        System.out.println(num+":  "+Arrays.toString(toSortArr));

        if (left >= right) {
            return;
        }

        int index = left;
        int rightIndex = 0;
        for (int i = left;i<=right-rightIndex;i++){
            int cur = toSortArr[i];
            if (cur <num){
                swap(toSortArr,i,index);
                index++;
            }

            if (cur >num){
               swap(toSortArr,i,right-rightIndex);
               rightIndex++;
               i--;
            }

        }

        //左边快排
        if (index - 1>0){
            quickSort(toSortArr,left,index-1,toSortArr[index-1]);
        }
        //右边快排
        if (rightIndex>0){
            quickSort(toSortArr,right-rightIndex,right,toSortArr[right]);
        }

    }

}
