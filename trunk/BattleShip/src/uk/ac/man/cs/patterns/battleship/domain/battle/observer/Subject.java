package uk.ac.man.cs.patterns.battleship.domain.battle.observer;

/**
 * Observer Pattern. Interface that represent the subject in the observer pattern.
 * @author Guillermo Antonio Toro Bayona
 */
public interface Subject {

    /**
     * Observer Pattern. Method to register an observer
     * @param observer Observer
     */
    public void registerObserver(Observer observer);

    /**
     * Observer Pattern. Method to remove an observer
     * @param observer Observer
     */
    public void removeObserver(Observer observer);

    /**
     * Observer Pattern. Method to notify an observer
     */
    public void notifyObservers();
}
