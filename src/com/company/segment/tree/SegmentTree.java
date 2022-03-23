package com.company.segment.tree;

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

    SegmentTree(int[] origin) {
        length = origin.length + 1;
        arr = new int[length];
        System.arraycopy(origin, 0, arr, 1, length - 1);
        sums = new int[length << 2];
        lazy = new int[length << 2];
    }

    //将L...R范围的数加上num
    public void add(int L,int R,int num){
        add(L, R, num,1,length,1);
    }

    private void add(int L, int R, int num, int l, int r, int ci) {
        if (L<=l&&R>=r){
            sums[ci] += (R-L+1)*num;
            lazy[ci] += num;
            return;
        }

        //超出范围后，如果当前范围有值需要先下发任务
        int mid = l+(r-l)/2;
        pushDown(l,r,ci);
        add(L, R, num,l,mid,ci<<1);
        add(L, R, num,mid+1,r,ci<<1|1);
        pushUp(ci);
    }

    private void pushDown(int l, int r, int ci) {
        if (l>r){
            return;
        }
        if (lazy[ci]!=0){
            int mid = (r - l) / 2 + l;
            int left = ci<<1;
            lazy[left]+=lazy[ci];
            sums[left]+=(mid-l+1)*lazy[left];
            int right = left|1;
            lazy[right]+=lazy[ci];
            sums[right]+=(l-mid)*lazy[right];
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

}
