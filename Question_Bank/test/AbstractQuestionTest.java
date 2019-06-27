import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import questionnaire.Question;
import questionnaire.YesAndNo;
import questionnaire.Likert;
import questionnaire.MultipleChoice;
import questionnaire.MultipleAnswers;

/**
 * Tests whether the sort is implemented correctly.
 */
public class AbstractQuestionTest {
  Question q;
  Question q1;

  /**
   * Tests whether all different kinds of objects are sorted properly.
   */
  @Test
  public void testSort() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7"};
    Question a = new YesAndNo("Is coffee good for health?", "Yes");
    Question b = new YesAndNo("Is caffeine bad for health?", "No");
    Question c = new Likert("Coffee is bad for health.");
    Question d = new Likert("Its good to work on coffee");
    Question e = new MultipleChoice("Select your favourite coffee?", "1", options);
    Question g = new MultipleChoice("Please select your favourite coffee?",
            "2", options);
    Question f = new MultipleAnswers("Select your favourite coffee?",
            "1 4 5", options);
    Question[] question1 = {a,b,c,d,e,f,g};
    Arrays.sort(question1);
    assertEquals(Arrays.toString(question1), "[Is caffeine bad for health?, "
            + "Is coffee good for health?, Coffee is bad for health., Its good to work on coffee, "
            + "Please select your favourite coffee?, Select your favourite coffee?, "
            + "Select your favourite coffee?]");
  }

  /**
   * Tests different combinations of Likert and YesAndNo questions are sorted properly.
   */
  @Test
  public void testSort1() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7"};
    Question a = new YesAndNo("Is coffee good for health?", "Yes");
    Question c = new Likert("Coffee is bad for health.");
    Question[] question1 = {c,a};
    Arrays.sort(question1);
    Question[] question2 = {a,c};
    Arrays.sort(question2);
    assertEquals(Arrays.toString(question1),
            "[Is coffee good for health?, Coffee is bad for health.]");
    assertEquals(Arrays.toString(question2),
            "[Is coffee good for health?, Coffee is bad for health.]");
  }

  /**
   * Tests if YesAndNo objects are sorted lexicographically.
   */
  @Test
  public void testSort2() {
    Question a = new YesAndNo("Is coffee good for health?", "Yes");
    Question b = new YesAndNo("Is caffeine bad for health?", "No");
    Question[] question1 = {b,a};
    Arrays.sort(question1);
    Question[] question2 = {a,b};
    Arrays.sort(question2);
    assertEquals(Arrays.toString(question1),
            "[Is caffeine bad for health?, Is coffee good for health?]");
    assertEquals(Arrays.toString(question2),
            "[Is caffeine bad for health?, Is coffee good for health?]");
  }

  /**
   * Tests that the different types of MultipleChoice questions are sorted lexicographically.
   */
  @Test
  public void testSort4() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7"};
    Question e = new MultipleChoice("Select your favourite coffee?", "1", options);
    Question f = new MultipleChoice("Select your favourite coffeee?", "1", options);
    Question[] question1 = {e,f};
    Arrays.sort(question1);
    assertEquals(Arrays.toString(question1),
            "[Select your favourite coffee?, Select your favourite coffeee?]");
  }

  /**
   * Tests all kinds of objects, with minimum 2 objects from each class.
   */
  @Test
  public void testSort5() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    Question e = new MultipleChoice("Select your favourite coffee?", "1", options);
    Question f = new MultipleChoice("Select your favourite coffeee?", "1", options);
    Question a = new YesAndNo("Is coffee good for health?", "Yes");
    Question b = new YesAndNo("Is caffeine bad for health?", "No");
    Question c = new Likert("Coffee is bad for health.");
    Question d = new Likert("Its good to work on coffee");
    Question g = new MultipleChoice("Please select your favourite coffee?",
            "2", options);
    Question h = new MultipleAnswers("Select your favourite coffee?",
            "1 4 5", options);
    Question i = new MultipleAnswers("Select your favourite drink?",
            "1 6 5", options);
    Question[] question1 = {e,f,a,b,c,d,g,h,i};
    Arrays.sort(question1);
    assertEquals(Arrays.toString(question1),
            "[Is caffeine bad for health?, "
                    + "Is coffee good for health?, Coffee is bad for health., "
                    + "Its good to work on coffee, Please select your favourite coffee?, "
                    + "Select your favourite coffee?, Select your favourite coffeee?, "
                    + "Select your favourite coffee?, Select your favourite drink?]");
  }

  /**
   * Tests the inheritance between MultipleChoice and MultipleAnswer.
   */
  @Test
  public void testSort6() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    Question f = new MultipleChoice("Which is your favourite coffee?", "1", options);
    Question h = new MultipleAnswers("Select your favourite coffee?",
            "1 4 5", options);
    Question[] question1 = {f,h};
    Arrays.sort(question1);
    assertEquals(Arrays.toString(question1),
            "[Which is your favourite coffee?, Select your favourite coffee?]");
    Object[] question2 = {h,f};
    Arrays.sort(question2);
    assertEquals(Arrays.toString(question2),
            "[Which is your favourite coffee?, Select your favourite coffee?]");
  }

  /**
   * Tests if the symmetry is maintained in equals in
   * the case of MultipleChoice and MultipleAnswers.
   */
  @Test
  public void testEquals1() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    Question f = new MultipleChoice("Which is your favourite coffee?", "1", options);
    Question h = new MultipleAnswers("Select your favourite coffee?",
            "1 4 5", options);
    assertEquals(f.equals(h), false);
    assertEquals(h.equals(f), false);
  }

  /**
   * Tests if the equals method holds for YesAndNo.
   */
  @Test
  public void testEquals2() {
    Question a = new YesAndNo("Is coffee good for health?", "Yes");
    Question b = new YesAndNo("Is caffeine bad for health?", "No");
    assertEquals(a.equals(b), true);
    assertEquals(b.equals(a), true);
  }

  /**
   * Tests if equal holds for two different class objects.
   */
  public void testEquals3() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    Question a = new YesAndNo("Is coffee good for health?", "Yes");
    Question h = new MultipleAnswers("Select your favourite coffee?",
            "1 4 5", options);
    assertEquals(a.equals(h), false);
    assertEquals(h.equals(a), false);
    Question f = new MultipleChoice("Which is your favourite coffee?", "1", options);
    assertEquals(a.equals(f), false);
    assertEquals(f.equals(a), false);
    Question c = new Likert("Coffee is bad for health.");
    assertEquals(a.equals(c), false);
    assertEquals(c.equals(a), false);
  }

  /**
   * Tests if two objects belong to the same class.
   */
  public void testEquals4() {
    String[] options = {"1", "2", "3", "4", "5", "6", "7", "8"};
    Question a = new YesAndNo("Is coffee good for health?", "Yes");
    Question b = new YesAndNo("Is caffeine bad for health?", "No");
    assertEquals(a.equals(b), true);
    assertEquals(b.equals(a), true);
    Question c = new Likert("Coffee is bad for health.");
    Question d = new Likert("Its good to work on coffee");
    assertEquals(c.equals(d), true);
    assertEquals(d.equals(c), true);
    Question e = new MultipleChoice("Select your favourite coffee?", "1", options);
    Question g = new MultipleChoice("Please select your favourite coffee?",
            "2", options);
    assertEquals(e.equals(g), true);
    assertEquals(g.equals(e), true);
    Question h = new MultipleAnswers("Select your favourite coffee?",
            "1 4 5", options);
    Question i = new MultipleAnswers("Select your favourite drink?",
            "1 6 5", options);
    assertEquals(h.equals(i), true);
    assertEquals(i.equals(h), true);
  }

}
