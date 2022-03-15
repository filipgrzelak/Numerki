import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private List<Function> objectList = new ArrayList<>();
    double leftComparment;
    double rightComparment;
    double bisectionValue = 0.0;
    double secantValue = 0.0;
    double accuracy = 0.0;
    int amountOfAproximations = 0;
    private ArrayList<Double> x = new ArrayList<>();
    private ArrayList<Double> y = new ArrayList<>();

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void start() {
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
        shouldContinue = true;

        while (shouldContinue) {
            System.out.println();
            try {
                System.out.print("Podaj lewy punkt/koniec przedziału: ");
                leftComparment = Double.parseDouble(scanner.nextLine());
                System.out.println();
                System.out.print("Podaj prawy punkt/koniec przedziału: ");
                rightComparment = Double.parseDouble(scanner.nextLine());
                shouldContinue = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano nieprawidłową liczbę!");
            }
        }

        shouldContinue = true;


        while (shouldContinue) {
            System.out.println();
            System.out.println("Wybierz metode zakończenia liczenia");
            System.out.println("1. Dokładność");
            System.out.println("2. Ilość powtórzeń");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    setAccuracy();
                    bisectionValue = Bisection.findZeroPlace(objectList, leftComparment, rightComparment, accuracy);
                    secantValue = Secant.findZeroPlace(leftComparment, rightComparment, objectList, accuracy);
                    shouldContinue = false;
                    break;
                case "2":
                    setAmountOfAproximations();
                    bisectionValue = Bisection.findZeroPlace(objectList, leftComparment, rightComparment, amountOfAproximations);
                    secantValue = Secant.findZeroPlace(leftComparment, rightComparment, objectList, amountOfAproximations);
                    shouldContinue = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wybrano złą opcję!");

            }
        }
        System.out.println(bisectionValue);
        System.out.println(secantValue);
        fillArrays();
        //TODO: add zero places here
        Chart chart = new Chart("ApplicationTitle", "ChartTitle", x, y, bisectionValue);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
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

    public void setAmountOfAproximations() {
        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println();
            try {
                System.out.print("Podaj liczbę aproksymacji: ");
                amountOfAproximations = Integer.parseInt(scanner.nextLine());

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
            System.out.println("3.Wykładnicza");
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
                    objectList.add(new Exponential());
                    shouldContinue = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wybrano zla opcje");
            }
        }
    }

    public void fillArrays() {
        double value = leftComparment;
        while (value <= rightComparment) {
            x.add(value);
            y.add(countValueOfFunctions(value, objectList));
            value += 0.05;
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
