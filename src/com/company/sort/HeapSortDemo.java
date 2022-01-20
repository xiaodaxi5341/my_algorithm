package com.company.sort;

import java.util.Arrays;
import java.util.Scanner;

import static com.company.util.CommonUtils.swap;

//堆排序是建立在完全二叉树的基础上  此处写大根堆
public class HeapSortDemo {

    public static void main(String[] args) {
        BigHeap bigHeap = new BigHeap(100);
        for (int i = 0; i < 5; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入数字：");
            int j = scanner.nextInt();
            bigHeap.heapInsert(j);
            System.out.println(bigHeap);
        }

        //去掉后再打
        for (int i = 0 ;i <5;i++){
            bigHeap.pop();
            System.out.println(" pop "+i+" time :    "+bigHeap);
        }

    }

    public static class BigHeap {
        //堆容量
        private int size;
        //当前指针位置
        private int cur;
        //存放数据
        private int[] data;

        public BigHeap(int size) {
            this.size = size;
            this.cur = 0;
            this.data = new int[size];
        }

        //往对结构插入数据
        public void heapInsert(int a) {
            if (isFull()) {
                throw new RuntimeException("full data");
            }
            //思路：先将数据插入数组末尾，随后与其父节点进行比较交换，直到不能交换为止，此时的结构就是大根堆结构
            int i = cur++;
            data[i] = a;
            //当前节点值大于其父节点的值，交换
            while (data[i] > data[(i - 1) / 2]) {
                swap(data, i, (i - 1) / 2);
                i = (i - 1) >> 1;
            }
        }

        //弹出数据
        public int pop() {
            return sink();
        }

        //弹出数据后保持大根堆结构
        private int sink() {
            if (isEmpty()) {
                throw new RuntimeException("empty data");
            }

            int head = data[0];
            //先交换首尾，然后将0位置的数据下沉
            swap(data, 0, cur - 1);
            data[cur-1] = Integer.MIN_VALUE;
            cur--;
            //下沉过程：当前值与子节点的最大值比较，如果当前节点小，则互换位置
            int i = 0;
            while ((i << 1) + 2 < cur) {
                int index = data[(i << 1) + 1] > data[(i << 1) + 2] ? (i << 1) + 1 : (i << 1) + 2;
                if (data[i] < data[index]) {
                    swap(data, i, index);
                    i = index;
                } else {
                    break;
                }
            }

            if ((i << 1) + 1 < cur && data[(i << 1) + 1] > data[i]) {
                //说明已经不会有右节点，但是会有左节点
                swap(data, i, (i << 1) + 1);
            }


            return head;

        }

        public boolean isEmpty() {
            return cur == 0 || data == null;
        }

        public boolean isFull() {
            return size == cur;
        }

        @Override
        public String toString() {
            return Arrays.toString(data);
        }
    }


}
