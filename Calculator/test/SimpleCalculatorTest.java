import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import calculator.SimpleCalculator;


public class SimpleCalculatorTest extends AbstractCalculatorTest {

  @Before
  public void setup() {
    cal1 = new SimpleCalculator();
  }

  @Test
  public void testInvalidInput() {
    try {
      cal1.input('3');
      cal1.input('2');
      cal1.input('+');
      cal1.input('=');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid Input: It cannot be two consecutive operands");
    }
  }

  @Test
  public void testInvalidInput1() {
    try {
      cal1.input('+');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cant start with +, -, * or =");
    }
  }

  @Test
  public void testInvalidInput2() {
    try {
      cal1.input('=');
      cal1.input('+');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cant start with +, -, * or =");
    }
  }

  @Test
  public void testInput7() {
    try {
      cal1.input('=');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cant start with +, -, * or =");
    }
  }

  @Test
  public void testInput8() {
    try {
      cal1.input('4');
      cal1.input('6');
      cal1.input('=');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cant start with +, -, * or =.");
    }

  }

  @Test
  public void testInput14() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('4');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "56");
  }

  @Test
  public void testInput15() {
    try {
      cal1.input('+');
      cal1.input('1');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cant start with +, -, * or =");
    }
  }

  @Test
  public void testInput16() {
    cal1.input('3');
    cal1.input('*');
    cal1.input('2');
    cal1.input('*');
    assertEquals(cal1.getResult(), "6*");
  }

  @Test
  public void testInvalidInput6() {
    try {
      cal1.input('3');
      cal1.input('2');
      cal1.input(' ');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Inputs can be only numbers or +,-,*");
    }
  }

  @Test
  public void testInput17() {
    try {
      cal1.input('2');
      cal1.input('1');
      cal1.input('3');
      cal1.input('*');
      cal1.input('=');
      fail();
    }
    catch (IllegalArgumentException e ) {
      assertEquals(e.getMessage(), "Invalid Input: It cannot be two consecutive operands");
    }
  }

  @Test
  public void testInput18() {
    try {
      cal1.input('A');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Inputs can be only numbers or +,-,*");
    }
    assertEquals(cal1.getResult(), "");
    cal1.input('2');
    cal1.input('+');
    assertEquals(cal1.getResult(), "2+");
  }

  @Test
  public void testInput19() {
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
    cal1.input('7');
    cal1.input('-');
    assertEquals(cal1.getResult(), "-2147483646-");
    cal1.input('1');
    cal1.input('=');
    assertEquals(cal1.getResult(), "-2147483647");
    cal1.input('-');
    cal1.input('3');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
  }

  @Test
  public void testInput20() {
    try {
      cal1.input('1');
      cal1.input('+');
      cal1.input('-');
      cal1.input('2');
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid Input: It cannot be two consecutive operands");
    }
  }

  @Test
  public void testInput21() {
    cal1.input('1');
    cal1.input('+');
    cal1.input('2');
    cal1.input('=');
    cal1.input('+');
    assertEquals(cal1.getResult(), "3+");
  }
}