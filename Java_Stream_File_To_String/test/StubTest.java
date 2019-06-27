import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


import grades.Gradebook;
import grades.StudentRecord;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests Gradebook class.
 */
public class StubTest {

  private Gradebook records;
  private List<Double> weights;
  private List<Double> finalScores;
  private List<String> letterGrades;
  private List<String> firstNames, lastNames;
  private final int NumAssignments = 4;
  private List<String> letters;
  private List<Double> thresholds;

  @Before
  public void setup() {

    letters = Arrays.asList(new String[]{"F","D-","D","D+","C-","C","C+",
            "B-","B","B+","A-","A"});
    thresholds = Arrays.asList(new Double[]{60.0,63.0,66.0,70.0,73.0,76.0,
            80.0,83.0,86.0,90.0,93.0,100.0});
    records = new Gradebook(letters,thresholds);
    finalScores = new ArrayList<Double>();
    letterGrades = new ArrayList<String>();
    firstNames = new ArrayList<String>();
    lastNames = new ArrayList<String>();
    int i = 0;
    while (i < input.length) {
      String fName = input[i];
      String lName = input[i + 1];
      double[] points = new double[NumAssignments];
      for (int j = 0; j < NumAssignments; j++) {
        points[j] = 100 * Double.parseDouble(input[i + 2 + j]);
      }

      finalScores.add(Double.parseDouble(input[i + 2 + NumAssignments]));
      letterGrades.add(input[i + 2 + NumAssignments+1]);
      firstNames.add(fName);
      lastNames.add(lName);

      i = i + 4 + NumAssignments;
      records.addStudent(new StudentRecord(fName, lName, points));
    }

    weights = new ArrayList<Double>();
    weights.add(20.0);
    weights.add(30.0);
    weights.add(40.0);
    weights.add(10.0);

  }

  /**
   * Tests if the finalScores are computed correctly.
   */
  @Test
  public void testIndividualGrades() {
    List<Double> finals = records.getFinalScores(weights);
    records.averageScoreForName("Aditya", weights);
    for (int i = 0; i < finalScores.size(); i++) {
      assertEquals(finalScores.get(i), finals.get(i), 0.001);

    }
  }

  /**
   * Tests if countLetterGrade function executes correctly.
   */
  @Test
  public void testcountLetterGrade() {
    assertEquals(records.countLetterGrade("A", weights), 8);
    assertEquals(records.countLetterGrade("B", weights), 4);
    assertEquals(records.countLetterGrade("A-", weights), 2);
    assertEquals(records.countLetterGrade("B-", weights), 4);
    assertEquals(records.countLetterGrade("C", weights), 2);
    assertEquals(records.countLetterGrade("B+", weights), 6);
    assertEquals(records.countLetterGrade("C-", weights), 7);
    assertEquals(records.countLetterGrade("C+", weights), 9);
    assertEquals(records.countLetterGrade("D+", weights), 7);
    assertEquals(records.countLetterGrade("D", weights), 1);
    assertEquals(records.countLetterGrade("D-", weights), 0);
    assertEquals(records.countLetterGrade("F", weights), 2);
  }

  /**
   * Tests if countLetterGrade works for invalid grade values.
   */
  @Test
  public void testcountLetterGrade1() {
    try {
      assertEquals(records.countLetterGrade("F-", weights), 7);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid Grade");
    }
  }

  /**
   * Tests if getStudentNames function concatenates students name correctly.
   */
  @Test
  public void testGetStudentNames() {
    for (int i = 0; i < firstNames.size(); i++) {
      assertEquals(firstNames.get(i) + " " + lastNames.get(i),
              records.getStudentNames().get(i));
    }
  }

  /**
   * Tests if the AverageScoreForName function executes correctly for different function.
   */
  @Test
  public void testAverageScoreForName() {
    assertEquals(records.averageScoreForName("Amit", weights),
            70.8548951, 0.001);
    assertEquals(records.averageScoreForName("Aditya", weights),
            79.5050, 0.001);
    assertEquals(records.averageScoreForName("Praful", weights),
            78.822,0.001);
  }

