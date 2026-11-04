package com.kaushik.lab.patterns.structural.decorator.barista.beverage;

public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
