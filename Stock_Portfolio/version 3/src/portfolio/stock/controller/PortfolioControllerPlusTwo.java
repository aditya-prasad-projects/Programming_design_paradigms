package portfolio.stock.controller;

import org.jfree.data.category.DefaultCategoryDataset;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;

import portfolio.stock.constants.Constants;
import portfolio.stock.controller.inputcommands.BasicBuyInputs;
import portfolio.stock.controller.inputcommands.DollarCostAveragingInput;
import portfolio.stock.controller.inputcommands.InputCommands;
import portfolio.stock.controller.inputcommands.WeightedBuyInput;
import portfolio.stock.model.filesavingandretrieval.FileParsing;
import portfolio.stock.model.filesavingandretrieval.PortfolioFile;
import portfolio.stock.model.Portfolio;
import portfolio.stock.model.Stock;
import portfolio.stock.model.UserPortfolioThree;

import portfolio.stock.view.PortfolioViewPlusTwo;

/**
 * The class extends PortfolioControllerPlus to implement a GUI as well as support persistence of
 * data. A strategy can be saved only after a strategy is executed successfully. Buy Stock menu
 * provides a additional option of retrieving the strategy from a file.
 */
public class PortfolioControllerPlusTwo extends PortfolioControllerPlus {
  private PortfolioViewPlusTwo viewPlusTwo;
  private UserPortfolioThree modelPlus;
  //private Map<Integer, Map> input;

  /**
   * Constructs an object by initializing the view object with System.in and System.out.
   */
  public PortfolioControllerPlusTwo(UserPortfolioThree model, PortfolioViewPlusTwo view) {
    super(model, view);
    this.viewPlusTwo = view;
    this.modelPlus = model;

    configureButtonListener();
  }

  /**
   * Initializes a map containing all the button listeners for the GUI.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap();
    ButtonListener buttonListener = new ButtonListener();
    buttonClickedMap.put(Constants.CLEAR, () -> this.viewPlusTwo.clearOutput());
    buttonClickedMap.put(Constants.BUY_STOCK, () -> this.viewPlusTwo.getBuyMenu());
    buttonClickedMap.put(Constants.BACK, () -> this.viewPlusTwo.getMenu());
    buttonClickedMap.put(Constants.EXIT, () -> System.exit(0));
    buttonClickedMap.put(Constants.GET_COST_BASIS, () -> getStockBasis());
    buttonClickedMap.put(Constants.GET_STOCK_VALUE, () -> getTotalValue());
    buttonClickedMap.put(Constants.GET_PORTFOLIO_CONTENTS, () -> getPortfolioContents());
    buttonClickedMap.put(Constants.BASIC_BUY, () -> buyBasic());
    buttonClickedMap.put(Constants.WEIGHTED_BUY, () -> buyWeightedBuy());
    buttonClickedMap.put(Constants.DOLLAR_COST_AVERAGING, () -> buyDollarCostAveraging());
    buttonClickedMap.put(Constants.CREATE_PORTFOLIO, () -> this.viewPlusTwo.displayPortfolioMenu());
    buttonClickedMap.put(Constants.CREATE_NEW_PORTFOLIO, () -> {
      try {
        createPortfolioListener();
      } catch (NullPointerException e) {
        //Continue to run the code.
      }
    });
    buttonClickedMap.put(Constants.GENERATE_GRAPH, () -> {
      try {
        generateGraphListner();
      } catch (IllegalArgumentException e) {
        //Continue to run the code.
      } catch (IllegalStateException f) {
        viewPlusTwo.portfolioNotExists();
      } catch (NullPointerException e) {
        //Continue to run the code.
      }
    });
    buttonClickedMap.put(Constants.RETRIEVE_STRATEGY, () -> {
      try {
        retrieveFile();
      } catch (IllegalStateException e) {
        //Continue to run the code.
      } catch (NullPointerException f) {
        //Continue to run the code.
      }
    });
    buttonClickedMap.put(Constants.SAVE_PORTFOLIO, () -> {
      try {
        savePortfolioListener();
      } catch (IllegalStateException e) {
        //Continue to run the code.
      } catch (NullPointerException e) {
        //Continue to run the code.
      }

    });
    buttonClickedMap.put(Constants.RETRIEVE_PORTFOLIO, () -> {
      try {
        retrievePortfolioListener();
      } catch (NullPointerException e) {
        //Continue to run the code.
      }
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.viewPlusTwo.addActionListener(buttonListener);
  }

  /**
   * Helper method to implement listener for retrieving portfolio.
   */
  protected void retrievePortfolioListener() {
    String portfolioName = viewPlusTwo.getPortfolioName();
    if (portfolioName.equals("")) {
      return;
    }
    FileParsing parseRead = new PortfolioFile(portfolioName, modelPlus);
    while (true) {
      try {
        String fileName = viewPlusTwo.fileToWrite();
        boolean a = parseRead.readFromFile(fileName, false);
        if (!a) {
          String overWrite = viewPlusTwo.overWrite();
          if (overWrite.matches("[Yy][eE][sS]")) {
            parseRead.readFromFile(fileName, true);
          }
        }
        break;
      } catch (NoSuchElementException e) {
        viewPlusTwo.displayFileMissing();
      }
    }
    viewPlusTwo.putSuccess();
  }

