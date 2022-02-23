package com.company.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: my_algorithm
 * @description 队列实现栈
 * @author: 34371
 * @create: 2022-02-23 15:36
 **/
public class QueueImplStack {

    private final Queue<Integer> queue = new LinkedList<>();
    private final Queue<Integer> anotherQueue = new LinkedList<>();
    //切换路径可以通过互换引用实现，不一定要靠开关
    private boolean inQueue = true;

    public void push(int num){

        if (inQueue){
            queue.add(num);
        }else {
            anotherQueue.add(num);
        }

    }

    private void moveData(Queue<Integer> resource, Queue<Integer> dest) {
        if (dest.isEmpty()){
            while (!resource.isEmpty()&&resource.size()!=1){
                dest.add(resource.poll());
            }
        }
    }

    public int pop(){
        if (queue.isEmpty()&&anotherQueue.isEmpty()){
            throw new RuntimeException("栈空");
        }
        int result;
        if (inQueue){
            moveData(queue,anotherQueue);
            result = queue.poll();
            this.inQueue = false;
        }else{
            moveData(anotherQueue,queue);
            result = anotherQueue.poll();
            this.inQueue = true;
        }

        return result;
    }

}
