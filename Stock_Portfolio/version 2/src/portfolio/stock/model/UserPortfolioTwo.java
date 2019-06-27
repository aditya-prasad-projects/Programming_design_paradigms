package portfolio.stock.model;

import java.time.LocalDateTime;

import portfolio.stock.informationretrieval.StockData;

public interface UserPortfolioTwo extends UserPortfolios {

  /**
   * Used to buy Stocks with commission.
   * @param pName represents the portfolio to which stock is to be added
   * @param ticker the label of the stock to be added.
   * @param price the price of the stock to be added.
   * @param commission the amount of commission paid to the stock.
   * @param date the date at which the stock was bought.
   * @param data the data object from which the stock data is to be got.
   */
  void buyStocksWithCommission(String pName, String ticker, double price,double commission,
                               LocalDateTime date, StockData data);


}
