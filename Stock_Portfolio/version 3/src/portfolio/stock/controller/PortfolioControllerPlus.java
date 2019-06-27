package portfolio.stock.controller;

import java.util.InputMismatchException;


import portfolio.stock.controller.inputcommands.BasicBuyInputs;
import portfolio.stock.controller.inputcommands.DollarCostAveragingInput;
import portfolio.stock.controller.inputcommands.InputCommands;
import portfolio.stock.controller.inputcommands.WeightedBuyInput;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlusTwo;

/**
 * The class extends PortfolioController and provides new buy menu with the new strategy options.
 */
public class PortfolioControllerPlus extends PortfolioController {
  private PortfolioViewPlusTwo viewPlus;
  private UserPortfolioTwo modelPlus;

  /**
   * Constructs an object by initializing the view object with System.in and System.out.
   */
  public PortfolioControllerPlus(UserPortfolioTwo model, PortfolioViewPlusTwo view) {
    super(model, view);
    this.viewPlus = view;
    this.modelPlus = model;
  }

  /**
   * Provides a updated buy menu incorporating different investment strategies.
   */
  @Override
  protected void buyStocks() {
    try {
      int option = 0;

      viewPlus.getBuyMenu();
      try {
        option = view.getOption();

      } catch (InputMismatchException e) {
        //Input is not integer.
      }
      InputCommands buy = new BasicBuyInputs();
      switch (option) {
        case 1:
          buy = new BasicBuyInputs();
          buy.goInputController(modelPlus, viewPlus,api);
          break;
        case 2:
          buy = new WeightedBuyInput();
          buy.goInputController(modelPlus, viewPlus,api);
          break;
        case 3:
          buy = new DollarCostAveragingInput();
          buy.goInputController(modelPlus, viewPlus,api);
          break;
        case 4:
          return;
        default:
          view.putInvalidInput();
          return;
      }

    } catch (Exception e) {
      view.putInvalidInput();
    }
  }
}
