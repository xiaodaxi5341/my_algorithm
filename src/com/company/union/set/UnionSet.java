package com.company.union.set;

import java.util.*;

public class UnionSet<T> {

    private Map<T, UnionSetNode<T>> nodes;
    //子-》父
    private Map<UnionSetNode<T>, UnionSetNode<T>> parents;
    //子集的元素个数，仅保留父节点的
    private Map<UnionSetNode<T>, Integer> sizeMap;

    public void union(T t1, T t2) {
        UnionSetNode<T> t1HeadNode = findFather(t1);
        UnionSetNode<T> t2HeadNode = findFather(t2);

        //父节点不一样的时候才合并，否则已经属于同一集团，不再合并
        //合并时，将短的向长的合并
        if (t1HeadNode != t2HeadNode) {

            int t1Size = sizeMap.get(t1HeadNode);
            int t2Size = sizeMap.get(t2HeadNode);

            UnionSetNode<T> shortHeadNode = t1Size >= t2Size ? t2HeadNode : t1HeadNode;
            UnionSetNode<T> longHeadNode = t1Size >= t2Size ? t1HeadNode : t2HeadNode;

            parents.put(shortHeadNode, longHeadNode);
            sizeMap.put(longHeadNode, t1Size + t2Size);
            sizeMap.remove(shortHeadNode);

        }

    }

    public boolean isSameSet(T t1, T t2) {
        UnionSetNode<T> t1Node = findFather(t1);
        UnionSetNode<T> t2Node = findFather(t2);

        return t1Node == t2Node;
    }

    private UnionSetNode<T> findFather(T t1) {
        Stack<UnionSetNode<T>> path = new Stack<>();
        UnionSetNode<T> cur = nodes.get(t1);
        while (cur != parents.get(cur)) {
            path.push(cur);
            cur = parents.get(cur);
        }
        //扁平化处理，将所有集合的子节点都指向同一个父节点
        while (!path.isEmpty()) {
            parents.put(path.pop(), cur);
        }
        return cur;
    }


    public UnionSet(Collection<T> values) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (T value : values) {
            if (value != null) {
                UnionSetNode<T> node = new UnionSetNode<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }
    }

    public int getNum(){
        return sizeMap.size();
    }

    public Map<T, UnionSetNode<T>> getNodes() {
        return nodes;
    }

    public void setNodes(Map<T, UnionSetNode<T>> nodes) {
        this.nodes = nodes;
    }

    public Map<UnionSetNode<T>, UnionSetNode<T>> getParents() {
        return parents;
    }

    public void setParents(Map<UnionSetNode<T>, UnionSetNode<T>> parents) {
        this.parents = parents;
    }

    public Map<UnionSetNode<T>, Integer> getSizeMap() {
        return sizeMap;
    }

    public void setSizeMap(Map<UnionSetNode<T>, Integer> sizeMap) {
        this.sizeMap = sizeMap;
    }

}
