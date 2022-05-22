package brush.questions.chapter01;

/**
 * @program: my_algorithm
 * @description 一个数组中只有两种字符'G'和'B'，可以让所有的G都放在左侧，所有的B都放在右侧
 * 或者可以让所有的G都放在右侧，所有的B都放在左侧，但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
 * @author: 34371
 * @create: 2022-04-07 19:45
 **/
public class ExchangeGB {

    public static void main(String[] args) {
        System.out.println(count("GBGGBB".toCharArray()));
    }

    public static int count(char[] arr) {
        if (arr == null|| arr.length == 0){
            return 0;
        }
        if (arr.length == 1){
            return 0;
        }
        return Math.min(process(arr,'G'),process(arr,'B'));
    }

    /**
     * 指定字母放在左边
     * @param arr example : GBGGBB
     * @param letter G
     * @return
     */
    public static int process(char[] arr,char letter){
        int l = 0;
        int index = 0;
        int sum = 0;
        for (;index<arr.length;index++){
            if (arr[index] == letter){
                sum+=index-l;
                l++;
            }
        }

        return sum;
    }

}
