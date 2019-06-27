package portfolio.stock.controller;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import portfolio.stock.informationretrieval.AlphaVantageAPI;
import portfolio.stock.informationretrieval.FileRetrieval;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolios;
import portfolio.stock.view.PortfolioView;


/**
 * The class implements the IPortfolioController class. It interacts with the model and the view to
 * provide the necessary operations of the interactive stock portfolio program.
 */
public class PortfolioController implements IPortfolioController {
  private PortfolioView view;
  private UserPortfolios model;
  private StockData api;


  /**
   * Constructs an object by initializing the view object with System.in and System.out.
   */
  public PortfolioController(UserPortfolios model, PortfolioView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * The method provides an interface for the user to interact with the program and perform all the
   * operations the user desires. This is done with the controller interacting with the model and
   * the view. It provides 6 option for the user including create portfolio, buy stocks, get cost
   * base, get total value, get portfolio contents and exit. The portfolio name has to be unique.
   * Duplicates are not allowed.
   */
  public void goController() {
    try {
      int option = 0;
      boolean runProgram = true;
      inputType();
      while (runProgram) {
        view.getMenu();
        try {
          option = view.getOption();

        } catch (InputMismatchException e) {
          //Input is not integer.
        }
        switch (option) {
          case 1:
            createPortfolio();
            break;
          case 2:
            buyStocks();
            break;
          case 3:
            getStockBasis();
            break;
          case 4:
            getTotalValue();
            break;
          case 5:
            getPortfolioContents();
            break;
          case 6:
            runProgram = false;
            break;
          default:
            view.putInvalidInput();
        }
      }
    } catch (Exception e) {
      view.putInvalidInput();
    }

  }

  /**
   * Used to communicate with the model and the view for creating the portfolio.
   */
  private void createPortfolio() {
    String name = view.getPortfolioName();
    if (name.equals("")) {
      return;
    }
    try {
      model.createPortfolio(name);
      view.putSuccess();
    } catch (IllegalArgumentException e) {
      view.putErrorPortfolioExists();
    }
  }

  /**
   * Used to communicate with the model and the view for buying stocks.
   */
  private void buyStocks() {
    String portfolioName = view.getPortfolioName();
    if (portfolioName.equals("")) {
      return;
    }
    String tickerName = view.getTicker();
    if (tickerName.equals("")) {
      return;
    }
    double value;
    try {
      value = view.getBuyPrice();
      if (value == -999) {
        return;
      }
    } catch (InputMismatchException e) {
      view.putInvalidInput();
      return;
    }
    String date = view.getBuyDate();
    if (date.equals("")) {
      return;
    }
    try {
      model.buyStocks(portfolioName, tickerName, value, date, api);
      view.putSuccess();
    } catch (NoSuchElementException e) {
      view.putStockHoliday();
    } catch (DateTimeParseException f) {
      view.putInvalidDate();
    } catch (IllegalArgumentException g) {
      if (g.getMessage().equals("Market closed!")) {
        view.putMarketClosed();
      } else if (g.getMessage().equals("Portfolio does not exist!")) {
        view.portfolioNotExists();
      }
    }
  }

  /**
   * Used to communicate with the model and the view for getting the stock basis for the particular
   * date.
   */
  private void getStockBasis() {

    String date = view.getValueDate();
    if (date.equals("")) {
      return;
    }
    try {
      view.putMessage(model.getTotalCostBasis(date) + "\n");
    } catch (DateTimeParseException f) {
      view.putInvalidDate();
    } catch (NoSuchElementException e) {
      view.putStockPriceUnavailable();
    }
  }

  /**
   * Used to communicate with the model and the view for getting the total value for a particular
   * date.
   */
  private void getTotalValue() {
    String date = view.getValueDate();
    if (date.equals("")) {
      return;
    }
    try {
      view.putMessage(model.getTotalValue(date) + "\n");
    } catch (DateTimeParseException f) {
      view.putInvalidDate();
    } catch (NoSuchElementException e) {
      view.putStockPriceUnavailable();
    }
  }

  /**
   * Used to communicate with the model and the view for getting the portfolio contents.
   */
  private void getPortfolioContents() {
    String name = view.getPortfolioName();
    if (name.equals("")) {
      return;
    }
    try {
      view.putMessage(model.portfolioContents(name));
    } catch (IllegalStateException e) {
      view.portfolioNotExists();
    }
  }

  /**
   * Used to communicate with the model and the view for deciding how to take in the stock data.
   */
  private void inputType() {
    int optionAcceptInput;
    boolean firstInput = true;

    try {
      while (firstInput) {
        optionAcceptInput = view.getDataOption();
        switch (optionAcceptInput) {
          case 1:
            api = new AlphaVantageAPI();
            firstInput = false;
            break;
          case 2:
            api = new FileRetrieval(view.getSource());
            firstInput = false;
            break;
          default:
            view.putInvalidInput();
        }
      }

      String name = view.start();
      if (name.equals("")) {
        return;
      }
      model.createPortfolio(name);
      view.putSuccess();
    } catch (IllegalArgumentException e) {
      view.putErrorPortfolioExists();
    }
  }
}
