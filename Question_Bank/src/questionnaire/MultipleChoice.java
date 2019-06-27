package questionnaire;

import java.util.Objects;

/**
 * Creates MultipleChoice question types, extends MultipleAnswers.
 * Evaluates the answer entered by the user and returns the result.
 */
public class MultipleChoice extends MultipleAnswers {

  /**
   * Initializes the MultipleChoice question type.
   * @param text Represents the text of the question.
   * @param answer Represents the correct answer for the question.
   * @param options Represents the different options available.
   */
  public MultipleChoice(String text, String answer, String... options)
          throws IllegalArgumentException {
    super(text,answer,options);
    priority = 3;
    countOfSubClasses = 3;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Question) {
      AbstractQuestion aQuestion = (AbstractQuestion) other;
      return aQuestion.equalMC();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, correctAnswer, options);
  }

  @Override
  protected boolean equalMC() {
    return true;
  }

  @Override
  protected boolean equalMA() {
    return false;
  }
}
