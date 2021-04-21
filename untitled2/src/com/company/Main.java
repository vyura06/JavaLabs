package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LinkedList<LinkedList<String>> SOURCE = new LinkedList<>();

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


    private static void makeActionWidthFile(BiConsumer<File, LinkedList<String>> consumer, LinkedList<String> list) {
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
                    consumer.accept(file, list);
                } else {
                    System.out.println("File doesn't exist");
                }
            }
        } while (flag);
    }

    private static void writeInFile(File file, LinkedList<String> list) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(list.show());
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    private static void writeToConsole(LinkedList<String> list) {
        System.out.println(list.show());
    }

    private static void printList(LinkedList<String> list) {
        String line;
        String message = "Select when you want write data:\nTo console: " + ONE +
                "\nTo file: " + TWO +
                "\nExit: " + THREE;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                writeToConsole(list);
            } else if (TWO.equals(line)) {
                makeActionWidthFile(Main::writeInFile, list);
            } else if (THREE.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void deleteAllSameElements(LinkedList<String> list) {
        System.out.println("Enter line");
        list.removeAll(SCANNER.nextLine());


    }

    private static void deleteElement(LinkedList<?> list) {
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

    private static void readAll(File file, LinkedList<String> list) {
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

    private static void readFromConsole(LinkedList<String> list) {
        System.out.println("Enter line");
        list.add(SCANNER.nextLine());
    }

    private static void addElement(LinkedList<String> list) {
        String line;
        String message = "Select when you want read data:\nFrom console: " + ONE +
                "\nFrom file: " + TWO +
                "\nExit: " + THREE;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                readFromConsole(list);
            } else if (TWO.equals(line)) {
                makeActionWidthFile(Main::readAll, list);
            } else if (THREE.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void operations(LinkedList<String> list) {
        String line;
        String message = "Enter operation:\nAdd element: " + ONE +
                "\nDelete element by index: " + TWO +
                "\nDelete add same elements: " + THREE +
                "\nWrite data: " + FOUR +
                "\nExit: " + FIVE;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                addElement(list);
            } else if (TWO.equals(line)) {
                deleteElement(list);
            } else if (THREE.equals(line)) {
                deleteAllSameElements(list);
            } else if (FOUR.equals(line)) {
                printList(list);
            } else if (FIVE.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static LinkedList<String> getList() {
        LinkedList<String> result = null;
        String message = "Enter index";
        boolean flag = true;
        do {
            System.out.println(message);
            int index = getNumber();
            if (index < 0) {
                System.out.println("Index can be positive!");
            } else if (index >= SOURCE.size()) {
                System.out.println("Index out of bounds. Index = " + index + ", size = " + SOURCE.size());
            } else {
                result = SOURCE.get(index);
                flag = false;
            }
        } while (flag);
        return result;
    }

    private static void createNewList() {
        SOURCE.add(new LinkedList<>());
        System.out.println("Done");
    }

    private static void launch() {
        String line;
        String message = "Enter operation:\nCreate new list: " + ONE +
                "\nDelete list by index: " + TWO +
                "\nMake operations width list: " + THREE +
                "\nExit: " + FOUR;
        boolean flag = true;
        do {
            System.out.println(message);
            line = SCANNER.nextLine();
            if (ONE.equals(line)) {
                createNewList();
            } else if (TWO.equals(line)) {
                if (SOURCE.size() == 0) {
                    System.out.println("List is empty! Create new list");
                } else {
                    deleteElement(SOURCE);
                }
            } else if (THREE.equals(line)) {
                if (SOURCE.size() == 0) {
                    System.out.println("List is empty! Create new list");
                } else {
                    operations(getList());
                }
            } else if (FOUR.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    public static void main(String[] args) {
        launch();
    }
}