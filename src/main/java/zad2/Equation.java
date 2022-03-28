package zad2;

import zad1.Function;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public int countValues() {
        int value = 0;
        for (Double coefficient : coefficients) {
            value += coefficient;
        }
        return value;
    }

    public int countValuesWithoutResult() {
        int value = 0;
        for (int i = 0; i < coefficients.size() - 1; i++) {
            value += coefficients.get(i);
        }
        return value;
    }

    public void checkSystemOfContradictoryEquations() {
        if (countValuesWithoutResult() == 0 && coefficients.get(coefficients.size() - 1) != 0) {
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