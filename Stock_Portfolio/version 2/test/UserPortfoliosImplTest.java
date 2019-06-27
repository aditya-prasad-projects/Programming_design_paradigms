import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.NoSuchElementException;

import portfolio.stock.informationretrieval.AlphaVantageAPI;
import portfolio.stock.informationretrieval.StockData;
import portfolio.stock.model.UserPortfolios;
import portfolio.stock.model.UserPortfoliosImpl;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * This class tests all the methods that are provided by the Userportfolio and the UserPortfolioImpl
 * classes.
 */
public class UserPortfoliosImplTest {

  private UserPortfolios testObject;
  private StockData api;
  private DateTimeFormatter formatter;

  /**
   * Used to set up the api and the formatted for the date object.
   */
  @Before
  public void setup() {
    testObject = new UserPortfoliosImpl();
    testObject.createPortfolio("A");
    api = new AlphaVantageAPI();
    formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 15)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
            .toFormatter();
  }

  /**
   * Test creating a portfolio.
   */
  @Test
  public void testCreatePortfolio() {
    testObject.createPortfolio("TestName1");
    assertEquals("\n" +
            "PortfolioName = TestName1\n\n",
            testObject.portfolioContents("TestName1"));
    testObject.createPortfolio("TestName2");
    assertEquals("\n" +
            "PortfolioName = TestName2\n\n",
            testObject.portfolioContents("TestName2"));
    testObject.createPortfolio("TestName1 TestName2");
    assertEquals("\n" +
            "PortfolioName = TestName1 TestName2\n\n", testObject.
            portfolioContents("TestName1 TestName2"));
  }

  /**
   * Test create invalid portfolio.
   */
  @Test
  public void testInvalidPortfolio() {
    testObject.createPortfolio("TestName");
    assertEquals("\n" +
            "PortfolioName = TestName\n\n", testObject.portfolioContents("TestName"));
    try {
      testObject.createPortfolio("TestName");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Portfolio already exists", e.getMessage());
    }
    try {
      testObject.createPortfolio(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null can't be passed!", e.getMessage());
    }
    try {
      testObject.createPortfolio("");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null can't be passed!", e.getMessage());
    }
  }

  /**
   * Test adding stocks.
   */
  @Test
  public void testAddStock() {
    testObject.buyStocks("A", "AAPL", 1000,
            LocalDateTime.parse("2018-11-01", formatter), api);
    assertEquals("\nPortfolioName = A\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-11-01 16:00:00\n" +
            "NumberOfShares=4.5000450004500046\n" +
            "\n" +
            "\n", testObject.portfolioContents("A"));

    testObject.buyStocks("A", "AA", 300,
            LocalDateTime.parse("2018-11-13", formatter), api);
    assertEquals("\n" +
            "PortfolioName = A\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-11-01 16:00:00\n" +
            "NumberOfShares=4.5000450004500046\n" +
            "\n" +
            "\n" +
            "stock 2:\n" +
            "Ticker='AA'\n" +
            "Price bought=300.0\n" +
            "DateBought=2018-11-13 16:00:00\n" +
            "NumberOfShares=8.640552995391705\n" +
            "\n" +
            "\n", testObject.portfolioContents("A"));
    testObject.createPortfolio("TestName");
    assertEquals("\n" +
            "PortfolioName = TestName\n\n", testObject.portfolioContents("TestName"));
    testObject.buyStocks("TestName", "AAPL", 1000,
            LocalDateTime.parse("2018-11-01", formatter), api);
    assertEquals("\nPortfolioName = TestName\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-11-01 16:00:00\n" +
            "NumberOfShares=4.5000450004500046\n" +
            "\n" +
            "\n", testObject.portfolioContents("TestName"));
  }

  /**
   * Test passing null to buy stock.
   */
  @Test
  public void testNullToBuy() {
    try {
      testObject.buyStocks(null, null, 2000, null, api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Inputs cant be null", e.getMessage());
    }

    try {
      testObject.buyStocks("A", null, 2000, null, api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Inputs cant be null", e.getMessage());
    }

    try {
      testObject.buyStocks("A", "AA", 2000, null, api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Inputs cant be null", e.getMessage());
    }

    try {
      testObject.buyStocks(null, null, 2000,
              LocalDateTime.parse("2018-11-01", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Inputs cant be null", e.getMessage());
    }
    try {
      testObject.buyStocks(null, "AA", 2000,
              LocalDateTime.parse("2018-11-01", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Inputs cant be null", e.getMessage());
    }

    try {
      testObject.buyStocks("A", null, 2000,
              LocalDateTime.parse("2018-11-01", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Inputs cant be null", e.getMessage());
    }

    try {
      testObject.buyStocks(null, "AA", 2000, null, api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Inputs cant be null", e.getMessage());
    }
  }

  /**
   * Test invalid inputs.
   */
  @Test
  public void testInvalidBuyInputs() {
    try {
      testObject.buyStocks("A", "AAPL", -500,
              LocalDateTime.parse("2018-11-01", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Price cant be negative!", e.getMessage());
    }

    try {
      testObject.buyStocks("AAA", "AAPL", 500,
              LocalDateTime.parse("2018-11-01", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Portfolio does not exist!", e.getMessage());
    }

    try {
      testObject.buyStocks("A", "AAAAAAAAAAAAAAAAA", 500,
              LocalDateTime.parse("2018-11-01", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Ticker!", e.getMessage());
    }
  }

  /**
   * Test buy invalid date.
   */
  @Test
  public void testInvalidBuyDate() {
    try {
      testObject.buyStocks("A", "AA", 500,
              LocalDateTime.parse("2017-13-01", formatter), api);
      fail();
    } catch (DateTimeParseException e) {
      assertEquals("\n" +
              "PortfolioName = A\n" +
              "\n", testObject.portfolioContents("A"));
    }

    try {
      testObject.buyStocks("A", "AA", 500,
              LocalDateTime.parse("2017-11-40", formatter), api);
      fail();
    } catch (DateTimeParseException e) {
      assertEquals("\n" +
              "PortfolioName = A\n" +
              "\n", testObject.portfolioContents("A"));
    }

    try {
      testObject.buyStocks("A", "AA", 500,
              LocalDateTime.parse("kshadf", formatter), api);
      fail();
    } catch (DateTimeParseException e) {
      assertEquals("\n" +
              "PortfolioName = A\n" +
              "\n", testObject.portfolioContents("A"));
    }
    try {
      testObject.buyStocks("A", "AA", 500,
              LocalDateTime.parse("2019-12-01", formatter), api);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("\n" +
              "PortfolioName = A\n" +
              "\n", testObject.portfolioContents("A"));
    }
    try {
      testObject.buyStocks("A", "AA", 500,
              LocalDateTime.parse("2018-11-11", formatter), api);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("\n" +
              "PortfolioName = A\n" +
              "\n", testObject.portfolioContents("A"));
    }
    try {
      testObject.buyStocks("A", "AA", 500,
              LocalDateTime.parse("2018-11-10", formatter), api);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("\n" +
              "PortfolioName = A\n" +
              "\n", testObject.portfolioContents("A"));
    }
    try {
      testObject.buyStocks("A", "AA", 500,
              LocalDateTime.parse("2018-01-15", formatter), api);
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("\n" +
              "PortfolioName = A\n" +
              "\n", testObject.portfolioContents("A"));
    }
  }

  /**
   * Test getvalue().
   */
  @Test
  public void testGetValue() {
    assertEquals(0, testObject.getTotalCostBasis(LocalDateTime.parse("2018-11-01",
            formatter)), 0.01);
    assertEquals(0, testObject.getTotalValue(LocalDateTime.parse("2018-10-01",
            formatter)), 0.01);
    testObject.buyStocks("A", "AAPL", 1000,
            LocalDateTime.parse("2018-11-01", formatter), api);
    assertEquals("\nPortfolioName = A\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-11-01 16:00:00\n" +
            "NumberOfShares=4.5000450004500046\n" +
            "\n" +
            "\n", testObject.portfolioContents("A"));
    testObject.buyStocks("A", "AAPL", 500,
            LocalDateTime.parse("2018-10-01", formatter), api);

    assertEquals(1488.9113790372262,
            testObject.getTotalValue(LocalDateTime.parse("2018-11-01", formatter)),
            0.01);
    assertEquals(1500, testObject.getTotalCostBasis(LocalDateTime.parse("2018-11-01",
            formatter)), 0.01);
    assertEquals(500, testObject.getTotalValue(LocalDateTime.parse("2018-10-01",
            formatter)), 0.01);
    assertEquals(500, testObject.getTotalCostBasis(LocalDateTime.parse("2018-10-01",
            formatter)), 0.01);
    assertEquals(478.2187802516941, testObject
            .getTotalValue(LocalDateTime.parse("2018-10-15", formatter)), 0.01);
    assertEquals(500, testObject.getTotalCostBasis(LocalDateTime.parse("2018-10-15",
            formatter)), 0.01);
    assertEquals(1500, testObject.getTotalCostBasis(LocalDateTime.parse("2018-11-12",
            formatter)), 0.01);
    assertEquals(1300.9716608210701, testObject.getTotalValue(LocalDateTime
            .parse("2018-11-12", formatter)), 0.01);
    assertEquals(1500, testObject.getTotalCostBasis(LocalDateTime
            .parse("2018-11-11", formatter)), 0.01);
    assertEquals(1369.9833933567709, testObject.getTotalValue(LocalDateTime
            .parse("2018-11-11", formatter)), 0.01);
    assertEquals(1500, testObject.getTotalCostBasis(LocalDateTime
            .parse("2020-11-11", formatter)), 0.01);
    try {
      assertEquals(1369.9833933567709, testObject.getTotalValue(LocalDateTime
              .parse("2020-11-11", formatter)), 0.01);
      fail();
    } catch (NoSuchElementException e) {
      //pass the case.
    }
    try {
      assertEquals(500, testObject.getTotalCostBasis(LocalDateTime
              .parse("asjdnkajsd", formatter)), 0.01);
      fail();
    } catch (DateTimeParseException e) {
      //pass the case.
    }
    try {
      assertEquals(500, testObject.getTotalCostBasis(LocalDateTime
              .parse("2018-13-11", formatter)), 0.01);
      fail();
    } catch (DateTimeParseException e) {
      //pass the case
    }
    try {
      assertEquals(500, testObject.getTotalCostBasis(LocalDateTime
              .parse("2018-12-41", formatter)), 0.01);
      fail();
    } catch (DateTimeParseException e) {
      //pass the case
    }
    try {
      assertEquals(500, testObject.getTotalValue(LocalDateTime
              .parse("asjdnkajsd", formatter)), 0.01);
      fail();
    } catch (DateTimeParseException e) {
      //pass the case.
    }
    try {
      assertEquals(500, testObject.getTotalValue(LocalDateTime
              .parse("2018-13-11", formatter)), 0.01);
      fail();
    } catch (DateTimeParseException e) {
      //pass the case
    }
    try {
      assertEquals(500, testObject.getTotalValue(LocalDateTime
              .parse("2018-12-41", formatter)), 0.01);
      fail();
    } catch (DateTimeParseException e) {
      //pass the case
    }
  }

  /**
   * Test portfolio contents.
   */
  @Test
  public void testPortfolioContents() {
    assertEquals("\n" +
            "PortfolioName = A\n" +
            "\n", testObject.portfolioContents("A"));
    testObject.buyStocks("A", "AA", 1000, LocalDateTime.
            parse("2018-11-01", formatter), api);
    assertEquals("\n" +
            "PortfolioName = A\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AA'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-11-01 16:00:00\n" +
            "NumberOfShares=27.18868950516585\n" +
            "\n\n", testObject.portfolioContents("A"));
    testObject.buyStocks("A", "AAPL", 1000, LocalDateTime.
            parse("2017-11-01", formatter), api);
    assertEquals("\n" +
            "PortfolioName = A\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AA'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-11-01 16:00:00\n" +
            "NumberOfShares=27.18868950516585\n" +
            "\n" +
            "\n" +
            "stock 2:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2017-11-01 16:00:00\n" +
            "NumberOfShares=5.991970759182696\n" +
            "\n" +
            "\n", testObject.portfolioContents("A"));

    try {
      testObject.portfolioContents("bbb");
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Portfolio does not exist!", e.getMessage());
    }
  }

  /**
   * Test buy stock before 9am.
   */
  @Test
  public void testBuyStockBefore9() {
    try {
      testObject.buyStocks("A", "AA", 500, LocalDateTime.
              parse("2018-11-01 08:00:00", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Market closed!", e.getMessage());
    }
  }

  /**
   * Test buy stock after 4pm.
   */
  @Test
  public void testBuyStockAfter4() {
    try {
      testObject.buyStocks("A", "AA", 500, LocalDateTime.
              parse("2018-11-01 17:00:00", formatter), api);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Market closed!", e.getMessage());
    }
  }

  /**
   * Test buy stock before 4pm and after 9am.
   */
  @Test
  public void testBuyStockCorrectTime() {
    testObject.buyStocks("A", "AA", 500, LocalDateTime.
            parse("2018-11-01 14:00:00", formatter), api);
    assertEquals("\n" +
            "PortfolioName = A\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AA'\n" +
            "Price bought=500.0\n" +
            "DateBought=2018-11-01 14:00:00\n" +
            "NumberOfShares=13.594344752582925\n" +
            "\n" +
            "\n", testObject.portfolioContents("A"));
  }

  /**
   * Test multiple portfolios with multiple stocks.
   */
  @Test
  public void testMilti() {

    testObject.buyStocks("A", "AAPL", 1000,
            LocalDateTime.parse("2018-11-01", formatter), api);
    testObject.buyStocks("A", "FB", 5000,
            LocalDateTime.parse("2018-11-05", formatter), api);
    testObject.createPortfolio("B");
    testObject.buyStocks("B", "AAPL", 100,
            LocalDateTime.parse("2018-11-01", formatter), api);
    testObject.buyStocks("B", "FB", 50,
            LocalDateTime.parse("2018-11-05", formatter), api);
    assertEquals("\n" +
            "PortfolioName = A\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=1000.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=4.5000450004500046\n" +
            "\n" +
            "\n" +
            "stock 2:\n" +
            "Ticker='FB'\n" +
            "Price bought=5000.0\n" +
            "DateBought=2018-11-05 15:59:59\n" +
            "NumberOfShares=33.62927091740651\n" +
            "\n" +
            "\n", testObject.portfolioContents("A"));
    assertEquals("\n" +
            "PortfolioName = B\n" +
            "\n" +
            "stock 1:\n" +
            "Ticker='AAPL'\n" +
            "Price bought=100.0\n" +
            "DateBought=2018-11-01 15:59:59\n" +
            "NumberOfShares=0.4500045000450005\n" +
            "\n" +
            "\n" +
            "stock 2:\n" +
            "Ticker='FB'\n" +
            "Price bought=50.0\n" +
            "DateBought=2018-11-05 15:59:59\n" +
            "NumberOfShares=0.33629270917406506\n" +
            "\n" +
            "\n", testObject.portfolioContents("B"));

    assertEquals(6150, testObject.getTotalCostBasis(LocalDateTime
            .parse("2018-11-11", formatter)), 0.01);
    assertEquals(0, testObject.getTotalCostBasis(LocalDateTime
            .parse("2018-10-10", formatter)), 0.01);
    assertEquals(1100, testObject.getTotalCostBasis(LocalDateTime
            .parse("2018-11-03", formatter)), 0.01);

    assertEquals(5935.7847246753345, testObject.getTotalValue(LocalDateTime
                    .parse("2018-11-11", formatter)),
            0.01);
    assertEquals(0, testObject.getTotalValue(LocalDateTime
            .parse("2018-10-10", formatter)), 0.01);
    assertEquals(1027.0362703627036, testObject.getTotalValue(LocalDateTime
                    .parse("2018-11-03", formatter)),
            0.01);
  }
}