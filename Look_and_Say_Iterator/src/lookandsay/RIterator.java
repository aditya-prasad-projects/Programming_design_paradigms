package lookandsay;

import java.util.Iterator;

/**
 * Implements the Iterator Interface.
 * Also provides methods to get the previous value from the iterator
 * and to check if a previous value is present.
 * @param <T> a generic type.
 */
public interface RIterator<T> extends Iterator<T> {

  /**
   * Returns the current value of the Iterator of type T and increments the iterator.
   * @return the current value of the Iterator of type T.
   */
  T prev();

  /**
   * Checks if a previous value is present for the Iterator of type T.
   * @return true if previous value is present, false otherwise.
   */
  boolean hasPrevious();

}
