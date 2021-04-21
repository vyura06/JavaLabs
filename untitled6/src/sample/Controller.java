package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.Objects;

public class Controller {

    @FXML
    private ImageView toggle;

    @FXML
    private ImageView infoIMG;

    @FXML
    private ImageView controlIMG;

    @FXML
    private ImageView pauseIMG;

    @FXML
    private ImageView menuImg;

    @FXML
    private VBox menuPane, tempPane, settingsPane, pausePane, info, soundPane, controlPane;

    @FXML
    private Pane gamePane;

    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11,b12,b14,b15;

    //Menu
    @FXML
    void tempInvoke() {
        setPane(tempPane);
    }


    @FXML
    void settings() {
        setPane(settingsPane);
    }

    @FXML
    void infoAction() {
        setPane(info);
    }

    @FXML
    void exitMenu() {
        System.exit(0);
    }

    //Temp

    private void exitGame() {
        game.stop();
        setPane(menuPane);
    }

    private void pause() {
        game.stop();
        setPane(pausePane);
    }
    @FXML
    void newGame() {
        setPane(gamePane);
        gamePane.getChildren().clear();
        game = new Game(gamePane, this::exitGame, this::pause);
    }

    @FXML
    void exitTemp() {
        setPane(menuPane);
    }

    @FXML
    void mouseCliked(MouseEvent event) {
        if (isEnabled) {
            disable();
        } else {
            enable();
        }
    }

    //Settings

    @FXML
    void control() {
        setPane(controlPane);
    }

    @FXML
    void controlExit() {
        setPane(settingsPane);
    }

    @FXML
    void soundSettings() {
        setPane(soundPane);
    }


    @FXML
    void soundExit() {
        setPane(settingsPane);
    }

    @FXML
    void settingsExit() {
        setPane(menuPane);
    }

    //pause
    @FXML
    void contin() {
        game.start();
        setPane(gamePane);
    }

    @FXML
    void pauseExit() {
        setPane(menuPane);
    }

    //info
    @FXML
    void infoExit() {
        setPane(menuPane);
    }


    //
    @FXML
    void initialize() {
        menuImg.setImage(new Image(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("ФОН.png"))));
        infoIMG.setImage(new Image(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("ОБ ИГРЕ.png"))));
        controlIMG.setImage(new Image(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("УПРАВЛЕНИЕ.png"))));
        pauseIMG.setImage(new Image(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("ПАУЗА.png"))));
        audioClip.setCycleCount(AudioClip.INDEFINITE);
        audioClip.play();
        setPane(menuPane);
        setFill(b1);
        setFill(b2);
        setFill(b3);
        setFill(b4);
        setFill(b5);
        setFill(b6);
        setFill(b7);
        setFill(b8);
        setFill(b9);
        setFill(b10);
        setFill(b11);
        setFill(b12);
        setFill(b14);
        setFill(b15);
        enable();
    }

    private void setFill(Button button) {
        button.setStyle("-fx-background-color: aqua");
    }

    private Game game;

    private Pane oldPane = new Pane();

    private void setPane(Pane pane) {
        oldPane.setVisible(false);
        oldPane.setDisable(true);
        pane.setVisible(true);
        pane.setDisable(false);
        oldPane = pane;
    }
    public static AudioClip loadAudioClip(String name) {
        URL url = Controller.class.getClassLoader().getResource(name);
                Objects.requireNonNull(url);
        return new AudioClip(url.toExternalForm()); //загрузка мелодии
    }
    private AudioClip audioClip = loadAudioClip("МУЗЫКА.mp3");
    private final Image imageOn = new Image(Objects.requireNonNull(
            getClass().getClassLoader().getResourceAsStream("ВКЛ.png")));
    private final Image imageOff = new Image(Objects.requireNonNull(
            getClass().getClassLoader().getResourceAsStream("ВЫКЛ.png")));

    private boolean isEnabled = false;
    private void enable() {
        toggle.setImage(imageOn);
        audioClip.play();
        isEnabled = true;
    }
    private void disable() {
        toggle.setImage(imageOff);
        audioClip.stop();
        isEnabled = false;
    }
}
