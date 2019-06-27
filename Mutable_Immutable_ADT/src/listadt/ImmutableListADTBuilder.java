package listadt;

/**
 * A builder for the ImmutableListADT class.
 * @param <T> a generic  type
 */
public interface ImmutableListADTBuilder<T> {

  /**
   * Add an element to the Front of the list.
   * @param e the object to be added to the front of the list.
   * @return the builder object with the object added to the front.
   */
  ImmutableListADTBuilder<T> addFront(T e);

  /**
   * Add an element to the back of the list.
   * @param e the object to be added to the back of the list.
   * @return the builder object with the object added to the back of the list.
   */
  ImmutableListADTBuilder<T> addBack(T e);

  /**
   * Add an element at the specified index in the list.
   * @param index the index at which the element is to be added.
   * @param e the object to be added at the specific list.
   * @return the builder object with the object added at the specified position.
   */
  ImmutableListADTBuilder<T> add(int index, T e);

  /**
   * Return a object of type ImmutableListADT.
   * @return a object with the elements as created in it.
   */
  ImmutableListADT<T> build();
}
