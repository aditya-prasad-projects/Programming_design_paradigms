import org.junit.Test;

import freecell.model.Card;
import freecell.model.CardColor;
import freecell.model.CardSuit;
import freecell.model.CardValue;
import freecell.model.Cards;

import static org.junit.Assert.assertEquals;

/**
 * The class tests all the methods provided by the Card class.
 */
public class CardsTest {

  private Cards card1;

  /**
   * Test if correct suit is returned.
   */
  @Test
  public void getSuit() {
    assertEquals(CardSuit.SPADE, new Card(CardSuit.SPADE, CardValue.ACE).getSuit());
    assertEquals(CardSuit.CLUB, new Card(CardSuit.CLUB, CardValue.ACE).getSuit());
    assertEquals(CardSuit.HEART, new Card(CardSuit.HEART, CardValue.ACE).getSuit());
    assertEquals(CardSuit.DIAMOND, new Card(CardSuit.DIAMOND, CardValue.ACE).getSuit());
  }

  /**
   * Test if correct value is returned.
   */
  @Test
  public void getValue() {
    assertEquals(CardValue.ACE, new Card(CardSuit.SPADE, CardValue.ACE).getValue());
    assertEquals(CardValue.TWO, new Card(CardSuit.CLUB, CardValue.TWO).getValue());
    assertEquals(CardValue.THREE, new Card(CardSuit.HEART, CardValue.THREE).getValue());
    assertEquals(CardValue.FOUR, new Card(CardSuit.DIAMOND, CardValue.FOUR).getValue());
    assertEquals(CardValue.FIVE, new Card(CardSuit.SPADE, CardValue.FIVE).getValue());
    assertEquals(CardValue.SIX, new Card(CardSuit.CLUB, CardValue.SIX).getValue());
    assertEquals(CardValue.SEVEN, new Card(CardSuit.HEART, CardValue.SEVEN).getValue());
    assertEquals(CardValue.EIGHT, new Card(CardSuit.DIAMOND, CardValue.EIGHT).getValue());
    assertEquals(CardValue.NINE, new Card(CardSuit.SPADE, CardValue.NINE).getValue());
    assertEquals(CardValue.TEN, new Card(CardSuit.CLUB, CardValue.TEN).getValue());
    assertEquals(CardValue.JACK, new Card(CardSuit.HEART, CardValue.JACK).getValue());
    assertEquals(CardValue.QUEEN, new Card(CardSuit.DIAMOND, CardValue.QUEEN).getValue());
    assertEquals(CardValue.KING, new Card(CardSuit.DIAMOND, CardValue.KING).getValue());
  }

  /**
   * Test if correct color is returned.
   */
  @Test
  public void getColor() {
    assertEquals(CardColor.RED, new Card(CardSuit.DIAMOND, CardValue.KING).getColor());
    assertEquals(CardColor.RED, new Card(CardSuit.HEART, CardValue.KING).getColor());
    assertEquals(CardColor.BLACK, new Card(CardSuit.SPADE, CardValue.KING).getColor());
    assertEquals(CardColor.BLACK, new Card(CardSuit.CLUB, CardValue.KING).getColor());
  }

  /**
   * Test if equals works correctly.
   */
  @Test
  public void testEquals() {
    Cards card2;
    Cards card3;
    card1 = new Card(CardSuit.SPADE, CardValue.ACE);
    card2 = new Card(CardSuit.SPADE, CardValue.ACE);
    card3 = new Card(CardSuit.SPADE, CardValue.ACE);

    assertEquals(true, card1.equals(card2));
    assertEquals(true, card2.equals(card1));
    assertEquals(true, card2.equals(card3));
    assertEquals(true, card1.equals(card1));
    assertEquals(true, card3.equals(card1));
    assertEquals(true, card3.equals(card2));
  }

  /**
   * Test if toString works correctly.
   */
  @Test
  public void testToString() {
    for (CardValue value : CardValue.values()) {
      for (CardSuit suit : CardSuit.values()) {
        card1 = new Card(suit, value);
        assertEquals(value.getValue() + suit.getValue(),
                card1.toString());
      }
    }

  }
}