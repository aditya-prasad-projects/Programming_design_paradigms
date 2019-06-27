import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

/**
 * A JUnit test class for the RegularManualTransmission class.
 **/

public class RegularManualTransmissionTest {
  private ManualTransmission car;
  private ManualTransmission car1;

  /**
   * Assigning the Testing RegularManualTransmission object.
   */
  @Before
  public void setUp() {
    car = new RegularManualTransmission(0, 5, 4, 9, 8, 13, 12, 17,
            16, 21);
  }

  /**
   * Tests if the object was properly initialized.
   */
  @Test
  public void testInitialization() {
    assertEquals(car.getStatus(), "OK: everything is OK.");
    assertEquals(car.getSpeed(), 0);
    assertEquals(car.getGear(), 1);
  }

  /**
   * Tests if the program throws an exception if first gear doesnt start from zero.
   */
  @Test
  public void testLowEndInitialization() {
    try {
      car1 = new RegularManualTransmission(1, 5, 4, 9, 8, 13, 12,
              17, 16, 21);
      fail("IllegalArgumentException was not caught");
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid speed range for low end of 1st gear, "
              + "It has to be zero");
    }

  }

  /**
   * Tests if the program throws an exception if lower end of gear ranges are in strictly
   * ascending order.
   */
  @Test
  public void testLowEndInitialization1() {
    try {
      car1 = new RegularManualTransmission(0, 5, 4, 9, 3, 13, 12,
              17, 16, 21);
      fail("IllegalArgumentException was not caught");
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid low end speed ranges, "
              + "low end of a higher gear cannot be lesser than the low end of a lesser gear");
    }
  }

  /**
   * Tests if the program throws an exception if the ranges dont overlap.
   */
  @Test
  public void testOverLapInitialization() {
    try {
      car1 = new RegularManualTransmission(0, 5, 6, 9, 8, 13, 12,
              17, 16, 21);
      fail("IllegalArgumentException was not caught");
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid, ranges must be overlapping");
    }
  }

  /**
   * Tests if the program throws an exception if higher end of a gear is less than the lower end.
   */
  @Test
  public void testRangeInitialization() {
    try {
      car1 = new RegularManualTransmission(0, 5, 10, 9, 8, 13, 12,
              17, 16, 21);
      fail("IllegalArgumentException was not caught");
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid speed range, "
              + "high end cannot be lesser than than the low end");
    }
  }

  /**
   * Tests if the program throws an exception if higher end of gear ranges are in strictly
   * ascending order.
   */
  @Test
  public void testHighEndInitialization() {
    try {
      car1 = new RegularManualTransmission(0,5,2,4,3,8,6,12,10,
              13);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid high end speed ranges, "
              + "high end of a higher gear cannot be lesser than the high end of a lesser gear");
    }
  }

  /**
   * Tests if we can increase the speed safely.
   */
  @Test
  public void testSafeSpeedIncrease() {
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: everything is OK.");
    assertEquals(car.getSpeed(), 1);
    assertEquals(car.getGear(), 1);
  }

  /**
   * Tests if we can increase the speed and in the range of the next gear properly.
   */
  @Test
  public void testOverLapIncreaseSpeed() {
    int i;
    for (i = 1; i <= 3; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getStatus(), "OK: everything is OK.");
    }
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: you may increase the gear.");
    assertEquals(car.getGear(), 1);
    assertEquals(car.getSpeed(), 4);
  }

  /**
   * Tests if we can increase speed beyond its gear range.
   */
  @Test
  public void testGreaterThanRangeIncreaseSpeed() {
    int i;
    for (i = 1; i <= 3; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getStatus(), "OK: everything is OK.");
    }
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: you may increase the gear.");
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: you may increase the gear.");
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "Cannot increase speed, increase gear first.");
    assertEquals(car.getSpeed(), 5);
    assertEquals(car.getGear(), 1);
  }

  @Test
  /**
   * Tests if the gear increases safely.
   */
  public void testRandomSafeGearChange() {
    for (int i = 1; i <= 5; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getSpeed(), i);
      assertEquals(car.getGear(), 1);
    }
    car = car.increaseGear();
    assertEquals(car.getGear(), 2);
    car = car.increaseSpeed();
    assertEquals(car.getGear(), 2);
    assertEquals(car.getSpeed(), 6);
    assertEquals(car.getStatus(), "OK: everything is OK.");
  }

  /**
   * Tests if it is accurate when we change the gear including the
   * transition to gear change.
   */
  @Test
  public void testSafeIncreaseGear() {
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: everything is OK.");
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: everything is OK.");
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: everything is OK.");
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: you may increase the gear.");
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "OK: you may increase the gear.");
    car = car.increaseSpeed();
    assertEquals(car.getStatus(), "Cannot increase speed, increase gear first.");
    car = car.increaseGear();
    assertEquals(car.getStatus(), "OK: everything is OK.");
    assertEquals(car.getSpeed(), 5);
    assertEquals(car.getGear(), 2);
  }

  /**
   * Tests that we cannot change the gear, if the speed is not in its range.
   */
  @Test
  public void testLessSpeedIncreaseGear() {
    car = car.increaseGear();
    assertEquals(car.getStatus(), "Cannot increase gear, increase speed first.");
    assertEquals(car.getGear(), 1);
    assertEquals(car.getSpeed(), 0);
  }

  /**
   * Tests that we cannot increase the gear and the speed after reaching the maximum gear and speed.
   */
  @Test
  public void testMaximumGearMaximumSpeedIncreaseGear() {
    int i;
    for (i = 1; i <= 5; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getSpeed(), i);
      assertEquals(car.getGear(), 1);
    }
    car = car.increaseGear();
    for (i = 6; i <= 9; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getSpeed(), i);
      assertEquals(car.getGear(), 2);
    }
    car = car.increaseGear();
    for (i = 10; i <= 13; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getSpeed(), i);
      assertEquals(car.getGear(), 3);
    }
    car = car.increaseGear();
    for (i = 14; i <= 17; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getSpeed(), i);
      assertEquals(car.getGear(), 4);
    }
    car = car.increaseGear();
    for (i = 18; i <= 21; i++) {
      car = car.increaseSpeed();
      assertEquals(car.getSpeed(), i);
      assertEquals(car.getGear(), 5);
    }
    car = car.increaseGear();
    assertEquals(car.getStatus(),"Cannot increase gear. Reached maximum gear.");
    assertEquals(car.getGear(), 5);
    assertEquals(car.getSpeed(), 21);
    car = car.increaseSpeed();
    assertEquals(car.getStatus(),"Cannot increase speed. Reached maximum speed.");
    assertEquals(car.getSpeed(), 21);
    assertEquals(car.getGear(), 5);
  }

  /**
   * Tests that we cannot decrease the gear from first gear.
   */
  @Test
  public void testMinimumGearDecreaseGear() {
    car = car.decreaseGear();
    assertEquals(car.getStatus(), "Cannot decrease gear. Reached minimum gear.");
    assertEquals(car.getGear(), 1);
    assertEquals(car.getSpeed(), 0);
  }

  /**
   * Tests that we cannot decrease gear if the speed is not in its range.
   */
  @Test
  public void testGearHigherThanRangeDecreaseGear() {
    for (int i = 1;i <= 5;i++) {
      car = car.increaseSpeed();
    }
    car = car.increaseGear();
    assertEquals(car.getSpeed(),5);
    assertEquals(car.getGear(),2);
    car = car.increaseSpeed();
    assertEquals(car.getSpeed(),6);
    car = car.decreaseGear();
    assertEquals(car.getStatus(),"Cannot decrease gear, decrease speed first.");
    assertEquals(car.getSpeed(), 6);
    assertEquals(car.getGear(), 2);
  }

  /**
   * Tests if we can safely decrease gear.
   */
  @Test
  public void testSafeGearDecrease() {
    for (int i = 1;i < 5;i++) {
      car = car.increaseSpeed();
    }
    car = car.increaseGear();
    car = car.decreaseGear();
    assertEquals(car.getStatus(),"OK: everything is OK.");
    assertEquals(car.getGear(), 1);
    assertEquals(car.getSpeed(),4);
  }

  /**
   * Tests that we cannot decrease the speed from zero speed.
   */
  @Test
  public void testMinimumSpeedDecreaseSpeed() {
    car = car.decreaseSpeed();
    assertEquals(car.getStatus(), "Cannot decrease speed. Reached minimum speed.");
    assertEquals(car.getSpeed(), 0);
    assertEquals(car.getGear(),1);
  }

  /**
   * Tests the accuracy when we decrease the speed, and the speed is in the
   * lower gears range.
   */
  @Test
  public void testGearRangeOverlapDecreaseSpeed() {
    for (int i = 0;i <= 5;i++) {
      car = car.increaseSpeed();
    }
    car = car.increaseGear();
    car = car.decreaseSpeed();
    assertEquals(car.getStatus(),"OK: you may decrease the gear.");
    assertEquals(car.getGear(), 2);
    assertEquals(car.getSpeed(), 4);
  }

  /**
   * Tests that we cannot decrease the speed if its not in the current gear range.
   */
  @Test
  public void testGearHigherThanRangeDecreaseSpeed() {
    for (int i = 0;i <= 5;i++) {
      car = car.increaseSpeed();
    }
    car = car.increaseGear();
    car = car.decreaseSpeed();
    car = car.decreaseSpeed();
    car = car.decreaseSpeed();
    assertEquals(car.getStatus(), "Cannot decrease speed, decrease gear first.");
    assertEquals(car.getSpeed(), 4);
    assertEquals(car.getGear(), 2);
  }

  /**
   * Tests if we can decrease the speed safely.
   */
  @Test
  public void testSafeDecreaseSpeed() {
    car = car.increaseSpeed();
    car = car.decreaseSpeed();
    assertEquals(car.getStatus(),"OK: everything is OK.");
    assertEquals(car.getGear(), 1);
    assertEquals(car.getSpeed(), 0);
  }

  /**
   * Tests if the speed changes by one every time.
   */
  @Test
  public void testIncreaseSpeedByOne() {
    assertEquals(car.getSpeed(), 0);
    car = car.increaseSpeed();
    assertEquals(car.getSpeed(), 1);
  }
}
