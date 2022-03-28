package zad2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SystemOfEquations soe = new SystemOfEquations("test2.txt");
        soe.algorithm();
        soe.printEquations();
        System.out.println(Arrays.asList(soe.getTab()));
    }
}
