package com.company.graph;

import com.company.union.set.UnionSet;

import java.util.*;

/**
 * 求图的最小生成树
 * K算法：
 * 从权重最小的边开始遍历，如果边的节点不会产生环则保留，有环结构则不保留
 * <p>
 * 不使用set而使用并查集的原因是因为：如 A-B-C的图结构，用set判断是否有环结构时，B会重复出现，一定会判断出错
 */
public class Kruskal<T> {

    public static void main(String[] args) {
        GraphNode<String>  A = new GraphNode<>("A");
        GraphNode<String>  B = new GraphNode<>("B");
        GraphNode<String>  C = new GraphNode<>("C");
        GraphNode<String>  D = new GraphNode<>("D");
        GraphNode<String>  E = new GraphNode<>("E");

        //无向边
        Edge<String> e1 = new Edge<>(A,B,7);
        Edge<String> e2 = new Edge<>(A,D,100);
        Edge<String> e3 = new Edge<>(A,C,2);
        Edge<String> e4 = new Edge<>(B,E,100000);
        Edge<String> e5 = new Edge<>(B,D,1000);
        Edge<String> e6 = new Edge<>(C,D,4);

        Edge<String> e1_ = new Edge<>(B,A,7);
        Edge<String> e2_ = new Edge<>(D,A,100);
        Edge<String> e3_ = new Edge<>(C,A,2);
        Edge<String> e4_ = new Edge<>(E,B,100000);
        Edge<String> e5_ = new Edge<>(D,B,1000);
        Edge<String> e6_ = new Edge<>(D,C,4);

        Set<Edge<String>> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e1_);
        edges.add(e2);
        edges.add(e2_);
        edges.add(e3);
        edges.add(e3_);
        edges.add(e4);
        edges.add(e4_);
        edges.add(e5);
        edges.add(e5_);
        edges.add(e6);
        edges.add(e6_);

        Map<String,GraphNode<String>> map = new HashMap<>();
        map.put("A",A);
        map.put("B",B);
        map.put("C",C);
        map.put("D",D);
        map.put("E",E);

        A.addNode(B);
        A.addNode(C);
        A.addNode(D);
        A.addEdge(e1);
        A.addEdge(e3);
        A.addEdge(e2);

        B.addNode(A);
        B.addNode(E);
        B.addNode(D);
        B.addEdge(e1_);
        B.addEdge(e5);
        B.addEdge(e4);

        C.addNode(A);
        C.addNode(D);
        C.addEdge(e3_);
        C.addEdge(e6);

        D.addNode(A);
        D.addNode(B);
        D.addNode(C);
        D.addEdge(e2_);
        D.addEdge(e5_);
        D.addEdge(e6_);

        E.addNode(B);
        E.addEdge(e4_);

        Graph<String> graph = new Graph<>();
        graph.setEdges(edges);
        graph.setNodes(map);

        Kruskal<String> k = new Kruskal<>();
        Set<Edge<String>> min = k.minSpanningTree(graph);
        min.forEach(System.out::println);

        System.out.println("----------------------------------------------------------------");

        Prim<String> p = new Prim<>();
        Set<Edge<String>> pMin = p.minSpanningTree(graph);
        pMin.forEach(System.out::println);

        System.out.println("----------------------------最短距离-------------------------------");
        Dijkstra<String> d = new Dijkstra<>();
        Map<GraphNode<String>, Integer> minDistanceMap = d.minDistance(A);
        minDistanceMap.forEach((key,value)->System.out.println(key+"："+value));

    }

    public Set<Edge<T>> minSpanningTree(Graph<T> graph) {
        if (graph == null) {
            return null;
        }

        Set<Edge<T>> edges = graph.getEdges();
        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>(new EdgeComparator<>());
        priorityQueue.addAll(edges);

        UnionSet<GraphNode<T>> unionSet = new UnionSet<>(graph.getNodes().values());
        Set<Edge<T>> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge<T> poll = priorityQueue.poll();
            if (!unionSet.isSameSet(poll.getFrom(), poll.getTo())) {
                result.add(poll);
                unionSet.union(poll.getFrom(), poll.getTo());
            }
        }

        return result;
    }


}

