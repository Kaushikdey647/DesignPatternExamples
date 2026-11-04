package com.kaushik.lab.patterns.creational.singleton;

class SingletonLazySynchronizedBlock {
    private static SingletonLazySynchronizedBlock instance;

    private SingletonLazySynchronizedBlock() {
    }

    //Thread Safe
    public static SingletonLazySynchronizedBlock getInstance() {
        if (instance == null) {
            // This is called double-checked locking
            synchronized (SingletonLazySynchronizedBlock.class) {
                if (instance == null) {
                    instance = new SingletonLazySynchronizedBlock();
                }
            }
        }

        return instance;
    }
}
