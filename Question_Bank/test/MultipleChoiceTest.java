import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import questionnaire.MultipleChoice;

/**
 * Tests the MultipleChoice class.
 */
public class MultipleChoiceTest extends MultipleAnswersTest {

  @Before
  public void setup() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    q = new MultipleChoice("Which is the best coffee", "1", options);
  }

  /**
   * Tests if it evaluates the answer correctly.
   */
  @Test
  public void testInput() {
    assertEquals(q.evaluate("1"), "Correct");
  }

  /**
   * Tests for how it takes in invalid inputs.
   */
  @Test
  public void testInput1() {
    assertEquals(q.evaluate("2"), "Incorrect");
  }

  /**
   * Tests for how it takes in invalid inputs.
   */
  @Test
  public void testInput2() {
    assertEquals(q.evaluate("3"), "Incorrect");
  }

  /**
   * Tests for how it takes in invalid inputs.
   */
  @Test
  public void testInput3() {
    assertEquals(q.evaluate("   4"), "Incorrect");
  }

  /**
   * Tests for invalid inputs.
   */
  @Test
  public void testInput4() {
    assertEquals(q.evaluate("a"), "Incorrect");
  }

  /**
   * Tests whether the user has access to the question.
   */
  @Test
  public void testInput9() {
    assertEquals(q.getQuestion(), "Which is the best coffee - 1 2 3 4 5 6 7 8 ");
  }
}
