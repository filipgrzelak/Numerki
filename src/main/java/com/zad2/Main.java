package com.zad2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SystemOfEquations soe = new SystemOfEquations("zajecia.txt");
        soe.algorithm();
        System.out.println(Arrays.asList(soe.getTab()));
    }
}
