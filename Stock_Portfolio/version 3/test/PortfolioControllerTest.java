import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import portfolio.stock.controller.IPortfolioController;
import portfolio.stock.controller.PortfolioControllerPlus;
import portfolio.stock.model.UserPortfolioTwo;
import portfolio.stock.model.UserPortfolioTwoImpl;
import portfolio.stock.view.PortfolioViewPlusTwo;
import portfolio.stock.view.PortfolioViewPlusTwoImpl;

/**
 * Tests new Cotroller for new strategies and commission.
 */
public class PortfolioControllerTest {
  private StringReader in;
  private StringBuffer out;
  private PortfolioViewPlusTwo view;
  private UserPortfolioTwo model;
  private IPortfolioController controller;

  /**
   * Used to set the appendable object and the model.
   */
  @Before
  public void setup() {
    out = new StringBuffer();
    model = new UserPortfolioTwoImpl();
  }

  /**
   * Used to test normal operations of weighted buy.
   */
  @Test
  public void testWeightedBuy() {
    in = new StringReader("1\na\n2\n1\na\nAAPL\n2000\n20\n2018-11-01\n2\n1\na\nGOOG\n2000" +
            "\n20\n2018-11-01\n2\n2\na\n3000\n20\n2018-11-01\n2\n3\n4\n3\n2018-11-28\n5\na\n4\n" +
            "2018-11-27\n3\n2018-11-28\n4\n2018-11-26\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Enter 1 to split amount equally, 2 to provide weights:" +
            "Enter weight for AAPL:Enter weight for GOOG:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):7080.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=1.8691588785046729\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1285.7142857142856\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=5.785772143435719\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=1714.2857142857142\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=1.6021361815754338\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):6201.743893727321\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):7080.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):6221.976673545106\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Used to test the normal operations of dollar-cost averaging.
   */
  @Test
  public void testDollarCostAveraging() {
    in = new StringReader("1\na\n2\n3\na\n2\n2000\n20\n2018-11-01\nn\n5\nGOOG\nFB\n" +
            "2\n3\n5\na\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter the number of stocks:" +
            " Enter price you want to buy(numeric):Enter commission per stock(numeric):" +
            "Enter start date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):Enter end date(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss)[Enter N for ongoing strategy]:Enter buying frequency for " +
            "strategy(days):Enter ticker name 1: Enter ticker name 2: Enter weight for GOOG:" +
            "Enter weight for FB:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=0.7476635514018691\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=7.907742998352553\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=0.7577120883492295\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=8.003201280512204\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=0.7702454194467712\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=8.47756976333451\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=0.7536575945133727\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=8.600301010535368\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=0.7710025924962174\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=8.900756564307967\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=0.7629074402548112\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=8.798944126704797\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Used to check that the cost basis is correct for dollar-cost averaging.
   */
  @Test
  public void testDollarCostAveraging2() {
    in = new StringReader("1\na\n2\n3\na\n2\n2000\n20\n2018-11-01\nn\n5\nGOOG\nFB\n2\n3\n5\n" +
            "a\n" +
            "3\n2018-11-27\n3\n2018-11-25\n3\n2018-11-01\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter the number of stocks: Enter price you want to " +
            "buy(numeric):Enter commission per stock(numeric):Enter start date(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Enter end date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss)" +
            "[Enter N for ongoing strategy]:Enter buying frequency for strategy(days):" +
            "Enter ticker name 1: Enter ticker name 2: Enter weight for GOOG:" +
            "Enter weight for FB:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=0.7476635514018691\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=7.907742998352553\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=0.7577120883492295\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=8.003201280512204\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=0.7702454194467712\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=8.47756976333451\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=0.7536575945133727\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=8.600301010535368\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=0.7710025924962174\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=8.900756564307967\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=0.7629074402548112\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=8.798944126704797\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):12240.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):10200.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests passing negative for weighted buy.
   */
  @Test
  public void testCommissionWeightedBuy() {
    in = new StringReader("1\na\n2\n1\na\nAAPL\n2000\n20\n2018-11-01\n2\n1\na\nGOOG\n2000" +
            "\n20\n2018-11-01\n2\n2\na\n3000\n-20\n2018-11-01\n2\n3\n4\n3\n2018-11-28\n5\na\n4\n" +
            "2018-11-27\n3\n2018-11-28\n4\n2018-11-26\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Enter 1 to split amount equally, 2 to provide weights:" +
            "Enter weight for AAPL:Enter weight for GOOG:Please enter valid input\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):4040.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=2000.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=1.8691588785046729\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):3520.343906055883\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):4040.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):3531.6330991347295\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }



  /**
   * Tests passing negative and zero commission for dollar cost averaging.
   */
  @Test
  public void testCommission() {
    in = new StringReader("1\na\n2\n1\na\nAAPL\n2000\n0\n2018-11-01\n2\n1\na\nGOOG\n2000" +
            "\n-1\n2018-11-01\n5\na\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter name of ticker:Enter price you want to buy(numeric):" +
            "Enter commission per stock(numeric):Enter date when stock was bought(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Please enter valid input\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='AAPL'\n" +
            "Price bought=2000.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=9.000090000900009\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests if total value and total cost works properly for dollar cost averaging.
   */
  @Test
  public void testDollarCostAveragingValue() {
    in = new StringReader("1\na\n2\n3\na\n2\n2000\n20\n2018-11-01\nn\n5\nGOOG\nFB\n2\n3\n3" +
            "\n2018-11-27\n4\n2018-11-20\n3\n2018-11-25\n4\n2018-11-15\n5\na\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter the number of stocks: Enter price you want to " +
            "buy(numeric):Enter commission per stock(numeric):Enter start date(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Enter end date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss)" +
            "[Enter N for ongoing strategy]:Enter buying frequency for strategy(days):" +
            "Enter ticker name 1: Enter ticker name 2: Enter weight for GOOG:Enter weight " +
            "for FB:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):12240.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):7476.021649264492\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):10200.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):5931.164242908928\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=0.7476635514018691\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=7.907742998352553\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=0.7577120883492295\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=8.003201280512204\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=0.7702454194467712\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=8.47756976333451\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=0.7536575945133727\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=8.600301010535368\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=0.7710025924962174\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=8.900756564307967\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=0.7629074402548112\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=8.798944126704797\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests negative commission for dollar cost averaging.
   */
  @Test
  public void testCommissionForDollarCostAverging() {
    in = new StringReader("1\na\n2\n3\na\n2\n2000\n-10\n0\n2018-11-05\nn\n5" +
            "\nGOOG\nFB\n2\n3\n3\n2018-11-05\n4\n2018-11-05\n5\na\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter the number of stocks: Enter price you want to " +
            "buy(numeric):Enter commission per stock(numeric):Please enter valid input\n" +
            "Enter commission per stock(numeric):Enter start date(yyyy-mm-dd)or(yyyy-mm-dd " +
            "HH:MM:ss):Enter end date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss)" +
            "[Enter N for ongoing strategy]:Enter buying frequency for strategy(days):" +
            "Enter ticker name 1: Enter ticker name 2: Enter weight for GOOG:" +
            "Enter weight for FB:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2000.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2000.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-05 15:59:59\n" +
            "NumberOfShares=0.76916420694363\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-05 15:59:59\n" +
            "NumberOfShares=8.071025020177562\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=0.7702454194467712\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=8.47756976333451\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-15 15:59:59\n" +
            "NumberOfShares=0.751378309586648\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-15 15:59:59\n" +
            "NumberOfShares=8.342022940563087\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-20 15:59:59\n" +
            "NumberOfShares=0.7799095304944627\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-20 15:59:59\n" +
            "NumberOfShares=9.061390923506758\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=0.7629074402548112\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=0.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=8.798944126704797\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }

  /**
   * Tests MultipleDollarCostAveraging operations.
   */
  @Test
  public void testMultipleDollarCostAveraging() {
    in = new StringReader("1\na\n2\n3\na\n2\n2000\n20\n2018-11-01\nn\n5\nGOOG\nFB\n2\n3" +
            "\n2\n3\n" +
            "b\n2\n2000\n20\n2018-11-02\n2018-11-28\n10\nGRPN\nFB\n2\n3\n3\n2018-11-01\n3\n" +
            "2018-11-02\n3\n2018-11-03\n4\n2018-11-03\n5\na\n6");
    view = new PortfolioViewPlusTwoImpl(in, out);
    controller = new PortfolioControllerPlus(model, view);
    controller.goController();
    assertEquals(out.toString(), "1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n" +
            "Create a portfolio to start:\n" +
            "\n" +
            "Enter name of new Portfolio:Success!!!\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter the number of stocks: Enter price you want to buy" +
            "(numeric):Enter commission per stock(numeric):Enter start date(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Enter end date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss)" +
            "[Enter N for ongoing strategy]:Enter buying frequency for strategy(days):" +
            "Enter ticker name 1: Enter ticker name 2: Enter weight for GOOG:" +
            "Enter weight for FB:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "1. Basic Buy\n" +
            "2. Weighted Buy\n" +
            "3. Dollar-cost averaging\n" +
            "4. Back\n" +
            "Enter name of Portfolio:Enter the number of stocks: Enter price you want to " +
            "buy(numeric):Enter commission per stock(numeric):Enter start date(yyyy-mm-dd)or" +
            "(yyyy-mm-dd HH:MM:ss):Enter end date(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss)" +
            "[Enter N for ongoing strategy]:Enter buying frequency for strategy(days):" +
            "Enter ticker name 1: Enter ticker name 2: Enter weight for GRPN:" +
            "Enter weight for FB:1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):2040.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):4080.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):4080.0\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n" +
            "Enter name of Portfolio:Portfolio: a\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=0.7476635514018691\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=7.907742998352553\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=0.7577120883492295\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-06 15:59:59\n" +
            "NumberOfShares=8.003201280512204\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=0.7702454194467712\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-12 15:59:59\n" +
            "NumberOfShares=8.47756976333451\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=0.7536575945133727\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-16 15:59:59\n" +
            "NumberOfShares=8.600301010535368\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=0.7710025924962174\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-21 15:59:59\n" +
            "NumberOfShares=8.900756564307967\n" +
            "\n" +
            "Ticker='GOOG'\n" +
            "Price bought=800.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=0.7629074402548112\n" +
            "\n" +
            "Ticker='FB'\n" +
            "Price bought=1200.0\n" +
            "Commission=20.0\n" +
            "DateBought=2018-11-26 15:59:59\n" +
            "NumberOfShares=8.798944126704797\n" +
            "\n" +
            "1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n");
  }
}
