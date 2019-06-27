package portfolio.stock.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import portfolio.stock.informationretrieval.StockData;

/**
 * Represents a portfolio. Each portfolio should have a unique name. Names are not case sensitive.
 * This is a package private class.
 */
public class PortfolioImpl implements Portfolio {

  private final String name;
  private List<Stock> stocks;

  /**
   * Initializes the PortfolioImpl class object with a unique name.
   *
   * @param name the name of the portfolio.
   */
  public PortfolioImpl(String name) {
    this.name = name;
    stocks = new ArrayList<>();
  }

  /**
   * Copy constructor.
   */
  PortfolioImpl(Portfolio p) {
    this.name = p.getName();
    List<Stock> temp = new ArrayList<>();
    for (int i = 0; i < p.getStocks().size(); i++) {
      temp.add(new StockImpl(p.getStocks().get(i)));
    }
    this.stocks = temp;

  }


  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<Stock> getStocks() {
    return stocks;
  }

  @Override
  public void addStocks(String ticker, double price, LocalDateTime dateBought, StockData data) {
    if (dateBought.getHour() > 15 || dateBought.getHour() < 9) {
      throw new IllegalArgumentException("Market closed!");
    }
    if (dateBought.getDayOfWeek().getValue() == 7) {
      throw new NoSuchElementException("Cannot buy on a holiday");
    }
    if (dateBought.getDayOfWeek().getValue() == 6) {
      throw new NoSuchElementException("Cannot buy on a holiday");
    }
    Stock currentStock = new StockImpl(ticker, price, dateBought, data);
    stocks.add(currentStock);
  }

  @Override
  public void addStocksWithCommission(String ticker, double price, double commission,
                                      LocalDateTime dateBought, StockData data) {
    if (dateBought.getHour() > 15 || dateBought.getHour() < 9) {
      throw new IllegalArgumentException("Market closed!");
    }
    if (dateBought.getDayOfWeek().getValue() == 7) {
      dateBought = dateBought.plusDays(1);
    }
    if (dateBought.getDayOfWeek().getValue() == 6) {
      dateBought = dateBought.plusDays(2);
    }
    Stock currentStock = new StockImpl(ticker, price, commission, dateBought, data);
    stocks.add(currentStock);

  }


  @Override
  public void addStocksFromFile(String ticker, double price, double commission,
                                LocalDateTime dateBought, double shares) {
    if (dateBought.getHour() > 15 || dateBought.getHour() < 9) {
      throw new IllegalArgumentException("Market closed!");
    }
    if (dateBought.getDayOfWeek().getValue() == 7) {
      dateBought = dateBought.plusDays(1);
    }
    if (dateBought.getDayOfWeek().getValue() == 6) {
      dateBought = dateBought.plusDays(2);
    }
    Stock currentStock = new StockImpl(ticker, price, commission, dateBought, shares);
    stocks.add(currentStock);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("PortfolioName = " + name + "\n\n");
    for (int i = 0; i < stocks.size(); i++) {
      s.append("stock " + (i + 1) + ":\n");
      s.append(stocks.get(i).toString() + "\n");
      s.append("\n\n");
    }
    return s.toString();
  }

  /**
   * Two PortfolioImpl are equal if their names are equal. Names are case-insensitive.
   *
   * @param o the object this is to be compared with.
   * @return true if if their names are equal. Names are case-sensitive. false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Portfolio) {
      return this.getName().equalsIgnoreCase(((Portfolio) o).getName());
    } else {
      return false;
    }
  }

  /**
   * HashCode of objects are equal if their names are equal. Names are case-insensitive.
   *
   * @return the hashCode
   */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
