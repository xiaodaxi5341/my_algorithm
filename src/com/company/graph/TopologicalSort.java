package com.company.graph;

import com.company.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;

//拓扑排序，有向无环图结构的拓扑排序
//例如可以计算包的加载顺序
public class TopologicalSort {

    public static <T> void topologicalProcess(Graph<T> graph, Consumer<GraphNode<T>> exec){
        Map<T, GraphNode<T>> nodes = graph.getNodes();
        if (CollectionUtils.isEmpty(nodes)){
            return;
        }

        Map<GraphNode<T>,Integer> inNumMap = new HashMap<>(nodes.size());
        Queue<GraphNode<T>> zeroInQueue = new LinkedList<>();

        //找出入度为0的节点
        for (GraphNode<T> node : nodes.values()){
            inNumMap.put(node,node.getIn());
            if (node.getIn() == 0){
                zeroInQueue.add(node);
            }
        }

        while (!zeroInQueue.isEmpty()){
            GraphNode<T> cur = zeroInQueue.poll();
            exec.accept(cur);
            List<GraphNode<T>> nexts = cur.getNexts();
            if (!CollectionUtils.isEmpty(nexts)){
                for (GraphNode<T> graphNode : nexts){
                    int currentNodeInNum = inNumMap.get(graphNode);
                    if (currentNodeInNum-1 == 0){
                        zeroInQueue.add(graphNode);
                    }
                    inNumMap.put(graphNode,currentNodeInNum-1);
                }
            }
        }

    }

}
