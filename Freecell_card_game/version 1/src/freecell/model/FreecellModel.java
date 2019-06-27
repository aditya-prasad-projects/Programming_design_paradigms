package freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import constants.Constants;

/**
 * It implements the operations required to run a Freecell game.
 */
public class FreecellModel implements FreecellOperations<Cards> {
  private GameRepresentation currentState;


  /**
   * Used to instantiate the piles in the Freecell game.
   * @param cascadePile represents the cascade pile.
   * @param foundationPile represents the foundation pile.
   * @param openPile represents the openPile.
   */
  private FreecellModel(Map<Integer, List<Cards>> cascadePile, Map<Integer,
          List<Cards>> foundationPile, Map<Integer, List<Cards>> openPile) {
    currentState = new GameState();
    this.currentState.setOpenPile(openPile);
    this.currentState.setCascadePile(cascadePile);
    this.currentState.setFoundationPile(foundationPile);
  }

  /**
   * Used to build the Freecell game. Foolows the builder pattern.
   * @return a object of builder type.
   */
  public static FreecellModelBuilder getBuilder() {
    return new FreecellModelBuilder();
  }

  /**
   * A static builder class,
   * that follows the builder pattern to instantiate the object of FreeCell class.
   */
  public static class FreecellModelBuilder implements FreecellOperationsBuilder {

    private GameRepresentation currentState;

    /**
     * Used to initialise the gameRepresentation object.
     */
    private FreecellModelBuilder() {
      currentState = new GameState();
    }

    @Override
    public FreecellOperationsBuilder cascades(int c) {
      currentState.setCascadePileSize(c);
      return this;
    }

    @Override
    public FreecellOperationsBuilder opens(int o) {
      currentState.setOpenPileSize(o);
      return this;
    }

    @Override
    public FreecellOperations build() {
      return new FreecellModel(currentState.getCascadePile(), currentState.getFoundationPile(),
              currentState.getOpenPile());
    }
  }

  @Override
  public List<Cards> getDeck() {
    List<Cards> cards = new ArrayList<>();
    for (CardSuit suit : CardSuit.values()) {
      for (CardValue value : CardValue.values()) {
        cards.add(new Card(suit, value));
      }
    }
    return cards;
  }

  @Override
  public void startGame(List<Cards> deck, boolean shuffle) throws IllegalArgumentException {
    if (null == deck) {
      throw new IllegalArgumentException("The passed deck can't be null");
    }
    if (deck.size() != Constants.DECK_SIZE || !deck.containsAll(getDeck())) {
      throw new IllegalArgumentException("The deck should always have 52 unique cards!");
    }
    if (shuffle) {
      Collections.shuffle(deck);
    }
    currentState.emptyPiles();
    for (int i = 0; i < 52; i++) {
      currentState.addCardCascadePile(deck.get(i), (i) % currentState.getCascadePileSize());
    }
  }

  /**
   * Move a card from the given source pile to the given destination pile, if
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
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    currentState.moveCards(source, pileNumber, cardIndex, destination, destPileNumber);
  }

  @Override
  public boolean isGameOver() {
    return currentState.isGameOver();
  }

  @Override
  public String getGameState() {
    return currentState.toString();
  }
}
