package com.company.stack;

import java.util.Stack;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-02-23 16:12
 **/
public class QueueImplStackDemo {

    public static void main(String[] args) {
        QueueImplStack queueImplStack = new QueueImplStack();
        Stack<Integer> stack = new Stack<>();

        int times = 0;
        while (times < 1000) {
            int i = 0;
            while (i < 1000) {
                int num = (int) (Math.random() * 10000);
                queueImplStack.push(num);
                stack.push(num);
                i++;
            }

            while (!stack.isEmpty()) {
                if (stack.pop() != queueImplStack.pop()) {
                    System.out.println("error");
                    break;
                }
            }
            times++;
        }


        System.out.println("end");
    }

}
