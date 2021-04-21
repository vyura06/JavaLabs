package sample;

public class Node<T> {
    Node<T> left = null;
    Node<T> right = null;
    Node<T> parent = null;
    T value = null;
    int index = 0;
    boolean select = false;
}