package brush.questions.chapter24;

/**
 * @program: my_algorithm
 * @description 正常的里程表会依次显示自然数表示里程
 * 吉祥的里程表会忽略含有4的数字而跳到下一个完全不含有4的数
 * 正常：1 2 3 4 5 6 7 8  9 10 11 12 13 14 15
 * 吉祥：1 2 3 5 6 7 8 9 10 11 12 13 15 16 17 ... 38 39 50 51 52 53 55
 * 给定一个吉祥里程表的数字num(当然这个数字中不含有4)
 * 返回这个数字代表的真实里程
 * @author: 34371
 * @create: 2022-06-20 22:08
 **/
public class NumberTranslate {

    public static int[] BIT_COUNTS = new int[]{
            1, 9, 81, 729, 6561, 59049, 531441, 4782969, 43046721, 387420489/*,
            3486784401L,31381059609L,282429536481L,2541865828329L,22876792454961L,205891132094649L,
            1853020188851841L,16677181699666569L,150094635296999121L,1350851717672992089L*/
    };

    public static void main(String[] args) {

        System.out.println(transferNum(17));
    }

    public static int transferNum(int luckyNum) {
        int len = getLength(luckyNum);
        int base = getBase(len);

        int firstNum = luckyNum/base;
        return BIT_COUNTS[len-1]-1+(firstNum - (firstNum < 4 ? 1 : 2))*BIT_COUNTS[len-1] + process(luckyNum%base,len-1,base/10);

    }

    private static int process(int luckyNum,int len,int base) {
        if (len == 0){
            return 1;
        }

        int firstNum = luckyNum/base;

        return (firstNum<4?firstNum : (firstNum - 1))*BIT_COUNTS[len-1] + process(luckyNum%base,len-1,base/10);

    }

    public static int getLength(int num) {
        int i = 1;
        while ((num /= 10) != 0) {
            i++;
        }
        return i;
    }

    public static int getBase(int len) {
        return (int) Math.pow(10, len - 1);
    }

}
