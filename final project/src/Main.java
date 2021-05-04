/** Application purpose: A game that consists of 3 different games where a user can play solo
 * or with friends. The game keeps going until the user asks to stop. A pointing system is given
 * to each player.
 *  Author: Alex Vitor Marques Moreira da Cunha
 * Date: 13/04/2021
 * Time: 10PM
 */

import java.util.*;
import java.lang.*;
import java.util.Random;


public class Main {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        GeneralMethods.pressAnyKeyToContinue(); //user needs to press any key to start game
        int numOfPlayers =  GeneralMethods.numOfPlayers(); //user decide number of players
        System.out.println("***************************");
        Player[] players = new Player[numOfPlayers]; // array with all the players

        //creates a Player object for each player of the game
        for (int i = 0; i < numOfPlayers ; i++) {
            System.out.printf("Player %s name:",i + 1);
            String name = input.next();
            players[i] = new Player(name,0, 1);
        }
        System.out.println("***************************");
        char difficulty  = GeneralMethods.chooseDifficulty(); // lets user decide the difficulty of the game
        Games games = new Games(difficulty);
        var playing = 'P';

        //plays the game while user wants to keep playing
        do {
            System.out.println("      *Choose a game:*     ");
            System.out.println("***************************");
            System.out.println("* 1. Light Sequence       *");
            System.out.println("* 2. Rock, Paper, Scissor *");
            System.out.println("* 3. Tic-Tac-Toe          *");
            System.out.println("* 4. Random               *");
            System.out.println("***************************");
            int currentGame = input.nextInt();
            //chooses a random game if user inputs 4 or more
            if(currentGame >= 4)
                currentGame = random.nextInt(3) + 1;
                //starts a game depending on what the user have chosen
                for(int i = 0; i < players.length; i++){
                    System.out.printf("   %s's turn%n   ",players[i].getPlayerName());
                    switch (currentGame){
                        case 1:
                            players[i].setPoints(games.playLightsGame(players[i].getPoints()));
                            break;
                        case 2:
                            players[i].setPoints(games.playRPS(players[i].getPoints()));
                            break;
                        case 3:
                            System.out.println("Would you like to play against who?");
                            for(int j = 0; j < players.length; j++){
                                if(j != i)
                                    System.out.printf("%s. %s%n", j, players[j].getPlayerName());
                            }
                            System.out.printf("%s. PC%n",players.length);
                            int enemy = input.nextInt();
                            if(enemy == players.length)
                                players[i].setPoints(games.playTTT(players[i].getPoints()));
                            else
                                players[i].setPoints(games.playTTT(players[i].getPoints(), players[enemy]));
                            break;
                    }
                }
            GeneralMethods.orderPositions(players);
            GeneralMethods.printScoreBoard(players);
            System.out.println("Keep playing or quit?");
            System.out.println("P or Q");
            playing = Character.toUpperCase(input.next().charAt(0));
        }while(playing != 'Q');
        System.out.println("*   Final Result    *");
        GeneralMethods.printScoreBoard(players);
        System.out.println("Thank you for playing!");
    }
}


