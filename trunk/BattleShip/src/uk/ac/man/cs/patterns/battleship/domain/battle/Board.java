package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.RandomUtil;
import uk.ac.man.cs.patterns.battleship.domain.ships.Ship;
import java.util.ArrayList;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Observer;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Subject;
import uk.ac.man.cs.patterns.battleship.domain.ships.ShipFactory;

/**
 * Class that represent a board. A board is one of the main concepts in the game. Is a collection of position with ships in them.
 * @author Guillermo Antonio Toro Bayona
 */
public class Board extends Subject implements Observer {

    /**
     * List of Ships
     */
    private List<Ship> ships;
    /**
     * Integer that represents the number of ships available in the board.
     */
    private Integer shipsAvailable;
    /**
     * List of positions in the board, attacked 
     */
    private List<Position> positions;
    private List<Position> positionsVisited;

    /**
     * Constructor.Initialise the board with all its elements.
     */
    public Board() {
        // Initialise the lists.
        this.ships = new ArrayList<Ship>();
        this.positions = new ArrayList<Position>();
        this.positionsVisited = new ArrayList<Position>();
        // Initialise the board
        this.initializeBoard();
        // Create the Ships
        this.createShips();
        // Localize the ships in the board.
        this.localizeShipsInBoard();
    }

    /**
     * Method to initialise the board.
     */
    private void initializeBoard() {
        // Loop for coordinates Y in the board
        for (int y = 0; y < Constants.BOARD_SIZE_HEIGHT; y++) {
            // Loop for coordinates X in the board
            for (int x = 0; x < Constants.BOARD_SIZE_WIDTH; x++) {
                // Create positions and add to the board.
                Position position = new Position(x, y);
                this.positions.add(position);
            }
        }
    }

    /**
     * Create ships in the board
     */
    private void createShips() {
        // Create Ships
        this.ships.add(ShipFactory.createShip(Constants.SHIP_NAME_AIRCRAFT));
        this.ships.add(ShipFactory.createShip(Constants.SHIP_NAME_SUBMARINE));
        this.ships.add(ShipFactory.createShip(Constants.SHIP_NAME_DESTROYER));
        this.ships.add(ShipFactory.createShip(Constants.SHIP_NAME_CRUISER));
        this.ships.add(ShipFactory.createShip(Constants.SHIP_NAME_BOAT));
        // Register observer
        for (Ship ship : this.ships) {
            ship.registerObserver(this);
        }
        this.shipsAvailable = this.ships.size();
    }

    /**
     * Method that localise each ship in the board with specific positions
     */
    private void localizeShipsInBoard() {
        List<Position> positionsOccupied = new ArrayList<Position>();
        // Loop for each ships
        for (Ship ship : this.ships) {
            // Validation of ship alocated
            boolean shipAllocated = false;
            // List of possible positios for the ships
            List<Position> possiblePositions = new ArrayList<Position>();            
            while (!shipAllocated) {
                // Take a random number to determine the direction.
                Integer randomPosition = RandomUtil.generateRandom(2);
                // Take a random X coordinate Y coordinate
                Integer x = RandomUtil.generateRandom(Constants.BOARD_SIZE_WIDTH);
                Integer y = RandomUtil.generateRandom(Constants.BOARD_SIZE_HEIGHT);
                // Loop against the ship size
                for (int i = 0; i < ship.getSize(); i++) {
                    // Create a initial position
                    Position position = null;
                    // Validate the random direction and Increase the coordinate
                    if (randomPosition == Constants.BOARD_DIRECTION_HORIZONTAL) {
                        position = new Position(x + i, y);
                    } else if (randomPosition == Constants.BOARD_DIRECTION_VERTICAL) {
                        position = new Position(x, y + i);
                    }
                    // Validate if the positions is valid
                    if (this.positions.contains(position) && !positionsOccupied.contains(position)) {
                        // Add to the possible positions
                        possiblePositions.add(position);
                        shipAllocated = true;
                    } else {
                        // Initialise the list as new list
                        possiblePositions = new ArrayList<Position>();
                        shipAllocated = false;
                        break;
                    }
                }
            }
            // Set the positions occupied by the ship.
            positionsOccupied.addAll(possiblePositions);
            ship.setPositionsOccupied(possiblePositions);
        }
    }

    /**
     * Method that validate if one position is valid in the board and is not attacked.
     * @param positionToValidate Position to validate
     * @return boolean with validation result.
     */
    public boolean validatePosition(Position positionToValidate) {
        // If the position is in the board and is not attacked.
        return (this.positions.contains(positionToValidate)
                && !this.positionsVisited.contains(positionToValidate));
    }

    /**
     * Method that validate a shoot in the board
     * @param positionToValidate Position to validate
     * @return boolean with validation result.
     */
    public boolean validateShootPosition(Position positionToValidate) {
        // If the position is valid
        if (this.validatePosition(positionToValidate)) {
            // Delete the position from the available positions in he board.
            this.positions.remove(positionToValidate);
            // Add the position in the attacked positions.
            this.positionsVisited.add(positionToValidate);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that execute and validate if a shoot was successful in the board
     * @param positionToValidate Position to validate
     * @return boolean with validation result.
     */
    public boolean executeAndValidateShoot(Position positionToValidate) {
        // Validation of the shoot
        boolean validationShootSuccessful = false;
        for (Ship ship : this.ships) {
            // Each ship validate if the position is being occupied by it.
            if (ship.validatePositionAttacked(positionToValidate)) {
                validationShootSuccessful = true;
                break;
            }
        }
        return validationShootSuccessful;
    }

    /**
     * Method that return an available position in the board
     * @return Position available
     */
    public Position getAvailablePosition() {
        // Take a number randomly
        int index = RandomUtil.generateRandom(this.positions.size());
        Position positionAvailable = this.positions.get(index);
        // Create a position with those coordinates and return it.
        return new Position(positionAvailable.getCoordinateX(), positionAvailable.getCoordinateY());
    }

    /**
     * Method to validate position occupied by a ship
     * @param positionReceived Position
     * @return boolean with the validation result
     */
    public boolean validatePositionsOccupied(Position positionReceived) {
        boolean validation = false;
        // Loop for each ship and validate the position received
        for (Ship ship : this.ships) {
            if (ship.validatePositionOccupied(positionReceived)) {
                validation = true;
                break;
            }
        }
        return validation;
    }

    /**
     * Get the ships available
     * @return Integer
     */
    public Integer getShipsAvailable() {
        return shipsAvailable;
    }

    /**
     * Get list of ships
     * @return List of ships.
     */
    public List<Ship> getShips() {
        return ships;
    }

    /**
     * Observer Pattern. Method to update the observers
     * @param subject
     */
    public void update(Subject subject) {
        // Validate observer ship
        if (subject instanceof Ship) {
            // Validate the state of the ship
            if (((Ship) subject).getState() == Constants.SHIP_STATE_DETROYED) {
                // Reduce the number of the ships available.
                this.shipsAvailable--;
            }
            // Notify observers
            this.notifyObservers();
        }
    }
}
