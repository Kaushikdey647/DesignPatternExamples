package org.example.Observer.DeliveryObserver;

public class Main {
    public static void main(String[] args) {
        DeliveryData deliveryData = new DeliveryData();

        Seller seller = new Seller();
        User user = new User();
        DeliveryWarehouseCenter deliveryWarehouseCenter = new DeliveryWarehouseCenter();

        deliveryData.registerObserver(seller);
        deliveryData.registerObserver(user);
        deliveryData.registerObserver(deliveryWarehouseCenter);

        deliveryData.locationChanged();
    }
}