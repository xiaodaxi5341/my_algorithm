package brush.questions.chapter16;

import java.util.Arrays;

/**
 * @program: my_algorithm
 * @description 给定整数power，给定一个数组arr，给定一个数组reverse。含义如下：
 * arr的长度一定是2的power次方，reverse中的每个值一定都在0~power范围。
 * 例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}
 * 任何一个在前的数字可以和任何一个在后的数组，构成一对数。可能是升序关系、相等关系或者降序关系。
 * 比如arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。
 * 接下来根据reverse对arr进行调整：
 * reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成[3,1,4,2]，此时有3个逆序对。
 * reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，那么arr变成[1,3,2,4]，此时有1个逆序对
 * reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成[1,3,2,4]，此时有1个逆序对。
 * reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，那么arr变成[4,2,3,1]，此时有5个逆序对。
 * 所以返回[3,1,1,5]，表示每次调整之后的逆序对数量。
 * 输入数据状况：
 * power的范围[0,20]
 * arr长度范围[1,10的7次方]
 * reverse长度范围[1,10的6次方]
 * @author: 34371
 * @create: 2022-06-10 15:36
 **/
public class CountReversePair {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getReversePair(3, new int[]{9, 16, 22, 20, 14, 14, 26, 12}, new int[]{1, 2, 3})));
    }

    public static int[] getReversePair(int power, int[] arr, int[] reverse) {
        int[][] reversePair = new int[power][2];
        processArr(arr, power - 1, 0, arr.length - 1, reversePair);

        int[] ansArr = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {
            int ans = 0;
            for (int j = reverse[i] - 1; j >= 0; j--) {
                int tmp = reversePair[j][0];
                reversePair[j][0] = reversePair[j][1];
                reversePair[j][1] = tmp;
            }
            for (int j = power - 1; j >= 0; j--) {
                ans+=reversePair[j][0];
            }
            ansArr[i] = ans;
        }

        return ansArr;
    }

    private static void processArr(int[] arr, int power, int left, int right, int[][] result) {
        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        processArr(arr, power - 1, left, mid, result);
        processArr(arr, power - 1, mid + 1, right, result);

        merge(arr, left, mid, right, power, result);

    }

    private static void merge(int[] arr, int left, int mid, int right, int power, int[][] result) {
        int positive = 0;
        int negative = 0;
        for (int l = left; l <= mid; l++) {
            for (int r = mid + 1; r <= right; r++) {
                if (arr[l] > arr[r]) {
                    negative++;
                } else if(arr[l] < arr[r]){
                    positive++;
                }
            }
        }

        result[power][0] += negative;
        result[power][1] += positive;
    }

}
