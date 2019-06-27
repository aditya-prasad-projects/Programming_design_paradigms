package freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import constants.Constants;

/**
 * The class implements the FreecellOperations interface and abstracts the methods that are common
 * to all the Freecell Models. It can also be used to add new operations to the next version of the
 * game. <br> The abstraction was created to make it easier to add new methods to both the Freecell
 * models. As the difference between both the models is only the move method, it made sense to
 * abstract all the common methods. The approach of extending freecellmodel was not taken because in
 * the case of this model being depricated and
 */

public abstract class FreecellModelAbstraction implements FreecellOperations<Cards> {
  GameRepresentation currentState;

  /**
   * Used to instantiate the piles in the Freecell game.
   *
   * @param cascadePile    represents the cascade pile.
   * @param foundationPile represents the foundation pile.
   * @param openPile       represents the openPile.
   */
  FreecellModelAbstraction(Map<Integer, List<Cards>> cascadePile, Map<Integer,
          List<Cards>> foundationPile, Map<Integer, List<Cards>> openPile) {
    currentState = new GameState();
    this.currentState.setOpenPile(openPile);
    this.currentState.setCascadePile(cascadePile);
    this.currentState.setFoundationPile(foundationPile);
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


  @Override
  public boolean isGameOver() {
    return currentState.isGameOver();
  }

  @Override
  public String getGameState() {
    return currentState.toString();
  }

  @Override
  abstract public void move(PileType source,
                            int pileNumber,
                            int cardIndex,
                            PileType destination,
                            int destPileNumber) throws IllegalArgumentException,
          IllegalStateException;
}
