import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *Tests LookAndSayIterator.
 */
public class LookAndSayIteratorTest {
  private RIterator iterator;


  /**
   * Tests normal operations of LookAndSayIterator.
   */
  @Test
  public void test1() {
    iterator = new LookAndSayIterator(BigInteger.ONE,BigInteger.TEN.add(BigInteger.TEN));
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), BigInteger.ONE);
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(),BigInteger.ONE.add(BigInteger.TEN));
    assertEquals(iterator.hasNext(), false);
  }

  /**
   * Passes -1 to the the seed only Constructor.
   */
  @Test
  public void test2() {
    try {
      iterator = new LookAndSayIterator(BigInteger.ZERO.subtract(BigInteger.ONE));
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Seed value cant be lesser than zero.");
    }
  }

  /**
   * Tests by passing no value to the constructor.
   */
  @Test
  public void test3() {
    iterator = new LookAndSayIterator();
    assertEquals(iterator.next(), new BigInteger("1"));
    assertEquals(iterator.next(), new BigInteger("11"));
    assertEquals(iterator.prev(), new BigInteger("21"));
    assertEquals(iterator.prev(), new BigInteger("11"));
    assertEquals(iterator.hasPrevious(), false);
    try {
      assertEquals(iterator.prev(), new BigInteger("1"));
      fail();
    }
    catch (NoSuchElementException e) {
      assertEquals(e.getMessage(), "Cant call previous on odd numbers");
    }
  }

  /**
   * Tests by passing null to the seed in the Constructor.
   */
  @Test
  public void test4() {
    try {
      iterator = new LookAndSayIterator(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Passed null value");
    }
  }

  /**
   * Tests by passing null in place of endValue.
   */
  @Test
  public void test5() {
    try {
      iterator = new LookAndSayIterator(BigInteger.ONE, null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Passed null value");
    }
  }

  /**
   * Tests for when null values are passed for seed and end value to the constructor.
   */
  @Test
  public void test6() {
    try {
      iterator = new LookAndSayIterator(null, null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Passed null value");
    }
  }

  /**
   * Tests for negative value for seed, passed for seed and end value constructor.
   */
  @Test
  public void test7() {
    try {
      iterator = new LookAndSayIterator(BigInteger.ZERO.subtract(BigInteger.ONE), BigInteger.TEN);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Seed value cant be lesser than zero.");
    }
  }

  /**
   * Tests for negative value for endValue, passed for seed and end value constructor.
   */
  @Test
  public void test8() {
    try {
      iterator = new LookAndSayIterator(BigInteger.TEN, BigInteger.ZERO.subtract(BigInteger.ONE));
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Seed value is greater than the endValue");
    }
  }

  /**
   * Tests for negative palue passed to both seed and endValue.
   */
  @Test
  public void test9() {
    try {
      iterator = new LookAndSayIterator(BigInteger.ZERO.subtract(BigInteger.ONE),
              BigInteger.ZERO.subtract(BigInteger.ONE));
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Seed value cant be lesser than zero.");
    }
  }

  /**
   * Tests when seed and endValue are the same.
   */
  @Test
  public void test10() {
    iterator = new LookAndSayIterator(BigInteger.ONE, BigInteger.ONE);
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.hasPrevious(), false);
    assertEquals(iterator.next(), BigInteger.ONE);
    assertEquals(iterator.hasNext(), false);
    assertEquals(iterator.hasPrevious(), true);
    assertEquals(iterator.prev(),BigInteger.ONE.add(BigInteger.TEN));
  }

  /**
   * Tests for when seed is greater than the end value.
   */
  @Test
  public void test11() {
    try {
      iterator = new LookAndSayIterator(BigInteger.TEN, BigInteger.ONE);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Seed value is greater than the endValue");
    }
  }

  /**
   * Tests for max value 100 9's in seed only constructor.
   */
  @Test
  public void test12() {
    iterator = new LookAndSayIterator(BigInteger.valueOf(10).pow(100).subtract(BigInteger.ONE));
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), BigInteger.valueOf(10).pow(100).subtract(BigInteger.ONE));
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), new BigInteger("1009"));
    assertEquals(iterator.hasPrevious(),true);
  }
}