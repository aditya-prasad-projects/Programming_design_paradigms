package portfolio.stock.controller;

import java.util.InputMismatchException;


import portfolio.stock.controller.investmentstrategy.BasicBuy;
import portfolio.stock.controller.investmentstrategy.DollarCostAveraging;
import portfolio.stock.controller.investmentstrategy.InvestmentStrategy;
import portfolio.stock.controller.investmentstrategy.WeightedBuy;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.view.PortfolioViewPlus;

/**
 * The class extends PortfolioController and provides new buy menu with the new strategy options.
 */
public class PortfolioControllerPlus extends PortfolioController {
  private PortfolioViewPlus viewPlus;
  private UserPortfolioTwo modelPlus;

  /**
   * Constructs an object by initializing the view object with System.in and System.out.
   */
  public PortfolioControllerPlus(UserPortfolioTwo model, PortfolioViewPlus view) {
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
      InvestmentStrategy buy;
      switch (option) {
        case 1:
          buy = new BasicBuy();
          break;
        case 2:
          buy = new WeightedBuy();
          break;
        case 3:
          buy = new DollarCostAveraging();
          break;
        case 4:
          return;
        default:
          view.putInvalidInput();
          return;
      }
      buy.goController(modelPlus, viewPlus, api);
    } catch (Exception e) {
      view.putInvalidInput();
    }
  }
}