  /**
   * Tests if the AverageScoreForName function takes in invalid values and return 0.
   */
  @Test
  public void testAverageScoreForName1() {
    assertEquals(records.averageScoreForName("Amita", weights),
            0.0, 0.001);
  }

  /**
   * Tests if countAboveAverage function executes correctly.
   */
  @Test
  public void testcountAboveAverage()
  {
    assertEquals(records.countAboveAverage(weights), 27);
  }

  // Data from the Excel file, to be used for testing
  String []input = {"Amit"
          , "Shesh"
          , "0.920833333"
          , "0.8"
          , "0.656410256"
          , "0.218181818"
          , "70.8548951"
          , "C-"
          , "Clark"
          , "Freifeld"
          , "1"
          , "0.888888889"
          , "0.9"
          , "0.987012987"
          , "92.53679654"
          , "A-"
          , "Aniruddha"
          , "Tapas"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Aditya"
          , "Sathyanarayan"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Ritika"
          , "Nair"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Rohan"
          , "Chitnis"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Aditya"
          , "Prasad"
          , "0.92076"
          , "1"
          , "0.978"
          , "0.8908"
          , "96.4432"
          , "A"
          , "Saurabha"
          , "Jirgi"
          , "0.9232"
          , "1"
          , "0.898"
          , "0.989"
          , "94.274"
          , "A"
          , "Divya"
          , "Venkatesh"
          , "0.989"
          , "1"
          , "0.9787"
          , "0.786"
          , "96.788"
          , "A"
          , "Praful"
          , "Badami"
          , "0.767"
          , "0.787"
          , "0.989"
          , "0.9676"
          , "88.186"
          , "B+"
          , "Amruta"
          , "Nayar"
          , "0.878"
          , "0.989"
          , "0.978"
          , "0.978"
          , "96.13"
          , "B+"
          , "Sheetal"
          , "Shekar"
          , "0.989"
          , "0.787"
          , "0.767"
          , "0.989"
          , "83.96"
          , "B"
          , "Pavan"
          , "Hegde"
          , "0.787"
          , "0.545"
          , "0.878"
          , "0.989"
          , "77.1"
          , "C+"
          , "Chandrajith"
          , "Shivanna"
          , "0.898"
          , "0.979"
          , "0.767"
          , "0.676"
          , "84.77"
          , "B"
          , "Pavan"
          , "Shekar"
          , "0.989"
          , "0.979"
          , "0.767"
          , "0.101"
          , "80.84"
          , "B-"
          , "Aditya"
          , "Krishna"
          , "0.878"
          , "0.989"
          , "0.787"
          , "0.888"
          , "87.59"
          , "B+"
          , "Saurabha"
          , "Hegde"
          , "0.676"
          , "0.888"
          , "0.999"
          , "1"
          , "90.12"
          , "A-"
          , "Aditya"
          , "Shivanna"
          , "0.9494"
          , "0.5465"
          , "0.989"
          , "0.323"
          , "78.173"
          , "C+"
          , "Praful"
          , "Shekar"
          , "0.9891"
          , "0.3456"
          , "0.9878"
          , "0.675"
          , "76.412"
          , "C+"
          , "Aditya"
          , "Hegde"
          , "0.787"
          , "0.989"
          , "0.678"
          , "0.9789"
          , "82.319"
          , "B-"
          , "Praful"
          , "Jirgi"
          , "0.979"
          , "0.456"
          , "0.768"
          , "0.789"
          , "71.87"
          , "C-"
          , "Roosevelt"
          , "Ramsey"
          , "0.52"
          , "0.64"
          , "0.82"
          , "0.71"
          , "69.5"
          , "D+"
          , "Debbie"
          , "Phillips"
          , "0.58"
          , "0.98"
          , "0.64"
          , "0.87"
          , "75.3"
          , "C"
          , "Robin"
          , "Poole"
          , "0.83"
          , "0.96"
          , "0.8"
          , "0.58"
          , "83.2"
          , "B"
          , "Rogelio"
          , "Cain"
          , "0.64"
          , "0.74"
          , "0.59"
          , "0.8"
          , "66.6"
          , "D+"
          , "Christine"
          , "Mendoza"
          , "0.89"
          , "0.92"
          , "0.79"
          , "0.51"
          , "82.1"
          , "B-"
          , "Kristine"
          , "Higgins"
          , "0.94"
          , "0.7"
          , "0.56"
          , "0.72"
          , "69.4"
          , "D+"
          , "Kurt"
          , "Mcbride"
          , "0.56"
          , "0.79"
          , "0.9"
          , "0.89"
          , "79.8"
          , "C+"
          , "Micheal"
          , "Cooper"
          , "0.85"
          , "0.57"
          , "0.88"
          , "0.51"
          , "74.4"
          , "C"
          , "Eunice"
          , "Aguila"
          , "0.77"
          , "0.56"
          , "0.88"
          , "0.89"
          , "76.3"
          , "C+"
          , "Scott"
          , "Buchanan"
          , "0.75"
          , "0.86"
          , "0.95"
          , "0.93"
          , "88.1"
          , "B+"
          , "Shayna"
          , "White"
          , "0.54"
          , "0.98"
          , "0.61"
          , "0.6"
          , "70.6"
          , "C-"
          , "Gregor"
          , "Ffion"
          , "0.74"
          , "0.74"
          , "0.55"
          , "0.91"
          , "68.1"
          , "D+"
          , "Ffion"
          , "Burgess"
          , "0.73"
          , "0.6"
          , "1"
          , "0.6"
          , "78.6"
          , "C+"
          , "Gregor"
          , "Burgess"
          , "0.94"
          , "0.72"
          , "1"
          , "0.67"
          , "87.1"
          , "B+"
          , "Ffion"
          , "Blair"
          , "0.52"
          , "0.9"
          , "0.86"
          , "0.51"
          , "76.9"
          , "C+"
          , "Gregor"
          , "Blair"
          , "0.6"
          , "0.9"
          , "0.8"
          , "0.76"
          , "78.6"
          , "C+"
          , "Maksim"
          , "Wilks"
          , "0.53"
          , "0.7"
          , "0.76"
          , "0.99"
          , "71.9"
          , "C-"
          , "Armani"
          , "Dawson"
          , "0.95"
          , "0.85"
          , "0.78"
          , "0.98"
          , "85.5"
          , "B"
          , "Maksim"
          , "Schmitt"
          , "0.69"
          , "0.95"
          , "0.92"
          , "0.75"
          , "86.6"
          , "B+"
          , "Armani"
          , "Wilks"
          , "0.55"
          , "0.52"
          , "0.75"
          , "0.88"
          , "65.4"
          , "D"
          , "Maksim"
          , "Haris"
          , "0.58"
          , "0.59"
          , "0.73"
          , "0.85"
          , "67"
          , "D+"
          , "Dawson"
          , "Wilks"
          , "0.67"
          , "0.99"
          , "1"
          , "0.99"
          , "93"
          , "A"
          , "Maksim"
          , "Dawson"
          , "0.52"
          , "0.57"
          , "0.98"
          , "0.57"
          , "72.4"
          , "C-"
          , "Nala"
          , "Blanchard"
          , "0.68"
          , "0.98"
          , "0.62"
          , "0.85"
          , "76.3"
          , "C+"
          , "Keeley"
          , "Wallace"
          , "0.98"
          , "0.66"
          , "0.6"
          , "0.66"
          , "70"
          , "C-"
          , "Keeley"
          , "Blanchard"
          , "0.57"
          , "0.79"
          , "0.65"
          , "0.73"
          , "68.4"
          , "D+"
          , "Keeley"
          , "Keeley"
          , "0.6"
          , "0.74"
          , "0.73"
          , "0.85"
          , "71.9"
          , "C-"
          , "Wallace"
          , "Blanchard"
          , "0.77"
          , "0.85"
          , "0.99"
          , "0.63"
          , "86.8"
          , "B+"
          , "Myron"
          , "Sears"
          , "0.79"
          , "0.95"
          , "0.81"
          , "0.58"
          , "82.5"
          , "B-"
          , "Myron"
          , "Blanchard"
          , "1"
          , "1"
          , "1"
          , "1"
          , "100"
          , "A"
          , "Myron"
          , "Myron"
          , "0"
          , "0"
          , "0"
          , "0"
          , "0"
          , "F"
  };
}