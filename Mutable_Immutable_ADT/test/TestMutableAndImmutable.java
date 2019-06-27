import org.junit.Test;

import listadt.ImmutableListADT;
import listadt.ImmutableListADTImpl;
import listadt.ListADT;
import listadt.MutableListADT;
import listadt.MutableListADTImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests Immutable and Mutable Lists.
 */
public class TestMutableAndImmutable {
  private MutableListADT<String> list;
  private ImmutableListADT<String> listImmutable;

  /**
   * Shows how to create a ImmutableList using MutableList and get a
   * MutableList from the ImmutableList.
   */
  @Test
  public void testCreateImmutableUsingMutable() {
    list = new MutableListADTImpl<String>();
    list.addBack("Test");
    assertEquals("(Test)",list.toString());
    list.add(1,"Mutable");
    assertEquals("(Test Mutable)", list.toString());
    listImmutable = list.getImmutable();
    assertEquals("(Test Mutable)", listImmutable.toString());
    list.addBack("Immutable");
    assertEquals(2, listImmutable.getSize());
    assertEquals("Test", listImmutable.get(0));
    assertEquals("Mutable", listImmutable.get(1));
    try {
      listImmutable.get(2);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid index", e.getMessage());
    }
    assertEquals("(Test Mutable Immutable)", list.toString());
    assertEquals("(Test Mutable)",listImmutable.toString());
    list = listImmutable.getMutable();
    assertEquals("(Test Mutable)", list.toString());
    assertEquals("(Test Mutable)",listImmutable.toString());
    list.addFront("Immutable");
    assertEquals("(Immutable Test Mutable)", list.toString());
    assertEquals("(Test Mutable)",listImmutable.toString());
    list.addBack("TestOne");
    assertEquals("(Immutable Test Mutable TestOne)", list.toString());
    assertEquals("(Test Mutable)",listImmutable.toString());
    list.add(2, "List");
    assertEquals("(Immutable Test List Mutable TestOne)", list.toString());
    assertEquals("(Test Mutable)",listImmutable.toString());
    list.remove("List");
    assertEquals("(Immutable Test Mutable TestOne)", list.toString());
    assertEquals("(Test Mutable)",listImmutable.toString());
  }

  /**
   * Shows how to create a ImmutableListADT without passing it a list and using builder.
   */
  @Test
  public void testCreateImmutableUsingBuilder() {
    listImmutable = ImmutableListADTImpl.getBuilder().addBack("Test").addFront("Immutable").build();
    assertEquals("(Immutable Test)", listImmutable.toString());
  }

  /**
   * Shows how to get a MutableList from a ImmutableList.
   */
  @Test
  public void testGetMutableFromImmutable() {
    listImmutable = ImmutableListADTImpl.getBuilder().addBack("Test").addFront("Immutable").build();
    assertEquals("(Immutable Test)", listImmutable.toString());
    list = listImmutable.getMutable();
    assertEquals("(Immutable Test)", list.toString());
    list.addBack("Mutable");
    assertEquals("(Immutable Test Mutable)", list.toString());
    assertEquals("(Immutable Test)", listImmutable.toString());
  }

  /**
   * Test if mutable map returns a mutable Map.
   */
  @Test
  public void testMutableMaps() {
    list = new MutableListADTImpl<String>();
    String sentence = "The quick brown fox jumps over the lazy dog";
    String []words = sentence.split("\\s+");
    for (String w:words) {
      list.addBack(w);
    }
    ListADT<Integer> wordLengths = list.map(s -> s.length());
    assertEquals("The mapped list's length does not match the original list!",
            list.getSize(),wordLengths.getSize());
    for (int i = 0;i < words.length;i++) {
      assertEquals(words[i].length(),(int)wordLengths.get(i));
    }
    wordLengths.addBack(4);
    assertEquals(list.getSize() + 1, wordLengths.getSize());
    assertEquals("(3 5 5 3 5 4 3 4 3 4)", wordLengths.toString());
  }

  /**
   *Test if Immutable map returns a Immutable map. Its proved that the map is not immutable,
   * because mutable operations cannot be called on it.
   */
  @Test
  public void testImmutableMaps() {
    list = new MutableListADTImpl<String>();
    String sentence = "The quick brown fox jumps over the lazy dog";
    String []words = sentence.split("\\s+");
    for (String w:words) {
      list.addBack(w);
    }
    listImmutable = list.getImmutable();
    ImmutableListADT<Integer> wordLengths = listImmutable.map(s -> s.length());
    assertEquals("The mapped list's length does not match the original list!",
            listImmutable.getSize(),wordLengths.getSize());
    for (int i = 0;i < words.length;i++) {
      assertEquals(words[i].length(),(int)wordLengths.get(i));
    }
  }
}
