package polynomial;

import java.util.Objects;

/**
 * Represents a Polynomial Variable in the Polynomial Equation.
 */
public class PolynomialVariable {
  private int coefficient;
  private int power;

  PolynomialVariable(int coefficient, int power) {
    this.coefficient = coefficient;
    this.power = power;
  }

  /**
   * Returns the coefficient of the PolynomialVariable.
   * @return
   */
  int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Returns the power of the PolynomialVariable.
   * @return
   */
  int getPower() {
    return this.power;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coefficient, power);
  }

  @Override
  public String toString() {
    if (this.power == 0 && this.coefficient > 0) {
      return ("+" + this.coefficient);
    }
    if (this.power == 0) {
      return Integer.toString(this.coefficient);
    }
    if (this.coefficient > 0) {
      return ("+" + this.coefficient + "x^" + this.power);
    }
    return (this.coefficient + "x^" + this.power);
  }

  /**
   * Used to check if the current PolynomialVariable is greater than the Parameter passed to it.
   * @param variable The PolynomialVariable whose power is to
   *                 be checked with the current PolynomialVariable.
   * @return returns true if the current PolynomialVariable's power is greater
   *         than the power of the PolynomialVariable passed to it.
   */
  boolean greaterThanPower(PolynomialVariable variable) {
    return this.power > variable.power;
  }

  /**
   * Used to check if the current PolynomialVariable's power is equal to the Parameter passed to it.
   * @param variable The PolynomialVariable whose power is to
   *                 be checked with the current PolynomialVariable.
   * @return returns true if the current PolynomialVariable's power is equal
   *         to the power of the PolynomialVariable passed to it.
   */
  boolean equalPower(PolynomialVariable variable) {
    return this.power == variable.power;
  }

  /**
   * Used to set the coefficient of the PolynomialVariable.
   * Used when one more Polynomial is added to it.
   * @param coefficient The coefficient to be added to the current Polynomial.
   */
  void setCoefficient(int coefficient) {
    this.coefficient = this.coefficient + coefficient;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PolynomialVariable)) {
      return false;
    }
    PolynomialVariable other = (PolynomialVariable) o;
    return this.coefficient == other.getCoefficient()
            && (this.power == other.getPower());
  }

  /**
   * Used to perform derivative on the current PolynomialVariable.
   * @return returns the derived PolynomialVariable.
   */
  PolynomialVariable derivative() {
    return new PolynomialVariable(this.coefficient * this.power, this.power - 1);
  }

  /**
   * Used to evaluate the PolynomialVariable with the parameter passed to it.
   * @param valueOfX the value used to evaluate the PolynomialVariable.
   * @return the evaluated PolynomialVariable.
   */
  double evaluate(double valueOfX) {
    if (this.power == 0) {
      return this.coefficient;
    }
    return Math.pow(valueOfX, this.power) * this.coefficient;
  }
}
