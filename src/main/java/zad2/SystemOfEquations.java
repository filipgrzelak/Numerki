package zad2;

import java.util.ArrayList;
import java.util.List;

public class SystemOfEquations {
    private List<Equation> equations = new ArrayList<>();
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

    public boolean algorithm() {
        boolean r = false;
        double accuracy = countAccuracy();
        for (int i = 0; i < tab.length - 1; i++) {
            for (int j = i + 1; j < tab.length; j++) {
                if (Math.abs(equations.get(i).getCoefficients().get(j)) < accuracy) {
                    return r;
                }
                double multiplier = -equations.get(j).getCoefficients().get(i) / equations.get(i).getCoefficients().get(i);
                for (int k = i; k < tab.length + 1; k++) {
                    equations.get(j).getCoefficients().set(k, equations.get(j).getCoefficients().get(k) + multiplier * equations.get(i).getCoefficients().get(k));

                }
            }
        }
        for (int i = tab.length - 1; i >= 0; i--) {
            double s = equations.get(i).getCoefficients().get(tab.length);
            for (int j = tab.length - 1; j >= i + 1; j--) {
                s -= equations.get(i).getCoefficients().get(j) * tab[j];
                if (Math.abs(equations.get(i).getCoefficients().get(j)) < countAccuracy()) {
                    return r;
                }
            }
            tab[i] = s / equations.get(i).getCoefficients().get(i);
        }
        return true;
    }

    public double countAccuracy() {
        double accuracy = 0.1;
        for (int i = 0; i < 8; i++) {
            accuracy *= accuracy;
        }
        System.out.println(accuracy);
        return accuracy;
    }

    public List<Equation> getEquations() {
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
