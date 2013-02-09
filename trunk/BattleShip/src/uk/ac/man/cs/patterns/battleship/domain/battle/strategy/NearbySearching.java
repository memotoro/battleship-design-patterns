/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import java.util.ArrayList;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.utils.RandomUtil;

/**
 * Nearby Searching is a concrete strategy to look for position near one successful shoot.
 * This class create possible positions to attack and select one randomly.
 * Strategy Pattern.
 * @author Guillermo Antonio Toro Bayona
 */
public class NearbySearching extends PositionSearching {

    /**
     * Player to be attacked. Is necessary to have access to its board.
     */
    private Player playerAttacekd;

    /**
     * Constructor.
     * Received a helper position and the player to be attacked.
     * @param positionHelper
     * @param playerAttacked
     */
    public NearbySearching(Position positionHelper, Player playerAttacekd) {
        super(positionHelper);
        this.playerAttacekd = playerAttacekd;
    }

    /**
     * Concrete implementation for search positions to attack.
     * Inside this method possible positions are created based on the position helper.
     * One position is selected randomly from the possible options.
     * If there are no possible options, return null.
     * @return Position
     */
    public Position searchPositionToAttack() {
        // Create list from temporal positions and possible positions
        List<Position> temporalPositions = new ArrayList<Position>();
        List<Position> possiblePositions = new ArrayList<Position>();
        // Create possible positions modifying one step in every direction from the position helper.
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX() + 1, getPositionHelper().getCoordinateY()));
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX() - 1, getPositionHelper().getCoordinateY()));
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX(), getPositionHelper().getCoordinateY() + 1));
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX(), getPositionHelper().getCoordinateY() - 1));
        // Iterate the possible positions
        for (Position possiblePosition : temporalPositions) {
            // Ask to the board if the possible position is valid
            if (this.playerAttacekd.getBoard().validatePosition(possiblePosition)) {
                // If valid, add to the possible positions.
                possiblePositions.add(possiblePosition);
            }
        }
        // Create a variable to return the selected position
        Position temporalPosition = null;
        // Validate the size of the list of the possible positions.
        if (possiblePositions.size() > 0) {
            // Take randomly one number and take the possible position with that index.
            Integer index = RandomUtil.generateRandom(possiblePositions.size());
            temporalPosition = possiblePositions.get(index);
        }
        // Return the possible position to attack.
        return temporalPosition;
    }
}
