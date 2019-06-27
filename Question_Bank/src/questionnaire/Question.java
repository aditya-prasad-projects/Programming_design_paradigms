package questionnaire;

/**
 * Provides methods to evaluate answers to a question and to get the question.
 * Also helps to sort objects belonging to it.
 * Provides a method to check if two objects belong to the same class.
 */
public interface Question extends Comparable<Question> {

  /**
   * Evaluates the user's answer and returns the result.
   * @param userAnswer Accepts the user's answer and evaluates it.
   * @return returns "Correct" or "Incorrect" depending on the user's answer.
   */
  String evaluate(String userAnswer);

  /**
   * A Getter method for the question with options.
   * @return Returns the question with the options.
   */
  String getQuestion();

  /**
   * providing a functionality for users to check if two objects belong to the same class.
   * Returns true if two objects belong to the same class. False otherwise.
   * @param other the object to be checked with.
   * @return true if both objects belong to the same class, false if they don't.
   */
  @Override
  boolean equals(Object other);

  /**
   * Calculates the hashCode based on the object parameters.
   * @return the hashCode value.
   */
  @Override
  int hashCode();
}
