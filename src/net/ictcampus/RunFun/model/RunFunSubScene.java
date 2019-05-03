package net.ictcampus.RunFun.model;

//imports
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

    private String BACKGROUND_IMAGE = "resources/orange_panel.png";
    private boolean isHidden;

    public RunFunSubScene() {
        super(new AnchorPane(), 1000, 600);
        prefWidth(1000);
        prefHeight(600);
        BackgroundImage image = new BackgroundImage(
                new Image(getClass().getResourceAsStream(BACKGROUND_IMAGE), 1000, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
        root2.getChildren().add(createCloseButton());

        isHidden = true;
        setLayoutX(1080);
        setLayoutY(100);

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-1040);
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

}
