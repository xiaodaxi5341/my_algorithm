package brush.questions.chapter14;

import java.util.TreeSet;

/**
 * @program: my_algorithm
 * @description arr中求子数组的累加和是 <=K的并且是最大的，返回这个最大的累加和
 * @author: 34371
 * @create: 2022-06-08 10:19
 **/
public class MaxSubArraySum {

    public static int getMaxSubArraySum(int[] arr, int K) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            //获得i位置的总和
            sum += arr[i];
            //要获得在该总和下最接近>=sum-K的前缀和，这样就能获取到子数组最接近<=K的
            if (treeSet.ceiling(sum - K) != null) {
                max = Math.max(sum - treeSet.ceiling(sum - K), max);
            }
            treeSet.add(sum);
        }
        return max;

    }

}
