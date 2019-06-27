package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;


/**
 * A iterator that follows the LookAndSay method of iteration.
 */
public class LookAndSayIterator implements RIterator {
  private String seed;
  private String endValue;
  private String current;

  /**
   * Creates a LookAndSee Iterator by passing the starting value and end value .
   * @param seed the starting value of the LookAndSee Iterator.
   * @param endValue the end value of the LookAndSee Iterator.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger endValue) {
    if (seed == null || endValue == null) {
      throw new IllegalArgumentException("Passed null value");
    }
    if (seed.compareTo(BigInteger.ZERO) < 0) {
      throw new IllegalArgumentException("Seed value cant be lesser than zero.");
    }
    if (seed.compareTo(endValue) > 0) {
      throw new IllegalArgumentException("Seed value is greater than the endValue");
    }
    this.seed = "" + seed;
    this.endValue = "" + endValue;
    this.current = this.seed;
  }

  /**
   * Create a LookAndSeeIterator by passing only the starting value.
   * The default end value is 100 9's.
   * @param seed the starting value of the LookAndSee Iterator.
   */
  public LookAndSayIterator(BigInteger seed) {
    if (seed == null) {
      throw new IllegalArgumentException("Passed null value");
    }
    if (seed.compareTo(BigInteger.ZERO) < 0) {
      throw new IllegalArgumentException("Seed value cant be lesser than zero.");
    }
    this.seed = "" + seed;
    this.endValue = "" + BigInteger.valueOf(10).pow(100).subtract(BigInteger.ONE);
    if (seed.compareTo(new BigInteger(endValue)) > 0) {
      throw new IllegalArgumentException("Seed value is greater than the endValue");
    }
    this.current = this.seed;
  }

  /**
   * Create a LookAndSeeIterator with starting value of one and end value of 100 9's.
   */
  public LookAndSayIterator() {
    this.seed = "" + BigInteger.ONE;
    this.current = this.seed;
    this.endValue = "" + BigInteger.valueOf(10).pow(100).subtract(BigInteger.ONE);
  }

  /**
   * Return the current number in the sequence and revert to the previous number in the sequence.
   * @return the current value for the LookAndSayIterator.
   */
  @Override
  public BigInteger prev() {
    if (current.length() % 2 != 0) {
      throw new NoSuchElementException("Cant call previous on odd numbers");
    }
    String a = previousInteger();
    String b = current;
    current = a;
    return new BigInteger(b);
  }

  /**
   * Return true if it is possible to go back one step, false otherwise.
   * @return true if it is possible to go back one step, false otherwise.
   */
  @Override
  public boolean hasPrevious() {
    return (current.length() % 2 == 0);
  }

  /**
   * return true if the next number to be returned is still lesser than end false otherwise.
   * @return true if the next number to be returned is still lesser than end false otherwise.
   */
  @Override
  public boolean hasNext() {
    return (new BigInteger(current).compareTo(new BigInteger(endValue)) <= 0);
  }

  /**
   * Return the current number in the sequence and advance to the next number.
   * @return the current number in the sequence
   */
  @Override
  public BigInteger next() {
    if (hasNext()) {
      String a = nextInteger();
      String b = current;
      current = a;
      return new BigInteger(b);
    }
    return new BigInteger(current);

  }

  /**
   * Used to get the previous LookAndSeeIterator value.
   * @return the previous LookAndSeeIterator value.
   */
  private String previousInteger() {
    String a = "";
    int i = 0;
    while (i < current.length()) {
      int count = Integer.parseInt("" + current.charAt(i));
      int j = Integer.parseInt("" + current.charAt(i + 1));
      for (int k = 0; k < count; k++) {
        a = a + j;
      }
      i = i + 2;
    }
    return a;
  }

  /**
   * Used to get the next LookAndSeeIterator value.
   * @return the next LookAndSeeIterator value.
   */
  private String nextInteger() {
    String a = "";
    int count = 1;
    for (int i = 0; i <= current.length() - 1; i++) {
      if (i != current.length() - 1 && current.charAt(i) == current.charAt(i + 1)) {
        count = count + 1;
        continue;
      }
      a = a + count + current.charAt(i);
      count = 1;
    }
    return a;
  }
}
