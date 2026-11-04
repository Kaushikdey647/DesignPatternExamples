package com.kaushik.lab.patterns.behavioral.observer;

import com.kaushik.lab.patterns.behavioral.observer.delivery.DeliveryData;
import com.kaushik.lab.patterns.behavioral.observer.delivery.DeliveryWarehouseCenter;
import com.kaushik.lab.patterns.behavioral.observer.delivery.Seller;
import com.kaushik.lab.patterns.behavioral.observer.delivery.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryObserverTest {
    DeliveryData deliveryData = new DeliveryData();
    Seller seller = new Seller();
    DeliveryWarehouseCenter deliveryWarehouseCenter = new DeliveryWarehouseCenter();
    User user = new User();

    @Test
    public void checkInitializationIntegritySeller(){
        assertNull(seller.getLocation());
    }

    @Test
    public void checkInitializationIntegrityDeliveryData(){
        assertNull(deliveryData.getLocation());
    }

    @Test
    public void checkInitializationIntegrityDeliveryWarehouseCenter(){
        assertNull(deliveryWarehouseCenter.getLocation());
    }



    @Test
    public void checkRegisterObserver(){
        deliveryData.registerObserver(seller);
        deliveryData.setLocation("New York");
        assertEquals(deliveryData.getLocation(), seller.getLocation());
        deliveryData.removeObserver(seller);
    }

    @Test
    public void checkRemoveObserver(){
        deliveryData.registerObserver(seller);
        deliveryData.removeObserver(seller);
        deliveryData.setLocation("Los Angeles");
        assertNotEquals(deliveryData.getLocation(), seller.getLocation());
    }

    @Test
    public void checkNotifyObservers(){
        deliveryData.registerObserver(seller);
        deliveryData.registerObserver(user);
        deliveryData.registerObserver(deliveryWarehouseCenter);
        deliveryData.notifyObservers();
        assertEquals(deliveryData.getLocation(), seller.getLocation());
        assertEquals(deliveryData.getLocation(), user.getLocation());
        assertEquals(deliveryData.getLocation(), deliveryWarehouseCenter.getLocation());
        deliveryData.removeObserver(seller);
        deliveryData.removeObserver(user);
        deliveryData.removeObserver(deliveryWarehouseCenter);
    }

}
