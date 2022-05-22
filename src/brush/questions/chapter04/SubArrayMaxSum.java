package brush.questions.chapter04;

/**
 * @program: my_algorithm
 * @description 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * @author: 34371
 * @create: 2022-05-15 20:46
 **/
public class SubArrayMaxSum {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int result = nums[0];
        int preMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (preMax>=0){
                preMax += nums[i];

            }else{
                preMax = nums[i];
            }
            result = Math.max(preMax,result);
        }

        return result;

    }

}
