package com.company.kmp;

import com.company.util.StringUtils;

public class Kmp {

    public static void main(String[] args) {
        String s1 = "abcdabd";
        String s2 = "abd";
        System.out.println(getIndexOf(s1,s2));
    }

    /**
     * source是否包含dest，如果包含，返回dest所在的第一个下标
     *
     * @param source source
     * @param dest   被包含的字符串
     * @return dest在source中的第一个下标位置，不存在返回-1
     */
    public static int getIndexOf(String source, String dest) {

        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(dest) || source.length() < dest.length()) {
            return -1;
        }

        char[] sourceArr = source.toCharArray();
        char[] destArr = dest.toCharArray();
        int[] nextArray = getNextArray(destArr);

        int sourceIndex = 0;
        int destIndex = 0;
        while (sourceIndex < sourceArr.length && destIndex < destArr.length) {
            if (sourceArr[sourceIndex] == destArr[destIndex]) {
                sourceIndex++;
                destIndex++;
            } else if (nextArray[destIndex]!=-1){
                destIndex = nextArray[destIndex];
            }else{
                sourceIndex++;
            }
        }

        return destIndex == destArr.length?sourceIndex-destIndex:-1;

    }

    /**
     * 用于存储dest中每个位置前最大的前缀后缀一致的值
     *
     * @param dest 目标字符串
     * @return 前后缀最大一致值
     * <p>
     * 如：[a,b,b,a,c] 返回值就是 [-1,0,0,0,2]
     */
    public static int[] getNextArray(char[] dest) {
        if (dest == null) {
            return null;
        }

        if (dest.length == 1) {
            return new int[]{-1};
        }
        int[] result = new int[dest.length];
        result[0] = -1;
        result[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < dest.length) {
            if (dest[i - 1] == dest[cn]) {
                result[i++] = ++cn;
            } else if (cn > 0) {
                cn = result[cn];
            } else {
                result[i++] = 0;
            }
        }

        return result;
    }

}
