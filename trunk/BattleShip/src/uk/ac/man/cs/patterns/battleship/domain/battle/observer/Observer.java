package uk.ac.man.cs.patterns.battleship.domain.battle.observer;

/**
 * Observer Pattern. Interface that represent the method for observer
 * @author Guillermo Antonio Toro Bayona
 */
public interface Observer {

    /**
     * Method to update the observers
     * @param subject
     */
    public void updateObserver(Subject subject);
}
