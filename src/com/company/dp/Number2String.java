package com.company.dp;

/**
 * @program: my_algorithm
 * @description 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * @author: 34371
 * @create: 2022-04-14 21:03
 **/
public class Number2String {

    public static void main(String[] args) {
        System.out.println(Integer.valueOf('1'));
    }

    public static int getResult(String param) {

        if (param == null || param.trim().equals("")) {
            return 0;
        }
        char[] str = param.toCharArray();
        return process(str, 0);

    }

    private static int process(char[] str, int i) {

        if (i == str.length) {
            return 1;
        }

        if (str[i] == '0') {
            return 0;
        }

        int res = process(str, i + 1);
        if (i + 1 < str.length && Integer.valueOf(str[i] - '0') * 10 + Integer.valueOf(str[i + 1] - '0') < 27) {
            res += process(str, i + 2);
        }
        return res;
    }

    private static int processDp(char[] str) {
        int[] dp = new int[str.length + 1];
        dp[str.length] = 1;

        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] != '0'){
                int res = dp[i+1];
                if (i + 1 < str.length && Integer.valueOf(str[i] - '0') * 10 + Integer.valueOf(str[i + 1] - '0') < 27){
                    res+=dp[i+2];
                }
                dp[i] = res;
            }
        }
        return dp[0];
    }

}
