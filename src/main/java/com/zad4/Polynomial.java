package com.zad4;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Polynomial implements Function {
    private int degreeOfPolynomial;
    private List<Double> coefficients = new ArrayList<>();

    public Polynomial() {
        this.degreeOfPolynomial = addDegree();
        for (int i = degreeOfPolynomial; i >= 0; i--) {
            addCoefficient(i);
        }
    }

    private int addDegree() {
        while (true) {
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj stopień wielomianu: ");
            String degree = scanner.nextLine();
            try {
                return Integer.parseInt(degree);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano złą liczbę");
            }
        }
    }

    private void addCoefficient(int number) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        while (shouldContinue) {
            if (number != 0) {
                System.out.println("Podaj współczynnik przy potedze: " + number);
            } else {
                System.out.println("Podaj współczynnik przy wyrazie wolnym");
            }
            try {
                String input = scanner.nextLine();
                coefficients.add(Double.parseDouble(input));
                shouldContinue = false;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano błędny współczynnik");
            }
        }
    }

    public double countValue(double x) {
        double value = 0;
        int counter = coefficients.size() - 1;
        for (int i = 0; i <= degreeOfPolynomial; i++) {
            value += countDegreeValue(i, x) * coefficients.get(counter);
            counter--;
        }
        return value;
    }

    private double countDegreeValue(int degree, double x) {
        if (degree == 0) {
            return 1;
        }
        double value = x;
        for (int i = 1; i < degree; i++) {
            value *= x;
        }
        return value;
    }
}