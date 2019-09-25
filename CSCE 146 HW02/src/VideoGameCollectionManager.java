/**
 * @author Jeffrey Cocklin
 * @version 1.0, 9-12-2015 This Class defines the operations for ArrayLists of VideoGame objects.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class VideoGameCollectionManager {

  /**
   * Method reads in a file and creates a list of VideoGame objects.
   * 
   * @param fileName the file to be read.
   * @return An ArrayList of VideoGame objects.
   */

  public static ArrayList<VideoGame> readInFile(String fileName) throws FileNotFoundException {

    Scanner fileScan = null;

    try {
      fileScan = new Scanner(new File(fileName));
    } catch (FileNotFoundException e) {
      System.out.println("Can not open file for reading");
      throw e;
    }

    fileScan.nextLine();// skip the top entry name console
    String line;

    ArrayList<VideoGame> list = new ArrayList<VideoGame>();

    for (line = fileScan.nextLine(); fileScan.hasNext(); line = fileScan.nextLine()) {

      list.add(new VideoGame((line.substring(0, line.indexOf('\t')).toUpperCase()), (line
          .substring(line.indexOf('\t') + 1, line.length()).toUpperCase())));// add line to
                                                                             // VideoGame and
                                                                             // uppercase the string

    }

    list.add(new VideoGame((line.substring(0, line.indexOf('\t')).toUpperCase()), (line.substring(
        line.indexOf('\t') + 1, line.length()).toUpperCase()))); // add the last line as a videoGame

    /*
     * for (VideoGame element: list){
     * System.out.println("name: "+element.getName()+" console: "+element.getConsole()); }
     */// test to see if the array list is filled correctly: check!
    fileScan.close();
    return list;
  }

  /**
   * Method executes a search on a VideoGame ArrrayList.
   * 
   * @param gameList the VideoGame ArrayList to search.
   * @param name the name of the VideoGame to find.
   * @param console the name of the console to find for a VideoGame.
   * @return A VideoGame ArrayList that matches the search criteria.
   */

  public static ArrayList<VideoGame> searchList(ArrayList<VideoGame> gameList, String name,
      String console) {

    ArrayList<VideoGame> results = new ArrayList<VideoGame>();

    if (name.equals("*") && console.equals("*")) {
      results = gameList;
    } else if (name.equals("*")) {
      for (VideoGame element : gameList) {
        if (element.getConsole().contains(console.toUpperCase())) {
          results.add(element);
        }
      }
    } else if (console.equals("*")) {
      for (VideoGame element : gameList) {
        if (element.getName().contains(name.toUpperCase())) {
          results.add(element);
        }
      }
    } else {
      for (VideoGame element : gameList) {
        if (element.getName().contains(name.toUpperCase())
            && element.getConsole().contains(console.toUpperCase())) {
          results.add(element);
        }
      }
    }
    return results;
  }

  /**
   * Method prints the results of a search to File.
   * 
   * @param results a VideoGame ArrayList that contains the results of a search.
   * @param fileName the file to which the user wants to print.
   */

  public static void printToFile(ArrayList<VideoGame> results, String fileName)
      throws FileNotFoundException {
    PrintWriter outPutStream = null;
    try {
      outPutStream = new PrintWriter(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("Can not print to File");
      throw e;
    }

    for (VideoGame element : results) {
      outPutStream.println("name: " + element.getName() + " console: " + element.getConsole());
    }
    outPutStream.close();
  }

  /**
   * Method appends current search results to an existing file.
   * 
   * @param results a VideoGame ArrayList that contains the results of a search.
   * @param fileName the file to which the user wants to append to.
   */

  public static void appendToFile(ArrayList<VideoGame> results, String fileName)
      throws FileNotFoundException {
    PrintWriter outPutStream = null;
    try {
      outPutStream = new PrintWriter(new FileOutputStream(fileName, true));
    } catch (FileNotFoundException e) {
      System.out.println("Can not append to File");
      throw e;
    }

    for (VideoGame element : results) {
      outPutStream.println("name: " + element.getName() + " console: " + element.getConsole());
    }
    outPutStream.close();
  }

}
