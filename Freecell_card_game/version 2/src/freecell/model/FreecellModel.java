package freecell.model;

import java.util.List;
import java.util.Map;

/**
 * It implements the operations required to run a Freecell game.
 */
public class FreecellModel extends FreecellModelAbstraction {

  /**
   * Used to instantiate the piles in the Freecell game.
   * @param cascadePile represents the cascade pile.
   * @param foundationPile represents the foundation pile.
   * @param openPile represents the openPile.
   */
  private FreecellModel(Map<Integer, List<Cards>> cascadePile, Map<Integer,
          List<Cards>> foundationPile, Map<Integer, List<Cards>> openPile) {
    super(cascadePile,foundationPile,openPile);
  }

  /**
   * Used to build the Freecell game. Foolows the builder pattern.
   * @return a object of builder type.
   */
  public static FreecellModel.FreecellModelBuilder getBuilder() {
    return new FreecellModel.FreecellModelBuilder();
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

  /**
   * Move a card from the given source pile to the given destination pile, if
   * the move is valid.
   * If a card is taken from a position and put back in the same position, it throws an exception.
   * but the card's position is not changed.
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile
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
}
