package freecell.model;

/**
 * Represents a enumerated type for the card suit.
 */
public enum CardSuit {
  SPADE('\u2660'), HEART('\u2665'), CLUB('\u2663'), DIAMOND('\u2666');

  private final char value;

  /**
   * Initialises the card suit.
   * @param i the value of the suit.
   */
  CardSuit(char i) {
    this.value = i;
  }

  /**
   * A getter for the value of the cardSuit.
   * @return the value of the cardSuit.
   */
  public char getValue() {
    return value;
  }
}
