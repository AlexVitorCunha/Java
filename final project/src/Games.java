/** Application purpose: A class that holds instance variables for difficulty and current game
 * points, and methods to play 3 games
 *  Author: Alex Vitor Marques Moreira da Cunha
 * Date: 13/04/2021
 * Time: 10PM
 */

import java.util.*;

public class Games {
    // instance variables
    private char difficulty;
    private int points;

    public char getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(char difficulty) {
        this.difficulty = difficulty;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    //constructor
    public Games(char difficulty){
        this.difficulty = difficulty;
        this.points = 0;
    }

    // Guess number of lights and order of lights on
    public int playLightsGame(int currentPoints){
        System.out.println("Lets play the lights game!!");
        System.out.println("***************************");
        System.out.println("On this game you will have to guess the number and position" +
                        "of lights that are on. You have 10 turns to guess it");
        System.out.println("Number of lights and positions:");
        int numOfLights;
        int rounds = 10;
        int rightGuess;
        int currentGuess;
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        if (this.difficulty == 'E')
        {
            System.out.println("0 1");
            System.out.println("\u20DD" + "  " + "\u20DD");
            numOfLights = 2;
        }
        else if (this.difficulty == 'M'){
            System.out.println("0 1 2");
            System.out.println("\u20DD" + "  " + "\u20DD" + "  " + "\u20DD");
            numOfLights = 3;
        }
        else {
            System.out.println("0 1 2 3");
            System.out.println("\u20DD" + "  " + "\u20DD" + "  " + "\u20DD" + "  " + "\u20DD");
            numOfLights = 4;
        }
        int[] answers = new int[numOfLights]; // array of answers
        // Randomly defines if lights are on or off.
        for(int i = 0; i < numOfLights; i++){
            answers[i] = random.nextInt(2);
        }

        //gives the player 10 rounds to guess the light
        do{
            rightGuess = 0;
            //let user decide for each light if they are on or off
            System.out.printf("You have %s rounds left%n",rounds);
            for(int i = 0; i < numOfLights; i++){
                System.out.println("Is light " + i +" on or off? ");
                System.out.println("(0 for off and 1 for on)");
                currentGuess = input.nextInt();
                //update write answers count
                if(answers[i] == currentGuess)
                    rightGuess++;
            }
            System.out.println("You've got " + rightGuess + " right guesses.");
            //returns number of right guesses
            if(rightGuess == numOfLights) {
                System.out.println("Nice! you've got all the lights right \uD83D\uDC4D");
                System.out.printf("You'll get %s extra points%n", rounds * 10);
                System.out.println("***************************");
                return currentPoints + rounds * 10;
            }
            System.out.println("***************************");
            rounds--;
        }while(rounds > 0);
        System.out.println("🙁 You didn't guess all the lights 🙁");
        System.out.println("No points earned");
        System.out.println("*************************************");
        return currentPoints;
    }

    // Rock, paper, scissor
    public int playRPS(int currentPoints){
        System.out.println("Lets play rock, paper, scissors!!");
        System.out.println("*********************************");
        System.out.println("In this game, you have to win by choosing rock, paper or scissors." +
                        "Rock beats scissors, scissors beats paper and paper beats rock");
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        //array of possible symbols
        String[] symbols = {"✊", "✋", "✌"};
        int wins = 0;
        int winsNeeded;
        int rounds;

        //defines number of rounds and wins needed depending on the difficulty chosen
        if (this.difficulty == 'E')
        {
            rounds = 3;
            winsNeeded = 2;
            System.out.println("_______________________");
            System.out.println("| Win 2 out of 3 games |");
            System.out.println("-----------------------");
        }
        else
        if (this.difficulty == 'M')
        {
            rounds = 4;
            winsNeeded = 3;
            System.out.println("_______________________");
            System.out.println("| Win 3 out of 4 games |");
            System.out.println("-----------------------");
        }
        else {
            rounds = 5;
            winsNeeded = 4;
            System.out.println("_______________________");
            System.out.println("| Win 4 out of 5 games |");
            System.out.println("-----------------------");
        }

        //plays while there are still rounds left
        while(rounds > 0){
            System.out.printf("There are %s rounds left%n", rounds);
            System.out.println("_______    ________    _________");
            System.out.println("   \u270A          \u270B          \u270C  ");
            System.out.println(" 0.rock     1.paper    2.scissor ");
            System.out.println("-------    --------    ---------");
            int choice = input.nextInt();
            int choicePC = random.nextInt(3);
            System.out.println(symbols[choice] + " X " + symbols[choicePC]);
            if(choice == choicePC){
                System.out.println("\uD83D\uDE10 DRAW! \uD83D\uDE10");
            }
            else if(choice == choicePC + 1 || choice == choicePC - 2){
                System.out.println("\uD83D\uDE01 WINNER! \uD83D\uDE01");
                wins++;
            }else {
                System.out.println("🙁 LOSER! 🙁");
            }
            System.out.println("*********************************");
            rounds--;
        }
        if(wins >= winsNeeded){
            points = wins*10 + 100;
        }
        else{
            points = wins*10;
        }
        System.out.printf("You'll get %s points!%n", points);
        System.out.println("******************");
        return currentPoints + points;
    }

    // Tic Tac Toe against PC
    public int playTTT(int currentPoints){
        System.out.println("Lets play Tic Tac Toe!!");
        System.out.println("You're playing against the PC");
        Scanner input = new Scanner(System.in);
        boolean playing = true;
        //array of tic tac toe positions
        String[][] positions = {{"0","1","2"},
                                {"3","4","5"},
                                {"6","7","8"}};
        String turn = "X";
        //maximum amount of turns
        int turns = 9;
        int choice;
        String winner = "";
        int i = 0;
        int j = 0;
        Random random = new Random();
        while(playing)
        {
            //prints the current board of available positions
            if(turn.equals("X")) {
                System.out.println("It's your turn");
                System.out.println("Choose a position:");
                System.out.printf("%s | %s | %s%n", positions[0][0], positions[0][1], positions[0][2]);
                System.out.println("----------");
                System.out.printf("%s | %s | %s%n", positions[1][0], positions[1][1], positions[1][2]);
                System.out.println("----------");
                System.out.printf("%s | %s | %s%n", positions[2][0], positions[2][1], positions[2][2]);
                choice = input.nextInt();
            }
            else { // chooses random position number when is the PC turn
                choice = random.nextInt(9);
            }

            //defines the position that the user want chose
            switch (choice){
                case 0:
                    i = j = 0;
                    break;
                case 1:
                    i = 0;
                    j = 1;
                    break;
                case 2:
                    i = 0;
                    j = 2;
                    break;
                case 3:
                    i = 1;
                    j = 0;
                    break;
                case 4:
                    i = 1;
                    j = 1;
                    break;
                case 5:
                    i = 1;
                    j = 2;
                    break;
                case 6:
                    i = 2;
                    j = 0;
                    break;
                case 7:
                    i = 2;
                    j = 1;
                    break;
                case 8:
                    i = 2;
                    j = 2;
                    break;
                }
            //checks if the position chosen is available, if not the current player has to try again
            try{
                Integer.parseInt(positions[i][j]);
                if(turn.equals("X")){
                    positions[i][j] = "X";
                    turn = "O";
                }
                else{
                    positions[i][j] = "O";
                    turn = "X";
                }
                turns--;
            }
            catch(NumberFormatException e){ //shows a message if the user chose a position not available
                if(turn.equals("X"))
                    System.out.println("Position not available, try again");
            }

            //check if the current player won
            for(int k = 0; k < 3; k++){
                if(positions[k][0].equals(positions[k][1]) && positions[k][1].equals(positions[k][2]))
                    winner = positions[k][0];
                else if(positions[0][k].equals(positions[1][k]) && positions[1][k].equals(positions[2][k]))
                    winner = positions[0][k];
            }
            if((positions[0][0].equals(positions[1][1]) && positions[1][1].equals(positions[2][2])) ||
                    (positions[0][2].equals(positions[1][1]) && positions[1][1].equals(positions[2][0])))
                winner = positions[1][1];

            //check if anybody won or there are no turns left to stop playing
            if(!winner.equals("") || turns == 0)
                playing = false;

            }
        //gives the user 100 points if they win
        if(winner.equals("X")){
            System.out.println("Winner! You get 100 points!");
            points = 100;
        }
        //takes away 50 points if they lose
        else if(winner.equals("O")){
            System.out.println("Loser! You lose 50 points!");
            points = -50;
        }
        else{
            System.out.println("Draw! No points earned!");
            points = 0;
        }
        return currentPoints + points;
    }

    // Tic Tac Toe against another player
    public int playTTT(int currentPoints, Player obj){
        System.out.println("Lets play Tic Tac Toe!!");
        System.out.printf("You're playing against %s%n",obj.getPlayerName());
        Scanner input = new Scanner(System.in);
        boolean playing = true;
        //array of tic tac toe positions
        String[][] positions = {{"0","1","2"},
                {"3","4","5"},
                {"6","7","8"}};
        String turn = "X";
        //maximum amount of turns
        int turns = 9;
        int choice;
        String winner = "";
        int i = 0;
        int j = 0;
        while(playing)
        {
            //prints the current board of available positions
            System.out.printf("      %s's turn    %n", turn);
            System.out.println("******************");
            System.out.println("Choose a position:");
            System.out.printf("%s | %s | %s%n", positions[0][0], positions[0][1], positions[0][2]);
            System.out.println("----------");
            System.out.printf("%s | %s | %s%n", positions[1][0], positions[1][1], positions[1][2]);
            System.out.println("----------");
            System.out.printf("%s | %s | %s%n", positions[2][0], positions[2][1], positions[2][2]);
            choice = input.nextInt();

            //defines the position that the user wants to choose
            switch (choice){
                case 0:
                    i = j = 0;
                    break;
                case 1:
                    i = 0;
                    j = 1;
                    break;
                case 2:
                    i = 0;
                    j = 2;
                    break;
                case 3:
                    i = 1;
                    j = 0;
                    break;
                case 4:
                    i = 1;
                    j = 1;
                    break;
                case 5:
                    i = 1;
                    j = 2;
                    break;
                case 6:
                    i = 2;
                    j = 0;
                    break;
                case 7:
                    i = 2;
                    j = 1;
                    break;
                case 8:
                    i = 2;
                    j = 2;
                    break;
            }
            //checks if the position chosen is available, if not the current player has to try again
            try{
                Integer.parseInt(positions[i][j]);
                if(turn.equals("X")){
                    positions[i][j] = "X";
                    turn = "O";
                }
                else{
                    positions[i][j] = "O";
                    turn = "X";
                }
                turns--;
            }
            catch(NumberFormatException e){ //shows a message if the user chose a position not available
                System.out.println("Position not available, try again");
            }

            //check if the current player won
            for(int k = 0; k < 3; k++){
                if(positions[k][0].equals(positions[k][1]) && positions[k][1].equals(positions[k][2]))
                    winner = positions[k][0];
                else if(positions[0][k].equals(positions[1][k]) && positions[1][k].equals(positions[2][k]))
                    winner = positions[0][k];
            }
            if((positions[0][0].equals(positions[1][1]) && positions[1][1].equals(positions[2][2])) ||
                    (positions[0][2].equals(positions[1][1]) && positions[1][1].equals(positions[2][0])))
                winner = positions[1][1];

            //check if anybody won or there are no turns left to stop playing
            if(!winner.equals("") || turns == 0)
                playing = false;
        }
        //gives the current user 100 points if they win and -50 to the loser
        if(winner.equals("X")){
            System.out.println("Winner! You get 100 points!");
            System.out.printf("%s loses 50 points!%n",obj.getPlayerName());
            points = 100;
            obj.setPoints(obj.getPoints() - 50);
        }
        //takes away 50 points if current user loses and gives 100 to the winner
        else if(winner.equals("O")){
            System.out.println("Loser! You lose 50 points!");
            System.out.printf("%s gets 100 points!%n",obj.getPlayerName());
            points = -50;
            obj.setPoints(obj.getPoints() + 100);
        }
        else{
            System.out.println("Draw! No points earned!");
            points = 0;
        }
        return currentPoints + points;
    }
}
