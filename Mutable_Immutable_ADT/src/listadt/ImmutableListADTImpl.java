package listadt;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a Immutable List.
 * Provides only non mutable operations.
 * If any change is made to the ListADTImpl it will be
 * reflected in the functions of this class as well.
 * If any new methods are to be added to ListADTImpl and it is a non mutable operation,
 * it should be implemented here as well.
 * Two ways ImmutableListADTImpl objects can be created, either by the provided builder or
 * passing it a ListADT object in its constructor.
 * @param <T> a generic type
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  private final ListADT<T> list;

  /**
   * Takes in a ListADT object and creates a ImmutableList.
   * @param list the list that is to be converted to a immutable list.
   */
  ImmutableListADTImpl(ListADT<T> list) {
    this.list = new ListADTImpl<T>();
    for (int i = 0; i < list.getSize(); i++) {
      this.list.add(i, list.get(i));
    }
  }

  /**
   * A Builder for the ImmutableListADT.
   * @param <T> a generic type
   */
  public static class Builder<T> implements ImmutableListADTBuilder<T> {
    ListADT<T> list;

    Builder() {
      list = new ListADTImpl<T>();
    }

    @Override
    public Builder<T> addFront(T e) {
      list.addFront(e);
      return this;
    }

    @Override
    public ImmutableListADTBuilder<T> addBack(T e) {
      list.addBack(e);
      return this;
    }

    @Override
    public ImmutableListADTBuilder<T> add(int index, T e) {
      list.add(index, e);
      return this;
    }

    @Override
    public ImmutableListADT<T> build() {
      return new ImmutableListADTImpl<T>(this.list);
    }
  }

  @Override
  public int getSize() {
    return list.getSize();
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return list.get(index);
  }

  @Override
  public <R> ImmutableListADT<R> map(Function<T, R> converter) {
    return new ImmutableListADTImpl(this.list.map(converter));
  }

  @Override
  public MutableListADT<T> getMutable() {
    MutableListADT list1 = new MutableListADTImpl();
    for (int i = 0; i < this.list.getSize(); i++) {
      list1.add(i, this.list.get(i));
    }
    return list1;
  }

  /**
   * Creates a ImmutableListADTBuider type object.
   * @return ImmutableListADTBuider type object
   */
  public static Builder getBuilder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return this.list.toString();
  }

  /**
   * Overridden because even though the list might be immutable, it is still a list.
   * A Muitable list and a immutableList are equal if their contents are same.
   * @param obj the object "this" is to be compared with
   * @return true if both objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ListADT) {
      return this.equals(((MutableListADT) obj).getImmutable());
    }
    else if (obj instanceof ImmutableListADT) {
      return this.getMutable().equals(((ImmutableListADT) obj).getMutable());
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(list);
  }
}
