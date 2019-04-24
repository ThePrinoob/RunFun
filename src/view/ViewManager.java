package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.RunFunButton;
import model.RunFunSubScene;

public class ViewManager {
    private static final int WIDTH = 1024, HEIGHT = 768;
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;
    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;

    List<RunFunButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        
        RunFunSubScene subScene = new RunFunSubScene();
        
        subScene.setLayoutX(200);
        subScene.setLayoutX(100);
        mainPane.getChildren().add(subScene);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(RunFunButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createScoreButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        RunFunButton startButton = new RunFunButton("PLAY");
        addMenuButton(startButton);

    }

    private void createScoreButton() {
        RunFunButton highscoreButton = new RunFunButton("HIGHSCORE");
        addMenuButton(highscoreButton);

    }

    private void createHelpButton() {
        RunFunButton helpButton = new RunFunButton("HELP");
        addMenuButton((helpButton));

    }

    private void createCreditsButton() {
        RunFunButton creditsButton = new RunFunButton("CREDITS");
        addMenuButton(creditsButton);

    }

    private void createExitButton() {
        RunFunButton exitButton = new RunFunButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(event -> mainStage.close());
    }

    private void createBackground() {
        Image backgroundImage = new Image("/view/resources/runfunTitelbildschirm.png", 1024, 768, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
}
