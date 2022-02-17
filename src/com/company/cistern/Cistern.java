package com.company.cistern;

//蓄水池算法
public class Cistern {

    public static int[] cistern(int[] arr,int size){
        int[] result = new int[size];
        for (int i =0;i<arr.length;i++){
            if (i<size){
                //容量未满时，都加进去
                result[i] = arr[i];
            }else {
                //容量已经满了，则需要概率选取
                if (add(i)<10){
                    //需要加到池子中
                    //此时需要在池子中随机选取一个扔掉
                    result[(int)(Math.random()*10)] = arr[i];
                }
            }

        }
        return result;
    }

    public static int add(int i){
        int ram = (int) (Math.random() * i);
        return ram;
    }

}
