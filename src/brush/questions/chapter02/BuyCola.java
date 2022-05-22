package brush.questions.chapter02;

import java.util.*;

/**
 * @program: my_algorithm
 * @description 贩卖机只支持硬币支付，且收退都只支持10 ，50，100三种面额
 * 一次购买只能出一瓶可乐，且投钱和找零都遵循优先使用大钱的原则
 * 需要购买的可乐数量是m，其中手头拥有的10、50、100的数量分别为a、b、c，可乐的价格是x(x是10的倍数)
 * 请计算出需要投入硬币次数
 * @author: 34371
 * @create: 2022-05-09 20:57
 **/

public class BuyCola {

    public static void main(String[] args) {
        System.out.println(coinTimes(8,1,2,4,110));
    }

    private static final int TEN = 10;
    private static final int FIFTY = 50;
    private static final int HUNDRED = 100;


    /**
     * 做完反思：这个方法巨烂。。。。我是菜鸡
     */
    public static int coinTimes(int tenCounts, int fiftyCounts, int hundredCounts, int m, int cola) {



        PriorityQueue<Integer> queue = new PriorityQueue<>(new IntegerDescComparator());
        while (hundredCounts > 0) {
            queue.add(HUNDRED);
            hundredCounts--;
        }
        while (fiftyCounts > 0) {
            queue.add(FIFTY);
            fiftyCounts--;
        }
        while (tenCounts > 0) {
            queue.add(TEN);
            tenCounts--;
        }

        int counts = 0;
        int curMoney = 0;
        while (!queue.isEmpty() && m > 0) {
            curMoney += queue.poll();
            if (curMoney >= cola) {
                counts ++;
                //剩余多少钱
                int surplusAll = curMoney - cola;
                //剩余钱分组，重新扔进库里
                int surplus100 = surplusAll / 100;
                int surplus50 = (surplusAll - surplus100) / 50;
                int surplus10 = (surplusAll - surplus100*100 - surplus50*50) / 10;
                while (surplus100 > 0) {
                    queue.add(100);
                    surplus100--;
                }
                while (surplus50 > 0) {
                    queue.add(50);
                    surplus50--;
                }
                while (surplus10 > 0) {
                    queue.add(10);
                    surplus10--;
                }

                curMoney = 0;
                m -= 1;

            } else {
                counts++;
            }
        }

        return m>0?-1:counts;

    }

    static class IntegerDescComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

}
