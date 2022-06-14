package brush.questions.chapter14;

/**
 * @program: my_algorithm
 * @description 给定一个棵完全二叉树，返回这棵树的节点个数，要求时间复杂度小于O(树的节点数)
 * @author: 34371
 * @create: 2022-06-08 10:56
 **/
public class CompleteTreeNodeNum {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.left.left = new Node(8);
        System.out.println(getNodesNum(head));

    }

    public static int getNodesNum(Node root) {
        if (root == null) {
            return 0;
        }

        int treeDeep = getTreeDeep(root);
        int rightTreeDeep = getTreeDeep(root.right);
        int nodeNums = 0;
        if (rightTreeDeep < treeDeep - 1) {
            //右子树满了，直接套用公式可以算出来
            nodeNums += (1 << rightTreeDeep) - 1;
            //加上根节点
            nodeNums += 1;
            //左子树需要再计算
            nodeNums += getNodesNum(root.left);
        } else {
            //左子树满了
            nodeNums += (1 << (treeDeep - 1)) - 1;
            //加上根节点
            nodeNums += 1;
            //右子树需要再计算
            nodeNums += getNodesNum(root.right);
        }
        return nodeNums;
    }

    public static int getTreeDeep(Node root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

}
