package listadt;

import java.util.function.Function;

/**
 * Represents a ImmutableList.
 * If any change is made to the functions present in ListADT class that are present
 * in this interface also, those changes will be reflected in the functions
 * presented in this class as well.
 * If any new methods are to be added to ListADT that are non mutable,
 * those functions should be implemented here as well.
 * the classes that implement this interface should also implement equals() method to make a
 * mutable list equal to immutable list
 * @param <T> the generic type
 */
public interface ImmutableListADT<T> {

  /**
   * Return the number of objects currently in this list.
   * @return the size of the list
   */
  int getSize();

  /**
   * Get the (index)th object in this list.
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  T get(int index) throws IllegalArgumentException;

  /**
   * A general purpose map higher order function on this list, that returns
   * the corresponding list of type R.
   * @param converter the function that converts T into R
   * @param <R> the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list,
   * but has data of type R
   */
  <R> ImmutableListADT<R> map(Function<T,R> converter);

  /**
   * returns a mutable version of the immutable list.
   * @return a Mutable list object
   */
  MutableListADT<T> getMutable();

}
