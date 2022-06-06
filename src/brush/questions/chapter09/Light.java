package brush.questions.chapter09;

/**
 * @program: my_algorithm
 * @description 给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态
 * 问题一：如果N栈灯排成一条直线,请问最少按下多少次开关？
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 * <p>
 * 问题二：如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 * @author: 34371
 * @create: 2022-05-24 20:55
 **/
public class Light {

    public static void main(String[] args) {
        System.out.println(lightAllOn(new int[]{0, 1, 1}));
    }

    public static int lightAllOn(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        if (arr.length == 1) {
            return arr[0] == 0 ? 1 : 0;
        }

        if (arr.length == 2) {
            if (arr[0] != arr[1]) {
                return Integer.MAX_VALUE;
            } else {
                return arr[0] == 0 ? 1 : 0;
            }
        }

        int r1 = process(arr, 1, arr[0], arr[1]);
        int r2 = process(arr, 1, arr[0] ^ 1, arr[1] ^ 1);

        if (r2 != Integer.MAX_VALUE){
            r2+=1;
        }

        return Math.min(r1,r2);
    }

    private static int process(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) {
            if (preStatus != curStatus) {//这种情况无论如何都改不了结果了
                return Integer.MAX_VALUE;
            } else {//按一下或者不按
                return preStatus == 1 ? 0 : 1;
            }
        }

        int result = Integer.MAX_VALUE;
        if ((preStatus ^ 1) == 1) {//如果上个灯是暗的，必须按
            int r = process(arr, nextIndex + 1, curStatus ^ 1, arr[nextIndex] ^ 1);
            if (r != Integer.MAX_VALUE) {
                result = Math.min(r + 1, result);
            }
        } else {//上个灯是亮的，不可以按
            int r = process(arr, nextIndex + 1, curStatus, arr[nextIndex]);
            if (r != Integer.MAX_VALUE) {
                result = Math.min(r, result);
            }
        }

        return result;
    }

}
