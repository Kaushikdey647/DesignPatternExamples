package com.kaushik.lab.patterns.creational.singleton;

class SingletonLazySynchronizedMethod {
    private static SingletonLazySynchronizedMethod instance;

    private SingletonLazySynchronizedMethod() {
    }

    //Thread Safe
    public static synchronized SingletonLazySynchronizedMethod getInstance() {
        if (instance == null) {
            instance = new SingletonLazySynchronizedMethod();
        }

        return instance;
    }
}
