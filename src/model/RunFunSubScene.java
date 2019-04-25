package model;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class RunFunSubScene extends SubScene {

    private final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "model/resources/orange_panel.png";

//    private boolean isHidden;

    private boolean isHidden;

    public RunFunSubScene() {

        super(new AnchorPane(), 1000, 600);
        prefWidth(1000);
        prefHeight(600);

        BackgroundImage image = new BackgroundImage(
                new Image(BACKGROUND_IMAGE, 1000, 600, false, true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
        root2.getChildren().add(createCloseButton());

        isHidden = true;
        setLayoutX(1024);
        setLayoutY(100);
//
//        isHidden = true;
//
//        setLayoutX(1024);
//        setLayoutY(180);

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-1000);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }

        transition.play();

    }

    public void moveCloseButton() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);
        transition.setToX(0);
        transition.play();

    }

    private RunFunCloseButton createCloseButton() {
        RunFunCloseButton closeButton = new RunFunCloseButton();
        closeButton.setLayoutX(40);
        closeButton.setLayoutY(30);

        closeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                moveCloseButton();
            }
        });
        return closeButton;
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

//    public void moveSubScene() {
//        TranslateTransition transition = new TranslateTransition();
//        transition.setDuration(Duration.seconds(0.3));
//        transition.setNode(this);
//
//        if (isHidden) {
//            transition.setToX(-676);
//            isHidden = false;
//        } else {
//            transition.setToX(0);
//            isHidden = true;
//        }
//
//        transition.play();
//    }
//
//    public AnchorPane getPane() {
//        return (AnchorPane) this.getRoot();
//    }

}
