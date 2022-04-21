package com.design.patterns.decorate;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-20 00:09
 **/
public class Egg extends Garnish {

    public Egg(Fastfood fastfood) {
        super(1, "加蛋", fastfood);
    }

    @Override
    public double cost() {
        return super.getPrice() + fastfood.getPrice();
    }

    @Override
    public String getDesc() {
        return super.getDesc()+fastfood.getDesc();
    }
}
