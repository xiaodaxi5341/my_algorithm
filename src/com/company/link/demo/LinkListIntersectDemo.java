package com.company.link.demo;

import com.company.link.base.Node;

//链表相交返回相交的第一个节点（给定头节点）
public class LinkListIntersectDemo {

    public static void main(String[] args) {
        Node node = new Node(3);
        Node node2 = new Node(4);
        Node node3 = new Node(5);
        node.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node3);
//        node2.setNext(node);
        System.out.println(getFirstLoopNode(node));
    }

    /**
     * 两个链表相交的第一个节点
     *
     * @param node1 链表1的头节点
     * @param node2 链表2的头节点
     * @return
     */
    public static Node intersectFirstNode(Node node1, Node node2) {
        Node node1First = getFirstLoopNode(node1);
        Node node2First = getFirstLoopNode(node2);
        if (node1First == node2First) {
            return node1First;
        }
        //两个都是无环结构：有可能是Y形状的链表
        if (node1First == null && node2First == null) {
            Node cur = node1;
            int i = 0;
            while (cur != null) {
                cur = cur.getNext();
                i++;
            }
            cur = node2;
            while (cur != null) {
                cur = cur.getNext();
                i--;
            }
            //获取到的值就是两个链表的差值
            Node longNode = i > 0 ? node1 : node2;
            Node shortNode = i > 0 ? node2 : node1;
            for (i = Math.abs(i); i > 0; i--) {
                longNode = longNode.getNext();
            }
            //长短链表从相同位置开始比较是否一致
            while (longNode != shortNode) {
                longNode = longNode.getNext();
                shortNode = shortNode.getNext();
            }

            return longNode;
        }
        //一个有环一个无环，则必不会相交
        if (node1First == null || node2First == null) {
            return null;
        }
        //两个都有环：
        //A环中如果有B环的入环节点，返回A环入环节点即可

        Node cur = node1First.getNext();
        boolean flag = false;
        while (cur != node2First && cur != node1First) {
            cur = cur.getNext();
            flag = cur == node2First;
            if (flag){
                break;
            }
        }
        //flag为true说明公用一个环

        return flag?node1First:null;
    }

    //获取单个链表的第一个入环节点
    public static Node getFirstLoopNode(Node head) {
        if (head == null || head.getNext() == null) {
            return null;
        }
        //判断是否两个节点是否会构成环结构
//        if (head.getNext().getNext() == null) {
//            //两节点未成环
//            return null;
//        } else if (head.getNext().getNext() == head) {
//            return head;
//        }
        //快慢指针
        Node slowNode = head.getNext();
        Node fastNode = head.getNext().getNext();

        boolean isLoop = false;
        //快慢指针第一次相遇或者非环结构
        while (fastNode != null && fastNode.getNext() != null) {
            if (fastNode == slowNode) {
                fastNode = head;
                isLoop = true;
                break;
            }
            fastNode = fastNode.getNext().getNext();
            if (fastNode != null) {
                slowNode = slowNode.getNext();
            }
        }

        //此时
        if (!isLoop) {
            //说明是无环结构
            return null;
        } else {
            while (fastNode != slowNode) {
                fastNode = fastNode.getNext();
                slowNode = slowNode.getNext();
            }
            return fastNode;
        }

    }

}
