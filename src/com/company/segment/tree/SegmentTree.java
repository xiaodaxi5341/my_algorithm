package com.company.segment.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: my_algorithm
 * @description 线段树
 * 给定一个数组arr，用户希望你实现如下三个方法
 * 1）void add(int L, int R, int V) :  让数组arr[L…R]上每个数都加上V
 * 2）void update(int L, int R, int V) :  让数组arr[L…R]上每个数都变成V
 * 3）int sum(int L, int R) :让返回arr[L…R]这个范围整体的累加和
 * 怎么让这三个方法，时间复杂度都是O(logN)
 * @author: 34371
 * @create: 2022-03-22 21:07
 **/
public class SegmentTree {

    int length;
    int[] arr;
    int[] sums;
    int[] lazy;
    int[] change;
    boolean[] update;

    SegmentTree(int[] origin) {
        length = origin.length + 1;
        arr = new int[length];
        System.arraycopy(origin, 0, arr, 1, length - 1);
        sums = new int[length << 2];
        lazy = new int[length << 2];
        change = new int[length << 2];
        update = new boolean[length << 2];
    }

    //将L...R范围的数加上num
    public void add(int L, int R, int num) {
        add(L, R, num, 1, length, 1);
    }

    public void update(int L, int R, int C, int l, int r, int ci) {
        if (l>r){
            return;
        }
        if (L <= l && r <= R) {
            update[ci] = true;
            change[ci] = C;
            sums[ci] = (r - l + 1) * C;
            lazy[ci] = 0;
            return;
        }

        //无法懒住，下发
        int mid = l + (r - l) / 2;
        pushDown(l, r, ci);
        if (L<=mid){
            update(L, R, C, l, mid, ci << 1);
        }
        if (mid+1<=R){
            update(L, R, C, mid + 1, r, ci << 1 | 1);
        }
        pushUp(ci);
    }

    public long query(int L, int R, int l, int r, int ci) {
        if (L <= l && R >= r) {
            return sums[ci];
        }

        int mid = l + (r - l) / 2;
        pushDown(l, r, ci);
        long result = 0;
        if (L <= mid) {
            result += query(L, R, l, mid, ci << 1);
        }
        if (mid + 1 <= R) {
            result += query(L, R, mid + 1, r, ci << 1 | 1);
        }
        return result;
    }

    private void add(int L, int R, int num, int l, int r, int ci) {
        if (l>r){
            return;
        }
        if (L <= l && R >= r) {
            sums[ci] += (r - l + 1) * num;
            lazy[ci] += num;
            return;
        }

        //超出范围后，如果当前范围有值需要先下发任务
        int mid = l + (r - l) / 2;
        pushDown(l, r, ci);
        if (L<=mid){
            add(L, R, num, l, mid, ci << 1);
        }
        if (R>=mid+1){
            add(L, R, num, mid + 1, r, ci << 1 | 1);
        }
        pushUp(ci);
    }

    private void pushDown(int l, int r, int ci) {
        if (l > r) {
            return;
        }
        int mid = (r - l) / 2 + l;
        int left = ci << 1;
        if (update[ci]) {
            update[left] = true;
            update[left | 1] = true;
            change[left] = change[ci];
            change[left | 1] = change[ci];
            sums[left] = change[ci] * (mid - l + 1);
            sums[left | 1] = change[ci] * (r - mid);
            lazy[left] = 0;
            lazy[left | 1] = 0;
            update[ci] = false;
        }
        if (lazy[ci] != 0) {
            lazy[left] += lazy[ci];
            sums[left] += (mid - l + 1) * lazy[ci];
            int right = left | 1;
            lazy[right] += lazy[ci];
            sums[right] += (r - mid) * lazy[ci];
            lazy[ci] = 0;
        }
    }

    public void build(int L, int R, int ci) {
        if (L == R) {
            sums[ci] = arr[L];
            return;
        }
        int mid = (R - L) / 2 + L;
        build(L, mid, ci << 1);
        build(mid + 1, R, ci << 1 | 1);
        pushUp(ci);
    }

    private void pushUp(int ci) {
        sums[ci] = sums[ci << 1] + sums[ci << 1 | 1];
    }

    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 1000;
        int addOrUpdateTimes = 500;
        int queryTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            System.out.println(Arrays.toString(origin));
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
//                System.out.print("L:"+L+"\t");
                int R = Math.max(num1, num2);
//                System.out.print("R:"+R+"\t");
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
//                System.out.print("C:"+C+"\t");
                System.out.println();
                if (Math.random() < 0.5) {
//                    System.out.println("add\t");
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
//                    System.out.println("update\t");
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
//                System.out.println(L+" - "+R);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = { 3, 127, -201, 480 };
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
//        seg.add(3, 4, 515, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.add(1, 4, -124, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(2, 3, S, N, root);

        Right right = new Right(origin);
//        right.add(3,4,515);
        right.add(1,4,-124);
//        right.update(L,R,C);
        long sum2 = right.query(2,3);
        System.out.println(sum);
        System.out.println(sum2);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

}
