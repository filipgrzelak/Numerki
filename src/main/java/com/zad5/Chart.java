package com.zad5;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Chart extends JFrame {


    public Chart(String title, ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> funcX, ArrayList<Double> funcY) {
        super(title);
        // Create dataset
        DefaultXYDataset dataset = createDataset(x, y, funcX, funcY);
        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Exercise 5", // Chart title
                "x", // X-Axis Label
                "y", // Y-Axis Label
                dataset
        );
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private DefaultXYDataset createDataset(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> funcX, ArrayList<Double> funcY) {

        DefaultXYDataset dataset = new DefaultXYDataset();

        double[][] tab1 = new double[2][x.size()];
        double[][] tab2 = new double[2][funcX.size()];
        for (int i = 0; i < x.size(); i++) {
            tab1[0][i] = x.get(i);
            tab1[1][i] = y.get(i);
        }
        for (int i = 0; i < funcX.size(); i++) {
            tab2[0][i] = funcX.get(i);
            tab2[1][i] = funcY.get(i);
        }
        dataset.addSeries("approximation",tab1);
        dataset.addSeries("function",tab2);
        return dataset;
    }
}
