package org.example.Observer.DeliveryObserver;

public class DeliveryWarehouseCenter implements Observer {
    private String location;

    @Override
    public void update(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
