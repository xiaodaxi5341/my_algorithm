package brush.questions.chapter02;

import java.util.PriorityQueue;

/**
 * @program: my_algorithm
 * @description 现有司机N*2人，调度中心会将所有司机平分给A、B两区域，i号司机去A可得收入为income[i][0]，去B可得收入为income[i][1]
 * 返回能使所有司机总收入最高的方案是多少钱?
 * @author: 34371
 * @create: 2022-05-09 22:59
 **/
public class DriverMostMoney {

    //arr数组说明：
    // [
    //  [1,2]  第一个司机去A区域能获得1块钱，B区域能获得2块钱
    //  [3,4]  第二个司机去A获得3，B获得4
    // ]
    public static int mostMoney(int[][] arr) {

        if (arr == null || arr.length < 2 || (arr.length & 1) != 0) {
            return 0;
        }

        int N = arr.length>>1;
        PriorityQueue<Driver> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(new Driver(arr[i][0],arr[i][1]));
        }
        int result = 0;
        int aRegion = 0;
        int bRegion = 0;
        while (!priorityQueue.isEmpty()){
            Driver poll = priorityQueue.poll();
            boolean aBig = poll.A >= poll.B;
            if (aBig && aRegion<N){ //A收益相等或较高且a区域未满
                aRegion++;
                result+= poll.A;
            }else if (!aBig && bRegion< N){ //B收益高且B区域未满
                bRegion++;
                result+=poll.B;
            }else {//都满了，直接break
                break;
            }
        }
        return result;
    }

    static class Driver implements Comparable<Driver> {
        int A;
        int B;

        public Driver(int a, int b) {
            A = a;
            B = b;
        }

        @Override
        public int compareTo(Driver o) {
            return Math.abs(o.A - o.B) - Math.abs(this.A - this.B);
        }
    }

}
