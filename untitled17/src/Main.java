
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack english = new Stack();
        Stack russian = new Stack();
        boolean running = true;
        do {
            System.out.println("Enter operation:\nAdd in first = 0\nAdd in second = 1\nSort = 2\nPrint = 3");
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
                    break;
                case "3":
                    for (Node n = english.last; n != null; n = n.prev)
                        System.out.append();
                    break;
            }
        } while (running);
    }
}

class Node {
    int data;
    Node prev;
}

class Stack {
    Node last = null;

    public boolean isEmpty() {
        return last == null;
    }

    public void push(int c) {
        Node node = new Node();
        node.data = c;
        node.prev = last;
        last = node;
    }

    public int peek() {
        return last == null ? -1 : last.data;
    }

    public int pop() {
        if (last == null)
            return -1;
        int ret = last.data;
        Node p = last.prev;
        last.prev = null;
        last = p;
        return ret;
    }
}