package questionnaire;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Creates yes-no type of questions.
 * Evaluates the user's answer and returns the result.
 */
public class YesAndNo extends AbstractQuestion {

  /**
   * Initializes the  YesAndNo type of question.
   * @param text Represents the text of the question.
   * @param answer Represents the correct answer for the question.
   */
  public YesAndNo(String text, String answer) {
    super(text, answer);
    priority = 1;
  }

  @Override
  public String evaluate(String userAnswer) throws IllegalArgumentException {
    this.userAnswer = userAnswer;
    if ((Pattern.matches("[Yy][Ee][Ss]", this.userAnswer)
            && Pattern.matches("[Yy][Ee][Ss]", this.correctAnswer))
            || (Pattern.matches("[Nn][oO]", this.userAnswer)
            && Pattern.matches("[Nn][oO]", this.correctAnswer))) {
      this.evaluatedResult = "Correct";
    }
    else {
      this.evaluatedResult = "Incorrect";
    }
    return this.evaluatedResult;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Question) {
      AbstractQuestion aQuestion = (AbstractQuestion) other;
      return aQuestion.equalYN();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, correctAnswer);
  }

  @Override
  public String getQuestion() {
    return text;
  }

  @Override
  protected boolean equalYN() {
    return true;
  }
}
