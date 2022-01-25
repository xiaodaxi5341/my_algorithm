package com.company.link.base;

public class MyLinkedList<T> {

    private Node<T> head;
//    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head = new Node<>(null);
//        tail = new Node<>(null);
//        head.setNext(tail);
        size=0;
    }

    public void insert(T value) {
        Node<T> addNode = new Node<>(value);
        Node node = head;
//        while (node.getNext() != tail) {
//            node = node.getNext();
//        }

        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(addNode);
//        addNode.setNext(tail);
        size++;
    }

    public Node<T> getFirstNode(){
        if (size == 0){
            return null;
        }

        return head.getNext();
    }

    public Node<T> getNode(T value,int index){
        if (index<0){
            throw new RuntimeException("不合格参数");
        }
        int i = 0;
        Node node = head;
//        while (node.getNext()!=tail){
//            node = node.getNext();
//            if (node.getValue() == value){
//                if (i == index){
//                    return node;
//                }
//                i++;
//            }
//        }

        while (node.getNext()!=null){
            node = node.getNext();
            if (node.getValue() == value){
                if (i == index){
                    return node;
                }
                i++;
            }
        }

        return null;
    }

    public int getSize() {
        return size;
    }

}
