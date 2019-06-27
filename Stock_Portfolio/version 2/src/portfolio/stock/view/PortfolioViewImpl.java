package portfolio.stock.view;

import java.io.IOException;
import java.util.Scanner;

import portfolio.stock.constants.Constants;

/**
 * The class implements the view interface and all the methods provided by it. It uses a readable
 * object to take inputs from the user. It uses an appendable object to print on stdout and a
 * scanner to parse the input given by the user.
 */
public class PortfolioViewImpl implements PortfolioView {

  protected Appendable out;
  protected Scanner sc;

  /**
   * Constructs an object by initializing on, out and sc. If the parameters are passed as null it
   * throws an IllegalArgumentException.
   *
   * @param in  inputstream object
   * @param out outputstream object
   */
  public PortfolioViewImpl(Readable in, Appendable out) {

    if (in == null || out == null) {
      throw new IllegalArgumentException(Constants.INVALID_PARAMETERS);
    }
    this.out = out;
    sc = new Scanner(in);
  }

  @Override
  public int getOption() {
    return Integer.parseInt(sc.nextLine());
  }

  @Override
  public String start() {
    try {
      out.append(Constants.CREATE_A_PORTFOLIO_TO_START);
      out.append(Constants.ENTER_NAME_OF_NEW_PORTFOLIO);
    } catch (IOException e) {
      //In case of an outputstream error.
    }
    if (sc.hasNext()) {
      return sc.nextLine();
    } else {
      return "";
    }

  }

  @Override
  public void putErrorPortfolioExists() {
    try {
      out.append(Constants.PORTFOLIO_ALREADY_EXISTS_CANNOT_BE_ADDED);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTicker() {
    try {
      out.append(Constants.ENTER_NAME_OF_TICKER);
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
  public void portfolioNotExists() {
    try {
      out.append(Constants.PORTFOLIO_DOES_NOT_EXIST_TRY_AGAIN);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putMarketClosed() {
    try {
      out.append(Constants.MARKET_CLOSED_TRY_A_DIFFERENT_TIME);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putInvalidDate() {
    try {
      out.append(Constants.ENTER_A_VALID_DATE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putStockHoliday() {
    try {
      out.append(Constants.STOCK_CANNOT_BE_BOUGHT_ON_THIS_DAY);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putStockPriceUnavailable() {
    try {
      out.append(Constants.PRICE_NOT_AVAILABLE_FOR_THIS_DATE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putInvalidInput() {
    try {
      out.append(Constants.ENTER_VALID_INPUT);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putSuccess() {
    try {
      out.append(Constants.SUCCESS);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getBuyDate() {
    try {
      out.append(Constants.ENTER_DATE_WHEN_STOCK_WAS_BOUGHT);
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
  public String getValueDate() {
    try {
      out.append(Constants.ENTER_DATE_TO_GET_VALUE);
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
  public void putMessage(String message) {
    try {
      out.append(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public double getBuyPrice() {
    try {
      out.append(Constants.ENTER_PRICE_YOU_WANT_TO_BUY);
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
  public String getPortfolioName() {
    try {
      out.append(Constants.ENTER_NAME_OF_PORTFOLIO);
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
  public void getMenu() {
    try {
      out.append(Constants.MAIN_MENU);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getSource() {
    try {
      out.append(Constants.ENTER_FOLDER_CSV_FILES_PRESENT);

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
  public int getDataOption() {
    try {
      out.append(Constants.DATA_IS_TO_BE_QUERIED_BY_THE_APPLICATION);
      out.append(Constants.IF_USER_WISHES_TO_GIVE_THE_DATA);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return getOption();
  }
}
