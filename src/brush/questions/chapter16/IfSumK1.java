package brush.questions.chapter16;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: my_algorithm
 * @description 给定一个有正、有负、有0的数组arr，
 * 给定一个整数k，
 * 返回arr的子集是否能累加出k
 * 1）正常怎么做？
 * 2）如果arr中的数值很大，但是arr的长度不大，怎么做？
 * @author: 34371
 * @create: 2022-06-10 09:51
 **/
public class IfSumK1 {

    public static void main(String[] args) {
//        System.out.println(ifSumK(new int[]{72,-42,-16,84,11,45,-26,-95}, -20));
        System.out.println(ifSumKShortLength(new int[]{72, -42, -16, 84, 11, 45, -26, -95}, -20));

//        System.out.println(getSumSet(new int[]{1,3,5},0,2));
    }

    //第二问方法
    //分治暴力
    public static boolean ifSumKShortLength(int[] arr, int k) {
        if (k == 0) {
            return true;
        }
        int max = 0;
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            max += arr[i] > 0 ? arr[i] : 0;
            min += arr[i] < 0 ? arr[i] : 0;
        }
        if (k > max || k < min) {
            return false;
        }
        if (k == max || k == min) {
            return true;
        }

        int mid = arr.length / 2;
        Set<Integer> leftSet = getSumSet(arr, 0, mid - 1);
        Set<Integer> rightSet = getSumSet(arr, mid, arr.length - 1);
        if (leftSet.contains(k) || rightSet.contains(k)) {
            return true;
        }
        for (Integer leftNum : leftSet) {
            if (rightSet.contains(k - leftNum)) {
                return true;
            }
        }
        return false;
    }

    private static Set<Integer> getSumSet(int[] arr, int left, int right) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        if (left > right) {
            return set;
        }
        myProcess(arr, left, 0, set, right);
        return set;
    }

    private static void myProcess(int[] arr, int index, int sum, Set<Integer> set, int stop) {
        if (index == stop+1){
            set.add(sum);
            return;
        }

        //要index位置
        myProcess(arr,index+1,sum+arr[index],set,stop);
        //不要i位置
        myProcess(arr,index+1,sum,set,stop);
    }

    //第一问的解法
    public static boolean ifSumK(int[] arr, int k) {
        if (k == 0) {
            return true;
        }
        int max = 0;
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            max += arr[i] > 0 ? arr[i] : 0;
            min += arr[i] < 0 ? arr[i] : 0;
        }
        if (k > max || k < min) {
            return false;
        }

        boolean[][] dp = new boolean[arr.length][max - min + 1];
        dp[0][-min] = true;
        //col 的实际值等于 arr[0]的时候     dp中  ： 实际值 = 下标 + min
        dp[0][arr[0] - min] = true;

        for (int row = 1; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
                dp[row][col] = dp[row - 1][col];
                if (col - arr[row] >= 0 && (col - arr[row] < dp[0].length)) {
                    dp[row][col] |= dp[row - 1][col - arr[row]];
                }
            }
        }
        return dp[arr.length - 1][k - min];

    }

}
