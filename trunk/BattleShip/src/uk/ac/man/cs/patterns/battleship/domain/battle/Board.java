/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.RandomUtil;
import uk.ac.man.cs.patterns.battleship.domain.ships.Submarine;
import uk.ac.man.cs.patterns.battleship.domain.ships.Cruiser;
import uk.ac.man.cs.patterns.battleship.domain.ships.Destroyer;
import uk.ac.man.cs.patterns.battleship.domain.ships.Ship;
import uk.ac.man.cs.patterns.battleship.domain.ships.AirCraft;
import java.util.ArrayList;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.domain.ships.Boat;

/**
 *
 * @author memotoro
 */
public class Board {

    private List<Ship> ships;
    private int shipsAvailable;
    private List<Position> positions;
    private List<Position> positionsVisited;
    private List<Position> positionsOccupied;

    public Board() {
        this.ships = new ArrayList<Ship>();
        this.positions = new ArrayList<Position>();
        this.positionsVisited = new ArrayList<Position>();
        this.positionsOccupied = new ArrayList<Position>();
        this.initializeBoard();
        this.createShips();
        this.localizeShipsInBoard();
    }

    private void initializeBoard() {
        System.out.println("Creating Board...");
        for (int y = 0; y < Constants.BOARD_SIZE_HEIGHT; y++) {
            for (int x = 0; x < Constants.BOARD_SIZE_WIDTH; x++) {
                Position position = new Position(x, y);
                this.positions.add(position);
            }
        }
    }

    private void createShips() {
        System.out.println("Creating Ships...");
        this.ships.add(new AirCraft());
        this.ships.add(new Boat());
        this.ships.add(new Cruiser());
        this.ships.add(new Destroyer());
        this.ships.add(new Submarine());
        this.shipsAvailable = this.ships.size();
    }

    private void localizeShipsInBoard() {
        System.out.println("Localizing Ships...");        
        for (Ship ship : this.ships) {
            boolean shipAllocated = false;
            List<Position> possiblePositions = new ArrayList<Position>();
            while (!shipAllocated) {
                int randomPosition = RandomUtil.generateRandom(2);
                int x = RandomUtil.generateRandom(Constants.BOARD_SIZE_WIDTH);
                int y = RandomUtil.generateRandom(Constants.BOARD_SIZE_HEIGHT);
                for (int i = 0; i < ship.getSize(); i++) {
                    Position position = new Position(x, y);
                    if (randomPosition == Constants.BOARD_DIRECTION_HORIZONTAL) {
                        position.setCoordinateX(x + i);
                    } else if (randomPosition == Constants.BOARD_DIRECTION_VERTICAL) {
                        position.setCoordinateY(y + i);
                    }
                    if (this.positions.contains(position) && !positionsOccupied.contains(position)) {
                        possiblePositions.add(position);
                        shipAllocated = true;
                    } else {
                        shipAllocated = false;
                        possiblePositions = new ArrayList<Position>();
                        break;
                    }
                }
            }
            this.positionsOccupied.addAll(possiblePositions);
            ship.setPositionsOccupied(possiblePositions);
        }
    }

    public boolean validateShootPosition(Position positionToValidate) {
        if (this.positions.contains(positionToValidate)
                && !this.positionsVisited.contains(positionToValidate)) {
            this.positions.remove(positionToValidate);
            this.positionsVisited.add(positionToValidate);
            return true;
        } else {
            return false;
        }
    }

    public boolean validateShootSuccessful(Position positionToValidate) {
        boolean validationShootSuccessful = false;
        for (Ship ship : this.ships) {
            if (ship.validatePositionAttacked(positionToValidate)) {
                validationShootSuccessful = true;
                if (ship.getState() == Constants.SHIP_STATE_DETROYED) {
                    this.shipsAvailable--;
                }
                break;
            }
        }
        return validationShootSuccessful;
    }

    public Position availablePosition() {
        int index = RandomUtil.generateRandom(this.positions.size());
        Position positionAvailable = this.positions.get(index);
        return new Position(positionAvailable.getCoordinateX(), positionAvailable.getCoordinateY());
    }

    public int getShipsAvailable() {
        return shipsAvailable;
    }

    public void setShipsAvailable(int shipsAvailable) {
        this.shipsAvailable = shipsAvailable;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Position> getPositionsOccupied() {
        return positionsOccupied;
    }

    public void setPositionsOccupied(List<Position> positionsOccupied) {
        this.positionsOccupied = positionsOccupied;
    }
}
