package com.company.monotonic.stack;

import java.util.Arrays;
import java.util.Stack;

//单调栈
//在数组中找一个数，左边和右边比这个数小，且距离这个数最近的位置
public class MonotonicStack {

    public static void main(String[] args) {
        int[] test = new int[]{1, 6, 3, 7, 4, 5};
        int[][] indexFromArr = getIndexFromArr(test);
        Arrays.stream(indexFromArr).forEach(

                arr -> System.out.println(Arrays.toString(arr))
        );
    }

    //[1,6,3,7,4,5] ->[[-1,-1],[0,2],[0,-1],[2,4],[2,-1],[4,-1]]
    //数组中无重复值
    public static int[][] getIndexFromArr(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return null;
        }
        int[][] result = new int[arr.length][2];
        Stack<Integer> smallStack = new Stack<>();
        smallStack.push(0);
        int index = 1;
        while (index < arr.length) {
            if (arr[index] < arr[smallStack.peek()]) {
                //当下一个值比栈顶的值小的时候，弹出栈顶的值，假设为a，此时，a的左边结果
                //就是新的栈顶的值；a的右边结果就是即将新加进来的值
                //PS:栈中存的是下标值
                Integer i = smallStack.pop();
                int left = smallStack.isEmpty() ? -1 : smallStack.peek();
                result[i] = new int[]{left, index};
            }
            smallStack.push(index);
            index++;
        }

        while (!smallStack.isEmpty()) {
            //压栈过程结束以后，依次弹出，弹出结果为：左边为当前元素的下一个元素，
            //右边无结果为-1
            Integer pop = smallStack.pop();
            int next = smallStack.isEmpty() ? -1 : smallStack.peek();
            result[pop] = new int[]{next, -1};
        }


        return result;

    }

}
