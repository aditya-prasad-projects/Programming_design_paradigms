import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import portfolio.stock.controller.IPortfolioController;
import portfolio.stock.controller.PortfolioControllerPlus;
import portfolio.stock.model.UserPortfolioThree;
import portfolio.stock.model.UserPortfolioThreeImpl;
import portfolio.stock.view.PortfolioViewPlusTwo;
import portfolio.stock.view.PortfolioViewPlusTwoImpl;


/**
 * Tests new Cotroller for persistance.
 */

public class PortfolioControllerPersistenceTest {

  private StringReader in;
  private StringBuffer out;
  private PortfolioViewPlusTwo view;
  private UserPortfolioThree model;
  private IPortfolioController controller;

  /**
   * Used to set the appendable object and the model.
   */
  @Before
  public void setup() {
    out = new StringBuffer();
    model = new UserPortfolioThreeImpl();
  }

  /**
   * Test saving portfolio.
   */
  @Test
  public void testSaveAndRetrieve() throws IOException {
    in = new StringReader("1\na\n2\n1\na\nAAPL\n2000\n20\n2018-11-01\nNo\n1\n2\na\na.csv\n3" +
            "\nb\na.csv\n4\n5\nb\n");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertTrue(out.toString().contains("Success"));

    File file = new File("a.csv");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    String result = "";
    while ((st = br.readLine()) != null) {
      result = result + st;
    }
    assertEquals("\na,AAPL,2000.0,20.0,2018-11-01T15:59:59,9.000090000900009", result);

    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "1\n" +
            "1\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:a\n" +
            "Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "1\n" +
            "Enter name of Portfolio:a\n" +
            "Enter name of ticker:AAPL\n" +
            "Enter price you want to buy(numeric): $2000\n" +
            "Enter commission per stock(numeric): $20\n" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2018-11-01\n" +
            "Success!!!\n" +
            "\n" +
            "Press Yes if you want to save the strategy, No otherwise: No\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1\n" +
            "1)Create Portfolio\n" +
            "2)Save Portfolio\n" +
            "3)Retrieve Portfolio\n" +
            "4)Back\n" +
            "2\n" +
            "Enter name of Portfolio:a\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "a.csv\n" +
            "Portfolio already exists, Type Yes to override, No otherwise: \n" +
            " yes\n" +
            "Success!!!\n" +
            "\n" +
            "1)Create Portfolio\n" +
            "2)Save Portfolio\n" +
            "3)Retrieve Portfolio\n" +
            "4)Back\n" +
            "3\n" +
            "Enter name of Portfolio:b\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "a.csv\n" +
            "Success!!!\n" +
            "\n" +
            "1)Create Portfolio\n" +
            "2)Save Portfolio\n" +
            "3)Retrieve Portfolio\n" +
            "4)Back\n" +
            "4\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "5\n" +
            "Enter name of Portfolio:b\n" +
            "Portfolio: b\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n", out.toString());
  }

  /**
   * Test save and retrieve for basic buy strategy.
   */

  @Test
  public void testSaveAndRetrieveBasicBuy() throws IOException {
    in = new StringReader("1\na\n2\n1\na\nAAPL\n2000\n20\n2018-11-01\nYes\nbba.csv\n" +
            "basic\n2\n4\nbba.csv\nb\nbasic\n5\nb\n");

    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertTrue(out.toString().contains("Success"));

    File file = new File("bba.csv");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    String result = "";
    while ((st = br.readLine()) != null) {
      result = result + st;
    }
    assertEquals("\nbasic,Basic buy,AAPL,2000.0,20.0,2018-11-01T15:59:59", result);

    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "1\n" +
            "1\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:a\n" +
            "Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "1\n" +
            "Enter name of Portfolio:a\n" +
            "Enter name of ticker:AAPL\n" +
            "Enter price you want to buy(numeric): $2000\n" +
            "Enter commission per stock(numeric): $20\n" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2018-11-01\n" +
            "Success!!!\n" +
            "\n" +
            "Press Yes if you want to save the strategy, No otherwise: yes\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "bba.csv\n" +
            "Enter the Strategy name(Press q to back to main menu): basic\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "4\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "bba.csv\n" +
            "Enter name of Portfolio:b\n" +
            "Enter the Strategy name(Press q to back to main menu): basic\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "5\n" +
            "Enter name of Portfolio:b\n" +
            "Portfolio: b\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n", out.toString());
  }

  /**
   * Test save and retrieve for weighted buy strategy.
   */

  @Test
  public void testWeightedBuyPersistence() throws IOException {
    in = new StringReader("1\na\n2\n1\na\nAAPL\n2000\n20\n2018-11-01\nNo\n" +
            "2\n1\na\n100\n0\n2018-11-01\nNo\n2\n2\na\n5000\n30\n2018-11-02\n1\nYes\nwba.csv" +
            "\nweighted\n2\n4\nwba.csv\nb\nweighted\n5\nb\n");

    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertTrue(out.toString().contains("Success"));

    File file = new File("wba.csv");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    String result = "";
    while ((st = br.readLine()) != null) {
      result = result + st;
    }
    assertEquals("\nweighted,Weighted Buy,5000.0,30.0,2018-11-02T15:59:59,2,AAPL," +
            "goog,1.0,1.0,", result);

    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "1\n" +
            "1\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:a\n" +
            "Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "1\n" +
            "Enter name of Portfolio:a\n" +
            "Enter name of ticker:AAPL\n" +
            "Enter price you want to buy(numeric): $2000\n" +
            "Enter commission per stock(numeric): $20\n" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2018-11-01\n" +
            "Success!!!\n" +
            "\n" +
            "Press Yes if you want to save the strategy, No otherwise: Yes\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "wba.csv\n" +
            "Enter the Strategy name(Press q to back to main menu): weighted\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "1\n" +
            "Enter name of Portfolio:a\n" +
            "Enter name of ticker:goog\n" +
            "Enter price you want to buy(numeric): $100\n" +
            "Enter commission per stock(numeric): $0\n" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2018-11-01\n" +
            "Success!!!\n" +
            "\n" +
            "Press Yes if you want to save the strategy, No otherwise: No\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "2\n" +
            "Enter name of Portfolio:a\n" +
            "Enter price you want to buy(numeric): $5000\n" +
            "Enter commission per stock(numeric): $30\n" +
            "Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2018-11-02\n" +
            "Enter 1 to split amount equally, 2 to provide weights:1\n" +
            "Success!!!\n" +
            "\n" +
            "Press Yes if you want to save the strategy, No otherwise: Yes\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "wba.csv\n" +
            "Enter the Strategy name(Press q to back to main menu): weighted\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "4\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "wba.csv\n" +
            "Enter name of Portfolio:b\n" +
            "Enter the Strategy name(Press q to back to main menu): weighted\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "5\n" +
            "Enter name of Portfolio:b\n" +
            "Portfolio: b\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2500.0\n" +
            "Commission=30.0\n" +
            "DateBought=2018-11-02 15:59:59\n" +
            "NumberOfShares=12.049354154617314\n" +
            "\n" +
            "Ticker='goog'\n" +
            "Price bought=2500.0\n" +
            "Commission=30.0\n" +
            "DateBought=2018-11-02 15:59:59\n" +
            "NumberOfShares=2.363418069749194\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n", out.toString());
  }

  /**
   * Test save and retrieve for dollar cost averaging buy strategy.
   */
  @Test
  public void testDCAPersistence() throws IOException {
    in = new StringReader("1\na\n2\n3\na\n2000\n2\n20\n2018-11-01\nn\n5\nAAPL\nGOOG\n1\n1\n" +
            "Yes\ndca.csv\nDCA\n");

    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertTrue(out.toString().contains("Success"));

    File file = new File("dca.csv");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    String result = "";
    while ((st = br.readLine()) != null) {
      result = result + st;
    }
    assertEquals("\nDCA,DollarCostAveraging,2000.0,20.0,2018-11-01T15:59:59," +
            "2018-12-06T13:48:22.604536800,5.0,2,AAPL,GOOG,1.0,1.0,", result);

    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "1\n" +
            "1\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:a\n" +
            "Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "3\n" +
            "Enter name of Portfolio:a\n" +
            "Enter price you want to buy(numeric): $2000\n" +
            "Enter the number of stocks: 2\n" +
            "Enter commission per stock(numeric): $20\n" +
            "Enter start date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2018-11-01\n" +
            "Enter end date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss)[Enter N for today's date]:n\n" +
            "Enter buying frequency for strategy(days):5\n" +
            "Enter ticker name 1: AAPL\n" +
            "Enter ticker name 2: GOOG\n" +
            "Enter weight for AAPL:1\n" +
            "Enter weight for GOOG:1\n" +
            "Press Yes if you want to save the strategy, No otherwise: Yes\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "dca.csv\n" +
            "Enter the Strategy name(Press q to back to main menu): DCA\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n", out.toString());
  }

  /**
   * Test retrieve portfolio from user given file.
   */
  @Test
  public void testUserPortfolio() throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter("a.csv", "UTF-8");
    writer.println("a,AAPL,2000.0,20.0,2018-11-01T15:59:59,9.000090000900009");
    writer.println();
    writer.close();

    in = new StringReader("1\na\n1\n3\na\na.csv\nYes\n4\n5\na\n");

    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "1\n" +
            "1\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:a\n" +
            "Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1\n" +
            "1)Create Portfolio\n" +
            "2)Save Portfolio\n" +
            "3)Retrieve Portfolio\n" +
            "4)Back\n" +
            "3\n" +
            "Enter name of Portfolio:a\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "a.csv\n" +
            "Portfolio already exists, Type Yes to override, No otherwise: \n" +
            " Yes\n" +
            "Success!!!\n" +
            "\n" +
            "1)Create Portfolio\n" +
            "2)Save Portfolio\n" +
            "3)Retrieve Portfolio\n" +
            "4)Back\n" +
            "4\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "5\n" +
            "Enter name of Portfolio:a\n" +
            "Portfolio: a\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n", out.toString());
  }

  /**
   * Test retrieve all strategies from user.
   */
  @Test
  public void testUserStrategy() throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter("b.csv", "UTF-8");
    writer.println("basic,Basic buy,AAPL,2000.0,20.0,2018-11-01T15:59:59");
    writer.println("weighted,Weighted Buy,5000.0,30.0,2018-11-02T15:59:59,2,AAPL,goog,1.0,1.0,");
    writer.println("DCA,DollarCostAveraging,2000.0,20.0,2018-11-01T15:59:59," +
            "2018-12-06T13:48:22.604536800,5.0,2,AAPL,GOOG,1.0,1.0,");
    writer.close();

    in = new StringReader("1\na\n2\n4\nb.csv\na\nbasic\n2\n4\nb.csv\na\nweighted\n2\n4\nb.csv" +
            "\na\nDCA\n5\na");

    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "1\n" +
            "1\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:a\n" +
            "Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "4\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "b.csv\n" +
            "Enter name of Portfolio:a\n" +
            "Enter the Strategy name(Press q to back to main menu): basic\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "4\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "b.csv\n" +
            "Enter name of Portfolio:a\n" +
            "Enter the Strategy name(Press q to back to main menu): weighted\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "2\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Retrieve strategy \n" +
            "5. Back\n" +
            "4\n" +
            "Enter the file name in which the operation has to be done: \n" +
            "b.csv\n" +
            "Enter name of Portfolio:a\n" +
            "Enter the Strategy name(Press q to back to main menu): DCA\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "5\n" +
            "Enter name of Portfolio:a\n" +
            "Portfolio: a\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2500.0\n" +
            "Commission=30.0\n" +
            "DateBought=2018-11-02 15:59:59\n" +
            "NumberOfShares=12.049354154617314\n" +
            "\n" +
            "Ticker='goog'\n" +
            "Price bought=2500.0\n" +
            "Commission=30.0\n" +
            "DateBought=2018-11-02 15:59:59\n" +
            "NumberOfShares=2.363418069749194\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=4.5000450004500046\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=0.9345794392523364\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=4.907493742945477\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=0.9471401104365369\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=5.150126178091363\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=0.962806774308464\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=5.167157546633597\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=0.9420719931417159\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=5.656748500961648\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=0.9637532406202717\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=5.7267208796243265\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=0.9536343003185139\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-12-03 15:59:59\n" +
            "NumberOfShares=5.410669840926307\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-12-03 15:59:59\n" +
            "NumberOfShares=0.9038077420171181\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get cost Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n", out.toString());
  }
}
