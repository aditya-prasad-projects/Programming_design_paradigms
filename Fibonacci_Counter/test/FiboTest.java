import static org.junit.Assert.assertEquals;
import fib.CounterException;
import fib.Fibo;
import fib.FibonacciCounter;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Fibo class.
 */
public class FiboTest {
  private FibonacciCounter n;

  @Before
  public void setup() {
    n = new Fibo();
  }

  @Test
  public void testInitialization() {
    assertEquals(n.getCount(), 1);
    assertEquals(n.getFibo(), 0);
  }

  @Test
  public void testIncreaseFibo() {
    n.increaseFibo();
    assertEquals(n.getCount(),2);
    assertEquals(n.getFibo(),1);
  }

  @Test
  public void testRandomIncreaseFibo() {
    int i;
    for (i = 2;i <= 5;i++) {
      n.increaseFibo();
    }
    assertEquals(n.getCount(), 5);
    assertEquals(n.getFibo(), 3);
  }

  @Test
            (expected = CounterException.class)
  public void testDecreaseFibo() throws CounterException {
    n.decreaseFibo();
  }

  @Test
  public void testSafeDecreaseFibo() throws CounterException {
    n.increaseFibo();
    assertEquals(n.getCount(),2);
    n.decreaseFibo();
    assertEquals(n.getCount(), 1);
    assertEquals(n.getFibo(), 0);
  }
}