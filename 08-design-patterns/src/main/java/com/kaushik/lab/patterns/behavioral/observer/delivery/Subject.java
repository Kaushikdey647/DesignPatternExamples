package com.kaushik.lab.patterns.behavioral.observer.delivery;

interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
