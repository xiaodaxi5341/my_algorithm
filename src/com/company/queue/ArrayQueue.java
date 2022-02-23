package com.company.queue;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-02-23 14:34
 **/
public class ArrayQueue {

    private int pushIndex;
    private int pollIndex;
    private final int[] values;
    private int size;
    private final int limit;

    public ArrayQueue(int limit) {
        this.limit = limit;
        size = 0;
        values = new int[limit];
        pushIndex = 0;
        pollIndex = 0;
    }

    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("队列满");
        }

        values[pushIndex] = num;
        size++;
        pushIndex = nextIndex(pushIndex);
    }

    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int num = values[pollIndex];
        size--;
        pollIndex = nextIndex(pollIndex);
        return num;
    }

    private int nextIndex(int index) {
        return index == limit - 1 ? 0 : index + 1;
    }

    private void correctPollIndex() {
        if (pollIndex == values.length) {
            this.pollIndex = 0;
        }
    }

    private void correctPushIndex() {
        if (pushIndex == values.length) {
            this.pushIndex = 0;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == limit;
    }
}
