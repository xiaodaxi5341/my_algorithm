package com.company.ac.automaton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-03-30 22:30
 **/
public class ACAutomaton {

    class Node {
        String end;
        boolean endUse;
        Node[] next;
        Node fail;

        Node() {
            end = null;
            endUse = false;
            next = new Node[26];
            fail = null;
        }
    }

    Node root;

    ACAutomaton() {
        root = new Node();
    }

    public void add(String text) {
        if (text == null || "".equals(text.trim())) {
            return;
        }

        char[] chars = text.toCharArray();
        Node cur = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (cur.next[index] == null) {
                Node newNode = new Node();
                cur.next[index] = newNode;
            }
            cur = cur.next[index];
        }

        cur.end = text;
    }

    //构建fail指针
    public void build() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 26; i++) {
                if (cur.next[i] != null) {
                    cur.next[i].fail = root;
                    Node parentFail = cur.fail;
                    while (parentFail != null) {
                        if (parentFail.next[i] != null) {
                            cur.next[i].fail = parentFail.next[i];
                            break;
                        }
                        parentFail = parentFail.fail;
                    }
                    queue.add(cur.next[i]);
                }
            }
        }
    }

    //content中包含了树中的词
    public List<String> containWords(String content) {
        List<String> result = new ArrayList<>();

        char[] str = content.toCharArray();
        Node cur = root;
        Node follow = null;
        for (int i = 0; i < str.length; i++) {
            int index = str[i] - 'a';
            while (cur.next[index] == null && cur.fail!=null){
                cur = cur.fail;
            }

            cur = cur.next[index] == null ? root : cur.next[index];
            follow = cur;
            while (follow != root) {
                if (follow.endUse) {
                    break;
                }
                // 不同的需求，在这一段之间修改
                if (follow.end != null) {
                    result.add(follow.end);
                    follow.endUse = true;
                }
                // 不同的需求，在这一段之间修改
                follow = follow.fail;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ACAutomaton ac = new ACAutomaton();
        ac.add("dhe");
        ac.add("he");
        ac.add("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("dhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }

}
