package com.company;

import java.util.NoSuchElementException;

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

    private void linkFirst(E element) {
        final Node<E> first = this.first;
        final Node<E> newNode = new Node<>(null, element, first);
        this.first = newNode;
        if (first == null)
            last = newNode;
        else
            first.previous = newNode;
        size++;
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

    private void linkBefore(E element, Node<E> node) {
        final Node<E> previous = node.previous;
        final Node<E> newNode = new Node<>(previous, element, node);
        node.previous = newNode;
        if (previous == null)
            first = newNode;
        else
            previous.next = newNode;
        size++;
    }

    private E unlinkFirst(Node<E> node) {
        final E element = node.value;
        final Node<E> next = node.next;
        node.value = null;
        node.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.previous = null;
        size--;
        return element;
    }

    private E unlinkLast(Node<E> node) {
        final E element = node.value;
        final Node<E> prev = node.previous;
        node.value = null;
        node.previous = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
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

    public E getFirst() {
        final Node<E> first = this.first;
        if (first == null)
            throw new NoSuchElementException();
        return first.value;
    }

    public E getLast() {
        final Node<E> last = this.last;
        if (last == null)
            throw new NoSuchElementException();
        return last.value;
    }

    public E removeFirst() {
        final Node<E> node = first;
        if (node == null)
            throw new NoSuchElementException();
        return unlinkFirst(node);
    }

    public E removeLast() {
        final Node<E> node = last;
        if (node == null)
            throw new NoSuchElementException();
        return unlinkLast(node);
    }

    public void addFirst(E element) {
        linkFirst(element);
    }

    public void addLast(E element) {
        linkLast(element);
    }

    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    public int size() {
        return size;
    }

    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    public boolean remove(Object object) {
        if (object == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.value == null) {
                    unlink(node);
                    return true;
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (object.equals(node.value)) {
                    unlink(node);
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        Node<E> next;
        for (Node<E> node = first; node != null; node = next) {
            next = node.next;
            node.value = null;
            node.next = null;
            node.previous = null;
        }
        first = last = null;
        size = 0;
    }

    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).value;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> node = getNode(index);
        E oldVal = node.value;
        node.value = element;
        return oldVal;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size)
            linkLast(element);
        else
            linkBefore(element, getNode(index));
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(getNode(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
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

    public int indexOf(Object object) {
        int index = 0;
        if (object == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.value == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (object.equals(x.value))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public E remove() {
        return removeFirst();
    }
}