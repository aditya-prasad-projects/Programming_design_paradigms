package portfolio.stock.controller.inputcommands;

import java.util.Map;

import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlusTwo;

/**
 * Represents the method of getting the input from the user.
 */
public interface InputCommands {

  /**
   * It should be implemented by the classes that are getting the input from the user.
   *
   * @param model    represents the model of the application.
   * @param viewPlus represents the view of the application.
   * @param api      represents the stock database
   * @return false if we need to go back from the buy menu, or true in the end of the method.
   */
  boolean goInputController(UserPortfolioTwo model, PortfolioViewPlusTwo viewPlus, StockData api);

  /**
   * Used to ask the user if he would like to save the strategy, if yes then saves the strategy onto
   * a file provided by the user.
   *
   * @param viewPlusTwo the view of the program
   */
  void save(PortfolioViewPlusTwo viewPlusTwo);

  /**
   * Method used to get all the inputs provided by the user in the form of a map.
   * @return a map containing maps of different inputs provided by the user.
   */
  Map<Integer, Map> getInput();
}
