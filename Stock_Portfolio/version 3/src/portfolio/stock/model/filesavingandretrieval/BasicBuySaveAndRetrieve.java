package portfolio.stock.model.filesavingandretrieval;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.model.investmentstrategy.BasicBuy;
import portfolio.stock.model.investmentstrategy.InvestmentStrategy;
import portfolio.stock.model.UserPortfolioThree;

/**
 * Used to Perform Save and retrieve for BasicBuy.
 * A object of this class can either read or write into a file, but not both at the same time.
 * If the entry already exists, it asks for the user whether the entry should be overridden.
 * The object is written of the form
 * Unique Strategy name, Strategy class name,Ticker Symbol, Price, Commission, Date
 */
public class BasicBuySaveAndRetrieve implements FileParsing {

  private Map<Integer, Map> inputMaps;
  private String name;
  private String[] lines = new String[1000];
  private int lineNumber = -1;
  private String portfolioName;
  private String[] input;
  private DateTimeFormatter formatter;
  private Map<String, String> inputDataString;
  private Map<String, Double> inputDataDouble;
  private Map<String, LocalDateTime> inputDataDate;


  /**
   * Used for writing into a file.
   * @param name uniqueName of the entry.
   * @param inputMaps input data to be written.
   */
  public BasicBuySaveAndRetrieve(String name, Map<Integer, Map> inputMaps) {
    this.inputMaps = inputMaps;
    this.name = name;
  }

  /**
   * Used for retrieving into a file.
   * @param portfolioName Unique portfolio name.
   * @param model a model object
   * @param inputLine the input to be written.
   */
  public BasicBuySaveAndRetrieve(String portfolioName, UserPortfolioThree model,
                                 String... inputLine) {
    this.input = inputLine;
    UserPortfolioThree model1 = model;
    this.portfolioName = portfolioName;
    formatter = new DateTimeFormatterBuilder()
            .appendPattern(Constants.DATE_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
    inputDataString = new HashMap<>();
    inputDataDouble = new HashMap<>();
    inputDataDate = new HashMap<>();
  }

  @Override
  public boolean writeToFile(String fileName, boolean overWrite) {
    if (fileName.length() > 4) {
      if (!fileName.substring(fileName.length() - 4).equals(".csv")) {
        fileName = fileName + ".csv";
      }
    }
    BufferedReader in;
    PrintWriter write;
    StringBuilder fileCheck = new StringBuilder();
    try {
      write = new PrintWriter(new FileWriter(fileName, true));
    } catch (FileNotFoundException e) {
      throw new NoSuchElementException();
    } catch (IOException e) {
      throw new NoSuchElementException();
    }
    StringBuilder sb = appendToFile();
    if (!overWrite) {
      try {
        in = new BufferedReader(new FileReader(fileName));
      } catch (FileNotFoundException e) {
        throw new NoSuchElementException();
      }
      String dayValue1;
      try {
        while ((dayValue1 = in.readLine()) != null) {
          fileCheck.append(dayValue1 + "\n");
        }
      } catch (IOException e) {
        throw new NoSuchElementException();
      }
      String stockValues;
      lines = fileCheck.toString().split("\\n");
      int i = 0;
      for (String dayValue : lines) {
        i++;
        stockValues = dayValue;
        String[] arrOfValues = stockValues.split(",");
        if (arrOfValues[0].equals(name)) {
          lineNumber = i;
          break;
        }
      }
    }
    if (lineNumber == -1) {
      write.write(sb.toString());
    } else {
      lines[lineNumber - 1] = sb.toString();
      try {
        write = new PrintWriter(new FileWriter(fileName));
      } catch (IOException e) {
        throw new NoSuchElementException();
      }
      String temp = String.join("\n", lines);
      write.write(temp);
    }
    write.close();
    lines = new String[100];
    return true;

  }

  /**
   * A helper method to create the string that is to be appended to the file.
   * @return The StringBuilder object that is to be appended to the file.
   */
  protected StringBuilder appendToFile() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    sb.append(name);
    sb.append(",");
    sb.append("Basic buy");
    sb.append(",");
    sb.append(inputMaps.get(0).get(Constants.TICKER_NAME));
    sb.append(",");
    sb.append(inputMaps.get(1).get(Constants.VALUE));
    sb.append(",");
    sb.append(inputMaps.get(1).get(Constants.COMMISSION_FEE));
    sb.append(",");
    sb.append(inputMaps.get(2).get(Constants.START_DATE));
    return sb;
  }

  @Override
  public boolean readFromFile(String fileName, boolean portfolioExists) {
    return true;
  }

  @Override
  public InvestmentStrategy getStrategy() {
    inputDataString.put(Constants.PORTFOLIO_NAME, portfolioName);
    inputDataString.put(Constants.TICKER_NAME, input[2]);
    inputDataDouble.put(Constants.VALUE, Double.parseDouble(input[3]));
    inputDataDouble.put(Constants.COMMISSION_FEE, Double.parseDouble(input[4]));
    LocalDateTime startDate = LocalDateTime.parse(input[5].substring(0,10), formatter);
    inputDataDate.put(Constants.START_DATE,startDate);
    InvestmentStrategy buy = new BasicBuy(inputDataString, inputDataDouble, inputDataDate);
    return buy;
  }


}
