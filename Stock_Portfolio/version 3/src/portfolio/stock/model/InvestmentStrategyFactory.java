package portfolio.stock.model;

import portfolio.stock.model.investmentstrategy.BasicBuy;
import portfolio.stock.model.investmentstrategy.DollarCostAveraging;
import portfolio.stock.model.investmentstrategy.InvestmentStrategy;
import portfolio.stock.model.investmentstrategy.WeightedBuy;

/**
 * A factory class for creating a investment strategy object.
 */
class InvestmentStrategyFactory {

  /**
   * A static factory method for creating a investment strategy file.
   * @param n used for creating the object.
   * @return the investmentStrategy object.
   */
  static InvestmentStrategy getStrategyFactory(int n) {
    if (n == 1) {
      return new BasicBuy();
    }
    else if (n == 2) {
      return new WeightedBuy();
    }
    else if (n == 3) {
      return new DollarCostAveraging();
    }
    else {
      return null;
    }
  }
}
