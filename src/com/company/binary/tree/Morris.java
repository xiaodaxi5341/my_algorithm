package com.company.binary.tree;

public class Morris {

    public static void traverse(BinaryTreeNode head) {

        if (head == null) {
            return;
        }

        BinaryTreeNode cur = head;
        BinaryTreeNode mostRightNode = null;

        while (cur != null) {
            //如果cur有左子树，则移到mostRightNode移到左树最右节点
            if (cur.left != null) {
                mostRightNode = cur.left;
                while (mostRightNode.right != null && mostRightNode.right != cur) {
                    mostRightNode = mostRightNode.right;
                }
                //移到最右节点以后,如果最右节点的右子树为空，则将其指向cur,同时cur左移
                if (mostRightNode.right == null) {
                    mostRightNode.right = cur;
                    cur = cur.left;
                    continue;
                } else if (mostRightNode.right == cur) {
                    //移到最右节点以后,如果其右子树指向cur，则cur右移，之后最右节点的右指针指挥null
                    mostRightNode.right = null;
                }

            }
            cur = cur.right;

        }


    }

    //先序遍历
    public static void traversePre(BinaryTreeNode head) {

        if (head == null) {
            return;
        }

        BinaryTreeNode cur = head;
        BinaryTreeNode mostRightNode = null;

        while (cur != null) {
            mostRightNode = cur.left;
            if (mostRightNode != null) {
                while (mostRightNode.right != null && mostRightNode.right != cur) {
                    mostRightNode = mostRightNode.right;
                }
                if (mostRightNode.right == null) {
                    System.out.println(cur.value + "  ");
                    mostRightNode.right = cur;
                    cur = cur.left;
                    continue;
                } else if (mostRightNode.right == cur) {
                    mostRightNode.right = null;
                }

            }else {
                System.out.println(cur.value + "  ");
            }
            cur = cur.right;

        }


    }

    //中序遍历
    public static void traverseIn(BinaryTreeNode head) {

        if (head == null) {
            return;
        }

        BinaryTreeNode cur = head;
        BinaryTreeNode mostRightNode = null;

        while (cur != null) {
            mostRightNode = cur.left;
            if (mostRightNode != null) {
                while (mostRightNode.right != null && mostRightNode.right != cur) {
                    mostRightNode = mostRightNode.right;
                }
                if (mostRightNode.right == null) {
                    mostRightNode.right = cur;
                    cur = cur.left;
                    continue;
                } else if (mostRightNode.right == cur) {
                    mostRightNode.right = null;
                }

            }
                System.out.println(cur.value + "  ");
            cur = cur.right;

        }


    }

    //后序遍历
    public static void traversePost(BinaryTreeNode head) {

        if (head == null) {
            return;
        }

        BinaryTreeNode cur = head;
        BinaryTreeNode mostRightNode = null;

        while (cur != null) {
            mostRightNode = cur.left;
            if (mostRightNode != null) {
                while (mostRightNode.right != null && mostRightNode.right != cur) {
                    mostRightNode = mostRightNode.right;
                }
                if (mostRightNode.right == null) {
                    mostRightNode.right = cur;
                    cur = cur.left;
                    continue;
                } else if (mostRightNode.right == cur) {
                    //打印其左子树的右边界
                    mostRightNode.right = null;
                    printRightEdge(cur);
                }

            }
            cur = cur.right;

        }

        //逆序打印整个树的右边界
        BinaryTreeNode treeNode = reverseRight(head);
        print(treeNode);
        reverseRight(treeNode);
        System.out.println("校验树状结构");

    }

    private static void printRightEdge(BinaryTreeNode head) {
        if (head!=null && head.left!=null){
            //从该节点开始打印
            BinaryTreeNode node = head.left;
            BinaryTreeNode treeNode = reverseRight(node);
            print(treeNode);
            reverseRight(treeNode);
        }
    }

    private static void print(BinaryTreeNode head){
        BinaryTreeNode cur = head;
        while (cur!=null){
            System.out.print(cur.value+"  ");
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
//        BinaryTreeNode n3 = new BinaryTreeNode(3);
//        BinaryTreeNode n4 = new BinaryTreeNode(4);
//        BinaryTreeNode n5 = new BinaryTreeNode(5);
//        BinaryTreeNode n6 = new BinaryTreeNode(6);
        n1.right = n2;
//        n2.right = n3;
//        n3.right = n4;
//        n4.right = n5;
//        n5.right = n6;

        BinaryTreeNode treeNode = reverseRight(n1);
        BinaryTreeNode cur = reverseRight(treeNode);
        while (cur!=null){
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    public static BinaryTreeNode reverseRight(BinaryTreeNode head){
        if (head == null || head.right == null){
            return head;
        }

        BinaryTreeNode node = head;
        BinaryTreeNode sonNode = head.right;
        BinaryTreeNode temp = head.right.right;
        node.right = null;
        while(temp!=null){
            sonNode.right = node;
            node = sonNode;
            sonNode = temp;
            temp = temp.right;
        }

        sonNode.right = node;
        return sonNode;
    }

}
