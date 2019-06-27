import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import freecell.model.Card;
import freecell.model.CardSuit;
import freecell.model.CardValue;
import freecell.model.Cards;
import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * A class to test the different operations provided by the FreecellModel.
 */

public class FreecellModelTest2 {

  private FreecellOperations testShuffled;
  private FreecellOperations testOrdered;
  private FreecellOperations testNotStarted;

  /**
   * Used to setup the different model objects being used for testing.
   */
  @Before
  public void setup() {
    testNotStarted = FreecellModel
            .getBuilder()
            .build();

    testShuffled = FreecellModel
            .getBuilder()
            .build();
    testShuffled.startGame(testShuffled.getDeck(), true);

    testOrdered = FreecellModel
            .getBuilder()
            .build();
    testOrdered.startGame(testOrdered.getDeck(), false);

  }

  /**
   * Test starting a game again resets the game.
   */
  @Test
  public void testStartGameAgain() {
    testShuffled.startGame(testShuffled.getDeck(), false);
    assertEquals(testOrdered.getGameState(), testShuffled.getGameState());
  }

  /**
   * Test if shuffled and unshuffled games start differently.
   */
  @Test
  public void testStartStateDiff() {
    for (int i = 0; i < 10000; i++) {
      assertNotEquals(testOrdered.getGameState(), testShuffled.getGameState());
      testOrdered.startGame(testOrdered.getDeck(), true);
      assertNotEquals(testOrdered.getGameState(), testShuffled.getGameState());
    }
  }

  /**
   * Test if a new started game is over or not.
   */
  @Test
  public void testGameOverFalse() {
    assertEquals(false, testShuffled.isGameOver());
    assertEquals(false, testOrdered.isGameOver());
  }

  /**
   * Test game state before start game.
   */
  @Test
  public void testEmptyGameState() {
    assertEquals("", testNotStarted.getGameState());
  }

