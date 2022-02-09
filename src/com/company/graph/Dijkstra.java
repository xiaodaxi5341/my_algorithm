package com.company.graph;

import com.company.util.CollectionUtils;

import java.util.*;

//最短路径问题：某个节点到其他节点的最短距离
public class Dijkstra<T> {

    /**
     * 通过Dijkstra算法计算最短距离
     *
     * @param from 起始节点
     * @return key：目的节点  value：最短距离
     */
    public Map<GraphNode<T>, Integer> minDistance(GraphNode<T> from) {
        if (from == null) {
            return null;
        }
        Map<GraphNode<T>, Integer> minDistanceMap = new HashMap<>();
        //锁定的节点
        Set<GraphNode<T>> lockNodes = new HashSet<>();
        minDistanceMap.put(from, 0);

        //在未锁定的节点中，选择距离最小的节点
        GraphNode<T> minNode = selectMinDistanceAndUnlockNode(minDistanceMap,lockNodes);

        //以当前节点，开始更新其他节点与该节点的距离，当新的值大于已有的值时才去更新最短距离
        //当该节点的边都遍历完后，将该节点锁定
        while (minNode!=null) {
            lockNodes.add(minNode);
            List<Edge<T>> edges = minNode.getEdges();
            if (!CollectionUtils.isEmpty(edges)) {
                for (Edge<T> edge : edges) {
                    if (edge != null && !lockNodes.contains(edge.getTo())) {
                        if (minDistanceMap.containsKey(edge.getTo())) {
                            minDistanceMap.put(edge.getTo(), Math.min(
                                    minDistanceMap.get(edge.getTo()),
                                    minDistanceMap.get(minNode) + edge.getWeight()));
                        } else {
                            minDistanceMap.put(edge.getTo(), minDistanceMap.get(minNode)+edge.getWeight());
                        }
                    }
                }
            }
            minNode = selectMinDistanceAndUnlockNode(minDistanceMap,lockNodes);
        }

        return minDistanceMap;
    }

    private GraphNode<T> selectMinDistanceAndUnlockNode(Map<GraphNode<T>, Integer> minDistanceMap, Set<GraphNode<T>> lockNodes) {

        if (CollectionUtils.isEmpty(minDistanceMap)){
            return null;
        }

        int minDistance = Integer.MAX_VALUE;
        GraphNode<T> minNode = null;
        for (Map.Entry<GraphNode<T>,Integer> entry : minDistanceMap.entrySet()){
            GraphNode<T> node = entry.getKey();
            Integer distance = entry.getValue();
            if (!lockNodes.contains(node)&&distance <= minDistance){
                minDistance = distance;
                minNode = node;
            }
        }

        return minNode;

    }

}
