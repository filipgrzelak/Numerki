import java.util.List;

public class Secant {

    public static double findZeroPlace(double leftCompartment, double rightCompartment, double firstPoint, double secondPoint, List<Function> list, int amountOfApproximations) {
        double fPoint = firstPoint;
        double sPoint = secondPoint;
        double valueOne;
        double valueTwo;
        double nPoint = 0.0;
        double nValue;
        int n = 0;

        valueOne = Menu.countValueOfFunctions(fPoint, list);
        valueTwo = Menu.countValueOfFunctions(secondPoint, list);

        if (Menu.countValueOfFunctions(leftCompartment, list) * Menu.countValueOfFunctions(rightCompartment, list) > 0) {
            throw new IllegalArgumentException("Brak miejsc zerowych");
        }

        while (n < amountOfApproximations) {
            if ((valueOne - valueTwo) == 0) {
                throw new IllegalArgumentException("Dzielenie przez 0");
            }
            nPoint = (valueOne * sPoint - valueTwo * fPoint) / (valueOne - valueTwo);
            nValue = Menu.countValueOfFunctions(nPoint, list);
            fPoint = sPoint;
            sPoint = nPoint;
            valueOne = valueTwo;
            valueTwo = nValue;
            n++;
        }
        return nPoint;
    }

    public static double findZeroPlace(double leftCompartment, double rightCompartment, double firstPoint, double secondPoint, List<Function> list, double functionAccuracy) {
        double fPoint = firstPoint;
        double sPoint = secondPoint;
        double valueOne;
        double valueTwo;
        double nPoint;
        double nValue;
        int counter = 0;

        valueOne = Menu.countValueOfFunctions(fPoint, list);
        valueTwo = Menu.countValueOfFunctions(secondPoint, list);

        if (Menu.countValueOfFunctions(leftCompartment, list) * Menu.countValueOfFunctions(rightCompartment, list) > 0) {
            throw new IllegalArgumentException("Brak miejsc zerowych");
        }

        while (true) {
            counter++;
            if ((valueOne - valueTwo) == 0) {
                throw new IllegalArgumentException("Dzielenie przez 0");
            }
            nPoint = (valueOne * sPoint - valueTwo * fPoint) / (valueOne - valueTwo);
            nValue = Menu.countValueOfFunctions(nPoint, list);
            if (Math.abs(nValue) < functionAccuracy) {
                System.out.println("Sieczne iteracje: " + counter);
                return nPoint;
            }
            fPoint = sPoint;
            sPoint = nPoint;
            valueOne = valueTwo;
            valueTwo = nValue;
        }
    }
}
