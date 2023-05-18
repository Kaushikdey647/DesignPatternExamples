package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Write a welcome and guide to other Main classes within the module
        System.out.println("Welcome to the Design Patterns module!");
        System.out.println("Please select a Main class to run:");
        System.out.println("1. SingletonExample");
        System.out.println("2. BaristaDecorator");
        System.out.println("3. DeliveryObserver");
        System.out.println("4. WeatherStationObserver");

        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();

        switch (selection) {
            case 1 -> org.example.Singleton.SingletonExample.Main.main(args);
            case 2 -> org.example.Decorator.BaristaDecorator.Main.main(args);
            case 3 -> org.example.Observer.DeliveryObserver.Main.main(args);
            case 4 -> org.example.Observer.WeatherStationObserver.Main.main(args);
            default -> System.out.println("Invalid selection");
        }
    }
}