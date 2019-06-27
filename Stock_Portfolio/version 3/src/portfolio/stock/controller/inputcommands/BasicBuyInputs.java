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
import portfolio.stock.model.filesavingandretrieval.BasicBuySaveAndRetrieve;
import portfolio.stock.model.filesavingandretrieval.FileParsing;
import portfolio.stock.model.filesavingandretrieval.FileValidations;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlusTwo;

/**
 * Used to get basic buy strategy from the user.
 */
public class BasicBuyInputs implements InputCommands {
  private DateTimeFormatter formatter;
  private Map<Integer, Map> inputMaps;

  /**
   * Default constructor used to initialize all the variables.
   */
  public BasicBuyInputs() {
    formatter = new DateTimeFormatterBuilder()
            .appendPattern(Constants.DATE_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
    Map<String, String> inputDataString = new HashMap<>();
    Map<String, Double> inputDataDouble = new HashMap<>();
    Map<String, LocalDateTime> inputDataDate = new HashMap<>();
    inputMaps = new HashMap<>();
    inputMaps.put(0, inputDataString);
    inputMaps.put(1, inputDataDouble);
    inputMaps.put(2, inputDataDate);

  }

  /**
   * The order of parameter in inputMaps is String,double, date, lists.
   *
   * @param model     represents the model of the application.
   * @param viewPlus  represents the view of the application.
   * @param api       represents the stock database
   * @return false if we need to go back from the buy menu, or true in the end of the method.
   */
  @Override
  public boolean goInputController(UserPortfolioTwo model, PortfolioViewPlusTwo viewPlus,
                                   StockData api) {
    String portfolioName = viewPlus.getPortfolioName();
    if (portfolioName.equals("")) {
      return false;
    }
    inputMaps.get(0).put(Constants.PORTFOLIO_NAME, portfolioName);
    String tickerName = viewPlus.getTicker();
    if (tickerName.equals("")) {
      return false;
    }
    inputMaps.get(0).put(Constants.TICKER_NAME, tickerName);
    double value;
    try {
      value = Double.parseDouble(viewPlus.getBuyPrice());
      if (value == -999) {
        return false;
      }
    } catch (InputMismatchException e) {
      viewPlus.putInvalidInput();
      return false;
    }
    inputMaps.get(1).put(Constants.VALUE, value);
    double commissionFee;
    try {
      commissionFee = Double.parseDouble(viewPlus.getCommissionPrice());
      if (commissionFee == -999) {
        return false;
      }
    } catch (InputMismatchException e) {
      viewPlus.putInvalidInput();
      return false;
    }
    inputMaps.get(1).put(Constants.COMMISSION_FEE, commissionFee);
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
          FileParsing basicSave = new BasicBuySaveAndRetrieve(entryName, this.inputMaps);
          basicSave.writeToFile(fileName, false);
        }
      } else {
        FileParsing basicSave = new BasicBuySaveAndRetrieve(entryName, this.inputMaps);
        basicSave.writeToFile(fileName, true);
      }
    }
  }

  @Override
  public Map<Integer, Map> getInput() {
    return this.inputMaps;
  }
}
