package org.example.Decorator.BaristaDecorator.Decorator;

import org.example.Decorator.BaristaDecorator.Beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}