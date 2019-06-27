import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Person class.
 **/

public class SimpleProjectileTest {
  private SimpleProjectile newton;

  @Before
  public void setUp() {
    newton = new SimpleProjectile(0, 0,10, 5);
  }

  @Test
  public void testgetState() {
    assertEquals(newton.getState(0), "At time 0.00: position is (0.00,0.00)");
  }
}

