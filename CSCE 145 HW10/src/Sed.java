/**
 * @author Jeffrey Cocklin                    o_0
 * @version 1.0, 17 April 2015 
 * This class creates a simple editor 
 * with the ability to find an replace strings 0_o
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Sed
{
    public static Scanner keyboard = new Scanner(System.in);
    public static Scanner fileScan;

    public static String fileInName;
    public static String fileOutName = "a.out.txt";

    public static PrintWriter write;

    /**
     * openFile
     * 
     * Method opens a file to be read
     * 
     * @return void
     * 
     */
    
    private static void openFile()
    {
        System.out.println("Enter the name of a file: ");
        keyboard.nextLine();
        fileInName = keyboard.nextLine();

        try
        {
            fileScan = new Scanner(new File(fileInName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open file " + fileInName);
            System.exit(0);
        }

    }
    
    /**
     * openFileEdit
     * 
     * Method opens a file to edit it
     * 
     * @return void
     */
    
    private static void openFileEdit()
    {
        try
        {
            write = new PrintWriter(new File(fileOutName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error could not write to file " + fileOutName);
            System.exit(0);
        }

    }
    
    /**
     * findReplace
     * 
     * Method finds and replaces Strings, and then prints
     * the changes to a file.
     * 
     * @param String find, the String to find
     * @param String replace, the String that replaces the 'find' String
     *
     * @return void
     */
    
    public static void findReplace(String find, String replace)
    {
        openFileEdit();
        String line, modLine;

        for (line = fileScan.nextLine(); fileScan.hasNextLine(); line = fileScan
                .nextLine())
        {
            modLine = line.replaceAll(find, replace);
            write.println(modLine);
            write.flush();
        }
        modLine = line.replaceAll(find, replace);
        write.println(modLine);
        write.flush();

        write.close();
        System.out.println("file writting complete.");
    }

    /**
     * find
     * 
     * Method searches for a String, and the prints
     * the line that it is found on.
     * 
     * @param String find, the String to look for
     *
     * @return void
     */
        
    public static void find(String find)
    {
        Scanner lineScan;
        String line, word;

        int lineCount = 0;
        int findCount = 0;
        
        for (line = fileScan.nextLine(); fileScan.hasNextLine(); line = fileScan
                .nextLine())
        {
            lineCount++;

            lineScan = new Scanner(line);

            for (word = lineScan.next(); lineScan.hasNext(); word = lineScan
                    .next())
                if (word.equalsIgnoreCase(find))
                {
                    System.out.println(lineCount + ": " + line);
                    findCount++;
                }

            if (word.equalsIgnoreCase(find))
            {
                System.out.println(lineCount + ": " + line);// check last word
                findCount++;
            }

        }
        if(findCount==0)
            System.out.println("there's not instance of "+find+" in the file");
    }
    
    /**
     * Program that allows users to perform simple
     * editing tasks on .txt documents.
     * 
     */
    
    public static void main(String[] args)
    {

        String toFind, toReplace;

        boolean notCase = true;
        int switchCase;

        System.out.println("Welcome to simple editor");

        while (notCase)
        {
            System.out.println("\n\nselect mode:" +
            		"\n1. Find/Replace\n2. Find \n3. Exit");
            try
            {
                try
                {
                    switchCase = keyboard.nextInt();
                }
                catch (InputMismatchException e)
                {
                    throw e;
                }

                switch (switchCase)
                {
                    case 1:
                        openFile();
                        System.out.println("Enter a word to find: ");
                        toFind = keyboard.nextLine();

                        System.out.println("Enter a word to replace: ");
                        toReplace = keyboard.nextLine();

                        findReplace(toFind, toReplace);
                        break;

                    case 2:
                        openFile();
                        System.out.println("Enter a word to find: ");
                        toFind = keyboard.nextLine();

                        find(toFind);
                        break;
                    
                    case 3:

                        System.out.println("Good bye");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Please choose a mode 1-3");
                        break;

                }

            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter an integer between 1-3");
                keyboard.nextLine();
            }
        }
        System.out.println("Thank you for using simple editor.");

    }

}
