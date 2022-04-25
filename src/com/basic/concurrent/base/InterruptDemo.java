package com.basic.concurrent.base;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-22 15:50
 **/
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("interrupt");
                    break;
                }
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("sleep interrupt");
//                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        });

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

}
