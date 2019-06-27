package freecell.model;

/**
 * An enumerated data type that represents the valid cardValue.
 * card value has two properties, its value and the order in the deck.
 */
public enum CardValue {
  ACE("A",1), TWO("2",2), THREE("3",3), FOUR("4",4), FIVE("5",5), SIX("6",6), SEVEN("7",7),
  EIGHT("8",8), NINE("9",9), TEN("10",10), JACK("J",11), QUEEN("Q",12), KING("K",13);
  private final String value;
  private final int order;

  /**
   * Initialises the card value with its order.
   * @param i the card value.
   * @param order the card order.
   */
  CardValue(String i, int order) {
    this.value = i;
    this.order = order;
  }

  /**
   * A getter for the order of the cards.
   * @return the order of the card.
   */
  public int getOrder() {
    return order;
  }

  /**
   * A getter for the card value.
   * @return the value of the card.
   */
  public String getValue() {
    return value;
  }
}
