package brush.questions.chapter09;

/**
 * @program: my_algorithm
 * @description 定义何为step sum？比如680，680 + 68 + 6 = 754，680的step sum叫754。给定一个正数num，判断它是不是某个数的step sum
 * @author: 34371
 * @create: 2022-05-29 16:39
 **/
public class StepNum {

    public static void main(String[] args) {
        System.out.println(myIsStepNum(0));
    }

    public static boolean myIsStepNum(int num) {
        int l = 0;
        int r = num;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midNum = myGetStepNum(mid);
            if (midNum == num) {
                return true;
            }

            if (midNum > num) {
                r = mid - 1;
            } else if (midNum < num) {
                l = mid + 1;
            }
        }

        return false;

    }


    public static int myGetStepNum(int num) {
        int res = 0;
        while (num > 0) {
            res += num;
            num /= 10;
        }

        return res;
    }


}
