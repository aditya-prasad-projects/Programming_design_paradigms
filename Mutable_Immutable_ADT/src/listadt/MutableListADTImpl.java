package listadt;

/**
 * It extends ListADTImpl and provides a method to get a ImmutableListADT object.
 * No changes have been made to the existing code, including ListADTImpl.
 * So, if any new public methods are to be implemented here that are non mutable,
 * it should be implemented in ImmutableListADTImpl also.
 * @param <T> a generic type.
 */
public class MutableListADTImpl<T> extends ListADTImpl<T> implements MutableListADT<T>  {

  /**
   * Calls the constructor of ListADTImpl.
   */
  public MutableListADTImpl() {
    super();
  }

  /**
   * A getter for the ImmutableListADT object.
   * @return a ImmutableList
   */
  @Override
  public ImmutableListADT<T> getImmutable() {
    return new ImmutableListADTImpl<T>(this);
  }
}
