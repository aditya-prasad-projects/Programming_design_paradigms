import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import freecell.controller.FreecellController;
import freecell.controller.IFreecellController;
import freecell.model.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import freecell.model.FreecellOperations;


/**
 * Tests the controller in isolation.
 */
public class ControllerTest {

  /**
   * Tests the controller in isolation.
   */
  @Test
  public void testControllerIsolation() {
    Reader in = new StringReader("C1\n" +
            "7\n" +
            "O1\n" +
            "O1 1 O2");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n" + rand2 + "\n" + rand2 + "\n", out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! CASCADE 0 6  OPEN 0\n" +
            "getGameState() called!\n" +
            "isGameOver() called!\n" +
            "move() called! OPEN 0 0  OPEN 1\n" +
            "getGameState() called!\n" +
            "isGameOver() called!\n", log.toString());
  }

  /**
   * Tests that the exceptions written to the output is sent by the model. Invalid Source cascade
   * pile.
   */
  @Test
  public void testControllerIsolationExceptionsSourceCascadePile() {
    Reader in = new StringReader("C9 3 C1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n" + "Invalid move. " +
            "Try again. Invalid move. Try again. Mock model throwing exception, Invalid " +
            "value in source cascade pile.\n", out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! CASCADE 8 2  CASCADE 0\n", log.toString());
  }

  /**
   * Tests that the exceptions written to the output is sent by the model. Invalid destination
   * cascade pile.
   */
  @Test
  public void testControllerIsolationExceptionsDestinationCascadePile() {
    Reader in = new StringReader("C1 3 C10");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n"
                    + "Invalid move. Try again. Invalid move. Try again. Mock model throwing " +
                    "exception, Invalid value in destination cascade pile.\n"
            , out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! CASCADE 0 2  CASCADE 9\n", log.toString());
  }

  /**
   * Tests that the exceptions written to the output is sent by the model. Invalid Source open
   * pile.
   */
  @Test
  public void testControllerIsolationExceptionSourceOpenPile() {
    Reader in = new StringReader("O7 3 C4");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n"
                    + "Invalid move. Try again. Invalid move. Try again. Mock model throwing " +
                    "exception, Invalid value in source open pile.\n"
            , out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! OPEN 6 2  CASCADE 3\n", log.toString());
  }

  /**
   * Tests that the exceptions written to the output is sent by the model. Invalid destination open
   * pile.
   */
  @Test
  public void testControllerIsolationExceptionDestinationOpenPile() {
    Reader in = new StringReader("O1 3 O7");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n"
                    + "Invalid move. Try again. Invalid move. Try again. Mock model" +
                    " throwing exception, Invalid value in destination open pile.\n"
            , out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! OPEN 0 2  OPEN 6\n", log.toString());
  }

  /**
   * Tests that the exceptions written to the output is sent by the model. Invalid Source Foundation
   * pile.
   */
  @Test
  public void testControllerIsolationExceptionSourceFoundationPile() {
    Reader in = new StringReader("F7 3 O1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n"
                    + "Invalid move. Try again. Invalid move. Try again. Mock model throwing" +
                    " exception, Invalid value in source foundation pile.\n"
            , out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! FOUNDATION 6 2  OPEN 0\n", log.toString());
  }

  /**
   * Tests that the exceptions written to the output is sent by the model. Invalid Source Foundation
   * pile.
   */
  @Test
  public void testControllerIsolationExceptionDestinationFoundationPile() {
    Reader in = new StringReader("F5 3 F7");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n"
                    + "Invalid move. Try again. Invalid move. Try again. Mock model " +
                    "throwing exception, Invalid value in destination foundation pile.\n"
            , out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! FOUNDATION 4 2  FOUNDATION 6\n", log.toString());
  }

  @Test
  public void testInvalidCardIndex() {
    Reader in = new StringReader("F5 12 F7");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, false);
    assertEquals("" + rand2 + "\n"
                    + "Invalid move. Try again. Mock model throwing exception, Invalid CardIndex\n"
            , out.toString());
    assertEquals("startGame() called! [] false\n" +
            "getGameState() called!\n" +
            "move() called! FOUNDATION 4 11  FOUNDATION 6\n", log.toString());
  }

  /**
   * Tests for invalid readable passed to the constructor.
   */
  @Test
  public void testInvalidReadableControllerInput() {
    Reader in;
    StringBuffer out = new StringBuffer();
    try {
      IFreecellController controller = new FreecellController(null, out);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters", e.getMessage());
    }
  }

  /**
   * Tests for invalid appendable passed to the constructor.
   */
  @Test
  public void testInvalidAppendableControllerInput() {
    Reader in = new StringReader("");
    try {
      IFreecellController controller = new FreecellController(in, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters", e.getMessage());
    }
  }

  /**
   * Tests for when both are appendable and readable invalid.
   */
  @Test
  public void testInvalidAppendableAndReadableController() {
    try {
      IFreecellController controller = new FreecellController(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters", e.getMessage());
    }
  }

  /**
   * Tests for when deck is null.
   */
  @Test
  public void testInvalidDeck() {
    Reader in = new StringReader("F5 3 F7");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    try {
      controller.playGame(null, model, false);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters", e.getMessage());
    }
  }

  /**
   * Tests for when model is null.
   */
  @Test
  public void testInvalidModel() {
    Reader in = new StringReader("F5 3 F7");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    try {
      controller.playGame(card, null, false);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid parameters", e.getMessage());
    }
  }

  /**
   * Tests for playGame with shuffle.
   */
  @Test
  public void testInvalid() {
    Reader in = new StringReader("C1\n" +
            "7\n" +
            "O1\n" +
            "O1 1 O2");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Cards> card = new ArrayList<>();
    controller.playGame(card, model, true);
    assertEquals("" + rand2 + "\n" + rand2 + "\n" + rand2 + "\n", out.toString());
    assertEquals("startGame() called! [] true\n" +
            "getGameState() called!\n" +
            "move() called! CASCADE 0 6  OPEN 0\n" +
            "getGameState() called!\n" +
            "isGameOver() called!\n" +
            "move() called! OPEN 0 0  OPEN 1\n" +
            "getGameState() called!\n" +
            "isGameOver() called!\n", log.toString());
  }

  /**
   * Tests that the exception thrown by the startGame is the one that is sent by the controller.
   */
  @Test
  public void testStartGameException() {
    Reader in = new StringReader("O7 3 C4");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    StringBuilder log = new StringBuilder();
    Random r = new Random();
    double rand1 = r.nextDouble();
    double rand2 = r.nextDouble();
    FreecellOperations model = new MockModel(log, rand1, rand2);
    List<Integer> card = new ArrayList<>();
    card.add(1);
    card.add(2);
    controller.playGame(card, model, false);
    assertEquals("Invalid deck", out.toString());
    assertEquals("startGame() called! [1, 2] false", log.toString());
  }
}



