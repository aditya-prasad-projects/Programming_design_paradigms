package portfolio.stock.view;

import java.io.IOException;

import portfolio.stock.constants.Constants;

/**
 * The class extends PortfolioViewImpl and provides a way to taken in commission and new strategy
 * from the user.
 */
public class PortfolioViewPlusImpl extends PortfolioViewImpl implements PortfolioViewPlus {

  /**
   * Constructs an object by initializing on, out and sc. If the parameters are passed as null it
   * throws an IllegalArgumentException.
   *
   * @param in  inputstream object
   * @param out outputstream object
   */
  public PortfolioViewPlusImpl(Readable in, Appendable out) {
    super(in, out);
  }

  @Override
  public double getCommissionPrice() {
    try {
      out.append(Constants.ENTER_COMMISSION_PER_STOCK);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (sc.hasNext()) {
      return Double.parseDouble(sc.nextLine());
    } else {
      return -999;
    }
  }

  @Override
  public void getBuyMenu() {
    try {
      out.append(Constants.BUY_MENU);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putEqualWeightOption() {
    try {
      out.append(Constants.SPLIT_OPTIONS);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public double getWeight(String ticker) {
    try {
      out.append(Constants.ENTER_WEIGHT_FOR + ticker + ":");
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (sc.hasNext()) {
      return Double.parseDouble(sc.nextLine());
    } else {
      return -999;
    }
  }

  @Override
  public void putNumberPortfolios() {
    try {
      out.append(Constants.ENTER_THE_NUMBER_OF_STOCKS);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTickerName(int num) {
    try {
      out.append(Constants.ENTER_TICKER_NAME + num + ": ");
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (sc.hasNext()) {
      return sc.nextLine();
    } else {
      return "";
    }
  }

  @Override
  public String getStartDate() {
    try {
      out.append(Constants.ENTER_START_DATE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (sc.hasNext()) {
      return sc.nextLine();
    } else {
      return "";
    }

  }

  @Override
  public void putBuyFrequency() {
    try {
      out.append(Constants.ENTER_BUYING_FREQUENCY_FOR_STRATEGY_DAYS);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getEndDate() {
    try {
      out.append(Constants.ENTER_END_DATE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (sc.hasNext()) {
      return sc.nextLine();
    } else {
      return "";
    }

  }
}
