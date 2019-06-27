package portfolio.stock.controller.inputcommands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import portfolio.stock.constants.Constants;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.filesavingandretrieval.FileParsing;
import portfolio.stock.model.filesavingandretrieval.FileValidations;
import portfolio.stock.model.filesavingandretrieval.WeightedBuySaveAndRetrieve;
import portfolio.stock.model.Portfolio;
import portfolio.stock.model.Stock;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;
import portfolio.stock.view.PortfolioViewPlusTwo;

/**
 * Used to get weighted buy strategy from the user.
 */
public class WeightedBuyInput implements InputCommands {

  private DateTimeFormatter formatter;
  private Map<Stock, Double> stockWeights;
  private double total;
  private List<String> tickerList;
  private Map<Integer, Map> inputMaps;


  /**
   * Default constructor.
   */
  public WeightedBuyInput() {
    formatter = new DateTimeFormatterBuilder()
            .appendPattern(Constants.DATE_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
    stockWeights = new HashMap<>();
    tickerList = new ArrayList<>();
    inputMaps = new HashMap<>();
    Map<String, String> inputDataString = new HashMap<>();
    Map<String, Double> inputDataDouble = new HashMap<>();
    Map<String, LocalDateTime> inputDataDate = new HashMap<>();
    Map<String, Map<Stock, Double>> inputDataList = new HashMap<>();
    Map<String, List<String>> inputDataStringList = new HashMap<>();
    inputMaps.put(0, inputDataString);
    inputMaps.put(1, inputDataDouble);
    inputMaps.put(2, inputDataDate);
    inputMaps.put(3, inputDataStringList);
    inputMaps.put(4, inputDataList);
  }

  /**
   * The order of parameter in inputMaps is String,double, date, lists.
   *
   * @param model    represents the model of the application.
   * @param viewPlus represents the view of the application.
   * @param api      represents the stock database
   * @return false if we need to go back from the buy menu, or true in the end of the method.
   */
  @Override
  public boolean goInputController(UserPortfolioTwo model, PortfolioViewPlusTwo viewPlus,
                                   StockData api) {
    int option;
    String portfolioName;
    while (true) {
      portfolioName = viewPlus.getPortfolioName();
      if (portfolioName == null || portfolioName.equals("")) {
        viewPlus.putInvalidInput();
        continue;
      }
      break;
    }
    inputMaps.get(0).put(Constants.PORTFOLIO_NAME, portfolioName);
    try {
      model.portfolioContents(portfolioName);
    } catch (IllegalStateException e) {
      viewPlus.portfolioNotExists();
      return false;
    }
    boolean priceCheck = price(viewPlus);
    if (!priceCheck) {
      return false;
    }
    boolean commissionFeesCheck = commissionFees(viewPlus);
    if (!commissionFeesCheck) {
      return false;
    }
    boolean dateError = getDate(viewPlus);
    if (!dateError) {
      return false;
    }
    while (true) {
      viewPlus.putEqualWeightOption();
      String s;
      try {
        s = viewPlus.getSplitOption();
        if (s.equals("0")) {
          viewPlus.putInvalidInput();
          continue;
        }
        option = Integer.parseInt(s);
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        continue;
      } catch (NumberFormatException e) {
        viewPlus.putInvalidInput();
        continue;
      }
    }
    Portfolio p = model.portfolioContents(portfolioName);
    total = 0;
    switch (option) {
      case 1:
        equalWeights(p);
        break;
      case 2:
        differentWeights(p, viewPlus);
        break;
      default:
        viewPlus.putInvalidInput();
        return false;
    }
    inputMaps.get(1).put(Constants.TOTAL, total);
    inputMaps.get(4).put(Constants.STOCK_WEIGHTS, stockWeights);
    inputMaps.get(3).put(Constants.TICKER_LIST, tickerList);
    return true;
  }

  /**
   * A helper to get the price form the user.
   *
   * @param viewPlus a view object
   * @return false if error occurs, true otherwise.
   */
  protected boolean price(PortfolioViewPlusTwo viewPlus) {
    double value;
    String s;
    while (true) {
      try {
        s = viewPlus.getBuyPrice();

        if (s == null) {
          viewPlus.putInvalidInput();
          continue;
        }
        value = Double.parseDouble(s);
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        continue;
      } catch (NumberFormatException e) {
        viewPlus.putInvalidInput();
        continue;
      }
    }
    inputMaps.get(1).put(Constants.VALUE, value);
    return true;
  }

  /**
   * A helper to get the commissionFee from the user.
   *
   * @param viewPlus a view object
   * @return false if error occurs, true otherwise.
   */
  protected boolean commissionFees(PortfolioViewPlusTwo viewPlus) {
    double commissionFee;
    String s;
    while (true) {
      try {
        s = viewPlus.getCommissionPrice();
        if (null == s) {
          viewPlus.putInvalidInput();
          continue;
        }
        commissionFee = Double.parseDouble(s);
        if (commissionFee == -999) {
          return false;
        }
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        return false;
      } catch (NumberFormatException e) {
        viewPlus.putInvalidInput();
        continue;
      }
    }
    inputMaps.get(1).put(Constants.COMMISSION_FEE, commissionFee);
    return true;
  }

  /**
   * A helper to get the date from the user.
   *
   * @param viewPlus a view object
   * @return false if error occurs, true otherwise.
   */
  protected boolean getDate(PortfolioViewPlusTwo viewPlus) {
    String date;
    LocalDateTime startDate;
    while (true) {
      date = viewPlus.getBuyDate();
      if (date == null || date.equals("")) {
        viewPlus.putInvalidInput();
        continue;
      }
      try {
        startDate = LocalDateTime.parse(date, formatter);
        break;
      } catch (DateTimeParseException f) {
        viewPlus.putInvalidDate();
        continue;
      }
    }
    inputMaps.get(2).put(Constants.START_DATE, startDate);
    return true;
  }

  @Override
  public void save(PortfolioViewPlusTwo viewPlusTwo) {
    String save = viewPlusTwo.saveStrategy();
    String entryName;
    if (save.matches("[Yy][Ee][Ss]")) {
      FileValidations validate = new FileValidations();
      String fileName;
      fileName = viewPlusTwo.fileToWrite();
      entryName = viewPlusTwo.getStrategyName();
      if (entryName.equals("q")) {
        return;
      }
      String[] index = validate.checkUniqueEntryExists(fileName, entryName);
      if (index != null) {
        String overWriteStrategy = viewPlusTwo.entryExistOverwrite();
        if (overWriteStrategy.matches("[Yy][Ee][Ss]")) {
          FileParsing basicSave = new WeightedBuySaveAndRetrieve(entryName, this.inputMaps);
          basicSave.writeToFile(fileName, false);
        }
      } else {
        FileParsing basicSave = new WeightedBuySaveAndRetrieve(entryName, this.inputMaps);
        basicSave.writeToFile(fileName, true);
      }
    }
  }

  @Override
  public Map<Integer, Map> getInput() {
    return this.inputMaps;
  }

  /**
   * Used for calculation regarding equal weights.
   *
   * @param p the portfolio on which equal weights is to be applied
   */
  private void equalWeights(Portfolio p) {
    for (Stock s : p.getStocks()) {
      if (stockWeights.containsKey(s)) {
        continue;
      }
      stockWeights.put(s, 1.0);
      tickerList.add(s.getTicker());
      total = total + 1;
    }
  }

  /**
   * Used for calculation regarding different weights.
   *
   * @param p        the portfolio on which different weights is to be applied
   * @param viewPlus the view implementation from which the user gets the view.
   */
  private void differentWeights(Portfolio p, PortfolioViewPlus viewPlus) {
    for (Stock s : p.getStocks()) {
      if (stockWeights.containsKey(s)) {
        continue;
      }
      double weight;
      while (true) {
        weight = Double.parseDouble(viewPlus.getWeight(s.getTicker()));
        if (weight >= 0) {
          break;
        }
        viewPlus.putInvalidInput();
      }
      stockWeights.put(s, weight);
      tickerList.add(s.getTicker());
      total = total + weight;
    }
  }
}
