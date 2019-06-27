import org.junit.Test;
import org.junit.Before;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Used to test the PolynomialImpl test.
 */
public class PolynomialImplTest {
  Polynomial polynomial;
  Polynomial polynomial1;

  @Before
  public void setup() {
    polynomial = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    polynomial1 = new PolynomialImpl();
  }

  /**
   * Tests if the polynomial was created correctly.
   */
  @Test
  public void testtoString() {
    assertEquals(polynomial.toString(), "-2x^5+1x^4+11x^1-5");
    assertEquals(polynomial1.toString(), "0");
  }

  /**
   * Checks if AddTerm works correctly.
   */
  @Test
  public void testAddTerm() {
    polynomial.addTerm(3, 2);
    polynomial.addTerm(4, 6);
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-4, 6);
    assertEquals(polynomial.toString(), "-2x^5+1x^4+3x^2+11x^1-4");
    polynomial.addTerm(0, 6);
  }

  /**
   * Checks AddTerm for power 0.
   */
  @Test
  public void testAddTerm1() {
    polynomial1.addTerm(1, 0);
    assertEquals(polynomial1.toString(), "1");
  }

  /**
   * Checks AddTerm for coefficient 0.
   */
  @Test
  public void testAddTerm2() {
    polynomial1.addTerm(0, 0);
    polynomial1.addTerm(0, 6);
    assertEquals(polynomial1.toString(), "0");
  }

  /**
   * Tests if we can add a valid term to the polynomial.
   */
  @Test
  public void testaddTerm() {
    polynomial.addTerm(7, 2);
    assertEquals(polynomial.toString(), "-2x^5+1x^4+7x^2+11x^1-5");
  }

  /**
   * Tests if adding an invalid term throws an exception.
   */
  @Test
  public void testaddTermInvalidTerm() {
    try {
      polynomial.addTerm(-7, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid arguments");
    }
  }

  /**
   * Tests if we can add a negative  coefficient to the polynomial.
   */
  @Test
  public void testaddTermNegativeInput() {
    polynomial.addTerm(-1, 2);
    assertEquals(polynomial.toString(), "-2x^5+1x^4-1x^2+11x^1-5");
  }

  /**
   * Tests if we can add a term with power zero.
   */
  @Test
  public void testaddTermWithPowerZero() {
    polynomial.addTerm(1, 0);
    assertEquals(polynomial.toString(), "-2x^5+1x^4+11x^1-4");
  }

  /**
   * Tests if getDegree returns the correct degree of the polynomial.
   */
  @Test
  public void testgetDegree() {
    assertEquals(polynomial.getDegree(), 5);
  }

  /**
   * Tests getDegree when degree is zero.
   */
  @Test
  public void testgetDegreeZero() {
    Polynomial p = new PolynomialImpl();
    assertEquals(p.getDegree(), 0);
  }

  /**
   * Tests if getCoefficient returns correct coefficient.
   */
  @Test
  public void testgetCoefficient() {
    assertEquals(polynomial.getCoefficient(5), -2);
  }

  /**
   * Test if getCoefficient returns zero, if power is not present.
   */
  @Test
  public void testGetCoefficientNotPresent() {

    assertEquals(polynomial.getCoefficient(3),
            0);
  }

  /**
   * Tests if getCoefficient works correctly when power is zero.
   */
  @Test
  public void testGetCoefficientPowerZero() {
    assertEquals(polynomial.getCoefficient(0), -5);
  }

  /**
   * Tests if evaluate works correctly.
   */
  @Test
  public void testEvaluate() {
    assertEquals(polynomial.evaluate(1.0), 5.0, 0.001);
  }

  /**
   * Tests if evaluate works for x = 0.
   */
  @Test
  public void testEvaluateZero() {
    assertEquals(polynomial.evaluate(0), -5.0, 0.001);
  }

  /**
   * Tests evaluate with a different number.
   */
  @Test
  public void testEvaluate1() {
    assertEquals(polynomial.evaluate(2), -31, 0.001);
  }

  /**
   * Tests evaluate on an empty polynomial.
   */
  @Test
  public void testEvaluate2() {
    assertEquals(polynomial1.evaluate(0), 0, 0.001);
    assertEquals(polynomial1.evaluate(2), 0, 0.001);
  }

  /**
   * Tests if evaluate works for a decimal number.
   */
  @Test
  public void testEvaluateDecimal() {
    assertEquals(polynomial.evaluate(243142423.34243242), -1.6995490348729834E42, 0.001);
  }

  /**
   * Tests add function with two parameters.
   */
  @Test
  public void testAdd() {
    Polynomial p = new PolynomialImpl("2x^4 -3x^4");
    Polynomial q = polynomial.add(p);
    assertEquals(q.toString(), "-2x^5+11x^1-5");
  }


  /**
   * Tests if derivative function works correctly.
   */
  @Test
  public void testDerivative() {
    Polynomial q = polynomial.derivative();
    assertEquals(q.toString(), "-10x^4+4x^3+11");
    assertEquals(polynomial.toString(), "-2x^5+1x^4+11x^1-5");
    Polynomial z = q.derivative();
    assertEquals(z.toString(), "-40x^3+12x^2");
    assertEquals(q.toString(), "-10x^4+4x^3+11");
    Polynomial r = z.derivative();
    assertEquals(r.toString(), "-120x^2+24x^1");
    Polynomial t = r.derivative();
    assertEquals(t.toString(), "-240x^1+24");
    Polynomial u = t.derivative();
    assertEquals(u.toString(), "-240");
    Polynomial v = u.derivative();
    //assertEquals(v.toString(),"0");
    assertEquals(v.derivative().toString(), "0");
  }

  /**
   * Tests if toString works correctly for two PolynomialVariables.
   */
  @Test
  public void test1() {
    Polynomial p = new PolynomialImpl("-120x^2 +24x^1");
    assertEquals(p.toString(), "-120x^2+24x^1");
    Polynomial q = new PolynomialImpl("-240x^1 +24");
    assertEquals(q.toString(), "-240x^1+24");
  }

  /**
   * Tests if equals works correctly.
   */
  @Test
  public void testEquals() {
    Polynomial p = new PolynomialImpl("-120x^2 +24x^1");
    Polynomial q = new PolynomialImpl("-120x^2 +24x^1");
    assertEquals(p.equals(q), true);
    assertEquals(q.equals(p), true);
  }

  /**
   * Tests equals on two Empty Polynomials.
   */
  @Test
  public void testEquals1() {
    Polynomial p = new PolynomialImpl();
    Polynomial q = new PolynomialImpl();
    assertEquals(p.equals(q), true);
    assertEquals(q.equals(p), true);
  }

  /**
   * Tests equals on a empty and a valid Polynomial.
   */
  @Test
  public void testEquals2() {
    Polynomial p = new PolynomialImpl();
    Polynomial q = p;
    assertEquals(p.equals(q), true);
    assertEquals(q.equals(p), true);
  }

  /**
   * Checks symmetric property of equals.
   */
  @Test
  public void testEquals3() {
    Polynomial p = new PolynomialImpl("-120x^2 +24x^1");
    Polynomial q = new PolynomialImpl();
    assertEquals(p.equals(q), false);
    assertEquals(q.equals(p), false);
  }

  /**
   * Tests addTerm.
   */
  @Test
  public void teswtAdd1() {
    polynomial1.addTerm(2, 1);
    //assertEquals(polynomial1.toString(),"");
    polynomial1.addTerm(2, 2);
    assertEquals(polynomial1.toString(), "2x^2+2x^1");
  }

  /**
   * Checks if a polynomial was created successfully.
   */
  @Test
  public void testAdd2() {
    Polynomial p = new PolynomialImpl("5x^2 +4x^1 -2");
    assertEquals(p.toString(), "5x^2+4x^1-2");
  }

  /**
   * Checks addTerm on a polynomial with power 0.
   */
  @Test
  public void testAdd3() {
    Polynomial p = new PolynomialImpl("2");
    p.addTerm(3, 0);
    p.addTerm(4, 0);
    p.addTerm(4, 1);
    assertEquals(p.toString(), "4x^1+9");
  }

  /**
   * Tests add function on polynomials of equal size.
   */
  @Test
  public void testAdd4() {
    Polynomial p = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals(polynomial.add(p).toString(), "-4x^5+2x^4+22x^1-10");
    assertEquals(polynomial.toString(), "-2x^5+1x^4+11x^1-5");
  }

  /**
   * Tests add polynomial by adding a single variable.
   */
  @Test
  public void testAdd6() {
    Polynomial p1 = new PolynomialImpl("-4x^5");
    assertEquals(polynomial.add(p1).toString(), "-6x^5+1x^4+11x^1-5");
  }

  /**
   * Tests if add works for three PolynomialVariables.
   */
  @Test
  public void testAdd10() {
    Polynomial p1 = new PolynomialImpl("-4x^5 +3x^4 -10");
    assertEquals(p1.add(polynomial).toString(), "-6x^5+4x^4+11x^1-15");
  }

  /**
   * Tests if add works for two PolynomialVariables.
   */
  @Test
  public void testAdd9() {
    Polynomial p1 = new PolynomialImpl("-4x^5 +3x^4");
    assertEquals(polynomial.add(p1).toString(), "-6x^5+4x^4+11x^1-5");
    assertEquals(p1.add(polynomial).toString(), "-6x^5+4x^4+11x^1-5");
  }

  /**
   * Tests addPolynomial on a empty polynomial.
   */
  @Test
  public void testAdd5() {
    Polynomial p = new PolynomialImpl();
    assertEquals(polynomial.add(p).toString(), "-2x^5+1x^4+11x^1-5");
  }

  /**
   * Tests two Polynomials with opposite signs.
   */
  @Test
  public void testAdd11() {
    Polynomial p = new PolynomialImpl("-1x^2");
    Polynomial p1 = new PolynomialImpl("1x^2");
    assertEquals(p1.add(p).toString(), "0");
  }

  /**
   * Checks for negative power.
   */
  @Test
  public void testaddTerm1() {
    Polynomial p = new PolynomialImpl();
    try {
      p.addTerm(9,-1);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid arguments");
    }
  }

  /**
   * Tests addTerm for 0 coefficient.
   */
  @Test
  public void testAddTerm10() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(0,1);
    assertEquals(p.toString(), "0");
  }

  /**
   * Tests addTerm power = 0, coefficient = 0.
   */
  @Test
  public void testAddTerm11() {
    Polynomial p = new PolynomialImpl();
    p.addTerm(0,0);
    assertEquals(p.toString(), "0");
  }

  /**
   * Tests creation of a polynomial with zero coefficient.
   */
  @Test
  public void testAddTerm12() {
    Polynomial p = new PolynomialImpl("0x^1");
    assertEquals(p.toString(), "0");
  }

  /**
   * Tests for an entire operation on empty polynomials.
   */
  @Test
  public void testPolynomial() {
    Polynomial p = new PolynomialImpl();
    assertEquals(p.toString(), "0");
    p.addTerm(1,0);
    p.addTerm(2,2);
    p.addTerm(4,1);
    p.addTerm(6,0);
    p.addTerm(8978,8987);
    p.addTerm(4,567);
    assertEquals(p.toString(), "8978x^8987+4x^567+2x^2+4x^1+7");
    assertEquals(p.getCoefficient(0), 7);
    assertEquals(p.getDegree(), 8987);
    assertEquals(p.getCoefficient(3), 0);
    assertEquals(p.getCoefficient(-3), 0);
    assertEquals(p.evaluate(0), 7.0, 0.001);
    assertEquals(p.evaluate(-1),-8977.0, 0.001);
    Polynomial p1 = new PolynomialImpl("3x^2 +0");
    Polynomial p2 = new PolynomialImpl();
    assertEquals(p.add(p1).toString(), "8978x^8987+4x^567+5x^2+4x^1+7");
    assertEquals(p.add(p).toString(),"17956x^8987+8x^567+4x^2+8x^1+14");
    assertEquals(p.toString(), "8978x^8987+4x^567+2x^2+4x^1+7");
    assertEquals(p.add(p).toString(), "17956x^8987+8x^567+4x^2+8x^1+14");
    assertEquals(p1.add(p).toString(), "8978x^8987+4x^567+8x^2+4x^1+7");
    p.addTerm(0,1);
    assertEquals(p.toString(), "8978x^8987+4x^567+2x^2+4x^1+7");
    assertEquals(p.add(p2).toString(), "8978x^8987+4x^567+2x^2+4x^1+7");
    assertEquals(p.toString(), "8978x^8987+4x^567+2x^2+4x^1+7");
  }

  /**
   * Tests entire Polynomial operation on existing polynomial.
   */
  @Test
  public void testPolynomial1() {
    Polynomial p = new PolynomialImpl("3x^2 +2x^2 +3x^21");
    assertEquals(p.toString(), "3x^21+5x^2");
    p.addTerm(1, 0);
    p.addTerm(2, 2);
    p.addTerm(4, 1);
    p.addTerm(6, 0);
    p.addTerm(8978, 8987);
    p.addTerm(4, 567);
    assertEquals(p.toString(), "8978x^8987+4x^567+3x^21+7x^2+4x^1+7");
    assertEquals(p.getCoefficient(0), 7);
    assertEquals(p.getDegree(), 8987);
    assertEquals(p.getCoefficient(3), 0);
    assertEquals(p.getCoefficient(-3), 0);
    assertEquals(p.evaluate(0), 7.0, 0.001);
    assertEquals(p.evaluate(-1), -8975.0, 0.001);
    Polynomial p1 = new PolynomialImpl("3x^2 +0");
    assertEquals(p.add(p1).toString(), "8978x^8987+4x^567+3x^21+10x^2+4x^1+7");
    assertEquals(p.add(p).toString(), "17956x^8987+8x^567+6x^21+14x^2+8x^1+14");
    assertEquals(p.toString(), "8978x^8987+4x^567+3x^21+7x^2+4x^1+7");
    assertEquals(p.add(p).toString(), "17956x^8987+8x^567+6x^21+14x^2+8x^1+14");
    assertEquals(p1.add(p).toString(), "8978x^8987+4x^567+3x^21+13x^2+4x^1+7");
    p.addTerm(0, 1);
    assertEquals(p.toString(), "8978x^8987+4x^567+3x^21+7x^2+4x^1+7");
    assertEquals(p.toString(), "8978x^8987+4x^567+3x^21+7x^2+4x^1+7");
  }
}