  /**
   * Helper to implement listener for creating portfolio.
   */
  protected void createPortfolioListener() {
    String name = viewPlusTwo.getPortfolioName();
    if (name.equals("")) {
      return;
    }
    try {
      model.createPortfolio(name);
      viewPlusTwo.putSuccess();
    } catch (IllegalArgumentException e) {
      viewPlusTwo.putErrorPortfolioExists();
    }
  }

  /**
   * Helper to implement listener for saving a portfolio.
   */
  protected void savePortfolioListener() {
    String pname = viewPlusTwo.getPortfolioName();

    if (pname.equals("")) {
      return;
    }
    Portfolio p;
    try {
      p = modelPlus.portfolioContents(pname);

    }
    catch (IllegalArgumentException e) {
      viewPlusTwo.putInvalidInput();
      return;
    }
    FileParsing parse = new PortfolioFile(p);

    boolean fileAccept = true;
    while (fileAccept) {
      String fileName = viewPlusTwo.fileToWrite();
      try {
        boolean b = parse.writeToFile(fileName, false);
        if (!b) {
          String overWrite = viewPlusTwo.overWrite();
          if (overWrite.matches("[Yy][eE][sS]")) {
            parse.writeToFile(fileName, true);
          }
        }
        fileAccept = false;
      } catch (NoSuchElementException e) {
        viewPlusTwo.displayFileMissing();
      }
    }
    viewPlusTwo.putSuccess();
  }

  /**
   * Helper to implement listener for generating a graph.
   */
  protected void generateGraphListner() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    Portfolio p = modelPlus.portfolioContents(viewPlusTwo.getPortfolioName());
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern(Constants.DATE_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
    LocalDateTime startDate;
    LocalDateTime endDate;
    String date;
    while (true) {
      date = viewPlusTwo.getStartDate();
      try {
        startDate = LocalDateTime.parse(date, formatter);
        break;
      } catch (DateTimeParseException f) {
        viewPlusTwo.putInvalidInput();
      }
    }
    while (true) {
      date = viewPlusTwo.getEndDate();
      if (date.equals("N") || date.equals("n")) {
        endDate = LocalDateTime.now();
        break;
      }
      try {
        endDate = LocalDateTime.parse(date, formatter);
        if (endDate.isBefore(startDate) || endDate.isAfter(LocalDateTime.now())) {
          throw new DateTimeParseException("", "", 1);
        }
        break;
      } catch (DateTimeParseException f) {
        viewPlusTwo.putInvalidInput();
      }
    }
    double total;
    while (startDate.isBefore(endDate)) {
      total = 0;
      try {
        for (Stock s : p.getStocks()) {
          if (s.getDateBought().isBefore(startDate)) {
            total = total + (api.getPrice(s.getTicker(), startDate) * s.getNumberOfShares());
          }
        }
        dataset.addValue(total, "Stocks Value", startDate.getMonth().toString().substring(0, 3));
      } catch (Exception e) {
        startDate = startDate.plusDays(1);
        continue;
      }
      startDate = startDate.plusDays(1);
    }
    viewPlusTwo.plotChart(dataset);
  }


