package polynomial;

/**
 * It provides the user with functions that operate on polyomials.
 * Two polynomials are equal if they inherit from this interface, belong to the same class and
 * their coefficient and power are equal.
 *
 */
public interface Polynomial {

  /**
   * We can add a term to a existing polynomial. It mutates the existing polynomial.
   * @param coefficient coefficient of the term to be added.
   * @param power power of the term to be added.
   */
  void addTerm(int coefficient, int power);

  /**
   * Gets the degree of the polynomial.
   * @return returns the degree of the Polynomial.
   */
  int getDegree();

  /**
   * Returns the coefficient of the power passed to it. Returns zero if
   * the power is invalid or the coefficient does not exist.
   * @param power the power of the term, whose coefficient is to be returned.
   * @return returns the coefficient of the power specified.
   */
  int getCoefficient(int power);

  /**
   * Evaluates the polynomial with the parameter passed.
   * @param valueOfX the value to evaluate the polynomial with.
   * @return return the resulting value of the evaluated polynomial.
   */
  double evaluate(double valueOfX);

  /**
   * Used to add two polynomials of the same type.
   * Can only add polynomial of the same class.
   * Does not mutate the current polynomial or the polynomial passed to the function.
   * @param p The Polynomial to be added to the current polynomial.
   * @return the resultant polynomial.
   */
  Polynomial add(Polynomial p);

  /**
   * Used to derivate on a polynomial.
   * Does not mutate the polynomial.
   * @return the derivated polynomial
   */
  Polynomial derivative();
}
