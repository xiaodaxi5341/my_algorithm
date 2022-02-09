package com.company.graph;

import com.company.util.CollectionUtils;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//P算法的最小生成树，从任一节点开始，寻找其最小边，再从最小边的另一节点开始
//重复此过程，已经添加的节点要跳过
public class Prim<T> {

    public Set<Edge<T>> minSpanningTree(Graph<T> graph) {
        if (graph == null || CollectionUtils.isEmpty(graph.getNodes())) {
            return null;
        }

        Set<GraphNode<T>> addedNodes = new HashSet<>(graph.getNodes().size());
        Set<Edge<T>> result = new HashSet<>();
        for (GraphNode<T> graphNode : graph.getNodes().values()) {
            PriorityQueue<Edge<T>> edges = new PriorityQueue<>(new EdgeComparator<>());
            edges.addAll(graphNode.getEdges());
            addedNodes.add(graphNode);

            while (!edges.isEmpty()) {
                Edge<T> poll = edges.poll();
                if (!addedNodes.contains(poll.getTo())) {
                    addedNodes.add(poll.getTo());
                    result.add(poll);
                    edges.addAll(poll.getTo().getEdges());
                }
            }
        }

        return result;
    }

}
