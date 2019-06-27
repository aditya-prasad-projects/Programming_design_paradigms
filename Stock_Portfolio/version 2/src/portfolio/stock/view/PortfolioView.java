package portfolio.stock.view;

/**
 * The interface represents the interface for the view for the MVC model. It has methods to read and
 * write data to and from the console. It is the layer that interacts with the user.
 */
public interface PortfolioView {

  /**
   * Prints the menu for the user on stdout.
   */
  void getMenu();

  /**
   * Takes the options as an integer from the user.
   *
   * @return the option entered by the user.
   */
  int getOption();

  /**
   * Prints the initial portfolio creation, and takes in the name of the portfolio to be created.
   *
   * @return the name of the portfolio to be created.
   */
  String start();

  /**
   * Asks the user to input the name of a portfolio and reads their input.
   *
   * @return name of the portfolio
   */
  String getPortfolioName();

  /**
   * Prints that the portfolio is already created.
   */
  void putErrorPortfolioExists();

  /**
   * Asks the user to input the buying price and reads the input.
   *
   * @return the buy price entered by the user
   */
  double getBuyPrice();

  /**
   * Asks the user to input the date the stock was bought and reads the input.
   *
   * @return date when the stock was bought as a string
   */
  String getBuyDate();

  /**
   * Asks the user the date for which the values must be found and reads the input.
   *
   * @return date for which the value must be found
   */
  String getValueDate();

  /**
   * Asks the user to enter the ticker and reads the input by the user.
   *
   * @return name of the ticker
   */
  String getTicker();

  /**
   * Prints a message stating that the portfolio does not exist.
   */
  void portfolioNotExists();

  /**
   * Prints the given message to the user.
   *
   * @param message the message that must be printed on stdout.
   */
  void putMessage(String message);

  /**
   * Prints a message that the market is closed on a particular day.
   */
  void putMarketClosed();

  /**
   * Prints a message that the date is invalid.
   */
  void putInvalidDate();

  /**
   * Prints that the stock cannot be bought on this particular day.
   */
  void putStockHoliday();

  /**
   * Prints that the stock price is unavailable.
   */
  void putStockPriceUnavailable();

  /**
   * Prints that the input was invalid.
   */
  void putInvalidInput();

  /**
   * Gets the source path if input is to be accepted as a file.
   */
  String getSource();

  /**
   * Queries what type of data object is used to get the data.
   */
  int getDataOption();

  /**
   * Prints success for a message.
   */
  void putSuccess();
}
