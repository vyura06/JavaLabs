package sample;

public static class BinaryTree {

    Tree root;

    void showOnScreenMyTree(int x, int y) {
        if (root != null) {
            makeNewNode(x, y, root.data);
            if (root.left != null) {
                makeNewNode(x - 140, y + 100, root.left.data);
            }
            if (root.right != null) {
                makeNewNode(x + 140, y + 100, root.right.data);
            }
        }
        addToScreenNewElementRecursive(root, x - 140, y + 100);
    }


    private void addToScreenNewElementRecursive(Tree current, int x, int y) {
        if (current.left != null) {
            y += 40;
            addToScreenLeftElement(current, x, y);
            addToScreenNewElementRecursive(current.left, x - 140, y + 70);
        }
        if (current.right != null) {
            x += 285;
            addToScreenRightElement(current, x, y);
            addToScreenNewElementRecursive(current.right, x - 140, y + 70);
        }
    }


    private void addToScreenLeftElement(Tree current, int x, int y) {
        if (current.left.left != null) {
            makeNewNode(x - 60, y + 60, current.left.left.data);
        }
        if (current.left.right != null) {
            makeNewNode(x + 60, y + 60, current.left.right.data);
        }
    }


    private void addToScreenRightElement(Tree current, int x, int y) {
        if (current.right.left != null) {
            makeNewNode(x - 60, y + 60, current.right.left.data);
        }
        if (current.right.right != null) {
            makeNewNode(x + 60, y + 60, current.right.right.data);
        }
    }


    private void makeNewNode(int x, int y, int value) {
        Circle circle = new Circle(x, y, 30);
        Text text = new Text(x - 10, y + 10, String.valueOf(value));
        text.setFont(Font.font(26));
        circle.setFill(Color.LAWNGREEN);
        circle.setStroke(Color.DARKGREEN);
        circle.setStrokeWidth(4);
        Main.group.getChildren().addAll(circle, text);
    }


    private Tree addElement(Tree current, int data) {
        if (current == null) {
            current = new Tree(data);
        } else {
            if (data < current.data) {
                current.left = addElement(current.left, data);
            } else {
                if (data > current.data) {
                    current.right = addElement(current.right, data);
                }
            }
        }
        return current;
    }

    void add(int value) {
        root = addElement(root, value);
    }


    void printRootLeftBranchRightBranch(Tree node) {
        if (node != null) {
            System.out.print(" " + node.data);
            printRootLeftBranchRightBranch(node.left);
            printRootLeftBranchRightBranch(node.right);
        }
    }


    void delete(int data) {
        root = deleteRecursive(root, data);
    }


    private Tree deleteRecursive(Tree current, int data) {
        Tree element;
        if (current == null) {
            element = null;
        } else {
            if (data == current.data) {
                element = deleteAfterFinding(current);
            } else {
                if (data < current.data) {
                    current.left = deleteRecursive(current.left, data);
                    element = current;
                } else {
                    current.right = deleteRecursive(current.right, data);
                    element = current;
                }
            }
        }
        return element;
    }

    private Tree deleteAfterFinding(Tree current) {
        Tree element;
        if (current.left == null && current.right == null) {
            element = null;
        } else {
            if (current.right == null) {
                element = current.left;
            } else {
                if (current.left == null) {
                    element = current.right;
                } else {
                    element = deleteIfThereAreTwoBranches(current);
                }
            }
        }
        return element;
    }

    private Tree deleteIfThereAreTwoBranches(Tree current) {
        Tree element;
        int smallestValue = findSmallestValue(current.right);
        current.data = smallestValue;
        current.right = deleteRecursive(current.right, smallestValue);
        element = current;
        return element;
    }


    private int findSmallestValue(Tree root) {
        int smallestElement;
        if (root.left == null) {
            smallestElement = root.data;
        } else {
            smallestElement = findSmallestValue(root.left);
        }
        return smallestElement;
    }

}

}
