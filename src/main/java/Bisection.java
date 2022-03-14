import java.util.List;

public class Bisection {

    public static double findZeroPlace(List<Function> list, double leftCompartment, double rightCompartment, double functionAccuracy) {
        double lCompartment = leftCompartment;
        double rCompartment = rightCompartment;
        double valueInTheLeftCompartment;
        double valueInTheRightCompartment;
        double middleOfCompartment;

        valueInTheLeftCompartment = Menu.countValueOfFunctions(lCompartment, list);
        valueInTheRightCompartment = Menu.countValueOfFunctions(rCompartment, list);
        if (valueInTheLeftCompartment * valueInTheRightCompartment > 0) {
            throw new IllegalArgumentException("Brak miejsc zerowych");
        }
        while (true) {
            middleOfCompartment = (rCompartment + lCompartment) / 2;
            if (Math.abs(Menu.countValueOfFunctions(middleOfCompartment, list)) < functionAccuracy) {
                return middleOfCompartment;
            }
            if (Menu.countValueOfFunctions(middleOfCompartment, list) * Menu.countValueOfFunctions(lCompartment, list) < 0) {
                rCompartment = middleOfCompartment;
            } else {
                lCompartment = middleOfCompartment;
            }
        }
    }

    public static double findZeroPlace(List<Function> list, double leftCompartment, double rightCompartment, int numberOfOperation) {
        double lCompartment = leftCompartment;
        double rCompartment = rightCompartment;
        double valueInTheLeftCompartment;
        double valueInTheRightCompartment;
        double middleOfCompartment = 0.0;
        int counter = 0;

        valueInTheLeftCompartment = Menu.countValueOfFunctions(lCompartment, list);
        valueInTheRightCompartment = Menu.countValueOfFunctions(rCompartment, list);
        if (valueInTheLeftCompartment * valueInTheRightCompartment > 0) {
            throw new IllegalArgumentException("Brak miejsc zerowych");
        }
        while (counter < numberOfOperation) {
            middleOfCompartment = (rCompartment + lCompartment) / 2;

            if (Menu.countValueOfFunctions(middleOfCompartment, list) * Menu.countValueOfFunctions(lCompartment, list) < 0) {
                rCompartment = middleOfCompartment;
            } else {
                lCompartment = middleOfCompartment;
            }
            counter++;
        }
        return middleOfCompartment;
    }
}
