package org.example.Singleton.SingletonExample;

public class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    //Def not thread safe
    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }

        return instance;
    }
}
