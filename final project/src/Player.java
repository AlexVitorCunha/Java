/** Application purpose: A class that holds a players name, current points and position
 * as well as setters and getters for all instance variables.
 * Author: Alex Vitor Marques Moreira da Cunha
 * Date: 13/04/2021
 * Time:10PM
 */
public class Player {
    // instance variables
    private String playerName;
    private int points;
    private int position;

    //constructor
    public Player(String playerName, int points, int position) {
        this.playerName = playerName;
        this.points = points;
        this.position = position;
    }

    //setters and getters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
