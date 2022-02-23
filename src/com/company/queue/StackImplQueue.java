package com.company.queue;

import java.util.Stack;

/**
 * @program: my_algorithm
 * @description 用栈实现队列
 * @author: 34371
 * @create: 2022-02-23 15:30
 **/
public class StackImplQueue {

    private final Stack<Integer> firstStack = new Stack<>();
    private final Stack<Integer> secondStack = new Stack<>();

    public void push(int num){
        firstStack.push(num);
        if (secondStack.isEmpty()){
            while (!firstStack.isEmpty()){
                secondStack.push(firstStack.pop());
            }
        }
    }

    public int poll(){
        if (secondStack.isEmpty()){
            while (!firstStack.isEmpty()){
                secondStack.push(firstStack.pop());
            }
        }

        return secondStack.pop();
    }

}
