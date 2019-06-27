import decoder.Decoder;
import decoder.DecoderImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests DecoderImpl class.
 */
public class TestDecoderImpl {

  DecoderImpl decoder;

  @Before
  public void setup() {
    decoder = new DecoderImpl("01");
  }

  /**
   * Tests creating and performing operation on a binary tree.
   */
  @Test
  public void testCorrectness() {
    decoder.addCode('a', "100");
    decoder.addCode('b', "00");
    assertEquals(decoder.allCodes(), "a:100\n" + "b:00\n");
    assertEquals(decoder.isCodeComplete(), false);
    decoder.addCode('c', "01");
    decoder.addCode('d', "11");
    decoder.addCode('e', "101");
    assertEquals(decoder.decode("10001101"), "ace");
    assertEquals(decoder.allCodes(), "a:100\n"
            + "b:00\n"
            + "c:01\n"
            + "d:11\n"
            + "e:101\n");
    assertEquals(decoder.isCodeComplete(), true);
  }

  /**
   * Test for edge cases in addCode.
   */
  @Test
  public void test0() {
    decoder.addCode('a', "100");
    decoder.addCode('b', "00");
    decoder.addCode('c', "01");
    decoder.addCode('d', "11");
    decoder.addCode('e', "101");
    try {
      decoder.addCode('p', "11");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Prefix is already present");
    }
    try {
      decoder.addCode('p', "111");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Prefix is already present");
    }
    try {
      decoder.addCode('a', "");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Cant add empty code and symbol to a tree");
    }
    try {
      decoder.addCode('p', "0");
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Prefix is already present");
    }

    try {
      decoder.addCode('z', null);
      fail();
    }

    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Cant add empty code and symbol to a tree");
    }
  }

  /**
   * Tests creating and performing operations on a hexadecimal tree.
   */
  @Test
  public void test1() {
    DecoderImpl d = new DecoderImpl("123456789abcdef");
    assertEquals(d.isCodeComplete(), false);
    d.addCode('a', "9");
    d.addCode('b', "8");
    d.addCode('c', "7");
    d.addCode('d', "6");
    d.addCode('e', "5");
    d.addCode('f', "4");
    d.addCode('g', "3");
    d.addCode('h', "2");
    d.addCode('i', "1");
    d.addCode('j', "a");
    d.addCode('k', "b");
    d.addCode('l', "c");
    d.addCode('m', "d");
    d.addCode('n', "e");
    d.addCode('o', "f");
    assertEquals(d.decode("9813"), "abig");
    assertEquals(d.decode("9999"), "aaaa");
    assertEquals(d.decode("abcdef123456789"), "jklmnoihgfedcba");
    assertEquals(d.isCodeComplete(), true);
    try {
      d.addCode('p', "11");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Prefix is already present");
    }
    assertEquals(d.isCodeComplete(), true);
  }

  /**
   * Try creating a DecoderImpl with invalid  symbols.
   */
  @Test
  public void test2() {
    try {
      Decoder p = new DecoderImpl("");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid codeSymbols");
    }
    try {
      Decoder q = new DecoderImpl(null);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid codeSymbols");
    }
  }

  /**
   * Check efficiency of tree for various symbols.
   */
  @Test
  public void test3() {
    Decoder a = new DecoderImpl("!@#$%^&*()_+");
    try {
      a.decode("!");
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Tree is empty");
    }
    a.addCode('!',"!");
    a.addCode('@',"@");
    a.addCode('#',"#");
    assertEquals(a.isCodeComplete(), false);
    assertEquals(a.decode("!@#"), "!@#");
    try {
      a.decode("()");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Decoding message is invalid");
    }
    try {
      a.decode("!@#$");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Decoding message is invalid");
    }
    a.addCode('$',"$");
    a.addCode('%',"%");
    a.addCode('^',"^");
    a.addCode('&',"&");
    a.addCode('*',"*");
    a.addCode('(',"(");
    a.addCode(')',")");
    a.addCode('_', "_");
    a.addCode('+', "+");
    assertEquals(a.isCodeComplete(),true);
    assertEquals(a.decode("!@#$%^&*()_+"), "!@#$%^&*()_+");
  }

  /**
   * Test decode for edge cases.
   */
  @Test
  public void test4() {
    Decoder b = new DecoderImpl("1234567890()*&^%$#@!");
    b.addCode('a',"123");
    b.addCode('b',"!@#");
    b.addCode('c', "1!@#");
    b.addCode('d', "4");
    b.addCode('e',"()");
    try {
      b.decode("");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Decoding message is invalid");
    }
    try {
      b.decode(null);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Decoding message is invalid");
    }
    assertEquals(b.decode("4"), "d");
    assertEquals(b.decode("()4123"), "eda");
    b.addCode('f', "(*");
    assertEquals(b.allCodes(), "a:123\n" + "b:!@#\n" + "c:1!@#\n"
            + "d:4\n" + "e:()\n" + "f:(*\n");
    assertEquals(b.isCodeComplete(), false);
    try {
      b.addCode('a', "234");
    }
    catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Character already present in the tree");
    }


  }

  /**
   * Tests for a tree with single code in it.
   */
  @Test
  public void test5() {
    Decoder c = new DecoderImpl("1");
    try {
      c.addCode('a', "10");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid code");
    }
    try {
      c.addCode('1', "0");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid code");
    }
    c.addCode('a', "1");
    assertEquals(c.decode("11"), "aa");
    assertEquals(c.decode("1"),"a");
    assertEquals(c.isCodeComplete(), true);
    assertEquals(c.allCodes(), "a:1\n");
    try {
      c.addCode('a', "1");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Prefix is already present");
    }
  }

  @Test
  public void test6() {
    Decoder j = new DecoderImpl("01");
    assertEquals(j.allCodes(),"");
  }



}


