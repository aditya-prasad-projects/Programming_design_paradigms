package portfolio.stock.view;

import java.io.IOException;
import java.util.Scanner;

/**
 * The class implements the view interface and all the methods provided by it. It uses a readable
 * object to take inputs from the user. It uses an appendable object to print on stdout and a
 * scanner to parse the input given by the user.
 */
public class PortfolioViewImpl implements PortfolioView {

  private Appendable out;
  private Scanner sc;

  /**
   * Constructs an object by initializing on, out and sc. If the parameters are passed as null it
   * throws an IllegalArgumentException.
   *
   * @param in  inputstream object
   * @param out outputstream object
   */
  public PortfolioViewImpl(Readable in, Appendable out) {

    if (in == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters");
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
      out.append("Create a portfolio to start:\n\n");
      out.append("Enter name of new Portfolio:");
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
      out.append("Portfolio already exists! Cannot be added!\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTicker() {
    try {
      out.append("Enter name of ticker:");
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
      out.append("Portfolio does not exist! Try again\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putMarketClosed() {
    try {
      out.append("Market closed. Try a different time!\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putInvalidDate() {
    try {
      out.append("Enter a valid date!\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putStockHoliday() {
    try {
      out.append("stock cannot be bought on this day!\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putStockPriceUnavailable() {
    try {
      out.append("stock price not available for this date!\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putInvalidInput() {
    try {
      out.append("Please enter valid input\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void putSuccess() {
    try {
      out.append("Success!!!\n\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getBuyDate() {
    try {
      out.append("Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):");
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
      out.append("Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):");
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
      out.append("Enter price you want to buy(numeric):");
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
      out.append("Enter name of Portfolio:");
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
      out.append("1. Add Portfolio\n2. Buy stock\n3. Get stock Basis\n4. Get " +
              "total Value\n5. Get Portfolio Contents\n6. Exit\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getSource() {
    try {
      out.append("Enter the folder in which all the csv files are present.\n");

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
      out.append("1. If data is to be queried by the application.\n");
      out.append("2. If user wishes to give the data.\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return getOption();
  }
}
