package listadt;

/**
 * Represents a MutableList and provides a method to get the Immutable version of itself.
 * No changes have been made to ListADT.
 * So, if any new functions are to be added that are non mutable, those functions
 * should be added in ImmutableListADT as well.
 * @param <T> a generic type.
 */
public interface MutableListADT<T> extends ListADT<T> {

  /**
   * A getter for the ImmutableListADT object.
   * @return a ImmutableList
   */
  ImmutableListADT<T> getImmutable();
}
