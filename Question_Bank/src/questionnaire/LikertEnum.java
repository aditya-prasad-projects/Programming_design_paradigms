package questionnaire;

/**
 * An enumerator to store Likert types.
 */
public enum LikertEnum {
  StronglyAgree("1"), Agree("2"),
  NeitherAgreenorDisagree("3"), Disagree("4"), StronglyDisagree("5");

  String likert;

  /**
   * To get the value of an enum.
   * @return the value of an enum.
   */
  public String getEnum() {
    return likert;
  }

  /**
   * Creates a LikertEnum type and assigns value.
   * @param likert the value of the enum.
   */
  LikertEnum(String likert) {
    this.likert = likert;
  }
}




