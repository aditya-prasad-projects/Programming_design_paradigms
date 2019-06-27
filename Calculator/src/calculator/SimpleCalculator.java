package calculator;

/**
 * Performs basic operations on a calculator, doesnt assume missing inpits.
 */
public class SimpleCalculator extends AbstractCalculator {


  /**
   * Creates a SimpleCalculator object and sets the display to "".
   */
  public SimpleCalculator() {
    super();
  }

  @Override
  protected void setFlags(char ch) {
    addDigits(ch);
  }

  @Override
  protected void initialize() {
    clear();
  }

  @Override
  protected void performOperation(char ch) {
    if (result.length() == 0) {
      throw new IllegalArgumentException("Cant start with +, -, * or =");
    }
    else if (this.opcount == 0) {
      firstOperand(ch);
    }
    else if (this.opcount == 1) {
      consecutiveOperand();
      b = Long.parseLong(result.substring(oplength + 1));
      operate();
      result = result + Character.toString(ch);
      op = ch;
    }
  }

  @Override
  protected void evaluateExpression() {
    if (result.length() == 0) {
      throw new IllegalArgumentException("Cant start with +, -, * or =");
    }
    if (this.opcount == 1) {
      consecutiveOperand();
      b = Long.parseLong(result.substring(oplength + 1));
      operate();
      opcount = 0;
      flag3 = 1;
    } else if (this.opcount == 0 && flag3 == 0) {
      throw new IllegalArgumentException("Cant start with +, -, * or =.");
    }
  }
}

