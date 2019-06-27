import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import portfolio.stock.view.PortfolioView;
import portfolio.stock.view.PortfolioViewImpl;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class PortfolioViewImplTest {

  PortfolioView view;
  Reader in;
  StringBuffer out;

  /**
   * Test getOptions.
   */
  @Test
  public void testGetOptions() {
    in = new StringReader("1");
    out = new StringBuffer();
    view = new PortfolioViewImpl(in, out);
    assertEquals(1, view.getOption());
    in = new StringReader("-3");
    view = new PortfolioViewImpl(in, out);
    assertEquals(-3, view.getOption());
    in = new StringReader("0");
    view = new PortfolioViewImpl(in, out);
    assertEquals(0, view.getOption());
    in = new StringReader("a");
    view = new PortfolioViewImpl(in, out);
    try {
      assertEquals("a", view.getOption());
      fail();
    } catch (NumberFormatException e) {
      assertEquals("For input string: \"a\"", e.getMessage());
    }
  }

  /**
   * Test start.
   */
  @Test
  public void testStart() {
    in = new StringReader("Team Portfolio");
    out = new StringBuffer();
    view = new PortfolioViewImpl(in, out);
    assertEquals("Team Portfolio", view.start());

    in = new StringReader("Team Portfolio\nExtra");
    view = new PortfolioViewImpl(in, out);
    assertEquals("Team Portfolio", view.start());

    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    assertEquals("", view.start());

    in = new StringReader("77 77");
    view = new PortfolioViewImpl(in, out);
    assertEquals("77 77", view.start());
  }

  /**
   * Test Error Portfolio Exists.
   */
  @Test
  public void testPutErrorPortfolioExists() {
    out = new StringBuffer();
    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    view.putErrorPortfolioExists();
    assertEquals("Portfolio already exists! Cannot be added!\n", out.toString());

    in = new StringReader("Testing");
    out = new StringBuffer();
    view = new PortfolioViewImpl(in, out);
    view.putErrorPortfolioExists();
    assertEquals("Portfolio already exists! Cannot be added!\n", out.toString());
  }

  /**
   * Test getTicker.
   */
  @Test
  public void testGetTicker() {
    out = new StringBuffer();
    in = new StringReader("testTicker");
    view = new PortfolioViewImpl(in, out);
    assertEquals("testTicker", view.getTicker());
    assertEquals("Enter name of ticker:", out.toString());

    out = new StringBuffer();
    in = new StringReader("testTicker Extra");
    view = new PortfolioViewImpl(in, out);
    assertEquals("testTicker Extra", view.getTicker());
    assertEquals("Enter name of ticker:", out.toString());
  }

  /**
   * Test portfolio not exists.
   */
  @Test
  public void testPortfolioNotExists() {
    out = new StringBuffer();
    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    view.portfolioNotExists();
    assertEquals("Portfolio does not exist! Try again\n", out.toString());
  }

  /**
   * Test market closed.
   */
  @Test
  public void testMarketClosed() {
    out = new StringBuffer();
    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    view.putMarketClosed();
    assertEquals("Market closed. Try a different time!\n", out.toString());
  }

  /**
   * Test Invalid Date.
   */
  @Test
  public void testPutInvalidDate() {
    out = new StringBuffer();
    in = new StringReader("asdlkndvnskv");
    view = new PortfolioViewImpl(in, out);
    view.putInvalidDate();
    assertEquals("Enter a valid date!\n", out.toString());
  }

  /**
   * Test stock holiday message.
   */
  @Test
  public void testPutStockHoliday() {
    out = new StringBuffer();
    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    view.putStockHoliday();
    assertEquals("stock cannot be bought on this day!\n", out.toString());
  }

  /**
   * Test stock Price Unavailable.
   */
  @Test
  public void testPutStockPriceUnavailable() {
    out = new StringBuffer();
    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    view.putStockPriceUnavailable();
    assertEquals("stock price not available for this date!\n", out.toString());
  }

  /**
   * Test invalid input message.
   */
  @Test
  public void testPutInvalidInput() {
    out = new StringBuffer();
    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    view.putInvalidInput();
    assertEquals("Please enter valid input\n", out.toString());
  }

  /**
   * Test get buy date.
   */
  @Test
  public void testGetBuyDate() {
    out = new StringBuffer();
    in = new StringReader("2018-11-11");
    view = new PortfolioViewImpl(in, out);
    assertEquals("2018-11-11", view.getBuyDate());
    assertEquals("Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):",
            out.toString());

    out = new StringBuffer();
    in = new StringReader("sjdbkv");
    view = new PortfolioViewImpl(in, out);
    assertEquals("sjdbkv", view.getBuyDate());
    assertEquals("Enter date when stock was bought(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):",
            out.toString());

  }

  /**
   * Test get value for date.
   */
  @Test
  public void testGetValueDate() {
    out = new StringBuffer();
    in = new StringReader("2018-11-11");
    view = new PortfolioViewImpl(in, out);
    assertEquals("2018-11-11", view.getValueDate());
    assertEquals("Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):", out.toString());

    out = new StringBuffer();
    in = new StringReader("sjdbkv");
    view = new PortfolioViewImpl(in, out);
    assertEquals("sjdbkv", view.getValueDate());
    assertEquals("Enter date to get value(yyyy-mm-dd)or(yyyy-mm-dd HH:MM:ss):", out.toString());

  }

  /**
   * Test get buying price.
   */
  @Test
  public void testGetBuyPrice() {
    out = new StringBuffer();
    in = new StringReader("2018");
    view = new PortfolioViewImpl(in, out);
    assertEquals(2018, view.getBuyPrice(), 0.1);
    assertEquals("Enter price you want to buy(numeric):", out.toString());
  }

  /**
   * Test get portfolio name.
   */
  @Test
  public void testGetPortfolioName() {
    out = new StringBuffer();
    in = new StringReader("Name");
    view = new PortfolioViewImpl(in, out);
    assertEquals("Name", view.getPortfolioName());
    assertEquals("Enter name of Portfolio:", out.toString());

    out = new StringBuffer();
    in = new StringReader("Name Name2");
    view = new PortfolioViewImpl(in, out);
    assertEquals("Name Name2", view.getPortfolioName());
    assertEquals("Enter name of Portfolio:", out.toString());
  }

  /**
   * Test menu.
   */
  @Test
  public void testPutMenu() {
    out = new StringBuffer();
    in = new StringReader("");
    view = new PortfolioViewImpl(in, out);
    view.getMenu();
    assertEquals("1. Add Portfolio\n" +
            "2. Buy stock\n" +
            "3. Get stock Basis\n" +
            "4. Get total Value\n" +
            "5. Get Portfolio Contents\n" +
            "6. Exit\n", out.toString());
  }

  /**
   * Test source of file.
   */
  @Test
  public void testGetFileSource() {
    out = new StringBuffer();
    in = new StringReader("Name");
    view = new PortfolioViewImpl(in, out);
    assertEquals("Name", view.getSource());
    assertEquals("Enter the folder in which all the csv files are present.\n", out.toString());

    out = new StringBuffer();
    in = new StringReader("Name Name2");
    view = new PortfolioViewImpl(in, out);
    assertEquals("Name Name2", view.getSource());
    assertEquals("Enter the folder in which all the csv files are present.\n", out.toString());
  }

  /**
   * Test get Data Option.
   */
  @Test
  public void testGetDataOption() {
    out = new StringBuffer();
    in = new StringReader("1");
    view = new PortfolioViewImpl(in, out);
    assertEquals(1, view.getDataOption());
    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n", out.toString());

    out = new StringBuffer();
    in = new StringReader("2");
    view = new PortfolioViewImpl(in, out);
    assertEquals(2, view.getDataOption());
    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n", out.toString());

    out = new StringBuffer();
    in = new StringReader("3");
    view = new PortfolioViewImpl(in, out);
    assertEquals(3, view.getDataOption());
    assertEquals("1. If data is to be queried by the application.\n" +
            "2. If user wishes to give the data.\n", out.toString());

  }
}