package freecell.model;

/**
 * Represents a card in a card game.
 */
public interface Cards {

  /**
   * A getter for the suit of the card.
   * @return the suit for the card.
   */
  CardSuit getSuit();

  /**
   * A getter for the value of the card.
   * @return the value of the card.
   */
  CardValue getValue();

  /**
   * A getter for the color of the card.
   * @return the color of the card.
   */
  CardColor getColor();
}
