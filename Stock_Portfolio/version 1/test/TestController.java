import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import portfolio.stock.controller.IPortfolioController;
import portfolio.stock.controller.PortfolioController;
import portfolio.stock.model.UserPortfolios;
import portfolio.stock.model.UserPortfoliosImpl;
import portfolio.stock.view.PortfolioView;
import portfolio.stock.view.PortfolioViewImpl;

/**
 * Used to test the Model+Controller.
 */
public class TestController {
  private StringReader in;
  private StringBuffer out;
  private PortfolioView view;
  private UserPortfolios model;
  private IPortfolioController controller;

  /**
   * Used to set the appendable object and the model.
   */
  @Before
  public void setup() {
    out = new StringBuffer();
    model = new UserPortfoliosImpl();
  }

  /**
   * Tests if the controller can wait.
   */
  @Test
  public void testIfItWorks() {
    in = new StringReader("1");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Please enter valid input\n");
  }

  /**
   * Tests if it reads properly from the file.
   */
  @Test
  public void testController() {
    in = new StringReader("2\na\n/Users/adityaprasad/Downloads/csv files\n2\na\nGOOG" +
            "\n2000\n2018-11-12\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the " +
            "csv files are present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):1. " +
            "Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests if testController takes in input from AlphaVantage.
   */
  @Test
  public void testController2() {
    in = new StringReader("1\na\n2\na\nAAPL\n2000\n2018-07-02\n5\na\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):1. " +
            "Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:\n" +
            "PortfolioName = a\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "DateBought=2018-07-02 16:00:00\n" +
            "NumberOfShares=10.684902233144566\n" +
            "\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Test Creating portfolio with null value.
   */
  @Test
  public void testPortfolioVoid() {
    in = new StringReader("1\n\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Test if duplicate portfolios can be created.
   */
  @Test
  public void testDuplicatePortfolio() {
    in = new StringReader("1\na\n1\na\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio already exists! Cannot be added!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests creating portfolio names with spaces.
   */
  @Test
  public void testCreatingWithSpaces() {
    in = new StringReader("1\na b c\n5\na b c\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:\n" +
            "PortfolioName = a b c\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Create multiple portfolios and add stocks to it.
   */
  @Test
  public void testMultiplePortfolios() {
    in = new StringReader("2\na\n/Users/adityaprasad/Downloads/csv files\n1" +
            "\nb\n2\na\nAAPL\n2000\n2018-11-01\n2\nb\nGOOG\n2000\n2018-01-04\n5\nb\n2\na\n" +
            "AAPL\n1000\n2018-01-04\n5\na\n3\n2018-11-01\n4\n2018-11-13\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are" +
            " present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you " +
            "want to buy(numeric):Enter date when stock was bought(yyyy-mm-dd)or(" +
            "yyyy-mm-dd HH:MM:ss):1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want " +
            "to buy(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:\n" +
            "PortfolioName = b\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='GOOG'\n" +
            "Price bought=2000.0\n" +
            "DateBought=2018-01-04 16:00:00\n" +
            "NumberOfShares=11.558689244639657\n" +
            "\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric)" +
            ":Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):1. " +
            "Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:\n" +
            "PortfolioName = a\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "DateBought=2018-11-01 16:00:00\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "\n" +
            "stock 2:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-01-04 16:00:00\n" +
            "NumberOfShares=5.779344622319829\n" +
            "\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):5000.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):5062.977551118631\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests buyStock with null values.
   */
  @Test
  public void testBuyStock() {
    in = new StringReader("2\na\n/Users/adityaprasad/Downloads/csv files\n2\na\n"
            + null + "2000\n2018-11-01\n5\na\n2\na\nAAPL\n-1\n2018-11-12\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files " +
            "are present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Please enter valid input\n" +
            "Enter name of portfolio:\nPortfolioName = a\n1. Add Portfolio\n2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\nEnter name of Portfolio:Enter name of ticker:Enter price you want to " +
            "buy(numeric):Please enter valid input\n");
  }

  /**
   * Tests invalid date.
   */
  @Test
  public void testInvalidDate() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n2\na\nAAPL\n2000\n" +
            "2018-11-adscasc\n2\na\nAAPL\n2000\n2018-acasd-04\n2\na\nAAPL\n2000\ns" +
            "dvcadca-08-04\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are " +
            "present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):" +
            "Enter a valid date!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):Enter a " +
            "valid date!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):Enter a " +
            "valid date!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests for Sunday.
   */
  @Test
  public void testHoliday() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n2\na\nAAPL\n2000\n" +
            "2018-11-11\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are " +
            "present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss)" +
            ":stock cannot be bought on this day!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests for saturday.
   */
  @Test
  public void testSaturday() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n2\na\nAAPL\n" +
            "2000\n2018-11-10\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are " +
            "present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):" +
            "stock cannot be bought on this day!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");

  }

  /**
   * Tests for holiday.
   */
  @Test
  public void testHoliday1() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n2\na\nAAPL\n2000\n" +
            "2017-01-01\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are " +
            "present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):" +
            "stock cannot be bought on this day!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests with time.
   */
  @Test
  public void testWithTime() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n2\na\nAAPL\n2000\n" +
            "2018-11-13 16:00:00\n2\na\nAAPL\n2000\n" +
            "2018-11-13 15:59:59");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv " +
            "files are present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):stock cannot " +
            "be bought on this day!\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Please enter valid input");
  }

  @Test
  public void testContent() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n5\na\n6");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are present." +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:\n" +
            "PortfolioName = a\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests cost value before buying stock.
   */
  @Test
  public void testCost() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n4\n2018-11-13\n");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are " +
            "present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):0.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");

  }

  /**
   * Tests total cost basis.
   */
  @Test
  public void testCostBasis() {
    in = new StringReader("2\na\nUsers/adityaprasad/Downloads/csv files\n4\n2018-11-13\n");
    view = new PortfolioViewImpl(in, out);
    controller = new PortfolioController(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Enter the folder in which all the csv files are " +
            "present.\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):0.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Please enter valid input");
  }
}






