package brush.questions.chapter24;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description 给定一个正数数组arr，长度一定大于6（>=7），一定要选3个数字做分割点，从而分出4个部分，并且每部分都有数
 * 分割点的数字直接删除，不属于任何4个部分中的任何一个。返回有没有可能分出的4个部分累加和一样大
 * 如：{3,2,3,7,4,4,3,1,1,6,7,1,5,2}。可以分成{3,2,3}、{4,4}、{1,1,6}、{1,5,2}。分割点是不算的！
 * @author: 34371
 * @create: 2022-06-20 15:38
 **/
public class SumEqual {

    public static void main(String[] args) {
        System.out.println(sumEqual(new int[]{
                4, 6, 9, 6, 3, 8, 6, 4, 1, 5, 10, 2
        }));
    }


    public static boolean sumEqual(int[] arr) {
        int[] preSum = new int[arr.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 0; i < arr.length; i++) {
            preSum[i + 1] = arr[i] + preSum[i];
            map.put(preSum[i + 1], i + 1);
        }

        for (int i = 1; i <= arr.length - 6; i++) {
            //第一刀切的位置,最多切到arr.length-6的位置，否则无法分出四份
//            int part1 = preSum[i];
            //推断第二刀切的位置
            int part2 = preSum[i + 1] + preSum[i];
            if (!map.containsKey(part2) || map.get(part2) - 1 > arr.length - 4) {
                continue;
            }

            //第三刀
            int part3 = part2 + arr[map.get(part2)] + preSum[i];
            if (!map.containsKey(part3) || map.get(part3) - 1 > arr.length - 2) {
                continue;
            }
            //看剩下的部分能不能凑成第四部分
            if (preSum[i] == preSum[arr.length] - preSum[map.get(part3) + 1]) {
                return true;
            }
        }

        return false;
    }

}
