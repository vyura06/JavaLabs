package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static final Scanner in = new Scanner(System.in);
    public static final String LAST_NAME_OF_THE_TEACHER = "Enter surname of the teacher:";
    public static final String NUMBER_OF_THE_DAY = "Enter number of the day:";
    static ArrayList<Teacher> teachers = new ArrayList<>();

    public static void main(String[] args) {
        input();
        process();
        output();
        in.close();
    }

    public static void input() {
        String number_input;
        String message = "INPUT\n" + "How do you want to enter the records?\n" +
                "1. Keyboard input\n" + "2. Reading data from a file";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_input = in.nextLine();
            if (number_input.equals("1")) {
                inputConsole();
                isNotCorrect = false;
            }
            if (number_input.equals("2")) {
                inputFile();
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static void inputConsole() {
        String check;
        do {
            Teacher teacher = new Teacher();
            teacher.inizialize();
            fillTeachers(teacher);
            teachers.add(teacher);
            System.out.println("Do you want to continue to enter data? (\"yes\" or \"no\")");
            check = in.nextLine();
        } while (!check.equals("no"));
    }

    public static void fillTeachers(Teacher teacher) {
        System.out.println("Enter teacher: ");
        System.out.println(LAST_NAME_OF_THE_TEACHER);
        enterTeacherSurname(teacher);
        System.out.println(NUMBER_OF_THE_DAY);
        enterDay(teacher);
    }

    public static int enterNumber() {
        String number;
        boolean isNotCorrect = true;
        do {
            number = in.nextLine();
            if (isPositiveDigit(number)) {
                isNotCorrect = false;
            } else {
                System.err.println("ERROR!!!");
            }
        } while (isNotCorrect);
        return Integer.parseInt(number);
    }

    public static void enterTeacherSurname(Teacher teacher) {
        do {
            teacher.teacherSurname = in.nextLine();
            if (teacher.teacherSurname.equals("")) {
                System.err.println("ERROR!!!");
            }
        } while (teacher.teacherSurname.equals(""));
    }

    public static void enterDay(Teacher teacher) {
        for (int i = 0; i < teacher.days.length; i++) {
            teacher.days[i].lessonNumber = enterNumber();
            teacher.days[i].className = in.nextLine();
        }
    }

    private static boolean isPositiveDigit(String str) {
        boolean isCorrect;
        try {
            isCorrect = Integer.parseInt(str) >= 0;
        } catch (NumberFormatException e) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static void inputFile() {
        String pathname;
        File file_input;
        boolean isNotCorrect = true;
        do {
            System.out.println("Enter path to the file (input):");
            pathname = in.nextLine();
            file_input = new File(pathname);
            if (file_input.exists()) {
                System.out.println("File Found!");
                readFile(file_input);
                if (teachers.size() < 1) {
                    System.out.println("File is not correct!");
                } else {
                    isNotCorrect = false;
                }
            } else {
                System.out.println("File not found");
            }
        } while (isNotCorrect);
    }

    private static void readFile(File file_input) {
        String line;
        try (Scanner scanner = new Scanner(new FileInputStream(file_input))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (!line.equals("")) {
                    fillRecord(line);
                }
            }
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        }
    }

    private static void fillRecord(String line) {
        String[] array = line.split("\\s");
        if (array.length == 13 && isPositiveDigit(array[1]) && isPositiveDigit(array[3]) && isPositiveDigit(array[5]) && isPositiveDigit(array[7]) && isPositiveDigit(array[9]) && isPositiveDigit(array[11])) {
            fillRecordFromFile(array);
        }
    }

    public static void fillRecordFromFile(String[] array) {
        Teacher teacher = new Teacher();
        teacher.inizialize();
        teacher.teacherSurname = array[0];
        teacher.days[0].lessonNumber = Integer.parseInt(array[1]);
        teacher.days[0].className = array[2];
        teacher.days[1].lessonNumber = Integer.parseInt(array[3]);
        teacher.days[1].className = array[4];
        teacher.days[2].lessonNumber = Integer.parseInt(array[5]);
        teacher.days[2].className = array[6];
        teacher.days[3].lessonNumber = Integer.parseInt(array[7]);
        teacher.days[3].className = array[8];
        teacher.days[4].lessonNumber = Integer.parseInt(array[9]);
        teacher.days[4].className = array[10];
        teacher.days[5].lessonNumber = Integer.parseInt(array[11]);
        teacher.days[5].className = array[12];
        teachers.add(teacher);
    }

    public static void process() {
        String number_process;
        String message = "PROCESSING\n" +
                "1. Delete selected records\n" + "2. Change selected records\n" +
                "3. Add records\n" + "0. Exit";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_process = in.nextLine();
            if (number_process.equals("1")) {
                deleteRecords();
            }
            if (number_process.equals("2")) {
                changeRecords();
            }
            if (number_process.equals("3")) {
                addRecords();
            }
            if (number_process.equals("0")) {
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static void deleteRecords() {
        String number_delete;
        String message = "DELETE\n" + "How do you want to delete the record?\n" +
                "1. By surname teacher\n" + "2. By number of day\n" +
                "3. By records that are in the file\n" + "0. Exit";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_delete = in.nextLine();
            if (number_delete.equals("1")) {
                deleteBySurName();
            }
            if (number_delete.equals("0")) {
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }
    public static void deleteBySurName() {
        String last_name;
        boolean isNotCorrect = true;
        do {
            System.out.println("Enter the surname for which you want to delete the record:");
            last_name = in.nextLine();
            for (int i = 0; i < teachers.size(); i++) {
                if (teachers.get(i).teacherSurname.equals(last_name)) {
                    teachers.remove(teachers.get(i));
                    isNotCorrect = false;
                }
            }
            isNotCorrect = isExit(isNotCorrect);
        } while (isNotCorrect);
    }

    public static void changeRecords() {
        String number_change;
        String message = "CHANGE\n" + "How do you want to search the record?\n" +
                "1. By surname teacher\n" + "2. By number of day\n" + "0. Exit";
        boolean isNotCorrect = true;
        int indexOfRecord;
        do {
            System.out.println(message);
            number_change = in.nextLine();
            if (number_change.equals("1")) {
                indexOfRecord = searchIndexBySurName();
                changeValue(indexOfRecord);
            }
            if (number_change.equals("0")) {
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static int searchIndexBySurName() {
        String surname;
        int indexOfRecord = 0;
        boolean isNotCorrect = true;
        do {
            System.out.println("Enter surname for which you want to search the record:");
            surname = in.nextLine();
            for (int i = 0; i < teachers.size(); i++) {
                if (teachers.get(i).teacherSurname.equals(surname)) {
                    indexOfRecord = i;
                    isNotCorrect = false;
                }
            }
            isNotCorrect = isExit(isNotCorrect);
        } while (isNotCorrect);
        return indexOfRecord;
    }

    public static boolean isExit(boolean isNotCorrect) {
        String check;
        String message = "Record not found!\n" + "If you want to exit - enter \"STOP\"\n"
                + "If not - enter something";
        if (isNotCorrect) {
            System.err.println(message);
            check = in.nextLine();
            if (check.equals("STOP")) {
                isNotCorrect = false;
            }
        } else {
            System.out.println("Record found!");
        }
        return isNotCorrect;
    }

    public static void changeValue(int index) {
        String number_change;
        String message = "CHANGE\n" + "What do you want to change?\n" +
                "1. Surname of the teacher\n" + "2. Number of the day\n" +
                 "3. All fields\n" + "0. Exit";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_change = in.nextLine();
            if (number_change.equals("1")) {
                System.out.println(LAST_NAME_OF_THE_TEACHER);
                enterTeacherSurname(teachers.get(index));
            }
            if (number_change.equals("2")) {
                System.out.println(NUMBER_OF_THE_DAY);
                enterDay(teachers.get(index));
            }
            if (number_change.equals("3")) {
                fillTeachers(teachers.get(index));
            }
            if (number_change.equals("0")) {
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static void addRecords() {
        String number_add;
        String message = "ADD\n" + "How do you want to add the record?\n" +
                "1. Keyboard\n" + "2. File\n" + "0. Exit";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_add = in.nextLine();
            if (number_add.equals("1")) {
                inputConsole();
            }
            if (number_add.equals("2")) {
                inputFile();
            }
            if (number_add.equals("0")) {
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static void output() {
        String number_output;
        String message = "OUTPUT\n" + "How do you want to print the records?\n" +
                "1. Output to the console\n" + "2. Writing data to a file";
        boolean isNotCorrect = true;
        do {
            System.out.println(message);
            number_output = in.nextLine();
            if (number_output.equals("1")) {
                outputConsole();
                isNotCorrect = false;
            }
            if (number_output.equals("2")) {
                outputFile();
                isNotCorrect = false;
            }
        } while (isNotCorrect);
    }

    public static void outputConsole() {
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(teachers.get(i).toString());
        }
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
            for (int i = 0; i < teachers.size(); i++) {
                printwriter.println(teachers.get(i).toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
