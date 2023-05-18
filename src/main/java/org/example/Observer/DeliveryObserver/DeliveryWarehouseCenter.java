package org.example.Observer.DeliveryObserver;

class DeliveryWarehouseCenter implements Observer {
    private String location;

    @Override
    public void update(String location) {
        this.location = location;
        showLocation();
    }

    public void showLocation() {
        System.out.println("Notification at DeliveryWarehouseCenter - Current location: " + location);
    }
}
