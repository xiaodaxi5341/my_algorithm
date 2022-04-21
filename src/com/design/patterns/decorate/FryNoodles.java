package com.design.patterns.decorate;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-20 00:04
 **/
public class FryNoodles extends Fastfood{

    public FryNoodles(){
        super(15,"炒面");
    }

    @Override
    public double cost() {
        return getPrice();
    }
}
