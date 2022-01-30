package com.company.binary.tree;

import java.util.Stack;

//非递归遍历二叉树
public class TraverseDemo {

    public static void main(String[] args) {
        BinaryTreeNode head = init();
//        BreadthFirstTraversalDemo.breadthFirstTraversal(head);
        System.out.println(BreadthFirstTraversalDemo.getTreeMaxWidth(head));
    }

    public static void recursiveTraversal(BinaryTreeNode head){
        if (head == null){
            return;
        }

        recursiveTraversal(head.left);
        System.out.print(head.value+"\t");
        recursiveTraversal(head.right);
    }

    public static void in(BinaryTreeNode cur) {
        System.out.print("in-order: ");
        if (cur != null) {
            Stack<BinaryTreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }


    public static void inorderTraversal(BinaryTreeNode head){
        if (head != null){
            Stack<BinaryTreeNode> stack = new Stack<>();
            BinaryTreeNode cur = head;

            while (cur!=null||!stack.isEmpty()){
               if (cur!=null){
                   stack.push(cur);
                   cur = cur.left;
               }else{
                   cur = stack.pop();
                   System.out.print(cur.value+"\t");
                   cur = cur.right;
               }
            }
        }
    }

    //后序遍历 思路：先中右左 -》 再左右中
    public static void postOrderTraversal(BinaryTreeNode head) {
        if (head == null) {
            return;
        }

        //此时排完是中右左的顺序
        Stack<BinaryTreeNode> stack = new Stack<>();
        Stack<BinaryTreeNode> stack1 = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            BinaryTreeNode pop = stack.pop();
            stack1.push(pop);

            if (pop.left != null) {
                stack.push(pop.left);
            }

            if (pop.right != null) {
                stack.push(pop.right);
            }
        }

        while (!stack1.isEmpty()){
            System.out.print(stack1.pop().value+"\t");
        }

    }

    //先序遍历 中左右
    public static void preorderTraversal(BinaryTreeNode head) {
        if (head == null) {
            return;
        }

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            BinaryTreeNode pop = stack.pop();
            System.out.print(pop.value+"\t");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }

    }

    public static BinaryTreeNode init(){
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(3);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(7);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n9 = new BinaryTreeNode(9);
        BinaryTreeNode n10 = new BinaryTreeNode(10);
        BinaryTreeNode n11 = new BinaryTreeNode(11);
        BinaryTreeNode n12 = new BinaryTreeNode(12);

        n1.left = n2;
        n1.right = n3;
        n2.left = n11;
        n11.left = n12;
        n2.right = n4;
        n3.left = n5;
//        n3.right = n7;
        n4.left = n6;
        n4.right = n7;
        n5.left = n8;
        n5.right = n9;
//        n6.left = n9;
//        n9.left = n10;
//        n7.right = n11;
//        n6.right = n12;
        return n1;
    }


}
