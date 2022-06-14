package caiji.bit;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-24 10:34
 **/
public class Main {

    public static void main(String[] args) {
//        A a = new B();
//        B b = new B();
        int a = 1;
        int b = a;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(a);
        System.out.println(b);
    }

}
