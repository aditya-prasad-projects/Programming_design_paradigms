package portfolio.stock.model.filesavingandretrieval;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Used for doing necessary File validations,
 * like to check if the file exists and to check if a entry exists in a file.
 */
public class FileValidations {
  private BufferedReader in;

  /**
   * Returns true if the file exists, false otherwise.
   * @param fileName the path of the file.
   * @return true if file exists, false otherwise.
   */
  public boolean checkFileExists(String fileName) {

    try {
      in = new BufferedReader(new FileReader(fileName));
      return true;
    } catch (FileNotFoundException e) {
      return false;
    }

  }

  /**
   * Used to check if a unique entry exists in a class, if it does it return the entry,
   * otherwise returns null.
   * @param fileName the file path
   * @param entryName the unique entry in the file
   * @return the entry if it is present, null if it is not.
   */
  public String[] checkUniqueEntryExists(String fileName, String entryName) {
    int i = 0;
    if (checkFileExists(fileName)) {
      String dayValue1;
      String[] arrOfValues;
      try {
        while ((dayValue1 = in.readLine()) != null) {
          i++;
          arrOfValues = dayValue1.split(",");
          if (arrOfValues[0].equals(entryName)) {
            return arrOfValues;
          }
        }
      } catch (IOException e) {
        throw new NoSuchElementException();
      }
    }
    return null;
  }



}
