package com.company.manacher;

import com.company.util.StringUtils;

//该算法可以用来求解字符串中的最长回文子字符串长度
//其余用途，目前尚未学习到。
public class Manacher {

    public static void main(String[] args) {
        System.out.println(getMaxLengthOfPalindrome("ss12321aa"));
    }

    //各字段含义详见视频：https://www.bilibili.com/video/BV1Ef4y1T7Qi?p=36  前一个半小时
    public static int getMaxLengthOfPalindrome(String s) {

        if (StringUtils.isEmpty(s)) {
            return -1;
        }

        char[] chars = specialProcess(s);

        int[] radiusArr = new int[chars.length];
        int maxRightIndex = -1;
        int center = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0;i<chars.length;i++){
            radiusArr[i] = maxRightIndex>i?Math.min(radiusArr[2*center-i],maxRightIndex-i):1;

            while (i+radiusArr[i]<chars.length&&i-radiusArr[i]>-1){
                if (chars[i+radiusArr[i]] == chars[i-radiusArr[i]]){
                    radiusArr[i]++;
                }else{
                    break;
                }
            }

            if (i+radiusArr[i] > maxRightIndex){
                maxRightIndex = i+radiusArr[i];
                center = i;
            }

            max = Math.max(max,radiusArr[i]);
        }

        return max-1;

    }

    //给字符串的每个字符之间加上分割字符
    //如：abbc -> #a#b#b#c#
    private static char[] specialProcess(String s) {
        if (StringUtils.isEmpty(s)) {
            return new char[0];
        }

        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append("#").append(aChar);
        }
        sb.append("#");

        return sb.toString().toCharArray();

    }

}
