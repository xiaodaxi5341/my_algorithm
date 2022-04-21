package com.design.patterns.decorate;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-20 00:03
 **/
public class FryRice extends Fastfood{

    public FryRice(){
        super(10,"炒饭");
    }

    @Override
    public double cost() {
        return getPrice();
    }
}
