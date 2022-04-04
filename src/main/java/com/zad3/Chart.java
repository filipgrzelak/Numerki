package com.zad3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.ArrayList;

public class Chart extends ApplicationFrame {

    public Chart(String applicationTitle, String chartTitle, ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> xNewton, ArrayList<Double> yNewton) {
        super(applicationTitle);

        JFreeChart xyLineChart = createChart(chartTitle, x, y, xNewton, yNewton);

        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        final XYPlot plot = xyLineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(0.5f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(0.5f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(String chartTitle, ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> xNewton, ArrayList<Double> yNewton) {
        final XYSeries series1 = new XYSeries("Jeden");
        final XYSeries series2 = new XYSeries("Dwa");
        final XYSeriesCollection dataset = new XYSeriesCollection();

        for (int i = 0; i < x.size(); i++) {
            series1.add(x.get(i), y.get(i));
        }
        dataset.addSeries(series1);

        for (int i = 0; i < xNewton.size(); i++) {
            series2.add(xNewton.get(i), yNewton.get(i));
        }
        dataset.addSeries(series2);

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
