package com.company.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import static com.company.util.CommonUtils.swap;

//堆排序是建立在完全二叉树的基础上  此处写大根堆 O(N*logN)  空间复杂度：O(1)
public class HeapSortDemo {

    public static void main(String[] args) {

        int counts = 0;
        while (counts < 1000000) {
            int[] toTestArr = MergeSortDemo.generateRandomArray(100000, 10000);
//            int[] toTestArr = {3393, 2941, -5764, -288, 7640, 2972, 3082, -1685};
            int[] arr = new int[toTestArr.length];
            int[] origin = new int[toTestArr.length];
            System.arraycopy(toTestArr, 0, arr, 0, toTestArr.length);
            System.arraycopy(toTestArr, 0, origin, 0, toTestArr.length);
            HeapSortDemo heapSortDemo = new HeapSortDemo(toTestArr.length);
            for (int num : toTestArr){
                heapSortDemo.add(num);
            }

            Arrays.sort(arr);
            for (int i = arr.length-1; i >= 0; i--) {
                if (arr[i] != heapSortDemo.pop()) {
                    System.out.println(Arrays.toString(origin));
                    return;
                }
            }

            counts++;
        }

        System.out.println("end");

    }

    int[] values;
    int size;

    HeapSortDemo(int limit){
        values = new int[limit];
        size = 0;
    }

    public void add(int val){
        if (isFull()){
            throw new RuntimeException("堆满");
        }

        heapInsert(val);
    }

    private void heapInsert(int val) {
        int cur = size;
        values[size] = val;
        while (values[cur]>values[(cur-1)/2]){
            swap(values,cur,(cur-1)/2);
            cur = ((cur-1)/2);
        }
        size++;
    }


    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("堆空");
        }
        int cur = 0;
        int result = values[0];

        //调整大根堆
        swap(values,0,--size);

        heapfy(cur);
        return result;
    }

    private void heapfy(int cur) {
        //当一定有右子节点时
        while (((cur*2)+2)<size){
            int largerIndex = values[((cur*2)+2)] > values[((cur*2)+1)] ?((cur*2)+2) : ((cur*2)+1);
            if (values[cur]<values[largerIndex]){
                swap(values,cur,largerIndex);
                cur = largerIndex;
            }else{
                break;
            }
        }

        //没有右，只有左
        while (((cur*2)+1)<size){
            if (values[cur] < values[(cur*2)+1]){
                swap(values,cur,(cur*2)+1);
                cur = (cur*2)+1;
            }else {
                break;
            }
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == values.length;
    }

}
