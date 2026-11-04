package com.kaushik.lab.patterns.structural.decorator.barista.decorator;

import com.kaushik.lab.patterns.structural.decorator.barista.beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}