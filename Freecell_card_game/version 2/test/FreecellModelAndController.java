import org.junit.Before;
import org.junit.Test;

import freecell.controller.FreecellController;
import freecell.controller.IFreecellController;
import freecell.model.FreecellModel;
import freecell.model.Cards;
import freecell.model.FreecellMultiMoveModel;
import freecell.model.FreecellOperations;
import java.io.Reader;
import java.io.StringReader;

import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Checks for FreecellController with both FreecellModel and FreecelMultiMoveModel.
 */
public class FreecellModelAndController {
  private FreecellOperations oneMove;
  private FreecellOperations multiMove;
  private IFreecellController controller;
  private Reader in;
  private StringBuffer out;

  /**
   * Initializes oneMove, multiMove and out objects representing a freecellModel, multiMoveModel
   * and a string buffer respectively.
   */
  @Before
  public void setup() {
    oneMove = FreecellModel.getBuilder().build();
    multiMove = FreecellMultiMoveModel.getBuilder().build();
    out = new StringBuffer();
  }

  /**
   * Tests invalid deck passed to the play game of FreecellMultiMoveModel.
   */
  @Test
  public void testInvalidDeck() {
    in = new StringReader("C5 6 O1");
    controller = new FreecellController(in, out);
    List<Cards> deck = multiMove.getDeck();
    deck.remove(deck.remove(0));
    controller.playGame(deck, multiMove, false);
    assertEquals(out.toString(), "Invalid deck");
  }

  /**
   * Tests invalid deck passed to the play game for FreecellModel.
   */
  @Test
  public void testInvalidDeckSingleMove() {
    in = new StringReader("C5 6 O1");
    controller = new FreecellController(in, out);
    List<Cards> deck = multiMove.getDeck();
    deck.remove(deck.remove(0));
    controller.playGame(deck, oneMove, false);
    assertEquals(out.toString(), "Invalid deck");
  }

  /**
   * Tests creating invalid FreecellModel.
   */
  @Test
  public void testInvalidFreecellModel() {
    in = new StringReader("C5 6 O1");
    controller = new FreecellController(in, out);
    List<Cards> deck = multiMove.getDeck();
    deck.remove(deck.remove(0));
    try {
      controller.playGame(deck, FreecellModel.getBuilder().opens(0).build(), true);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Pile size cannot be lower than 1",e.getMessage());
    }
  }

  /**
   * Tests creating invalid FreecellMultiMoveModel.
   */
  @Test
  public void testInvalidFreecellMultiMoveModel() {
    in = new StringReader("C5 6 O1 q");
    controller = new FreecellController(in, out);
    List<Cards> deck = multiMove.getDeck();
    deck.remove(deck.remove(0));
    try {
      controller.playGame(deck, FreecellMultiMoveModel.getBuilder().opens(0).build(), true);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Pile size cannot be lower than 1",e.getMessage());
    }
  }

  /**
   * Tests for controller by passing it a empty reader.
   */
  @Test
  public void testEmptyReader() {
    in = new StringReader("");
    controller = new FreecellController(in,out);
    controller.playGame(multiMove.getDeck(), multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n", out.toString());
  }

  /**
   * Test by passing q in the beginning to the reader.
   */
  @Test
  public void testQ() {
    in = new StringReader("q");
    controller = new FreecellController(in, out);
    controller.playGame(multiMove.getDeck(), multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "Game quit prematurely.\n", out.toString());
  }

  /**
   * Tests what happens after game quits.
   */
  @Test
  public void testAfterGameQuit() {
    in = new StringReader("q C1 6 O1");
    controller = new FreecellController(in, out);
    controller.playGame(multiMove.getDeck(), multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "Game quit prematurely.\n", out.toString());
    controller.playGame(oneMove.getDeck(), oneMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "Game quit prematurely.\n" +
            "F1:\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n", out.toString());
  }

  /**
   * Tests for multiple valid moves.
   */
  @Test
  public void testValidMoves() {
    in = new StringReader("C8 6 O1 C8 5 F1 C8");
    controller = new FreecellController(in, out);
    List<Cards> deck = multiMove.getDeck();
    controller.playGame(deck, multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦\n" +
            "F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣\n", out.toString());
  }

  /**
   * Tests for multiple valid moves for FreecellModel.
   */
  @Test
  public void testValidMovesFreecellModel() {
    in = new StringReader("C8 6 O1 C8 5 F1 C8");
    controller = new FreecellController(in, out);
    List<Cards> deck = oneMove.getDeck();
    controller.playGame(deck, oneMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦\n" +
            "F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣\n", out.toString());
  }

  /**
   * Tests if the controller waits if invalid cardIndex is typed.
   */
  @Test
  public void testInvalidCardIndexReader() {
    in = new StringReader("C8 M 6 O1");
    controller = new FreecellController(in, out);
    controller.playGame(multiMove.getDeck(), multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦\n", out.toString());
  }

  /**
   * Tests if controller waits if invalid Destination is typed.
   */
  @Test
  public void main() {
    in = new StringReader("C8 6 M O1");
    controller = new FreecellController(in, out);
    controller.playGame(multiMove.getDeck(), multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦\n", out.toString());
  }

  /**
   * Tests if command starts with an invalid value.
   */
  @Test
  public void testInvalidValueCommand() {
    in = new StringReader("M C8 6 O1");
    controller = new FreecellController(in, out);
    controller.playGame(multiMove.getDeck(), multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦\n", out.toString());
  }

  /**
   * Tests for null passed to the controller.
   */
  @Test
  public void testNull() {
    try {
      in = new StringReader(null);
      controller = new FreecellController(in, out);
      fail();
    }
    catch (NullPointerException e) {
      assertEquals(e.getMessage(), null);
    }
  }

  /**
   * Tests for invalid values passed to the Reader.
   */
  @Test
  public void testInvalidValues() {
    in = new StringReader("AAA C1 acsdsa 7 acasc O1 adassa Q");
    controller = new FreecellController(in, out);
    controller.playGame(multiMove.getDeck(), multiMove, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "F1:\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦\n" +
            "Game quit prematurely.\n", out.toString());
  }






}
