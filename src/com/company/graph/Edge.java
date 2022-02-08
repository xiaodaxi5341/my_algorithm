package com.company.graph;

/**
 * 边
 */
public class Edge {

    private GraphNode from;
    private GraphNode to;
    private int weight;

    public Edge(GraphNode from, GraphNode to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public GraphNode getFrom() {
        return from;
    }

    public void setFrom(GraphNode from) {
        this.from = from;
    }

    public GraphNode getTo() {
        return to;
    }

    public void setTo(GraphNode to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
