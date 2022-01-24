package com.company.sort;

import java.util.Arrays;

import static com.company.util.CommonUtils.swap;

//堆排序是建立在完全二叉树的基础上  此处写大根堆
public class HeapSortDemo {

    public static void main(String[] a){
        int[] arr = new int[]{
                21,-36, -35, -16, 4, 17, -30
        };

        BigHeap bigHeap = new BigHeap(arr.length);
        bigHeap.heapSort(arr);
        System.out.println(Arrays.toString(arr));


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

        public void heapSort(int[] arr) {
            if (arr == null || arr.length < 2) {
                return;
            }
            int i=0;
            for (;i<arr.length;i++){
                push(arr[i]);
            }

            //得到了大根堆,将大概堆进行排序
            while (!isEmpty()){
                arr[--i] = pop();
            }
        }

        //往对结构插入数据
        public void push(int a) {
            if (isFull()) {
                throw new RuntimeException("full data");
            }
            //思路：先将数据插入数组末尾，随后与其父节点进行比较交换，直到不能交换为止，此时的结构就是大根堆结构
            int i = cur++;
            heapInsert(a, i);
        }

        /**
         * //思路：先将数据插入数组指定位置，随后与其父节点进行比较交换，直到不能交换为止，此时的结构就是大根堆结构
         *
         * @param a     插入的数据
         * @param index 插入的位置
         */
        public void heapInsert(int a, int index) {
            data[index] = a;
            while (data[index] > data[(index - 1) / 2]) {
                swap(data, index, (index - 1) / 2);
                index = (index - 1) >> 1;
            }
        }

        //弹出数据
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("已经空了");
            }

            int head = data[0];
            swap(data, 0, cur - 1);
            cur--;
            heapify(data[0], 0);
            return head;
        }

        private void heapify(int a, int index) {
            data[index] = a;
            int subLeftIndex = index * 2 + 1;


            while (subLeftIndex < cur) {
                int subRightIndex = index * 2 + 2;
                int biggerValIndex = subRightIndex >= cur ? subLeftIndex :
                        (data[subLeftIndex] > data[subRightIndex] ? subLeftIndex : subRightIndex);
                if (data[biggerValIndex]>data[index]){
                    swap(data, biggerValIndex, index);
                }
                index = biggerValIndex;
                subLeftIndex = index * 2 + 1;
            }
        }

        public void setValue(int index, int a) {
            data[index] = a;
            heapify(a, index);
            heapInsert(a, index);
        }

        //弹出数据后保持大根堆结构
        private int sink() {
            if (isEmpty()) {
                throw new RuntimeException("empty data");
            }

            int head = data[0];
            //先交换首尾，然后将0位置的数据下沉
            swap(data, 0, cur - 1);
            data[cur - 1] = Integer.MIN_VALUE;
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
            return "BigHeap{" +
                    "size=" + size +
                    ", cur=" + cur +
                    ", data=" + Arrays.toString(data) +
                    '}';
        }
    }


}
