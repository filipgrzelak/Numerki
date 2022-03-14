import java.util.List;

public class Secant {

    public static double findZeroPlace(double firstPoint, double secondPoint, List<Function> list, int amountOfApproximations, double functionAccuracy) {
        double fPoint = firstPoint;
        double sPoint = secondPoint;
        double valueOne;
        double valueTwo;
        double nPoint;
        double nValue;
        int n = amountOfApproximations;

        valueOne = Menu.countValueOfFunctions(fPoint, list);
        valueTwo = Menu.countValueOfFunctions(secondPoint, list);

        while (n != 0) {
            n--;
            nPoint = (valueOne * sPoint - valueTwo * fPoint) / (valueOne - valueTwo);
            nValue = Menu.countValueOfFunctions(nPoint, list);
            if (Math.abs(nValue) < functionAccuracy) {
                return nPoint;
            }
            fPoint = sPoint;
            sPoint = nPoint;
            valueOne = valueTwo;
            valueTwo = nValue;
        }
        throw new IllegalArgumentException("Przekroczono liczbę aproksymacji");
    }
}
