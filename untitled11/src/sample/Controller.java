package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.IntConsumer;

public class Controller {
    @FXML
    private Canvas canvas;

    @FXML
    private TextField containsElement, removeElement, removeChildren, add, spaceX, spaceY;

    @FXML
    private Label containsElementLabel, removeElementLabel, removeChildrenLabel, sizeLabel;

    @FXML
    void add() {
        tree.add("" + (int) (Math.random() * 50));
        sizeLabel.setText("" + tree.size());
    }

    @FXML
    void clear() {
        tree.clear();
        sizeLabel.setText("0");
    }

    @FXML
    void addPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            tree.add(add.getText());
            sizeLabel.setText("" + tree.size());
        }
    }

    @FXML
    void containsElementPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            containsElementLabel.setText("" + tree.contains(containsElement.getText()));
        }
    }

    @FXML
    void removeElementPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            removeElementLabel.setText("" + tree.remove(removeElement.getText().trim()));
        }
    }

    @FXML
    void initialize() {
        tree = new Tree<>(Controller::compare, canvas);
        IntConsumer consumer;
        consumer = value -> tree.spaceX = value;
        spaceX.setOnKeyPressed(onEnterPressed(consumer, spaceX));
        consumer = value -> tree.spaceY = value;
        spaceY.setOnKeyPressed(onEnterPressed(consumer, spaceY));
        removeChildren.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                removeChildrenLabel.setText("" + tree.removeE(removeChildren.getText().trim()));
            }
        });
    }

    private EventHandler<KeyEvent> onEnterPressed(IntConsumer setter, TextField textField) {
        if (setter == null || textField == null)
            throw new NullPointerException();
        EventHandler<KeyEvent> keyEventEventHandler = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    int value = Integer.parseInt(textField.getText().trim());
                    setter.accept(value);
                    tree.repaint();
                } catch (NumberFormatException ignored) {
                }
            }
        };
        return keyEventEventHandler;
    }

    private static int compare(String first, String second) {
        int result;
        try {
            double one = Double.parseDouble(first);
            double two = Double.parseDouble(second);
            result = Double.compare(one, two);
        } catch (NumberFormatException e) {
            result = first.compareTo(second);
        }
        return result;
    }

    private Tree<String> tree;
}