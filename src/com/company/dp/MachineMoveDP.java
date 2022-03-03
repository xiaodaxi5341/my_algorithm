package com.company.dp;

/**
 * @program: my_algorithm
 * @description 机器人移动，动态规划版
 * @author: 34371
 * @create: 2022-03-03 16:56
 **/
public class MachineMoveDP {

    public static void main(String[] args) {
        System.out.println(calculateTotal(5,2,4,6));
    }

    public static int calculateTotal(int totalPos,int start,int aim,int limit){
        int[][] arr = new int[totalPos+1][limit+1];
        //初始化第一行
        for (int i=0;i<arr.length;i++){
            if (i == aim){
                arr[i][0] = 1;
            }else{
                arr[i][0] = 0;
            }
        }
        for (int i = 1;i<limit+1;i++){
            for (int j=totalPos;j>0;j--){
                if (j==totalPos){
                    arr[j][i] = arr[j-1][i-1];
                }else if(j == 1){
                    arr[j][i] = arr[j+1][i-1];
                }else{
                    arr[j][i] = arr[j+1][i-1]+arr[j-1][i-1];
                }
            }
        }
        return process(totalPos,start,limit,arr);
    }

    private static int process(int totalPos, int cur, int limit, int[][] arr) {

        int result = 0;
        if (cur == 1){
            result+=arr[cur+1][limit-1];
        }else if (cur == totalPos){
            result+=arr[cur-1][limit-1];
        }else{
            //既可以向右走也可以向左走
            result+=arr[cur+1][limit-1];
            result+=arr[cur-1][limit-1];
        }

        return result;

    }

}
