package zad2;

import java.util.ArrayList;
import java.util.List;

public class SystemOfEquations {
    private ArrayList<Equation> equations = new ArrayList<>();
    private Double[] tab;

    public SystemOfEquations(String filename) {
        List<String> lines = LineReader.readLinesFromFile(filename);
        for (String line : lines) {
            equations.add(new Equation(line));
        }
        tab = new Double[equations.get(0).getAmountOfVariables() - 1];
        initializeTab();
    }

    public void initializeTab() {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 0.0;
        }
    }

    public void algorithm() {
        double accuracy = countAccuracy();

        for (int i = 0; i < tab.length - 1; i++) {
            checkIfFirstEquationIsProperly(i, i);
            for (int j = i + 1; j < tab.length; j++) {
                checkIfWeHaveSameEquations();
                checkIfWeHaveSystemOfContradictoryEquations();
                if (Math.abs(equations.get(i).getCoefficients().get(j)) >= accuracy) {
                    double multiplier = -equations.get(j).getCoefficients().get(i) / equations.get(i).getCoefficients().get(i);
                    for (int k = i; k < tab.length + 1; k++) {
                        equations.get(j).getCoefficients().set(k, equations.get(j).getCoefficients().get(k) + multiplier * equations.get(i).getCoefficients().get(k));
                    }
                }
            }
        }
        checkIfWeHaveSameEquations();
        checkIfWeHaveSystemOfContradictoryEquations();
        for (int i = tab.length - 1; i >= 0; i--) {
            double s = equations.get(i).getCoefficients().get(tab.length);
            for (int j = tab.length - 1; j >= i + 1; j--) {
                if (Math.abs(equations.get(i).getCoefficients().get(j)) >= countAccuracy()) {
                    s -= equations.get(i).getCoefficients().get(j) * tab[j];
                }
            }
            tab[i] = s / equations.get(i).getCoefficients().get(i);
        }
    }

    public void checkIfWeHaveSameEquations() {
        for (Equation equation : equations) {
            if (equation.countValues() < countAccuracy()) {
                throw new IndefinityEquationException("Uklad nieoznaczony");
            }
        }
    }

    public void checkIfWeHaveSystemOfContradictoryEquations() {
        for (Equation equation : equations) {
            equation.checkSystemOfContradictoryEquations();
        }
    }

    public static double countAccuracy() {
        double accuracy = 1;
        for (int i = 0; i < 12; i++) {
            accuracy /= 10;
        }
        return accuracy;
    }

    public void checkIfFirstEquationIsProperly(int x, int y) {
        boolean canChange = true;
        if (Math.abs(equations.get(x).getCoefficients().get(y)) < countAccuracy()) {
            for (int i = x + 1; i < equations.size(); i++) {
                if (Math.abs(equations.get(i).getCoefficients().get(y)) > countAccuracy() && canChange) {
                    Equation temp = equations.get(i);
                    equations.set(i, equations.get(y));
                    equations.set(y, temp);
                    canChange = false;
                }
            }
        }
    }

    public ArrayList<Equation> getEquations() {
        return equations;
    }

    public Double[] getTab() {
        return tab;
    }

    public void printEquations() {
        for (Equation equation : equations) {
            System.out.println(equation);
        }
    }
}
