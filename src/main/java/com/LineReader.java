package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader {

    public static List<String> readLinesFromFile(String filename) {
        List<String> result = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filename))) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
