package portfolio.stock.model;

import java.time.LocalDateTime;

/**
 * Represents a stock. A stock is added to the portfolio when it is bought by the user. Any data
 * retrival classes implements this interface should throw NoSuchElementException if the required
 * data is not found. This is a package private class.
 */
public interface Stock {

  /**
   * Used to get the unique label of each object.
   *
   * @return the label of the stock
   */
  String getTicker();

  /**
   * A getter for the cost basis of the stock.
   *
   * @return the cost basis of the stock.
   */
  double getPrice();

  /**
   * A getter for the date at which the stock was bought.
   *
   * @return the date at which the stock was bought.
   */
  LocalDateTime getDateBought();

  /**
   * A getter for the number of shares of the particular stock bought.
   *
   * @return the number of shares of the particular stock bought.
   */
  double getNumberOfShares();

  /**
   * Gets the value of the stock at the particular date.
   *
   * @param date the date at which the cost is to be found.
   * @return the price of the stock at the specified date.
   */
  double getPriceFromDate(LocalDateTime date);

  /**
   * Gets the commission paid for the stock.
   * @return the commission amount
   */
  double getCommission();

}

