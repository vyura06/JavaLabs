package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String path = "D:/101.txt";
        razdel();
        Scanner scanner = new Scanner(new FileInputStream(path));
        ArrayList<String> strings = new ArrayList<String>();
        while (scanner.hasNextLine())
            strings.add(scanner.nextLine());

        double[][] matrix = rowsToMatrix(strings);
        for (double[] r : matrix) {
            System.out.println(Arrays.toString(r));
            System.out.println();
        }
    }

    private static double[][] rowsToMatrix(ArrayList<String> rows) {
        double[][] matrix = new double[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            Scanner stringScanner = new Scanner(rows.get(i));
            ArrayList<Double> doubleList = new ArrayList<Double>();
            readDoubles(stringScanner, doubleList);
            matrix[i] = convert(doubleList);
        }
        return matrix;
    }

    private static void readDoubles(Scanner scanner, List<Double> doubleList) {
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                doubleList.add(scanner.nextDouble());
            } else {
                scanner.next();
            }
        }
    }

    private static double[] convert(ArrayList<Double> doubleList) {
        double[] row = new double[doubleList.size()];
        for (int i = 0; i < doubleList.size(); i++)
            row[i] = doubleList.get(i);
        return row;
    }

    public static void razdel(){
        Scanner scanner = new Scanner(new FileInputStream());
        ArrayList<Double> arrayList = new ArrayList<Double>();
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                arrayList.add(scanner.nextDouble());
            } else {
                scanner.next();
            }
        }
        int y = 10;
        int x = 10;
        double[][] matrix = new double[y][x];
        int prev = 0;
        int next = 0;
        for (int i = 0; i < y; i++) {
            next = prev + x;
            List<Double> sublistOne = arrayList.subList(prev, next);
            prev = next;
            for (int j = 0; j < sublistOne.size(); j++)
                matrix[i][j] = sublistOne.get(j);
        }
    }

}
