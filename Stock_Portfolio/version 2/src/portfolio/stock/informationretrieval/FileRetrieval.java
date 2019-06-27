package portfolio.stock.informationretrieval;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import java.time.LocalDateTime;

/**
 * Used to get stock data from a file. We assume that the file has the data in the following format
 * timestamp,open,high,low,close,volume. The user enters the file path pointing to the csv file for
 * the respective ticker. The file name that contains the particular ticker's data should be given
 * the name of the ticker.
 */
public class FileRetrieval implements StockData {
  private StringBuilder apiOutput;
  private String fileName;

  /**
   * Used to create a FileRetrieval object with the specified filename.
   * @param fileName the file from which data is to be read.
   */
  public FileRetrieval(String fileName) {
    this.fileName = fileName;
    apiOutput = new StringBuilder();
  }


  @Override
  public double getPrice(String ticker, LocalDateTime date) {
    BufferedReader in;
    String a = this.fileName + "/" + ticker + ".csv";
    try {
      in = new BufferedReader(new FileReader(a));
    } catch (FileNotFoundException e) {
      throw new NoSuchElementException();
    }
    String dayValue1;
    try {
      while ((dayValue1 = in.readLine()) != null) {
        apiOutput.append(dayValue1 + "\n");
      }
    } catch (IOException e) {
      throw new NoSuchElementException();
    }
    String stockValues = "";
    String[] lines = apiOutput.toString().split("\\n");
    for (String dayValue : lines) {
      if (dayValue.startsWith(date.toString().substring(0, 10))) {
        stockValues = dayValue;
        break;
      }
    }
    if (stockValues.length() == 0) {
      throw new NoSuchElementException("Date not available!");
    }
    String[] arrOfValues = stockValues.split(",");
    return Double.parseDouble(arrOfValues[4]);
  }
}
