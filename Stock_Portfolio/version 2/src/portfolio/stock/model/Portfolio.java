package portfolio.stock.model;

import java.time.LocalDateTime;
import java.util.List;

import portfolio.stock.informationretrieval.StockData;

/**
 * It represents a portfolio of objects. It maintains a list of stocks that belong to it. A
 * portfolio is created by the user and stock is added to the portfolio when the user buys the
 * stock. Portfolio represents the list of stocks bought by the user. This is a package private
 * class.
 */
public interface Portfolio {

  /**
   * Returns the name of the portfolio. Every portfolio name has to be unique.
   *
   * @return the name of the portfolio.
   */
  String getName();

  /**
   * Return the list of stocks belonging to this portfolio.
   *
   * @return the list of stocks.
   */
  List<Stock> getStocks();

  /**
   * Adds stocks to its list of stocks.
   *
   * @param ticker     the label of the stock to be added
   * @param price      the price of the stock to be added
   * @param dateBought the time of purchase of the stock to be added
   * @param data       the place of origin of the stock to be added
   */
  void addStocks(String ticker, double price, LocalDateTime dateBought, StockData data);

  /**
   * Add stocks with their Commission to its list of stocks.
   * @param ticker    the label of the stock to be added
   * @param price     the price of the stock to be added
   * @param commission the commission of the stock to be added
   * @param dateBought the time of purchase of the stock to be added
   * @param data       the place of origin of the stock to be added
   */
  void addStocksWithCommission(String ticker, double price, double commission,
                               LocalDateTime dateBought, StockData data);
}
