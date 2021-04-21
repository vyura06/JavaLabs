package com.company;

import java.io.*;
import java.net.ConnectException;
import java.util.*;

public class Main {

    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File file = searchFile();
        readFile(file);
        arraysRazdel();
        System.out.println(arrayList.toString());
    }
	public static File searchFile(){
        File file_input;
        String pathname;
        boolean isNotCorrect = true;
        do{
            System.out.println("Enter pathname: ");
            pathname = in.nextLine();
            file_input = new File(pathname);
            if(file_input.exists()){
                isNotCorrect = false;
            } else {
                System.out.println("File not found! ");
                isNotCorrect = true;
            }
        }while(isNotCorrect);
        return file_input;
    }

    public static void readFile(File file_input) throws IOException{
        Scanner scanner = new Scanner(new FileInputStream(file_input));
        while(scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                arrayList.add(scanner.nextInt());
            } else scanner.next();
        }
    }

    public static void arraysRazdel(){
        Integer a = arrayList.get(0);
        Integer b = arrayList.get(5);
        Integer c = arrayList.get(10);
        ArrayList<Integer> one = new ArrayList<>();
        one.add(a);
        one.add(b);
        one.add(c);
        Integer d = arrayList.get(1);
        Integer e = arrayList.get(6);
        Integer i = arrayList.get(11);
        ArrayList<Integer> two = new ArrayList<>();
        two.add(d);
        two.add(e);
        two.add(i);
        Integer q = arrayList.get(2);
        Integer w = arrayList.get(7);
        Integer r = arrayList.get(12);
        ArrayList<Integer> three = new ArrayList<>();
        three.add(q);
        three.add(w);
        three.add(r);
        Integer t = arrayList.get(3);
        Integer y = arrayList.get(8);
        Integer u = arrayList.get(13);
        ArrayList<Integer> four = new ArrayList<>();
        four.add(t);
        four.add(y);
        four.add(u);
        Integer g = arrayList.get(4);
        Integer h = arrayList.get(9);
        Integer j = arrayList.get(14);
        ArrayList<Integer> five = new ArrayList<>();
        int z = g+h+j;
        if ( z % 2 == 0 ) {
            System.out.println("Число четное");
        }else{
            System.out.println("Число нечетное");
        }
        five.add(z);
        System.out.println(five);
    }
    }

