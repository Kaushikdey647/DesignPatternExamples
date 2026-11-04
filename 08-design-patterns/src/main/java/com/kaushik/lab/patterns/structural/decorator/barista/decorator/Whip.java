package com.kaushik.lab.patterns.structural.decorator.barista.decorator;

import com.kaushik.lab.patterns.structural.decorator.barista.beverage.Beverage;

public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription(){
        return beverage.getDescription() + ", Whip";
    }

    public double cost() {
        return .10 + beverage.cost();
    }
}
