import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import calculator.SmartCalculator;

public class SmartCalculatorTest extends AbstractCalculatorTest {

  @Before
  public void setup() {
    cal1 = new SmartCalculator();
  }

  @Test
  public void test123() {
    cal1.input('+');
    cal1.input('+');
    cal1.input('*');
    assertEquals(cal1.getResult(), "");
  }

  @Test
  public void testInput6() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('=');
    assertEquals(cal1.getResult(), "64");
  }


  @Test
  public void testInput8() {
    cal1.input('+');
    assertEquals(cal1.getResult(), "");
  }

  @Test
  public void testInput9() {
    cal1.input('=');
    cal1.input('+');
    assertEquals(cal1.getResult(), "");
  }

  @Test
  public void testInput10() {
    cal1.input('=');
    assertEquals(cal1.getResult(), "");
  }

  @Test
  public void testInput11() {
    cal1.input('4');
    cal1.input('3');
    cal1.input('=');
    assertEquals(cal1.getResult(), "43");
  }













  @Test
  public void testOverflow() {
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
    cal1.input('=');
    assertEquals(cal1.getResult(), "2147483647");
  }



  @Test
  public void testInput17() {
    cal1.input('3');
    cal1.input('+');
    cal1.input('-');
    cal1.input('2');
    assertEquals(cal1.getResult(), "3-2");
    cal1.input('=');
    assertEquals(cal1.getResult(), "1");
  }

  @Test
  public void testInput18() {
    cal1.input('3');
    cal1.input('+');
    cal1.input('-');
    cal1.input('2');
    assertEquals(cal1.getResult(), "3-2");
    cal1.input('*');
    assertEquals(cal1.getResult(), "1*");
  }

  @Test
  public void testInput21() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('4');
    cal1.input('=');
    assertEquals(cal1.getResult(), "56");
    cal1.input('=');
    assertEquals(cal1.getResult(), "80");
    cal1.input('=');
    assertEquals(cal1.getResult(), "104");
    cal1.input('=');
    assertEquals(cal1.getResult(), "128");
  }

  @Test
  public void testInput22() {
    cal1.input('3');
    cal1.input('+');
    cal1.input('=');
    assertEquals(cal1.getResult(), "6");
    cal1.input('=');
    assertEquals(cal1.getResult(), "9");
    cal1.input('=');
    assertEquals(cal1.getResult(), "12");
    cal1.input('=');
    assertEquals(cal1.getResult(), "15");
  }

  @Test
  public void testInput23() {
    cal1.input('*');
    cal1.input('3');
    cal1.input('*');
    cal1.input('3');
    assertEquals(cal1.getResult(), "3*3");
    cal1.input('=');

    assertEquals(cal1.getResult(), "9");
  }

  @Test
  public void testInput24() {
    cal1.input('2');
    cal1.input('*');
    cal1.input('=');
    assertEquals(cal1.getResult(), "4");
    cal1.input('=');
    assertEquals(cal1.getResult(), "8");
    cal1.input('=');
    assertEquals(cal1.getResult(), "16");
  }

  @Test
  public void testInput25() {
    cal1.input('2');
    cal1.input('-');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
    cal1.input('=');
    assertEquals(cal1.getResult(), "-2");
    cal1.input('=');
    assertEquals(cal1.getResult(), "-4");
  }

  @Test
  public void testInput26() {
    cal1.input('4');
    cal1.input('6');
    cal1.input('3');
    cal1.input('4');
    cal1.input('1');
    cal1.input('*');
    cal1.input('-');
    cal1.input('4');
    cal1.input('6');
    cal1.input('3');
    cal1.input('4');
    cal1.input('1');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
    cal1.input('=');
    assertEquals(cal1.getResult(), "-46341");
    cal1.input('=');
    assertEquals(cal1.getResult(), "-92682");
  }



  @Test
  public void testInput28() {
    cal1.input('2');
    cal1.input('-');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
    cal1.input('=');
    assertEquals(cal1.getResult(), "-2");
    cal1.input('=');
    assertEquals(cal1.getResult(), "-4");
    cal1.input('C');
    cal1.input('*');
    cal1.input('2');
    cal1.input('*');
    cal1.input('-');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
    cal1.input('*');
    assertEquals(cal1.getResult(), "0*");
  }



  @Test
  public void testInput30() {
    cal1.input('3');
    cal1.input('0');
    cal1.input('*');
    cal1.input('3');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "810");
  }



  @Test
  public void testInput32() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('=');
    cal1.input('-');
    cal1.input('-');
    cal1.input('5');
    cal1.input('*');
    assertEquals(cal1.getResult(), "59*");
  }

  @Test
  public void testInput33() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('-');
    cal1.input('=');
    cal1.input('-');
    cal1.input('3');
    cal1.input('=');
    assertEquals(cal1.getResult(), "-3");
  }

  @Test
  public void testInput34() {
    cal1.input('4');
    cal1.input('=');
    cal1.input('=');
    cal1.input('*');
    cal1.input('3');
    cal1.input('=');
    assertEquals(cal1.getResult(), "12");
  }

  @Test
  public void testInput35() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('=');
    assertEquals(cal1.getResult(), "64");
    cal1.input('*');
    cal1.input('2');
    cal1.input('=');
    assertEquals(cal1.getResult(), "128");
    cal1.input('=');
    assertEquals(cal1.getResult(), "256");
  }

  @Test
  public void testInput36() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('4');
    cal1.input('+');
    cal1.input('*');
    cal1.input('=');
    assertEquals(cal1.getResult(), "3136");
  }

  @Test
  public void testInput37() {
    cal1.input('3');
    cal1.input('2');
    try {
      cal1.input('/');
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Inputs can be only numbers or +,-,*");
    }
    cal1.input('+');
    cal1.input('7');
    assertEquals(cal1.getResult(), "32+7");
  }

  @Test
  public void testInput38() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('*');
    cal1.input('=');
    assertEquals(cal1.getResult(), "1024");
    cal1.input('+');
    cal1.input('3');
    cal1.input('2');
    assertEquals(cal1.getResult(), "1024+32");
    cal1.input('+');
    cal1.input('=');
    assertEquals(cal1.getResult(), "2112");
  }

  @Test
  public void testInput40() {
    cal1.input('4');
    cal1.input('6');
    cal1.input('3');
    cal1.input('4');
    cal1.input('1');
    cal1.input('*');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
  }

  @Test
  public void testInput41() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('4');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    cal1.input('+');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "312");
  }

  @Test
  public void testInput42() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('=');
    cal1.input('=');
    cal1.input('*');
    cal1.input('3');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    cal1.input('+');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    cal1.input('-');
    cal1.input('2');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "31098");
  }

  @Test
  public void testInput43() {
    cal1.input('2');
    cal1.input('1');
    cal1.input('6');
    cal1.input('*');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "0");
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('2');
    cal1.input('4');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    cal1.input('+');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "312");
  }

  @Test
  public void testInput44() {
    cal1.input('3');
    cal1.input('-');
    cal1.input('=');
    cal1.input('=');
    cal1.input('=');
    assertEquals(cal1.getResult(), "-6");
  }

  @Test
  public void testInput45() {
    cal1.input('3');
    cal1.input('-');
    cal1.input('6');
    cal1.input('+');
    cal1.input('2');
    cal1.input('=');
    assertEquals(cal1.getResult(), "-1");
  }

  @Test
  public void testInput46() {
    cal1.input('3');
    cal1.input('-');
    cal1.input('*');
    cal1.input('2');
    assertEquals(cal1.getResult(), "3*2");
  }

  @Test
  public void testInput47() {
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
    cal1.input('-');
    cal1.input('1');
    assertEquals(cal1.getResult(), "0-1");
  }

  @Test
  public void testInput48() {
    cal1.input('3');
    cal1.input('2');
    cal1.input('+');
    cal1.input('=');
    cal1.input('+');
    cal1.input('=');
    cal1.input('+');
    cal1.input('=');
    cal1.input('*');
    cal1.input('2');
    cal1.input('=');
    cal1.input('*');
    cal1.input('+');
    cal1.input('=');
    assertEquals(cal1.getResult(), "1024");
  }
}






