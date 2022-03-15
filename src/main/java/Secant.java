import java.util.List;

public class Secant {

    public static double findZeroPlace(double firstPoint, double secondPoint, List<Function> list, int amountOfApproximations) {
        double fPoint = firstPoint;
        double sPoint = secondPoint;
        double valueOne;
        double valueTwo;
        double nPoint = 0.0;
        double nValue;
        int n = 0;

        valueOne = Menu.countValueOfFunctions(fPoint, list);
        valueTwo = Menu.countValueOfFunctions(secondPoint, list);

        if ((valueOne - valueTwo) == 0) {
            throw new IllegalArgumentException("Dzielenie przez 0");
        }

        while (n < amountOfApproximations) {
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

    public static double findZeroPlace(double firstPoint, double secondPoint, List<Function> list, double functionAccuracy) {
        double fPoint = firstPoint;
        double sPoint = secondPoint;
        double valueOne;
        double valueTwo;
        double nPoint;
        double nValue;

        valueOne = Menu.countValueOfFunctions(fPoint, list);
        valueTwo = Menu.countValueOfFunctions(secondPoint, list);

        if ((valueOne - valueTwo) == 0) {
            throw new IllegalArgumentException("Dzielenie przez 0");
        }

        while (true) {
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
    }
}
