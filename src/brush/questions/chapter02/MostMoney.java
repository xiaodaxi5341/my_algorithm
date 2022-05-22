package brush.questions.chapter02;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @program: my_algorithm
 * @description 给定数组hard和money，长度都为N，hard[i]表示i号工作的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力，每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 * @author: 34371
 * @create: 2022-05-08 22:24
 **/
public class MostMoney {


    static class MyJob implements Comparable<MyJob> {
        int hard;
        int money;

        public MyJob(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }

        @Override
        public int compareTo(MyJob o) {
            return o.money - this.money == 0 ? this.hard - o.hard : o.money - this.money;
        }

//        @Override
//        public int compareTo(Job o) {
//            return o.money - this.money;
//        }


        @Override
        public String toString() {
            return "hard : " + hard + " , money : " + money;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{21, 26, 3,2, 44, 56};
        int[] b = new int[]{145, 221, 33,234, 425, 25};
        int[] c = new int[]{1, 56, 23, 35, 45};
        System.out.println(Arrays.toString(myMostMoney2(a, b, c)));
        System.out.println(Arrays.toString(myMostMoney(a, b, c)));
    }


    public static int[] myMostMoney2(int[] hard, int[] money, int[] ability) {
        MyJob[] myJobs = new MyJob[hard.length];
        for (int i = 0; i < hard.length; i++) {
            myJobs[i] = new MyJob(hard[i], money[i]);
        }
        //排序后的工作：挣的钱多的靠前，钱相等时难度低的在前
        Arrays.sort(myJobs);
        //key-难度:从小到大的排序  value-以money为由大到小的排序
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < myJobs.length; i++) {
            //先循环到的挣钱一定多,后循环到的挣钱一定少
            //看了视频才明白的点：任务难度高，但是收入小的直接去掉即可，不用保留
            Integer latestMaxMoney = map.floorKey(myJobs[i].hard - 1) == null ? 0 : map.get(map.floorKey(myJobs[i].hard - 1));
            if (!map.containsKey(myJobs[i].hard)&&myJobs[i].money>latestMaxMoney) {
                map.put(myJobs[i].hard, myJobs[i].money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            int get = map.floorKey(ability[i]) == null ? 0 : map.get(map.floorKey(ability[i]));
            ans[i] = get;
        }
        return ans;
    }

    public static int[] myMostMoney(int[] hard, int[] money, int[] ability) {

        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            int get = 0;
            for (int j = 0; j < hard.length; j++) {
                if (hard[j] <= ability[i]) {
                    get = Math.max(get, money[j]);
                }
            }
            ans[i] = get;
        }

        return ans;
    }

}
