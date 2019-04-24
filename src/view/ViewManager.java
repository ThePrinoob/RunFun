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
import javafx.event.*;
import model.RunFunButton;
import model.RunFunSubScene;

public class ViewManager {
    private static final int WIDTH = 1024, HEIGHT = 768;
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;
    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;
    
   
    private RunFunSubScene highscoreSubScene;
    private RunFunSubScene characterChooserScene;
    private RunFunSubScene creditsSubScene;
    
    private RunFunSubScene sceneToHide;
    
    
    //private RunFunSubScene creditsSubScene;

    List<RunFunButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        createBackground();
        
        
    }
    private void showSubScene(RunFunSubScene subScene) {
        if(sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }
    private void createSubScenes() {
        
        highscoreSubScene = new RunFunSubScene();
        mainPane.getChildren().add(highscoreSubScene);
        
        characterChooserScene = new RunFunSubScene();
        mainPane.getChildren().add(characterChooserScene);
        
        creditsSubScene = new RunFunSubScene();
        mainPane.getChildren().add(creditsSubScene);
        
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
        //createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        RunFunButton startButton = new RunFunButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            

            @Override
            public void handle(ActionEvent event) {
                   showSubScene(characterChooserScene);
        }});
    }

    private void createScoreButton() {
        RunFunButton highscoreButton = new RunFunButton("HIGHSCORE");
        addMenuButton(highscoreButton);
        highscoreButton.setOnAction(new EventHandler<ActionEvent>() {
            

            @Override
            public void handle(ActionEvent event) {
                   showSubScene(highscoreSubScene);
        }});
    }

//    private void createHelpButton() {
//        RunFunButton helpButton = new RunFunButton("HELP");
//        addMenuButton((helpButton));
//
//    }

    private void createCreditsButton() {
        RunFunButton creditsButton = new RunFunButton("CREDITS");
        addMenuButton(creditsButton);
        
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
        

            @Override
            public void handle(ActionEvent event) {
                   showSubScene(creditsSubScene);
        }});
    }

    private void createExitButton() {
        RunFunButton exitButton = new RunFunButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                mainStage.close();
            }
            
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("/view/resources/runfunTitelbildschirm.png", 1024, 768, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
}
