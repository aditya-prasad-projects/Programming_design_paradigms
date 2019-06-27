package portfolio.stock.controller.investmentstrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.controller.inputcommands.BasicBuyInputs;
import portfolio.stock.controller.inputcommands.InputCommands;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Used to implement the basic buy strategy.
 */
public class BasicBuy implements InvestmentStrategy {

  private Map<String, String> inputDataString;
  private Map<String, Double> inputDataDouble;
  private Map<String, LocalDateTime> inputDataDate;
  private Map<String, Object> inputDataObject;

  /**
   * Default constructor.
   */
  public BasicBuy() {
    inputDataString = new HashMap<>();
    inputDataDouble = new HashMap<>();
    inputDataDate = new HashMap<>();
    inputDataObject = new HashMap<>();
  }

  @Override
  public void goController(UserPortfolioTwo model, PortfolioViewPlus viewPlus, StockData api) {
    InputCommands input = new BasicBuyInputs();
    if (!input.goInputController(model, viewPlus, api, inputDataString, inputDataDouble,
            inputDataDate, inputDataObject)) {
      return;
    }
    try {
      model.buyStocksWithCommission(inputDataString.get(Constants.PORTFOLIO_NAME),
              inputDataString.get(Constants.TICKER_NAME), inputDataDouble.get(Constants.VALUE),
              inputDataDouble.get(Constants.COMMISSION_FEE), inputDataDate.
                      get(Constants.START_DATE), api);
      viewPlus.putSuccess();
    } catch (NoSuchElementException e) {
      viewPlus.putStockHoliday();
    } catch (DateTimeParseException f) {
      viewPlus.putInvalidDate();
    } catch (IllegalArgumentException g) {
      if (g.getMessage().equals(Constants.MARKET_CLOSED)) {
        viewPlus.putMarketClosed();
      } else if (g.getMessage().equals(Constants.PORTFOLIO_DOES_NOT_EXIST)) {
        viewPlus.portfolioNotExists();
      } else {
        viewPlus.putInvalidInput();
      }

    }

  }
}
