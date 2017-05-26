/**
 * @author Jeffrey Cocklin o_0
 * @version 1.0, 18 February 2015 
 *  This program emulates Mine Sweeper 0_o
 */

import java.util.Random;
import java.util.Scanner;

public class MineWalker
{
    public static final int ICE_CREAM_VALUE = 2;

    public static final int BOMB_VALUE = 1;
    public static final int SAFE_SPACE = 0;

    public static final int BOARD_HEIGHT = 10;
    public static final int BOARD_LENGTH = BOARD_HEIGHT;

    public static final int NUMBER_OF_BOMBS = BOARD_HEIGHT;
    public static final int RANGE_OF_RAND = BOARD_HEIGHT;

    static Random random = new Random();
    static int iceCreamX, iceCreamY, bombX, bombY, playerX, playerY;

    static Scanner keyboard = new Scanner(System.in);

    static int[][] iceCream = new int[BOARD_HEIGHT][BOARD_LENGTH];
    static int[][] bombs = new int[BOARD_HEIGHT][BOARD_LENGTH];
   
    static int[][] board = new int[BOARD_HEIGHT][BOARD_LENGTH];

    /**
    *placeIceCream
    *
    *method randomly decides where the ice cone will be, and 
    *ensures it will not be in coordinate(0,0). 
    *
    *@return void
    */
        
    public static void placeIceCream()
    {
        iceCreamX = random.nextInt(RANGE_OF_RAND);
        iceCreamY = random.nextInt(RANGE_OF_RAND);

        for (int i = 0; i < iceCream.length; i++)
            for (int j = 0; j < iceCream.length; j++)
                iceCream[i][j] = 0;

        while (iceCreamX == 0 && iceCreamY == 0)
        {
            iceCreamX = random.nextInt(RANGE_OF_RAND);
            iceCreamY = random.nextInt(RANGE_OF_RAND);
        }

        iceCream[iceCreamY][iceCreamX] = ICE_CREAM_VALUE;
    }

    /**
    *placeMines
    *
    *Method randomly assigns places to the mines ensuring that,
    *mines do not get placed on the starting point, the icecream,
    *or other mines.
    *
    *@return void
    */
    
    public static void placeMines()
    {

        for (int i = 0; i < BOARD_HEIGHT; i++)
            for (int j = 0; j < BOARD_LENGTH; j++)
                bombs[i][j] = SAFE_SPACE;

        for (int k = 0; k < NUMBER_OF_BOMBS; k++)
        {
            bombX = random.nextInt(RANGE_OF_RAND);
            bombY = random.nextInt(RANGE_OF_RAND);

            while ((bombX == SAFE_SPACE && bombY == SAFE_SPACE)
                    || (bombX == iceCreamX && bombY == iceCreamY)
                    || bombs[bombY][bombX] == 1)
            {
                bombX = random.nextInt(RANGE_OF_RAND);
                bombY = random.nextInt(RANGE_OF_RAND);

            }

            bombs[bombY][bombX] = BOMB_VALUE;

        }

    }

    /**
    * setPosition
    *
    *Method sets the starting point of the player at (0,0)
    *
    *@return void
    */
    
    public static void setPosition()
    {
        playerX = SAFE_SPACE;
        playerY = SAFE_SPACE;
    }

    /**
    *drawBoard
    *
    *Displays the game board in '_', the player's location via'X', 
    *and the iceCream via'^'
    *
    *@return void
    */
    
    public static void drawBoard()
    {
        for (int i = 0; i < BOARD_HEIGHT; i++)
        {
            for (int j = 0; j < BOARD_LENGTH; j++)
            {
                if (i == iceCreamY && j == iceCreamX)
                    System.out.print("^");

                else if (i == playerY && j == playerX)
                    System.out.print("X");

               /* 
                  else if(bombs[i][j]==1 ) System.out.print("*");
                 */// Reveals mines
                else
                    System.out.print("_");
            }

            System.out.println();

        }
    }

    /**
    *move
    *
    *Prompts the user to move, and gives feed back if the move is illegal,
    *results in a loss, or results in a win.
    *
    *@return void
    */
    
    public static void move()
    {
        int move;

        while ((bombs[playerY][playerX] != 1)
                && (iceCream[playerY][playerX] != 2))
        {
            System.out.println("Enter either a -1, 0, or 1 in the X" +
                    " or 9 to quit");
            move = keyboard.nextInt();

            switch (move)
            {
            case -1:

                playerX -= 1;

                if (playerX < 0)
                {
                    playerX += 1;

                    System.out.println("Out of bounds X, try again");
                    continue;
                }

                break;

            case 0:
                playerX += 0;
                break;

            case 1:

                playerX += 1;

                if (playerX > (BOARD_LENGTH - 1))
                {
                    playerX -= 1;

                    System.out.println("Out of bounds X, try again");

                }

                break;

            case 9:
                System.out.println("Thank you for playing, goodbye.");
                System.exit(0);
                break;

            default:
                System.out.println("This is not an avialable move, "
                        + "please try again.");
                continue;

            }

            System.out.println("Enter either a -1, 0, or 1 in the Y");
            move = keyboard.nextInt();

            switch (move)
            {
            case -1:

                playerY -= 1;

                if (playerY < 0)
                {
                    playerY += 1;
                    System.out.println("Out of bounds Y, try again");

                }

                break;

            case 0:
                playerY += 0;
                break;

            case 1:

                playerY += 1;

                if (playerY > (BOARD_HEIGHT - 1))
                {
                    playerY -= 1;
                    System.out.println("Out of bounds Y, try again");
                    continue;
                }

                break;

            default:
                System.out.println("This is not an avialable move, "
                        + "please try again.");
                continue;

            }
            drawBoard();
        }

        if (bombs[playerY][playerX] == 1)
            System.out.println("\nBoom! Dead!");
        
        else if (iceCream[playerY][playerX] == 2)
            System.out.println("\nYOU MADE IT CONGRATULATIONS!!");
    }

    /**
    *startGame
    *
    *Initializes a game of mineWalker.
    *
    *@return void
    */
    
    public static void startGame()
    {
        placeIceCream();
        placeMines();

        setPosition();
        drawBoard();
    }

    /**
    * program plays a game of mine walker, if the player loses or
    * wins the game, the player is asked if they wish to continue.
    *
    *@return void
    */
    
    public static void main(String[] args)
    {
        int cont = 0;

        System.out.println("Welcome to Mine Walker.  Get the ice cream cone "
                + "and avoid the mines");

        do
        {
            startGame();
            move();
            
            System.out.println("would you like to play again? yes=1, no=0\n\n");
            cont = keyboard.nextInt();
            
        } while (cont == 1);

        System.out.println("Goodbye.");

    }

}
