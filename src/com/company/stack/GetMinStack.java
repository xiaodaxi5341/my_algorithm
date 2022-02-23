package com.company.stack;

import java.util.Stack;

/**
 * @program: my_algorithm
 * @description 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * @author: 34371
 * @create: 2022-02-23 15:01
 **/
public class GetMinStack {

    Stack<Integer> normalStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int num){
        normalStack.push(num);
        if (minStack.isEmpty()||num<=minStack.peek()){
            minStack.push(num);
        }
    }

    public int pop(){
        int num = normalStack.pop();
        if (num == minStack.peek()){
            minStack.pop();
        }
        return num;
    }

    public int getMin(){
        if (normalStack.isEmpty()){
            throw new RuntimeException("空了");
        }
        return minStack.peek();
    }

}
