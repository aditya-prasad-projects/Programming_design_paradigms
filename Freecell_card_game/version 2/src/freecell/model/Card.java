package freecell.model;

import java.util.Objects;

/**
 * Represents a card in any card game.
 */
public class Card implements Cards {

  private final CardSuit suit;
  private final CardValue value;
  private final CardColor color;

  /**
   * Used to initialize the card with the suit and value.
   * @param suit represents the suit of the card.
   * @param value represents the value of the card.
   */
  public Card(CardSuit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
    if (this.suit == CardSuit.CLUB || this.suit == CardSuit.SPADE) {
      color = CardColor.BLACK;
    } else {
      color = CardColor.RED;
    }
  }

  /**
   * Returns the suit of the card.
   * @return a enumerated type of CardSuit type.
   */
  @Override
  public CardSuit getSuit() {
    return suit;
  }

  /**
   * Two cards are equal if its suit and value are same.
   * @param o Card to be compared with.
   * @return true if two cards suits and values are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return suit == card.suit
            && value == card.value
            && color == card.color;
  }

  /**
   * HashCode of two cards are similar if their suit and value are same.
   * @return the hashCode for the card object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(suit, value, color);
  }

  /**
   * Returns the value of the card.
   * @return the value of the card.
   */
  @Override
  public CardValue getValue() {
    return value;
  }

  /**
   * Returns the color of the card.
   * @return the color of the card.
   */
  @Override
  public CardColor getColor() {
    return color;
  }

  /**
   * Calling toString on a card object returns its value followed by the suit.
   * @return the cardValue concatenated with the card suit.
   */
  @Override
  public String toString() {
    return value.getValue() + suit.getValue();
  }
}
