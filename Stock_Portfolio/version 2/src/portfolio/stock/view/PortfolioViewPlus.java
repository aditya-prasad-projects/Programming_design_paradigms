package portfolio.stock.view;

public interface PortfolioViewPlus extends PortfolioView {

  /**
   * Used to return the commission price from the user.
   * @return the commission price.
   */
  double getCommissionPrice();

  /**
   * Used to print the updated buy menu.
   */
  void getBuyMenu();

  /**
   * Used to print equal weight options.
   */
  void putEqualWeightOption();

  /**
   * Used to return the weight for a particular ticker.
   * @param ticker the ticker for which weight is to be asked.
   * @return the weight of a particular ticker.
   */
  double getWeight(String ticker);

  /**
   * Used to ask for number of portfolios.
   */
  void putNumberPortfolios();

  /**
   * Used to get the ticker name "num" of times.
   * @param num the number of times ticker name is to be asked.
   * @return the tickername.
   */
  String getTickerName(int num);

  /**
   * Used to get the end date for a strategy.
   * @return date as a string.
   */
  String getEndDate();

  /**
   * Used to get the start date for a strategy.
   * @return date as a string.
   */
  String getStartDate();

  /**
   * Used to ask for number of days the strategy is to be implemented.
   */
  void putBuyFrequency();
}
