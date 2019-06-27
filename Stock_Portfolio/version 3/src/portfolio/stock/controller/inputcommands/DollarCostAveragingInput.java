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
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.filesavingandretrieval.DollarCostAveragingSaveAndRetrieve;
import portfolio.stock.model.filesavingandretrieval.FileParsing;
import portfolio.stock.model.filesavingandretrieval.FileValidations;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;
import portfolio.stock.view.PortfolioViewPlusTwo;

/**
 * Used to get the dollar cost averaging strategy from the user.
 */
public class DollarCostAveragingInput implements InputCommands {

  private DateTimeFormatter formatter;
  private String date;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private List<String> tickerList;
  private String num;
  private List<Double> tickerWeight;
  private double total;
  private Map<Integer, Map> inputMaps;

  /**
   * Default constructor to initialize all the variables.
   */
  public DollarCostAveragingInput() {
    formatter = new DateTimeFormatterBuilder()
            .appendPattern(Constants.DATE_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
    tickerList = new ArrayList<>();
    tickerWeight = new ArrayList<>();
    inputMaps = new HashMap<>();
    Map<String, String> inputDataString = new HashMap<>();
    Map<String, Double> inputDataDouble = new HashMap<>();
    Map<String, LocalDateTime> inputDataDate = new HashMap<>();
    Map<String, List<String>> inputDataStringList = new HashMap<>();
    Map<String, List<Double>> inputDataDoubleList = new HashMap<>();
    inputMaps.put(0, inputDataString);
    inputMaps.put(1, inputDataDouble);
    inputMaps.put(2, inputDataDate);
    inputMaps.put(3, inputDataStringList);
    inputMaps.put(4, inputDataDoubleList);
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
    createPortfolioDollarCost(viewPlus, model);
    boolean checkPriceError = price(viewPlus);
    if (!checkPriceError) {
      return false;
    }
    boolean numberOfStocksError = numberOfStocks(viewPlus);
    if (!numberOfStocksError) {
      return false;
    }
    boolean commissionFeeError = commissionPriceDollarCost(viewPlus);
    if (!commissionFeeError) {
      return false;
    }
    startDate = getDateStart(viewPlus);
    endDate = getDateEnd(viewPlus);
    inputMaps.get(2).put(Constants.START_DATE, startDate);
    inputMaps.get(2).put(Constants.END_DATE, endDate);
    frequency(viewPlus);
    getTicker(viewPlus, api);
    getWeights(viewPlus);
    inputMaps.get(3).put(Constants.TICKER_LIST, tickerList);
    inputMaps.get(4).put(Constants.TICKER_WEIGHT, tickerWeight);
    inputMaps.get(1).put(Constants.TOTAL, total);
    return true;
  }

  /**
   * Helper method to get the number of stocks to be entered.
   *
   * @param viewPlus a view object
   * @return false if error occurs, true otherwise.
   */
  protected boolean numberOfStocks(PortfolioViewPlusTwo viewPlus) {
    while (true) {
      try {
        viewPlus.putNumberPortfolios();
        num = viewPlus.getNumberStocks();
        try {
          int a = Integer.parseInt(num);
          if (a < 0) {
            viewPlus.putInvalidInput();
            continue;
          }
        } catch (NumberFormatException e) {
          viewPlus.putInvalidInput();
          continue;
        }
        break;
      } catch (InputMismatchException e) {
        return false;
      }
    }
    inputMaps.get(1).put(Constants.NUM, Double.parseDouble(num));
    return true;
  }

  /**
   * Helper method to get the Commission fee from the user.
   *
   * @param viewPlus a view object
   * @return false if error occurs, true otherwise.
   */
  protected boolean commissionPriceDollarCost(PortfolioViewPlusTwo viewPlus) {
    String commissionFee;
    while (true) {
      try {
        commissionFee = (viewPlus.getCommissionPrice());
        if (null == commissionFee) {
          viewPlus.putInvalidInput();
          continue;
        }
        double a = Double.parseDouble(commissionFee);
        if (a < 0) {
          viewPlus.putInvalidInput();
          continue;
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
    inputMaps.get(1).put(Constants.COMMISSION_FEE, Double.parseDouble(commissionFee));
    return true;
  }

  /**
   * Helper method to get the portfolio Name.
   *
   * @param viewPlus a view object
   * @param model    a model object
   */
  protected void createPortfolioDollarCost(PortfolioViewPlusTwo viewPlus, UserPortfolioTwo model) {
    String name;
    while (true) {
      name = viewPlus.getPortfolioName();
      if (name == null) {
        viewPlus.putInvalidInput();
        continue;
      }
      createPortfolio(name, model);
      break;
    }
    inputMaps.get(0).put(Constants.PORTFOLIO_NAME, name);
  }

  /**
   * Helper method to get the price from the user.
   *
   * @param viewPlus a view object
   * @return false if error occurs, true otherwise.
   */
  protected boolean price(PortfolioViewPlusTwo viewPlus) {
    String value;
    while (true) {
      try {
        value = viewPlus.getBuyPrice();
        if (null == value) {
          viewPlus.putInvalidInput();
          continue;
        }
        try {
          double a = Double.parseDouble(value);
          if (a < 0) {
            viewPlus.putInvalidInput();
            continue;
          }
        } catch (NumberFormatException e) {
          viewPlus.putInvalidInput();
          continue;
        }
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        return false;
      }
    }
    inputMaps.get(1).put(Constants.VALUE, Double.parseDouble(value));
    return true;
  }

  /**
   * Helper to get the frequency of execution of a strategy from the user.
   *
   * @param viewPlus a view object.
   */
  protected void frequency(PortfolioViewPlusTwo viewPlus) {
    viewPlus.putBuyFrequency();
    String days;
    while (true) {
      try {
        days = viewPlus.getBuyFrequency();
        if (days == null) {
          viewPlus.putInvalidInput();
          continue;
        }
        Double.parseDouble(days);
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        continue;
      } catch (NumberFormatException e) {
        viewPlus.putInvalidInput();
        continue;
      }
    }
    inputMaps.get(1).put(Constants.DAYS, Double.parseDouble(days));
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
          FileParsing basicSave = new DollarCostAveragingSaveAndRetrieve(entryName, this.inputMaps);
          basicSave.writeToFile(fileName, false);
        }
      } else {
        FileParsing basicSave = new DollarCostAveragingSaveAndRetrieve(entryName, this.inputMaps);
        basicSave.writeToFile(fileName, true);
      }
    }
  }

  @Override
  public Map<Integer, Map> getInput() {
    return this.inputMaps;
  }

  /**
   * Used to get the start date from the user.
   *
   * @param viewPlus the view implementation
   * @return the date entered by the user.
   */
  private LocalDateTime getDateStart(PortfolioViewPlus viewPlus) {
    while (true) {
      date = viewPlus.getStartDate();
      if (date == null) {
        viewPlus.putInvalidInput();
        continue;
      }
      try {
        startDate = LocalDateTime.parse(date, formatter);
        break;
      } catch (DateTimeParseException f) {
        viewPlus.putInvalidInput();
      }
    }
    return startDate;
  }

  /**
   * Used to get the end date for the strategy from the user.
   *
   * @param viewPlus the view implementation
   * @return the date entered by the user.
   */
  private LocalDateTime getDateEnd(PortfolioViewPlus viewPlus) {
    while (true) {
      date = viewPlus.getEndDate();
      if (date == null) {
        viewPlus.putInvalidInput();
        continue;
      }
      if (date.equals("N") || date.equals("n")) {
        endDate = LocalDateTime.now();
        break;
      }
      try {
        endDate = LocalDateTime.parse(date, formatter);
        if (endDate.isBefore(startDate) || endDate.isAfter(LocalDateTime.now())) {
          throw new DateTimeParseException("", "", 1);
        }
        break;
      } catch (DateTimeParseException f) {
        viewPlus.putInvalidInput();
      }
    }
    return endDate;
  }

  /**
   * Used to create the portfolio.
   *
   * @param name  the name of the portfolio to be created.
   * @param model the model which should contain the portfolio.
   */
  private void createPortfolio(String name, UserPortfolioTwo model) {
    if (name.equals("")) {
      return;
    }
    try {
      model.createPortfolio(name);
    } catch (IllegalArgumentException e) {
      //work on existing portfolio.
    }
  }

  /**
   * Used to get the ticker from the user.
   *
   * @param viewPlus the view implementation from which the information is to be got.
   * @param api      the stockDatabase against which the information has to be verified.
   */
  private void getTicker(PortfolioViewPlus viewPlus, StockData api) {
    String s;
    for (int i = 1; i <= Double.parseDouble(num); i++) {
      while (true) {
        s = viewPlus.getTickerName(i);
        try {
          api.getPrice(s, startDate);
          tickerList.add(s);
          break;
        } catch (NoSuchElementException e) {
          viewPlus.putInvalidInput();
        }
      }
    }
  }

  /**
   * Used to get the ticker weights from the user.
   *
   * @param viewPlus the view implementation from which the information is to be got.
   */
  private void getWeights(PortfolioViewPlus viewPlus) {
    double d;
    String s;
    for (int i = 1; i <= Double.parseDouble(num); i++) {
      while (true) {
        try {
          s = viewPlus.getWeight(tickerList.get(i - 1));
          if (s == null) {
            viewPlus.putInvalidInput();
            continue;
          }
          d = Double.parseDouble(s);
          if ((d >= 0)) {
            tickerWeight.add(d);
            total = total + d;
            break;
          }
          viewPlus.putInvalidInput();
        } catch (NumberFormatException e) {
          viewPlus.putInvalidInput();
          continue;
        }
      }
    }
  }
}
