package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack english = new Stack();
        Stack russian = new Stack();

        for (char c : scanner.nextLine().toCharArray())
            english.push(c);
        for (char c : scanner.nextLine().toCharArray())
            russian.push(c);

        boolean running = true;
        do {
            System.out.println("Enter operation:\nAdd in first = 0\nAdd in second = 1\nSort = 2\nPrint = 3\nExit = 4");
            switch (scanner.nextLine()) {
                case "0":
                    String l = scanner.nextLine();
                    english.push(l.isEmpty() ? '\n' : l.charAt(0));
                    break;
                case "1":
                    String l1 = scanner.nextLine();
                    russian.push(l1.isEmpty() ? '\n' : l1.charAt(0));
                    break;
                case "2":
                    Stack all = new Stack();
                    while (!english.isEmpty())
                        all.push(english.pop());
                    while (!russian.isEmpty())
                        all.push(russian.pop());
                    while (!all.isEmpty()) {
                        char c = all.pop();
                        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) { //is english letter
                            sortAdd(english, c);
                        } else {
                            sortAdd(russian, c);
                        }
                    }
                    break;
                case "3":
                    System.out.println("First stack");
                    printStack(english);
                    System.out.println("Second stack");
                    printStack(russian);
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid line");
            }
        } while (running);
    }

    private static void sortAdd(Stack stack, char c) {
        if (stack.isEmpty()) {
            stack.push(c);
        } else {
            Node prev = null, cur = stack.last;
            while (cur != null) {
                if (cur.data > c)
                    break;
                prev = cur;
                cur = cur.prev;
            }
            Node newNode = new Node();
            newNode.data = c;
            newNode.prev = cur;
            if (prev == null)
                stack.last = newNode;
            else
                prev.prev = newNode;
        }
    }
    private static void printStack(Stack stack) {
        for (Node node = stack.last; node != null; node = node.prev)
            System.out.append(node.data).print(' ');
        System.out.println();
    }
}

class Node {
    char data;
    Node prev;
}

class Stack {
    Node last = null;

    public boolean isEmpty() {
        return last == null;
    }

    public void push(char c) {
        Node node = new Node();
        node.data = c;
        node.prev = last;
        last = node;
    }

    public char pop() {
        if (last == null)
            return 0;
        char ret = last.data;
        last = last.prev;
        return ret;
    }
}