  /**
   * Implements the new menu for creating a portfolio with save and retrieve options.
   */
  protected void createPortfolio() {
    int option;
    boolean firstInput = true;
    try {
      while (firstInput) {
        viewPlusTwo.displayPortfolioMenu();
        try {
          option = viewPlusTwo.getOption();
        } catch (NumberFormatException e) {
          viewPlusTwo.putInvalidInput();
          continue;
        }
        switch (option) {
          case 1:
            try {
              boolean createPortfolioError = createNormalPortfolio();
              if (!createPortfolioError) {
                return;
              }
              firstInput = false;
            } catch (NullPointerException e) {
              //Continue to run the code.
            }
            break;
          case 2:
            boolean savePortfolioError = savePortfolio();
            if (!savePortfolioError) {
              viewPlusTwo.putInvalidInput();
              return;
            }
            break;
          case 3:
            boolean retrieveFileError = retrievePortfolio();
            if (!retrieveFileError) {
              viewPlusTwo.putInvalidInput();
              return;
            }
            break;
          case 4:
            return;
          default:
            viewPlusTwo.putInvalidInput();
        }
      }
    } catch (Exception e) {
      viewPlusTwo.putInvalidInput();
    }
  }

  /**
   * A helper method to retrieve a portfolio form the user.
   *
   * @return false if error occurs, true otherwise.
   */
  protected boolean retrievePortfolio() {
    String portfolioName = viewPlusTwo.getPortfolioName();
    if (portfolioName.equals("")) {
      return false;
    }
    FileParsing parseRead = new PortfolioFile(portfolioName, modelPlus);
    while (true) {
      try {
        String fileName = viewPlusTwo.fileToWrite();
        boolean a = parseRead.readFromFile(fileName, false);
        if (!a) {
          String overWrite = viewPlusTwo.overWrite();
          if (overWrite.matches("[Yy][eE][sS]")) {
            parseRead.readFromFile(fileName, true);
          }
        }
        break;
      } catch (NoSuchElementException e) {
        viewPlusTwo.displayFileMissing();
      }
    }
    viewPlusTwo.putSuccess();
    return true;
  }

  /**
   * A helper method to save a portfolio.
   *
   * @return false if error occurs, true otherwise.
   */
  protected boolean savePortfolio() {
    Portfolio p;
    try {
      String pname = viewPlusTwo.getPortfolioName();
      if (pname.equals("")) {
        return false;
      }
      p = modelPlus.portfolioContents(pname);
    }
    catch (IllegalArgumentException e) {
      viewPlusTwo.putInvalidInput();
      return false;
    }
    FileParsing parse = new PortfolioFile(p);
    boolean fileAccept = true;
    while (fileAccept) {
      String fileName = viewPlusTwo.fileToWrite();
      try {
        boolean b = parse.writeToFile(fileName, false);
        if (!b) {
          String overWrite = viewPlusTwo.overWrite();
          if (overWrite.matches("[Yy][eE][sS]")) {
            parse.writeToFile(fileName, true);
          }
        }
        fileAccept = false;
      } catch (NoSuchElementException e) {
        viewPlusTwo.displayFileMissing();
      }
    }
    viewPlusTwo.putSuccess();
    return true;
  }

  /**
   * A helper method to create a portfolio.
   *
   * @return false if error occurs, true otherwise.
   */
  protected boolean createNormalPortfolio() {
    String name = viewPlusTwo.getPortfolioName();
    if (name.equals("")) {
      return false;
    }
    try {
      model.createPortfolio(name);
      viewPlusTwo.putSuccess();
    } catch (IllegalArgumentException e) {
      viewPlusTwo.putErrorPortfolioExists();
    }
    return true;
  }

  /**
   * Creates a new menu for buying stocks in incorporate persistence of different strategies.
   */
  @Override
  protected void buyStocks() {
    try {
      int option = 0;
      viewPlusTwo.getBuyMenu();
      try {
        option = view.getOption();

      } catch (InputMismatchException e) {
        //Input is not integer.
      }
      switch (option) {
        case 1:
          buyBasic();
          break;
        case 2:
          buyWeightedBuy();
          break;
        case 3:
          buyDollarCostAveraging();
          break;
        case 4:
          retrieveFile();
          break;
        case 5:
          return;
        default:
          view.putInvalidInput();
          return;
      }

    } catch (Exception e) {
      view.putInvalidInput();
    }
  }

