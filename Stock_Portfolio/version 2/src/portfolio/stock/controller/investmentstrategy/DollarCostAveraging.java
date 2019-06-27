package portfolio.stock.controller.investmentstrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.controller.inputcommands.DollarCostAveragingInput;
import portfolio.stock.controller.inputcommands.InputCommands;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Used to implement the dollar cost averaging strategy. This can be done on an existing portfolio
 * or it creates a portfolio if the portfolio does not exist. The amount of each stock to be bought
 * is given as weights. It is not required for the weights to add upto 100, the ratio of the weights
 * to the total weights is taken to calculate the price to be bought.
 */
public class DollarCostAveraging implements InvestmentStrategy {

  private Map<String, String> inputDataString;
  private Map<String, Double> inputDataDouble;
  private Map<String, LocalDateTime> inputDataDate;
  private Map<String, List<String>> inputDataStringList;
  private Map<String, List<Double>> inputDataDoubleList;


  /**
   * Default constructor to initialize the private variables..
   */
  public DollarCostAveraging() {
    inputDataString = new HashMap<>();
    inputDataDouble = new HashMap<>();
    inputDataDate = new HashMap<>();
    inputDataStringList = new HashMap<>();
    inputDataDoubleList = new HashMap<>();
  }

  @Override
  public void goController(UserPortfolioTwo model, PortfolioViewPlus viewPlus, StockData api) {
    InputCommands input = new DollarCostAveragingInput();
    if (!input.goInputController(model, viewPlus, api, inputDataString,
            inputDataDouble, inputDataDate,
            inputDataStringList, inputDataDoubleList)) {
      return;
    }
    buyDates(model, api);
    viewPlus.putSuccess();
  }

  /**
   * Used to buy stock in the interval provided.
   *
   * @param model the model where the portfolios are to be stored after buying.
   * @param api   the stock database from which stocks are to be bought.
   */
  private void buyDates(UserPortfolioTwo model, StockData api) {
    int i;
    LocalDateTime startDate = inputDataDate.get(Constants.START_DATE);
    LocalDateTime tempDate = LocalDateTime.parse(startDate.toString());
    while (startDate.isBefore(inputDataDate.get(Constants.END_DATE)) &&
            tempDate.isBefore(inputDataDate.get(Constants.END_DATE))) {
      i = 0;
      tempDate = LocalDateTime.parse(startDate.toString());
      while (i < inputDataDouble.get(Constants.NUM)) {
        try {
          model.buyStocksWithCommission(inputDataString.get(Constants.PORTFOLIO_NAME),
                  inputDataStringList.get(Constants.TICKER_LIST).get(i),
                  inputDataDouble.get(Constants.VALUE) *
                          (inputDataDoubleList.get(Constants.TICKER_WEIGHT).get(i) /
                                  inputDataDouble.get(Constants.TOTAL)),
                  inputDataDouble.get(Constants.COMMISSION_FEE), tempDate, api);
          i++;
        } catch (NoSuchElementException e) {
          tempDate = tempDate.plusDays(1);
        } catch (IllegalArgumentException g) {
          if (g.getMessage().equals(Constants.MARKET_CLOSED)) {
            tempDate = tempDate.plusDays(1);
          }
        }
      }
      startDate = startDate.plusDays(inputDataDouble.get(Constants.DAYS).intValue());
    }
  }
}
