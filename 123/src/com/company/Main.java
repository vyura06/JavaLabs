package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.StrictMath.random;

public class Main {

    static int max = Integer.MIN_VALUE;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        outputCondition();
        int[][] array = input();
        int x1 = getX(array[0].length, "Enter coordinate x1: ");
        int y1 = getY(array.length, "Enter coordinate y1: ");
        int x2 = getX(array[0].length, "Enter coordinate x2: ");
        int y2 = getY(array.length, "Enter coordinate y2: ");
        move(0, x1, y1, x2, y2, array);
        String res = result( x1, y1, x2, y2, array);
        output(res);
    }

    private static String result(int x1, int y1, int x2, int y2, int[][] array) {
        String result;
        if (array.length == 0) {
            result = "Your matrix does not contain numbers. ";
        } else {
            result = "Required set is: \n";
            for (int i = 0; i < array.length; i++) {
                result += Arrays.toString(array[i]) + "\n";
            }
            result += "Max value is: " + max;
            result += "Coordinate x1: " + x1 + "\n";
            result += "Coordinate y1: " + y1 + "\n";
            result += "Coordinate x2: " + x2 + "\n";
            result += "Coordinate y1: " + y2;
        }
        return result;
    }

    static void outputCondition() {
        System.out.println("This program does the following in this matrix: ");
        System.out.println("1) Introduces the matrix and its dimensions. ");
        System.out.println("2) Randomly fills it with numbers. ");
        System.out.println("3) Finds out which element to start the search from. ");
        System.out.println("4) Searches for the maximum amount. ");
        System.out.println("5) Displays the result. ");
    }

    static int[][] input() {
        String choice;
        boolean isNotCorrect = true;
        System.out.println("Please, press F if you want to use the file. ");
        System.out.println("Please, press C if you want to use the console. ");
        int[][] array = {};
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            if (choice.equals("C")) {
                array = console();
                isNotCorrect = false;
            } else if (choice.equals("F")) {
                array = readFromFile();
                isNotCorrect = false;
            } else {
                System.out.println("You need to enter only F or C. Please, try again. ");
            }
        } while (isNotCorrect);
        return array;
    }

    static void output(String result) {
        String choice;
        boolean isNotCorrect = true;
        System.out.println("Please, press F if you want to use the file. ");
        System.out.println("Please, press C if you want to use the console. ");
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            if (choice.equals("C")) {
                outputConsole(result);
                isNotCorrect = false;
            } else if (choice.equals("F")) {
                outputFile(result);
                isNotCorrect = false;
            } else {
                System.out.println("You need to enter only F or C. Please, try again. ");
            }
        } while (isNotCorrect);
    }

    static int[][] readFromFile() {
        String fileAddress;
        int[][] array = {};
        boolean isNotCorrect = true;
        do {
            System.out.println("\nPlease, put in your file the sequence of chars. ");
            System.out.print("Please, enter the address of your input-file : ");
            fileAddress = scanner.next();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileAddress));
                List<String> lines = new ArrayList<>();
                while (reader.ready()) {
                    lines.add(reader.readLine());
                }
                int matrixWidth = lines.get(0).split(" ").length;
                int matrixHeight = lines.size();
                array = new int[matrixHeight][matrixWidth];
                for (int i = 0; i < matrixHeight; i++) {
                    for (int j = 0; j < matrixWidth; j++) {
                        String[] line = lines.get(i).split(" ");
                        array[i][j] = Integer.parseInt(line[j]);
                    }
                }
                isNotCorrect = false;
            } catch (IOException e) {
                System.out.println("\nFile with this name is not exists. " +
                        "Please, check the name.");
            }
        } while (isNotCorrect);
        return array;
    }

    private static int[][] console() {
        System.out.println("Enter number m: ");
        int m = getPositiveNumber();
        System.out.println("Enter number n: ");
        int n = getPositiveNumber();
        int[][] array = new int[n][m];
        fill(array);
        return array;
    }

    private static void fill(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) (random() * 20) - 10;
            }
        }
    }

    private static int getX(int m, String message) {
        int result;
        boolean flag = true;
        do {
            System.out.println(message);
            result = getPositiveNumber();
            if (result < m) {
                flag = false;
            } else {
                System.out.println("Going beyond the bounds of an array. Try again. ");
            }
        } while (flag);
        return result;
    }

    private static int getY(int n, String message) {
        int result;
        boolean flag = true;
        do {
            System.out.println(message);
            result = getPositiveNumber();
            if (result < n) {
                flag = false;
            } else {
                System.out.println("Going beyond the bounds of an array. Try again. ");
            }
        } while (flag);
        return result;
    }

    private static int getPositiveNumber() {
        int result;
        do {
            System.out.println("Numbers must be greater than zero! ");
            while (!scanner.hasNextInt()) {
                System.out.println("ERROR!");
                scanner.next();
            }
            result = scanner.nextInt();
        } while (result < 0);
        return result;
    }

    static void move(int current, int x, int y, int x2, int y2, int[][] array) {
        current += array[y][x];
        if (x < x2) {
            move(current, x + 1, y, x2, y2, array);
        }
        if (y < y2) {
            move(current, x, y + 1, x2, y2, array);
        }
        if (x == x2 && y == y2) {
            if (max < current) {
                max = current;
            }
        }
    }

    static void outputConsole(String result) {
        System.out.println(result);
    }

    static void outputFile(String array) {
        String fileAddress;
        boolean isNotCorrect = false;
        do {
            System.out.print("Please, enter the address of your output file: ");
            fileAddress = scanner.nextLine();
            try {
                FileWriter output = new FileWriter(fileAddress);
                output.write(array);
                output.close();
            } catch (FileNotFoundException e) {
                isNotCorrect = true;
                System.out.println("File with this name is not exists. " +
                        "Please, check the name. ");
            } catch (IOException e) {
                System.out.println("Can not open this file. Please, try again. ");
                isNotCorrect = true;
            }
        } while (isNotCorrect);
    }
}
