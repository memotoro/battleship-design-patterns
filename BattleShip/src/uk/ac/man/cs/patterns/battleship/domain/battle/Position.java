/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

/**
 *
 * @author memotoro
 */
public class Position {

    private int coordinateX;
    private int coordinateY;

    public Position(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        stringBuffer.append(this.coordinateX);
        stringBuffer.append(",");
        stringBuffer.append(this.coordinateY);
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.coordinateX;
        hash = 37 * hash + this.coordinateY;
        return hash;
    }
}
