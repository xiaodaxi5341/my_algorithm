package com.company.graph;

import com.company.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;

public class GraphTraverse {

    /**********************  深度优先遍历  ************************/
    public static void deepFirstTraversal(GraphNode node, Consumer<GraphNode> exec) {
        if (node == null) {
            return;
        }

        Set<GraphNode> nodeSet = new HashSet<>();
        Stack<GraphNode> stack = new Stack<>();
        nodeSet.add(node);
        stack.add(node);
        exec.accept(node);
        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            List<GraphNode> nexts = cur.getNexts();
            if (!CollectionUtils.isEmpty(nexts)) {
                for (GraphNode next : nexts) {
                    if (!nodeSet.contains(next)) {
                        stack.add(cur);
                        stack.add(next);
                        nodeSet.add(next);
                        exec.accept(next);
                        break;
                    }

                }
            }
        }

    }

    /**********************  宽度优先遍历  ************************/
    public static void breadthFirstTraversal(GraphNode node) {
        breadthFirstTraversal(node, System.out::println);
    }

    //给定图的一个节点，进行宽度优先遍历
    public static void breadthFirstTraversal(GraphNode node,
                                             Consumer<GraphNode> exec) {
        if (node == null) {
            return;
        }
        Set<GraphNode> nodeSet = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        nodeSet.add(node);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            //根据传进来的方法进行调用
            exec.accept(cur);
            List<GraphNode> nexts = cur.getNexts();
            if (!CollectionUtils.isEmpty(nexts)) {
                for (GraphNode next : nexts) {
                    if (!nodeSet.contains(next)) {
                        queue.add(next);
                        nodeSet.add(next);
                    }
                }
            }
        }
    }

}
