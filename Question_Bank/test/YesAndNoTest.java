import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import questionnaire.YesAndNo;

/**
 * Tests the YesAndNo class.
 */
public class YesAndNoTest extends AbstractQuestionTest {

  @Before
  public void setup() {
    q = new YesAndNo("Do you like coffee?", "Yes");
  }

  /**
   * Tests whether the user can create a question with an answer other than yes or no.
   */
  @Test
  public void testInitialisation() {
    try {
      q1 = new YesAndNo("Do you like food?", "Tests");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Answer can be only \"Yes\" or \"No\" ");
    }

  }

  /**
   * Tests if it evaluates the answer correctly.
   */
  @Test
  public void testAnswer1() {
    assertEquals(q.evaluate("Yes"), "Correct");
  }

  /**
   * Tests if it evaluates to Incorrect.
   */
  @Test
  public void testAnswer2() {
    assertEquals(q.evaluate("No"), "Incorrect");
  }

  /**
   * Tests whether it can accept different variations of yes.
   */
  @Test
  public void testAnswer3() {
    assertEquals(q.evaluate("yes"), "Correct");
  }

  /**
   * Tests whether it accepts invalid variant of yes.
   */
  @Test
  public void testAnswer4() {
    assertEquals(q.evaluate("I think it is Yes"), "Incorrect");
  }

  /**
   * Tests if it evaluates invalid answer to Incorrect.
   */
  @Test
  public void testAnswer5() {
    assertEquals(q.evaluate("I think so."), "Incorrect");
  }

  /**
   * Tests if the user has access to the question.
   */
  @Test
  public void testAnswer6() {
    assertEquals(q.getQuestion(), "Do you like coffee?");
  }
}
