package portfolio.stock.controller.inputcommands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Used to get the dollar cost averaging strategy from the user.
 */
public class DollarCostAveragingInput implements InputCommands {

  private DateTimeFormatter formatter;
  private String date;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private List<String> tickerList;
  private int num;
  private List<Double> tickerWeight;
  private double total;

  /**
   * default constructor.
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
  }

  /**
   * The order of parameter in inputMaps is String,double, date, lists.
   *
   * @param model     represents the model of the application.
   * @param viewPlus  represents the view of the application.
   * @param api       represents the stock database
   * @param inputMaps represents the values that need to be taken from the user.
   * @return false if we need to go back from the buy menu, or true in the end of the method.
   */
  @Override
  public boolean goInputController(UserPortfolioTwo model, PortfolioViewPlus viewPlus,
                                   StockData api, Map... inputMaps) {
    String name;
    double value;
    double commissionFee;
    int days;
    name = viewPlus.getPortfolioName();
    createPortfolio(name, model);
    inputMaps[0].put(Constants.PORTFOLIO_NAME, name);
    viewPlus.putNumberPortfolios();
    while (true) {
      try {
        num = viewPlus.getOption();
        if (num < 0) {
          viewPlus.putInvalidInput();
          continue;
        }
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        return false;
      }
    }
    inputMaps[1].put(Constants.NUM, (double) num);
    while (true) {
      try {
        value = viewPlus.getBuyPrice();
        if (value < 0) {
          viewPlus.putInvalidInput();
          continue;
        }
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        return false;
      }
    }
    inputMaps[1].put(Constants.VALUE, value);
    while (true) {
      try {
        commissionFee = viewPlus.getCommissionPrice();
        if (commissionFee < 0) {
          viewPlus.putInvalidInput();
          continue;
        }
        break;
      } catch (InputMismatchException e) {
        viewPlus.putInvalidInput();
        return false;
      }
    }
    inputMaps[1].put(Constants.COMMISSION_FEE, commissionFee);

    startDate = getDateStart(viewPlus);
    endDate = getDateEnd(viewPlus);

    inputMaps[2].put(Constants.START_DATE, startDate);
    inputMaps[2].put(Constants.END_DATE, endDate);

    viewPlus.putBuyFrequency();
    try {
      days = viewPlus.getOption();
    } catch (InputMismatchException e) {
      viewPlus.putInvalidInput();
      return false;
    }
    inputMaps[1].put(Constants.DAYS, (double) days);
    getTicker(viewPlus, api);
    getWeights(viewPlus);

    inputMaps[3].put(Constants.TICKER_LIST, tickerList);
    inputMaps[4].put(Constants.TICKER_WEIGHT, tickerWeight);
    inputMaps[1].put(Constants.TOTAL, total);
    return true;
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
    for (int i = 1; i <= num; i++) {
      while (true) {
        s = viewPlus.getTickerName(i);
        try {
          api.getPrice(s, startDate);
          tickerList.add(s);
          break;
        } catch (NoSuchElementException e) {
          if (e.getMessage().equals(Constants.INVALID_TICKER)) {
            viewPlus.putInvalidInput();
          }
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
    for (int i = 1; i <= num; i++) {
      while (true) {
        d = viewPlus.getWeight(tickerList.get(i - 1));
        if ((d >= 0)) {
          tickerWeight.add(d);
          total = total + d;
          break;
        }
        viewPlus.putInvalidInput();
      }
    }
  }
}
