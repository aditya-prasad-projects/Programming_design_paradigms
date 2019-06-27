package fib;

/**
 * A Exception that throws an error if we try to decrease the counter to zero.
 */

public class CounterException extends Exception {

  /**
   * The constructor passes the message passed with the creation of CounterException object
   * to the Exception class's constructor.
   * @param message message to be printed when exception occurs
   */
  public CounterException(String message) {
    super(message);
  }
}
