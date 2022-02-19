package caiji.bit;

public class BitDemo {

    public static void main(String[] args) {
        printBit(~(-1)+1);
    }

    public static void printBit(int num){
        for (int i=31;i>=0;i--){
            System.out.print((num&(1<<i))==0?"0":"1");
        }
        System.out.println();
    }

}
