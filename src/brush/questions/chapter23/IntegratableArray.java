package brush.questions.chapter23;
/**
 * @program: my_algorithm
 * @description 定义什么是可整合数组：一个数组排完序之后，除了最左侧的数外，有arr[i] = arr[i-1]+1
 * 则称这个数组为可整合数组比如{5,1,2,4,3}、{6,2,3,1,5,4}都是可整合数组，返回arr中最长可整合子数组的长度
 * <p>
 * 对于给定定义的题目，可以看看是否可以利用其性质简化其定义
 * @author: 34371
 * @create: 2022-06-20 09:36
 **/
public class IntegratableArray {

    /**
     * 可整合数组可转化为新的定义：
     * 1，数组长度 = 最大值 - 最小值 + 1
     * 2，无重复数
     *
     * @param arr
     * @return
     */
    public static int maxLength(int[] arr) {
        //基本需要练习。。。比如：这个题，查找所有子数组
        return 0;
    }

    public static void main(String[] args) {
        maxLength(new int[]{5, 5, 3, 2, 6, 4, 3});
    }

}
