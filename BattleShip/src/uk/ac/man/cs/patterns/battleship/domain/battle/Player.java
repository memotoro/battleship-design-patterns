/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author memotoro
 */
public class Player {

    private String name;
    private List<Turn> previousTurns;
    private Board board;

    public Player(String name) {
        System.out.println("Creating Player...");
        this.name = name;
        this.previousTurns = new ArrayList<Turn>();
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Turn> getPreviousTurns() {
        return previousTurns;
    }

    public void setPreviousTurns(List<Turn> previousTurns) {
        this.previousTurns = previousTurns;
    }
}
