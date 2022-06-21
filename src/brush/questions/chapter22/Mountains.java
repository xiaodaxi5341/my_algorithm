package brush.questions.chapter22;

import java.util.Stack;

/**
 * @program: my_algorithm
 * @description 一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度
 * 比如， {3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山
 * 山峰A和山峰B能够相互看见的条件为:
 * 1.如果A和B是同一座山，认为不能相互看见
 * 2.如果A和B是不同的山，并且在环中相邻，认为可以相互看见
 * 3.如果A和B是不同的山，并且在环中不相邻，假设两座山高度的最小值为min
 * 1)如果A通过顺时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
 * 2)如果A通过逆时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
 * 两个方向只要有一个能看见，就算A和B可以相互看见
 * 给定一个不含有负数且没有重复值的数组 arr，请返回有多少对山峰能够相互看见
 * 进阶，给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见
 * @author: 34371
 * @create: 2022-06-19 18:17
 **/

//无重复值的计算公式：(n-2) * 2+1  待验证
public class Mountains {

    public static void main(String[] args) {
        System.out.println(canLookOther(new int[]{
                6, 3, 2, 5, 5, 6, 1, 4, 3, 3, 2
        }));
    }

    //有重复值的计算
    public static int canLookOther(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        if (arr.length == 2) {
            return 1;
        }

        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[maxIndex] < arr[i]) {
                maxIndex = i;
            }
        }

        Stack<Info> stack = new Stack<>();
        stack.push(new Info(arr[maxIndex], 1));
        int cur = maxIndex;
        int ans = 0;
        //一圈没有转完
        while (getNextIndex(cur, arr.length) != maxIndex) {
            int nextIndex = getNextIndex(cur, arr.length);
            Info top = stack.peek();
            if (arr[nextIndex] < top.val) {
                stack.push(new Info(arr[nextIndex], 1));
            } else if (arr[nextIndex] == top.val) {
                top.counts++;
            } else {
                //弹出开始计算

                while (stack.peek().val < arr[nextIndex]) {
                    top = stack.pop();
                    int res1 = top.counts * 2;
                    int res2 = getCN2(top.counts);
                    ans += (res1 + res2);
                }

                if (stack.isEmpty() || stack.peek().val > arr[nextIndex]) {
                    stack.push(new Info(arr[nextIndex], 1));
                } else {
                    stack.peek().counts++;
                }

            }
            cur = nextIndex;
        }

        while (stack.size() > 2) {
            Info top = stack.pop();
            ans += getCN2(top.counts);
            ans += 2 * top.counts;
        }

        while (!stack.isEmpty()) {
            //剩下最后两层了
            Info top = stack.pop();
            ans += getCN2(top.counts);
            if (!stack.isEmpty()) {
                ans += stack.peek().counts > 1 ? 2 : 1 * top.counts;
            }
        }
        return ans;

    }

    static int getCN2(int n) {
        return n < 2 ? 0 : n * (n - 1) / 2;
    }

    static int getNextIndex(int index, int length) {
        return index == length - 1 ? 0 : index + 1;
    }

    static class Info implements Comparable<Info> {
        int val;
        int counts;

        public Info(int val, int counts) {
            this.val = val;
            this.counts = counts;
        }

        @Override
        public int compareTo(Info o) {
            return this.val - o.val;
        }
    }

}
