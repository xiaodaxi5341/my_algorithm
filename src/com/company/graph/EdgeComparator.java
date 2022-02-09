package com.company.graph;

import java.util.Comparator;

public class EdgeComparator<T> implements Comparator<Edge<T>> {

    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.getWeight() - o2.getWeight();
    }

}
