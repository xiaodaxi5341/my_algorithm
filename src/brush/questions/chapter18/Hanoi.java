package brush.questions.chapter18;

/**
 * @program: my_algorithm
 * @description 给定一个数组arr，长度为N，arr中的值只有1，2，3三种
 * arr[i] == 1，代表汉诺塔问题中，从上往下第i个圆盘目前在左
 * arr[i] == 2，代表汉诺塔问题中，从上往下第i个圆盘目前在中
 * arr[i] == 3，代表汉诺塔问题中，从上往下第i个圆盘目前在右
 * 那么arr整体就代表汉诺塔游戏过程中的一个状况，如果这个状况不是汉诺塔最优解运动过程中的状况，返回-1
 * 如果这个状况是汉诺塔最优解运动过程中的状态，返回它是第几个状态
 * @author: 34371
 * @create: 2022-06-15 10:00
 **/
public class Hanoi {

    public static int getStep(int[] arr) {
        return myProcess(arr, arr.length - 1, 1, 3, 2);
    }

    private static int myProcess(int[] arr, int index, int from, int to, int other) {
        if (index < 0) {
            return 0;
        }

        if (arr[index] == other) {
            return -1;
        }

        //第i层已经到达了目的地
        if (arr[index] == to) {
            int r1 = (1 << index) - 1;
            int r2 = 1;
            int r3 = myProcess(arr, index - 1, other, to, from);
            if (r3 == -1) {
                return -1;
            } else {
                return r1 + r2 + r3;
            }
        } else {
            return myProcess(arr, index - 1, from, to, other);
        }
    }

}
