package com.company.link.doubly;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-02-23 12:38
 **/
public class DoublyLinkedList {

    //双链表的反转
    public static Node reverseDoublyLinkedList(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

}
