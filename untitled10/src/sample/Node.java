package sample;

public class Node<E> {
    public E value;
    public Node<E> leftChild;
    public Node<E> rightChild;
    public double x;
    public double y;
    public int level;


    @Override
    public String toString() {
        return "VALUE: " + value + "\n" + "LEVEL: " + level + "\n";
    }
}
