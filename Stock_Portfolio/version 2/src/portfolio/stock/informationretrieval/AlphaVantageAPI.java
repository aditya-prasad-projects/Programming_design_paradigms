package portfolio.stock.informationretrieval;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Used to get stock data from AlphaVantage site. Caching is also implemented using HashMaps that is
 * conformed to a fixed size.
 */
public class AlphaVantageAPI implements StockData {

  private static Map<String, String> tickerData;
  private static Queue<String> tickerQueue;
  private static int keyNumber;
  private List<String> apiKeys;

  /**
   * A default constructor.
   */
  public AlphaVantageAPI() {
    tickerData = new HashMap<>();
    tickerQueue = new LinkedList<>();
    keyNumber = 0;
    apiKeys = new ArrayList<>();
  }

  @Override
  public double getPrice(String ticker, LocalDateTime date) {
    StringBuilder apiOutput = new StringBuilder();
    BufferedReader in = null;
    initializeKeys();
    keyNumber = (keyNumber + 1) % 10;
    String apiKey = apiKeys.get(keyNumber);
    String stockSymbol = ticker; //ticker symbol for Google
    URL url;

    try {
      /*
      create the URL. This is the query to the web service.
      */
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage informationretrieval has either changed or "
              + "no longer works");
    }
    if (!tickerData.containsKey(ticker)) {

      try {
        in = new BufferedReader(new InputStreamReader(url.openStream()));
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        String dayValue;
        while ((dayValue = in.readLine()) != null) {
          apiOutput.append(dayValue + "\n");
        }
        tickerQueue.add(ticker);
        tickerData.put(ticker, apiOutput.toString());
        if (tickerData.size() > 100) {
          tickerData.remove(tickerQueue.remove());
        }
      } catch (IOException e) {
        throw new IllegalArgumentException("No price data found for " + stockSymbol);
      }
    } else {
      apiOutput.append(tickerData.get(ticker));
    }
    if (apiOutput.toString().contains("Error Message")) {
      throw new NoSuchElementException("Invalid Ticker!");
    }
    String stockValues = "";
    String[] lines = apiOutput.toString().split("\\n");
    for (String dayValue : lines) {
      if (dayValue.startsWith(date.toString().substring(0, 10))) {
        stockValues = dayValue;
        break;
      }
    }
    if (stockValues.length() == 0) {
      throw new NoSuchElementException("Date not available!");
    }
    String[] arrOfValues = stockValues.split(",");

    return Double.parseDouble(arrOfValues[4]);
  }

  /**
   * A second line of defence to caching. If caching fails, we go to the next key instead of making
   * the user wait. To build a scalable version of this implementation, buy the 250$ version of
   * AlphaVantage.
   */
  private void initializeKeys() {
    apiKeys.add("ZIVRJYT7XE375BPE");
    apiKeys.add("SCWIYKWZLIVJUQ0W");
    apiKeys.add("64BOIGTMYLFDHO1J");
    apiKeys.add("I3IW9JU735M48OZR");
    apiKeys.add("LTD23QJWK16WWD0R");
    apiKeys.add("777HMLC7AWXCGBF4");
    apiKeys.add("GF87S1PXQAUIO4HR");
    apiKeys.add("QST91MOXZFZMF755");
    apiKeys.add("1RVOX0ABVLSXFBEA");
    apiKeys.add("UIH4RV8L7KKCSFR7");
  }
}

