package brush.questions.chapter23;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description 超级水王问题
 * 给定一个数组arr，长度为N，如果某个数出现次数大于N/2，称该数为水王数，如果arr中有水王数，打印这个数；如果没有水王数，打印没有水王数
 * 要求时间复杂度O(N)，额外空间复杂度O(1)
 * 扩展1：摩尔投票
 * 扩展2：给定一个正数K，返回所有出现次数>N/K的数
 * @author: 34371
 * @create: 2022-06-20 10:38
 **/
public class WaterKing {

    public static void waterKing(int[] arr) {
        int candidate = arr[0];
        int vote = 1;
        for (int i = 1; i < arr.length; i++) {
            if (vote == 0) {
                candidate = arr[i];
                vote++;
            } else {
                if (arr[i] == candidate) {
                    vote++;
                } else {
                    vote--;
                }
            }
        }

        if (vote == 0) {
            System.out.println("没有水王数");
            return;
        }

        vote = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == candidate) {
                vote++;
            }
        }

        if (arr.length / 2 >= vote) {
            System.out.println("没有水王数");
        } else {
            System.out.println(candidate);
        }

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 1, 2};
        waterKing(arr);
    }

}
