package portfolio.stock.controller.inputcommands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.InputMismatchException;
import java.util.Map;

import portfolio.stock.constants.Constants;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Used to get basic buy strategy from the user.
 */
public class BasicBuyInputs implements InputCommands {
  private DateTimeFormatter formatter;

  /**
   * default constructor.
   */
  public BasicBuyInputs() {
    formatter = new DateTimeFormatterBuilder()
            .appendPattern(Constants.DATE_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
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

    String portfolioName = viewPlus.getPortfolioName();
    if (portfolioName.equals("")) {
      return false;
    }
    inputMaps[0].put(Constants.PORTFOLIO_NAME, portfolioName);
    String tickerName = viewPlus.getTicker();
    if (tickerName.equals("")) {
      return false;
    }
    inputMaps[0].put(Constants.TICKER_NAME, tickerName);
    double value;
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
    double commissionFee;
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
    String date = viewPlus.getBuyDate();
    LocalDateTime startDate;
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
    return true;
  }
}
