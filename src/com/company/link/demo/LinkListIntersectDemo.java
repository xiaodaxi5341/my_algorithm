package com.company.link.demo;

import com.company.link.base.Node;

import java.util.*;

//链表相交返回相交的第一个节点（给定头节点）
public class LinkListIntersectDemo {

    public static void main(String[] args) {

        //===============================  无环 ================================
        //情形1：无相交
        Node<Integer> head1 = generateNode(false, 5);
        Node<Integer> head2 = generateNode(false, 5);

        //理论返回值：null
        System.out.println("【无环，无相交】节点：" + intersectFirstNode(head1, head2));

        //情形2：相交
        //1：完全相交
        //理论返回值：head1.getNext()
        System.out.println("【无环，完全相交】节点：" + intersectFirstNode(head1, head1.getNext()));

        //2:    部分相交
        //理论返回值：head1.getNext().getNext()
        head2.getNext().setNext(head1.getNext().getNext());
        System.out.println("【无环，部分相交】节点：" + intersectFirstNode(head1, head2));

        // ======================== 有环 ============================
        Node<Integer> loopHead1 = generateNode(true, (int) (Math.random() * 10));
        Node<Integer> loopHead2 = generateNode(true, (int) (Math.random() * 10));

        //理论返回值：null
        System.out.println("【有环，无相交】节点：" + intersectFirstNode(loopHead1, loopHead2));

        //情形2：相交
        //1：完全相交
        //理论返回值：head1.getNext()
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n5);
        n5.setNext(n4);
        n4.setNext(n6);
        n9.setNext(n8);
        n8.setNext(n7);
        n7.setNext(n6);
        n6.setNext(n5);
        System.out.println("【有环，有相交】节点：" + intersectFirstNode(n1, n9));

    }


    public static Node<Integer> generateNode(boolean loop, int length) {
        int[] arr = new int[length + 1];
        arr[length] = -1;
        int a = (int) (Math.random() * 100);
        arr[0] = a;
        Node<Integer> head = new Node<>(a);
        Node<Integer> cur = head;
        for (int i = 0; i < length - 1; i++) {
            a = (int) (Math.random() * 100);
            arr[i + 1] = a;
            cur.setNext(new Node<>(a));
            cur = cur.getNext();
        }
        if (loop) {
            a = (int) (Math.random() * length);
            Node<Integer> loopNode = head;
            for (int i = 0; i < a - 1; i++) {
                loopNode = head.getNext();
            }
            cur.setNext(loopNode);
            arr[length] = loopNode.getValue();
        }

        System.out.println("生成的" + (loop ? "有环：" : "无环：") + "数组：" + Arrays.toString(arr));

        return head;
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

        Node node1End = getEndNode(node1);
        Node node2End = getEndNode(node2);

        //如果单节点成环
        if (node1First == node1 || node2First == node2) {
            return node1 == node2 ? node1 : null;
        }

        //两个都是无环结构：有可能是Y形状的链表
        if (node1First == null && node2First == null) {
            return intersectFirstNodeForNoLoopOrYLoop(node1, node2, null, null,node1End,node2End);
        }
        //一个有环一个无环，则必不会相交
        if (node1First == null || node2First == null) {
            return null;
        }
        //两个都有环：
        //A环中如果有B环的入环节点，返回A环入环节点即可
        Node yLoopNode = intersectFirstNodeForNoLoopOrYLoop(node1, node2, node1First, node2First,node1End,node2End);
        if (yLoopNode != null) {
            return yLoopNode;
        }
        boolean flag = false;
        Node cur = node1First.getNext();
        while (cur != node2First && cur != node1First) {
            cur = cur.getNext();
            flag = cur == node2First;
            if (flag) {
                break;
            }
        }

        //flag为true说明公用一个环
        return flag ? node1First : null;
    }

    private static Node getEndNode(Node node) {
        Set<Node> set = new HashSet<>();
        Node cur = node;
        while (cur != null && !set.contains(cur)) {
            set.add(cur);
            cur = cur.getNext();
        }

        return cur;
    }

    public static Node intersectFirstNodeForNoLoopOrYLoop(Node node1, Node node2, Node firstNode, Node secondNode, Node node1End, Node node2End) {

        Node current = node1;
        while (current!=secondNode){
            current = current.getNext();
            if (current == node1End && current != secondNode ){
                return null;
            }
        }

        Node cur = node1.getNext();
        int i = 0;
        while (cur != null && cur != firstNode) {
            cur = cur.getNext();
            i++;
        }
        cur = node2.getNext();
        while (cur != null && cur != firstNode ) {
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

    public static Node getFirstLoopNodeWithPrint(Node head) {
        Node firstLoopNode = getFirstLoopNode(head);
        System.out.println(String.format("[head:%s]   --->    [firstNode:%s]", head, firstLoopNode));
        return firstLoopNode;
    }

    //获取单个链表的第一个入环节点
    public static Node getFirstLoopNode(Node head) {
        if (head == null || head.getNext() == null) {
            return null;
        }
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
