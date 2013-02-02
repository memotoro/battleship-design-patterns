/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import java.util.Date;

/**
 *
 * @author memotoro
 */
public class Turn {

    private Date timeStamp;
    private Shoot shoot;
    private Player player;

    public Turn(Date timeStamp, Player player) {
        this.timeStamp = timeStamp;
        this.player = player;
    }

    public Shoot createShoot(Position position){
        this.shoot = new Shoot(position);
        return this.shoot;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Shoot getShoot() {
        return shoot;
    }

    public void setShoot(Shoot shoot) {
        this.shoot = shoot;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
