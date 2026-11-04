package com.kaushik.lab.patterns.structural.decorator.barista;


import com.kaushik.lab.patterns.structural.decorator.barista.beverage.Beverage;
import com.kaushik.lab.patterns.structural.decorator.barista.beverage.DarkRoast;
import com.kaushik.lab.patterns.structural.decorator.barista.beverage.Espresso;
import com.kaushik.lab.patterns.structural.decorator.barista.beverage.HouseBlend;
import com.kaushik.lab.patterns.structural.decorator.barista.decorator.Mocha;
import com.kaushik.lab.patterns.structural.decorator.barista.decorator.Soy;

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
