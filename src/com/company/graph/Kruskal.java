package com.company.graph;

import com.company.union.set.UnionSet;
import com.company.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * K算法：从一个节点出发，依次寻找最短的边
 */
public class Kruskal {

    public Set<Edge> minSpanningTree(List<GraphNode> nodes) {
        if (CollectionUtils.isEmpty(nodes)){
            return null;
        }

        UnionSet<GraphNode> unionSet = new UnionSet<>(nodes);
        for (GraphNode node : nodes){
            List<Edge> edges = node.getEdges();
        }

        return null;
    }

}
