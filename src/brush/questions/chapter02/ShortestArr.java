package brush.questions.chapter02;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @program: my_algorithm
 * @description 给定一个数组arr，只能对arr中的一个子数组排序，但是想让arr整体都有序，返回满足这一设定的子数组中最短的是多长
 *              ps：这里根本用不到单调栈。。。。从左往右找右边界，从右往左找左边界即可。。。。
 * @author: 34371
 * @create: 2022-05-10 21:37
 **/
public class ShortestArr {

    static class Info implements Comparable<Info> {
        //包含该索引 -1除外
        int leftBound;
        //包含该索引 -1除外
        int rightBound;
        //原信息总长度
        int length;

        public Info(int leftBound, int rightBound, int length) {
            this.leftBound = leftBound;
            this.rightBound = rightBound;
            this.length = length;
        }

        @Override
        public int compareTo(Info o) {
            int thisL = leftBound == -1 ? 0 : leftBound;
            int thisR = rightBound == -1 ? length - 1 : rightBound;
            int oL = o.leftBound == -1 ? 0 : o.leftBound;
            int oR = o.rightBound == -1 ? o.length - 1 : o.rightBound;
            return thisR - thisL - (oR - oL);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "leftBound=" + leftBound +
                    ", rightBound=" + rightBound +
                    '}';
        }
    }

    //左边和右边比该数大的  比较容易判断单调减
    public static Info[] monotonicDecreaseStack(int[] arr) {

        //存的是下标
        Stack<Integer> stack = new Stack<>();
        Info[] result = new Info[arr.length];
        stack.add(0);
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                result[stack.pop()] = new Info(stack.isEmpty() ? -1 : stack.peek(), i, arr.length);
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = new Info(stack.isEmpty() ? -1 : stack.peek(), -1, arr.length);
        }

        return result;
    }

    // 左边和右边比该数小的 ： 比较容易判断单调增
    public static Info[] monotonicIncreaseStack(int[] arr) {

        //存的是下标
        Stack<Integer> stack = new Stack<>();
        Info[] result = new Info[arr.length];
        stack.add(0);
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                result[stack.pop()] = new Info(stack.isEmpty() ? -1 : stack.peek(), i, arr.length);
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = new Info(stack.isEmpty() ? -1 : stack.peek(), -1, arr.length);
        }

        return result;
    }

//    //单调减栈: 左边和右边比该数小的  ==> 改用对象，这里记作一个模板，方便回滚
//    public static int[][] monotonicDecreaseStack(int[] arr) {
//
//        //存的是下标
//        Stack<Integer> stack = new Stack<>();
//        int[][] result = new int[arr.length][2];
//        stack.add(0);
//        for (int i = 1; i < arr.length; i++) {
//            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
//                Integer pop = stack.pop();
//                result[pop][0] = stack.isEmpty() ? -1 : stack.peek();
//                result[pop][1] = i;
//            }
//            stack.add(i);
//        }
//
//        while (!stack.isEmpty()) {
//            Integer pop = stack.pop();
//            result[pop][0] = stack.isEmpty() ? -1 : stack.peek();
//            result[pop][1] = -1;
//        }
//
//        return result;
//    }

}
