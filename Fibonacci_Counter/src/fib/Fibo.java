package fib;

import java.util.ArrayList;

/**
 * Fibo implements the fibonacci sequence using a counter to keep track of which fibonacci number
 * to return.
 */

public class Fibo implements FibonacciCounter {
  private int counter;
  public ArrayList<Integer> a = new ArrayList<Integer>();

  /**
   * The constructor initialized the counter to 1, and assigns the first two elements of the
   * fibonacci sequence.
   */
  public Fibo() {
    this.counter = 1;
    this.a.add(0);
    this.a.add(1,0);
    this.a.add(2,1);
  }

  /**
   * It increments the counter by 1, and adds the respective fibonacci element to the List.
   * @return A FibonacciCounter object.
   */
  public FibonacciCounter increaseFibo() {
    this.counter += 1;
    if (this.counter > 2) {
      this.a.add(counter, this.a.get(counter - 1) + this.a.get(counter - 2));
    }
    return this;
  }

  /**
   * It decreases the counter by 1.
   * @return A FibonacciCounter object.
   * @throws CounterException when counter becomes 0
   */
  public FibonacciCounter decreaseFibo() throws CounterException {
    if (this.counter - 1 < 1 ) {
      throw new CounterException("Counter value cannot be zero.");
    }
    else {
      this.counter -= 1;
    }
    return this;
  }

  /**
   * returns the Fibonacci number given by the counter.
   * @return
   */
  public int getFibo() {
    return this.a.get(counter);
  }

  /**
   * returns the counter.
   * @return
   */
  public int getCount() {
    return this.counter;
  }
}
