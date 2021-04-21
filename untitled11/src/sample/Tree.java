package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Comparator;

public class Tree<E> {

    public int x = 500;
    public int y = 100;
    public int spaceX = 200;
    public int spaceY = 40;
    public float weigh = 1.5F;
    public final int width = 50;
    public final int height = 20;
    public final Font font = new Font(height);
    private final Canvas canvas;

    private int size = 0;
    private Node<E> root;

    private final Comparator<E> comparator;

    public Tree(Comparator<E> comparator, Canvas canvas) {
        if (comparator == null || canvas == null)
            throw new NullPointerException();
        this.comparator = comparator;
        this.canvas = canvas;
    }

    private Node<E> getNode(E value) {
        Node<E> result = null;
        Node<E> node = root;
        int current;
        while (node != null) {
            current = comparator.compare(value, node.value);
            if (current < 0) {
                node = node.left;
            } else if (current > 0) {
                node = node.right;
            } else {
                result = node;
                node = null;
            }
        }
        return result;
    }

    private Node<E> nextOf(Node<E> node) {
        Node<E> result = null;
        if (node != null) {
            if (node.right != null) {
                result = node.right;
                while (result.left != null)
                    result = result.left;
            } else {
                result = node.parent;
                Node<E> temp = node;
                while (result != null && temp == result.right) {
                    temp = result;
                    result = result.parent;
                }
            }
        }
        return result;
    }

    private void deleteNode(Node<E> node) {
        if (node.left != null && node.right != null) {
            Node<E> temp = nextOf(node);
            node.value = temp.value;
            node = temp;
        }
        Node<E> replacement = (node.left == null ? node.right : node.left);
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            node.left = null;
            node.right = null;
            node.parent = null;
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else if (node == node.parent.right) {
                node.parent.right = null;
            }
            node.parent = null;
        }
        repaint();
        size--;
    }

    public int size() {
        return size;
    }

    public void add(E value) {
        if (root == null) {
            comparator.compare(value, value);
            root = new Node<>();
            root.value = value;
        } else {
            int current;
            Node<E> parent;
            Node<E> node = root;
            do {
                parent = node;
                current = comparator.compare(value, node.value);
                if (current < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            } while (node != null);
            Node<E> newNode = new Node<>();
            newNode.value = value;
            newNode.parent = parent;
            if (current < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
        size++;
        repaint();
    }

    public boolean contains(E value) {
        boolean result = getNode(value) != null;
        return result;
    }

    public boolean remove(E value) {
        Node<E> node = getNode(value);
        boolean result;
        if (node == null) {
            result = false;
        } else {
            deleteNode(node);
            result = true;
        }
        return result;
    }

    public boolean removeE(E element) {
        boolean result = false;
        Node<E> node = getNode(element);
        if (node != null) {
            Node<E> parent = node.parent;
            if (parent != null) {
                if (parent.left == node) {
                   parent.left = null;
                   result = true;
                } else if (parent.right == node) {
                    parent.right = null;
                    result = true;
                }
            }
        }
        if (result) {
            repaint();
        }
        return result;
    }

    public void clear() {
        size = 0;
        root = null;
        repaint();
    }

    void recursion(Node<E> node, GraphicsContext graphicsContext,
                   int x, int y, int spaceX, int spaceY) {
        if (node.left != null) {
            int newX = x - spaceX;
            int newY = y + spaceY;
            graphicsContext.strokeLine(x, y, newX, newY);
            recursion(node.left, graphicsContext, newX, newY, spaceX / 2, spaceY);
        }
        if (node.right != null) {
            int newX = x + spaceX;
            int newY = y + spaceY;
            graphicsContext.strokeLine(x, y, newX, newY);
            recursion(node.right, graphicsContext, newX, newY, spaceX / 2, spaceY);
        }
        drawNode(node, graphicsContext, x, y);
    }

    void drawNode(Node<E> node, GraphicsContext graphicsContext, int x, int y) {
        Color color = node.select ? Color.RED : Color.GRAY;
        graphicsContext.setFill(color);
        int halfX = width / 2;
        int halfY = height / 2;
        graphicsContext.fillRect(x - halfX, y - halfY, width, height);
        graphicsContext.setFill(Color.BLACK);
        String string = node.index + " " + node.value;
        graphicsContext.fillText(string, x - halfX, y + halfY);
    }

    public void repaint() {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (root != null) {
            graphicsContext2D.setLineWidth(weigh);
            graphicsContext2D.setFont(font);
            recursion(root, graphicsContext2D, x, y, spaceX, spaceY);
        }
    }
}