package portfolio.stock.model;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Map;

import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.filesavingandretrieval.FileParsing;
import portfolio.stock.model.filesavingandretrieval.FileValidations;
import portfolio.stock.model.investmentstrategy.InvestmentStrategy;

/**
 * Used to implement persistence for portfolio and strategies.
 */
public class UserPortfolioThreeImpl extends UserPortfolioTwoImpl implements UserPortfolioThree {

  @Override
  public void buyStocksFromFile(String pName, String ticker, double price, double commission,
                                LocalDateTime date, double shares) {
    if (pName == null || pName.equals("") || ticker == null || ticker.equals("")) {
      throw new IllegalArgumentException("Inputs cant be null");
    }
    if (shares < 0) {
      throw new IllegalArgumentException("Shares cannot be negative");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price cant be negative!");
    }
    if (commission < 0) {
      throw new IllegalArgumentException("Commission cannot be negative");
    }
    for (Portfolio p : portfolios) {
      if (p.getName().equalsIgnoreCase(pName)) {
        p.addStocksFromFile(ticker, price, commission, date, shares);
        return;
      }
    }

  }

  @Override
  public void buyStrategy(Map<Integer, Map> input, StockData api, int strategy) {
    InvestmentStrategy obj = InvestmentStrategyFactory.getStrategyFactory(strategy);
    obj.goController(input, this,api);
  }

  @Override
  public void retrieveStrategy(String fileName, String portfolioName, String entryName,
                               StockData api) throws FileNotFoundException {
    FileValidations validations = new FileValidations();
    if (!validations.checkFileExists(fileName)) {
      throw new FileNotFoundException();
    }
    String[] input = validations.checkUniqueEntryExists(fileName,entryName);
    if (input == null) {
      throw new IllegalArgumentException("Entry does not exist");
    }
    FileParsing retrieve = FileSavingFactory
            .fileSavingFactory(portfolioName, this, input);
    InvestmentStrategy obj = retrieve.getStrategy();
    obj.goControllerFile(this, api);

  }
}
