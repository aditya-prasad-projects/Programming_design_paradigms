package portfolio.stock.model;

import java.time.LocalDateTime;

import portfolio.stock.informationretrieval.StockData;

/**
 * The class extends UserPortfolioImpl and provides a way to buy with commission and to show the
 * total cost basis value with commission.
 */
public class UserPortfolioTwoImpl extends UserPortfoliosImpl implements UserPortfolioTwo {

  @Override
  public void buyStocksWithCommission(String pName, String ticker, double price,
                                      double commission, LocalDateTime date, StockData data) {

    if (pName == null || pName.equals("") || ticker == null || ticker.equals("") || data == null
            || data.equals("") || date == null || date.equals("")) {
      throw new IllegalArgumentException("Inputs cant be null");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price cant be negative!");
    }
    if (commission < 0) {
      throw new IllegalArgumentException("Commission cannot be negative");
    }
    for (Portfolio p : portfolios) {
      if (p.getName().equalsIgnoreCase(pName)) {
        p.addStocksWithCommission(ticker, price, commission, date, data);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist!");
  }

  /**
   * overridden the method written in UserportfoliosImpl to handle commission.
   *
   * @param date The date at which the total cost basis value of the stock is to be calculated.
   * @return the total cost basis including the commission.
   */
  @Override
  public double getTotalCostBasis(LocalDateTime date) {
    if (date == null || date.equals("")) {
      throw new IllegalArgumentException("Null can't be passed!");
    }
    double ret = 0;
    for (Portfolio p : portfolios) {
      for (Stock stock : p.getStocks()) {
        if (stock.getDateBought().isBefore(date) || stock.getDateBought().equals(date)) {
          ret = ret + stock.getPrice() + stock.getCommission();
        }
      }
    }
    return ret;
  }

}
