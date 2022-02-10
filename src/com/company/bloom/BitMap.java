package com.company.bloom;

import java.util.function.IntBinaryOperator;

//位图：以位数表示状态：那么int类型就可以保存32种状态
public class BitMap {

    public static String intToBinary32(int i){
        String binaryStr = Integer.toBinaryString(i);
        while(binaryStr.length() < 32){
            binaryStr = "0"+binaryStr;
        }
        return binaryStr;
    }

    //11010000
    public static void main(String[] args) {

        //一共就320位
        int[] arr = new int[10];
        for (int i = 0 ; i<10;i++){
            System.out.println(intToBinary32(i*10));
            arr[i] = i * 10;
        }

        //获取第280位
        //求位于哪个下标
        int index = 280/32;
        System.out.println("index:"+index);
        //求位于哪个
        int bitIndex = 280%32;
        System.out.println("bitIndex:"+bitIndex);


        //获取该位的值 0或1
        int bitNum = (arr[index]>>bitIndex)&1;
        System.out.println("bitNum:"+intToBinary32(bitNum));

        //改变该位的状态为1
        arr[index] = arr[index]|(1<<bitIndex);
        System.out.println("arr[index]:"+intToBinary32(arr[index]));

        //改变该位置状态为0
        arr[index] = arr[index]&(~(1<<bitIndex));
        System.out.println("arr[index]:"+intToBinary32(arr[index]));
    }

}
