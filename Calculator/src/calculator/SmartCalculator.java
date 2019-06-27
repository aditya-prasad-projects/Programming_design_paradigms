package calculator;

/**
 * Performs Calculator operations, works like a windows or a mac calculator, with +, - and *
 * operators.
 * Does not take in negative operands.
 */
public class SmartCalculator extends AbstractCalculator {

  private int flag1;
  private int flag2;
  private int flag5;

  /**
   * Creates a SmartCalculator object and initializes display to "".
   */
  public SmartCalculator() {
    super();
    flag1 = 0;
    flag2 = 1;
    flag5 = 0;
  }

  @Override
  protected void setFlags(char ch) {
    addDigits(ch);
    if (flag1 == 0) {
      flag1 = 1;
    }
    else if (flag2 == 0) {
      flag2 = 1;
    }
  }

  @Override
  protected void evaluateExpression() {
    if (opcount == 1) {
      if (flag2 == 0) {
        b = a;
      }
      else {
        b = Long.parseLong(result.substring(oplength + 1));
      }
      operateSmart();
      opcount = 0;
      flag3 = 1;
    }
    else if (flag3 == 1) {
      operateSmart();
      opcount = 0;
      flag2 = 0;
    }
  }

  @Override
  protected void performOperation(char ch) {
    if (flag1 == 1 && opcount == 0) {
      firstOperand(ch);
      flag2 = 0;
    }
    else if (opcount == 1) {
      if (result.substring(result.length() - 1).equals("+")
              || result.substring(result.length() - 1).equals("-")
              || result.substring(result.length() - 1).equals("*")) {
        op = ch;
        result = result.substring(0, result.length() - 1);
        result = result + Character.toString(ch);
      }
      else if (flag2 == 1) {
        operateSmart();
        result = result + Character.toString(ch);
        op = ch;
        flag2 = 0;
      }
    }
  }

  @Override
  protected void initialize() {
    clear();
    flag1 = 0;
    flag2 = 1;
    flag5 = 0;
  }
}