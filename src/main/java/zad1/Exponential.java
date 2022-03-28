package zad1;

import javax.swing.*;
import java.util.Scanner;

public class Exponential implements Function {
    private double coefficient;
    private double freeWord;

    public Exponential() {
        coefficient = addValue("Podaj wartość współczynnika: ");
        freeWord = addValue("Podaj wartość wyrazu wolnego: ");
    }

    public double addValue(String message) {
        while (true) {
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            String degree = scanner.nextLine();
            try {
                return Double.parseDouble(degree);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Podano złą liczbę");
            }
        }
    }

    public double countValue(double x) {
        return Math.pow(coefficient, x) + freeWord;
    }

}
