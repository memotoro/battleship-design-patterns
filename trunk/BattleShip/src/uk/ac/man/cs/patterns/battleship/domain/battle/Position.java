package uk.ac.man.cs.patterns.battleship.domain.battle;

/**
 * Position. Represent a slot in the board with specific coordinates.
 * @author Guillermo Antonio Toro Bayona
 */
public class Position {

    /**
     * Integer with coordinate X
     */
    private Integer coordinateX;
    /**
     * Integer with coordinate Y
     */
    private Integer coordinateY;

    /**
     * Constructor. Received a pair of coordinates.
     * @param coordinateX Integer
     * @param coordinateY Integer
     */
    public Position(Integer coordinateX, Integer coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * Get Coordinate X
     * @return Integer
     */
    public Integer getCoordinateX() {
        return coordinateX;
    }

    /**
     * Set Coordinate X
     * @param coordinateX Integer
     */
    public void setCoordinateX(Integer coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Get Coordinate Y
     * @return Integer
     */
    public Integer getCoordinateY() {
        return coordinateY;
    }

    /**
     * Set Coordinate Y
     * @param coordinateY Integer
     */
    public void setCoordinateY(Integer coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Method that allows to identify object as equals based on the values of its coordinates.
     * @param obj Object to be compared.
     * @return boolean with validation
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.coordinateX != other.coordinateX) {
            return false;
        }
        if (this.coordinateY != other.coordinateY) {
            return false;
        }
        return true;
    }

    /**
     * Method that calculated the hash code of the object.
     * @return Integer with the value of the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.coordinateX;
        hash = 37 * hash + this.coordinateY;
        return hash;
    }
}
