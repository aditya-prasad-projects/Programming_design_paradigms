package freecell.model;

import java.util.List;
import java.util.Map;

/**
 * Used to create a model, that can move multiple cards at the same time.
 */
public class FreecellMultiMoveModel extends FreecellModelAbstraction {

  FreecellMultiMoveModel(
          java.util.Map<Integer, List<Cards>> cascadePile, Map<Integer,
          List<Cards>> foundationPile, Map<Integer, List<Cards>> openPile) {
    super(cascadePile, foundationPile, openPile);
  }

  /**
   * Used to build the Freecell game. Follows the builder pattern.
   *
   * @return a object of builder type.
   */
  public static FreecellMultiMoveModel.FreecellModelBuilder getBuilder() {
    return new FreecellMultiMoveModel.FreecellModelBuilder();
  }

  /**
   * A static builder class, that follows the builder pattern to instantiate the object of FreeCell
   * class.
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
      return new FreecellMultiMoveModel(currentState.getCascadePile(),
              currentState.getFoundationPile(),
              currentState.getOpenPile());
    }
  }

  /**
   * It can move multiple cards at the same time, from cascade pile to another cascade pile.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible {@link PileType})
   * @throws IllegalStateException    if a move is attempted before the game has starts
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    currentState.multiMove(source, pileNumber, cardIndex, destination, destPileNumber);
  }
}
