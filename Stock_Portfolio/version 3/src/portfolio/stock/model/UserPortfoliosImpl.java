package portfolio.stock.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import portfolio.stock.informationretrieval.StockData;

/**
 * Represents the implementation of the model of the stock MVC application. Maintains a list of
 * portfolios. All the dates used are in 24hour format. The portfolio name is not case sensitive.
 */
public class UserPortfoliosImpl implements UserPortfolios {

  List<Portfolio> portfolios;

  /**
   * Used to create the model object.
   */
  public UserPortfoliosImpl() {
    portfolios = new ArrayList<>();
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
  public void buyStocks(String pName, String ticker,
                        double price, LocalDateTime date, StockData data) {

    if (pName == null || pName.equals("") || ticker == null || ticker.equals("") || data == null
            || data.equals("") || date == null || date.equals("")) {
      throw new IllegalArgumentException("Inputs cant be null");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price cant be negative!");
    }
    for (Portfolio p : portfolios) {
      if (p.getName().equalsIgnoreCase(pName)) {
        p.addStocks(ticker, price, date, data);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist!");
  }

  @Override
  public double getTotalValue(LocalDateTime date) {
    if (date == null || date.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    double ret = 0;
    for (Portfolio p : portfolios) {
      for (Stock stock : p.getStocks()) {
        if (stock.getDateBought().isBefore(date) || stock.getDateBought().equals(date)) {
          ret = ret + stock.getPriceFromDate(date) * stock.getNumberOfShares();
        }
      }
    }
    return ret;
  }

  @Override
  public double getTotalCostBasis(LocalDateTime date) {
    if (date == null || date.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    double ret = 0;
    for (Portfolio p : portfolios) {
      for (Stock stock : p.getStocks()) {
        if (stock.getDateBought().isBefore(date) || stock.getDateBought().equals(date)) {
          ret = ret + stock.getPrice();
        }
      }
    }
    return ret;
  }

  @Override
  public Portfolio portfolioContents(String portfolioName) {
    if (portfolioName == null || portfolioName.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    StringBuffer s = new StringBuffer();
    for (Portfolio p : portfolios) {
      if (p.getName().equalsIgnoreCase(portfolioName)) {
        return new PortfolioImpl(p) {
        };
      }
    }
    throw new IllegalStateException("Portfolio does not exist!");
  }

}
