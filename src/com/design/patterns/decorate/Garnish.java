package com.design.patterns.decorate;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-20 00:07
 **/
public abstract class Garnish extends Fastfood{

    protected Fastfood fastfood;

    public Garnish(double price, String desc, Fastfood fastfood) {
        super(price, desc);
        this.fastfood = fastfood;
    }

}
