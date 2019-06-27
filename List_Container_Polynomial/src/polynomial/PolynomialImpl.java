package polynomial;

import java.util.Objects;
import java.util.Scanner;

/**
 * Used to perform operations on a polynomial.
 * Makes use of inbuilt recursive list implementation to perform
 * operations on different PolynomialVariables.
 */
public class PolynomialImpl implements Polynomial {

  /**
   * Represents the PolynomialVariable with the highest degree in the current Polynomial equation.
   * Represent the head node of the list.
   */
  private PolynomialListNode head;

  /**
   * Create a empty Polynomial.
   */
  public PolynomialImpl() {
    head = new PolynomialEmptyNode();
  }

  /**
   * Creates a Polynomial with the equation passed to it.
   * @param equation Represents the polynomial equation.
   */
  public PolynomialImpl(String equation) {
    head = new PolynomialEmptyNode();
    Scanner sc = new Scanner(equation);
    while (sc.hasNext()) {
      int k;
      int j;
      String i = sc.next();
      if (i.contains("x")) {
        k = Integer.parseInt(i.substring(0, i.indexOf("x")));

        j = Integer.parseInt(i.substring(i.indexOf("x") + 2));
      }
      else {
        k = Integer.parseInt(i);
        j = 0;
      }
      if (k != 0 && j > -1) {
        addTerm(k, j);
      }
      else if (j < 0) {
        throw new IllegalArgumentException("Invalid arguments");
      }
    }
  }

  private PolynomialImpl(PolynomialListNode p) {
    this.head = p;
  }

  @Override
  public void addTerm(int coefficient, int power) {
    if (coefficient != 0 && power > -1) {
      PolynomialVariable p = new PolynomialVariable(coefficient, power);
      head = head.addTerm(p);
    } else if (power < 0) {
      throw new IllegalArgumentException("Invalid arguments");
    }
  }

  @Override
  public int getDegree() {
    return head.getDegree();
  }

  @Override
  public int getCoefficient(int power) {
    return head.getCoefficient(power);
  }

  @Override
  public double evaluate(double valueOfX) {
    return head.evaluate(valueOfX);
  }

  @Override
  public Polynomial add(Polynomial q) {
    Polynomial p1 = new PolynomialImpl();
    this.head.addPolynomial(p1,((PolynomialImpl)q).head);
    if (this.head.count() < (((PolynomialImpl)q).head.count())) {
      for (int i = 0; i < this.getDegree(); i++) {
        if (this.getCoefficient(i) != 0) {
          p1.addTerm(-this.getCoefficient(i), i);
          break;
        }
      }
    }
    return p1;
  }

  @Override
  public int hashCode() {
    return Objects.hash(head);
  }

  @Override
  public Polynomial derivative() {
    return new PolynomialImpl(head.derivative());
  }

  @Override
  public String toString() {
    String z = head.toString();
    char ch = z.charAt(0);
    if (ch == '+') {
      z = z.substring(1);
    }
    if (z.equals("0")) {
      return z;
    }
    else {
      return z.substring(0, z.length() - 1);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof PolynomialImpl)) {
      return false;
    }
    Polynomial p = (PolynomialImpl)other;
    if (p.getDegree() == this.getDegree()) {
      return this.head.equals(((PolynomialImpl) other).head);
    }
    return false;
  }
}
