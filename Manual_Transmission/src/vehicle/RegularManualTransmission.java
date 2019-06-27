package vehicle;

import java.util.Map;
import java.util.HashMap;

/**
 * We can use the class to perform operations like increasing speed, decreasing speed,
 * increasing gear, decreasing gear, getting the status of the vehicle, gear level and speed value.
 */

public class RegularManualTransmission implements ManualTransmission {
  private final int l1;
  private final int h1;
  private final int l2;
  private final int h2;
  private final int l3;
  private final int h3;
  private final int l4;
  private final int h4;
  private final int l5;
  private final int h5;
  private int speed;
  private int gear;
  private String statusTransmission;
  private Map<Integer, Integer> low = new HashMap<Integer, Integer>();
  private Map<Integer, Integer> high = new HashMap<Integer, Integer>();

  /**
   * Constructs a RegularManualTransmission object and
   * initializes the ranges.
   * @param l1 the low end of first gear, should always be zero
   * @param h1 the high end of first gear
   * @param l2 the low end of second gear
   * @param h2 the high end of second gear
   * @param l3 the low end of third gear
   * @param h3 the high end of third gear
   * @param l4 the low end of fourth gear
   * @param h4 the high end of fourth gear
   * @param l5 the low end of fifth gear
   * @param h5 the high end of fifth gear
   */
  public RegularManualTransmission(int l1, int h1, int l2, int h2, int l3, int h3, int l4,
                                   int h4, int l5,
                            int h5) throws IllegalArgumentException {
    if (l1 > h1 || l2 > h2 || l3 > h3 || l4 > h4
            || l5 > h5) {
      throw new IllegalArgumentException("Invalid speed range, "
              + "high end cannot be lesser than than the low end");
    }
    if (l1 >= l2 || l2 >= l3 || l3 >= l4 || l4 >= l5) {
      throw new IllegalArgumentException("Invalid low end speed ranges, "
              + "low end of a higher gear cannot be lesser than the low end of a lesser gear");
    }
    if (h1 >= h2 || h2 >= h3 || h3 >= h4 || h4 >= h5) {
      throw new IllegalArgumentException("Invalid high end speed ranges, "
              + "high end of a higher gear cannot be lesser than the high end of a lesser gear");
    }
    if (l2 > h1 || l3 > h2 || l4 > h3 || l5 > h4) {
      throw new IllegalArgumentException("Invalid, ranges must be overlapping");
    }
    if (l1 != 0) {
      throw new IllegalArgumentException("Invalid speed range for low end of 1st gear, "
              + "It has to be zero");
    }
    this.l1 = l1;
    this.l2 = l2;
    this.l3 = l3;
    this.l4 = l4;
    this.l5 = l5;
    this.h1 = h1;
    this.h2 = h2;
    this.h3 = h3;
    this.h4 = h4;
    this.h5 = h5;
    this.statusTransmission = "OK: everything is OK.";
    this.speed = 0;
    this.gear = 1;
    low.put(1, l1);
    low.put(2, l2);
    low.put(3, l3);
    low.put(4, l4);
    low.put(5, l5);
    high.put(1,h1);
    high.put(2,h2);
    high.put(3,h3);
    high.put(4,h4);
    high.put(5,h5);
  }

  public int getSpeed() {
    return this.speed;
  }

  public int getGear() {
    return this.gear;
  }

  /**
   * Increase the speed of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  public RegularManualTransmission increaseSpeed() {
    RegularManualTransmission bike = makeObject(this.l1, this.h1, this.l2, this.h2, this.l3,
            this.h3, this.l4, this.h4, this.l5, this.h5, this.speed, this.gear,
            this.statusTransmission, this.high, this.low);
    if (bike.speed + 1 > bike.high.get(5)) {
      bike.statusTransmission = "Cannot increase speed. Reached maximum speed.";
    }
    else if (bike.gear != 5 && bike.speed + 1 >= bike.low.get(bike.gear + 1)
            && bike.speed + 1 <= bike.high.get(bike.gear)) {
      bike.speed += 1;
      bike.statusTransmission = "OK: you may increase the gear.";
    }
    else if (bike.speed + 1 > bike.high.get(bike.gear)) {
      bike.statusTransmission = "Cannot increase speed, increase gear first.";
    }
    else {
      bike.speed += 1;
      bike.statusTransmission = "OK: everything is OK.";
    }
    return bike;
  }

  /**
   * Decrease the speed of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  public RegularManualTransmission decreaseSpeed() {
    RegularManualTransmission bike = makeObject(this.l1, this.h1, this.l2, this.h2, this.l3,
            this.h3, this.l4, this.h4, this.l5, this.h5, this.speed, this.gear,
            this.statusTransmission, this.high, this.low);
    if (bike.speed - 1 < bike.low.get(1)) {
      bike.statusTransmission = "Cannot decrease speed. Reached minimum speed.";
    }
    else if (bike.gear != 1 && bike.speed - 1 <= bike.high.get(bike.gear - 1)
            && bike.speed - 1 >= bike.low.get(bike.gear)) {
      bike.speed -= 1;
      bike.statusTransmission = "OK: you may decrease the gear.";
    }
    else if (bike.speed - 1 < bike.low.get(bike.gear)) {
      bike.statusTransmission = "Cannot decrease speed, decrease gear first.";
    }
    else {
      bike.speed -= 1;
      bike.statusTransmission = "OK: everything is OK.";
    }
    return bike;
  }

  /**
   * Increase the gear of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  public RegularManualTransmission increaseGear() {
    RegularManualTransmission bike = makeObject(this.l1, this.h1, this.l2, this.h2, this.l3,
            this.h3, this.l4, this.h4, this.l5, this.h5, this.speed, this.gear,
            this.statusTransmission, this.high, this.low);
    if (bike.gear + 1 > 5) {
      bike.statusTransmission = "Cannot increase gear. Reached maximum gear.";
    }
    else if (bike.speed < bike.low.get(bike.gear + 1)) {
      bike.statusTransmission = "Cannot increase gear, increase speed first.";
    }
    else {
      bike.gear += 1;
      bike.statusTransmission = "OK: everything is OK.";
    }
    return bike;
  }

  /**
   * Decrease the gear of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  public RegularManualTransmission decreaseGear() {
    RegularManualTransmission bike = makeObject(this.l1, this.h1, this.l2, this.h2, this.l3,
            this.h3, this.l4, this.h4, this.l5, this.h5, this.speed, this.gear,
            this.statusTransmission, this.high, this.low);
    if (bike.gear - 1 < 1) {
      bike.statusTransmission = "Cannot decrease gear. Reached minimum gear.";
    }
    else if (bike.speed > bike.low.get(bike.gear)) {
      bike.statusTransmission = "Cannot decrease gear, decrease speed first.";
    }
    else {
      bike.gear -= 1;
      bike.statusTransmission = "OK: everything is OK.";
    }
    return bike;
  }

  public String getStatus() {
    return this.statusTransmission;
  }

  private RegularManualTransmission makeObject(int l1, int h1, int l2, int h2, int l3, int h3,
                                               int l4, int h4, int l5, int h5, int s, int g,
                                               String st, Map high, Map low) {
    RegularManualTransmission a = new RegularManualTransmission(l1, h1, l2, h2, l3, h3, l4, h4,
            l5, h5);
    a.speed = s;
    a.gear = g;
    a.statusTransmission = st;
    a.high = high;
    a.low = low;
    return a;
  }
}


