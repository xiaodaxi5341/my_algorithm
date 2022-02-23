package com.company.stack;

import java.util.Stack;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-02-23 14:19
 **/
public class TestDemo {

    public static void main(String[] args) {
        int totalTime = 0;
        while (totalTime<100000){
            int size = (int)(Math.random()*200);
            ArrayStack arrayStack = new ArrayStack(size);
            Stack<Integer> stack = new Stack<>();

            int times = (int)(Math.random()* (arrayStack.getSize()+1));

            for (int i = 0 ; i< times;i++){
                int num = (int)(Math.random()*1000);
                arrayStack.push(num);
                stack.push(num);
            }

            while (!arrayStack.isEmpty()||!stack.isEmpty()){
                if (arrayStack.pop()!= stack.pop()){
                    System.out.println("error");
                    break;
                }
            }

            totalTime++;
        }


        System.out.println("success");

    }

}
