/*****************************************************
Name: Alina Zatzick
Course: CS201 - A
Filename: Homework2.java

Battleship game!
This is a one player version of Battleship in which
5 ships are placed randomly on a 10 x 10 board. The
player then enters a coordinate that will be determined
a hit or miss. The player wins when all coordinates containing
ships have been hit and all ships are sunk. The game ends if a
player does not sink all ships in 30 attempts.

*****************************************************/

import java.util.Random;
import java.util.Scanner;

class Battleship {

  String [][] gameBoard;
  int [][] playerBoard;
  int boardSize = 10;
  int rowCoordinate;
  int colCoordinate;

  public void createBoard(){
    //initializes 2D array, gameBoard, that will be updated & printed throughout the game
    gameBoard = new String [boardSize][boardSize];
    for (int c=0; c<boardSize; c++){
      for (int r=0; r<boardSize; r++){
        gameBoard[c][r] = "-";}
  }
}
  public void printBoard(){
    //prints 2d array, gameBoard, with columns and rows coordinates labeled respectively
    char ch = 'A';
    System.out.println("  0\t1\t2\t3\t4\t5\t6\t7\t8\t9\n");
    for (int c=0; c<boardSize; c++){
      System.out.print(ch+ " ");
      ch++;
      for (int r=0; r<boardSize; r++){
          System.out.print(gameBoard[c][r] +"\t");}
      System.out.println();}
  }

  public void createPlayerBoard(){
    //initializes playerBoard to 2d, integer array filled with zeros
    playerBoard = new int [boardSize][boardSize];
    for (int c=0; c<boardSize; c++){
      for (int r=0; r<boardSize; r++){
        playerBoard[c][r] =0;
      }
    }
  }

  public void printPlayerBoard(){
    for (int c=0; c<boardSize; c++){
      for (int r=0; r<boardSize; r++){
        System.out.println(playerBoard[c][r]);
      } System.out.println();
    }
  }

  public void placeShips() {
    //Randomly places 5 ships of different name and size
    //Ships are placed both horizontally and vertically, do not overlap, and do not go off of the 10x10 board
    int carrier = 5;
    int battleship = 4;
    int submarine = 3;
    int destroyer = 3;
    int patrolBoat =2;

    int[] shipArray = {carrier, battleship, submarine, destroyer, patrolBoat};
    for (int i =0; i<shipArray.length; i++){
      int currentShip = shipArray[i];

      while(true){
        Random randomShips = new Random();

        //creating boolean value to exit to beginning of while loop, throughout
        boolean reset = false;
        //creating boolean value to determine if ship placement will be horizontal or vertical
        boolean horizontal = randomShips.nextBoolean();

        int colPlacement = randomShips.nextInt(boardSize);
        int rowPlacement = randomShips.nextInt(boardSize);

        // if there is ship already at coordinate, restart the while loop
        if (playerBoard[rowPlacement][colPlacement] == 1){
          continue;
        }

        //horizontal case
        if (horizontal){
          if((colPlacement + currentShip) >= boardSize){   //if the random integer chosen, plus the size of the ship is greater than 10, RESET
            continue;
          }
          for (int n = colPlacement; n < (colPlacement + currentShip); n++){
             if(playerBoard[rowPlacement][n+1] == 1){   //if column coordinate +1 has ship --> exit to beginning of while loop
               reset = true;
            }
          } if (reset == true){
            continue;
          }
          for (int j = colPlacement; j < (colPlacement +currentShip); j++){ // placement of ship
                  playerBoard[rowPlacement][j+1] = 1;
              }
          }
        //vertical case:
        else{
          if((rowPlacement + currentShip) >= boardSize){
            continue;
          }
          for (int n = rowPlacement; n < (rowPlacement + currentShip) ; n++){
             if (playerBoard[n+1][colPlacement]==1){ //if row coordinate +1 has ship --> exit to beginning of while loop
               reset = true;
            }
          } if (reset == true){
            continue;
          }
            for (int j = rowPlacement; j < (rowPlacement +currentShip); j++){ //placement of ship
                playerBoard[j+1][colPlacement] = 1;
              }
          }
          break;
          }
        }
      }

  public void getCoordinates(){
    //Asks user to input a certain coordinate, storing elements of this input String in column and row variables
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter a coord (eg. E8): ");
    String coordinates = scan.next();

    //using ASCII table values here
    char rowCoord = coordinates.charAt(0);
    rowCoordinate = (Character.getNumericValue(rowCoord) -10);

    String colCoord = coordinates.substring(1);
    colCoordinate = Integer.parseInt(colCoord);
  }

  public boolean determineHit(){
    //Determines if the coordinate inputted has hit a ship, has missed, or has previously been entered
    Scanner scan = new Scanner(System.in);
    boolean hit = true;
    if (gameBoard[rowCoordinate][colCoordinate] != "-"){
      System.out.println("Location already attempted, Try again!");
      hit = false;
      }
    else if(playerBoard[rowCoordinate][colCoordinate] ==1){
        System.out.println("Hit!");
        gameBoard[rowCoordinate][colCoordinate] = "X";
      }
    else{
      System.out.println("Miss, Try again!");
      gameBoard[rowCoordinate][colCoordinate] = "o";
      hit = false;
      }
    return hit;
    }
}

class Homework2{

  public static void main(String[] argv){
    //main function! imports Battleship class and its methods.
    //Prints out certain statements if hit or attempt variables equal certain values (determining if game has been won/lost)
    int attempts = 0;
    int hits = 0;

    System.out.println("\nWelcome to Battleship!\nThere are 5 ships placed in this board.\nGuess their location and sink them all to win. Good luck!\n");

    Battleship b = new Battleship();
    b.createPlayerBoard();
    b.createBoard();
    b.placeShips();

    while(attempts<30 && hits<17) {
      b.printBoard();
      b.getCoordinates();
      boolean result = b.determineHit();
      attempts++;

      if (result==true){
        hits++;}
    }
    if(attempts==30){
      System.out.println("You have attempted to hit your enemy's ship 30 times without success. Game over.");
    }
    else if(hits==30){
      System.out.printf("Congradulations, You Win! You sank all of your enemy's ships in %d attempts\n", attempts);
    }
  }
}
