package sample;

import java.util.Comparator;
import java.util.Objects;

public class Tree<E> {

    private final Comparator<? super E> comparator;

    public Tree(Comparator<? super E> comparator) {
        this.comparator = Objects.requireNonNull(comparator);
    }

    int size;
    int level;
    Node<E> root;
    final String LEFT = "LEFT";
    final String RIGHT = "RIGHT";
    private String treeToString = "";


    public void add(E value) {
        Node<E> node = new Node<>();
        node.value = value;
        boolean isNotCorrect = true;
        if (root == null) {
            node.x = 700 / 2.0;
            node.y = 20;
            node.level = 0;
            root = node;
            level = node.level;
            size++;
        } else {
            Node<E> current = root;
            Node<E> prev;
            int cmp;
            while (isNotCorrect) {
                prev = current;
                cmp = comparator.compare(value, prev.value);
                if (cmp < 0) {
                    current = current.leftChild;
                    if (current == null) {
                        node.x = calculateX(prev, LEFT);
                        node.y = prev.y + 20;
                        node.level = prev.level + 1;
                        prev.leftChild = node;
                        level = node.level;
                        size++;
                        isNotCorrect = false;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        node.x = calculateX(prev, RIGHT);
                        node.y = prev.y + 20;
                        node.level = prev.level + 1;
                        prev.rightChild = node;
                        level = node.level;
                        size++;
                        isNotCorrect = false;
                    }
                }
            }
        }
    }

    public double calculateX(Node<E> previous, String side) {
        int level = previous.level + 1;
        double divider = Math.pow(2, level);
        double x = 0;
        if (side.equals(LEFT)) {
            x = previous.x - (700 / divider / 2);
        } else if (side.equals(RIGHT)) {
            x = previous.x + (700 / divider / 2);
        }
        return x;
    }

    public Node<E> findNode(E value) {
        Node<E> current = root;
        int cmp;
        boolean isNotExit = true;
        while (isNotExit) {
            cmp = comparator.compare(value, current.value);
            if (cmp < 0) {
                current = current.leftChild;
            } else if (cmp > 0) {
                current = current.rightChild;
            } else {
                isNotExit = false;
            }
            if (current == null) {
                isNotExit = false;
            }
        }
        return current;
    }

    public Node<E> searchPrev(Node<E> current) {
        Node<E> startNode = root;
        Node<E> prev = null;
        int cmp;
        boolean isNotExit = true;
        while (isNotExit) {
            prev = startNode;
            cmp = comparator.compare(current.value, startNode.value);
            if (current == root) {
                isNotExit = false;
            } else if (cmp < 0) {
                startNode = startNode.leftChild;
                if (startNode != null && current == startNode) {
                    isNotExit = false;
                }
            } else {
                startNode = startNode.rightChild;
                if (startNode != null && current == startNode) {
                    isNotExit = false;
                }
            }
            if (startNode == null) {
                isNotExit = false;
            }
        }
        return prev;
    }

    public void treeToString(Node<E> startNode) {
        if (startNode != null) {
            treeToString(startNode.leftChild);
            treeToString += startNode.toString();
            treeToString(startNode.rightChild);
        }
    }

    public boolean heightOfBinaryTreeLeftRight(Node<E> node) {
        boolean isCorrect = false;
        if (heightOfBinaryTree(node.leftChild) != heightOfBinaryTree(node.rightChild)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    public int heightOfBinaryTree(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 +
                    Math.max(heightOfBinaryTree(node.leftChild),
                            heightOfBinaryTree(node.rightChild));
        }
    }

    @Override
    public String toString() {
        treeToString(root);
        return treeToString;
    }
}
