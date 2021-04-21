package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.util.Scanner;

public class Controller {

    static Tree<Integer> tree = new Tree<>(Integer::compareTo);
    static final Scanner in = new Scanner(System.in);

    @FXML
    private Canvas canvas;

    @FXML
    void initialize() {
        System.out.println((19 % 16) + 1);
        GraphicsContext context = canvas.getGraphicsContext2D();
        input();
        output();
        drawTree(context, tree.root);
        in.close();
    }

    public static void input() {
        String number_input;
        String message = "INPUT\n" + "How do you want to enter the data?\n" +
                "1. Keyboard input\n" + "2. Reading data from a file\n" +
                "0. Exit";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_input = in.nextLine();
            if (number_input.equals("1")) {
                inputConsole();
            }
            if (number_input.equals("2")) {
                inputFile();
            }
            if (number_input.equals("0")) {
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static void inputConsole() {
        String check;
        String message = "Enter number(10 < number < 100): ";
        do {
            System.out.println(message);
            enterNumber();
            System.out.println("If you want to stop enter \"STOP\", if you want to continue enter something:");
            check = in.nextLine();
        } while (!check.equals("STOP"));
    }

    public static void enterNumber() {
        String line;
        boolean isNotCorrect = true;
        do {
            line = in.nextLine();
            if (isCorrectDigit(line)) {
                isNotCorrect = false;
            } else {
                System.err.println("Error!");
            }
        } while (isNotCorrect);
        tree.add(Integer.parseInt(line));
    }

    private static boolean isCorrectDigit(String str) {
        boolean isCorrect;
        try {
            isCorrect = Integer.parseInt(str) >= -9 && Integer.parseInt(str) < 100;
        } catch (NumberFormatException e) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static String enterLine() {
        String line;
        do {
            line = in.nextLine();
            if (line.equals("")) {
                System.err.println("Error!");
            }
        } while (line.equals(""));
        return line;
    }

    public static void inputFile() {
        String pathname;
        File file_input;
        boolean isNotCorrect = true;
        do {
            System.out.println("Enter path to the file (input):");
            pathname = enterLine();
            file_input = new File(pathname);
            if (file_input.exists()) {
                System.out.println("File Found!");
                readFile(file_input);
                if (tree.size < 1) {
                    System.err.println("File is not correct!");
                } else {
                    isNotCorrect = false;
                }
            } else {
                System.err.println("File not found");
            }
        } while (isNotCorrect);
    }

    public static void readFile(File file_input) {
        int element;
        try (Scanner scanner = new Scanner(new FileInputStream(file_input))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    element = scanner.nextInt();
                    tree.add(element);
                } else {
                    scanner.next();
                }
            }
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        }
    }

    public static void output() {
        String number_output;
        String message = "OUTPUT\n" + "How do you want to print list?\n" +
                "1. Output to the console\n" + "2. Writing data to a file\n" +
                "3. Task\n" + "0. Exit";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_output = in.nextLine();
            if (number_output.equals("1")) {
                outputConsole();
            }
            if (number_output.equals("2")) {
                outputFile();
            }
            if (number_output.equals("3")) {
                printTask();
            }
            if (number_output.equals("0")) {
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static void outputConsole() {
        System.out.print(tree);
    }

    public static void outputFile() {
        File file_output;
        boolean isNotCorrect = true;
        do {
            System.out.println("Enter path to the file (output):");
            file_output = new File(in.nextLine());
            if (file_output.exists()) {
                System.out.println("File Found!");
                isNotCorrect = false;
            } else {
                System.out.println("File not found");
            }
        } while (isNotCorrect);
        writeInFile(file_output);
    }

    public static void writeInFile(File file_output) {
        try (PrintWriter printwriter = new PrintWriter(file_output)) {
            printwriter.print(tree);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printTask() {
        Node<Integer> node;
        String line;
        boolean isNotCorrect = true;
        String message = "Enter the number by which you want to find the tree element: ";
        do {
            System.out.println(message);
            line = enterLine();
            if (isCorrectDigit(line)) {
                node = tree.findNode(Integer.parseInt(line));
                if (node != null) {
                    System.out.println("High of binary tree: " + tree.heightOfBinaryTree(node));
                    isNotCorrect = false;
                } else {
                    System.out.println("Element not found!");
                }
            }
        } while (isNotCorrect);
    }

    public static void drawTree(GraphicsContext context, Node<Integer> starNode) {
        if (starNode != null) {
            drawTree(context, starNode.rightChild);
            if (tree.heightOfBinaryTreeLeftRight(starNode)) {
                context.fillText("(" + starNode.value.toString() +")", starNode.x, starNode.y);
            } else {
                context.fillText(starNode.value.toString(), starNode.x, starNode.y);
            }
            Node<Integer> node = tree.searchPrev(starNode);
            if (node != null) {
                context.strokeLine(node.x, node.y, starNode.x, starNode.y);
            }
            drawTree(context, starNode.leftChild);
        }
    }
}
