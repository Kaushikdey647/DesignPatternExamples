package org.example.Decorator.BaristaDecorator;


import org.example.Decorator.BaristaDecorator.Beverage.Beverage;
import org.example.Decorator.BaristaDecorator.Beverage.DarkRoast;
import org.example.Decorator.BaristaDecorator.Beverage.Espresso;
import org.example.Decorator.BaristaDecorator.Beverage.HouseBlend;
import org.example.Decorator.BaristaDecorator.Decorator.Mocha;
import org.example.Decorator.BaristaDecorator.Decorator.Soy;

public class Main {
    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        Beverage houseBlend = new HouseBlend();
        Beverage darkRoast = new DarkRoast();

        Beverage espressoWithMocha = new Mocha(espresso);
        System.out.println(espressoWithMocha.getDescription() + " $" + espressoWithMocha.cost());

        Beverage houseBlendWithSoy = new Soy(houseBlend);
        System.out.println(houseBlendWithSoy.getDescription() + " $" + houseBlendWithSoy.cost());

        Beverage darkRoastWithMochaAndSoy = new Soy(new Mocha(darkRoast));
        System.out.println(darkRoastWithMochaAndSoy.getDescription() + " $" + darkRoastWithMochaAndSoy.cost());
    }
}
