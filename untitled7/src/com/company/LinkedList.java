package com.company;

public class LinkedList<E> {

    private static class Node<T> {
        Node<T> next, previous;
        T value;

        public Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<E> first, last;

    public LinkedList() {
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private Node<E> getNode(int index) {
        Node<E> node;
        if (index < (size / 2)) {
            node = first;
            for (int i = 0; i < index; i++)
                node = node.next;
        } else {
            node = last;
            for (int i = size - 1; i > index; i--)
                node = node.previous;
        }
        return node;
    }

    private void linkLast(E element) {
        final Node<E> last = this.last;
        final Node<E> newNode = new Node<>(last, element, null);
        this.last = newNode;
        if (last == null)
            first = newNode;
        else
            last.next = newNode;
        size++;
    }

    private E unlink(Node<E> node) {
        final E element = node.value;
        final Node<E> next = node.next;
        final Node<E> prev = node.previous;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.previous = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.previous = prev;
            node.next = null;
        }

        node.value = null;
        size--;
        return element;
    }

    public int size() {
        return size;
    }

    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(getNode(index));
    }

    public String normal() {
        String result = null;
        if (first == null) {
            result = "[]";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            Node<E> next;
            Node<E> node = first;
            boolean flag = true;
            do {
                next = node.next;
                builder.append(node.value);
                if (next == null) {
                    result = builder.append(']').toString();
                    flag = false;
                } else {
                    builder.append(',').append(' ');
                    node = next;
                }
            } while (flag);
        }
        return result;
    }

    public String viseVersa() {
        String result = null;
        if (last == null) {
            result = "[]";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            Node<E> previous;
            Node<E> node = last;
            boolean flag = true;
            do {
                previous = node.previous;
                builder.append(node.value);
                if (previous == null) {
                    result = builder.append(']').toString();
                    flag = false;
                } else {
                    builder.append(',').append(' ');
                    node = previous;
                }
            } while (flag);
        }
        return result;
    }
}