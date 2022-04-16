package com.company.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 * @author: 34371
 * @create: 2022-04-14 22:21
 **/
public class Stickers2Str {


    public static void main(String[] args) {
        String[] stickers = new String[]{
                "heavy", "claim", "seven", "set", "had", "it", "dead", "jump", "design", "question", "sugar", "dress", "any", "special", "ground", "huge", "use", "busy", "prove", "there", "lone", "window", "trip", "also", "hot", "choose", "tie", "several", "be", "that", "corn", "after", "excite", "insect", "cat", "cook", "glad", "like", "wont", "gray", "especially", "level", "when", "cover", "ocean", "try", "clean", "property", "root", "wing"
        };
        String t = "travelbell";

        long start = System.nanoTime();
        System.out.println(minStickers(stickers, t));
        long end = System.nanoTime();
        System.out.println("time : " + (end - start));
    }

    public static int minStickers(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }
        if (stickers == null || stickers.length == 0) {
            return -1;
        }

        int[][] str = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {

            char[] chars = stickers[i].toCharArray();
            int[] letters = new int[26];
            for (int j = 0; j < chars.length; j++) {
                letters[chars[j] - 'a']++;
            }

            str[i] = letters;
        }

        int[] tArr = new int[26];
        char[] tChars = target.toCharArray();
        for (int i = 0; i < target.length(); i++) {
            tArr[tChars[i] - 'a']++;
        }

        int[] init = new int[26];

        Map<String, Integer> map = new HashMap<>();
        map.put(Arrays.toString(init), 0);
        int result = myProcess(str, tArr, map);
        return result == Integer.MAX_VALUE ? -1 : result;

    }

    private static int myProcess(int[][] stickers, int[] target, Map<String, Integer> map) {

        String key = Arrays.toString(target);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            boolean selected = false;
            //如果所选贴纸中有target的字母，才被选中
            for (int j = 0; j < 26; j++) {
//                if (sticker[j] != 0 && target[j] != 0) {
//                    selected = true;
//                    break;
//                }
                if (target[j]!=0){
                    if (sticker[j]!=0){
                        selected = true;
                    }else{
                        break;
                    }
                }
            }

            if (selected) {
                //这张贴纸解决的字母树后，剩余未解决的字母
                int[] surplus = myMinus(sticker, target);
                int r1 = myProcess(stickers, surplus, map);
                result = Math.min(result, r1
                        == Integer.MAX_VALUE ? Integer.MAX_VALUE : r1 + 1);
                map.put(key, result);
            }
        }

        return result;

    }

    private static int[] myMinus(int[] sticker, int[] target) {
//    }

        int[] res = new int[26];

        for (int i = 0; i < 26; i++) {
            int nums = target[i] - sticker[i];
            if (nums > 0) {
                res[i] = nums;
            }
        }

        return res;
    }

//    public static int myMinStickers(String[] stickers, String target) {
//        if (target == null || target.length() == 0) {
//            return 0;
//        }
//        if (stickers == null || stickers.length == 0) {
//            return -1;
//        }
//
//        int[][] str = new int[stickers.length][26];
//        for (int i = 0; i < stickers.length; i++) {
//
//            char[] chars = stickers[i].toCharArray();
//            int[] letters = new int[26];
//            for (int j = 0; j < chars.length; j++) {
//                letters[chars[j] - 'a']++;
//            }
//
//            str[i] = letters;
//        }
//
//        int[] tArr = new int[26];
//        char[] tChars = target.toCharArray();
//        for (int i = 0; i < target.length(); i++) {
//            tArr[tChars[i] - 'a']++;
//        }
//
//        int result = myProcess(str, tArr);
//        return result == Integer.MAX_VALUE ? -1 : result;
//
//    }
//
//    private static int myProcess(int[][] stickers, int[] target) {
//
//        boolean hasLetter = false;
//        for (int i = 0; i < 26; i++) {
//            if (target[i]!=0){
//                hasLetter = true;
//                break;
//            }
//        }
//
//        if (!hasLetter){
//            return 0;
//        }
//
//        int result = Integer.MAX_VALUE;
//        for (int i = 0; i < stickers.length; i++) {
//            int[] sticker = stickers[i];
//            boolean selected = false;
//            //如果所选贴纸中有target的字母，才被选中
//            for (int j = 0; j < 26; j++) {
//                if (sticker[j] != 0 && target[j] != 0) {
//                    selected = true;
//                    break;
//                }
//            }
//
//            if (selected) {
//                //这张贴纸解决的字母树后，剩余未解决的字母
//                int[] surplus = myMinus(sticker, target);
//                result = Math.min(result, myProcess(stickers, surplus) == Integer.MAX_VALUE ? Integer.MAX_VALUE : myProcess(stickers, surplus) + 1);
//            }
//        }
//
//        return result;
//


}
