import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class Chart extends ApplicationFrame {

    public Chart(String applicationTitle, String chartTitle, double[] x, double[] y) {
        super(applicationTitle);

        JFreeChart xyLineChart = createChart(chartTitle, x, y);

        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ));
        final XYPlot plot = xyLineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0,Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(String chartTitle, double[] x, double[] y) {
        final XYSeries series = new XYSeries("XY");
        final XYSeriesCollection dataset = new XYSeriesCollection();

        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i]);
        }
        dataset.addSeries(series);

        return ChartFactory.createXYLineChart(chartTitle,
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
                );
    }
}
