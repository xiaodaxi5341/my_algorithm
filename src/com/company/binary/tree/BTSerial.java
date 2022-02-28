package com.company.binary.tree;

import com.company.link.base.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: my_algorithm
 * @description 树的序列化
 * @author: 34371
 * @create: 2022-02-28 23:04
 **/
public class BTSerial {

    public static void main(String[] args) {
        BinaryTreeNode head = TraverseDemo.init();
        Queue<String> queue = serialTree(head);
        while (!queue.isEmpty()){
//            String s = queue.peek()==null?"null":queue.poll();
            System.out.println(queue.poll());
        }
    }

    //先序序列化
    public static Queue<String> serialTree(BinaryTreeNode head) {
        if (head == null) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        preProcess(head, queue);
        return queue;
    }

    private static void preProcess(BinaryTreeNode head, Queue<String> queue) {

        queue.add(head == null?null:String.valueOf(head.value));

        if (head!=null){
            preProcess(head.left,queue);
            preProcess(head.right,queue);
        }

    }

}
