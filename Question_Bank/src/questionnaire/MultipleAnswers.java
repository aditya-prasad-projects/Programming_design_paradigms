package questionnaire;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Create a Multiple Answers question type.
 * Evaluates the user's answers and returns the result.
 */
public class MultipleAnswers extends AbstractQuestion {

  /**
   * Initializes the MultipleAnswer question type.
   * @param text Represents the text of the question.
   * @param answer Represents the correct answer for the question.
   * @param options Represents the different options available.
   * @throws IllegalArgumentException when a question is created whose options are not between 1 & 8
   */
  public MultipleAnswers(String text, String answer, String... options)
          throws IllegalArgumentException {
    super(text, answer, options);
    String[] check = this.correctAnswer.split(" ");
    for (String i:check) {
      if (!Pattern.matches("[1-8]", i)) {
        throw new IllegalArgumentException("The answer has to be from 1-8");
      }
    }
    priority = 4;
    countOfSubClasses = 4;
  }

  @Override
  public String evaluate(String userAnswer) {
    this.userAnswer = userAnswer;
    String[] correctAnswer1 = this.correctAnswer.split(" " + "+");
    String[] individual = this.userAnswer.split(" " + "+" );
    Arrays.sort(individual);
    Arrays.sort(correctAnswer1);
    if (Arrays.equals(individual, correctAnswer1)) {
      evaluatedResult = "Correct";
    }
    else {
      evaluatedResult = "Incorrect";
    }
    return evaluatedResult;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Question) {
      AbstractQuestion aQuestion = (AbstractQuestion) other;
      return aQuestion.equalMA();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, correctAnswer, options);
  }

  @Override
  public String getQuestion() {
    String a = text + " - ";
    for (int i = 0; i < this.options.length; i++) {
      a = a + options[i] + " ";
    }
    return a;
  }

  @Override
  protected boolean equalMA() {
    return true;
  }
}
