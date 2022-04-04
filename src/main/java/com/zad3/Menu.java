package com.zad3;

import com.LineReader;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private double leftComparment;
    private double rightComparment;
    private double accuracy = 0.0;
    private List<Double> knots = new ArrayList<>();
    private List<Double> valuesForKnotsArguments = new ArrayList<>();
    private ArrayList<Double> x = new ArrayList<>();
    private ArrayList<Double> y = new ArrayList<>();
    private ArrayList<Double> xNewton = new ArrayList<>();
    private ArrayList<Double> yNewton = new ArrayList<>();
    private List<Function> objectList = new ArrayList<>();

    public Menu() {
        scanner = new Scanner(System.in);
        fillKnots();
    }

    public void start() {
        amountOfAssemblies();
        setComparment();
        countValueForKnotsArguments();
        Newton newton = new Newton(knots,valuesForKnotsArguments);
        fillArrays(xNewton,yNewton,newton);
        fillArrays(x,y);


        Chart chart = new Chart("ApplicationTitle", "ChartTitle", x, y, xNewton,yNewton);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public void amountOfAssemblies() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.print("Podaj liczbe złożeń: ");
            String amountOfSubmission = scanner.nextLine();

            try {
                for (int i = 0; i < Integer.parseInt(amountOfSubmission); i++) {
                    submissionFunction();
                }
                shouldContinue = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano złą liczbę złożeń");
            }
        }
    }

    public void setComparment() {
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println();
            try {
                System.out.print("Podaj lewy koniec przedziału: ");
                leftComparment = Double.parseDouble(scanner.nextLine());
                System.out.println();
                System.out.print("Podaj prawy koniec przedziału: ");
                rightComparment = Double.parseDouble(scanner.nextLine());
                shouldContinue = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano nieprawidłową liczbę!");
            }
        }
    }

    public void setAccuracy() {
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println();
            try {
                System.out.print("Podaj dokładność: ");
                accuracy = Double.parseDouble(scanner.nextLine());

                shouldContinue = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano nieprawidłową liczbę!");
            }
        }
    }

    public void submissionFunction() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println();
            System.out.println("Podaj rodzaj funkcji:");
            System.out.println("1.Wielomian");
            System.out.println("2.Sinus");
            System.out.println("3.Cosinus");
            System.out.println("4.Modul");
            System.out.print("Wybór: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    objectList.add(new Polynomial());
                    shouldContinue = false;
                    break;
                case "2":
                    objectList.add(new Sinus());
                    shouldContinue = false;
                    break;
                case "3":
                    objectList.add(new Cosinus());
                    shouldContinue = false;
                    break;
                case "4":
                    objectList.add(new Module());
                    shouldContinue = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wybrano zla opcje");
            }
        }
    }

    public void fillArrays(ArrayList<Double> x, ArrayList<Double> y) {
        double value = leftComparment;
        while (value <= rightComparment) {
            x.add(value);
            y.add(countValueOfFunctions(value, objectList));
            value += 0.05;
        }
    }

    public void fillArrays(ArrayList<Double> x, ArrayList<Double> y,Newton newton) {
        double value = leftComparment;
        while (value <= rightComparment) {
            x.add(value);
            y.add(newton.countValue(value));
            value += 0.05;
        }
    }

    public void fillKnots() {
        List<String> lines = LineReader.readLinesFromFile("src/main/java/com/zad3/wezly.txt");
        for (String line : lines) {
            String[] knot = line.split(",");
            for (String s : knot) {
                try {
                    knots.add(Double.parseDouble(s));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void countValueForKnotsArguments() {
        for (Double knot : knots) {
            valuesForKnotsArguments.add(countValueOfFunctions(knot, objectList));
        }
    }


    public static double countValueOfFunctions(double x, List<Function> list) {
        double value = list.get(0).countValue(x);
        for (int i = 1; i < list.size(); i++) {
            value = list.get(i).countValue(value);
        }
        return value;
    }

    public List<Double> getX() {
        return x;
    }

    public List<Double> getY() {
        return y;
    }
}
