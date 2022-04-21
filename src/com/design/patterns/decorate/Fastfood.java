package com.design.patterns.decorate;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-04-19 23:59
 **/
public abstract class Fastfood {

    private double price;
    private String desc;

    public Fastfood() {
    }

    public Fastfood(double price, String desc) {
        this.price = price;
        this.desc = desc;
    }

    public abstract double cost();

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
