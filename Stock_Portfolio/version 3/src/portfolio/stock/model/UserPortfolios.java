package portfolio.stock.model;

import java.time.LocalDateTime;

import portfolio.stock.informationretrieval.StockData;

/**
 * Represents a model for the stock MVC application.
 * It provides different operation for the user to use in creating, maintaining and querying the
 * portfolios.
 */
public interface UserPortfolios {

  /**
   * Used to create a portfolio with the specified name. Names have to be unique,
   * no two portfolio can have the same name. Names are case insensitive.
   * @param name the name of the portfolio to be created.
   */
  void createPortfolio(String name);

  /**
   * Used to get the stock to be bought and add it to the specified portfolio.
   * @param portfolioName represents the portfolio to which stock is to be added.
   * @param ticker the label of the stock to be added.
   * @param price the price of the stock to be added.
   * @param date the date at which the stock was bought.
   * @param data the data object from which the stock data is to be got.
   */
  void buyStocks(String portfolioName, String ticker,
                 double price, LocalDateTime date, StockData data);

  /**
   * Returns the total value of all the portfolio at the specified date.
   * @param date The date at which the total value of the stock is to be calculated.
   * @return the total value of the stock at the specified date.
   */
  double getTotalValue(LocalDateTime date);

  /**
   * Returns the total cost basis value of all the portfolio at the specified date.
   * @param date The date at which the total cost basis value of the stock is to be calculated.
   * @return the total cost basis value of the stock at the specified date.
   */
  double getTotalCostBasis(LocalDateTime date);

  /**
   * Used to get the specified portfolio contents.
   * @param portfolioName the portfolio whose contents are to be displayed.
   * @return the portfolio name, the stocks data in the specified portfolio.
   */
  Portfolio portfolioContents(String portfolioName);
}
