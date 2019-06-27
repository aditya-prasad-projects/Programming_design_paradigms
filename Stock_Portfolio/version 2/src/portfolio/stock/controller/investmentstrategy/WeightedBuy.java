package portfolio.stock.controller.investmentstrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.controller.inputcommands.InputCommands;
import portfolio.stock.controller.inputcommands.WeightedBuyInput;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.Stock;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Used to implement the weighted buy strategy. The strategy can only be implemented on an existing
 * portfolio and the stocks currently in the portfolio will be bought. The amount of each stock to
 * be bought is given as weights. It is not required for the weights to add upto 100, the ratio of
 * the weights to the total weights is taken to calculate the price to be bought.
 */
public class WeightedBuy implements InvestmentStrategy {

  private Map<String, String> inputDataString;
  private Map<String, Double> inputDataDouble;
  private Map<String, LocalDateTime> inputDataDate;
  private Map<Stock, Map<Stock, Double>> inputDataList;

  /**
   * Default constructor.
   */
  public WeightedBuy() {
    inputDataString = new HashMap<>();
    inputDataDouble = new HashMap<>();
    inputDataDate = new HashMap<>();
    inputDataList = new HashMap<>();
  }

  @Override
  public void goController(UserPortfolioTwo model, PortfolioViewPlus viewPlus, StockData api) {

    InputCommands input = new WeightedBuyInput();
    if (!input.goInputController(model, viewPlus, api, inputDataString, inputDataDouble,
            inputDataDate, inputDataList)) {
      return;
    }
    buyStocksWithWeights(viewPlus, model, api);
  }


  /**
   * Used to buy stocks with weights.
   *
   * @param viewPlus view from which input is to be taken from the user.
   * @param model    the model which stored the portfolio.
   * @param api      the stock data base.
   */
  private void buyStocksWithWeights(PortfolioViewPlus viewPlus, UserPortfolioTwo model,
                                    StockData api) {
    try {
      for (Map.Entry<Stock, Double> t : inputDataList.get(Constants.STOCK_WEIGHTS).entrySet()) {
        double w = inputDataDouble.get(Constants.VALUE) * (t.getValue() / inputDataDouble.
                get(Constants.TOTAL));
        model.buyStocksWithCommission(inputDataString.get(Constants.PORTFOLIO_NAME),
                t.getKey().getTicker(),
                w, inputDataDouble.get(Constants.COMMISSION_FEE),
                inputDataDate.get(Constants.START_DATE), api);
      }
      viewPlus.putSuccess();
    } catch (NoSuchElementException e) {
      viewPlus.putStockHoliday();
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
