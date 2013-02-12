package uk.ac.man.cs.patterns.battleship.domain.battle.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern. Abstract class that represent the subject in the observer pattern.
 * @author Guillermo Antonio Toro Bayona
 */
public abstract class Subject {

    /**
     * List of observers
     */
    private List<Observer> observers;

    /**
     * Constructor
     */
    protected Subject() {
        this.observers = new ArrayList<Observer>();
    }

    /**
     * Observer Pattern. Method to register observer.
     * @param observer Observer
     */
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Observer Pattern. Method to remove observer.
     * @param observer Observer
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Observer Pattern. Method to notify observer of changes
     */
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.updateObserver(this);
        }
    }
}
