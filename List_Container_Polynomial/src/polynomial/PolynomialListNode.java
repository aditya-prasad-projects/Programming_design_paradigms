package polynomial;

public interface PolynomialListNode {
  /**
   * Return the number of PolynomialVariables in this list.
   * @return the size of this list
   */
  int count();

  /**
   * We can add a term to a existing polynomial. It mutates the existing polynomial.
   * @param p PolynomialVariable to be added to the current Polynomial.
   */
  PolynomialListNode addTerm(PolynomialVariable p);

  /**
   * Gets the degree of the PolynomialListNodes.
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
   * Used to derivate on a polynomialListNode.
   * Does not mutate the polynomial.
   * @return the derivated polynomial
   */
  PolynomialListNode derivative();

  /**
   * Evaluates the PolynomialListNode with the parameter passed.
   * @param valueOfX the value to evaluate the polynomial with.
   * @return return the resulting value of the evaluated polynomial.
   */
  double evaluate(double valueOfX);

  /**
   * Returns the PolynomialVariable associated with the current node.
   * @return The PolynomialVariable associated with the current node.
   */
  PolynomialVariable getVariable();

  /**
   * Adds the current and p1 PolynomialListNodes to p.
   * @param p The Polynomial that will have the result.
   * @param p1 The PolynomialListNode to be added to the current node.
   * @return p , The result of the addition.
   */
  PolynomialListNode addPolynomial(Polynomial p, PolynomialListNode p1);
}
