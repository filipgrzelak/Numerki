package com.zad5;

import com.zad3.Chart;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private double accuracy = 0.0;
    private int numberOfNodes = 0;
    private double leftCompartment = 0;
    private double rightCompartment = 0;
    private int polynomialDegree = 0;
    private List<Function> objectList = new ArrayList<>();

    private ArrayList<Double> approxX = new ArrayList<>();
    private ArrayList<Double> approxY = new ArrayList<>();

    private ArrayList<Double> functionX = new ArrayList<>();
    private ArrayList<Double> functionY = new ArrayList<>();

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        amountOfAssemblies();
        setNumberOfNodes();
        setCompartment();
        degreeOfPolynomial();

        double epsilon = 0.0;
        double x, y;

        for (int i = 0; i < 100; i++) {
            x = leftCompartment + 1.0 * i / 100 * (rightCompartment - leftCompartment);
            y = approximation(x, polynomialDegree, numberOfNodes);
            approxX.add(x);
            approxY.add(y);
            epsilon += Math.abs(countValueOfFunctions(x, objectList) - y);
        }

        epsilon = epsilon / 100;

        System.out.println("Błąd aproksymacji: " + epsilon);

        for (double i = leftCompartment; i <= rightCompartment; i += 0.01) {
            functionX.add(i);
            functionY.add(countValueOfFunctions(i, objectList));
        }

        SwingUtilities.invokeLater(() -> {
            com.zad5.Chart example = new com.zad5.Chart("Title", approxX, approxY, functionX, functionY);
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(600, 400);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
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

    public void setCompartment() {
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println();
            try {
                System.out.print("Podaj początek przedziału: ");
                leftCompartment = Double.parseDouble(scanner.nextLine());
                System.out.print("Podaj koniec przedziału: ");
                rightCompartment = Double.parseDouble(scanner.nextLine());

                shouldContinue = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano nieprawidłową liczbę!");
            }
        }
    }

    public void setNumberOfNodes() {
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println();
            try {
                System.out.print("Podaj liczbę węzłów: ");
                numberOfNodes = Integer.parseInt(scanner.nextLine());

                shouldContinue = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano nieprawidłową liczbę!");
            }
        }
    }

    public void degreeOfPolynomial() {
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println();
            try {
                System.out.print("Podaj stopień wielomianu: ");
                polynomialDegree = Integer.parseInt(scanner.nextLine());

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

    //funkcja obliczajaca wartosc iloczynu wybranej funkcji i wagi 1/sqrt(1-x^2) w punkcie x
    double functionsValueWithWeight(double x) {
        return (countValueOfFunctions(x, objectList) * (1 / Math.sqrt(1 - x * x)));
    }

    //funkcja obliczajaca calke zgodnie z kwadratura Gaussa-Czebyszewa
    double GaussIntegral(int numberOfNodes) {
        double output = 0;
        double currentWeight;
        double currentNode;
        for (int i = 1; i <= numberOfNodes; i++) {
            currentWeight = Math.PI / numberOfNodes;
            currentNode = -Math.cos(((2 * i - 1) * Math.PI) / (2 * numberOfNodes));
            output += currentWeight * countValueOfFunctions(currentNode, objectList);
        }
        return output;
    }

    double SimpsonIntegral(double leftComparment, double rightComparment) {
        double output = 0;
        double temp;
        double length;
        double delta;
        int subCompartment = 1;
        delta = rightComparment - leftComparment; //odleglosc od konca do poczatku przedzialu
        do {
            subCompartment = subCompartment * 2;
            length = delta / subCompartment;
            temp = output;
            output = 0;
            output += functionsValueWithWeight(leftComparment) + functionsValueWithWeight(rightComparment);
            for (int i = 1; i < subCompartment / 2; i++) {
                output += 4 * functionsValueWithWeight(leftComparment + (2 * i - 1) * length);
                output += 2 * functionsValueWithWeight(leftComparment + (2 * i) * length);
            }
            output *= length / 3;
        } while (Math.abs(temp - output) > accuracy);
        return output;
    }

    //funkcja obliczajaca granice w celu wyliczenia calki Newtona-Cotesa
    double SimpsonBound() {
        double leftComparment;
        double rightComparment;
        double temp = 0;
        double output = 0;

        //granica  do +1
        leftComparment = 0;
        rightComparment = 0.5;
        do {
            temp = SimpsonIntegral(leftComparment, rightComparment);
            output += temp;
            leftComparment = rightComparment;
            rightComparment = rightComparment + ((1 - rightComparment) * 1 / 2);
        } while (Math.abs(temp) > accuracy);

        //granica do -1
        leftComparment = -0.5;
        rightComparment = 0;
        do {
            temp = SimpsonIntegral(leftComparment, rightComparment);
            output += temp;
            rightComparment = leftComparment;
            leftComparment = leftComparment - ((1 - Math.abs(rightComparment)) * 1 / 2);
        } while (Math.abs(temp) > accuracy);

        return output;
    }

    double czebyszew(int degree, double x) {
        if (degree == 0) {
            return 1;
        } else if (degree == 1) {
            return x;
        }
        return (2 * x * czebyszew(degree - 1, x) - czebyszew(degree - 2, x));
    }

    double czebyszewIntegral(int nodeCounter, int czybyszewDegree) {
        double calka = 0.0, weight = 0.0, node = 0.0;
        for (int i = 1; i <= nodeCounter; i++) {
            weight = Math.PI / nodeCounter;
            node = -Math.cos(((2 * i - 1) * Math.PI) / (2 * nodeCounter));
            calka += weight * countValueOfFunctions(node, objectList) * czebyszew(czybyszewDegree, node);
        }
        return calka;
    }

    double approximation(double x, int degree, int nodeCount) {
        double output = 1.0 / 2.0 * 2.0 / Math.PI * czebyszewIntegral(nodeCount, 0);
        for (int i = 1; i <= degree; i++) {
            output += 2.0 / Math.PI * czebyszewIntegral(nodeCount, i) * czebyszew(i, x);
        }
        return output;
    }


    public static double countValueOfFunctions(double x, List<Function> list) {
        double value = list.get(0).countValue(x);
        for (int i = 1; i < list.size(); i++) {
            value = list.get(i).countValue(value);
        }
        return value;
    }
}
