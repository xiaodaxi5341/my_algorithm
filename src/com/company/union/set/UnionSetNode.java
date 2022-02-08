package com.company.union.set;

/**
 * 并查集节点
 */
public class UnionSetNode<T> {

    private T value;

    public UnionSetNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
