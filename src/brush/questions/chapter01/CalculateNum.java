package brush.questions.chapter01;

/**
 * @program: my_algorithm
 * @description 给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的，2的某次方
 * @author: 34371
 * @create: 2022-04-07 19:20
 **/
public class CalculateNum {

    public static int calculateNum(int n){
        n--;
        n|=n>>1;
        n|=n>>2;
        n|=n>>4;
        n|=n>>8;
        n|=n>>16;
        return n+1;
    }

    public static void main(String[] args) {
        System.out.println(calculateNum(9));
    }

}
