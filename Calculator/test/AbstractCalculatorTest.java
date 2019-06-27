import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import calculator.Calculator;

/**
 * Tests the common operations in SimpleCalculator and SmartCalculator.
 */

public abstract class AbstractCalculatorTest {
  protected Calculator cal1;

  @Test
  public void testInitialization() {
    assertEquals(cal1.getResult(), "");
  }

  @Test
  public void testInput() {
    cal1.input('3');
    assertEquals(cal1.getResult(), "3");
    cal1.input('2');
    assertEquals(cal1.getResult(), "32");
  }

  @Test
  public void testInput1() {
    cal1.input('3');
    cal1.input('2');
    assertEquals(cal1.getResult(), "32");
    cal1.input('+');
    assertEquals(cal1.getResult(), "32+");
  }

  @Test
  public void testInput2() {
    cal1.input('3');
    cal1.input('2');
    assertEquals(cal1.getResult(), "32");
    cal1.input('+');
    assertEquals(cal1.getResult(), "32+");
    cal1.input('2');
    assertEquals(cal1.getResult(), "32+2");
    cal1.input('4');
    assertEquals(cal1.getResult(), "32+24");
    cal1.input('=');
    assertEquals(cal1.getResult(), "56");
  }

  @Test
  public void testInput3() {
    cal1.input('3');
    cal1.input('2');
    assertEquals(cal1.getResult(), "32");
    cal1.input('+');
    assertEquals(cal1.getResult(), "32+");
    cal1.input('2');
    cal1.input('4');
    assertEquals(cal1.getResult(), "32+24");
    cal1.input('-');
    assertEquals(cal1.getResult(), "56-");
  }

  @Test
  public void testInput4() {
    cal1.input('3');
    cal1.input('2');
    assertEquals(cal1.getResult(), "32");
    cal1.input('+');
    assertEquals(cal1.getResult(), "32+");
    cal1.input('2');
    cal1.input('4');
    assertEquals(cal1.getResult(), "32+24");
    cal1.input('-');
    assertEquals(cal1.getResult(), "56-");
    cal1.input('2');
    assertEquals(cal1.getResult(), "56-2");
    cal1.input('0');
    assertEquals(cal1.getResult(), "56-20");
    cal1.input('=');
    assertEquals(cal1.getResult(), "36");
  }

  @Test
  public void testInput5() {
    cal1.input('3');
    cal1.input('2');
    assertEquals(cal1.getResult(), "32");
    cal1.input('+');
    assertEquals(cal1.getResult(), "32+");
    cal1.input('2');
    cal1.input('4');
    assertEquals(cal1.getResult(), "32+24");
    cal1.input('-');
    assertEquals(cal1.getResult(), "56-");
    cal1.input('2');
    assertEquals(cal1.getResult(), "56-2");
    cal1.input('0');
    assertEquals(cal1.getResult(), "56-20");
    cal1.input('-');
    assertEquals(cal1.getResult(), "36-");
  }

  @Test
  public void testInput7() {
    cal1.input('3');
    cal1.input('2');
    assertEquals(cal1.getResult(), "32");
    cal1.input('+');
    assertEquals(cal1.getResult(), "32+");
    cal1.input('2');
    cal1.input('4');
    assertEquals(cal1.getResult(), "32+24");
    cal1.input('=');
    assertEquals(cal1.getResult(), "56");
    cal1.input('-');
    assertEquals(cal1.getResult(), "56-");
    cal1.input('1');
    cal1.input('0');
    cal1.input('=');
    assertEquals(cal1.getResult(), "46");
  }

  @Test
  public void testInvalidInputs3() {
    try {
      cal1.input('A');
      cal1.input('b');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Inputs can be only numbers or +,-,*");
    }
  }

  @Test
  public void testInput12() {
    cal1.input('4');
    cal1.input('6');
    cal1.input('*');
    cal1.input('5');
    cal1.input('-');
    assertEquals(cal1.getResult(), "230-");
    cal1.input('4');
    assertEquals(cal1.getResult(), "230-4");
    cal1.input('+');
    assertEquals(cal1.getResult(), "226+");
    cal1.input('3');
    cal1.input('=');
    assertEquals(cal1.getResult(), "229");
  }

  @Test
  public void testInput13() {
    cal1.input('4');
    cal1.input('6');
    cal1.input('*');
    cal1.input('5');
    cal1.input('-');
    assertEquals(cal1.getResult(), "230-");
    cal1.input('4');
    cal1.input('+');
    assertEquals(cal1.getResult(), "226+");
    cal1.input('3');
    cal1.input('=');
    assertEquals(cal1.getResult(), "229");
    cal1.input('+');
    cal1.input('1');
    cal1.input('2');
    cal1.input('4');
    cal1.input('-');
    assertEquals(cal1.getResult(), "353-");
    cal1.input('4');
    cal1.input('1');
    cal1.input('2');
    cal1.input('=');
    assertEquals(cal1.getResult(), "-59");
    cal1.input('-');
    cal1.input('1');
    cal1.input('=');
    assertEquals(cal1.getResult(), "-60");
    cal1.input('-');
    assertEquals(cal1.getResult(), "-60-");
  }

