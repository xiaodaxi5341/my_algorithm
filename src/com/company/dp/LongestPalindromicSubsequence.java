package com.company.dp;

/**
 * @program: my_algorithm
 * @description 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * @author: 34371
 * @create: 2022-04-19 21:21
 **/
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println(dp("aabaaba"));
        System.out.println(longestPalindromeSubseq("aabaaba"));
    }

    public static int dp(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int[][] dp = new int[str.length()][str.length()];
        for (int l = 0; l < str.length(); l++) {
            dp[l][l] = 1;
            if (l < str.length() - 1) {
                dp[l][l + 1] = chars[l] == chars[l + 1] ? 2 : 1;
            }
        }

        for (int r = 2; r < str.length(); r++) {
            int l = 0;
            int ri = r;
            while (l < str.length() - r && l<ri) {
                if (chars[l] == chars[ri]) {
                    dp[l][ri] = 2 + dp[l + 1][ri - 1];
                } else {
                    int r1 = dp[l][ri - 1];
                    int r2 = dp[l + 1][ri];
                    dp[l][ri] = Math.max(r1, r2);
                }
                l++;
                ri++;
            }
        }

        return dp[0][str.length() - 1];
    }

    //范围尝试模型
    public static int longestPalindromeSubseq(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }

        return process(str.toCharArray(), 0, str.length() - 1);
    }

    private static int process(char[] str, int l, int r) {

        if (l == r) {
            return 1;
        }

        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }

        //公共子序列以l开头，以r结尾
        if (str[l] == str[r]) {
            return 2 + process(str, l + 1, r - 1);
        } else {
            // l 开  r 不
            int r1 = process(str, l, r - 1);
            // l 不  r 尾
            int r2 = process(str, l + 1, r);
            // l 不  r 不
            int r3 = process(str, l + 1, r - 1);
            return Math.max(r1, Math.max(r2, r3));
        }


    }

}
