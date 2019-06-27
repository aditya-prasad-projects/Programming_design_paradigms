Changes from previous version.
1)Extended the main class and the interface in the model. Added buyStockWithCommission method in the extended class and overrode total basis method. 
2)Rewrote getContents to return an object of portfolio class. As returning a string means that the model is doing the views job. If any other way of printing is needed, it can use portfolio object returned by getContents.
3)Extended the current view class and the interface to provide methods to handle commission and different investment strategies.
4)Extended the controller and overrode the buyStock method to handle new Strategies.
5)Created a package called investment strategy inside controller that handles different investment strategies. It has an interface with a method that takes in the model, view and the api object. 
6)Created a package input commands inside controller that is used to communicate with the view, and is called by the investment strategy class to take in inputs.
7)To support GUI in the future, or any other form of view, we will have to change input commands package, with no changes to the investment strategy package or the controller.
8)Created a constants package that stores all the constants being used in the program.
9)The messages from the previous view were moved to the constants class.


The application has been implemented using the classic MVC pattern. 

When the jar file is run, it calls the main method in the InvestmentPortfolio class located in the main package.

The model and view objects are created in this main class and the controller object is created by passing the model and view as parameters.

Then the control is passed to the controller.

Controller:

The controller takes in the input from the view, and passes it to the model for computation. It receives the computation result form the model and passes it to the view to display.

Model:

The model provides methods to create portfolios, buy stocks and add it to a particular portfolio, get the cost basis, total value and displays the contents of the portfolio.

The model maintains a list of portfolio class objects each representing a portfolio. 

Each portfolio object contains a list of stock objects representing the current list of stocks in the portfolio.

A stock object represents a particular stock with it's ticker, date bought, price bought and number of shares. It also provides methods to get all these values.

View:

The view provides multiple methods to display and read data to and from the user. It provides a textual based interface to the user.

The data for retrieving the stock data, is in a separate package called information retrieval. We are providing the user with the option to choose in the beginning
from where the stock data is to be retrieved. The data package has a interface which all the files that provide the stock data should implement. If new database is to be added then the only change would be
that the new Data Retrieval class should implement the interface. 
The model is independent of the data retrieval operations. 

AlphaVantage:

As AlphaVantage allows us to get the data only 5 times in a minute, we use 10 different keys expanding out capacity to 50 times a minute. We also do not use the API for the same ticker. Once we get the data from the API
we store the data as a hashmap, caching the data this way helps in getting the data quickly the next time. To prevent overflow of memory, we allow only 15 ticker data in the hashmap.

Files:

The data is stored for each ticker in a ticker.csv file. All the csv files must be in one folder and we take in the folder's file path in the beginning if the user wants to operate offline.
