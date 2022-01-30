package com.company.binary.tree;

import java.util.LinkedList;
import java.util.Queue;

//宽度优先遍历
public class BreadthFirstTraversalDemo {

    //求二叉树的最大宽度
    public static int getTreeMaxWidth(BinaryTreeNode head) {
        int max = 0;
        if (head != null) {
            BinaryTreeNode cur ;
            BinaryTreeNode curEnd = head;
            BinaryTreeNode nextEnd = head;
            int curWidth = 0;
            Queue<BinaryTreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(head);
            while (!nodeQueue.isEmpty()) {
                cur = nodeQueue.poll();
                curWidth++;
                if (cur.left != null) {
                    nodeQueue.add(cur.left);
                    nextEnd = cur.left;
                }
                if (cur.right != null) {
                    nodeQueue.add(cur.right);
                    nextEnd = cur.right;
                }
                if (cur == curEnd) {
                    max = Math.max(max, curWidth);
                    curEnd = nextEnd;
                    nextEnd = null;
                    curWidth = 0;
                }
            }

        }
        return max;
    }

    public static void breadthFirstTraversal(BinaryTreeNode head) {
        if (head != null) {
//            LinkedBlockingQueue<BinaryTreeNode> nodesQueue =new LinkedBlockingQueue<>();
            Queue<BinaryTreeNode> nodesQueue = new LinkedList<>();
            nodesQueue.add(head);
            while (!nodesQueue.isEmpty()) {
                BinaryTreeNode poll = nodesQueue.poll();
                doProcess(poll);
                if (poll.left != null) {
                    nodesQueue.add(poll.left);
                }
                if (poll.right != null) {
                    nodesQueue.add(poll.right);
                }
            }
        }
    }

    private static void doProcess(BinaryTreeNode node) {
        System.out.println(node);
    }

}
