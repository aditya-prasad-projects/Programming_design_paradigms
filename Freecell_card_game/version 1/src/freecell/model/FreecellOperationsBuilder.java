package freecell.model;

/**
 * Represents the builder interface for a ga,e of Freecell.
 */
public interface FreecellOperationsBuilder {

  /**
   * Used to set the number of CascadePiles..
   * It should have a minimum size of 4.
   * @param c the number of cascadePiles to be created.
   * @return the builder object with the specified number of cascade piles.
   */
  FreecellOperationsBuilder cascades(int c);

  /**
   * Used to set the number of openPiles.
   * It should have a minimum size of 1.
   * @param o the number of openPiles to be created.
   * @return the builder object with the specified number of open piles.
   */
  FreecellOperationsBuilder opens(int o);

  /**
   * Used to return a object of type FreecellOperations.
   * @param <K> the type of card used in the game.
   * @return a object of type FreecellOperations.
   */
  <K> FreecellOperations<K> build();
}