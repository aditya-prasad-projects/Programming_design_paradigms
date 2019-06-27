package portfolio.stock.controller.inputcommands;

import java.util.Map;

import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * Represents the method of getting the input from the user.
 */
public interface InputCommands {

  /**
   * It should be implemented by the classes that are getting the input from the user.
   * @param model represents the model of the application.
   * @param viewPlus represents the view of the application.
   * @param api represents the stock database
   * @param inputMaps represents the values that need to be taken from the user.
   * @return false if we need to go back from the buy menu, or true in the end of the method.
   */
  boolean goInputController(UserPortfolioTwo model, PortfolioViewPlus viewPlus, StockData api,
                            Map... inputMaps);
}
