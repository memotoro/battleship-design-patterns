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
 *
 * @author memotoro
 */
public class NearbySearching extends PositionSearching {

    private Player playerAttacekd;
    
    public NearbySearching(Position positionHelper, Player playerAttacekd) {
        super(positionHelper);
        this.playerAttacekd = playerAttacekd;
    }

    public Position searchPositionToAttack() {
        List<Position> temporalPositions = new ArrayList<Position>();
        List<Position> possiblePositions = new ArrayList<Position>();
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX()+1, getPositionHelper().getCoordinateY()));
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX()-1, getPositionHelper().getCoordinateY()));
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX(), getPositionHelper().getCoordinateY()+1));
        temporalPositions.add(new Position(getPositionHelper().getCoordinateX(), getPositionHelper().getCoordinateY()-1));
        for(Position possiblePosition : temporalPositions){
            if(this.playerAttacekd.getBoard().validatePosition(possiblePosition)){
                possiblePositions.add(possiblePosition);
            }
        }
        Position temporalPosition =  null;
        if(possiblePositions.size()>0){
            Integer index = RandomUtil.generateRandom(possiblePositions.size());
            temporalPosition = possiblePositions.get(index);
        }
        return temporalPosition;
    }
}
