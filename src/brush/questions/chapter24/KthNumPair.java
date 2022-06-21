package brush.questions.chapter24;

import java.util.Arrays;

/**
 * @program: my_algorithm
 * @description 长度为N的数组arr，一定可以组成N^2个数字对。例如arr = [3,1,2]，数字对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)
 * 也就是任意两个数都可以，而且自己和自己也算数字对。数字对怎么排序？第一维数据从小到大；第一维数据一样的，第二维数组也从小到大
 * 所以上面的数值对排序的结果为：(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)。给定一个数组arr，和整数k，返回第k小的数值对
 * @author: 34371
 * @create: 2022-06-20 16:18
 **/
public class KthNumPair {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getKthNumPair(new int[]{1,1,1,2,2,3,3,3,3,5,5}, 45)));
    }

    public static int[] getKthNumPair(int[] arr, int k) {
        int kthIndex = (k - 1) / arr.length;
        int kthNum = getKthNum(arr, kthIndex + 1, 0, arr.length - 1);
        int lessFristNumSize = 0;
        int fristNumSize = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < kthNum) {
                lessFristNumSize++;
            }
            if (arr[i] == kthNum) {
                fristNumSize++;
            }
        }
        int rest = k - (lessFristNumSize * arr.length);
        int secondXth = (rest-1)/fristNumSize;
        int sNum = getKthNum(arr, secondXth+1, 0, arr.length - 1);
        return new int[]{kthNum, sNum};
    }

    //[L,R]闭区间
    private static int getKthNum(int[] arr, int k, int L, int R) {

        int ram = arr[(int) (Math.random() * (R - L + 1)) + L];
//        int ram = 3;

        //闭区间
        int l = L - 1;
        int r = R + 1;
        for (int i = L; i < r; ) {
            if (arr[i] < ram) {
                swap(arr, ++l, i++);
            } else if (arr[i] == ram) {
                i++;
            } else {
                swap(arr, i, --r);
            }
        }

        if (k <= l + 1) {
            return getKthNum(arr, k, L, l);
        } else if (k - 1 >= r) {
            return getKthNum(arr, k, r, R);
        } else {
            return ram;
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
