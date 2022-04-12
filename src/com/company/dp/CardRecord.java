package com.company.dp;

/**
 * @program: my_algorithm
 * @description 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明·
 * 请返回最后获胜者的分数。
 * @author: 34371
 * @create: 2022-04-11 20:12
 **/
public class CardRecord {

    public static void main(String[] args) {
        int[] arr = {6,8,3,6,7};
        System.out.println(getRecordDp(arr));

    }

    public static int getRecord(int[] arr) {

        return Math.max(f1(arr, 0, arr.length - 1), g1(arr, 0, arr.length - 1));

    }

    public static int f1(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }

        int num1 = arr[l] + g1(arr, l + 1, r);
        int num2 = arr[r] + g1(arr, l, r - 1);

        return Math.max(num1, num2);
    }

    public static int g1(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int num1 = f1(arr, l + 1, r);
        int num2 = f1(arr, l, r - 1);

        return Math.min(num1, num2);
    }

    public static int getRecordDp(int[] arr) {

        int[][] f1 = new int[arr.length][arr.length];
        int[][] g1 = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            f1[i][i] = arr[i];
            g1[i][i] = 0;
        }
        for (int r = 1; r < arr.length; r++) {
            for (int l = 0,ri = r; l < arr.length - 1 && ri < arr.length; l++,ri++) {
//                int ri = r;
                int n1 = arr[ri] + g1[l][ri - 1];
                int n2 = arr[l] + g1[l + 1][ri];
                f1[l][ri] = Math.max(n1, n2);
                int n3 = f1[l][ri - 1];
                int n4 = f1[l+1][ri];
                g1[l][ri] = Math.min(n3, n4);
            }
        }

        return Math.max(f1[0][arr.length - 1], g1[0][arr.length - 1]);

    }

}
