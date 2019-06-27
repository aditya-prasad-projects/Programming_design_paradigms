package polynomial;


import java.util.Objects;

public class PolynomialEmptyNode implements PolynomialListNode {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public PolynomialListNode addTerm(PolynomialVariable p) {
    return new PolynomialElementNode(p, this);
  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public PolynomialListNode derivative() {
    return new PolynomialEmptyNode();
  }

  @Override
  public double evaluate(double valueOfX) {
    return 0;
  }


  @Override
  public PolynomialVariable getVariable() {
    return null;
  }

  @Override
  public String toString() {
    return "0";
  }

  @Override
  public boolean equals(Object other) {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(0);
  }

  @Override
  public PolynomialListNode addPolynomial(Polynomial p, PolynomialListNode p2) {
    return new PolynomialEmptyNode();
  }
}
