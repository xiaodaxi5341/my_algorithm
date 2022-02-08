package com.company.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的点
 */
public class GraphNode {

    private int value;
    private int in;
    private int out;
    private List<GraphNode> nexts;
    private List<Edge> edges;

    GraphNode(int value){
        this.value = value;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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

    public List<GraphNode> getNexts() {
        return nexts;
    }

    public void setNexts(List<GraphNode> nexts) {
        this.nexts = nexts;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
