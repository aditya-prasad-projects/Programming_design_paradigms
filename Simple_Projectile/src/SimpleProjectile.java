public class SimpleProjectile implements Particle {
  private double x;
  private double y;
  private double vx;
  private double vy;

  /**
   * Constructs a SimpleProjectile object and
   * initialize initial position and initial velocity.
   *@param x the initial position
   * @param y the initial position
   * @param vx initial velocity along x axis
   * @param vy initial velocity along y axis
   */

  SimpleProjectile(float x, float y, float vx, float vy) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  SimpleProjectile(double x, double y, double vx, double vy) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  /**
   * Get the state of the particle at time t.
   * @return the string representing the position at time t,formatted to two decimal places
   */

  public String getState(float time) {
    double time1;
    double sx;
    double sx1;
    double sy;
    String a = String.format("%.2f", time);
    String b = String.format("%.2f",x);
    String c = String.format("%.2f",y);
    if (time < 0) {
      return ("At time " + a + ": position is (" + b
              + "," + c + ")");
    }
    sx = x + (vx * time);
    sy = y + (vy * time) - (0.5 * 9.81 * time * time);
    String d = String.format("%.2f",sx);
    String f = String.format("%.2f",sy);
    time1 = (2 * vy) / 9.81;
    sx1 = x + (vx * time1);
    String g = String.format("%.2f",sx1);
    if (time > time1 && vx == 0) {
      return ("At time " + a + ": position is (" + b + "," + c + ")");
    }

    else if (time > time1) {
      return ("At time " + a + ": position is (" + g + "," + c + ")");
    }

    else {
      return ("At time " + String.format("%.2f", time) + ": position is (" + d + "," + f + ")");
    }
  }
}




