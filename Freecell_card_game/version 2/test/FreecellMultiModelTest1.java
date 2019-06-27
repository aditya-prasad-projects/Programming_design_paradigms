import org.junit.Test;

import java.util.List;

import freecell.model.FreecellMultiMoveModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests FreecellModel class.
 */
public class FreecellMultiModelTest1 {
  /**
   * Check if startGame works correctly with no shuffle.
   */
  @Test
  public void test1() {
    helper(FreecellMultiMoveModel
            .getBuilder()
            .build());
    FreecellOperations test = FreecellMultiMoveModel
            .getBuilder()
            .build();
    assertEquals("", test.getGameState());

  }

  /**
   * Helper method for startGame works correctly with no shuffle.
   *
   * @param model A FreecellOperations model.
   * @param <T>   the card type.
   */
  private static <T> void helper(freecell.model.FreecellOperations<T> model) {
    List<T> deck = model.getDeck();
    model.startGame(deck, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", model.getGameState());
    model.move(PileType.CASCADE, 6, 5, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 8♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦, Q♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", model.getGameState());
    model.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 6);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 8♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", model.getGameState());
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 8♦\n" +
            "O2: 9♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣, A♦", model.getGameState());
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    assertEquals("F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 8♦\n" +
            "O2: 9♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", model.getGameState());
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 2);
    assertEquals("F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 8♦\n" +
            "O2: 9♦\n" +
            "O3: 10♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣, 2♦\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", model.getGameState());
    model.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    assertEquals("F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 8♦\n" +
            "O2: 9♦\n" +
            "O3: 10♦\n" +
            "O4:\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", model.getGameState());
    model.move(PileType.FOUNDATION, 0, 1, PileType.OPEN, 3);
    assertEquals("F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 8♦\n" +
            "O2: 9♦\n" +
            "O3: 10♦\n" +
            "O4: 2♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", model.getGameState());
    String s = model.getGameState();
    //Check for invalid move from foundation to cascade.
    try {
      model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid move");
    }
    assertEquals(s, model.getGameState());
    //Check invalid move from foundation to open when it is full.
    try {
      model.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Open pile can have only 1 card");
    }
    assertEquals(s, model.getGameState());
    //Check invalid move from Cascade to open when it is full.
    try {
      model.move(PileType.CASCADE, 0, 4, PileType.OPEN, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Open pile can have only 1 card");
    }
    assertEquals(s, model.getGameState());
    //Check invalid move from open to cascade.
    try {
      model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 7);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid move");
    }
    assertEquals(s, model.getGameState());
    //Check invalid move from open to foundation.
    try {
      model.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid move");
    }
    assertEquals(s, model.getGameState());
    //Check for invalid source pile number for openPile.
    try {
      model.move(PileType.OPEN, 4, 0, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid destination pile number");
    }
    assertEquals(s, model.getGameState());
    model.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 3);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4: A♦\n" +
            "O1: 8♦\n" +
            "O2: 9♦\n" +
            "O3: 10♦\n" +
            "O4: 2♦\n" +
            "C1: A♠, 9♠, 4♥, Q♥, 7♣\n" +
            "C2: 2♠, 10♠, 5♥, K♥, 8♣, 3♦, J♦\n" +
            "C3: 3♠, J♠, 6♥, A♣, 9♣, 4♦\n" +
            "C4: 4♠, Q♠, 7♥, 2♣, 10♣, 5♦, K♦\n" +
            "C5: 5♠, K♠, 8♥, 3♣, J♣, 6♦\n" +
            "C6: 6♠, A♥, 9♥, 4♣, Q♣, 7♦\n" +
            "C7: 7♠, 2♥, 10♥, 5♣, K♣, Q♦\n" +
            "C8: 8♠, 3♥, J♥, 6♣", model.getGameState());
    s = model.getGameState();
    //Check for invalid source pile number for foundationPile.
    try {
      model.move(PileType.FOUNDATION, 4, 0, PileType.CASCADE, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid destination pile number");
    }
    assertEquals(s, model.getGameState());
    //Check for invalid cardIndex in openPile.
    try {
      model.move(PileType.OPEN, 3, 1, PileType.FOUNDATION, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Only last card can be moved", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check for invalid cardIndex in FoundationPile.
    try {
      model.move(PileType.FOUNDATION, 3, 1, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Only last card can be moved", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Test if SourcePileNumber can be negative for FoundationPile.
    try {
      model.move(PileType.FOUNDATION, -1, 0, PileType.CASCADE, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid destination pile number");
    }
    assertEquals(s, model.getGameState());
    //Test if SourcePileNumber can be negative for OpenPile.
    try {
      model.move(PileType.OPEN, -1, 0, PileType.CASCADE, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid destination pile number");
    }
    assertEquals(s, model.getGameState());
    //Test if SourcePileNumber can be negative for CascadePile.
    try {
      model.move(PileType.CASCADE, -1, 0, PileType.CASCADE, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid destination pile number");
    }
    assertEquals(s, model.getGameState());
    //Check whether cardIndex can take in a negative value for openPile.
    try {
      model.move(PileType.OPEN, 3, -1, PileType.FOUNDATION, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Only last card can be moved", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check whether cardIndex can take in a negative value for FoundationPile.
    try {
      model.move(PileType.FOUNDATION, 3, -1, PileType.FOUNDATION, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Only last card can be moved", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check whether cardIndex can take in a negative value for CascadePile.
    try {
      model.move(PileType.CASCADE, 3, -1, PileType.FOUNDATION, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Only last card can be moved", e.getMessage());
    }
    assertEquals(s, model.getGameState());
  }

  /**
   * Check for invalid moves.
   */
  @Test
  public void test2() {
    helper1(FreecellMultiMoveModel
            .getBuilder()
            .build());
    FreecellOperations test = FreecellMultiMoveModel
            .getBuilder()
            .build();
    assertEquals("", test.getGameState());
  }

  /**
   * Helper method to check for invalid moves.
   *
   * @param model A FreecellOperations model.
   * @param <T>   the card type.
   */
  private static <T> void helper1(freecell.model.FreecellOperations<T> model) {
    List<T> deck = model.getDeck();
    model.startGame(deck, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", model.getGameState());
    String s = model.getGameState();
    //Invalid move from cascade to foundation.
    try {
      model.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only Ace can be added to empty foundation");
    }
    assertEquals(s, model.getGameState());
    //Check for invalid source pile number for cascadePile.
    try {
      model.move(PileType.CASCADE, 8, 5, PileType.OPEN, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid destination pile number", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check for invalid cardIndex in cascadePile.
    try {
      model.move(PileType.CASCADE, 0, 7, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only last card can be moved");
    }
    assertEquals(s, model.getGameState());
    //Check for invalid cardIndex when it is empty in Foundation Pile.
    try {
      model.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only last card can be moved");
    }
    assertEquals(s, model.getGameState());
    //Check for invalid cardIndex when it is empty in Open Pile.
    try {
      model.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only last card can be moved");
    }
    assertEquals(s, model.getGameState());
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    assertEquals("F1:\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦", model.getGameState());
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    assertEquals("F1: A♦\n" +
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
            "C8: 8♠, 3♥, J♥, 6♣", model.getGameState());
    s = model.getGameState();
    //Check if destination pile number is valid when moving to cascadePile.
    try {
      model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 8);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid source pile numbers", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check if destination pile number is valid when moving to OpenPile.
    try {
      model.move(PileType.OPEN, 0, 0, PileType.OPEN, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid source pile numbers", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check if destination pile number is valid when moving to FoundationPile.
    try {
      model.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid source pile numbers", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check if destination pile number of cascadePile by passing negative value.
    try {
      model.move(PileType.OPEN, -1, 0, PileType.CASCADE, 8);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid source pile numbers", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check if destination pile number of OpenPile by passing negative value.
    try {
      model.move(PileType.OPEN, -1, 0, PileType.OPEN, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid source pile numbers", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check if destination pile number of FoundationPile by passing negative value.
    try {
      model.move(PileType.FOUNDATION, -1, 0, PileType.FOUNDATION, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid source pile numbers", e.getMessage());
    }
    assertEquals(s, model.getGameState());
    //Check invalid move from cascade to cascade.
    try {
      model.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Can't be added to destination card!");
    }
    assertEquals(s, model.getGameState());
    //Check invalid move from foundation to foundation.
    try {
      model.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid move");
    }
    assertEquals(s, model.getGameState());
    //Check invalid move from open to open.
    try {
      model.move(PileType.OPEN, 0, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Open pile can have only 1 card");
    }
    assertEquals(s, model.getGameState());
  }

  /**
   * Try moving a card and place it back in the same position.
   */
  @Test
  public void test3() {
    helper2(FreecellMultiMoveModel
            .getBuilder()
            .build());
    FreecellOperations test = FreecellMultiMoveModel
            .getBuilder()
            .build();
    assertEquals("", test.getGameState());

  }

  /**
   * helper function for moving a card and placing it back in the same position.
   *
   * @param model A FreecellOperations model.
   * @param <T>   the card type.
   */
  private static <T> void helper2(freecell.model.FreecellOperations<T> model) {
    List<T> deck = model.getDeck();
    model.startGame(deck, false);
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
            "C8: 8♠, 3♥, J♥, 6♣, A♦, 9♦", model.getGameState());
    String s = model.getGameState();
    try {
      model.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Can't be added to destination card!");
    }
    assertEquals(s, model.getGameState());
    try {
      model.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Only Ace can be added to empty foundation");
    }
    assertEquals(s, model.getGameState());
  }
}