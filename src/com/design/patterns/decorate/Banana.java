package com.design.patterns.decorate;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-20 00:10
 **/
public class Banana extends Garnish {

    public Banana(Fastfood fastfood) {
        super(18, "香蕉", fastfood);
    }

    @Override
    public double cost() {
        return super.getPrice() + fastfood.getPrice();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + fastfood.getDesc();
    }
}
