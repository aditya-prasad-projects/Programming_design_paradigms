package questionnaire;

/**
 * A abstract class that implements the compareTo function for the subclasses.
 */
abstract class AbstractQuestion implements Question {

  /**
   * Holds the text of the question.
   */
  final String text;

  /**
   * Holds the answer given when creating the object.
   */
  String correctAnswer;

  /**
   * Holds the options available to the user.
   */
  String []options;

  /**
   * Holds the evaluated result of the user's answer.
   */
  String evaluatedResult;

  /**
   * Holds the user's answer.
   */
  String userAnswer;

  /**
   * Holds the priority of the class.
   */
  int priority;

  /**
   * Holds the number of classes extending it.
   * User can use this variable to assign a priority level to a class.
   */
  static int countOfSubClasses = 0;


  AbstractQuestion(String text, String answer) throws IllegalArgumentException {
    this(text);
    if (!answer.equals("Yes") && !answer.equals("No")) {
      throw new IllegalArgumentException("Answer can be only \"Yes\" or \"No\" ");
    }
    this.correctAnswer = answer;
    countOfSubClasses = 1;
  }

  AbstractQuestion(String text) {
    this.text = text;
    countOfSubClasses = 2;
  }

  AbstractQuestion(String text, String answer, String... options) throws IllegalArgumentException {
    this(text);
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("The number of options can only be between 3 and 8");
    }
    if (!options[0].equals("1")) {
      throw new IllegalArgumentException("The option number should start from \"1\" ");
    }
    this.correctAnswer = answer;
    this.options = options;
  }

  /**
   * Returns the question.
   * @return the question entered.
   */
  @Override
  public String toString() {
    return this.text;
  }

  /**
   * Returns the logic of how the objects should be compared.
   * @param question the object being compared.
   * @return the comparison result of two objects.
   */
  @Override
  public int compareTo(Question question) {
    AbstractQuestion question1 = (AbstractQuestion) question;
    if (this.equals(question1)) {
      return this.toString().compareToIgnoreCase(question.toString());
    }
    else {
      return Integer.compare(this.priority, question1.priority);
    }
  }

  /**
   * Checks if object belongs to MultipleChoice class.
   * @return true if object belongs to MultipleChoice class.
   */
  protected boolean equalMC() {
    return false;
  }

  /**
   * Checks if object belongs to YesAndNo class.
   * @return true if object belongs to YesAndNo class.
   */
  protected boolean equalYN() {
    return false;
  }

  /**
   * Checks if object belongs to Likert class.
   * @return true if object belongs to Likert class.
   */
  protected boolean equalLikert() {
    return false;
  }

  /**
   * Checks if object belongs to MultipleAnswer class.
   * @return true if object belongs to MultipleAnswer class.
   */
  protected boolean equalMA() {
    return false;
  }

  /**
   * Used to get the number of classes extending AbstractQuestion.
   * @return
   */
  public int getNumberofClasses() {
    return countOfSubClasses;
  }

  @Override
  public String getQuestion() {
    return this.text;
  }

  @Override
  public abstract String evaluate(String userAnswer);

  @Override
  public abstract boolean equals(Object other);

  @Override
  public abstract int hashCode();
}
