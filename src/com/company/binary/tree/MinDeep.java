package com.company.binary.tree;

public class MinDeep {

    public static int process(BinaryTreeNode head) {
        if (head.right == null && head.left == null) {
            return 1;
        }

        int leftHeight = Integer.MAX_VALUE;
        if (head.left != null) {

            leftHeight = process(head.left);
        }
        int rightHeight = Integer.MAX_VALUE;
        if (head.right != null) {
            rightHeight = process(head.right);
        }


        return Math.min(leftHeight, rightHeight) + 1;

    }


}
