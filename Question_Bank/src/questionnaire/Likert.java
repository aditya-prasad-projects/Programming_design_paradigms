package questionnaire;

import java.util.Objects;

/**
 * Creates Likert type questions, accepts valid answer and evaluates it.
 */
public class Likert extends AbstractQuestion {

  /**
   * Initializes the Likert type question.
   * @param text represents question text.
   */
  public Likert(String text) {
    super(text);
    priority = 2;
  }

  @Override
  public String evaluate(String userAnswer) {
    this.userAnswer = userAnswer;
    for (LikertEnum e : LikertEnum.values()) {
      if (e.getEnum().equals(userAnswer)) {
        this.evaluatedResult = "Correct";
        return evaluatedResult;
      }
    }
    this.evaluatedResult = "Incorrect";
    return evaluatedResult;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Question) {
      AbstractQuestion aQuestion = (AbstractQuestion) other;
      return aQuestion.equalLikert();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public String getQuestion() {
    return this.text + " - " + "Strongly Agree, Agree, Neither Agree nor Disagree,"
            + " Disagree, Strongly Disagree";
  }

  @Override
  protected boolean equalLikert() {
    return true;
  }
}
