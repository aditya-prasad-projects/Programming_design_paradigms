package portfolio.stock.main;

import portfolio.stock.controller.IPortfolioController;
import portfolio.stock.model.UserPortfolios;

import java.io.InputStreamReader;

import portfolio.stock.controller.PortfolioController;
import portfolio.stock.model.UserPortfoliosImpl;
import portfolio.stock.view.PortfolioView;
import portfolio.stock.view.PortfolioViewImpl;

/**
 * The main method that initializes the model, view and the controller. It calls the controller to
 * start the interactive program.
 */
public class InvestmentPortfolio {
  /**
   * Represents the start of the program.
   */
  public static void main(String[] args) {
    UserPortfolios model = new UserPortfoliosImpl();
    PortfolioView view = new PortfolioViewImpl(new InputStreamReader(System.in), System.out);
    IPortfolioController controller = new PortfolioController(model, view);
    controller.goController();
  }
}
