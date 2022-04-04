package com.zad2;

import java.util.ArrayList;
import java.util.List;

public class Equation {
    private int amountOfVariables;
    private List<Double> coefficients = new ArrayList<>();

    public Equation(String line) {
        addEquation(line);
    }

    private void addEquation(String line) {
        String[] tab = line.split(",");
        for (int i = 0; i < tab.length; i++) {
            coefficients.add(Double.parseDouble(tab[i]));
        }
        amountOfVariables = coefficients.size();
    }

    public int getAmountOfVariables() {
        return amountOfVariables;
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    public double countValues() {
        double value = 0;
        for (Double coefficient : coefficients) {
            value += Math.abs(coefficient);
        }
        return value;
    }

    public double countValuesWithoutResult() {

        double value = 0;
        for (int i = 0; i < coefficients.size() - 1; i++) {
            value += Math.abs(coefficients.get(i));
        }
        return value;
    }

    public void checkSystemOfContradictoryEquations() {
        double accuracy = SystemOfEquations.countAccuracy();
        if (countValuesWithoutResult() < accuracy && coefficients.get(coefficients.size() - 1) != 0) {
            throw new SystemOfContrafictoryEquationsException("Uklad sprzeczny");
        }
    }

    @Override
    public String toString() {
        String line = "";
        for (Double coefficient : coefficients) {
            line += coefficient + " ";
        }
        return line;
    }
}