  /**
   * Implements the basic buy of a single stock by calling the model.
   */
  protected void buyBasic() {
    try {
      InputCommands buy = new BasicBuyInputs();

      Boolean b = buy.goInputController(modelPlus, viewPlusTwo, api);
      if (!b) {
        return;
      }
      modelPlus.buyStrategy(buy.getInput(), api, 1);
      viewPlusTwo.putSuccess();
      buy.save(viewPlusTwo);
      viewPlusTwo.putSuccess();
    } catch (NoSuchElementException e) {
      viewPlusTwo.putStockHoliday();
    } catch (DateTimeParseException f) {
      viewPlusTwo.putInvalidDate();
    } catch (IllegalArgumentException g) {
      if (g.getMessage().equals(Constants.MARKET_CLOSED)) {
        viewPlusTwo.putMarketClosed();
      } else if (g.getMessage().equals(Constants.PORTFOLIO_DOES_NOT_EXIST)) {
        viewPlusTwo.portfolioNotExists();
      } else {
        viewPlusTwo.putInvalidInput();
      }
    } catch (NullPointerException e) {
      //Continue to run the code.
    }

  }

  /**
   * Implements the weighted buy by calling the model.
   */
  protected void buyWeightedBuy() {
    try {
      InputCommands buy = new WeightedBuyInput();
      boolean b = buy.goInputController(modelPlus, viewPlusTwo, api);
      if (!b) {
        return;
      }
      modelPlus.buyStrategy(buy.getInput(), api, 2);
      viewPlusTwo.putSuccess();
      buy.save(viewPlusTwo);
      viewPlusTwo.putSuccess();
    } catch (NoSuchElementException e) {
      viewPlusTwo.putStockHoliday();
    } catch (IllegalArgumentException g) {
      if (g.getMessage().equals(Constants.MARKET_CLOSED)) {
        viewPlusTwo.putMarketClosed();
      } else if (g.getMessage().equals(Constants.PORTFOLIO_DOES_NOT_EXIST)) {
        viewPlusTwo.portfolioNotExists();
      } else {
        viewPlusTwo.putInvalidInput();
      }
    }
  }

  /**
   * Implements dollar cost averaging by calling the model.
   */
  protected void buyDollarCostAveraging() {
    InputCommands buy = new DollarCostAveragingInput();
    boolean b = buy.goInputController(modelPlus, viewPlusTwo, api);
    if (!b) {
      return;
    }
    modelPlus.buyStrategy(buy.getInput(), api, 3);

    viewPlusTwo.putSuccess();
    buy.save(viewPlusTwo);
    viewPlusTwo.putSuccess();
  }


  /**
   * Gets the filename from the user.
   *
   * @return string with the name of the file.
   */
  protected String getFileName() {
    String fileName;
    fileName = viewPlusTwo.fileToWrite();
    if (fileName.equals("q")) {
      return null;
    }
    return fileName;
  }

  /**
   * Gets the name of the portfolio from the user.
   *
   * @return string with the name of the portfolio.
   */
  protected String getPortfolioName() {
    String portfolioName;
    portfolioName = viewPlusTwo.getPortfolioName();
    if (portfolioName.equals("q")) {
      return null;
    }
    try {
      modelPlus.createPortfolio(portfolioName);
    } catch (IllegalArgumentException e) {
      //portfolio already exists.
    }
    return portfolioName;
  }

  /**
   * Gets the name of the strategy from the user.
   *
   * @param fileName file where the strategy must be looked
   * @return strategy name
   */

  protected String getEntryName(String fileName) {
    String entryName;
    entryName = viewPlusTwo.getStrategyName();
    if (entryName.equals("q")) {
      return null;
    }
    return entryName;
  }

  /**
   * Retrieves the strategy and applies it to a portfolio.
   */
  protected void retrieveFile() {
    String fileName = getFileName();
    if (fileName == null) {
      return;
    }
    String portfolioName = getPortfolioName();
    if (portfolioName == null) {
      return;
    }
    String entryName = getEntryName(fileName);
    if (entryName == null) {
      return;
    }
    while (true) {
      try {
        modelPlus.retrieveStrategy(fileName, portfolioName, entryName, api);
        viewPlusTwo.putSuccess();
        break;
      } catch (FileNotFoundException e) {
        viewPlusTwo.putInvalidInput();
        fileName = getFileName();
        if (fileName == null) {
          break;
        }
      } catch (IllegalArgumentException e) {
        viewPlusTwo.putInvalidInput();
        entryName = getEntryName(fileName);
        if (entryName == null) {
          break;
        }
      }
    }
  }


}
