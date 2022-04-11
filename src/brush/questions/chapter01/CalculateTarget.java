package brush.questions.chapter01;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description 给定一个数组arr，你可以在每个数字之前决定+或者-但是必须所有数字都参与，再给定一个数target
 * 请问最后算出target的方法数
 * @author: 34371
 * @create: 2022-04-07 19:58
 **/
public class CalculateTarget {

    //1,2,3,4,5   target 3
    public static int findTargetSumWays(int[] arr, int target) {
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put(arr.length + "_" + 0, 1);
//        resultMap.put(genKey(arr.length-1,arr[arr.length-1]),1);
//        resultMap.put(genKey(arr.length-1,-arr[arr.length-1]),1);
        return process1(arr, 0, target, resultMap);
    }

    private static int process(int[] arr, int index, int surplus) {

        if (index == arr.length) {
            return surplus == 0 ? 1 : 0;
        }


        return process(arr, index + 1, surplus + arr[index]) + process(arr, index + 1, surplus - arr[index]);

    }

    private static int getVal(String key,Map<String, Integer> resultMap){
        return resultMap.containsKey(key)?resultMap.get(key):0;
    }

    private static int processDP(int[] arr, int target) {

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put(arr.length + "_" + 0, 1);

        int surplusAdd = target;
        int surplusDe = target;
        for (int i = arr.length - 1; i >= 0; i--) {
            surplusAdd = surplusAdd + arr[i];
            surplusDe = surplusDe - arr[i];
            int surplusAddAdd = surplusAdd + arr[i];
            int surplusAddDe = surplusAdd - arr[i];
            int surplusDeAdd = surplusDe + arr[i];
            int surplusDeDe = surplusDe - arr[i];
            int res = 0;
            contains(resultMap, surplusAdd, i, surplusAddAdd, surplusAddDe, res);

            res = 0;

            contains(resultMap, surplusDe, i, surplusDeAdd, surplusDeDe, res);
        }

        return resultMap.get(genKey(0, target)) == null ? 0 : resultMap.get(genKey(0, target));
    }

    private static void contains(Map<String, Integer> resultMap, int surplusDe, int i, int surplusDeAdd, int surplusDeDe, int res) {
        if (resultMap.containsKey(genKey(i + 1, surplusDeAdd))) {
            res += resultMap.get(genKey(i + 1, surplusDeAdd));
        }

        if (resultMap.containsKey(genKey(i + 1, surplusDeDe))) {
            res += resultMap.get(genKey(i + 1, surplusDeDe));
        }

        resultMap.put(genKey(i, surplusDe), res);
    }

    private static String genKey(int k1, int k2) {
        return k1 + "_" + k2;
    }

    private static int process1(int[] arr, int index, int surplus, Map<String, Integer> resultMap) {
        if (index == arr.length && !resultMap.containsKey(genKey(index, surplus))) {
            return 0;
        }

        if (resultMap.containsKey(genKey(index, surplus))) {
            return resultMap.get(genKey(index, surplus));
        }

        int result = process1(arr, index + 1, surplus + arr[index], resultMap)
                + process1(arr, index + 1, surplus - arr[index], resultMap);

        resultMap.put(genKey(index, surplus), result);
        return result;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(process(arr, 0, 3));
        System.out.println(processDP(arr,3));
//        System.out.println(findTargetSumWays(arr, 3));
    }

}
