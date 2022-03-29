package com.company.index.tree;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-03-29 11:59
 **/
public class IndexTree {

    int[] help;

    IndexTree(int size) {
        help = new int[size + 1];
    }

    public void add(int index, int num) {
        while (index < help.length) {
            help[index] += num;
            index += (index & (-index));
        }
    }

    public int sum(int index) {
        int res = 0;
        while (index > 0) {
            res += help[index];
            index -= (index & (-index));
        }
        return res;
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }

}
