package com.company.stack;

/**
 * @program: my_algorithm
 * @description 用数组实现栈结构
 * @author: 34371
 * @create: 2022-02-23 14:07
 **/
public class ArrayStack {

    private int index;
    int[] values;

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满了");
        }
        values[++index] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空栈");
        }

        return values[index--];
    }

    public ArrayStack() {
        values = new int[5];
        index = -1;
    }

    public ArrayStack(int size) {
        values = new int[size];
        index = -1;
    }

    public boolean isFull() {
        return index == values.length ;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public int getSize(){
        return values.length;
    }

}
