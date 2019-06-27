package portfolio.stock.model.filesavingandretrieval;

import portfolio.stock.model.investmentstrategy.InvestmentStrategy;

/**
 * Used for Reading and writing from a file. Also to generate a
 * investmentStrategy object from the contents retrieved from the file.
 */
public interface FileParsing {

  /**
   * Used for writing data to the file. The data is to be passed in the constructor.
   * @param fileName the file path
   * @param overWrite true if the entry is to be overridden in the file.
   * @return true if executed successfully, false otherwise.
   */
  boolean writeToFile(String fileName, boolean overWrite);

  /**
   * Used for reading data from the file and store it.
   * @param fileName the file path.
   * @param entryExists if the data retrieved already exists in the application,
   *                    whether it should be appended to the data or not
   * @return true if operation was successful, false otherwise.
   */
  boolean readFromFile(String fileName, boolean entryExists);

  /**
   * Used to create the object of the stored data.
   * @return the object of the stored data.
   */
  InvestmentStrategy getStrategy();



}
