package com.kaushik.lab.patterns.structural.decorator.barista.beverage;

public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    public double cost() {
        return .99;
    }
}
