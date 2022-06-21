package brush.questions.chapter23;

/**
 * @program: my_algorithm
 * @description 给定一个数组arr，长度为N > 1，从中间切一刀，保证左部分和右部分都有数字，一共有N-1种切法
 * 如此多的切法中，每一种都有:绝对值(左部分最大值 – 右部分最大值)，返回最大的绝对值是多少
 * @author: 34371
 * @create: 2022-06-20 09:27
 **/
public class DiffAbsMax {

    public static int diffAbsMaxBest(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            max = Math.max(arr[i], max);
        }

        return max - Math.min(arr[0],arr[arr.length-1]);
    }

    public static int diffAbsMax(int[] arr) {
        //左边最大值
        int[] leftMax = new int[arr.length];
        leftMax[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }

        int[] rightMax = new int[arr.length];
        rightMax[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }

        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, Math.abs(leftMax[i] - rightMax[i + 1]));
        }

        return max;
    }

}
