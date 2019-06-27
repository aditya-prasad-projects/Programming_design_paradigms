import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import questionnaire.MultipleAnswers;

/**
 * Tests the MultipleAnswers class.
 */
public class MultipleAnswersTest extends AbstractQuestionTest {

  @Before
  public void setup() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    q = new MultipleAnswers("Select the best Coffees", "1 4 3", options);
  }

  /**
   * Tests if MultipleAnswer object can be created with option starting from "0".
   * @throws IllegalArgumentException when options starts from "0".
   */
  @Test
  public void testInitialization1() throws IllegalArgumentException {
    try {
      String[] options = {"0", "1", "2"};
      q1 = new MultipleAnswers("Which is the best coffee", "1", options);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "The option number should start from \"1\" ");
    }
  }

  /**
   * Tests if MultipleAnswer object can be created with less than three options.
   * @throws IllegalArgumentException when options size is lesser than three.
   */
  @Test
  public void testInitialisation2() throws IllegalArgumentException {
    try {
      String[] options = {"1", "2"};
      q1 = new MultipleAnswers("Which is the best coffee", "1", options);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "The number of options can only be between 3 and 8");
    }
  }

  /**
   * Tests if MultipleAnswer object can be created with more than 8 objects.
   * @throws IllegalArgumentException when options size is greater than 8.
   */
  @Test
  public void testInitialisation3() throws IllegalArgumentException {
    try {
      String[] options = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
      q1 = new MultipleAnswers("Which is the best coffee", "1", options);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "The number of options can only be between 3 and 8");
    }
  }

  /**
   * Tests if MultipleAnswer object can be created by passing an answer not in options.
   * @throws IllegalArgumentException when answer is not in options.
   */
  @Test
  public void testInitialisation4() throws IllegalArgumentException {
    try {
      String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
      q1 = new MultipleAnswers("Which is the best coffee", "1 3 9", options);
      fail();
    }
    catch (IllegalArgumentException e ) {
      assertEquals(e.getMessage(), "The answer has to be from 1-8");
    }
  }

  /**
   * Tests if user's answer is evaluated correctly.
   */
  @Test
  public void testInput() {
    assertEquals(q.evaluate("1 4 3"), "Correct");
  }

  /**
   * Tests that it can accepts correct answer in different order.
   */
  @Test
  public void testInput1() {
    assertEquals(q.evaluate("1 3 4"), "Correct");
  }

  /**
   * Tests that it can accepts correct answer in different order.
   */
  @Test
  public void testInput2() {
    assertEquals(q.evaluate("3 4 1"), "Correct");
  }

  /**
   * Tests that it can accepts correct answer in different order.
   */
  @Test
  public void testInput3() {
    assertEquals(q.evaluate("4 3 1"), "Correct");
  }

  /**
   * Tests if it evaluates the answer to incorrect.
   */
  @Test
  public void testInput4() {
    assertEquals(q.evaluate("1 2 3"), "Incorrect");
  }

  /**
   * Tests if it evaluates to incorrect when invalid input is given.
   */
  @Test
  public void testInput5() {
    assertEquals(q.evaluate("1 a 3"), "Incorrect");
  }

  /**
   * Tests if it evaluates partial answer as incorrect.
   */
  @Test
  public void testInput6() {
    assertEquals(q.evaluate("1 3"), "Incorrect");
  }

  /**
   * Tests when the answer is entire option list and there is space between the answers.
   */
  @Test
  public void testInput8() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    q1 = new MultipleAnswers("Which is the best coffee", "1 4 3 2 5 6 8 7", options);
    assertEquals(q1.evaluate("1 2 3    4 5 6 7 8"), "Correct");
    assertEquals(q1.evaluate("1 2 3"), "Incorrect");
  }

  /**
   * Tests whether the user has access to the question.
   */
  @Test
  public void testInput9() {
    assertEquals(q.getQuestion(), "Select the best Coffees - 1 2 3 4 5 6 7 8 ");
  }
}
