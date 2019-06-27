package portfolio.stock.informationretrieval;

import java.time.LocalDateTime;

/**
 * Represents stock data. If any new stock data is to be used by our application,
 * make it extend this interface.
 * All the data retrieval class implementations should extend this interface.
 */
public interface StockData {

  /**
   * Takes in a ticker label and a date, and returns the price per share of the
   * label on that particular date.
   * @param ticker the stock label for which price per share is to be found.
   * @param date the date at which the stocks price per share is to be found.
   * @return the price per share of the label on that particular date.
   */
  double getPrice(String ticker, LocalDateTime date);
}
