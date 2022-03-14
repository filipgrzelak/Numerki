import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private List<Function> objectList = new ArrayList<>();
    double a = -5;
    double b = 5;
    private ArrayList<Double> x = new ArrayList<>();
    private ArrayList<Double> y = new ArrayList<>();

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.print("Podaj liczbe złożeń: ");
        String amountOfSubmission = scanner.nextLine();

        try {
            for (int i = 0; i < Integer.parseInt(amountOfSubmission); i++) {
                submissionFunction();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Podano złą liczbę złożeń");
            System.out.println(e);
        }
        System.out.println("Bisekcja: " + Bisection.findZeroPlace(objectList, -5, 5, 0.05));
        System.out.println("Sieczna: " + Secant.findZeroPlace(-2, 2, objectList, 120, 0.05));
        fillArrays();
        //TODO: add zero places here
        Chart chart = new Chart("ApplicationTitle", "ChartTitle", x, y, null);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
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
        double value = a;
        while (value <= b) {
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
