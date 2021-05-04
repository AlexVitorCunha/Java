/** Application purpose: A class with three static method that can be used by other applications,
 * including press any key to continue, choose number of players, difficulty level, order players
 * and print score board.
 *  Author: Alex Vitor Marques Moreira da Cunha
 * Date: 13/04/2021
 * Time: 10PM
 */

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralMethods {

    //A starting method that starts the game only after the user presses enter
    public static void pressAnyKeyToContinue(){
        System.out.println(" ______________________________");
        System.out.println("| Press Enter key to continue. |");
        System.out.println(" ------------------------------");
        try{
            System.in.read();
        } catch(Exception ignored) { }
    }

    //A method that allows the user to choose the number of players
    public static int numOfPlayers() {
        int numPlayers = 0;
        boolean retry = true;
        //asks the user the number of players until a valid number is input
        while(retry) {
            try{
                System.out.printf("Choose number of Players:");
                Scanner input = new Scanner(System.in);
                numPlayers = input.nextInt();
                retry = false; // break the loop;
            }catch(InputMismatchException e){
                System.out.println("Invalid choice, try again...");
            }
        }
        return numPlayers;
    }

    //A method that allows the user to choose the difficulty of the games played
    public static char chooseDifficulty(){
        Scanner input = new Scanner(System.in);
        System.out.println("E = Easy" + " M = Medium" + " H = Hard");
        System.out.printf("Choose difficulty level: ");
        char difficulty = Character.toUpperCase(input.next().charAt(0));
        //asks the user for a difficulty level until a valid input
        while(difficulty != 'E' && difficulty != 'M' && difficulty != 'H'){
            System.out.println("Wrong input! Try again...");
            System.out.println("E = Easy" + " M = Medium" + " H = Hard");
            System.out.printf("Choose difficulty level: ");
            difficulty = Character.toUpperCase(input.next().charAt(0));
        }
        return difficulty;
    }

    //A method that helps define the users current position
    public static void orderPositions(Player[] players){
        int n = 0;
        int numOfPlayers = players.length;
        Integer[] currentPoints = new Integer[numOfPlayers];
        for(int i=0; i < numOfPlayers; i++){
            currentPoints[i] = players[i].getPoints();
        }
        Arrays.sort(currentPoints);
        do{
            while(n < numOfPlayers - 1 && currentPoints[n] == currentPoints[n+1]){
                n++;
            }
            for( Player player: players){
                if (player.getPoints() == currentPoints[n])
                    player.setPosition(numOfPlayers - n);
            }
            n++;
        }while(n < numOfPlayers);
    }


    //A function that prints out the current score board
    public static void printScoreBoard(Player[] players){
        System.out.println("   Score Board   ");
        System.out.println("   ***********   ");
        int i = 1; //current number of player that needs to print
        int pos = 1; // current position to print
        while(i <= players.length){
            for(Player player: players){
                if(pos == player.getPosition()){
                    System.out.printf("%s. %s: %s points %n", player.getPosition(), player.getPlayerName(),player.getPoints());
                    i++;
                }
            }
            pos++;
        }
    }
}