  @Test
  public void testInput14() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('-');
    cal1.input('5');
    cal1.input('C');
    assertEquals(cal1.getResult(), "");
  }

  @Test
  public void testInput15() {
    cal1.input('1');
    cal1.input('-');
    cal1.input('2');
    cal1.input('=');
    assertEquals(cal1.getResult(), "-1");
  }

  @Test
  public void testInput16() {
    cal1.input('3');
    cal1.input('6');
    cal1.input('+');
    cal1.input('4');
    cal1.input('2');
    cal1.input('*');
    cal1.input('5');
    cal1.input('6');
    cal1.input('-');
    cal1.input('6');
    cal1.input('2');
    cal1.input('+');
    cal1.input('4');
    cal1.input('8');
    cal1.input('=');
    cal1.input('+');
    cal1.input('6');
    cal1.input('2');
    assertEquals(cal1.getResult(), "4354+62");
  }

  @Test
  public void testOverflow0() {
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('6');
    cal1.input('+');
    cal1.input('1');
    cal1.input('-');
    assertEquals(cal1.getResult(), "2147483647-");
  }

  @Test
  public void testOverflow1() {
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('6');
    cal1.input('+');
    cal1.input('2');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
  }

  @Test
  public void testOverflow2() {
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('7');
    assertEquals(cal1.getResult(), "2147483647");
  }

  @Test
  public void testOverflow3() {
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('6');
    cal1.input('+');
    cal1.input('2');
    cal1.input('-');
    assertEquals(cal1.getResult(), "0-");
  }

  @Test
  public void testOverflow4() {
    try {
      cal1.input('2');
      cal1.input('1');
      cal1.input('4');
      cal1.input('7');
      cal1.input('4');
      cal1.input('8');
      cal1.input('3');
      cal1.input('6');
      cal1.input('4');
      cal1.input('8');
      fail();
    }
    catch (RuntimeException e) {
      assertEquals(e.getMessage(), "variable overflow");
    }
  }

  @Test
  public void testOverflow5() {
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('6');
    cal1.input('+');
    assertEquals(cal1.getResult(), "0+");
  }

  @Test
  public void testOverflow6() {
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('5');
    cal1.input('=');
    assertEquals(cal1.getResult(), "2147483647");
  }

  @Test
  public void testOverflow7() {
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('7');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
  }

  @Test
  public void testOverflow8() {
    try {
      cal1.input('1');
      cal1.input('-');
      cal1.input('2');
      cal1.input('1');
      cal1.input('4');
      cal1.input('7');
      cal1.input('4');
      cal1.input('8');
      cal1.input('3');
      cal1.input('6');
      cal1.input('4');
      cal1.input('9');
      fail();
    }
    catch (RuntimeException e) {
      assertEquals(e.getMessage(), "variable overflow");
    }
  }

  @Test
  public void testOverflow9() {
    cal1.input('2');
    cal1.input('1');
    cal1.input('4');
    cal1.input('7');
    cal1.input('4');
    cal1.input('8');
    cal1.input('3');
    cal1.input('6');
    cal1.input('4');
    cal1.input('7');
    cal1.input('*');
    cal1.input('2');
    cal1.input('*');
    assertEquals(cal1.getResult(), "0*");
    cal1.input('3');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");

  }

  @Test
  public void testOverflow10() {
    cal1.input('4');
    cal1.input('6');
    cal1.input('3');
    cal1.input('4');
    cal1.input('1');
    cal1.input('*');
    cal1.input('4');
    cal1.input('6');
    cal1.input('3');
    cal1.input('4');
    cal1.input('1');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
  }

  @Test
  public void testOverflow11() {
    try {
      cal1.input('2');
      cal1.input('1');
      cal1.input('4');
      cal1.input('7');
      cal1.input('4');
      cal1.input('8');
      cal1.input('3');
      cal1.input('6');
      cal1.input('4');
      cal1.input('8');
      fail();
    }
    catch (RuntimeException e) {
      assertEquals(e.getMessage(), "variable overflow");
    }
    assertEquals(cal1.getResult(), "214748364");
    cal1.input('3');
    assertEquals(cal1.getResult(), "2147483643");
  }

  @Test
  public void testInput27() {
    cal1.input('C');
    assertEquals(cal1.getResult(), "");
  }

  @Test
  public void testInput29() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('4');
    cal1.input('3');
    cal1.input('=');
    assertEquals(cal1.getResult(), "275");
  }

  @Test
  public void testInput100() {
    cal1.input('0');
    cal1.input('1');
    cal1.input('*');
    cal1.input('0');
    assertEquals(cal1.getResult(), "01*0");
  }
}
