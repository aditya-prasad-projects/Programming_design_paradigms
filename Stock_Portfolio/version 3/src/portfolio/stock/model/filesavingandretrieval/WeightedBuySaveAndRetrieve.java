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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.model.investmentstrategy.InvestmentStrategy;
import portfolio.stock.model.investmentstrategy.WeightedBuy;
import portfolio.stock.model.Stock;
import portfolio.stock.model.UserPortfolioThree;

/**
 * Used to perform save and retrieve from a file. A object of this class can either read or write
 * into a file, but not both at the same time. If the entry already exists, it asks for the user
 * whether the entry should be overridden. The object is written of the form Unique strategy name,
 * Strategy class name, price, Commission, Date, number of stocks, S1,...Sn, Weight1,...Weightn
 */
public class WeightedBuySaveAndRetrieve implements FileParsing {
  private Map<Integer, Map> inputMaps;
  private String name;
  private String[] lines = new String[1000];
  private int lineNumber = -1;
  private String portfolioName;
  private String[] input;
  private Map<String, String> inputDataString;
  private Map<String, Double> inputDataDouble;
  private Map<String, LocalDateTime> inputDataDate;
  private Map<String, Map<String, Double>> inputDataList;
  private Map<String, List<String>> inputDataStringList;
  private DateTimeFormatter formatter;


  /**
   * Used for writing data into the file.
   * @param name the unique entry name.
   * @param inputMaps the data that is to be written.
   */
  public WeightedBuySaveAndRetrieve(String name, Map<Integer, Map> inputMaps) {
    this.name = name;
    this.inputMaps = inputMaps;
  }

  /**
   * Used for reading from a file and storing the data.
   * @param portfolioName the portfolio to which the strategy is to be applied to.
   * @param model the model that contains the portfolio.
   * @param inputLine the data that is to be read.
   */
  public WeightedBuySaveAndRetrieve(String portfolioName, UserPortfolioThree model,
                                    String... inputLine) {
    this.input = inputLine;
    UserPortfolioThree model1 = model;
    this.portfolioName = portfolioName;
    inputDataString = new HashMap<>();
    inputDataDouble = new HashMap<>();
    inputDataDate = new HashMap<>();
    inputDataList = new HashMap<>();
    inputDataStringList = new HashMap<>();
    formatter = new DateTimeFormatterBuilder()
            .appendPattern(Constants.DATE_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
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
    StringBuilder sb = appendFile();
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
      String temp1 = String.join("\n", lines);
      write.write(temp1);
    }
    write.close();
    lines = new String[100];
    return true;
  }

  /**
   * Used to build the string that is to be written to a file.
   * @return the string builder object that is to be written to the file.
   */
  protected StringBuilder appendFile() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    sb.append(name);
    sb.append(",");
    sb.append("Weighted Buy");
    sb.append(",");
    sb.append(inputMaps.get(1).get(Constants.VALUE));
    sb.append(",");
    sb.append(inputMaps.get(1).get(Constants.COMMISSION_FEE));
    sb.append(",");
    sb.append(inputMaps.get(2).get(Constants.START_DATE));
    sb.append(",");
    List<String> tempList = (List<String>) inputMaps.get(3).get(Constants.TICKER_LIST);
    sb.append(tempList.size());
    sb.append(",");
    for (String s : (List<String>) inputMaps.get(3).get(Constants.TICKER_LIST)) {
      sb.append(s);
      sb.append(",");
    }
    Map<Stock, Double> temp = (Map<Stock, Double>) inputMaps.get(4).get(Constants.STOCK_WEIGHTS);
    for (Double d : temp.values()) {
      sb.append(d);
      sb.append(",");
    }
    return sb;
  }

  @Override
  public boolean readFromFile(String fileName, boolean portfolioExists) {
    return false;
  }

  @Override
  public InvestmentStrategy getStrategy() {
    inputDataString.put(Constants.PORTFOLIO_NAME, portfolioName);
    inputDataDouble.put(Constants.VALUE, Double.parseDouble(input[2]));
    inputDataDouble.put(Constants.COMMISSION_FEE, Double.parseDouble(input[3]));
    LocalDateTime startDate = LocalDateTime.parse(input[4].substring(0, 10), formatter);
    inputDataDate.put(Constants.START_DATE, startDate);
    int num = Integer.parseInt(input[5]);
    List<String> tempTickerList = new ArrayList<>();
    Map<String, Double> stockWeights = new HashMap<>();
    for (int i = 1; i <= num; i++) {
      tempTickerList.add(input[5 + i]);
      stockWeights.put(input[5 + i], Double.parseDouble(input[5 + num + i]));
    }
    inputDataStringList.put(Constants.TICKER_LIST, tempTickerList);
    inputDataList.put(Constants.STOCK_WEIGHTS, stockWeights);
    Double total = 0.0;
    for (Map.Entry<String, Double> n : stockWeights.entrySet()) {
      total = total + n.getValue();
    }
    inputDataDouble.put(Constants.TOTAL, total);
    InvestmentStrategy buy = new WeightedBuy(inputDataString, inputDataDouble, inputDataDate,
            inputDataStringList, inputDataList);
    return buy;
  }


}
