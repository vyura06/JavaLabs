import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Program {

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
        System.out.println("Enter age:");
        int age = getPositiveNumber();
        scanner.nextLine();
        System.out.println("Enter diagnosis");
        String diagnosis = scanner.nextLine();
        count(age, diagnosis);
    }

    private static void count(int age, String diagnosis) {
        int count = 0;
        Data data;
        for (int i = 0; i < list.size(); i++) {
            data = list.get(i);
            if (data.age >= age && diagnosis.equals(data.diagnosis)) {
                System.out.println(toString(data));
                count++;
            }
        }
        System.out.println(count);
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
            printWriter.println("Name | Male | Age | Diagnosis");
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
                "\nName = " + one +
                "\nMale = " + two +
                "\nAge = " + three +
                "\nDiagnos = " + four +
                "\nAll params = " + five +
                "\nExit = " + six;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                removeByName(arrayList);
            }
            if (two.equals(line)) {
                removeByMale(arrayList);
            }
            if (three.equals(line)) {
                removeByAge(arrayList);
            }
            if (four.equals(line)) {
                removeByDiagnosis(arrayList);
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

    private static void removeByDiagnosis(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByDiagnosis(data.diagnosis);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void removeByAge(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByAge(data.age);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void removeByMale(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByMale(data.male);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void removeByName(ArrayList<Data> arrayList) {
        int index;
        Data data;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            index = searchByName(data.name);
            if (index > -1) {
                list.remove(index);
            }
        }
    }

    private static void editFromFile() {
        ArrayList<Data> arrayList = new ArrayList<>();
        fillList(arrayList);
        String message = "Enter operation:" +
                "\nName = " + one +
                "\nMale = " + two +
                "\nAge = " + three +
                "\nDiagnos = " + four +
                "\nAll params = " + five +
                "\nExit = " + six;
        boolean flag = true;
        do {
            System.out.println(message);
            String line = scanner.nextLine();
            if (one.equals(line)) {
                setByName(arrayList);
            }
            if (two.equals(line)) {
                setByMale(arrayList);
            }
            if (three.equals(line)) {
                setByAge(arrayList);
            }
            if (four.equals(line)) {
                setByDiagnosis(arrayList);
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

    private static void setByDiagnosis(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByDiagnosis(data.diagnosis);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void setByAge(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByAge(data.age);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void setByMale(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByMale(data.male);
                if (index > -1) {
                    list.set(index, data);
                } else {
                    flag = false;
                }
            } while (flag);
        }
    }

    private static void setByName(ArrayList<Data> arrayList) {
        int index;
        Data data;
        boolean flag;
        for (int i = 0; i < arrayList.size(); i++) {
            data = arrayList.get(i);
            flag = true;
            do {
                index = searchByName(data.name);
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
                "\nSet name = " + one +
                "\nSet male = " + two +
                "\nSet age = " + three +
                "\nSet diagnosis = " + four +
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
                    setName(data);
                }
                if (two.equals(line)) {
                    setMale(data);
                }
                if (three.equals(line)) {
                    setAge(data);
                }
                if (four.equals(line)) {
                    setDiagnosis(data);
                }
            }
        } while (flag);
    }

    private static void setDiagnosis(Data data) {
        System.out.println("Enter new diagnosis");
        data.diagnosis = scanner.nextLine();
    }

    private static void setAge(Data data) {
        System.out.println("Enter new age");
        data.age = getPositiveNumber();
        scanner.nextLine();
    }

    private static void setMale(Data data) {
        System.out.println("Enter new male");
        data.male = scanner.nextLine();
    }

    private static void setName(Data data) {
        System.out.println("Enter new name");
        data.name = scanner.nextLine();
    }

    private static void removeFromConsole() {
        Data data = searchData();
        list.remove(data);
    }

    private static void enterToConsole() {
        System.out.println("Name | Male | Age | Diagnosis");
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
        data.name = getString(strings, 0);
        data.male = getString(strings, 1);
        data.age = parse(getString(strings, 2));
        data.diagnosis = getString(strings, 3);
        arrayList.add(data);
    }

    private static Data searchData() {
        String message = "Search by:" +
                "\nName = " + one +
                "\nMale = " + two +
                "\nAge = " + three +
                "\nDiagnosis = " + four +
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
                System.out.println("Enter name");
                index = searchByName(scanner.nextLine());
            }
            if (two.equals(line)) {
                System.out.println("Enter male");
                index = searchByMale(scanner.nextLine());
            }
            if (three.equals(line)) {
                System.out.println("Enter age");
                int age = getPositiveNumber();
                scanner.nextLine();
                index = searchByAge(age);
            }
            if (four.equals(line)) {
                System.out.println("Enter diagnosis");
                index = searchByDiagnosis(scanner.nextLine());
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
        String result = data.name;
        result += " ";
        result += data.male;
        result += " ";
        result += data.age;
        result += " ";
        result += data.diagnosis;
        return result;
    }

    private static boolean equals(Data first, Data second) {
        boolean result = Objects.equals(first.name, second.name) &&
                Objects.equals(first.male, second.male) &&
                first.age == second.age &&
                Objects.equals(first.diagnosis, second.diagnosis);
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

    private static int searchByDiagnosis(String diagnosis) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (diagnosis.equals(list.get(i).diagnosis)) {
                result = i;
            }
            i++;
        }
        return result;
    }

    private static int searchByAge(int age) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (age == list.get(i).age) {
                result = i;
            }
            i++;
        }
        return result;
    }

    private static int searchByMale(String male) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (male.equals(list.get(i).male)) {
                result = i;
            }
            i++;
        }
        return result;
    }

    private static int searchByName(String name) {
        int i = 0;
        int result = -1;
        while (i < list.size() && result == -1) {
            if (name.equals(list.get(i).name)) {
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
