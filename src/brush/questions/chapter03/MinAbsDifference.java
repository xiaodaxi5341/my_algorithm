package brush.questions.chapter03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-05-12 22:17
 **/
public class MinAbsDifference {

    public  int minAbsDifference(int[] nums, int goal) {
        Map<String,Integer> map = new HashMap<>();
        Arrays.sort(nums);
        return Math.abs(processMemory(nums, 0, 0, goal,map) - goal);
    }

    private  int processMemory(int[] nums, int i, int curSum, int goal, Map<String, Integer> map) {
        String key = generateKey(i, curSum);

        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (i == nums.length||(nums[i]>0&&curSum - goal>=0)) {
            map.put(key, curSum);
            return curSum;
        }

        //要当前值
        int p1 = processMemory(nums, i + 1, curSum + nums[i], goal, map);
        //不要当前
        int p2 = processMemory(nums, i + 1, curSum, goal, map);

        int result = Math.abs(p1 - goal) > Math.abs(p2 - goal) ? p2 : p1;
        map.put(key, result);
        return result;

    }

    private  String generateKey(int i, int curSum) {
        return i + "_" + curSum;
    }
    
}
