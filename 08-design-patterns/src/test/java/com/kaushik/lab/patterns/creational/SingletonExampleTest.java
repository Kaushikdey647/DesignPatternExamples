package com.kaushik.lab.patterns.creational;


import com.kaushik.lab.patterns.creational.singleton.SingletonEager;
import com.kaushik.lab.patterns.creational.singleton.SingletonLazy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonExampleTest {

    @Test
    public void checkEqualityOnEagerInstance(){

        SingletonEager instance1 = SingletonEager.getInstance();

        SingletonEager instance2 = SingletonEager.getInstance();

        assertEquals(instance1, instance2);
    }

    @Test
    public void checkEqualityOnLazyInstance(){

        SingletonLazy instance1 = SingletonLazy.getInstance();

        SingletonLazy instance2 = SingletonLazy.getInstance();

        assertEquals(instance1, instance2);
    }
}
