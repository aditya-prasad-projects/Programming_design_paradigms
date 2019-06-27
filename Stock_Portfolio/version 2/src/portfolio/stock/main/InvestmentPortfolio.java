package portfolio.stock.main;

import portfolio.stock.controller.IPortfolioController;
import portfolio.stock.controller.PortfolioControllerPlus;
import portfolio.stock.model.UserPortfolioTwo;
import java.io.InputStreamReader;
import portfolio.stock.model.UserPortfolioTwoImpl;
import portfolio.stock.view.PortfolioViewPlus;
import portfolio.stock.view.PortfolioViewPlusImpl;

/**
 * The main method that initializes the model, view and the controller. It calls the controller to
 * start the interactive program.
 */
public class InvestmentPortfolio {
  /**
   * Represents the start of the program.
   */
  public static void main(String[] args) {
    UserPortfolioTwo model = new UserPortfolioTwoImpl();
    PortfolioViewPlus view = new PortfolioViewPlusImpl(new InputStreamReader(System.in),
            System.out);
    IPortfolioController controller = new PortfolioControllerPlus(model, view);
    controller.goController();
  }
}
