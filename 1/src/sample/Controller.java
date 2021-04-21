package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private AnchorPane popa;

    @FXML
    private Button jopa;

    @FXML
    private Label pops;

    @FXML
    void handle(ActionEvent event) {
pops.setText("Jura-popa");
    }

}
