package org.example.Singleton.SingletonExample;

class SingletonLazy {
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
