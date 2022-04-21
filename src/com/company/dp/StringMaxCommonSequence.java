package com.company.dp;

/**
 * @program: my_algorithm
 * @description 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * <p>
 * 比如 ： str1 = “a12b3c456d”,
 * str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 *
 * 类目类型：范围的尝试模型
 * @author: 34371
 * @create: 2022-04-16 16:13
 **/
public class StringMaxCommonSequence {

    public static void main(String[] args) {
//        System.out.println(longestCommonSubsequence("abc".toCharArray(),"def".toCharArray()));
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

    public static int longestCommonSubsequence(String text1, String text2) {

        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }

        return process(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);

    }

    //公共子序列必须以s1/s2的某个字符结尾
    private static int process(char[] s1, char[] s2, int i, int j) {


        if (i == 0 && j == 0) {

            return s1[i] == s2[j] ? 1 : 0;

        } else if (i == 0) {

            if (s1[i] == s2[j]) {
                return 1;
            } else {
                return process(s1, s2, i, j - 1);
            }

        } else if (j == 0) {

            if (s1[i] == s2[j]) {
                return 1;
            } else {
                return process(s1, s2, i - 1, j);
            }

        }else{

            //假如，i:对  j：对
            //     i:对  j: 错
            //      i: 错， j对
            //     i错，j错
            if (s1[i] == s2[j]) {
                return process(s1, s2, i - 1, j - 1) + 1;
            } else {
                int p1 = process(s1, s2, i, j - 1);
                int p2 = process(s1, s2, i - 1, j);
                int p3 = process(s1, s2, i - 1, j - 1);
                return Math.max(p1, Math.max(p2, p3));
            }

        }


    }

}
