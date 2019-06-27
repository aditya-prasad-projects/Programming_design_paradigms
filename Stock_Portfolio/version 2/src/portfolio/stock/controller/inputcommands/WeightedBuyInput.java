package portfolio.stock.controller.inputcommands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import portfolio.stock.constants.Constants;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.Portfolio;
import portfolio.stock.model.Stock;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Used to get weighted buy strategy from the user.
 */
public class WeightedBuyInput implements InputCommands {

  private DateTimeFormatter formatter;
  private Map<Stock, Double> stockWeights;
  private double total;


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
    int option;
    String portfolioName;
    double value;
    String date;
    double commissionFee;
    LocalDateTime startDate;
    portfolioName = viewPlus.getPortfolioName();
    if (portfolioName.equals("")) {
      return false;
    }
    inputMaps[0].put(Constants.PORTFOLIO_NAME, portfolioName);
    try {
      model.portfolioContents(portfolioName);
    } catch (IllegalStateException e) {
      viewPlus.portfolioNotExists();
      return false;
    }
    try {
      value = viewPlus.getBuyPrice();
      if (value == -999) {
        return false;
      }
    } catch (InputMismatchException e) {
      viewPlus.putInvalidInput();
      return false;
    }
    inputMaps[1].put(Constants.VALUE, value);
    try {
      commissionFee = viewPlus.getCommissionPrice();
      if (commissionFee == -999) {
        return false;
      }
    } catch (InputMismatchException e) {
      viewPlus.putInvalidInput();
      return false;
    }
    inputMaps[1].put(Constants.COMMISSION_FEE, commissionFee);

    date = viewPlus.getBuyDate();
    if (date.equals("")) {
      return false;
    }
    try {
      startDate = LocalDateTime.parse(date, formatter);
    } catch (DateTimeParseException f) {
      viewPlus.putInvalidDate();
      return false;
    }

    inputMaps[2].put(Constants.START_DATE, startDate);

    viewPlus.putEqualWeightOption();
    try {
      option = viewPlus.getOption();
    } catch (InputMismatchException e) {
      viewPlus.putInvalidInput();
      return false;
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
    inputMaps[1].put(Constants.TOTAL, total);
    inputMaps[3].put(Constants.STOCK_WEIGHTS, stockWeights);

    return true;
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
        weight = viewPlus.getWeight(s.getTicker());
        if (weight >= 0) {
          break;
        }
        viewPlus.putInvalidInput();
      }
      stockWeights.put(s, weight);
      total = total + weight;
    }
  }
}
