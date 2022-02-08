package com.company.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    private Map<Integer,GraphNode> nodes;
    private Set<Edge> edges;

    Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public Map<Integer, GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Integer, GraphNode> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }
}
