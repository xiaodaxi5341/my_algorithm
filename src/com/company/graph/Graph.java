package com.company.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

    private Map<T,GraphNode<T>> nodes;
    private Set<Edge<T>> edges;

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public Map<T, GraphNode<T>> getNodes() {
        return nodes;
    }

    public void setNodes(Map<T, GraphNode<T>> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge<T>> edges) {
        this.edges = edges;
    }
}
