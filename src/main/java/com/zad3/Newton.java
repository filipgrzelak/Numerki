package com.zad3;

import java.util.ArrayList;
import java.util.List;

public class Newton implements Function {

    private List<Double> knots;
    private List<Double> valueForKnots;
    private List<List<Double>> listOfDividers = new ArrayList<>();
    private int counter = 0;
    private int amountOfDividers = 0;
    private int startingPoint = 2;
    private int checkingList = 0;

    public Newton(List<Double> knots, List<Double> valueForKnots) {
        this.knots = knots;
        this.valueForKnots = valueForKnots;
        amountOfDividers = knots.size() - 1;
        counter = amountOfDividers;
        for (int i = 0; i < counter; i++) {
            listOfDividers.add(new ArrayList<Double>());
        }
        countFirstRowDividerValues();
        while (counter != 0) {
            countNRowDividerValues();
        }
    }

    private void countFirstRowDividerValues() {
        for (int i = 0; i < counter; i++) {
            double result = (valueForKnots.get(i + 1) - valueForKnots.get(i)) / (knots.get(i + 1) - knots.get(i));
            listOfDividers.get(0).add(result);
        }
    }

    private void countNRowDividerValues() {
        counter--;
        int temp = startingPoint;
        for (int i = 0; i < counter; i++) {
            listOfDividers.get(checkingList + 1)
                    .add(
                            (listOfDividers.get(checkingList).get(i + 1) - listOfDividers.get(checkingList).get(i))
                                    / (knots.get(temp) - knots.get(temp - startingPoint))
                    );
            temp++;
        }
        startingPoint++;
        checkingList++;
    }

    public double countValue(double x) {
        double result = 0;
        result += valueForKnots.get(0);
        for (int i = 0; i < amountOfDividers; i++) {
            double temp = listOfDividers.get(i).get(0);
            for (int j = 0; j <= i; j++) {
                temp *= x - knots.get(j);
            }
            result += temp;
        }
        return result;
    }
}