  /**
   * Test game state after start game.
   */
  @Test
  public void testGameState() {
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());
  }

  /**
   * Test invalid move cascade to cascade.
   */
  @Test
  public void testCascadeCascade() {
    try {
      testOrdered.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }
    try {
      testOrdered.move(PileType.CASCADE, 0, 5, PileType.CASCADE, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Only last card can be moved", e.getMessage());
    }
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());
  }

  /**
   * Test start game with shuffled deck.
   */
  @Test
  public void testStartShuffled() {
    List deck = testShuffled.getDeck();

    testShuffled.startGame(deck, true);
    //test if open and foundation piles are empty
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\nC1:", testShuffled.getGameState().substring(0, 35));

    //test if all cards are present in the game
    Cards card;
    for (CardSuit cardSuit : CardSuit.values()) {
      for (CardValue cardValue : CardValue.values()) {
        card = new Card(cardSuit, cardValue);
        //check if every card is present in the game.
        assertEquals(true, testShuffled.getGameState().substring(31)
                .contains(card.toString()));
        //check if only one occurrence of the card is present.
        assertEquals(true, testShuffled.getGameState().substring(31)
                .indexOf(card.toString()) == testShuffled.getGameState().substring(31)
                .lastIndexOf(card.toString()));

      }
    }
    String[] array = testShuffled.getGameState().split(",|:");
    List<Integer> a = new ArrayList();
    for (int i = 0; i < array.length; i++) {
      if (array[i].contains("C")) {
        a.add(i);
      }
    }
    a.add(array.length - 1);
    //test cascade pile 1 has 7 cards
    assertEquals(7, a.get(1) - a.get(0));
    //test cascade pile 2 has 7 cards
    assertEquals(7, a.get(2) - a.get(1));
    //test cascade pile 3 has 7 cards
    assertEquals(7, a.get(3) - a.get(2));
    //test cascade pile 4 has 7 cards
    assertEquals(7, a.get(4) - a.get(3));
    //test cascade pile 5 has 6 cards
    assertEquals(6, a.get(5) - a.get(4));
    //test cascade pile 6 has 6 cards
    assertEquals(6, a.get(6) - a.get(5));
    //test cascade pile 7 has 6 cards
    assertEquals(6, a.get(7) - a.get(6));
    //test cascade pile 8 has 6 cards
    assertEquals(6, a.get(8) - a.get(7));
  }

  /**
   * Check if a deck is valid.
   */
  @Test
  public void testValidDeck() {
    List<Card> deck = testOrdered.getDeck();
    //check number of cards.
    assertEquals(52, deck.size());
    //check if all cards are present in the deck.
    Cards card;
    for (CardSuit cardSuit : CardSuit.values()) {
      for (CardValue cardValue : CardValue.values()) {
        card = new Card(cardSuit, cardValue);
        assertEquals(true, deck.contains(card));
      }
    }
  }

  /**
   * Check if getDeck is valid.
   */
  @Test
  public void testIfValidDeck() {
    List<Card> deck = testOrdered.getDeck();
    //check number of cards.
    assertEquals(52, deck.size());
    //check if all cards are present in the deck.
    Cards card;
    for (CardSuit cardSuit : CardSuit.values()) {
      for (CardValue cardValue : CardValue.values()) {
        card = new Card(cardSuit, cardValue);
        assertEquals(true, deck.contains(card));
      }
    }
  }

  /**
   * Check startgame with invalid deck.
   */
  @Test
  public void testInvalidDeckStartGame() {
    List<Card> deck = testOrdered.getDeck();
    deck.remove(1);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    //test with not unique cards.
    deck = testOrdered.getDeck();
    deck.remove(0);
    deck.add(new Card(CardSuit.CLUB, CardValue.ACE));
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
  }

  /**
   * Test start game with empty deck.
   */
  @Test
  public void testEmptyDeckStartGame() {
    List<Card> deck = new ArrayList<>();
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
  }

  /**
   * Test start game with null deck.
   */
  @Test
  public void testNullDeckStartGame() {
    try {
      testOrdered.startGame(null, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The passed deck can't be null", e.getMessage());
    }
  }

  /**
   * Check startgame with removing and adding deck.
   */
  @Test
  public void testRemoveAddCardsDeckStartGame() {
    List<Card> deck = testOrdered.getDeck();
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }

    deck.add(new Card(CardSuit.SPADE, CardValue.ACE));
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.add(new Card(CardSuit.SPADE, CardValue.TWO));
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.add(new Card(CardSuit.SPADE, CardValue.THREE));
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.add(new Card(CardSuit.SPADE, CardValue.FOUR));

    try {
      testOrdered.startGame(deck, true);
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  /**
   * Test multiple duplicates.
   */

  @Test
  public void testStartGameMultiDuplicateCards() {
    List<Card> deck = testOrdered.getDeck();
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.remove(0);
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.add(new Card(CardSuit.CLUB, CardValue.ACE));
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.add(new Card(CardSuit.CLUB, CardValue.ACE));
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.add(new Card(CardSuit.CLUB, CardValue.EIGHT));
    try {
      testOrdered.startGame(deck, true);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The deck should always have 52 unique cards!", e.getMessage());
    }
    deck.add(new Card(CardSuit.CLUB, CardValue.EIGHT));
  }

  /**
   * Test startGame in middle of the game.
   */
  @Test
  public void testStartGameMiddle() {
    testOrdered.startGame(testOrdered.getDeck(), false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());

    testOrdered.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());
    testOrdered.move(PileType.CASCADE, 1, 6, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: J♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());
    testOrdered.move(PileType.CASCADE, 2, 6, PileType.OPEN, 2);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: J♦\n" +
            "O3: Q♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());
    testOrdered.move(PileType.CASCADE, 3, 6, PileType.OPEN, 3);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: J♦\n" +
            "O3: Q♦\n" +
            "O4: K♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());
    testOrdered.startGame(testOrdered.getDeck(), false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testOrdered.getGameState());
  }

  /**
   * Test builder default vaules.
   */
  @Test
  public void testBuilder() {
    testNotStarted = FreecellModel
            .getBuilder()
            .build();
    testNotStarted.startGame(testOrdered.getDeck(), false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testNotStarted.getGameState());
  }

  /**
   * Test builder default values.
   */
  @Test
  public void testBuilderDifferentValues() {
    testNotStarted = FreecellModel
            .getBuilder()
            .opens(2)
            .build();
    testNotStarted.startGame(testNotStarted.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, 8♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", testNotStarted.getGameState());
    testNotStarted = FreecellModel
            .getBuilder()
            .cascades(10)
            .build();
    testNotStarted.startGame(testNotStarted.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, J♠, 8♥, 5♣, 2♦, Q♦\n" +
            "C2: 2♠, Q♠, 9♥, 6♣, 3♦, K♦\n" +
            "C3: 3♠, K♠, 10♥, 7♣, 4♦\n" +
            "C4: 4♠, A♥, J♥, 8♣, 5♦\n" +
            "C5: 5♠, 2♥, Q♥, 9♣, 6♦\n" +
            "C6: 6♠, 3♥, K♥, 10♣, 7♦\n" +
            "C7: 7♠, 4♥, A♣, J♣, 8♦\n" +
            "C8: 8♠, 5♥, 2♣, Q♣, 9♦\n" +
            "C9: 9♠, 6♥, 3♣, K♣, 10♦\n" +
            "C10: 10♠, 7♥, 4♣, A♦, J♦", testNotStarted.getGameState());
    testNotStarted = FreecellModel
            .getBuilder()
            .opens(2)
            .cascades(10)
            .build();
    testNotStarted.startGame(testNotStarted.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "C1: A♠, J♠, 8♥, 5♣, 2♦, Q♦\n" +
            "C2: 2♠, Q♠, 9♥, 6♣, 3♦, K♦\n" +
            "C3: 3♠, K♠, 10♥, 7♣, 4♦\n" +
            "C4: 4♠, A♥, J♥, 8♣, 5♦\n" +
            "C5: 5♠, 2♥, Q♥, 9♣, 6♦\n" +
            "C6: 6♠, 3♥, K♥, 10♣, 7♦\n" +
            "C7: 7♠, 4♥, A♣, J♣, 8♦\n" +
            "C8: 8♠, 5♥, 2♣, Q♣, 9♦\n" +
            "C9: 9♠, 6♥, 3♣, K♣, 10♦\n" +
            "C10: 10♠, 7♥, 4♣, A♦, J♦", testNotStarted.getGameState());
    testNotStarted = FreecellModel
            .getBuilder()
            .cascades(100)
            .opens(100)
            .build();
    testNotStarted.startGame(testNotStarted.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "O9:\n" +
            "O10:\n" +
            "O11:\n" +
            "O12:\n" +
            "O13:\n" +
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "O21:\n" +
            "O22:\n" +
            "O23:\n" +
            "O24:\n" +
            "O25:\n" +
            "O26:\n" +
            "O27:\n" +
            "O28:\n" +
            "O29:\n" +
            "O30:\n" +
            "O31:\n" +
            "O32:\n" +
            "O33:\n" +
            "O34:\n" +
            "O35:\n" +
            "O36:\n" +
            "O37:\n" +
            "O38:\n" +
            "O39:\n" +
            "O40:\n" +
            "O41:\n" +
            "O42:\n" +
            "O43:\n" +
            "O44:\n" +
            "O45:\n" +
            "O46:\n" +
            "O47:\n" +
            "O48:\n" +
            "O49:\n" +
            "O50:\n" +
            "O51:\n" +
            "O52:\n" +
            "O53:\n" +
            "O54:\n" +
            "O55:\n" +
            "O56:\n" +
            "O57:\n" +
            "O58:\n" +
            "O59:\n" +
            "O60:\n" +
            "O61:\n" +
            "O62:\n" +
            "O63:\n" +
            "O64:\n" +
            "O65:\n" +
            "O66:\n" +
            "O67:\n" +
            "O68:\n" +
            "O69:\n" +
            "O70:\n" +
            "O71:\n" +
            "O72:\n" +
            "O73:\n" +
            "O74:\n" +
            "O75:\n" +
            "O76:\n" +
            "O77:\n" +
            "O78:\n" +
            "O79:\n" +
            "O80:\n" +
            "O81:\n" +
            "O82:\n" +
            "O83:\n" +
            "O84:\n" +
            "O85:\n" +
            "O86:\n" +
            "O87:\n" +
            "O88:\n" +
            "O89:\n" +
            "O90:\n" +
            "O91:\n" +
            "O92:\n" +
            "O93:\n" +
            "O94:\n" +
            "O95:\n" +
            "O96:\n" +
            "O97:\n" +
            "O98:\n" +
            "O99:\n" +
            "O100:\n" +
            "C1: A♠\n" +
            "C2: 2♠\n" +
            "C3: 3♠\n" +
            "C4: 4♠\n" +
            "C5: 5♠\n" +
            "C6: 6♠\n" +
            "C7: 7♠\n" +
            "C8: 8♠\n" +
            "C9: 9♠\n" +
            "C10: 10♠\n" +
            "C11: J♠\n" +
            "C12: Q♠\n" +
            "C13: K♠\n" +
            "C14: A♥\n" +
            "C15: 2♥\n" +
            "C16: 3♥\n" +
            "C17: 4♥\n" +
            "C18: 5♥\n" +
            "C19: 6♥\n" +
            "C20: 7♥\n" +
            "C21: 8♥\n" +
            "C22: 9♥\n" +
            "C23: 10♥\n" +
            "C24: J♥\n" +
            "C25: Q♥\n" +
            "C26: K♥\n" +
            "C27: A♣\n" +
            "C28: 2♣\n" +
            "C29: 3♣\n" +
            "C30: 4♣\n" +
            "C31: 5♣\n" +
            "C32: 6♣\n" +
            "C33: 7♣\n" +
            "C34: 8♣\n" +
            "C35: 9♣\n" +
            "C36: 10♣\n" +
            "C37: J♣\n" +
            "C38: Q♣\n" +
            "C39: K♣\n" +
            "C40: A♦\n" +
            "C41: 2♦\n" +
            "C42: 3♦\n" +
            "C43: 4♦\n" +
            "C44: 5♦\n" +
            "C45: 6♦\n" +
            "C46: 7♦\n" +
            "C47: 8♦\n" +
            "C48: 9♦\n" +
            "C49: 10♦\n" +
            "C50: J♦\n" +
            "C51: Q♦\n" +
            "C52: K♦\n" +
            "C53:\n" +
            "C54:\n" +
            "C55:\n" +
            "C56:\n" +
            "C57:\n" +
            "C58:\n" +
            "C59:\n" +
            "C60:\n" +
            "C61:\n" +
            "C62:\n" +
            "C63:\n" +
            "C64:\n" +
            "C65:\n" +
            "C66:\n" +
            "C67:\n" +
            "C68:\n" +
            "C69:\n" +
            "C70:\n" +
            "C71:\n" +
            "C72:\n" +
            "C73:\n" +
            "C74:\n" +
            "C75:\n" +
            "C76:\n" +
            "C77:\n" +
            "C78:\n" +
            "C79:\n" +
            "C80:\n" +
            "C81:\n" +
            "C82:\n" +
            "C83:\n" +
            "C84:\n" +
            "C85:\n" +
            "C86:\n" +
            "C87:\n" +
            "C88:\n" +
            "C89:\n" +
            "C90:\n" +
            "C91:\n" +
            "C92:\n" +
            "C93:\n" +
            "C94:\n" +
            "C95:\n" +
            "C96:\n" +
            "C97:\n" +
            "C98:\n" +
            "C99:\n" +
            "C100:", testNotStarted.getGameState());

    testNotStarted = FreecellModel
            .getBuilder()
            .opens(2)
            .cascades(10)
            .build();
    testNotStarted.startGame(testNotStarted.getDeck(), false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "C1: A♠, J♠, 8♥, 5♣, 2♦, Q♦\n" +
            "C2: 2♠, Q♠, 9♥, 6♣, 3♦, K♦\n" +
            "C3: 3♠, K♠, 10♥, 7♣, 4♦\n" +
            "C4: 4♠, A♥, J♥, 8♣, 5♦\n" +
            "C5: 5♠, 2♥, Q♥, 9♣, 6♦\n" +
            "C6: 6♠, 3♥, K♥, 10♣, 7♦\n" +
            "C7: 7♠, 4♥, A♣, J♣, 8♦\n" +
            "C8: 8♠, 5♥, 2♣, Q♣, 9♦\n" +
            "C9: 9♠, 6♥, 3♣, K♣, 10♦\n" +
            "C10: 10♠, 7♥, 4♣, A♦, J♦", testNotStarted.getGameState());
  }

  /**
   * Test builder invalid parameters.
   */
  @Test
  public void testInvalidBuilder() {
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .opens(0)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .opens(-1)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .opens(-5)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }

    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .cascades(0)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .cascades(2)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .cascades(3)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .cascades(1)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .cascades(-1)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .cascades(-5)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
    try {
      testNotStarted = FreecellModel
              .getBuilder()
              .opens(4)
              .cascades(8)
              .cascades(-5)
              .build();
      fail();
    } catch (IllegalArgumentException e) {
      //pass the case.
    }
  }

  /**
   * Test gamestate after failed startgame.
   */
  @Test
  public void testFailStartEmptyGameState() {
    List l = testNotStarted.getDeck();
    testNotStarted.getDeck().remove(0);
    try {
      testNotStarted.startGame(l, false);
    } catch (IllegalArgumentException e) {
      assertEquals("", testNotStarted.getGameState());
    }
  }
}