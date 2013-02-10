/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.utils;

/**
 * Class that contains all the constants used in the application.
 * @author Guillermo Antonio Toro Bayona
 */
public class Constants {

    /**
     * Game.
     */
    /**
     * States of the game.
     */
    public static final Integer GAME_STATE_PLAYING = 0;
    public static final Integer GAME_STATE_FINISHED = 1;
    /**
     * Names and types of the players.
     */
    public static final String GAME_PLAYER_NAME_PC = "Pc";
    public static final String GAME_PLAYER_NAME_HUMAN = "Player";
    public static final String GAME_PLAYER_TYPE_PC = "PcPlayer";
    public static final String GAME_PLAYER_TYPE_HUMAN = "HumanPlayer";
    /**
     * Paths to resources and images in the game.
     */
    public static final String GAME_PATH_FILE_MESSAGES = "/uk/ac/man/cs/patterns/battleship/view/resources/messages.properties";
    public static final String GAME_PATH_IMAGE_SEA = "src/uk/ac/man/cs/patterns/battleship/view/resources/sea.png";
    public static final String GAME_PATH_IMAGE_HITTED = "src/uk/ac/man/cs/patterns/battleship/view/resources/hitted.png";
    public static final String GAME_PATH_IMAGE_MISSED = "src/uk/ac/man/cs/patterns/battleship/view/resources/missed.png";
    public static final String GAME_PATH_IMAGE_SHIP = "src/uk/ac/man/cs/patterns/battleship/view/resources/ship.png";
    public static final String GAME_TEXT_SEPARATOR = "-";
    public static final String GAME_TEXT_OPEN_BRAKET = "[";
    public static final String GAME_TEXT_CLOSE_BRAKET = "]";
    /**
     * Ship.
     */
    /**
     * States of the ship.
     */
    public static final Integer SHIP_STATE_OK = 0;
    public static final Integer SHIP_STATE_ATTACKED = 1;
    public static final Integer SHIP_STATE_DETROYED = 2;
    /**
     * Sizes of the ship.
     */
    public static final Integer SHIP_SIZE_AIRCRAFT = 5;
    public static final Integer SHIP_SIZE_SUBMARINE = 4;
    public static final Integer SHIP_SIZE_DESTROYER = 3;
    public static final Integer SHIP_SIZE_CRUISER = 2;
    public static final Integer SHIP_SIZE_BOAT = 1;
    public static final String SHIP_NAME_AIRCRAFT = "Air-Craft";
    public static final String SHIP_NAME_SUBMARINE = "Submarine";
    public static final String SHIP_NAME_DESTROYER_1 = "Destroyer-1";
    public static final String SHIP_NAME_DESTROYER_2 = "Destroyer-2";
    public static final String SHIP_NAME_CRUISER_1 = "Cruiser-1";
    public static final String SHIP_NAME_CRUISER_2 = "Cruiser-2";
    public static final String SHIP_NAME_BOAT_1 = "Boat-1";
    public static final String SHIP_NAME_BOAT_2 = "Boat-2";
    /**
     * Shoot.
     */
    /**
     * States of the shoots.
     */
    public static final Integer SHOOT_STATE_MISSED = 0;
    public static final Integer SHOOT_STATE_SUCCESSFUL = 1;
    /**
     * Control how many previous turns are checked with successful state.
     */
    public static final Integer SHOOT_CONTROL_SUCCESSFUL = 4;
    /**
     * Board.
     */
    public static final Integer BOARD_SIZE_WIDTH = 12;
    public static final Integer BOARD_SIZE_HEIGHT = 12;
    /**
     * Directions to localise the ships.
     */
    public static final Integer BOARD_DIRECTION_HORIZONTAL = 0;
    public static final Integer BOARD_DIRECTION_VERTICAL = 1;
    /**
     * Codes for Exceptions.
     */
    public static final String CODE_000 = "000";
    public static final String CODE_001 = "001";
    public static final String CODE_002 = "002";
    public static final String CODE_003 = "003";
    public static final String CODE_004 = "004";
    public static final String CODE_005 = "005";
    public static final String CODE_006 = "006";
    public static final String CODE_007 = "007";
    public static final String CODE_008 = "008";
    public static final String CODE_009 = "009";
    public static final String CODE_010 = "010";
    public static final String CODE_011 = "011";
}
