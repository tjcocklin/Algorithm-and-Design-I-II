/**
 * @author Jeffrey Cocklin
 * @version 1.0, 9-12-2015 This Class defines a driver for VideOGame.java, and
 *          VideoGameCollectionManager. java.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class VideoGameCollectionFrontend {

  /**
   * Program searches a file and prints its results to or a specified file.
   */

  public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);

    String readFile = null;
    String printFile = null;

    String name;
    String console;

    ArrayList<VideoGame> gameList = new ArrayList<VideoGame>();
    ArrayList<VideoGame> resultList = new ArrayList<VideoGame>();

    boolean append;
    int userInput = -1;

    do {

      System.out.println("Enter 1 to load the videogame database");
      System.out.println("Enter 2 to search the database");

      System.out.println("Enter 3 to print current results");
      System.out.println("Enter 4 to print current results to file");

      System.out.println("Enter 0 to quit");

      try {
        userInput = keyboard.nextInt();

      } catch (InputMismatchException e) {
        System.out.println();
      } // do nothing switch will take care of it.
      

      switch (userInput) {

        case 0:
          System.out.println("Goodbye");
          break;

        case 1:
          System.out.println("Enter the file name: ");
          keyboard.nextLine();

          readFile = keyboard.nextLine();
          try {
            gameList = VideoGameCollectionManager.readInFile(readFile);
          } catch (FileNotFoundException e) {
            continue;
          }

          break;

        case 2:
          if (readFile != null) {

            System.out.println("Enter the name of the game or '*' for all names:");
            keyboard.nextLine();

            name = keyboard.nextLine();

            System.out.println("Enter the name of the console or '*' for all consoles:");
            console = keyboard.nextLine();

            resultList = VideoGameCollectionManager.searchList(gameList, name, console);

          } else {
            System.out.println("Can carry out search with out a read file");
          }

          break;

        case 3:
          if (readFile != null) {
            for (VideoGame element : resultList) {
              System.out
                  .println("name: " + element.getName() + " console: " + element.getConsole());
            }
            if (resultList.isEmpty()) {
              System.out.println("Sorry no results.");
            }
          } else {
            System.out.println("Can not carry out print no read file");
          }
          break;

        case 4:
          keyboard.nextLine();
          if (readFile != null) {
            System.out.println("Enter the file name to print out:");
            printFile = keyboard.nextLine();

            System.out.println("Append to file (true/false):");
            append = keyboard.nextBoolean();

            if (append == false) {
              try {
                VideoGameCollectionManager.printToFile(resultList, printFile);
              } catch (FileNotFoundException e) {
                continue;
              }
            } else {
              try {
                VideoGameCollectionManager.appendToFile(resultList, printFile);
              } catch (FileNotFoundException e) {
                continue;
              }
            }

          } else {
            System.out.println("Can not carry out print to file results is empty");
          }

          break;

        default:
          System.out.println("Please choose one of the list options.");
          keyboard.nextLine();
          break;
      } // switch end

    } while (userInput != 0);
    keyboard.close();
  } // main end

} // class end
