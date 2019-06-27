import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import freecell.model.Card;
import freecell.model.CardSuit;
import freecell.model.CardValue;
import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests FreecellModel class.
 */
public class FreecellModelTest {

  /**
   *Tests invalid moves in a deck.
   */
  @Test
  public void testCard() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .build();
    test.startGame(test.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", test.getGameState());
    String s = test.getGameState();
    try {
      test.move(PileType.CASCADE, 5, 6, PileType.FOUNDATION, 2);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only last card can be moved");
    }
    assertEquals(s,test.getGameState());
    try {
      test.move(PileType.CASCADE,1,3,PileType.FOUNDATION,2);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only last card can be moved");
    }
    assertEquals(s,test.getGameState());
  }

  /**
   * Tests normal operations in a Freecell game.
   */
  @Test
  public void testMoveOpen() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .build();
    assertEquals("",test.getGameState());
    test.startGame(test.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦",test.getGameState());
    test.move(PileType.CASCADE, 7, 5, PileType.OPEN, 2);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦", test.getGameState());
    test.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦", test.getGameState());
    test.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    assertEquals("F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣",test.getGameState());
    test.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    try {
      test.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only Ace can be added to empty foundation");
    }
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    try {
      test.move(PileType.CASCADE, 5, 5, PileType.OPEN, 2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Open pile can have only 1 card");
    }
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    try {
      test.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 3);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only last card can be moved");
    }
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    try {
      test.move(PileType.CASCADE, 6, 5, PileType.OPEN, 1);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Open pile can have only 1 card");
    }
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    try {
      test.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 6);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    try {
      test.move(PileType.CASCADE, 2, 5, PileType.CASCADE, 5);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Only last card can be moved", e.getMessage());
    }
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
  }

  /**
   * Tests if the game is initialised properly after startGame is called.
   */
  @Test
  public void testInitialization() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .cascades(4)
            .build();
    test.startGame(test.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♥, 8♥, Q♥, 3♣, 7♣, J♣, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♥, 5♥, 9♥, K♥, 4♣, 8♣, Q♣, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♥, 6♥, 10♥, A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♥, 7♥, J♥, 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦", test.getGameState());
  }

  /**
   *Tests getGameState before the game has begun.
   */
  @Test
  public void test1() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .opens(5)
            .build();
    assertEquals("",test.getGameState());
    test.startGame(test.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦",test.getGameState());
  }

  /**
   * Tests duplicate cards in deck with shuffle.
   */
  @Test
  public void test2() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .build();
    List<Card> cards = test.getDeck();
    Card c = new Card(CardSuit.SPADE, CardValue.ACE);
    cards.remove(c);

