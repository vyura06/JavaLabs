package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Data> list = new ArrayList<>();

    private static final String one = "1";
    private static final String two = "2";
    private static final String three = "3";
    private static final String four = "4";
    private static final String five = "5";
    private static final String six = "6";

    private static void launch() {
        String message = "Enter operation:" +
                "\nConsole = " + one +
                "\nFile = " + two +
                "\nQuestion = " + three +
                "\nExit = " + four;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                console();
            }
            if (two.equals(line)) {
                file();
            }
            if (three.equals(line)) {
                question();
            }
            if (four.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void question() {
        enterToConsole();
    }

    private static void file() {
        String message = "Enter operation:" +
                "\nRead = " + one +
                "\nEdit = " + two +
                "\nRemove = " + three +
                "\nSave = " + four +
                "\nExit = " + five;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                readFromFile();
            }
            if (two.equals(line)) {
                editFromFile();
            }
            if (three.equals(line)) {
                removeFromFile();
            }
            if (four.equals(line)) {
                saveToFile();
            }
            if (five.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void saveToFile() {
        String message = "Enter path to the file:\nExit = " + one;
        File file;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                flag = false;
            } else {
                file = new File(line);
                if (file.exists()) {
                    printToFile(file);
                } else {
                    System.out.println("File doesn't exist");
                }
            }
        } while (flag);
    }

    private static void printToFile(File file) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println("Teacher surname | Lesson number | Day of the week | Class name");
            for (int i = 0; i < list.size(); i++) {
                printWriter.println(toString(list.get(i)));
            }
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeFromFile() {
        ArrayList<Data> arrayList = new ArrayList<>();
        fillList(arrayList);
        String message = "Enter operation:" +
                "\nTeacher surname = " + one +
                "\nLesson number = " + two +
                "\nDay of the week = " + three +
                "\nClass name = " + four +
                "\nAll params = " + five +
                "\nExit = " + six;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                removeByTeacherSurname(arrayList);
            }
            if (two.equals(line)) {
                removeByLessonNumber(arrayList);
            }
            if (three.equals(line)) {
                removeByDayOfTheWeek(arrayList);
            }
            if (four.equals(line)) {
                removeByClassName(arrayList);
            }
            if (five.equals(line)) {
                removeIfEquals(arrayList);
            }
            if (six.equals(line)) {
                flag = false;
            }
            if (flag) {
                System.out.println("Done");
            }
        } while (flag);
    }

    private static void removeIfEquals(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = indexOf(data);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void removeByClassName (ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByClassName(data.ClassName);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void  removeByDayOfTheWeek(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByDayOfTheWeek(data.DayOfTheWeek);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void  removeByLessonNumber(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByLessonNumber(data.LessonNumber);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void  removeByTeacherSurname(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByTeacherSurname(data.TeacherSurname);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void editFromFile() {
        ArrayList<Data> arrayList = new ArrayList<>();
        fillList(arrayList);
        String message = "Enter operation:" +
                "\nTeacher surname = " + one +
                "\nLesson number = " + two +
                "\nDay of the week = " + three +
                "\nClass name = " + four +
                "\nAll params = " + five +
                "\nExit = " + six;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                setByTeacherSurname(arrayList);
            }
            if (two.equals(line)) {
                setByLessonNumber(arrayList);
            }
            if (three.equals(line)) {
                setByDayOfTheWeek(arrayList);
            }
            if (four.equals(line)) {
                setByClassName(arrayList);
            }
            if (five.equals(line)) {
                setIfEquals(arrayList);
            }
            if (six.equals(line)) {
                flag = false;
            }
            if (flag) {
                System.out.println("Done");
            }
        } while (flag);
    }

    private static void setIfEquals(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = indexOf(data);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void setByClassName(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByClassName(data.ClassName);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void setByDayOfTheWeek(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByDayOfTheWeek(data.DayOfTheWeek);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void setByLessonNumber(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByLessonNumber(data.LessonNumber);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void setByTeacherSurname(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByTeacherSurname(data.TeacherSurname);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void readFromFile() {
        fillList(list);
    }

    private static void fillList(ArrayList<Data> dataArrayList) {
        String message = "Enter path to the file\nExit = " + one;
        File file;
        boolean flag = true;
        do {
            System.out.println(message);
            String string = scanner.nextLine();
            if (one.equals(string)) {
                flag = false;
            } else {
                file = new File(string);
                if (file.exists()) {
                    readAllFromFile(dataArrayList, file);
                } else {
                    System.out.println("File doesn't exist");
                }
            }
        } while (flag);
    }

    private static void readAllFromFile(ArrayList<Data> dataArrayList, File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String temp = bufferedReader.readLine();
            while (temp != null) {
                addElement(temp, dataArrayList);
                temp = bufferedReader.readLine();
            }
            System.out.println("Done");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void console() {
        String message = "Enter operation:" +
                "\nRead = " + one +
                "\nEdit = " + two +
                "\nRemove = " + three +
                "\nPrint = " + four +
                "\nExit = " + five;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                readFromConsole();
            }
            if (two.equals(line)) {
                editFromConsole();
            }
            if (three.equals(line)) {
                removeFromConsole();
            }
            if (four.equals(line)) {
                enterToConsole();
            }
            if (five.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void readFromConsole() {
        String message = "Enter operation:" +
                "\nBy size = " + one +
                "\nBy stop string = " + two +
                "\nExit = " + three;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                readFromSize();
            }
            if (two.equals(line)) {
                readFromStopString();
            }
            if (three.equals(line)) {
                flag = false;
            }
        } while (flag);
    }

    private static void readFromStopString() {
        System.out.println("Enter stop string");
        String stopString = scanner.nextLine();
        System.out.println("Enter lines");
        String line = scanner.nextLine();
        while (!line.equals(stopString)) {
            addElement(line, list);
            line = scanner.nextLine();
        }
    }

    private static void readFromSize() {
        int size = getPositiveNumber();
        scanner.nextLine();
        System.out.println("Enter lines");
        for (int i = 0; i < size; i++) {
            addElement(scanner.nextLine(), list);
        }
    }

    private static void editFromConsole() {
        String message = "Enter operation:" +
                "\nTeacher surname = " + one +
                "\nLesson number = " + two +
                "\nDay of the week = " + three +
                "\nClass name = " + four +
                "\nExit = " + five;
        Data data;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (five.equals(line)) {
                flag = false;
            } else {
                data = searchData();
                if (one.equals(line)) {
                    setTeacherSurname(data);
                }
                if (two.equals(line)) {
                    setLessonNumber(data);
                }
                if (three.equals(line)) {
                    setDayOfTheWeek(data);
                }
                if (four.equals(line)) {
                    setClassName(data);
                }
            }
        } while (flag);
    }

    private static void setClassName(Data data) {
        System.out.println("Enter new Class name");
        data.ClassName = scanner.nextLine();
    }

    private static void setDayOfTheWeek(Data data) {
        System.out.println("Enter new Day of the week");
        data.DayOfTheWeek = getPositiveNumber();
        scanner.nextLine();
    }

    private static void setLessonNumber(Data data) {
        System.out.println("Enter new Lesson number");
        data.LessonNumber = getPositiveNumber();
        scanner.nextLine();
    }

    private static void setTeacherSurname(Data data) {
        System.out.println("Enter new Teacher surname");
        data.TeacherSurname = scanner.nextLine();
    }

    private static void removeFromConsole() {
        Data data = searchData();
        list.remove(data);
    }

    private static void enterToConsole() {
        System.out.println("Teacher surname | Lesson number | Day of the week | Class name");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(toString(list.get(i)));
        }
    }

    private static int getPositiveNumber() {
        int result;
        do {
            System.out.println("Enter positive number");
            while (!scanner.hasNextInt()) {
                System.out.println("Error");
                scanner.next();
            }
            result = scanner.nextInt();
        } while (result < 0);
        return result;
    }

    private static String getString(String[] strings, int index) {
        String result;
        if (strings != null && index < strings.length) {
            result = strings[index];
        } else {
            result = "undefined";
        }
        return result;
    }

    private static int parse(String string) {
        int result = 0;
        try {
            result = Integer.parseInt(string);
        } catch (NumberFormatException ignored) {
        }
        return result;
    }

    private static void addElement(String string, ArrayList<Data> arrayList) {
        String[] strings = string.split(" ");
        Data data = new Data();
        data.TeacherSurname = scanner.nextLine();
        for (int i = 0; i < data.days.length; i++) {
            System.out.println("Enter LessonNumber");
            data.days[i].LessonNumber = enterNumber();
            System.out.println("Enter ClassName");
            data.days[i].ClassName = scanner.nextLine();
        }
        arrayList.add(data);
    }
    public static int enterNumber() {
        String number_of_items;
        boolean isNotCorrect = true;
        do {
            number_of_items = scanner.nextLine();
            if (isPositiveDigit(number_of_items)) {
                isNotCorrect = false;
            } else {
                System.err.println("Error!");
            }
        } while (isNotCorrect);
        return Integer.parseInt(number_of_items);
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

    private static Data searchData() {
        String message = "Search by:" +
                "\nTeacher surname = " + one +
                "\nLesson number = " + two +
                "\nDay of the week = " + three +
                "\nClass name = " + four +
                "\nIndex = " + five +
                "\nExit = " + six;
        boolean flag;
        int index;
        do {
            flag = false;
            index = -1;
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                System.out.println("Enter Teacher surname");
                index = searchByTeacherSurname(scanner.nextLine());
            }
            if (two.equals(line)) {
                System.out.println("Enter Lesson number");
                int LessonNumber = getPositiveNumber();
                scanner.nextLine();
                index = searchByLessonNumber(LessonNumber);
            }
            if (three.equals(line)) {
                System.out.println("Enter Day of the week");
                int age = getPositiveNumber();
                scanner.nextLine();
                index = searchByDayOfTheWeek(age);
            }
            if (four.equals(line)) {
                System.out.println("Enter Class name");
                index = searchByClassName(scanner.nextLine());
            }
            if (five.equals(line)) {
                int current = getPositiveNumber();
                scanner.nextLine();
                if (current < list.size()) {
                    index = current;
                } else {
                    System.out.println("Index out of bounds");
                }
            }
            if (index < 0) {
                flag = true;
                System.out.println("Not found");
            } else {
                System.out.println("Found");
            }
        } while (flag);
        return list.get(index);
    }

    private static String toString(Data data) {
        String result = data.TeacherSurname;
        result += " ";
        result += data.LessonNumber;
        result += " ";
        result += data.DayOfTheWeek;
        result += " ";
        result += data.ClassName;
        return result;
    }

    private static boolean equals(Data first, Data second) {
        boolean result = Objects.equals(first.TeacherSurname, second.TeacherSurname) &&
                Objects.equals(first.LessonNumber, second.LessonNumber) &&
                first.DayOfTheWeek == second.DayOfTheWeek &&
                Objects.equals(first.ClassName, second.ClassName);
        return result;
    }

    private static int indexOf(Data data) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (equals(data, list.get(i))) {
                result = i;
            }
            i++;
        }
        return result;
    }

    private static int searchByClassName(String ClassName) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (ClassName.equals(list.get(i).ClassName)) {
                result = i;
            }
            i++;
        }
        return result;
    }

    private static int searchByDayOfTheWeek(int DayOfTheWeek) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (DayOfTheWeek == list.get(i).DayOfTheWeek) {
                result = i;
            }
            i++;
        }
        return result;
    }

    private static int searchByLessonNumber(int LessonNumber ) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (LessonNumber == list.get(i).LessonNumber) {
                result = i;
            }
            i++;
        }
        return result;
    }

    private static int searchByTeacherSurname(String name) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (name.equals(list.get(i).TeacherSurname)) {
                result = i;
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        launch();
    }
}
