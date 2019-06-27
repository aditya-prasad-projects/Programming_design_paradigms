import java.util.ArrayList;
import java.util.List;

import freecell.model.FreecellOperations;
import freecell.model.PileType;

/**
 * The class implements a mock of the model. The class is purely used for testing.
 */
public class MockModel implements FreecellOperations {

  private StringBuilder log;
  private List<Double> uniqueCode;

  /**
   * Initialize a stringBuilder log to an empty string builder. Also initializes a unique code to
   * check if the value returned by the model is the same that the controller gets.
   *
   * @param log        stores the log of all the methods being called with their parameters
   * @param uniqueCode used to validate the communication between the controller and model
   */
  public MockModel(StringBuilder log, double... uniqueCode) {
    this.log = log;
    this.uniqueCode = new ArrayList();
    for (double a : uniqueCode) {
      this.uniqueCode.add(a);
    }
  }

  /**
   * Gets a list of different unique codes passed to the object constructor. Also adds to the log
   * that getDeck() was called!.
   *
   * @return list of unique codes.
   */
  @Override
  public List getDeck() {
    log.append("GetDeck() Called!\n");
    List list = new ArrayList();
    list.add(uniqueCode.get(0));
    return list;
  }

  /**
   * Adds to the log the string "startGame() called!" followed by the parameters.
   *
   * @param deck    dummy list
   * @param shuffle dummy value
   * @throws IllegalArgumentException if the deck size is two, throw exception
   */

  @Override
  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {
    log.append("startGame() called!" + " " + deck.toString() + " " + shuffle + "\n");
    if (deck.size() == 2) {
      throw new IllegalArgumentException("Invalid deck");
    }
  }

  /**
   * Adds string "move() called!" to the log followed by the parameters passed.
   *
   * @param source         dummy for the type of the source pile see @link{PileType}
   * @param pileNumber     dummy for the pile number of the given type, starting at 0
   * @param cardIndex      dummy for the index of the card to be moved from the source pile,
   *                       starting at 0
   * @param destination    dummy for the type of the destination pile (see
   * @param destPileNumber dummy for the pile number of the given type, starting at 0
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination
          , int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    log.append("move() called!" + " " + source + " " + pileNumber + " " + cardIndex + "  "
            + destination + " " + destPileNumber + "\n");
    if (cardIndex > 10) {
      throw new IllegalArgumentException("Mock model throwing exception, Invalid CardIndex");
    }
    if (source.equals(PileType.CASCADE) && pileNumber > 7) {
      throw new IllegalArgumentException("Invalid move. Try again. Mock model throwing exception, "
              + "Invalid value in source cascade pile.");
    }
    if (destination.equals(PileType.CASCADE) && destPileNumber > 7) {
      throw new IllegalArgumentException("Invalid move. Try again. Mock model throwing " +
              "exception, Invalid value in destination cascade pile.");
    }
    if (source.equals(PileType.OPEN) && pileNumber > 5) {
      throw new IllegalArgumentException("Invalid move. Try again. Mock model throwing " +
              "exception, Invalid value in source open pile.");
    }
    if (destination.equals(PileType.OPEN) && destPileNumber > 5) {
      throw new IllegalArgumentException("Invalid move. Try again. Mock model throwing " +
              "exception, Invalid value in destination open pile.");
    }
    if (source.equals(PileType.FOUNDATION) && pileNumber > 5) {
      throw new IllegalArgumentException("Invalid move. Try again. Mock model throwing " +
              "exception, Invalid value in source foundation pile.");
    }
    if (destination.equals(PileType.FOUNDATION) && destPileNumber > 5) {
      throw new IllegalArgumentException("Invalid move. Try again. Mock model throwing " +
              "exception, Invalid value in destination foundation pile.");
    }
  }

  /**
   * Dummy method that adds "isGameOver() called!" to the log.
   *
   * @return false always
   */
  @Override
  public boolean isGameOver() {
    log.append("isGameOver() called!\n");
    return false;
  }

  /**
   * Adds "getGameState() called!" to log.
   *
   * @return the first element of the unique code
   */
  @Override
  public String getGameState() {
    log.append("getGameState() called!\n");
    return "" + uniqueCode.get(0).toString();
  }
}
