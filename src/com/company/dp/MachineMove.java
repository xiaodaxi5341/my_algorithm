package com.company.dp;

/**
 * @program: my_algorithm
 * @description 机器人移动问题
 * @author: 34371
 * @create: 2022-03-03 16:44
 **/
public class MachineMove {

    public static void main(String[] args) {
        System.out.println(calculateTotal(4,2,4,4));
    }

    public static int calculateTotal(int totalPos,int start,int aim,int limit){
        return process(totalPos,start,aim,limit);
    }

    /**
     *
     * @param totalPos 一共有多少位置 从1开始
     * @param cur 当前在哪个位置
     * @param aim 目标在哪个位置
     * @param surplus 剩余多少步
     * @return
     */
    private static int process(int totalPos, int cur, int aim, int surplus) {
        if (surplus == 0){
            if (cur == aim){
                return 1;
            }else{
                return 0;
            }
        }
        int result = 0;
        if (cur == 1){
            result+=process(totalPos,cur+1,aim,surplus-1);
        }else if (cur == totalPos){
            result+=process(totalPos,cur-1,aim,surplus-1);
        }else{
            //既可以向右走也可以向左走
            result+=process(totalPos,cur+1,aim,surplus-1);
            result+=process(totalPos, cur-1, aim, surplus-1);
        }

        return result;
    }

}
