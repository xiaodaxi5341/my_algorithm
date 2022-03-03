package com.company.graph;

import com.company.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description 最短路径
 * @author: 34371
 * @create: 2022-03-02 22:25
 **/
public class DijkstraWithHeap<T> {

    public static void main(String[] args) {
        GraphNode<Character> a = new GraphNode<>('a');
        GraphNode<Character> b = new GraphNode<>('b');
        GraphNode<Character> c = new GraphNode<>('c');
        GraphNode<Character> d = new GraphNode<>('d');
        GraphNode<Character> e = new GraphNode<>('e');
        GraphNode<Character> f = new GraphNode<>('f');

        Edge<Character> a1 = new Edge<>(a, d, 2);
        Edge<Character> a2 = new Edge<>(a, c, 7);
        Edge<Character> a3 = new Edge<>(a, b, 1);
        Edge<Character> b1 = new Edge<>(b, c, 2);
        Edge<Character> b2 = new Edge<>(b, e, 6);
        Edge<Character> c1 = new Edge<>(c, d, 1);
        Edge<Character> c2 = new Edge<>(c, e, 2);
        Edge<Character> d1 = new Edge<>(d, e, 10);
        Edge<Character> f1 = new Edge<>(f, b, 3);

        a.addEdge(a1);
        a.addEdge(a2);
        a.addEdge(a3);
        b.addEdge(b1);
        b.addEdge(b2);
        c.addEdge(c1);
        c.addEdge(c2);
        d.addEdge(d1);
        f.addEdge(f1);

        a.addNode(b);
        a.addNode(c);
        a.addNode(d);
        b.addNode(c);
        b.addNode(e);
        c.addNode(e);
        c.addNode(d);
        d.addNode(e);
        f.addNode(b);


        DijkstraWithHeap<Character> heap = new DijkstraWithHeap<>();
        HashMap<GraphNode<Character>, Integer> result = heap.getMinDistance(a, 6);
        result.forEach((k, v) -> System.out.println(k.getValue() + ":" + v));
    }

    public HashMap<GraphNode<T>, Integer> getMinDistance(GraphNode<T> from, int size) {
        HashMap<GraphNode<T>, Integer> result = new HashMap<>();

        Heap<T> heap = new Heap<>(size);
        heap.addOrUpdateOrIgnore(from, 0);
        while (!heap.isEmpty()) {
            Record<T> pop = heap.pop();
            result.put(pop.node, pop.distance);
            List<Edge<T>> edges = pop.node.getEdges();
            if (!CollectionUtils.isEmpty(edges)) {
                for (Edge<T> edge : edges) {
                    heap.addOrUpdateOrIgnore(edge.getTo(), result.get(edge.getFrom()) + edge.getWeight());
                }
            }
        }

        return result;
    }

    static class Record<T> {
        GraphNode<T> node;
        int distance;

        public Record(GraphNode<T> node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static class Heap<T> {

        GraphNode<T>[] nodes;
        Map<GraphNode<T>, Integer> nodeIndexMap;
        Map<GraphNode<T>, Integer> distanceMap;
        int size;

        public Heap(int size) {
            nodes = new GraphNode[size];
            nodeIndexMap = new HashMap<>(size);
            distanceMap = new HashMap<>(size);
            this.size = 0;
        }

        public void addOrUpdateOrIgnore(GraphNode<T> node, int distance) {

            if (inHeap(node)) {
                if (distanceMap.get(node) > distance) {
                    //在堆中，则继续更新最小值
                    distanceMap.put(node, distance);
                    //根据距离重新调整顺序
                    heapUpdate(node);
                }
            }

            if (!isEntered(node)) {
                //未被添加过
                distanceMap.put(node, distance);
                heapInsert(node);

            }

            //否则被忽略

        }

        private void heapInsert(GraphNode<T> node) {
            nodes[size] = node;
            nodeIndexMap.put(node, size);
            size++;
            heapUpdate(node);
        }

        private void heapUpdate(GraphNode<T> node) {
            Integer index = nodeIndexMap.get(node);
            int fatherIndex = (index - 1) / 2;
            //小根堆调整，此处只可能变小
            while (distanceMap.get(node) < distanceMap.get(nodes[fatherIndex])) {
                swap(index, fatherIndex);
                index = nodeIndexMap.get(node);
                fatherIndex = (index - 1) / 2;
            }
        }

        private void swap(Integer index, int fatherIndex) {

            nodeIndexMap.put(nodes[index], fatherIndex);
            nodeIndexMap.put(nodes[fatherIndex], index);
            GraphNode<T> tmp = nodes[index];
            nodes[index] = nodes[fatherIndex];
            nodes[fatherIndex] = tmp;

        }

        public Record<T> pop() {
            Record<T> result = new Record<>(nodes[0], distanceMap.get(nodes[0]));

            //取值以后的后续操作
            swap(0, size - 1);
            nodeIndexMap.put(nodes[size - 1], -1);
            size--;

            //下沉头节点
            heapify(nodes[0], 0);

            return result;
        }

        private void heapify(GraphNode<T> node, int index) {

            int distance = distanceMap.get(node);
            int subIndexLeft = index * 2 + 1;
            int subIndexRight = index * 2 + 2;

            //左右节点都有
            while (subIndexRight < size) {
                int smallIndex = distanceMap.get(nodes[subIndexLeft]) > distanceMap.get(nodes[subIndexRight]) ?
                        subIndexRight : subIndexLeft;
                if (distance > distanceMap.get(nodes[smallIndex])) {
                    swap(index, smallIndex);
                    index = smallIndex;
                    subIndexLeft = index * 2 + 1;
                    subIndexRight = index * 2 + 2;
                } else {
                    break;
                }

            }

            while (subIndexLeft < size) {
                if (distance > distanceMap.get(nodes[subIndexLeft])) {
                    swap(index, subIndexLeft);
                    index = subIndexLeft;
                } else {
                    break;
                }
            }

        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isEntered(GraphNode<T> node) {
            return nodeIndexMap.containsKey(node);
        }

        public boolean inHeap(GraphNode<T> node) {
            return isEntered(node) && nodeIndexMap.get(node) != -1;
        }
    }

}
