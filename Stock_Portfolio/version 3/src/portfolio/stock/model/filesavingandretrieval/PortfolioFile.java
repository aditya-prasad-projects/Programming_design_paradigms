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
import java.util.List;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.model.investmentstrategy.InvestmentStrategy;
import portfolio.stock.model.Portfolio;
import portfolio.stock.model.Stock;
import portfolio.stock.model.UserPortfolioThree;

/**
 * Used to read and write portfolio data from a file.
 * A object of this class can either read or write into a file, but not both at the same time.
 * If the entry already exists, it asks for the user whether the entry should be overridden.
 * The object is written of the form
 * Unique portfolio name, Ticker symbol1, price1, commission1, date bought1, number of shares1, ...,
 * Ticker symboln, pricen, commissionn, date boughtn, number of sharesn
 */
public class PortfolioFile implements FileParsing {
  private Portfolio p;
  private BufferedReader in;
  private String[] lines = new String[1000];
  private int lineNumber = -1;
  private String portfolioName;
  private UserPortfolioThree model;
  private DateTimeFormatter formatter;

  /**
   * Used for writing the portfolio contents to a file.
   * @param p the portfolio whose contents is to be written in the file.
   */
  public PortfolioFile(Portfolio p) {
    this.p = p;
  }

  /**
   * Used for reading data from the file.
   * @param portfolioName The portfolio that needs to be extracted.
   * @param model the model to which the extracted data is to be added.
   */
  public PortfolioFile(String portfolioName, UserPortfolioThree model) {
    this.portfolioName = portfolioName;
    this.model = model;
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
    PrintWriter write;
    try {
      write = new PrintWriter(new FileWriter(fileName, true));
    } catch (FileNotFoundException e) {
      throw new NoSuchElementException();
    } catch (IOException e) {
      throw new NoSuchElementException();
    }
    if (!overWrite) {
      boolean check = checkDataExists(fileName);
      if (!check) {
        return false;
      }
    }
    StringBuilder sb1 = buildString();
    if (lineNumber == -1) {
      write.write(sb1.toString());
    } else {
      lines[lineNumber - 1] = sb1.toString();
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
   * A private helper method for building the string that is to be added to the file.
   * @return
   */
  protected StringBuilder buildString() {
    StringBuilder sb = new StringBuilder();
    List<Stock> tempStock = p.getStocks();
    sb.append("\n");
    sb.append(p.getName());
    for (int i = 0; i < tempStock.size(); i++) {
      sb.append("," + tempStock.get(i).getTicker() + "," + tempStock.get(i).getPrice() + ","
              + tempStock.get(i).getCommission() + ","
              + tempStock.get(i).getDateBought() + "," + tempStock.get(i).getNumberOfShares());
    }
    return sb;
  }

  /**
   * A private helper method to check if portfolio already exists in the file.
   * @param fileName the path of the file
   * @return false if error occurs, true otherwise.
   */
  protected boolean checkDataExists(String fileName) {
    StringBuilder fileCheck = new StringBuilder();
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
      if (arrOfValues[0].equals(p.getName())) {
        lineNumber = i;
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean readFromFile(String fileName, boolean portfolioExists) {
    Portfolio p;
    if (!portfolioExists) {
      try {
        model.createPortfolio(portfolioName);
      }
      catch (IllegalArgumentException e) {
        return false;
      }
    }
    try {
      in = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e) {
      throw new NoSuchElementException();
    }
    String dayValue1;
    String[] arrOfValues = new String[1000];
    try {
      while ((dayValue1 = in.readLine()) != null) {
        arrOfValues = dayValue1.split(",");
        if (arrOfValues[0].equals(portfolioName)) {
          break;
        }
      }
    } catch (IOException e) {
      throw new NoSuchElementException();
    }
    for (int i = 1; i < arrOfValues.length; i++) {
      model.buyStocksFromFile(portfolioName,arrOfValues[i], Double.parseDouble(arrOfValues[i + 1]),
              Double.parseDouble(arrOfValues[i + 2]),
              LocalDateTime.parse(arrOfValues[i + 3].substring(0, 10),formatter),
              Double.parseDouble(arrOfValues[i + 4]));
      i = i + 4;
    }
    return true;
  }

  @Override
  public InvestmentStrategy getStrategy() {
    return null;
  }


}
