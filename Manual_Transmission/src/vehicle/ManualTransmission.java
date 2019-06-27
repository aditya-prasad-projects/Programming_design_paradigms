package vehicle;

/**
 * This Interface represents the Manual Transmission Operations applicable in a car.
 */

public interface ManualTransmission {

  /**
   * Get the status of the vehicle.
   * @return a string representing the status of the vehicle
   */
  String getStatus();

  /**
   * Get the speed of the vehicle.
   * @return the speed of the vehicle
   */
  int getSpeed();

  /**
   * Get the gear of the vehicle.
   * @return the gear of the vehicle
   */
  int getGear();

  /**
   * Increase the speed of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  RegularManualTransmission increaseSpeed();

  /**
   * Decrease the speed of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  RegularManualTransmission decreaseSpeed();

  /**
   * Increase the gear of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  RegularManualTransmission increaseGear();

  /**
   * Decrease the gear of the vehicle safely and appropriately change the status of the vehicle.
   * @return a RegularManualTransmission object
   */
  RegularManualTransmission decreaseGear();

}
