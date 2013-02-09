/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import java.util.ArrayList;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 *
 * @author memotoro
 */
public class Player {

    private String name;
    private String type;
    private List<Turn> previousTurns;
    private Board board;

    public Player(String name, String type) {
        this.name = name;
        this.type = type;
        this.previousTurns = new ArrayList<Turn>();
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
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

    public String getType() {
        return type;
    }

    public Turn getLastSuccessfulTurn() {
        int sizePreviousTurns = this.previousTurns.size();
        Turn turn = null;
        int controlStepsBack = 0;
        for (int index = sizePreviousTurns - 1; index >= 0; index--) {
            controlStepsBack++;
            turn = this.previousTurns.get(index);
            if (turn.getShoot().getState() == Constants.SHOOT_STATE_SUCCESSFUL) {
                break;
            } else {
                if (controlStepsBack == Constants.SHOOT_CONTROL_SUCCESSFUL) {
                    break;
                }
            }
        }
        return turn;
    }

    public Turn getLastTurn() {
        if (this.previousTurns.size() > 0) {
            return this.previousTurns.get(this.previousTurns.size() - 1);
        } else {
            return null;
        }
    }
}
