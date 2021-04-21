package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.BinaryTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        BinaryTree tree = new BinaryTree();

        group = new Group();
        Scene scene = new Scene(group, WIDTH, HEIGHT, Color.BLUE);

        displayMenu(tree);
        try {
            stage.setTitle("Шевелянчик Александр 951008");
            stage.setScene(scene);
            stage.show();
            tree.showOnScreenMyTree(400, 50);
        } catch (RuntimeException e) {
            showMessageIsTreeEmpty();
        }
    }

    private void showMessageIsTreeEmpty() {
        System.out.println("Ваше дерево пустое");
    }


    private static void displayMenu(BinaryTree tree) {
        System.out.println("Меню");
        System.out.println("1. Добавить элемент в дерево.");
        System.out.println("2. Считать данные из файла.");
        System.out.println("3. Удалить элемент из дерева.");
        System.out.println("4. Вывести дерево в следующем порядке: узел, левая ветвь, правая ветвь.");
        System.out.println("5. Построить пустое дерево.");
        System.out.println("6. Показать визуализацию дерева и выйти из программы.");
        System.out.print("Выберите ваше действие :");
        String userSelection = inputUserSelection();
        checkUserSelection(userSelection, tree);
    }


    private static String inputUserSelection() {
        Scanner scan = new Scanner(System.in);
        boolean isNotCorrectSelection;
        String selection;
        do {
            selection = scan.nextLine();
            isNotCorrectSelection = isNotCorrectSelect(selection);
        } while (isNotCorrectSelection);
        return selection;
    }


    private static boolean isNotCorrectSelect(String select) {
        boolean isNotCorrectSelect;
        if (select.equals("1") || select.equals("2") || select.equals("3") || select.equals("4") || select.equals("5") || select.equals("6")) {
            isNotCorrectSelect = false;
        } else {
            isNotCorrectSelect = true;
            System.out.println("Неправильный выбор действия! Попробуйте еще раз.");
        }
        return isNotCorrectSelect;
    }


    private static void checkUserSelection(String selection, BinaryTree tree) {
        switch (selection.charAt(0)) {
            case '1':
                System.out.println("\r\n\r\n");
                System.out.println("Добавьте элемент в дерево");
                addElementInTree(tree);
                displayMenu(tree);
                break;
            case '2':
                System.out.println("\r\n\r\n");
                inputFile(tree);
                displayMenu(tree);
                break;
            case '3':
                System.out.println("\r\n\r\n");
                System.out.println("Введите элемент, который вы хотите удалить");
                removeElement(tree);
                displayMenu(tree);
                break;
            case '4':
                System.out.println("\r\n\r\n");
                tree.printRootLeftBranchRightBranch(tree.root);
                System.out.println("\r\n\r\n");
                displayMenu(tree);
                break;
            case '5':
                makeAnEmptyTree();
                displayMenu(tree);
                break;
            case '6':
                break;
        }
    }


    private static void addElementInTree(BinaryTree tree) {
        int element;
        element = inputElement();
        tree.add(element);
    }

    private static int inputElement() {
        Scanner scan = new Scanner(System.in);
        int element;
        String elementStr;
        boolean isNotCorrect;
        do {
            elementStr = scan.nextLine();
            isNotCorrect = isNotCorrectInput(elementStr);
        } while (isNotCorrect);
        try {
            element = Integer.parseInt(elementStr);
        } catch (NumberFormatException e) {
            showNumberFormatException();
            element = 0;
        }
        return element;
    }

    private static void showError() {
        System.out.println("Вы некорректно ввели элемент. Попробуйте еще раз.");
    }

    private static void showNumberFormatException() {
        System.out.println("Введенное число слишком большое, поэтому оно будет заменено на 0.\r\n");
    }

    private static boolean isNotCorrectInput(String elementStr) {
        boolean isNotCorrect;
        if (elementStr.isEmpty()) {
            isNotCorrect = true;
            showError();
        } else {
            if (elementStr.charAt(0) == '-' || (elementStr.charAt(0) > '0' - 1 && elementStr.charAt(0) < '9' + 1)) {
                isNotCorrect = false;
            } else {
                isNotCorrect = true;
                showError();
            }
            if (!isNotCorrect) {
                for (int i = 1; i < elementStr.length(); i++) {
                    if (elementStr.charAt(i) < '0' || elementStr.charAt(i) > '9') {
                        isNotCorrect = true;
                        showError();
                        i = elementStr.length();
                    }
                }
            }
        }
        return isNotCorrect;
    }


    private static void removeElement(BinaryTree tree) {
        int element;
        element = inputElement();
        tree.delete(element);
    }

    private static void makeAnEmptyTree() {
        Tree root;
        root = null;
        System.out.println("\r\n" + root + "\r\n");
    }


    private static void inputFile(BinaryTree tree) {
        BufferedReader input;
        input = readBuffer();
        readStr(input, tree);
    }


    private static BufferedReader readBuffer() {
        boolean isNotCorrect;
        String fileAddress;
        BufferedReader inputFile = null;
        Scanner scan = new Scanner(System.in);
        do {
            isNotCorrect = false;
            System.out.print("Пожалуйста, введите адрес вашего файла :");
            fileAddress = scan.nextLine();
            try {
                BufferedReader input = new BufferedReader(new FileReader(fileAddress));
                inputFile = input;
            } catch (FileNotFoundException e) {
                showFileNotFound();
                isNotCorrect = true;
            }
        } while (isNotCorrect);
        return inputFile;
    }


    private static void readStr(BufferedReader inputFile, BinaryTree tree) {
        boolean isNotCorrect = false;
        Scanner in = new Scanner(inputFile);
        int element = 0;
        String inputStr;
        while (in.hasNextLine() && !isNotCorrect) {
            inputStr = in.nextLine();
            if (!inputStr.equals("")) {
                String[] subStr = inputStr.split(" ");
                for (int i = 0; i < subStr.length; i++) {
                    try {
                        element = Integer.parseInt(subStr[i]);
                    } catch (InputMismatchException | NumberFormatException e) {
                        showMistake();
                        i = subStr.length;
                        isNotCorrect = true;
                    } finally {
                        if (!isNotCorrect) {
                            tree.add(element);
                        }
                    }
                }
            } else {
                showMistake();
            }
        }
    }


    private static void showFileNotFound() {
        System.out.println("\r\nФайла с таким именем не существует.\r\nПожалуйста, проверьте имя файла.\r\n");
    }


    private static void showMistake() {
        System.out.println("\r\nДанные в файле некорректно записаны(возможно использованы слишком большие числа). Пожалуйста, попробуйте еще раз.");
    }

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static Group group;
}


