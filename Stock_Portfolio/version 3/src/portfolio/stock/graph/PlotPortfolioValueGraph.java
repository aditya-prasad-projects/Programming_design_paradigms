package portfolio.stock.graph;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;

import portfolio.stock.constants.Constants;

/**
 * Creates a JFrame to have a graph using JFreeChart.
 */
public class PlotPortfolioValueGraph extends JFrame {

  /**
   * Initialize and create the JFrame.
   *
   * @param title      title of the frame
   * @param chartTitle title of the chart
   * @param dataset    the data that must be plotted.
   */
  public PlotPortfolioValueGraph(String title, String chartTitle,
                                 DefaultCategoryDataset dataset) {
    super(title);

    JFreeChart graph = ChartFactory.createLineChart(
            chartTitle,
            Constants.DATE, Constants.TOTAL_STOCKS_VALUE,
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);

    ChartPanel cPanel = new ChartPanel(graph);

    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    cPanel.setPreferredSize(new java.awt.Dimension(550, 500));
    setContentPane(cPanel);
  }

}
