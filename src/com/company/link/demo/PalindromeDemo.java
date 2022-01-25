package com.company.link.demo;

import com.company.link.base.MyLinkedList;
import com.company.link.base.Node;
import com.company.util.CommonUtils;

/**
 * 链表回文数 --- 劣質版本
 */
public class PalindromeDemo {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(1000);
        myLinkedList.insert(2000);
        myLinkedList.insert(23);
        myLinkedList.insert(3000);
        myLinkedList.insert(2000);
        myLinkedList.insert(200);
        System.out.println(isPalindromeLink(myLinkedList));

        System.out.println("----");
    }


    //给一个链表，判断是否是回文链表
    public static boolean isPalindromeLink(MyLinkedList toTestList){
        if (toTestList.getSize() == 0||toTestList.getSize()==1){
            return true;
        }
        if (toTestList.getSize()==2){
            return CommonUtils.equals(
                    toTestList.getFirstNode().getValue(),
                    toTestList.getFirstNode().getNext().getValue());
        }
        int slowIndex = 0;
        int fastIndex = 0;
        Node node = toTestList.getFirstNode();
        while(fastIndex<toTestList.getSize()){
            fastIndex+=2;
            if (fastIndex<toTestList.getSize()){
                slowIndex+=1;
                node = node.getNext();
            }
        }
        System.out.println("中点的值："+node.getValue());
        //退出循环的时候，慢指针正好走到中点，node即为中点node
        //中点要分为奇数和偶数两种情况
        boolean isOddSize = (toTestList.getSize()&1) == 1;
        // 将后续的节点的指针反转：1，将原来的一个链表转为左右两个链表 2，两个链表以中点为终点
        Node next = node.getNext();
        if(!isOddSize){
            //偶数时，当前node的下一个node为右链表的尾节点
            node.setNext(null);
            node = next;
            next = node.getNext();
            slowIndex++;
        }
        Node nextNext = node.getNext().getNext();
        node.setNext(null);
        while(slowIndex<toTestList.getSize()){
            next.setNext(node);
            slowIndex++;
            node = next;
            if (nextNext!=null){
                next = nextNext;
                nextNext = nextNext.getNext();
            }else{
                break;
            }
        }

        //此时，左链从左开始，右链从node开始进行比较
        Node leftLinkNode = toTestList.getFirstNode();
        Node rightLinkNode = node;
        while (leftLinkNode!=null&&rightLinkNode!=null){
            if (!leftLinkNode.getValue().equals(rightLinkNode.getValue())){
                //链回调
                mergeLink(toTestList.getFirstNode(),node);
                //返回结果
                return false;
            }
            leftLinkNode = leftLinkNode.getNext();
            rightLinkNode = rightLinkNode.getNext();
        }

        if (leftLinkNode == null && rightLinkNode == null){
            //链回调
            mergeLink(toTestList.getFirstNode(),node);
            //返回结果
            return true;
        }

        //链回调
        mergeLink(toTestList.getFirstNode(),node);
        //返回结果
        return false;
    }

    private static void mergeLink(Node leftNode,Node rightNode){
        while (leftNode!=null&&leftNode.getNext()!=null){
            leftNode = leftNode.getNext();
        }

        Node rightNext = rightNode.getNext();
        Node rightNextNext = rightNext.getNext();
        rightNode.setNext(null);
        while(rightNext!=null){
            rightNext.setNext(rightNode);
            rightNode = rightNext;
            rightNext = rightNextNext;
            if (rightNext!=null){
                rightNextNext = rightNext.getNext();
            }
        }


        leftNode.setNext(rightNode);
    }

}
