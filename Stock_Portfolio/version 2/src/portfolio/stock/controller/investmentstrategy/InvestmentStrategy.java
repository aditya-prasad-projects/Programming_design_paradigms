package portfolio.stock.controller.investmentstrategy;

import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Represents an investment strategy to buy stock.
 */
public interface InvestmentStrategy {

  /**
   * The classes that implement different strategies should implement this method.
   * @param model for which the strategy is to be applied.
   * @param viewPlus for which the strategy is to be applied.
   * @param api to get the stock data.
   */
  void goController(UserPortfolioTwo model, PortfolioViewPlus viewPlus, StockData api);
}
