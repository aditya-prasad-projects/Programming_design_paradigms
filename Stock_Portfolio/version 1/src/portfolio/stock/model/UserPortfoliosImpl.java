package portfolio.stock.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import portfolio.stock.informationretrieval.StockData;

/**
 * Represents the implementation of the model of the stock MVC application. Maintains a list of
 * portfolios. All the dates used are in 24hour format. The portfolio name is not case sensitive.
 */
public class UserPortfoliosImpl implements UserPortfolios {

  private List<Portfolio> portfolios;
  /**
   * The formatter for the date, and it is being used in three public functions.
   */
  private DateTimeFormatter formatter;

  /**
   * Used to create the model object.
   */
  public UserPortfoliosImpl() {
    portfolios = new ArrayList<>();
    formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
  }

  @Override
  public void createPortfolio(String name) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    for (Portfolio p : portfolios) {
      if (p.getName().equalsIgnoreCase(name)) {
        throw new IllegalArgumentException("Portfolio already exists");
      }
    }
    portfolios.add(new PortfolioImpl(name));
  }

  @Override
  public void buyStocks(String pName, String ticker, double price, String date, StockData data) {

    if (pName == null || pName.equals("") || ticker == null || ticker.equals("") || data == null
            || data.equals("") || date == null || date.equals("")) {
      throw new IllegalArgumentException("Inputs cant be null");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price cant be negative!");
    }
    for (Portfolio p : portfolios) {
      if (p.getName().equalsIgnoreCase(pName)) {
        p.addStocks(ticker, price, LocalDateTime.parse(date, formatter), data);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist!");
  }

  @Override
  public double getTotalValue(String date) {
    if (date == null || date.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    double ret = 0;
    LocalDateTime localDate = LocalDateTime.parse(date, formatter);
    for (Portfolio p : portfolios) {
      for (Stock stock : p.getStocks()) {
        if (stock.getDateBought().isBefore(localDate) || stock.getDateBought().equals(localDate)) {
          ret = ret + stock.getPriceFromDate(localDate) * stock.getNumberOfShares();
        }
      }
    }
    return ret;
  }

  @Override
  public double getTotalCostBasis(String date) {
    if (date == null || date.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    double ret = 0;
    LocalDateTime localDate = LocalDateTime.parse(date, formatter);
    for (Portfolio p : portfolios) {
      for (Stock stock : p.getStocks()) {
        if (stock.getDateBought().isBefore(localDate) || stock.getDateBought().equals(localDate)) {
          ret = ret + stock.getPrice();
        }
      }
    }
    return ret;
  }

  @Override
  public String portfolioContents(String portfolioName) {
    if (portfolioName == null || portfolioName.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    StringBuffer s = new StringBuffer();
    for (Portfolio p : portfolios) {
      if (p.getName().equalsIgnoreCase(portfolioName)) {
        s.append("\n" + p.toString());
      }
    }
    if (s.length() == 0) {
      throw new IllegalStateException("Portfolio does not exist!");
    }
    return s.toString();
  }
}
