package portfolio.stock.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Objects;


import portfolio.stock.informationretrieval.StockData;

/**
 * Creates and maintains a stock. This is a package private class.
 */
public class StockImpl implements Stock {
  private final String ticker;
  private final double price;
  private LocalDateTime dateBought;
  private final double numberOfShares;
  private static int i = 0;
  private StockData data;
  private double commission;

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

  /**
   * Used to create a bought stock.
   * @param ticker     the stock label.
   * @param price      the price of the stock.
   * @param commission the amount of commission paid.
   * @param dateBought the date at which the stock was bought.
   * @param shares the number of shares bought.
   */
  StockImpl(String ticker, double price, double commission,
            LocalDateTime dateBought, double shares) {
    this.ticker = ticker;
    this.price = price;
    this.commission = commission;
    this.dateBought = dateBought;
    this.numberOfShares = shares;
  }

  /**
   * Creates a deep copy of the stock passed as parameter.
   *
   * @param s the stock that should be copied.
   */

  StockImpl(Stock s) {
    this.ticker = s.getTicker();
    this.price = s.getPrice();
    this.dateBought = s.getDateBought();
    this.data = data;
    this.numberOfShares = s.getNumberOfShares();
    this.commission = s.getCommission();
  }

  /**
   * Used to create a stock with commission.
   *
   * @param ticker     the stock label.
   * @param price      the price of the stock.
   * @param commission the amount of commission paid.
   * @param dateBought the date at which the stock was bought.
   * @param data       the origin of the stock data.
   */
  StockImpl(String ticker, double price, double commission,
            LocalDateTime dateBought, StockData data) {
    this(ticker, price, dateBought, data);
    this.commission = commission;
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
  public double getCommission() {
    return this.commission;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return "Ticker='" + ticker + '\'' +
            "\nPrice bought=" + price +
            "\nCommission=" + commission +
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

  /**
   * Two stock objects are equal if their ticker symbols are equal.
   *
   * @param o object to be compared.
   * @return true if equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StockImpl)) {
      return false;
    }
    StockImpl stock = (StockImpl) o;
    return Objects.equals(ticker, stock.ticker);
  }

  /**
   * Objects with the same ticker symbol have the same hashcode.
   *
   * @return hashcode of the ticker symbol.
   */
  @Override
  public int hashCode() {
    return Objects.hash(ticker);
  }
}
