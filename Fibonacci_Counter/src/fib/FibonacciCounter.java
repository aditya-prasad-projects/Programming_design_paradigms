package fib;

/**
 * Provides function to increment or decrement counter and to keep track of the counter and
 * the fibonacci number in the counters position.
 */

public interface FibonacciCounter {
  /**
   * It increments the counter by 1, and adds the respective fibonacci element to the List.
   * @return A FibonacciCounter object.
   */
  FibonacciCounter increaseFibo();

  /**
   * It decreases the counter by 1.
   * @return A FibonacciCounter object.
   * @throws CounterException when counter becomes zero
   */
  FibonacciCounter decreaseFibo() throws CounterException;

  /**
   * returns the Fibonacci number given by the counter.
   * @return
   */
  int getCount();

  /**
   * returns the counter.
   * @return
   */
  int getFibo();

}
