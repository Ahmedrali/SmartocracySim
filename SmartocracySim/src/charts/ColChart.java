package charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 *
 */
public class ColChart extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public ColChart(final String title, int sol_num, int[] votes_DD, int[] votes_DDD, int[] votes_PV) {

        super(title);

        final CategoryDataset dataset = createDataset(sol_num, votes_DD, votes_DDD, votes_PV);
        final JFreeChart chart = createChart(dataset, title);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset(int sol_num, int[] votes_DD, int[] votes_DDD, int[] votes_PV) {
        
        // row keys...
        final String series1 = "DD";
        final String series2 = "DDD";
        final String series3 = "PV";

        // column keys...
        final String category1 = "Category 1";
        final String category2 = "Category 2";
        final String category3 = "Category 3";
        final String category4 = "Category 4";
        final String category5 = "Category 5";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < sol_num; i++){
        	String category = String.valueOf(i+1);
        	dataset.addValue(votes_DD[i], series1, category);
        	dataset.addValue(votes_DDD[i], series2, category);
        	dataset.addValue(votes_PV[i], series3, category);
        }
        return dataset;
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset, String title) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
        	title,                    // chart title
            "Solutions",              // domain axis label
            "Collective Decision",    // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
//        plot.setBackgroundPaint(Color.lightGray);
//        plot.setDomainGridlinePaint(Color.white);
//        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
//        final GradientPaint gp0 = new GradientPaint(
//            0.0f, 0.0f, Color.blue, 
//            0.0f, 0.0f, Color.lightGray
//        );
//        final GradientPaint gp1 = new GradientPaint(
//            0.0f, 0.0f, Color.green, 
//            0.0f, 0.0f, Color.lightGray
//        );
//        final GradientPaint gp2 = new GradientPaint(
//            0.0f, 0.0f, Color.red, 
//            0.0f, 0.0f, Color.lightGray
//        );
//        renderer.setSeriesPaint(0, gp0);
//        renderer.setSeriesPaint(1, gp1);
//        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }

}