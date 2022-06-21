package brush.questions.chapter19;

/**
 * @program: my_algorithm
 * @description 给定一个正数N，比如N = 13，在纸上把所有数都列出来如下：
 * 1 2 3 4 5 6 7 8 9 10 11 12 13
 * 可以数出1这个字符出现了6次，给定一个正数N，如果把1~N都列出来，返回1这个字符出现的多少次
 * @author: 34371
 * @create: 2022-06-16 10:03
 **/
public class OneCounts {

    public static void main(String[] args) {
        System.out.println(countOneTimes(2345234));
    }

    public static int countOneTimes(int num) {

        if (num < 1) {
            return 0;
        }

        if (num<10){
            return 1;
        }

        int bits = getNumberBit(num);
        int base = (int)Math.pow(10,bits-1);
        int multiple = num / base;
        int modAddOne = num % base + 1;
        int highestOneTimes = 0;
        if (multiple == 1){
            highestOneTimes = modAddOne;
        }else{
            highestOneTimes = base;
        }
        int other = multiple * (int)Math.pow(10,bits-2)* (bits - 1) ;
        int follow = countOneTimes(modAddOne-1);
        return highestOneTimes+other+follow;
    }



    private static int getNumberBit(int num) {
        int i = 1;
        while (num != 0) {
            num /= 10;
            i++;
        }
        return i-1;
    }


}
