package com.company.stack;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-02-23 15:07
 **/
public class GetMinStackDemo {

    public static void main(String[] args) {
        int[] arr = {5,7,3,2,-1,6,9,1,15,-2};
        GetMinStack minStack = new GetMinStack();
        for (int j : arr){
            minStack.push(j);
        }

        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.push(-100);
        minStack.push(-102);
        System.out.println(minStack.getMin());
    }

}
