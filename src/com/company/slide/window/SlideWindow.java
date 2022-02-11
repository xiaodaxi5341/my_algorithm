package com.company.slide.window;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口
 */
public class SlideWindow {

    public static void main(String[] args) {
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        List<Integer> maxInWindow = getMaxInWindow(arr);
        maxInWindow.forEach(num->System.out.println(arr[num]));
    }

    public static List<Integer> getMaxInWindow(int[] arr) {
        if (arr == null||arr.length<3) {
            throw new NullPointerException();
        }

        int left = -1;
        int right = -1;
        Deque<Integer> deque = new LinkedList<>();

        //假设题是这样：滑动窗口中一共三个值，左右同时移动
        moveRight(arr,right,3,deque);
        right = 2;
        moveLeft(arr,left,1,deque);
        left = 0;

        List<Integer> list = new ArrayList<>();
        list.add(deque.getFirst());
        while (right+1<arr.length){
            moveRight(arr,right,1,deque);
            right++;
            moveLeft(arr,left,1,deque);
            left++;
            list.add(deque.getFirst());
        }

        return list;
    }

    public static void moveRight(int[] arr, int curRight, int step, Deque<Integer> deque) {
        for (int i = 1; i <= step; i++) {
            //右侧越界
            if (curRight >= arr.length || curRight + i >= arr.length) {
                break;
            }

            //右侧新加的值大于等于双端队列尾部的值时，丢弃双端队列中的这些值
            //直到右侧新加的值比队尾的值小，或者队列空了
            while (!deque.isEmpty()&&arr[curRight + i] >= arr[deque.getLast()]){
                //丢弃小的值
                deque.pollLast();
            }
            deque.addLast(curRight+i);
        }
    }

    public static void moveLeft(int[] arr, int curLeft, int step, Deque<Integer> deque) {
        for (int i = 1; i <= step; i++) {
            //左标越界
            if (curLeft >= arr.length || curLeft + i >= arr.length) {
                break;
            }
            if ((curLeft + i -1) == deque.getFirst()) {
                deque.pollFirst();
            }
        }
    }

}
