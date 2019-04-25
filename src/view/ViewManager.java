package view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.event.*;
import model.CHARACTER;
import model.CharacterPicker;
import model.InfoLabel;
import model.RunFunButton;
import model.RunFunSubScene;

import Audio.AudioPlayer;


public class ViewManager {
    private static final int WIDTH = 1080, HEIGHT = 720;
    private final static int MENU_BUTTONS_START_X = 450;
    private final static int MENU_BUTTONS_START_Y = 250;
    
    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;

    private RunFunSubScene highscoreSubScene;
    private RunFunSubScene characterChooserScene;
    private RunFunSubScene creditsSubScene;

    private RunFunSubScene sceneToHide;
    
    private HashMap<String, AudioPlayer> sfx;
    

    // private RunFunSubScene creditsSubScene;

    
    
//    String hitNormal = ("choose_your_character.mp3");
//    Media sound = new Media(new File(hitNormal).toURI().toString());
//    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    
    
    List<RunFunButton> menuButtons;

    List<CharacterPicker> characterList;
    private CHARACTER choosenCharacter;
    
    //Audio
//    private AudioPlayer chooseYourCharacter;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createSubScenes();
        
        createBackground();
       

    }


    private void showSubScene(RunFunSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes() {

        highscoreSubScene = new RunFunSubScene();
        mainPane.getChildren().add(highscoreSubScene);

        createCharacterChooserSubScene();

        creditsSubScene = new RunFunSubScene();
        mainPane.getChildren().add(creditsSubScene);

    }

   

    
    
    private void createCharacterChooserSubScene() {
        characterChooserScene = new RunFunSubScene();
        mainPane.getChildren().add(characterChooserScene);
        InfoLabel chooseCharacterLabel = new InfoLabel("W�hle deinen Charakter");
        chooseCharacterLabel.setLayoutX(260);
        chooseCharacterLabel.setLayoutY(25);
        TextField name = new TextField("");
        name.setPromptText("Gebe deinen Namen ein");
        name.setLayoutX(260);
        name.setLayoutY(100);
        name.setPrefSize(500,  50);
        name.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        characterChooserScene.getPane().getChildren().add(chooseCharacterLabel);
        characterChooserScene.getPane().getChildren().add(name);
        characterChooserScene.getPane().getChildren().add(createCharacterToChoose());
        characterChooserScene.getPane().getChildren().add(createButtonToStart());

        
    }

    private HBox createCharacterToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        characterList = new ArrayList<>();
        for (CHARACTER character : CHARACTER.values()) {
            CharacterPicker characterToPick = new CharacterPicker(character);
            characterList.add(characterToPick);
            box.getChildren().add(characterToPick);
            characterToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    
                    for(CharacterPicker character : characterList) {
                        character.setIsCircleChoosen(false);
                    }
                    characterToPick.setIsCircleChoosen(true);
                    choosenCharacter = characterToPick.getCharacter();
                }
            });
        }
        box.setLayoutX(400-(118*2));
        box.setLayoutY(250);
        return box;
    }

    private RunFunButton createButtonToStart() {
        RunFunButton startButton = new RunFunButton("Start");
        startButton.setLayoutX(400);
        startButton.setLayoutY(500);
        
        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(choosenCharacter != null) {
                    GameViewManager gameManager = new GameViewManager();
                    gameManager.createNewGame(mainStage, choosenCharacter);
                }
            }
        });
        
        return startButton;
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
        // createHelpButton();
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
                sfx=new HashMap<String,AudioPlayer>();
                sfx.put("choose", new AudioPlayer("D:/RunFun/src/view/resources/sfx/chooseyourcharacter.mp3"));
                sfx.get("choose").play();
                
            }
        });
    }

    private void createScoreButton() {
        RunFunButton highscoreButton = new RunFunButton("HIGHSCORE");
        addMenuButton(highscoreButton);
        highscoreButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(highscoreSubScene);
            }
        });
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
            }
        });
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
        Image backgroundImage = new Image("/view/resources/background/runfunTitelbildschirm.png", 1080, 720,
                false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                null);
        mainPane.setBackground(new Background(background));
    }
}
