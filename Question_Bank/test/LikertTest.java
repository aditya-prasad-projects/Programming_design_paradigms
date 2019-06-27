import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import questionnaire.Likert;
import questionnaire.LikertEnum;

/**
 * Tests the Likert class.
 */
public class LikertTest extends AbstractQuestionTest {

  @Before
  public void setup() {
    q = new Likert("Coffee is good for health");
  }

  /**
   * Tests that the user can enter any option in the likert scale.
   */
  @Test
  public void testInput() {
    System.out.println(LikertEnum.StronglyAgree.toString());
    assertEquals(q.evaluate(LikertEnum.StronglyAgree.getEnum()), "Correct");
  }

  /**
   * Tests that the user can enter any option in the likert scale.
   */
  @Test
  public void testInput1() {
    assertEquals(q.evaluate(LikertEnum.StronglyDisagree.getEnum()), "Correct");
  }

  /**
   * Tests that the user can enter any option in the likert scale.
   */
  @Test
  public void testInput2() {
    assertEquals(q.evaluate(LikertEnum.NeitherAgreenorDisagree.getEnum()), "Correct");
  }

  /**
   * Tests that the user can enter any option in the likert scale.
   */
  @Test
  public void testInput3() {
    assertEquals(q.evaluate(LikertEnum.Disagree.getEnum()), "Correct");
  }

  /**
   * Tests that the user can enter any option in the likert scale.
   */
  @Test
  public void testInput4() {
    assertEquals(q.evaluate(LikertEnum.StronglyDisagree.getEnum()), "Correct");
  }

  /**
   * Tests that the user can enter any option in the likert scale.
   */
  @Test
  public void testInput5() {
    assertEquals(q.evaluate("6"), "Incorrect");
  }

  /**
   * Tests that the user can enter any option in the likert scale.
   */
  @Test
  public void testInput6() {
    assertEquals(q.evaluate("Yes"), "Incorrect");
  }

  /**
   * Tests that the user has access to the question.
   */
  @Test
  public void testInput7() {
    assertEquals(q.getQuestion(),
            "Coffee is good for health - Strongly Agree, Agree, Neither Agree nor Disagree, "
                    + "Disagree, Strongly Disagree");
  }
}
