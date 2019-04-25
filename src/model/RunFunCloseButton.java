package model;


import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;


public class RunFunCloseButton extends Button{
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/grey_boxCross.png')";

    public RunFunCloseButton() {
        setPrefWidth(35);
        setPrefHeight(35);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }
    
    private void setButtonPressedStyle() {
        setPrefHeight(35);
        setLayoutY(getLayoutY() + 4);
    }
    private void setButtonReleasedStyle() {
        setPrefHeight(35);
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
