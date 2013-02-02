/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.patterns.battleship.utils;

/**
 *
 * @author memotoro
 */
public class Constants {

    public static final int GAME_STATE_STARTING = 0;
    public static final int GAME_STATE_PLAYING = 1;
    public static final int GAME_STATE_FINISHED = -1;
    public static final String GAME_PLAYER_PC = "Pc";
    public static final String GAME_PLAYER_MAN = "Player";
    public static final String GAME_MESSAGE_ACTIONS = "Action: ";
    public static final int GAME_MODE_MAN_VS_PC = 0;
    public static final int GAME_MODE_PC_VS_PC = 1;
    public static final String GAME_PATH_FILE_MESSAGES = "/uk/ac/man/cs/patterns/battleship/view/resources/messages.properties";
    public static final String GAME_PATH_IMAGE_SEA = "src/uk/ac/man/cs/patterns/battleship/view/resources/sea.png";
    public static final String GAME_PATH_IMAGE_HITTED = "src/uk/ac/man/cs/patterns/battleship/view/resources/hitted.png";
    public static final String GAME_PATH_IMAGE_MISSED = "src/uk/ac/man/cs/patterns/battleship/view/resources/missed.png";
    public static final String GAME_PATH_IMAGE_SHIP = "src/uk/ac/man/cs/patterns/battleship/view/resources/ship.png";
    public static final String GAME_COORDS_SEPARATOR = "-";

    public static final int SHIP_STATE_OK = 0;
    public static final int SHIP_STATE_HITTED = 1;
    public static final int SHIP_STATE_DETROYED = -1;
    public static final int SHIP_SIZE_AIRCRAFT = 5;
    public static final int SHIP_SIZE_SUBMARINE = 4;
    public static final int SHIP_SIZE_DESTROYER = 3;
    public static final int SHIP_SIZE_CRUISER = 2;
    public static final int SHIP_SIZE_BOAT = 1;

    

    public static final int SHOOT_STATE_MISSED = 0;
    public static final int SHOOT_STATE_SUCCESSFUL = 1;

    public static final int BOARD_SIZE_WIDTH = 12;
    public static final int BOARD_SIZE_HEIGHT = 12;

    public static final int BOARD_DIRECTION_HORIZONTAL = 0;
    public static final int BOARD_DIRECTION_VERTICAL = 1;


    public static final String CODE_000 = "000";
    public static final String CODE_001 = "001";
    public static final String CODE_002 = "002";
    public static final String CODE_003 = "003";
    public static final String CODE_004 = "004";
    public static final String CODE_005 = "005";
    public static final String CODE_006 = "006";
    public static final String CODE_007 = "007";

}
