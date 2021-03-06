package com.company.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的点
 */
public class GraphNode<T> {

    private T value;
    private int in;
    private int out;
    private List<GraphNode<T>> nexts;
    private List<Edge<T>> edges;

    GraphNode(T value){
        this.value = value;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(GraphNode<T> node){
        nexts.add(node);
        out++;
        node.in++;
    }

    public void addEdge(Edge<T> edge){
        edges.add(edge);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public List<GraphNode<T>> getNexts() {
        return nexts;
    }

    public void setNexts(List<GraphNode<T>> nexts) {
        this.nexts = nexts;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "value=" + value +
                '}';
    }
}
