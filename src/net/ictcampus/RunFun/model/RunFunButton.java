package net.ictcampus.RunFun.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

public class RunFunButton extends Button {
    private String FONT_PATH = "src/net/ictcampus/RunFun/model/resources/kenvector_future.ttf";
    private String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('net/ictcampus/RunFun/model/resources/orange_button_pressed.png')";
    private String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('net/ictcampus/RunFun/model/resources/orange_button.png')";

    public RunFunButton(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(45);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    public void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {

        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });
        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));
    }
}
