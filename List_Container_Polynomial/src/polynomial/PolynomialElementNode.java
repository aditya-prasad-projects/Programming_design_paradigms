package polynomial;

import java.util.Objects;


public class PolynomialElementNode implements PolynomialListNode {

  private PolynomialVariable variable;
  private PolynomialListNode rest;

  PolynomialElementNode(PolynomialVariable p, PolynomialListNode rest) {
    this.variable = p;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public PolynomialListNode addTerm(PolynomialVariable p) {
    if (this.variable.equalPower(p)) {
      this.variable.setCoefficient(p.getCoefficient());
      if (this.variable.getCoefficient() == 0) {
        return this.rest;
      }
      return this;
    }
    else if (this.variable.greaterThanPower(p)) {
      return new PolynomialElementNode(this.variable, this.rest.addTerm(p));
    }
    return new PolynomialElementNode(p,this);
  }

  @Override
  public PolynomialVariable getVariable() {
    return this.variable;
  }

  @Override
  public int getDegree() {
    return this.variable.getPower();
  }

  @Override
  public int getCoefficient(int power) {
    if (this.variable.getPower() == power) {
      return this.variable.getCoefficient();
    }
    else {
      return this.rest.getCoefficient(power);
    }
  }

  @Override
  public PolynomialListNode derivative() {
    if (this.variable.getPower() == 0) {
      return new PolynomialEmptyNode();
    }
    return new PolynomialElementNode(this.variable.derivative(), this.rest.derivative());
  }

  @Override
  public double evaluate(double valueOfX) {
    return this.variable.evaluate(valueOfX) + this.rest.evaluate(valueOfX);
  }

  @Override
  public String toString() {
    return variable.toString() + this.rest.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(variable, rest);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof PolynomialListNode)) {
      return false;
    }
    if (this.variable.equals(((PolynomialElementNode) other).variable)) {
      return (this.rest.equals(((PolynomialElementNode)other).rest));
    }
    else {
      return false;
    }
  }

  @Override
  public PolynomialListNode addPolynomial(Polynomial p, PolynomialListNode p1) {
    p.addTerm(this.variable.getCoefficient(), this.variable.getPower());
    if (!(p1 instanceof PolynomialEmptyNode)) {
      p.addTerm(p1.getVariable().getCoefficient(), p1.getVariable().getPower());
      if (this.rest.getVariable() != null) {
        return this.rest.addPolynomial(p, (((PolynomialElementNode) p1).rest));
      }
    }
    if (this.rest.getVariable() != null) {
      return this.rest.addPolynomial(p,new PolynomialEmptyNode());

    }
    if (!(p1 instanceof PolynomialEmptyNode)) {
      return (((PolynomialElementNode) p1).rest.addPolynomial(p, this));
    }
    return null;
  }




  /**
  @Override
  public PolynomialListNode addPolynomial(Polynomial p, PolynomialListNode p1) {
    p.addTerm(this.variable.getCoefficient(), this.variable.getPower());
    if (!(p1 instanceof PolynomialEmptyNode)) {
      p.addTerm(p1.getVariable().getCoefficient(), p1.getVariable().getPower());
      if (this.rest.getVariable() != null) {
        return this.rest.addPolynomial(p, (((PolynomialElementNode) p1).rest));
      }
    }
    return this.rest.addPolynomial(p, new PolynomialEmptyNode());
  }

  */


}
