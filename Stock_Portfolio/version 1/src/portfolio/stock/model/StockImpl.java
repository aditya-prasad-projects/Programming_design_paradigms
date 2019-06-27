package portfolio.stock.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;


import portfolio.stock.informationretrieval.StockData;

/**
 * Creates and maintains a stock. This is a package private class.
 */
class StockImpl implements Stock {
  private final String ticker;
  private final double price;
  private LocalDateTime dateBought;
  private final double numberOfShares;
  private static int i = 0;
  private StockData data;

  /**
   * Used to create a stock.
   *
   * @param ticker     the stock label.
   * @param price      the price of the stock.
   * @param dateBought the date at which the stock was bought.
   * @param data       the origin of the stock data.
   */
  StockImpl(String ticker, double price, LocalDateTime dateBought, StockData data) {
    this.ticker = ticker;
    this.price = price;
    this.dateBought = dateBought;
    this.data = data;
    double a = getPriceFromDate(dateBought);
    this.numberOfShares = price / a;

  }

  @Override
  public String getTicker() {
    return ticker;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public LocalDateTime getDateBought() {
    return dateBought;
  }

  @Override
  public double getNumberOfShares() {
    return numberOfShares;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return "Ticker='" + ticker + '\'' +
            "\nPrice bought=" + price +
            "\nDateBought=" + dateBought.format(formatter) +
            "\nNumberOfShares=" + numberOfShares;
  }

  @Override
  public double getPriceFromDate(LocalDateTime date) {
    if (date.getDayOfWeek().getValue() == 7) {
      date = date.plusDays(-2);
    }
    if (date.getDayOfWeek().getValue() == 6) {
      date = date.plusDays(-1);
    }
    try {
      return (data.getPrice(ticker, date));
    } catch (NoSuchElementException e) {
      i = i + 1;
      if (i == 3) {
        throw new NoSuchElementException("It is a holiday! Can't buy stocks");
      }
      date = date.plusDays(-1);
      return (data.getPrice(ticker, date));
    }
  }
}
