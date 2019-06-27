package encoder;

import java.util.Comparator;
import java.util.Map;

/**
 * Used to create the comparator object for PriorityQueue.
 */
public class EncoderComparator implements Comparator<String> {
  private Map<String, Long> frequency;

  /**
   * Takes in the map, whose values are used to compare the objects.
   * @param frequency a map object whose values are used to compare the objects.
   */
  EncoderComparator(Map<String,Long> frequency) {
    this.frequency = frequency;
  }

  @Override
  public int compare(String o1, String o2) {
    if (frequency.get(o1).equals(frequency.get(o2))) {
      return o1.compareTo(o2);
    }
    else {
      return (int)(frequency.get(o1) - frequency.get(o2));
    }
  }
}
