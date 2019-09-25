/**
 * @author Jeffrey Cocklin
 * @version 1.0, 9-12-2015 This Class defines a VideoGame.
 * 
 */

public class VideoGame {

  String name;
  String console;

  /**
   * Mutator method.
   * 
   * @return the name of the VideoGame object.
   */

  public String getName() {
    return this.name;
  }

  /**
   * Mutator method.
   * 
   * @return the console of the VideoGame object.
   */

  public String getConsole() {
    return this.console;
  }

  /**
   * Default Constructor.
   */

  public VideoGame() {
    this.name = "blankName";
    this.console = "blankConsole";
  }

  /**
   * Parameterized Constructor.
   * 
   * @param name the name of the VideoGame.
   * @param console the console of the VideoGame.
   */

  public VideoGame(String name, String console) {
    this.name = name;
    this.console = console;
  }
}
