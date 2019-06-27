package freecell.model;

import java.util.List;
import java.util.Map;

/**
 * Represents the game state of a Freecell card Game.
 */
public interface GameRepresentation {

  /**
   * Returns the foundation pile.
   * @return the foundation pile.
   */
  Map<Integer, List<Cards>> getFoundationPile();

  /**
   * Sets the foundation pile.
   * @param foundationPile the value to set the current foundation pile.
   */
  void setFoundationPile(Map<Integer, List<Cards>> foundationPile);

  /**
   * A getter for the openPile.
   * @return the openPile.
   */
  Map<Integer, List<Cards>> getOpenPile();

  /**
   * A setter for the openPile.
   * @param openPile The value to set the openPile.
   */
  void setOpenPile(Map<Integer, List<Cards>> openPile);

  /**
   * A getter for the cascadePile.
   * @return the cascadePile.
   */
  Map<Integer, List<Cards>> getCascadePile();

  /**
   * A setter for the cascadePile.
   * @param cascadePile The value to set the cascadePile
   */
  void setCascadePile(Map<Integer, List<Cards>> cascadePile);

  /**
   * Used to set the cascadePileSize.
   * @param c the number of cascadePiles in the game.
   */
  void setCascadePileSize(int c);

  /**
   * Used to set the OpenPileSize.
   * @param c the number of openPiles.
   */
  void setOpenPileSize(int c);

  /**
   * Used to addCards to cascadePile when the game begins.
   * @param c the card to be added.
   * @param pileIndex The index in the cascadePile where the card is to be added.
   */
  void addCardCascadePile(Cards c, int pileIndex);

  /**
   * Getter for the CascadePileSize.
   * @return the number of CascadePiles.
   */
  int getCascadePileSize();

  /**
   * Checks if the game is over.
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();

  /**
   Move a card from the given source pile to the given destination pile, if
   * the move is valid.
   * If a card is taken from a position and put back in the same position, it throws an exception.
   * but the card's position is not changed.
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible {@link
   *                                  PileType})
   * @throws IllegalStateException if a move is attempted before the game has
   *                               starts
   */
  void moveCards(PileType source, int pileNumber, int cardIndex,
                 PileType destination, int destPileNumber)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Used to clear the deck when startGame is called.
   */
  void emptyPiles();


}
