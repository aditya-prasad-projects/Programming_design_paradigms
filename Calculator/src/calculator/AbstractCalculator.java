package calculator;

abstract class AbstractCalculator implements Calculator {
  protected long a;
  protected long b;
  protected String result;
  protected int opcount;
  protected char op;
  protected int oplength;
  protected int flag3;
  protected int flag5;


  /**
   * Initializes Calculator object.
   */
  protected AbstractCalculator() {
    this.result = "";
    this.opcount = 0;
    this.oplength = 0;
    flag3 = 0;
    flag5 = 0;
  }

  /**
   * Used to perform operations on simple calculator.
   */
  protected void operate() {

    if (op == '+') {
      if ((a + b) > Integer.MAX_VALUE || (a + b) < Integer.MIN_VALUE) {
        result = "0";
      }
    }
    else if (op == '-') {
      if ((a - b) > Integer.MAX_VALUE || (a - b) < Integer.MIN_VALUE) {
        result = "0";
      }
    }
    else if (op == '*') {
      if ((a * b) > Integer.MAX_VALUE || (a * b) < Integer.MIN_VALUE) {
        result = "0";
      }
    }
    basicOp();
    if (!result.equals("0")) {
      result = Long.toString(this.a);
    }
    setOplength();
  }

  /**
   * Performs basic operations.
   */
  protected void basicOp() {
    if (op == '+') {
      this.a = this.a + this.b;
    }
    else if (op == '-') {
      this.a = this.a - this.b;
    }
    else if (op == '*') {
      this.a = this.a * this.b;
    }
  }

  /**
   * Sets the oplength.
   */
  protected void setOplength() {
    oplength = result.length();
  }

  /**
   * Operations on smart calculator.
   */
  protected void operateSmart() {
    if (op == '+') {
      if ((a + b) > Integer.MAX_VALUE || (a + b) < Integer.MIN_VALUE) {
        result = "0";
        flag5 = 1;
      }
    }
    else if (op == '-') {
      if ((a - b) > Integer.MAX_VALUE || (a - b) < Integer.MIN_VALUE) {
        result = "0";
        flag5 = 1;
      }
    }
    else if (op == '*') {
      if ((a * b) > Integer.MAX_VALUE || (a * b) < Integer.MIN_VALUE) {
        result = "0";
        flag5 = 1;
      }
    }
    basicOp();
    if (!result.equals("0") || flag5 == 0) {
      result = Long.toString(this.a);
    }
    setOplength();
  }

  /**
   * Helper method: To clear display when 'C' is given as input.
   */
  protected void clear() {
    result = "";
    opcount = 0;
    oplength = 0;
    flag3 = 0;
  }

  /**
   * Check for two consecutive operands.
   */
  protected void consecutiveOperand() {
    if (result.substring(result.length() - 1).equals("+")
            || result.substring(result.length() - 1).equals("-")
            || result.substring(result.length() - 1).equals("*")) {
      throw new
              IllegalArgumentException("Invalid Input: It cannot be two consecutive operands");
    }
  }

  /**
   * overflow check.
   */
  protected void overflowCheck() {
    if (opcount == 0) {
      a = Long.parseLong(result);
      if (a > Integer.MAX_VALUE) {
        result = result.substring(0, result.length() - 1);
        throw new RuntimeException("variable overflow");
      }
    }
    else if (opcount == 1) {
      b = Long.parseLong(result.substring(oplength + 1));
      if (b > Integer.MAX_VALUE) {
        result = result.substring(0, result.length() - 1);
        throw new RuntimeException("variable overflow");
      }
    }
  }

  /**
   * Initialize first operand.
   */
  protected void firstOperand(char ch) {
    this.oplength = this.result.length();
    a = Long.parseLong(this.result);
    this.result = this.result + Character.toString(ch);
    this.opcount = 1;
    op = ch;
  }

  /**
   * Checks if it is an operator or not.
   * @param ch Represents the character entered.
   * @return true if it is a operator, false otherwise.
   */
  protected boolean isAOperator(char ch) {
    return (ch == '+' || ch == '-' || ch == '*');
  }

  @Override
  public String getResult() {
    return this.result;
  }

  /**
   * Adds digits to the result.
   * @param ch Represents the character entered.
   */
  protected void addDigits(char ch) {
    result = result + Character.toString(ch);
    overflowCheck();
  }

  @Override
  public Calculator input(char ch) throws IllegalArgumentException,RuntimeException {
    if (Character.isDigit(ch)) {
      setFlags(ch);
    }

    else if (isAOperator(ch)) {
      performOperation(ch);
    }

    else if (ch == '=') {
      evaluateExpression();
    }

    else if (ch == 'C') {
      initialize();
    }

    else {
      throw new IllegalArgumentException("Inputs can be only numbers or +,-,*");
    }
    return this;
  }

  /**
   * Called when ch is a digit.
   * @param ch Represents the character entered by the user.
   */
  protected abstract void setFlags(char ch);

  /**
   * Performs an operation on operands.
   * @param ch Represents the character entered by the user.
   */
  protected abstract void performOperation(char ch);

  /**
   * Evaluates the expression.
   */
  protected abstract void evaluateExpression();

  /**
   * Used to initialize the display when 'C' is pressed.
   */
  protected abstract void initialize();
}

