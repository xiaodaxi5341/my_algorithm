package com.company.graph;

/**
 * 边
 */
public class Edge<T> {

    private GraphNode<T> from;
    private GraphNode<T> to;
    private int weight;

    public Edge(GraphNode<T>  from, GraphNode<T>  to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public GraphNode<T> getFrom() {
        return from;
    }

    public void setFrom(GraphNode<T> from) {
        this.from = from;
    }

    public GraphNode<T> getTo() {
        return to;
    }

    public void setTo(GraphNode<T> to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
