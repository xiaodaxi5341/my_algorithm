package com.company.graph;

import com.company.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;

//拓扑排序，有向无环图结构的拓扑排序
public class TopologicalSort {

    public static <T> void topologicalProcess(Graph<T> graph, Consumer<GraphNode> exec){
        Map<T, GraphNode> nodes = graph.getNodes();
        if (CollectionUtils.isEmpty(nodes)){
            return;
        }

        Map<GraphNode,Integer> inNumMap = new HashMap<>(nodes.size());
        Queue<GraphNode> zeroInQueue = new LinkedList<>();

        //找出入度为0的节点
        for (GraphNode node : nodes.values()){
            inNumMap.put(node,node.getIn());
            if (node.getIn() == 0){
                zeroInQueue.add(node);
            }
        }

        while (!zeroInQueue.isEmpty()){
            GraphNode cur = zeroInQueue.poll();
            exec.accept(cur);
            List<GraphNode> nexts = cur.getNexts();
            if (!CollectionUtils.isEmpty(nexts)){
                for (GraphNode graphNode : nexts){
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
