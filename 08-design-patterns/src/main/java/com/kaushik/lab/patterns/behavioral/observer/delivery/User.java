package com.kaushik.lab.patterns.behavioral.observer.delivery;

public class User implements Observer {
    private String location;

    @Override
    public void update(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
