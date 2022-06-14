package brush.questions.chapter16;

import java.util.Arrays;

/**
 * @program: my_algorithm
 * @description 给定一个正数数组arr，
 * 返回arr的子集不能累加出的最小正数
 * 1）正常怎么做？
 * 2）如果arr中肯定有1这个值，怎么做？
 * @author: 34371
 * @create: 2022-06-10 14:29
 **/
public class UnSumNumber {

    public static int cal(int[] arr){
        if (arr == null || arr.length == 0){
            return 1;
        }

        Arrays.sort(arr);
        if (arr[0]!=1){
            return 1;
        }

        int sum = 1;
        int i = 1;
        while (arr[i]<=sum+1&&i<arr.length){
            sum+=arr[i++];
        }
        return sum+1;

    }

}
