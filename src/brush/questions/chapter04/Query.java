package brush.questions.chapter04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: my_algorithm
 * @description 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)
 * 意思是在数组里下标0~3这个范围上，有几个2？答案返回2。
 * 假设给你一个数组arr，
 * 对这个数组的查询非常频繁，都给出来
 * 请返回所有查询的结果
 * @author: 34371
 * @create: 2022-05-13 21:42
 **/
public class Query {

    public static void main(String[] args) {
        QueryBox queryBox = new QueryBox(new int[]{3, 2, 2, 3, 1});
        System.out.println(queryBox.query(0,3,2));
    }

    static class QueryBox {
        HashMap<Integer, ArrayList<Integer>> preprocessingStructure = new HashMap<>();

        public QueryBox(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if (preprocessingStructure.containsKey(arr[i])) {
                    preprocessingStructure.get(arr[i]).add(i);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    preprocessingStructure.put(arr[i], list);
                }
            }
        }

        /**
         * @param start 起始坐标
         * @param end   截至位置
         * @param num   查询的数
         * @return
         */
        public int query(int start, int end, int num) {
            if (!preprocessingStructure.containsKey(num)) {
                return 0;
            }

            //二分查找: 找出大于等于start的         小于等于end的下标
            ArrayList<Integer> indexes = preprocessingStructure.get(num);
            int startIndex = binSearchLeft(indexes, 0, indexes.size() - 1, start);
            int endIndex = binSearchLeft(indexes, 0, indexes.size() - 1, end);
            return endIndex - startIndex;
        }

        //二分查找<x的最大值
        public int binSearchLeft(ArrayList<Integer> indexes, int left, int right, int num) {
            int mostRight = -1;
            while (left <= right) {
                int mid =   + (right - left) / 2;
                if (indexes.get(mid) >= num) {
                    right = mid - 1;
                } else {
                    mostRight = mid;
                    left = mid + 1;
                }
            }
            return mostRight+1;
        }

    }


}
