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

    @Override
    public String toString() {
        String line = "";
        for (Double coefficient : coefficients) {
            line += coefficient + " ";
        }
        return line;
    }
}