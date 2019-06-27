package calculator;

/**
 * Provides an interface to take in inputs and display the result.
 */
public interface Calculator {

  /**
   * Takes in a single character and performs necessary arithmetic operations.
   * @param ch input character
   * @return Calculator object
   * @throws IllegalArgumentException for a invalid sequence of inputs
   * @throws RuntimeException when a operand overflows.
   */
  Calculator input(char ch);

  /**
   * Returns the value of the result.
   * @return The result that should have been displayed on the display of the calculator.
   */
  String getResult();
}
