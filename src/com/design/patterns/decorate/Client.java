package com.design.patterns.decorate;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-20 00:12
 **/
public class Client {

    public static void main(String[] args) {
        Fastfood food = new FryRice();
        print(food);
        food = new Egg(food);
        print(food);
        food = new Banana(food);
        print(food);
    }

    public static void print(Fastfood food){
        System.out.println(food.getDesc()+food.cost()+"元");
    }
}
