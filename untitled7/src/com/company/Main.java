package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static LinkedList<String> list = null;
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String FIVE = "5";

    private static int getNumber() {
        while (!SCANNER.hasNextInt()) {
            System.out.println("Error");
            SCANNER.next();
        }
        int result = SCANNER.nextInt();
        SCANNER.nextLine();
        return result;
    }

    private static void makeActionWidthFile(Consumer<File> consumer) {
        File file;
        String line;
        String message = "Enter path to file or enter " + ONE + " to exit";
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                flag = false;
            } else {
                file = new File(line);
                if (file.exists()) {
                    consumer.accept(file);
                } else {
                    System.out.println("File doesn't exist");
                }
            }
        } while (flag);
    }

    private static String select() {
        String result = null;
        String line;
        String message = "Select how print list:\nNormal: " + ONE +
                "\nVice versa: " + TWO;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                result = list.normal();
                flag = false;
            } else if (TWO.equals(line)) {
                result = list.viseVersa();
                flag = false;
            } else {
                System.out.println("Incorrect input");
            }
        } while (flag);
        return result;
    }

    private static void writeInFile(File file) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(select());
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    private static void writeToConsole() {
        System.out.println(select());
    }

    private static void printList() {
        String line;
        String message = "Select when you want write data:\nTo console: " + ONE +
                "\nTo file: " + TWO +
                "\nExit: " + THREE;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                writeToConsole();
            } else if (TWO.equals(line)) {
                makeActionWidthFile(Main::writeInFile);
            } else if (THREE.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void deleteElement() {
        String message = "Enter index";
        boolean flag = true;
        do {
            System.out.println(message);
            int index = getNumber();
            if (index < 0) {
                System.out.println("Index can be positive!");
            } else if (index >= list.size()) {
                System.out.println("Index out of bounds. Index = " + index + ", size = " + list.size());
            } else {
                list.remove(index);
                flag = false;
            }
        } while (flag);
    }

    private static void readAll(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            System.out.println("Done");
        } catch (IOException e) {
            System.out.println("IOE");
        }
    }

    private static void readFromConsole() {
        System.out.println("Enter line");
        list.add(SCANNER.nextLine());
    }

    private static void addElement() {
        String line;
        String message = "Select when you want read data:\nFrom console: " + ONE +
                "\nFrom file: " + TWO +
                "\nExit: " + THREE;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                readFromConsole();
            } else if (TWO.equals(line)) {
                makeActionWidthFile(Main::readAll);
            } else if (THREE.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void createNewList() {
        list = new LinkedList<>();
        System.out.println("Done");
    }

    private static void launch() {
        String line;
        String message = "Enter operation:\nCreate new list: " + ONE +
                "\nAdd element: " + TWO +
                "\nDelete element by index: " + THREE +
                "\nWrite data: " + FOUR +
                "\nExit: " + FIVE;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                createNewList();
            } else if (TWO.equals(line)) {
                if (list == null) {
                    System.out.println("List is null. Create new List");
                } else {
                    addElement();
                }
            } else if (THREE.equals(line)) {
                if (list == null) {
                    System.out.println("List is null. Create new List");
                } else {
                    deleteElement();
                }
            } else if (FOUR.equals(line)) {
                if (list == null) {
                    System.out.println("List is null. Create new List");
                } else {
                    printList();
                }
            } else if (FIVE.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    public static void main(String[] args) {
        launch();
    }
}