    Card c1 = new Card(CardSuit.SPADE, CardValue.FIVE);
    cards.add(c1);
    try {
      test.startGame(cards,true);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "The deck should always have 52 unique cards!");
    }
    assertEquals(test.getGameState(), "");
    try {
      test.startGame(cards,false);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "The deck should always have 52 unique cards!");
    }
    assertEquals(test.getGameState(), "");
  }

  /**
   * Tests moving to full open pile.
   */
  @Test
  public void test3() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .build();
    assertEquals("", test.getGameState());
    test.startGame(test.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", test.getGameState());
    test.move(PileType.CASCADE, 7, 5, PileType.OPEN, 2);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦",test.getGameState());
    test.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: A♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    test.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: A♦\n" +
            "O3: 9♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    test.move(PileType.CASCADE, 0, 5, PileType.OPEN, 3);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: A♦\n" +
            "O3: 9♦\n" +
            "O4: 2♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    test.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 3);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4: A♦\n" +
            "O1: 10♦\n" +
            "O2:\n" +
            "O3: 9♦\n" +
            "O4: 2♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    test.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4: A♦\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4: 2♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    test.move(PileType.FOUNDATION, 3, 0, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♦\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4: 2♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", test.getGameState());
    try {
      test.move(PileType.OPEN, 3, 0, PileType.OPEN, 0);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Open pile can have only 1 card");
    }
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♦\n" +
            "O2: 10♦\n" +
            "O3: 9♦\n" +
            "O4: 2♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣",test.getGameState());
  }

  /**
   * Tests invalid cascade and invalid open piles.
   */
  @Test
  public void test4() {
    try {
      FreecellOperations test = FreecellModel
              .getBuilder()
              .opens(-1)
              .build();
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pile size cannot be lower than 1");
    }
    try {
      FreecellOperations test = FreecellModel
              .getBuilder()
              .opens(0)
              .build();
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pile size cannot be lower than 1");
    }
    try {
      FreecellOperations test = FreecellModel
              .getBuilder()
              .cascades(-1)
              .build();
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pile size cannot be lower than 4");
    }
    try {
      FreecellOperations test = FreecellModel
              .getBuilder()
              .cascades(3)
              .build();
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pile size cannot be lower than 4");
    }
    try {
      FreecellOperations test = FreecellModel
              .getBuilder()
              .cascades(0)
              .build();
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Pile size cannot be lower than 4");
    }
  }

  /**
   * Tests IllegalStateException.
   */
  @Test
  public void test5() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .build();
    try {
      test.move(PileType.OPEN, 3, 0, PileType.OPEN, 0);
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Start the game before making a move");
    }
  }


  /**
   * Tests if the deck returned by getGame returns a valid deck.
   */
  @Test
  public void test6() {
    FreecellOperations test = FreecellModel
            .getBuilder()
            .build();
    List<Card> cards = new ArrayList<>();
    for (CardSuit suit : CardSuit.values()) {
      for (CardValue value : CardValue.values()) {
        cards.add(new Card(suit, value));
      }
    }
    if (cards.size() != Constants.DECK_SIZE || !cards.containsAll(test.getDeck())) {
      fail();
    }
  }

  /**
   * Tests the game till it finishes.
   */
  @Test
  public void testFullGame() {
    FreecellOperations gameObject = FreecellModel
            .getBuilder()
            .opens(5)
            .build();
    assertEquals(false, gameObject.isGameOver());
    assertEquals("", gameObject.getGameState());
    gameObject.startGame(gameObject.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,0);
    assertEquals("F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0, 6, PileType.OPEN,1);
    assertEquals("F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 1, 6, PileType.OPEN,2);
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 1, 5, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 6, 5, PileType.OPEN,3);
    assertEquals("F1: A♦, 2♦, 3♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4: 8♦\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 2, 6, PileType.CASCADE,6);
    assertEquals("F1: A♦, 2♦, 3♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4: 8♦\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 2, 5, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4: 8♦\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 3, 6, PileType.OPEN,4);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4: 8♦\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 3, 5, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4: 8♦\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4: 8♦\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4: 8♦\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4:\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♦\n" +
            "O3: J♦\n" +
            "O4:\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: J♦\n" +
            "O4:\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 2, 0, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 6, 5, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 4, 0, PileType.FOUNDATION,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 2, 4, PileType.OPEN,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥, A♣\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 3, 4, PileType.OPEN,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥, 2♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 4, 4, PileType.OPEN,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥, 2♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 5, 4, PileType.OPEN,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥, 2♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 6, 4, PileType.OPEN,4);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥, 2♣\n" +
            "C5: 5♠, K♠, 8♥, 3♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥, 3♣\n" +
            "C6: 6♠, A♥, 9♥, 4♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 4, 3, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥, 4♣\n" +
            "C7: 7♠, 2♥, 10♥, 5♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 5, 3, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥, 5♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 6, 3, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥, 6♣", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 1, 4, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♣\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: J♣\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 2, 0, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4: Q♣\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5: K♣\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 4, 0, PileType.FOUNDATION,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥, 9♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 5, 2, PileType.OPEN,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥\n" +
            "C7: 7♠, 2♥, 10♥\n" +
            "C8: 8♠, 3♥, J♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 6, 2, PileType.OPEN,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥\n" +
            "C7: 7♠, 2♥\n" +
            "C8: 8♠, 3♥, J♥",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 7, 2, PileType.OPEN,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥\n" +
            "C7: 7♠, 2♥\n" +
            "C8: 8♠, 3♥",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0, 3, PileType.OPEN,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥\n" +
            "C2: 2♠, 10♠, 5♥, K♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥\n" +
            "C7: 7♠, 2♥\n" +
            "C8: 8♠, 3♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 1, 3, PileType.OPEN,4);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠, 4♥\n" +
            "C2: 2♠, 10♠, 5♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠, A♥\n" +
            "C7: 7♠, 2♥\n" +
            "C8: 8♠, 3♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠, 4♥\n" +
            "C2: 2♠, 10♠, 5♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠\n" +
            "C7: 7♠, 2♥\n" +
            "C8: 8♠, 3♥", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 6, 1, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠, 4♥\n" +
            "C2: 2♠, 10♠, 5♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠, 3♥",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 7, 1, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠, 4♥\n" +
            "C2: 2♠, 10♠, 5♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0, 2, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠, 5♥\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 1, 2, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠, 6♥\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 2, 2, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠, 7♥\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠, 8♥\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 4, 2, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F4:\n" +
            "O1: 9♥\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♥\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: J♥\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 2, 0, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4: Q♥\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5: K♥\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 4, 0, PileType.FOUNDATION,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0, 1, PileType.OPEN,0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠\n" +
            "C2: 2♠, 10♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 1, 1, PileType.OPEN,1);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠\n" +
            "C2: 2♠\n" +
            "C3: 3♠, J♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 2, 1, PileType.OPEN,2);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠\n" +
            "C2: 2♠\n" +
            "C3: 3♠\n" +
            "C4: 4♠, Q♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 3, 1, PileType.OPEN,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5:\n" +
            "C1: A♠\n" +
            "C2: 2♠\n" +
            "C3: 3♠\n" +
            "C4: 4♠\n" +
            "C5: 5♠, K♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 4, 1, PileType.OPEN,4);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1: A♠\n" +
            "C2: 2♠\n" +
            "C3: 3♠\n" +
            "C4: 4♠\n" +
            "C5: 5♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2: 2♠\n" +
            "C3: 3♠\n" +
            "C4: 4♠\n" +
            "C5: 5♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3: 3♠\n" +
            "C4: 4♠\n" +
            "C5: 5♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4: 4♠\n" +
            "C5: 5♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5: 5♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7: 7♠\n" +
            "C8: 8♠",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8: 8♠", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "O1:\n" +
            "O2: 10♠\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:", gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: J♠\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 2, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4: Q♠\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:",gameObject.getGameState());
    assertEquals(false,gameObject.isGameOver());
    gameObject.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:",gameObject.getGameState());
    assertEquals(false, gameObject.isGameOver());
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5: K♠\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:",gameObject.getGameState());
    gameObject.move(PileType.OPEN, 4, 0, PileType.FOUNDATION,3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:", gameObject.getGameState());
    assertEquals(true, gameObject.isGameOver());
    gameObject.move(PileType.FOUNDATION,3, 12, PileType.OPEN, 0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "O1: K♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:", gameObject.getGameState());
    assertEquals(false, gameObject.isGameOver());
    String s = gameObject.getGameState();
    //Check Invalid move when cardIndex is wrong in CascadePile and it is empty.
    try {
      gameObject.move(PileType.CASCADE, 0,0, PileType.OPEN,1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only last card can be moved");
    }
    assertEquals(s,gameObject.getGameState());
    gameObject.move(PileType.OPEN, 0,0, PileType.FOUNDATION, 3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:", gameObject.getGameState());
    assertEquals(true, gameObject.isGameOver());
    gameObject.move(PileType.FOUNDATION,3, 12, PileType.CASCADE, 0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: K♠\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:", gameObject.getGameState());
    assertEquals(false, gameObject.isGameOver());
    gameObject.move(PileType.CASCADE, 0,0, PileType.FOUNDATION, 3);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:", gameObject.getGameState());
    assertEquals(true, gameObject.isGameOver());
    gameObject.startGame(gameObject.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", gameObject.getGameState());
  }
}
