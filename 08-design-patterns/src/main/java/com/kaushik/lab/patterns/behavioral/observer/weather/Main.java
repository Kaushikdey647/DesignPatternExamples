package com.kaushik.lab.patterns.behavioral.observer.weather;

public class Main {
    public static void main(String[] args) {

        //Observable Initialized
        WeatherData weatherData = new WeatherData();

        //Observers Initialized with Observable
        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherData);

        ForecastDisplay forecastDisplay =
                new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
    }
}
