package com.zad3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chart extends JFrame {

    public Chart(String title, ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> xNewton, ArrayList<Double> yNewton) {
        super(title);
        // Create dataset
        DefaultXYDataset dataset = createDataset(x, y, xNewton, yNewton);
        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Exercise 3", // Chart title
                "x", // X-Axis Label
                "y", // Y-Axis Label
                dataset
        );
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private DefaultXYDataset createDataset(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> xNewton, ArrayList<Double> yNewton) {

        DefaultXYDataset dataset = new DefaultXYDataset();

        double[][] tab1 = new double[2][x.size()];
        double[][] tab2 = new double[2][x.size()];
        for (int i = 0; i < x.size(); i++) {
            tab1[0][i] = x.get(i);
            tab1[1][i] = y.get(i);
            tab2[0][i] = xNewton.get(i);
            tab2[1][i] = yNewton.get(i);
        }
        dataset.addSeries("Podstawowy",tab1);
        dataset.addSeries("Interpolacja",tab2);
        return dataset;
    }
}
